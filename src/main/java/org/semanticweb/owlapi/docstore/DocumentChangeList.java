package org.semanticweb.owlapi.docstore;

import com.google.common.collect.ImmutableList;

/**
 * @author Matthew Horridge, Stanford University, Bio-Medical Informatics Research Group, Date: 26/10/2014
 */
public class DocumentChangeList {

    // Metadata?

    private final ImmutableList<DocumentChange> documentChanges;

    public DocumentChangeList(ImmutableList<DocumentChange> documentChanges) {
        this.documentChanges = documentChanges;
    }

    public ImmutableList<DocumentChange> getDocumentChanges() {
        return documentChanges;
    }
}
