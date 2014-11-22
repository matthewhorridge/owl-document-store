package org.semanticweb.owlapi.docstore;

import com.google.common.collect.*;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.AxiomSubjectProvider;

import java.util.*;

/**
 * Created by matthewhorridge on 04/11/2014.
 */
public class AxiomRepositoryImpl implements AxiomRepository {

    private Set<OWLAxiom> axioms = Sets.newHashSet();

    private Map<EntityType, Multimap<OWLEntity, OWLAxiom>> axiomsByTypeAndSignature = Maps.newHashMap();

    private Multimap<OWLEntity, OWLAxiom> axiomsBySubject = HashMultimap.create();

    private Multimap<AxiomType<?>, OWLAxiom> axiomsByType = HashMultimap.create();

    public AxiomRepositoryImpl() {
        for(EntityType<?> entityType : EntityType.values()) {
            axiomsByTypeAndSignature.put(entityType, HashMultimap.<OWLEntity, OWLAxiom>create());
        }
    }

    private Iterable<OWLEntity> getSubjects(OWLAxiom axiom) {
        AxiomSubjectProvider subjectProvider = new AxiomSubjectProvider();
        OWLObject subject = subjectProvider.getSubject(axiom);
        if(subject instanceof OWLEntity) {
            return Collections.singleton((OWLEntity) subject);
        }
        else {
            return Collections.emptySet();
        }
    }

    private boolean hasSubject(OWLAxiom axiom, OWLEntity entity) {
        AxiomSubjectProvider subjectProvider = new AxiomSubjectProvider();
        OWLObject subject = subjectProvider.getSubject(axiom);
        return entity.equals(subject);
    }

    @Override
    public ChangeResult addAxiom(OWLAxiom axiom) {
        if(axioms.add(axiom)) {
            for(OWLEntity subject : getSubjects(axiom)) {
                axiomsBySubject.put(subject, axiom);
            }
            for(OWLEntity sig : axiom.getSignature()) {
                axiomsByTypeAndSignature.get(sig.getEntityType()).put(sig, axiom);
            }
            axiomsByType.put(axiom.getAxiomType(), axiom);
            return ChangeResult.CHANGED;
        }
        else {
            return ChangeResult.UNCHANGED;
        }
    }

    @Override
    public ChangeResult removeAxiom(OWLAxiom axiom) {
        if(axioms.remove(axiom)) {
            for(OWLEntity subject : getSubjects(axiom)) {
                axiomsBySubject.remove(subject, axiom);
            }
            for(OWLEntity sig : axiom.getSignature()) {
                axiomsByTypeAndSignature.remove(sig.getEntityType()).put(sig, axiom);
            }
            axiomsByType.remove(axiom.getAxiomType(), axiom);
            return ChangeResult.CHANGED;
        }
        else {
            return ChangeResult.UNCHANGED;
        }
    }

    @Override
    public Iterable<OWLAxiom> findAxioms() {
        return ImmutableList.copyOf(axioms);
    }

    @Override
    public Long countAxioms() {
        return (long) axioms.size();
    }

    @Override
    public Iterable<OWLAxiom> findAxiomsBySubject(OWLEntity subject) {
        return ImmutableList.copyOf(axiomsBySubject.get(subject));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <A extends OWLAxiom> Iterable<A> findAxiomsByType(AxiomType<A> axiomType) {
        Collection<A> elements = (Collection<A>) axiomsByType.get(axiomType);
        return ImmutableList.copyOf(elements);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <A extends OWLAxiom> Iterable<A> findAxiomsByTypeAndSubject(AxiomType<A> axiomType, OWLEntity subject) {

        Collection<OWLAxiom> axiomsBySubject = this.axiomsBySubject.get(subject);
        Collection<A> axiomsByType = (Collection<A>) this.axiomsByType.get(axiomType);
        if(axiomsBySubject.isEmpty() || axiomsByType.isEmpty()) {
            return Collections.emptySet();
        }
        ImmutableList.Builder<A> resultBuilder = ImmutableList.builder();
        if(axiomsBySubject.size() <= axiomsByType.size()) {
            for(OWLAxiom ax : axiomsBySubject) {
                if(ax.getAxiomType().equals(axiomType)) {
                    resultBuilder.add((A) ax);
                }
            }
        }
        else {
            for(A axiom : axiomsByType) {
                if(hasSubject(axiom, subject)) {
                    resultBuilder.add(axiom);
                }
            }
        }

        return resultBuilder.build();
    }


    @Override
    public <A extends OWLAxiom> Iterable<A> findAxiomsByTypeAndSignature(AxiomType<A> axiomType, OWLEntity signature) {
        return null;
    }

    @Override
    public Iterable<OWLAxiom> findAxiomsBySignature(OWLEntity signature) {
        ImmutableList.Builder<OWLAxiom> builder = ImmutableList.builder();
        for(EntityType entityType : EntityType.values()) {
            Multimap<OWLEntity, OWLAxiom> signatureMap = axiomsByTypeAndSignature.get(entityType);
            builder.addAll(signatureMap.get(signature));
        }
        return builder.build();
    }

    @Override
    public Iterable<OWLEntity> getSignature() {
        ImmutableList.Builder<OWLEntity> builder = ImmutableList.builder();
        for(EntityType entityType : EntityType.values()) {
            Multimap<OWLEntity, OWLAxiom> signatureMap = axiomsByTypeAndSignature.get(entityType);
            builder.addAll(signatureMap.keySet());
        }
        return builder.build();
    }

    @Override
    public <E extends OWLEntity> Iterable<E> getSignature(EntityType<E> entityType) {
        Set<E> sig = (Set<E>) axiomsByTypeAndSignature.get(entityType).keySet();
        return ImmutableList.<E>copyOf(sig);
    }

    @Override
    public boolean containsInSignature(OWLEntity entity) {
        for(EntityType entityType : EntityType.values()) {
            Multimap<OWLEntity, OWLAxiom> signatureMap = axiomsByTypeAndSignature.get(entityType);
            if(signatureMap.containsKey(entity)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Long getSignatureSize() {
        long result = 0;
        for(EntityType entityType : EntityType.values()) {
            Multimap<OWLEntity, OWLAxiom> signatureMap = axiomsByTypeAndSignature.get(entityType);
            result += signatureMap.keySet().size();
        }
        return result;
    }

    @Override
    public <E extends OWLEntity> Long getSignatureSize(EntityType<E> entityType) {
        return (long) axiomsByTypeAndSignature.get(entityType).keySet().size();
    }
}
