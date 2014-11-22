package org.semanticweb.owlapi.docstore;

import com.google.common.base.Optional;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLEntity;

/**
 * @author Matthew Horridge, Stanford University, Bio-Medical Informatics Research Group, Date: 26/10/2014
 */
public interface AxiomMatcher {

    Optional<AxiomType<?>> getAxiomTypeToMatch();

    Optional<OWLEntity> getSubjectToMatch();

    Optional<OWLEntity> getMentionToMatch();

    boolean matches(OWLAxiom axiom);
}
