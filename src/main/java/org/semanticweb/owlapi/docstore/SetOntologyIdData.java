package org.semanticweb.owlapi.docstore;

/**
 * @author Matthew Horridge, Stanford University, Bio-Medical Informatics Research Group, Date: 26/10/2014
 */
public class SetOntologyIdData implements DocumentChangeData {

    @Override
    public <R> R accept(DocumentChangeDataVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
