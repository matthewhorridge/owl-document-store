package org.semanticweb.owlapi.docstore;

import org.semanticweb.owlapi.model.*;

/**
 * Created by matthewhorridge on 04/11/2014.
 */
public interface OntologyDocument {

    OWLOntologyID getOntologyId();

    ChangeResult setOntologyId(OWLOntologyID ontologyId);

    ChangeResult addAxiom(OWLAxiom axiom);

    ChangeResult removeAxiom(OWLAxiom axiom);

    ChangeResult addAnnotation(OWLAnnotation annotation);

    ChangeResult removeAnnotation(OWLAnnotation annotation);

    Iterable<OWLAxiom> findAxioms();

    Long countAxioms();

    Iterable<OWLLogicalAxiom> findLogicalAxioms();

    Long countLogicalAxioms();

    Iterable<OWLAxiom> findAxiomsBySubject(OWLEntity subject);

    <A extends OWLAxiom> Iterable<A> findAxiomsByAxiomType(AxiomType<A> axiomType);

    Long countAxiomsByAxiomType(AxiomType<?> axiomType);

    <A extends OWLAxiom> Iterable<A> findAxiomsByTypeAndSubject(AxiomType<A> axiomType, OWLEntity subject);

    <A extends OWLAxiom> Iterable<A> findAxiomsByTypeAndSignature(AxiomType<A> axiomType, OWLEntity signature);

    Iterable<OWLAxiom> findAxiomsBySignature(OWLEntity signature);

    Long countAxiomsBySignature(OWLEntity signature);

    Iterable<OWLEntity> getSignature();

    <E extends OWLEntity> Iterable<E> getSignature(EntityType<E> entityType);

    boolean containsInSignature(OWLEntity entity);

    Long getSignatureSize();

    <E extends OWLEntity> Long getSignatureSize(EntityType<E> entityType);
}
