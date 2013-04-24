/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import controllers.Converter;
import controllers.FileSaver;
import entity.Article;
import entity.Book;
import entity.Booklet;
import entity.Conference;
import entity.Inbook;
import entity.Incollection;
import entity.Inproceedings;
import entity.Manual;
import entity.Mastersthesis;
import entity.Misc;
import entity.Phdthesis;
import entity.Proceedings;
import entity.Reference;
import entity.Techreport;
import entity.Unpublished;
import exception.RepositoryException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import repositories.GenericDataFileRepository;
import repositories.GenericRepository;

/**
 *
 * @author jarno
 */
public class MainGUIController extends AbstractController {

    public static final String REFERENCE_TYPES = "ReferenceTypes";
    public static final String REFERENCES = "References";
    public static final String COLLECTED_REFERENCES = "CollectedReferences";
    public static final String CURRENTLY_SELECTED_REFERENCE = "CurrentlySelectedReference";
    private GenericRepository repository;
    public static Map<String, Class> availableReferenceSubClasses;

    static {
        availableReferenceSubClasses = new HashMap<String, Class>();
        availableReferenceSubClasses.put("Article", Article.class);
        availableReferenceSubClasses.put("Book", Book.class);
        availableReferenceSubClasses.put("Inproceedings", Inproceedings.class);
        availableReferenceSubClasses.put("Booklet", Booklet.class);
        availableReferenceSubClasses.put("Conference", Conference.class);
        availableReferenceSubClasses.put("Inbook", Inbook.class);
        availableReferenceSubClasses.put("Incollection", Incollection.class);
        availableReferenceSubClasses.put("Manual", Manual.class);
        availableReferenceSubClasses.put("Mastersthesis", Mastersthesis.class);
        availableReferenceSubClasses.put("Misc", Misc.class);
        availableReferenceSubClasses.put("Phdthesis", Phdthesis.class);
        availableReferenceSubClasses.put("Proceedings", Proceedings.class);
        availableReferenceSubClasses.put("Techreport", Techreport.class);
        availableReferenceSubClasses.put("Unpublished", Unpublished.class);

    }

    public MainGUIController(MainGUI gui, MainGUIModel model) {
        this.addModel(model);
        this.addView(gui);
        gui.registerController(this);
        repository = GenericDataFileRepository.getInstance();
    }

    public void populateReferencesWithTestData() {
        TestDataPopulator.populateTestdata(repository);
        updateReferences(Inproceedings.class);
    }

    public void createNewReference() {
        GUINewReferences ng = new GUINewReferences(this, new ArrayList<Reference>(), new ArrayList<Reference>());
        addView(ng);
        ng.initAndShow();
    }

    public void editSelectedReference() {
        MainGUIModel m = getMainGUIModel();
        Reference selected = m.getCurrentlySelected();
        if (selected != null) {
            GUIEditReference g = new GUIEditReference(selected, this);
            addView(g);
            g.initAndShow();
        }
    }

    public void SaveEditetReference(Reference r) {
        GUIEditReference g = getGUIEditReference();
        try {
            repository.update(r.getId(), r);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        g.dispose();
        removeView(g);
    }

    public void saveReferenceFromCreateNewReferenceDialog(List<Reference> r) {
        GUINewReferences g = getNewReferencesGuiView();
        repository.create(r);
        g.dispose();
        removeView(g);
    }

    private MainGUIModel getMainGUIModel() {
        for (AbstractModel m : registeredModels) {
            if (m instanceof MainGUIModel) {
                return (MainGUIModel) m;
            }
        }
        return null;
    }

    private GUINewReferences getNewReferencesGuiView() {
        for (View v : registeredViews) {
            if (v instanceof GUINewReferences) {
                return (GUINewReferences) v;
            }
        }
        return null;
    }

    private GUIEditReference getGUIEditReference() {
        for (View v : registeredViews) {
            if (v instanceof GUIEditReference) {
                return (GUIEditReference) v;
            }
        }
        return null;
    }

    public void save(File f) throws RepositoryException {
        repository.saveDataToFile(f);
    }

    public void open(File f) throws RepositoryException {
        repository.loadDataFromFile(f);
    }

    public void export(File f) throws IOException {
        MainGUIModel model = getMainGUIModel();
        FileSaver fs = new FileSaver(new Converter());
        fs.saveToFile(f.getAbsolutePath(), new ArrayList<Reference>(model.getCollectedReferences()));
    }

    public void deleteSelected() throws RepositoryException {
        MainGUIModel model = getMainGUIModel();
        Reference selected = model.getCurrentlySelected();
        if (selected != null) {
            model.setCurrentlySelected(null);
            model.removeFromCollected(selected);
            model.removeFromReferences(selected);
            repository.delete(selected.getId());
        }
    }

    public void updateCurrentlySelectedReference(Reference r) {
        MainGUIModel model = getMainGUIModel();
        model.setCurrentlySelected(r);
    }

    public void updateReferences(Class referenceType) {
        MainGUIModel model = getMainGUIModel();
        model.setReferences(repository.findAll(referenceType));
    }

    public void addReferenceToCollectedReferences(Reference r) {
        MainGUIModel model = getMainGUIModel();
        model.addToCollectedReferences(r);
    }

    public Set<String> getReferenceClassNames() {
        MainGUIModel m = getMainGUIModel();
        return m.getReferenceTypes().keySet();
    }
}
