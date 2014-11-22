package org.semanticweb.owlapi.docstore;

import org.semanticweb.owlapi.model.OWLOntologyID;

/**
 * @author Matthew Horridge, Stanford University, Bio-Medical Informatics Research Group, Date: 26/10/2014
 */
public interface OntologyIdHolder {

    ChangeResult setOntologyId(OWLOntologyID id);

    OWLOntologyID getOntologyId();
}
