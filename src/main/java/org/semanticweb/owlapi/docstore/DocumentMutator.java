package org.semanticweb.owlapi.docstore;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAxiom;

import java.util.List;

/**
 * @author Matthew Horridge, Stanford University, Bio-Medical Informatics Research Group, Date: 26/10/2014
 */
public class DocumentMutator {

    public Optional<DocumentChangeData> applyChange(DocumentChangeData changeData, final OntologyDocument ontologyDocument) {
        return changeData.accept(new DocumentChangeDataVisitor<Optional<DocumentChangeData>>() {
            @Override
            public Optional<DocumentChangeData> visit(AddAxiomsData addAxiomsData) {
                return handleAddAxioms(addAxiomsData, ontologyDocument);
            }

            @Override
            public Optional<DocumentChangeData> visit(RemoveAxiomsData removeAxiomsData) {
                return handleRemoveAxioms(removeAxiomsData, ontologyDocument);
            }

            @Override
            public Optional<DocumentChangeData> visit(AddAnnotationsData addAnnotationsData) {
                return handleAddAnnotations(addAnnotationsData, ontologyDocument);
            }

            @Override
            public Optional<DocumentChangeData> visit(RemoveAnnotationsData removeAnnotationsData) {
                return handleRemoveAnnotations(removeAnnotationsData, ontologyDocument);
            }

            @Override
            public Optional<DocumentChangeData> visit(SetOntologyIdData setOntologyIdData) {
                return handleSetOntologyId(setOntologyIdData, ontologyDocument);
            }
        });
    }

    private Optional<DocumentChangeData> handleAddAxioms(AddAxiomsData addAxiomsData, OntologyDocument ontologyDocument) {
        List<OWLAxiom> notAdded = Lists.newArrayList();
        for(OWLAxiom axiom : addAxiomsData.getAxioms()) {
            if(ontologyDocument.addAxiom(axiom) == ChangeResult.UNCHANGED) {
                notAdded.add(axiom);
            }
        }
        if(notAdded.size() == addAxiomsData.getAxioms().size()) {
            return Optional.absent();
        }
        if(notAdded.isEmpty()) {
            return Optional.<DocumentChangeData>of(addAxiomsData);
        }
        else {
            return Optional.<DocumentChangeData>of(addAxiomsData);
        }
    }

    private Optional<DocumentChangeData> handleRemoveAxioms(RemoveAxiomsData removeAxiomData, OntologyDocument ontologyDocument) {
        List<OWLAxiom> nonRemovedAxioms = Lists.newArrayList();
        for(OWLAxiom axiom : removeAxiomData.getAxioms()) {
            if(ontologyDocument.removeAxiom(axiom) == ChangeResult.UNCHANGED) {
                nonRemovedAxioms.add(axiom);
            }
        }
        if(nonRemovedAxioms.isEmpty()) {
            return Optional.<DocumentChangeData>of(removeAxiomData);
        }
        if(nonRemovedAxioms.size() == removeAxiomData.getAxioms().size()) {
            return Optional.absent();
        }
        return Optional.absent();
    }

    private Optional<DocumentChangeData> handleAddAnnotations(AddAnnotationsData addAnnotationsData, OntologyDocument ontologyDocument) {
        ImmutableList.Builder<OWLAnnotation> builder = ImmutableList.builder();
        for(OWLAnnotation annotation : addAnnotationsData.getAnnotations()) {
            if(ontologyDocument.addAnnotation(annotation) == ChangeResult.CHANGED) {
                builder.add(annotation);
            }
        }
        return Optional.<DocumentChangeData>of(new AddAnnotationsData(builder.build()));
    }

    private Optional<DocumentChangeData> handleRemoveAnnotationsData(RemoveAnnotationsData removeAnnotationsData, OntologyDocument ontologyDocument) {
        return Optional.absent();
    }

    private Optional<DocumentChangeData> handleRemoveAnnotations(RemoveAnnotationsData removeAnnotationsData, OntologyDocument ontologyDocument) {
        return Optional.absent();
    }

    private Optional<DocumentChangeData> handleSetOntologyId(SetOntologyIdData setOntologyIdData, OntologyDocument ontologyDocument) {
        return Optional.absent();
    }
}
