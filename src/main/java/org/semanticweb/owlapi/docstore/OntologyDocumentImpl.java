package org.semanticweb.owlapi.docstore;

import org.semanticweb.owlapi.model.*;

import javax.inject.Inject;

/**
 * @author Matthew Horridge, Stanford University, Bio-Medical Informatics Research Group, Date: 26/10/2014
 */
public class OntologyDocumentImpl implements OntologyDocument {

    private final OntologyIdHolder ontologyIdHolder;

    private final AxiomRepository axiomRepository;

    private final AnnotationIndex annotationIndex;

    @Inject
    public OntologyDocumentImpl(AxiomRepository axiomRepository, AnnotationIndex annotationIndex, OntologyIdHolder ontologyIdHolder) {
        this.ontologyIdHolder = ontologyIdHolder;
        this.axiomRepository = axiomRepository;
        this.annotationIndex = annotationIndex;
    }

    @Override
    public OWLOntologyID getOntologyId() {
        return ontologyIdHolder.getOntologyId();
    }

    @Override
    public ChangeResult setOntologyId(OWLOntologyID ontologyId) {
        return ontologyIdHolder.setOntologyId(ontologyId);
    }

    @Override
    public ChangeResult addAxiom(OWLAxiom axiom) {
        return axiomRepository.addAxiom(axiom);
    }

    @Override
    public ChangeResult removeAxiom(OWLAxiom axiom) {
        return axiomRepository.removeAxiom(axiom);
    }

    @Override
    public ChangeResult addAnnotation(OWLAnnotation annotation) {
        return annotationIndex.addAnnotation(annotation);
    }

    @Override
    public ChangeResult removeAnnotation(OWLAnnotation annotation) {
        return annotationIndex.removeAnnotation(annotation);
    }

    @Override
    public Iterable<OWLAxiom> findAxioms() {
        return axiomRepository.findAxioms();
    }

    @Override
    public Long countAxioms() {
        return axiomRepository.countAxioms();
    }

    @Override
    public Iterable<OWLLogicalAxiom> findLogicalAxioms() {
        return null;
    }

    @Override
    public Long countLogicalAxioms() {
        return 0l;
    }

    @Override
    public Iterable<OWLAxiom> findAxiomsBySubject(OWLEntity subject) {
        return null;
    }

    @Override
    public <A extends OWLAxiom> Iterable<A> findAxiomsByAxiomType(AxiomType<A> axiomType) {
        return null;
    }

    @Override
    public Long countAxiomsByAxiomType(AxiomType<?> axiomType) {
        return null;
    }

    @Override
    public <A extends OWLAxiom> Iterable<A> findAxiomsByTypeAndSubject(
            AxiomType<A> axiomType, OWLEntity subject) {
        return null;
    }

    @Override
    public <A extends OWLAxiom> Iterable<A> findAxiomsByTypeAndSignature(
            AxiomType<A> axiomType, OWLEntity signature) {
        return null;
    }

    @Override
    public Iterable<OWLAxiom> findAxiomsBySignature(OWLEntity signature) {
        return null;
    }

    @Override
    public Long countAxiomsBySignature(OWLEntity signature) {
        return null;
    }

    @Override
    public Iterable<OWLEntity> getSignature() {
        return axiomRepository.getSignature();
    }

    @Override
    public <E extends OWLEntity> Iterable<E> getSignature(EntityType<E> entityType) {
        return null;
    }

    @Override
    public boolean containsInSignature(OWLEntity entity) {
        return false;
    }

    @Override
    public Long getSignatureSize() {
        return null;
    }

    @Override
    public <E extends OWLEntity> Long getSignatureSize(EntityType<E> entityType) {
        return null;
    }
}
