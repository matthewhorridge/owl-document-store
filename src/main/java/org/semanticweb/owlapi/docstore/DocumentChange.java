package org.semanticweb.owlapi.docstore;

/**
 * @author Matthew Horridge, Stanford University, Bio-Medical Informatics Research Group, Date: 25/10/2014
 */
public final class DocumentChange implements HasDocumentId {

    private final DocumentId documentId;

    private final DocumentChangeData changeData;

    public DocumentChange(DocumentId documentId, DocumentChangeData changeData) {
        this.documentId = documentId;
        this.changeData = changeData;
    }

    @Override
    public DocumentId getDocumentId() {
        return documentId;
    }

    public DocumentChangeData getChangeData() {
        return changeData;
    }
}
