package org.semanticweb.owlapi.docstore;

import com.google.common.eventbus.Subscribe;

/**
 * @author Matthew Horridge, Stanford University, Bio-Medical Informatics Research Group, Date: 26/10/2014
 */
public interface DocumentChangeListener {

    @Subscribe
    void handleDocumentChanges(DocumentChangeEvent event);
}
