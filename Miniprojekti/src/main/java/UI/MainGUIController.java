/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import controllers.Converter;
import controllers.FileSaver;
import entity.Article;
import entity.Book;
import entity.Inproceedings;
import entity.Reference;
import exception.RepositoryException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

    public void save(File f) throws RepositoryException {
        for (AbstractModel m : registeredModels) {
            if (m instanceof MainGUIModel) {
                MainGUIModel model = (MainGUIModel) m;
                repository.saveDataToFile(f);
                return;
            }
        }
    }

    public void open(File f) throws RepositoryException {
        for (AbstractModel m : registeredModels) {
            if (m instanceof MainGUIModel) {
                MainGUIModel model = (MainGUIModel) m;
                repository.loadDataFromFile(f);
                return;
            }
        }
    }

    public void export(File f) throws IOException {
        for (AbstractModel m : registeredModels) {
            if (m instanceof MainGUIModel) {
                MainGUIModel model = (MainGUIModel) m;
                FileSaver fs = new FileSaver(new Converter());
                fs.saveToFile(f.getAbsolutePath(), new ArrayList<Reference>(model.getCollectedReferences()));
                return;
            }
        }
        

    }

    public void deleteSelected() throws RepositoryException {
        for (AbstractModel m : registeredModels) {
            if (m instanceof MainGUIModel) {
                MainGUIModel model = (MainGUIModel) m;
                Reference selected = model.getCurrentlySelected();
                model.setCurrentlySelected(null);
                model.removeFromCollected(selected);
                model.removeFromReferences(selected);
                repository.delete(selected.getId());
                return;
            }
        }
    }

    public void updateCurrentlySelectedReference(Reference r) {
        for (AbstractModel m : registeredModels) {
            if (m instanceof MainGUIModel) {
                ((MainGUIModel) m).setCurrentlySelected(r);
                return;
            }
        }
    }

    public void updateReferences(Class referenceType) {
        for (AbstractModel m : registeredModels) {
            if (m instanceof MainGUIModel) {
                ((MainGUIModel) m).setReferences(repository.findAll(referenceType));
                return;
            }
        }
    }

    public void addReferenceToCollectedReferences(Reference r) {
        for (AbstractModel m : registeredModels) {
            if (m instanceof MainGUIModel) {
                ((MainGUIModel) m).addToCollectedReferences(r);
                return;
            }
        }
    }
}
