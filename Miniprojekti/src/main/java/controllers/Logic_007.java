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
import javax.swing.JTextField;
import repositories.GenericDataFileRepository;
import repositories.GenericRepository;

public class Logic_007 {

    private GenericRepository repository;
    private ReferenceFactory RFactory;
    private FileSaver fileSaver;
    private Converter converter;
    
    private int currentRow;

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }
    private Reference ref;                              // working reference
    private ArrayList<Reference> listOfRef;             // documet's list of references
    private ArrayList<Reference> filteredList;          // list for filtering
    private String documentName = "newBibTex.bib";      // default name of document
    private String documentPath;                        // path 
    private boolean documentsIsSaved;
    ArrayList<JTextField> listOfFields;

    public Reference getRef() {
        return ref;
    }

    public GenericRepository getRepository() {
        return repository;
    }

    public void setRepository(GenericRepository repository) {
        this.repository = repository;
    }

    public ReferenceFactory getRFactory() {
        return RFactory;
    }

    public void setRFactory(ReferenceFactory RFactory) {
        this.RFactory = RFactory;
    }

    public FileSaver getFileSaver() {
        return fileSaver;
    }

    public void setFileSaver(FileSaver fileSaver) {
        this.fileSaver = fileSaver;
    }

    public Converter getConverter() {
        return converter;
    }
    public String currentRefToBibTex(){
     return converter.toBibTex(ref);
    }
    public void setRef(Reference r){
        this.ref = r;
    }
    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String filePath) {
       if(!filePath.toLowerCase().endsWith(".bib")){
            filePath += ".bib";
        }
        this.documentPath = filePath;
    }

    public boolean isDocumentsIsSaved() {
        return documentsIsSaved;
    }

    public void setDocumentsIsSaved(boolean documentsIsSaved) {
        this.documentsIsSaved = documentsIsSaved;
    }

    public ArrayList<JTextField> getListOfFields() {
        return listOfFields;
    }

    public void setListOfFields(ArrayList<JTextField> listOfFields) {
        this.listOfFields = listOfFields;
    }

    public void createNewRef(String name) {
        Reference r = new ReferenceFactory().createReference(name);
        this.ref = r;
    }

    public ArrayList<Reference> getListOfRef() {
        return listOfRef;
    }

    public void setListOfRef(ArrayList<Reference> listOfRef) {
        this.listOfRef = listOfRef;
    }

    public ArrayList<Reference> getFilteredList() {
        return filteredList;
    }

    public void setFilteredList(ArrayList<Reference> filteredList) {
        this.filteredList = filteredList;
    }

    public Logic_007() {
        RFactory = new ReferenceFactory();
        repository = GenericDataFileRepository.getInstance();
        converter = new Converter();
        fileSaver = new FileSaver(converter);
        repository.clearAll();
    }

    /**
     * Returns a list of all field object of a reference
     *
     * @param referenceType Name of the reference type known by the
     * ReferenceFactory
     * @return List<Field> References' fields
     */
    public List<Field> getFields(String referenceType) {
        return RFactory.getFields(referenceType);
    }

    /**
     * List of all reference types known by REferenceFactory
     *
     * @return Set<String> Reference type names
     */
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
    public Reference createReference(String referenceType, List<Field> fields) {
        Reference created = repository.create(RFactory.createReference(referenceType, fields));
        return created;
    }
    
    public Reference createReference(String referenceType) {
        Reference created = repository.create(RFactory.createReference(referenceType));
        return created;
    }

    /**
     * Loads References in specified file to memory.
     *
     * @param fileName Name of the file containing the References.
     */
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
    public void convertAllToBibtex(String fileName) throws IOException {
        List<Reference> references = repository.findAll();
        convertSelectedToBibtex(references, fileName);
    }

    /**
     * Converts selected references to bibtex and saves them in a file.
     *
     * @param references list of references to convert
     * @param fileName name of the file to be saved
     */
    public void convertSelectedToBibtex(List<Reference> references, String fileName) throws IOException {
        fileSaver.saveToFile(fileName, references);
    }

    /**
     * Gives a list of all references currently loaded in repository. Note:
     * includes only references created in this session and loaded using
     * loadFile-method.
     *
     * @return list of all references
     */
    public List<Reference> getAllReferences() {
        return repository.findAll();
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
    public void saveDocument() throws Exception {
        fileSaver.saveToFile(documentPath, listOfRef);
        documentsIsSaved = true;
    }

    public void updateReference(Reference ref) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clearAll() {
        repository.clearAll();
    }

    private ArrayList<String> getWords(String filter){
        ArrayList<String> list = new ArrayList<String>();
        System.out.println("Filer: " + filter);
        int j=0;
        for (int i=0; i<filter.length(); i++){
            if (filter.charAt(i)==' '){
                list.add(filter.substring(j,i));
                System.out.println(":" + filter.substring(j,i));
                j=i+1;
            }
        }
        list.add(filter.substring(j,filter.length()));
        return list;
    }

   public void setFilter(String filter) {
        if (listOfRef==null){
            filteredList=null;
        }
        filteredList = new ArrayList<Reference>();
        ArrayList<String> words = getWords(filter);
        for (Reference r: listOfRef){
            if (isContaintsWords(r, words)){
                 filteredList.add(r);
            } 
        }
    }
        private boolean isContaintsWords(Reference r, ArrayList<String>words){
        boolean contains = false;
        for (String word: words){
            contains = false;
            if (r.getReferenceType().contains(word)){
                contains = true;
            } else {
                for (Field f: r.getFields()){
                   if (f.getValue().contains(word)){
                       contains = true;
                       break;
                   } 
                }
            }
            if (!contains){
                return false;
            }
        }
        return true;
    }

    public List<Field> getRequiredFields() {
        return RFactory.getFields(ref.getReferenceType(), true);
    }

    public List<Field> getOptionalFields() {
        return RFactory.getFields(ref.getReferenceType(), false);
    }

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