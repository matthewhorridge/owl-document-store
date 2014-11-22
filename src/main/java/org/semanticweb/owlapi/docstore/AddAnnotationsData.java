package org.semanticweb.owlapi.docstore;

import com.google.common.collect.ImmutableList;
import org.semanticweb.owlapi.model.OWLAnnotation;

import javax.annotation.Nonnull;

/**
 * @author Matthew Horridge, Stanford University, Bio-Medical Informatics Research Group, Date: 26/10/2014
 */
public class AddAnnotationsData implements DocumentChangeData, HasAnnotationsList {

    private final ImmutableList<OWLAnnotation> annotations;

    public AddAnnotationsData(ImmutableList<OWLAnnotation> annotations) {
        this.annotations = annotations;
    }

    @Override
    public <R> R accept(DocumentChangeDataVisitor<R> visitor) {
        return visitor.visit(this);
    }

    @Nonnull
    @Override
    public ImmutableList<OWLAnnotation> getAnnotations() {
        return annotations;
    }
}
