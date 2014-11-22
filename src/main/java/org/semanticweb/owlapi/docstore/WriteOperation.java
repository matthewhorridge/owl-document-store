package org.semanticweb.owlapi.docstore;

/**
 * @author Matthew Horridge, Stanford University, Bio-Medical Informatics Research Group, Date: 25/10/2014
 */
public interface WriteOperation<R> {

    R execute(DocumentStore documentStore);
}
