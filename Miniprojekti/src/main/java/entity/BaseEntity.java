/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jarno
 */
public abstract class BaseEntity implements Serializable {

    protected int id;
    protected List<Field> fields;

    protected abstract void initMyFields();

    public BaseEntity() {
        initMyFields();
    }
    
    public BaseEntity(List<Field> fields) {
        this.fields = fields;
    }    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFieldValue(String fieldName, String fieldValue) {

        for (Field field : fields) {
            if (field.getKey().equals(fieldName)) {
                field.setValue(fieldValue);
                return;
            }
        }
    }

    public String getFieldValue(String fieldName) {
        
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
        final BaseEntity other = (BaseEntity) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
