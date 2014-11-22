package org.semanticweb.owlapi.docstore;

import org.semanticweb.owlapi.model.OWLAxiom;

/**
 * @author Matthew Horridge, Stanford University, Bio-Medical Informatics Research Group, Date: 25/10/2014
 */
public interface AxiomHandler<A extends OWLAxiom> {

    void handleAxiom(A axiom);
}
