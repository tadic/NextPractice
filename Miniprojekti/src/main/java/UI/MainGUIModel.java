/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import entity.Reference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author jarno
 */
public class MainGUIModel extends AbstractModel {

    private Map<String, Integer> referenceTypes = new HashMap<String, Integer>();
    private List<Reference> references = new ArrayList<Reference>();
    private Set<Reference> collectedReferences = new HashSet<Reference>();
    private Reference currentlySelectedReference;

    public Map<String, Integer> getReferenceTypes() {
        return referenceTypes;
    }

    
    public void addToCollectedReferences(Reference r) {
        Set<Reference> old = new HashSet<Reference>(this.collectedReferences);
        this.collectedReferences.add(r);
        firePropertyChange(MainGUIController.COLLECTED_REFERENCES, old, collectedReferences);
    }
    
    public void removeFromReferences(Reference r) {
        List<Reference> old = new ArrayList<Reference>(this.references);
        references.remove(r);
        firePropertyChange(MainGUIController.REFERENCES, old, references);
    }
    
    public void removeFromCollected(Reference r) {
        Set<Reference> old = new HashSet<Reference>(this.collectedReferences);
        this.collectedReferences.remove(r);
        firePropertyChange(MainGUIController.COLLECTED_REFERENCES, old, collectedReferences);
    }
    
    public void setReferenceTypes(Map<String, Integer> referenceTypes) {
        Map<String, Integer> old = this.referenceTypes;
        this.referenceTypes = referenceTypes;
        firePropertyChange(MainGUIController.REFERENCE_TYPES, old, referenceTypes);
    }

    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        List<Reference> old = new ArrayList<Reference>(this.references);
        this.references = references;
        firePropertyChange(MainGUIController.REFERENCES, old, references);
    }

    public Set<Reference> getCollectedReferences() {
        return collectedReferences;
    }

    public void setCollectedReferences(Set<Reference> collectedReferences) {
        Set<Reference> old = this.collectedReferences;
        this.collectedReferences = collectedReferences;
        firePropertyChange(MainGUIController.COLLECTED_REFERENCES, old, collectedReferences);
    }

    public Reference getCurrentlySelected() {
        return currentlySelectedReference;
    }

    public void setCurrentlySelected(Reference currentlySelected) {
        Reference old = this.currentlySelectedReference;
        this.currentlySelectedReference = currentlySelected;
        firePropertyChange(MainGUIController.CURRENTLY_SELECTED_REFERENCE, old, currentlySelected);
    }
}
