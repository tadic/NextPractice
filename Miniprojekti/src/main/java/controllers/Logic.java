package controllers;

import entity.Article;
import entity.Book;
import entity.FType;
import entity.Field;
import entity.Inproceedings;
import entity.Reference;
import entity.ReferenceFactory;
import exception.RepositoryException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import repositories.GenericDataFileRepository;
import repositories.GenericRepository;

public class Logic implements LogicInterface {

    private GenericRepository repository;
    private ReferenceFactory RFactory;
    private FileSaver fileSaver;
    private Converter converter;

    public Logic() {
        RFactory = new ReferenceFactory();
        repository = GenericDataFileRepository.getInstance();
        converter = new Converter();
        fileSaver = new FileSaver(converter);
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
    public List<String> getReferenceTypes() {
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
        return created;
    }

    /**
     * Loads References in specified file to memory.
     *
     * @param fileName Name of the file containing the References.
     */
    @Override
    public void loadFile(String fileName) {
        try {
            repository.loadDataFromFile(new File(fileName));
        } catch (RepositoryException e) {
        }
    }

    /**
     * Converts all references currently in repository to bibtex and saves them
     * in a file.
     *
     * @param fileName name of the file to be saved
     */
    @Override
    public void convertAllToBibtex(String fileName) {
        List<Reference> references = getAll();
        convertSelectedToBibtex(references, fileName);
    }

    /**
     * Converts selected references to bibtex and saves them in a file.
     *
     * @param references list of references to convert
     * @param fileName name of the file to be saved
     */
    @Override
    public void convertSelectedToBibtex(List<Reference> references, String fileName) {
//        fileSaver.saveToFile(fileName, references);
    }

    /**
     * Gives a list of all references currently loaded in repository. Note:
     * includes only references created in this session and loaded using
     * loadFile-method.
     *
     * @return list of all references
     */
    @Override
    public List<Reference> getAllReferences() {
        return getAll();
    }

    /**
     * Gives list of references searched by field. Note: includes only
     * references created in this session and loaded using loadFile-method.
     *
     * @param type name of the reference type to search
     * @param ftype field type to use in search
     * @param value searched field value
     * @return list of found references
     * @throws RepositoryException
     */
    @Override
    public List<Reference> getReferencesByField(String type, FType ftype, Object value) throws RepositoryException {
        return repository.findByField(RFactory.getClassOfype(type), ftype, value);
    }

    /**
     * Gives a list of all references of a given type. Note: includes only
     * references created in this session and loaded using loadFile-method.
     *
     * @param type type of reference as string
     * @return list of references
     */
    @Override
    public List<Reference> getReferenceByType(String type) {
        return repository.findAll(RFactory.getClassOfype(type));
    }

    /**
     * Saves all references to file using repository. Note: NOT in
     * bibtex-format. For that you must use convertAllToBibtex- or
     * convertSelectedToBibtex-method.
     *
     * @param fileName name of the file as string
     * @throws Exception IOException or RepositoryException
     */
    @Override
    public void saveAllToFile(String fileName) throws Exception {
        repository.saveDataToFile(new File(fileName));

    }

    @Override
    public void updateReference(Reference ref) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void clearAll() {
        repository.clearAll();
    }

    private List<Reference> getAll() {
        List<Reference> l = new ArrayList<Reference>();
        l.addAll(repository.findAll(Inproceedings.class));
        l.addAll(repository.findAll(Book.class));
        l.addAll(repository.findAll(Article.class));
        return l;
    }

    @Override
    public String[][] getRequiredFields() {
        List<Field> fields = RFactory.getFields("inproceedings", true);
        String[][] requiredFields = new String[fields.size()][2];
        for (int i = 0; i < fields.size(); i++) {
            requiredFields[i][0] = fields.get(i).getKey().name();
            requiredFields[i][1] = "";
        }
        return requiredFields;
    }

    @Override
    public String[][] getOptionalFields() {
        List<Field> fields = RFactory.getFields("inproceedings", false);
        String[][] optionalFields = new String[fields.size()][2];
        for (int i = 0; i < fields.size(); i++) {
            optionalFields[i][0] = fields.get(i).getKey().toString();
            optionalFields[i][1] = "";
        }
        return optionalFields;
    }

    @Override
    public Reference createReference(String[][] required, String[][] optional) {
        List<Field> fields = new ArrayList<Field>();
        for (String[] row : required) {
            fields.add(new Field(FType.valueOf(row[0]), row[1], true));
        }
        for (String[] row : optional) {
            fields.add(new Field(FType.valueOf(row[0]), row[1], false));
        }
        return (Reference) createReference("inproceedings", fields);
    }
}