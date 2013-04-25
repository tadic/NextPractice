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

public class Logic implements LogicInterface {

    private ReferenceFactory RFactory;

   
    private Converter converter;
    private Reference ref;        
    private Reference oldReference;// working reference
    private List<Reference> listOfRef;             // documet's list of references
    private List<Reference> filteredList;   
    private List<Reference> oldList;   // list for filtering
    private int currentRow;
    private String oldID;

    ArrayList<JTextField> listOfFields;

    public Logic() {
        RFactory = new ReferenceFactory();
        converter = new Converter();
    }
    
    @Override
    public int getCurrentRow() {
        return currentRow;
    }

    @Override
    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    @Override
    public Reference getOldReference() {
        return oldReference;
    }

    @Override
    public void setOldReference(Reference oldReference) {
        this.oldReference = oldReference;
    }
    @Override
    public String getOldId() {
        return oldID;
    }
    @Override
    public void setOldId(String oldID) {
        this.oldID = oldID;
    }
    @Override
    public List<Reference> getOldList() {
        return oldList;
    }

    @Override
    public void setOldList(List<Reference> oldList) {
        this.oldList = oldList;
    }
    
    @Override
    public Reference getRef() {
        return ref;
    }

    @Override
    public ReferenceFactory getRFactory() {
        return RFactory;
    }

    @Override
    public void setRFactory(ReferenceFactory RFactory) {
        this.RFactory = RFactory;
    }

    @Override
    public Converter getConverter() {
        return converter;
    }
    @Override
    public String currentRefToBibTex(){
        return converter.toBibTex(ref);
    }
    @Override
    public void setRef(Reference r){
        this.ref = r;
    }
    @Override
    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    @Override
    public ArrayList<JTextField> getListOfFields() {
        return listOfFields;
    }

    @Override
    public void setListOfFields(ArrayList<JTextField> listOfFields) {
        this.listOfFields = listOfFields;
    }

    @Override
    public void createNewRef(String name) {
        Reference r = new ReferenceFactory().createReference(name);
        this.ref = r;
    }

    @Override
    public List<Reference> getListOfRef() {
        return listOfRef;
    }

    @Override
    public void setListOfRef(List<Reference> listOfRef) {
        this.listOfRef = listOfRef;
    }

    @Override
    public List<Reference> getFilteredList() {
        return filteredList;
    }

    @Override
    public void setFilteredList(List<Reference> filteredList) {
        this.filteredList = filteredList;
    }

    /**
     * Returns a list of all field object of a reference
     * @param referenceType Name of the reference type known by the
     * ReferenceFactory
     * @return List<Field> References' fields
     */
    @Override
    public List<Field> getFields(String referenceType) {
        return RFactory.getFields(referenceType);
    }

//    /**
//     * List of all reference types known by REferenceFactory
//     *
//     * @return Set<String> Reference type names
//     */
//    @Override
//    public List<String> getReferenceTypes() {
//        return RFactory.getReferenceTypes();
//    }

//    
/**
 * Extract words from text
 * @param filter is text to be extracted
 * @return list of words
 */
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
/**
 * Applies filter words on reference list and set result to filteredList.
 * @param filter is list of search words
 */
    @Override
   public void setFilter(String filter) {
        if (listOfRef==null){
            filteredList=null;
            return;
        }
        filteredList = new ArrayList<Reference>();
        ArrayList<String> words = getWords(filter);
        for (Reference r: listOfRef){
            if (isContaintsWords(r, words)){
                 filteredList.add(r);
            } 
        }
    }
   /**
    * Checks if reference contains all of the words from filter criteria.
    * @param r reference to be checked
    * @param words list of criteria
    * @return 
    */
      private boolean isContaintsWords(Reference r, ArrayList<String> words){
        boolean contains;
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

    @Override
    public List<Field> getRequiredFields() {
        return RFactory.getFields(ref.getReferenceType(), true);

    }

    @Override
    public List<Field> getOptionalFields() {
        return RFactory.getFields(ref.getReferenceType(), false);
    }
}