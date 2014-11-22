package org.semanticweb.owlapi.docstore;

import com.google.common.eventbus.Subscribe;

/**
 * @author Matthew Horridge, Stanford University, Bio-Medical Informatics Research Group, Date: 26/10/2014
 */
public interface DocumentStoreListener {

    @Subscribe
    void handleDocumentAdded(DocumentAddedEvent event);

    @Subscribe
    void handleDocumentRemoved(DocumentRemovedEvent event);
}
