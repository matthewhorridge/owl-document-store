package org.semanticweb.owlapi.docstore;

/**
 * @author Matthew Horridge, Stanford University, Bio-Medical Informatics Research Group, Date: 26/10/2014
 */
public interface DocumentChangeDataVisitor<R> {

    R visit(AddAxiomsData addAxiomsData);

    R visit(RemoveAxiomsData removeAxiomsData);

    R visit(AddAnnotationsData addAnnotationsData);

    R visit(RemoveAnnotationsData removeAnnotationsData);

    R visit(SetOntologyIdData setOntologyIdData);
}
