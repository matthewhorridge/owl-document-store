package org.semanticweb.owlapi.docstore;

import com.google.common.base.Optional;
import org.semanticweb.owlapi.model.*;

import java.util.List;

/**
 * @author Matthew Horridge, Stanford University, Bio-Medical Informatics Research Group, Date: 25/10/2014
 */
public interface DocumentStore {

    boolean containsDocument(DocumentId documentId);

    List<DocumentId> listDocuments();

    Optional<OWLOntologyID> getDocumentOntologyId(DocumentId documentId);

    Iterable<OWLAxiom> findAxioms(DocumentId documentId);

    Long countAxioms(DocumentId documentId);

    Iterable<OWLLogicalAxiom> findLogicalAxioms(DocumentId documentId);

    Long countLogicalAxioms(DocumentId documentId);

    Iterable<OWLAxiom> findAxiomsBySignature(OWLEntity entity, DocumentId documentId);

    Long countAxiomsBySignature(DocumentId documentId, OWLEntity signature);

    Iterable<OWLAxiom> findAxiomsBySubject(OWLEntity entity, DocumentId documentId);

    <A extends OWLAxiom> Iterable<A> findAxiomsByAxiomType(AxiomType<A> axiomType, DocumentId documentId);

    Long countAxiomsByAxiomType(DocumentId documentId, AxiomType<?> axiomType);

    <A extends OWLAxiom> Iterable<A> findAxiomsByAxiomTypeAndSubject(AxiomType<A> axiomType, OWLEntity entity, DocumentId documentId);


    Iterable<OWLEntity> getSignature(DocumentId documentId);

    <E extends OWLEntity> Iterable<E> getSignatureByType(DocumentId documentId, EntityType<E> type);










    /**
     * Blocks any other read or write operations until all changes have been applied.
     * @param documentChanges The document changes.  Not {@code null}.
     * @return The changes that were actually applied.
     */
    DocumentChangeList applyChanges(DocumentChangeList documentChanges);

    /**
     * Blocks any other read or write operations until the operation is complete.
     * @param writeOperation
     */
    <R> R executeWriteOperation(WriteOperation<R> writeOperation);


    DocumentStoreListenerRegistration addDocumentStoreListener(DocumentStoreListener listener);

    DocumentStoreListenerRegistration addDocumentChangeListener(DocumentChangeListener listener);

}
