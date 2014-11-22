package org.semanticweb.owlapi.docstore;

import java.util.UUID;

/**
 * @author Matthew Horridge, Stanford University, Bio-Medical Informatics Research Group, Date: 25/10/2014
 */
public class DocumentId {

    private final String uuid;

    private DocumentId(String uuid) {
        this.uuid = uuid;
    }

    public static DocumentId getFreshDocumentId() {
        UUID uuid = UUID.randomUUID();
        return new DocumentId(uuid.toString());
    }


}
