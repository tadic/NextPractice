package controllers;

import entity.Reference;
import entity.Field;
import entity.Inproceedings;
import entity.Reference;
import entity.ReferenceFactory;
import exception.RepositoryException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import repositories.GenericDataFileRepository;
import repositories.GenericRepository;

public class Logic implements LogicInterface {

    private GenericRepository repository;
    private ReferenceFactory RFactory;
    private FileSaver fileSaver;

    public Logic() {
        RFactory = new ReferenceFactory();
        repository = GenericDataFileRepository.getInstance();
        fileSaver = new FileSaver(new Converter());
    }

    @Override
    public List<Inproceedings> getInproceedings() {
        return repository.findAll(Inproceedings.class);
    }

    /**
     * Returns a list of all field object of a reference
     *
     * @param referenceType Name of the reference type known by the
     * ReferenceFactory
     * @return List<Field> References' fields
     */
    @Override
    public List<Field> getFields(String referenceType) {
        return RFactory.getFields(referenceType);
    }

    /**
     * List of all reference types known by REferenceFactory
     *
     * @return Set<String> Reference type names
     */
    @Override
    public Set<String> getReferenceTypes() {
        return RFactory.getReferenceTypes();
    }

    /**
     * Creates reference, passes it to repository and returns the reference.
     *
     * @param referenceType Name of the reference type to be created
     * @param fields List of field objects for the new reference
     * @return Created reference
     */
    @Override
    public Reference createReference(String referenceType, List<Field> fields) {
        Reference created = repository.create(RFactory.createReference(referenceType, fields));
        return (Reference) created;
    }

    @Override
    public void saveToFile(String fileName) {
        try {
            repository.saveDataToFile(new File(fileName));
        } catch (RepositoryException e) {
        }
    }

    @Override
    public void loadFile(String fileName) {
        try {
            repository.loadDataFromFile(new File(fileName));
        } catch (RepositoryException e) {
        }
    }

    @Override
    public void convertLoadedToBibtex() throws IOException {
        List<Inproceedings> list = repository.findAll(Inproceedings.class);
        for (Inproceedings inproceedings : list) {
            fileSaver.saveToFile("bibtex.txt", inproceedings);
        }
    }

    @Override
    public String[][] getRequiredFields() {
        List<Field> fields = RFactory.getFields("inproceedings", true);
        String[][] requiredFields = new String[fields.size()][2];
        for (int i = 0; i < fields.size(); i++) {
            requiredFields[i][0] = fields.get(i).getKey();
            requiredFields[i][1] = "";
        }
        return requiredFields;
    }

    @Override
    public String[][] getOptionalFields() {
        List<Field> fields = RFactory.getFields("inproceedings", false);
        String[][] optionalFields = new String[fields.size()][2];
        for (int i = 0; i < fields.size(); i++) {
            optionalFields[i][0] = fields.get(i).getKey();
            optionalFields[i][1] = "";
        }
        return optionalFields;
    }

    @Override
    public Reference createReference(String[][] required, String[][] optional) {
        List<Field> fields = new ArrayList<Field>();
        for (String[] row : required) {
            fields.add(new Field(row[0], row[1], true));
        }
        for (String[] row : optional) {
            fields.add(new Field(row[0], row[1], false));
        }
        return (Reference) createReference("inproceedings", fields);
    }
}