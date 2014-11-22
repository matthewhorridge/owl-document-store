package org.semanticweb.owlapi.docstore;

import com.google.common.collect.ImmutableList;
import org.semanticweb.owlapi.model.OWLAxiom;

/**
 * @author Matthew Horridge, Stanford University, Bio-Medical Informatics Research Group, Date: 26/10/2014
 */
public class AddAxiomsData implements DocumentChangeData, HasAxiomList {

    private final ImmutableList<OWLAxiom> axioms;

    public AddAxiomsData(ImmutableList<OWLAxiom> axioms) {
        this.axioms = axioms;
    }

    @Override
    public <R> R accept(DocumentChangeDataVisitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public ImmutableList<OWLAxiom> getAxioms() {
        return axioms;
    }
}
