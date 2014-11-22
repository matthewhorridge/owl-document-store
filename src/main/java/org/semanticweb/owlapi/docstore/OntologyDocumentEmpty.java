package org.semanticweb.owlapi.docstore;

import org.semanticweb.owlapi.model.*;

import java.util.Collections;

/**
 * Created by matthewhorridge on 04/11/2014.
 */
public class OntologyDocumentEmpty implements OntologyDocument {

    private static final OWLOntologyID OWL_ONTOLOGY_ID = new OWLOntologyID();

    private static final long ZERO = 0l;

    @Override
    public OWLOntologyID getOntologyId() {
        return OWL_ONTOLOGY_ID;
    }

    @Override
    public ChangeResult setOntologyId(OWLOntologyID ontologyId) {
        return ChangeResult.UNCHANGED;
    }

    @Override
    public ChangeResult addAxiom(OWLAxiom axiom) {
        return ChangeResult.UNCHANGED;
    }

    @Override
    public ChangeResult removeAxiom(OWLAxiom axiom) {
        return ChangeResult.UNCHANGED;
    }

    @Override
    public ChangeResult addAnnotation(OWLAnnotation annotation) {
        return ChangeResult.UNCHANGED;
    }

    @Override
    public ChangeResult removeAnnotation(OWLAnnotation annotation) {
        return ChangeResult.UNCHANGED;
    }

    @Override
    public Iterable<OWLAxiom> findAxioms() {
        return Collections.emptySet();
    }

    @Override
    public Long countAxioms() {
        return ZERO;
    }

    @Override
    public Iterable<OWLLogicalAxiom> findLogicalAxioms() {
        return Collections.emptySet();
    }

    @Override
    public Long countLogicalAxioms() {
        return ZERO;
    }

    @Override
    public Iterable<OWLAxiom> findAxiomsBySubject(OWLEntity subject) {
        return Collections.emptySet();
    }

    @Override
    public <A extends OWLAxiom> Iterable<A> findAxiomsByAxiomType(AxiomType<A> axiomType) {
        return Collections.emptySet();
    }

    @Override
    public Long countAxiomsByAxiomType(AxiomType<?> axiomType) {
        return ZERO;
    }

    @Override
    public <A extends OWLAxiom> Iterable<A> findAxiomsByTypeAndSubject(AxiomType<A> axiomType, OWLEntity subject) {
        return Collections.emptySet();
    }

    @Override
    public <A extends OWLAxiom> Iterable<A> findAxiomsByTypeAndSignature(AxiomType<A> axiomType, OWLEntity signature) {
        return Collections.emptySet();
    }

    @Override
    public Iterable<OWLAxiom> findAxiomsBySignature(OWLEntity signature) {
        return Collections.emptySet();
    }

    @Override
    public Long countAxiomsBySignature(OWLEntity signature) {
        return ZERO;
    }

    @Override
    public Iterable<OWLEntity> getSignature() {
        return Collections.emptySet();
    }

    @Override
    public <E extends OWLEntity> Iterable<E> getSignature(EntityType<E> entityType) {
        return Collections.emptySet();
    }

    @Override
    public boolean containsInSignature(OWLEntity entity) {
        return false;
    }

    @Override
    public Long getSignatureSize() {
        return ZERO;
    }

    @Override
    public <E extends OWLEntity> Long getSignatureSize(EntityType<E> entityType) {
        return ZERO;
    }
}
