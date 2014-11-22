package org.semanticweb.owlapi.docstore;

import com.google.common.collect.ImmutableList;
import org.semanticweb.owlapi.model.OWLAnnotation;

/**
 * @author Matthew Horridge, Stanford University, Bio-Medical Informatics Research Group, Date: 26/10/2014
 */
public class RemoveAnnotationsData implements DocumentChangeData, HasAnnotationsList {
    @Override
    public <R> R accept(DocumentChangeDataVisitor<R> visitor) {
        return null;
    }

    @Override
    public ImmutableList<OWLAnnotation> getAnnotations() {
        return null;
    }
}
