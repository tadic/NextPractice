
package entity;

import java.util.List;

public interface Reference {
    public void setFieldValue(String fieldName, String fieldValue);
    public String getFieldValue(String fieldName);
    public List<Field> getFields();
    
}
