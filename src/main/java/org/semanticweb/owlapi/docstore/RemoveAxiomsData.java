package org.semanticweb.owlapi.docstore;

import com.google.common.collect.ImmutableList;
import org.semanticweb.owlapi.model.OWLAxiom;

/**
 * @author Matthew Horridge, Stanford University, Bio-Medical Informatics Research Group, Date: 26/10/2014
 */
public class RemoveAxiomsData implements DocumentChangeData, HasAxiomList {



    @Override
    public ImmutableList<OWLAxiom> getAxioms() {
        return null;
    }

    @Override
    public <R> R accept(DocumentChangeDataVisitor<R> visitor) {
        return null;
    }


}
