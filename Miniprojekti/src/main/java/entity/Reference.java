/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author jarno
 */
public abstract class Reference implements Serializable {

    private int id;
    private List<Field> fields;
    private List<String> tags = new ArrayList<String>();
    private String refType;

    public abstract List<Field> myFields();

    public abstract String initType();

    public Reference() {
        initialise(myFields());
    }

    public Reference(List<Field> fields) {
        initialise(fields);
    }

    private void initialise(List<Field> list) {
        if (list != null) {
            fields = new ArrayList<Field>();
            for (Field f : list) {
                fields.add(f);
            }
        }
        this.refType = initType();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFieldValue(FType fieldName, String fieldValue) {
        for (Field field : fields) {
            if (field.getKey().equals(fieldName)) {
                field.setValue(fieldValue);
                return;
            }
        }
    }

    public String getFieldValue(FType fieldName) {
        for (Field field : fields) {
            if (field.getKey().equals(fieldName)) {
                return field.getValue();
            }
        }
        return null;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> list) {
        this.fields = list;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getReferenceType() {
        return refType;
    }

    public void setReferenceType(String type) {
        refType = type;
    }

    /**
     * Generate next default value for referenceId.
     *
     * @param refId Current value of referenceId.
     * @return Next default value.
     */
    private String generateNextValueForRefId(String refId) {
        String apu = refId.substring(0, 5);
        int n = Integer.parseInt(refId.substring(5, 7));
        n++;
        return apu + n;
    }

    /**
     * Checks if the list already contains reference with same Id.
     *
     * @param list
     * @return True if list contains, false if doesn't.
     */
    private boolean isInTheList(List<Reference> list) {
        if (list == null) {
            return false;
        }
        for (Reference r : list) {
            if (this.getFieldValue(FType.referenceId).trim().equals(r.getFieldValue(FType.referenceId).trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if referenceId is correct. ReferenceId is correct if it is not
     * given by user (then method gives default name), or if value doesn't exist
     * already in the list.
     *
     * @param ref Reference object which referenceId value is to be checked.
     * @param list List of references in which all object must have different
     * referenceId.
     * @return boolean True, if doesn't repeat in the list. False if it is
     * already in the list.
     */
    public boolean isUnique(List<Reference> list) {
        if (this.getFieldValue(FType.referenceId).trim().length() == 0) {
            this.setFieldValue(FType.referenceId, this.getReferenceType().substring(0, 4) + "_" + "10");
            while (isInTheList(list)) {
                this.setFieldValue(FType.referenceId, generateNextValueForRefId(this.getFieldValue(FType.referenceId)));
            }
            return true;
        } else if (!isInTheList(list)) {
            return true;
        } else {
            throw new IllegalArgumentException("Reference with the same referenceId already exists!");
        }
    }

    private boolean checkRefType() {
        if (this.refType == null) {
            throw new IllegalArgumentException("Reference_type must be NON-NULL!");
        } else if (!(new ReferenceFactory().getReferenceTypes().contains(this.refType))) {
            throw new IllegalArgumentException("Reference_type value is illegal!");
        }
        return true;
    }

    private boolean checkIfContainsAllRightFields() {
        List<Field> list = new ReferenceFactory().getFields(refType);
        if (this.getFields() == null) {
            throw new IllegalArgumentException("Reference doesn't contain list of fields!?");
        }
        if (this.getFields().size() != list.size()) {
            throw new IllegalArgumentException("Reference doesn't have right number of fiels!");
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey() != this.getFields().get(i).getKey()) {
                throw new IllegalArgumentException("Reference doesn't contain right fields!");
            }
        }
        return true;
    }

    private boolean checkFields() {
        for (Field field : this.getFields()) {
            if (field.isRequired() && field.getValue().trim().length() == 0) {
                throw new IllegalArgumentException("Must fill all required fields!");
            }
            if (field.isRequired() && field.getValue().length() < 2) {
                throw new IllegalArgumentException("Fields lenght must be greather then 1");
            }
        }
        return true;
    }

    /**
     * Search and replace special characters.
     *
     * @param word is text which should be modified.
     * @return text with replaced special characters.
     */
    public boolean isRegular(List<Reference> list) throws Exception {
        checkRefType();
        checkIfContainsAllRightFields();
        isUnique(list);                     // if list is empty or null, it returns true.
        checkFields();
        checkYear();
        return true;
    }

    /**
     * Checks if year field is correct.
     *
     * @param year as String
     * @return boolean parameter true if year is correct, else false.
     */
    private boolean checkYear() {
        if (this.getFieldValue(FType.year) == null) {  // if reference doesn't contain year field at all.
            return true;
        }
        String year = "";
        for (Field f : this.getFields()) {
            if (f.getKey() == FType.year) {
                year = f.getValue();
                if (year.trim().length() == 0) {
                    if (!f.isRequired()) {   // if year is not required and empty returns true.
                        return true;
                    }
                    break;
                }
            }
        }
//         if year is not null and not empty
        for (int i = 0; i < year.trim().length(); i++) {
            if (year.charAt(i) > '9' || year.charAt(i) < '0') {
                throw new IllegalArgumentException("Year value must be a number");
            }
        }
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if ((Integer.parseInt(year) > currentYear)
                || (Integer.parseInt(year) < 0)) {
            throw new IllegalArgumentException("Year value must not be negative or grether then current year!");
        }
        return true;
    }

    public boolean isEmpty() {
        for (Field f : this.getFields()) {
            if (f.getValue().length() > 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reference other = (Reference) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(refType);
        sb.append("---");
        for (Field field : getFields()) {
            if (field.isRequired()) {
                sb.append(", ");
                sb.append(field.getValue());
            } else {
                break;
            }
        }
        while (sb.toString().length() < 80) {
            sb.append(" ");
        }
        return sb.toString().substring(0, 79);
    }
}
