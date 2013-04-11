
package entity;

import java.util.List;

/**
 *
 * 
 */
public interface Reference {
    public String getReferenceId();
    public void setReferenceId(String id);
    public void setFieldValue(String fieldName, String fieldValue);
    public String getFieldValue(String fieldName);
    public List<Field> getFields();
    
}
