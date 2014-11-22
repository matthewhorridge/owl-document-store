package org.semanticweb.owlapi.docstore;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.eventbus.EventBus;
import com.google.inject.Provider;
import org.semanticweb.owlapi.model.*;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Matthew Horridge, Stanford University, Bio-Medical Informatics Research Group, Date: 26/10/2014
 */
public class DocumentStoreImpl implements DocumentStore {


    private static final OntologyDocumentEmpty EMPTY_DOCUMENT = new OntologyDocumentEmpty();

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private final Lock readLock = readWriteLock.readLock();

    private final Lock writeLock = readWriteLock.writeLock();


    private final DocumentMutator applicator;

    private final Map<DocumentId, OntologyDocument> documentIndexMap;

    private final EventBus eventBus;

    private final Provider<OntologyDocument> documentIndexProvider;

    public DocumentStoreImpl(EventBus eventBus, Provider<OntologyDocument> documentIndexProvider, DocumentMutator applicator) {
        this.eventBus = eventBus;
        this.documentIndexProvider = documentIndexProvider;
        this.applicator = applicator;
        documentIndexMap = Maps.newHashMap();
    }

    @Override
    public DocumentStoreListenerRegistration addDocumentStoreListener(final DocumentStoreListener listener) {
        return registerListener(listener);
    }

    @Override
    public DocumentStoreListenerRegistration addDocumentChangeListener(DocumentChangeListener listener) {
        return registerListener(listener);
    }

    private DocumentStoreListenerRegistration registerListener(final Object listener) {
        DocumentStoreListenerRegistration registration = new DocumentStoreListenerRegistration() {
            @Override
            public void removeListener() {
                eventBus.unregister(listener);
            }
        };
        eventBus.register(listener);
        return registration;
    }


    @Override
    public boolean containsDocument(DocumentId documentId) {
        try {
            readLock.lock();
            return documentIndexMap.containsKey(documentId);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public List<DocumentId> listDocuments() {
        try {
            readLock.lock();
            return ImmutableList.copyOf(documentIndexMap.keySet());
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Optional<OWLOntologyID> getDocumentOntologyId(DocumentId documentId) {
        try {
            readLock.lock();
            OntologyDocument ontologyDocument = documentIndexMap.get(documentId);
            if(ontologyDocument == null) {
                return Optional.absent();
            }
            return Optional.of(ontologyDocument.getOntologyId());
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Iterable<OWLAxiom> findAxioms(DocumentId documentId) {
        try {
            readLock.lock();
            OntologyDocument doc = getDocumentIndex(documentId, false);
            return doc.findAxioms();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Long countAxioms(DocumentId documentId) {
        try {
            readLock.lock();
            OntologyDocument doc = getDocumentIndex(documentId, false);
            return doc.countAxioms();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Iterable<OWLLogicalAxiom> findLogicalAxioms(DocumentId documentId) {
        try {
            readLock.lock();
            OntologyDocument doc = getDocumentIndex(documentId, false);
            return doc.findLogicalAxioms();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Long countLogicalAxioms(DocumentId documentId) {
        try {
            readLock.lock();
            OntologyDocument doc = getDocumentIndex(documentId, false);
            return doc.countLogicalAxioms();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Iterable<OWLAxiom> findAxiomsBySignature(OWLEntity entity, DocumentId documentId) {
        try {
            readLock.lock();
            OntologyDocument doc = getDocumentIndex(documentId, false);
            return doc.findAxiomsBySignature(entity);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Long countAxiomsBySignature(DocumentId documentId, OWLEntity signature) {
        try {
            readLock.lock();
            OntologyDocument doc = getDocumentIndex(documentId, false);
            return doc.countAxiomsBySignature(signature);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Iterable<OWLAxiom> findAxiomsBySubject(OWLEntity entity, DocumentId documentId) {
        try {
            readLock.lock();
            OntologyDocument doc = getDocumentIndex(documentId, false);
            return doc.findAxiomsBySubject(entity);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public <A extends OWLAxiom> Iterable<A> findAxiomsByAxiomType(AxiomType<A> axiomType, DocumentId documentId) {
        try {
            readLock.lock();
            OntologyDocument doc = getDocumentIndex(documentId, false);
            return doc.findAxiomsByAxiomType(axiomType);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Long countAxiomsByAxiomType(DocumentId documentId, AxiomType<?> axiomType) {
        try {
            readLock.lock();
            OntologyDocument doc = getDocumentIndex(documentId, false);
            return doc.countAxiomsByAxiomType(axiomType);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public <A extends OWLAxiom> Iterable<A> findAxiomsByAxiomTypeAndSubject(AxiomType<A> axiomType, OWLEntity entity, DocumentId documentId) {
        try {
            readLock.lock();
            OntologyDocument doc = getDocumentIndex(documentId, false);
            return doc.findAxiomsByTypeAndSubject(axiomType, entity);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Iterable<OWLEntity> getSignature(DocumentId documentId) {
        try {
            readLock.lock();
            OntologyDocument doc = getDocumentIndex(documentId, false);
            return doc.getSignature();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public <E extends OWLEntity> Iterable<E> getSignatureByType(DocumentId documentId, EntityType<E> type) {
        try {
            readLock.lock();
            OntologyDocument doc = getDocumentIndex(documentId, false);
            return doc.getSignature(type);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public <R> R executeWriteOperation(WriteOperation<R> writeOperation) {
        try {
            writeLock.lock();
            R result = writeOperation.execute(this);
            checkNotNull(result);
            return result;
        } finally {
            writeLock.unlock();
        }

    }

    @Override
    public DocumentChangeList applyChanges(DocumentChangeList documentChanges) {
        try {
            writeLock.lock();
            ImmutableList.Builder<DocumentChange> appliedChanges = ImmutableList.builder();
            for(DocumentChange change : documentChanges.getDocumentChanges()) {
                DocumentId documentId = change.getDocumentId();
                final OntologyDocument ontologyDocument = getDocumentIndex(documentId, true);
                Optional<DocumentChangeData> appliedChange = applicator.applyChange(change.getChangeData(), ontologyDocument);
                if(appliedChange.isPresent()) {
                    appliedChanges.add(new DocumentChange(documentId, appliedChange.get()));
                }
            }
            return new DocumentChangeList(appliedChanges.build());
        } finally {
            writeLock.unlock();
        }
    }




    private OntologyDocument getDocumentIndex(DocumentId documentId, boolean create) {
        final OntologyDocument ontologyDocument;
        OntologyDocument doc = documentIndexMap.get(documentId);
        if(doc == null) {
            if(create) {
                ontologyDocument = documentIndexProvider.get();
                documentIndexMap.put(documentId, ontologyDocument);
            }
            else {
                ontologyDocument = EMPTY_DOCUMENT;
            }

        }
        else {
            ontologyDocument = doc;
        }
        return ontologyDocument;
    }
}
