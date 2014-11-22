package org.semanticweb.owlapi.docstore;

import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.EntityType;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLEntity;

/**
 * @author Matthew Horridge, Stanford University, Bio-Medical Informatics Research Group, Date: 26/10/2014
 */
public interface AxiomRepository {

    ChangeResult addAxiom(OWLAxiom axiom);

    ChangeResult removeAxiom(OWLAxiom axiom);


    Iterable<OWLAxiom> findAxioms();

    Long countAxioms();

    Iterable<OWLAxiom> findAxiomsBySubject(OWLEntity subject);

    <A extends OWLAxiom> Iterable<A> findAxiomsByType(AxiomType<A> axiomType);

    <A extends OWLAxiom> Iterable<A> findAxiomsByTypeAndSubject(AxiomType<A> axiomType, OWLEntity subject);

    <A extends OWLAxiom> Iterable<A> findAxiomsByTypeAndSignature(AxiomType<A> axiomType, OWLEntity signature);

    Iterable<OWLAxiom> findAxiomsBySignature(OWLEntity signature);



    Iterable<OWLEntity> getSignature();

    <E extends OWLEntity> Iterable<E> getSignature(EntityType<E> entityType);

    boolean containsInSignature(OWLEntity entity);

    Long getSignatureSize();

    <E extends OWLEntity> Long getSignatureSize(EntityType<E> entityType);
}
