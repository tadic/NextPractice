package entity;

public class Field {
    private String key;
    private String value;
    private boolean required;

    public Field(String key, String value, boolean required) {
        this.key = key;
        this.value = value;
        this.required = required;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    

    
    
    
    
    
}
