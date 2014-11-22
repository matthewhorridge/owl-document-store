package org.semanticweb.owlapi.docstore;

import org.semanticweb.owlapi.model.OWLAnnotation;

/**
 * @author Matthew Horridge, Stanford University, Bio-Medical Informatics Research Group, Date: 26/10/2014
 */
public interface AnnotationIndex {
    ChangeResult addAnnotation(OWLAnnotation annotation);

    ChangeResult removeAnnotation(OWLAnnotation annotation);
}
