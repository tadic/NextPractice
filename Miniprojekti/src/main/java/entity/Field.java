package entity;

import java.io.Serializable;

/**
 * Stores a field of an reference.
 */
public class Field implements Serializable {

    private FType key;
    private String value;
    private boolean required;

    /**
     * Constructor with all arguments.
     *
     * @param key Name of the field
     * @param value Value for the field
     * @param required Is the field required for the reference
     */
    public Field(FType key, String value, boolean required) {
        this.key = key;
        this.value = value;
        this.required = required;
    }

    /**
     * Constructor for a field with empty (not null) value.
     *
     * @param key Name of the field
     * @param required Is the field required for the reference
     */
    public Field(FType key, boolean required) {
        this(key, "", required);
    }

    public FType getKey() {
        return key;
    }

    public void setKey(FType key) {
        this.key = key;
    }

    public boolean hasValue() {
        return value.length() > 0;
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
 

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Field other = (Field) obj;
        if (!this.key.equals(other.getKey())) {
            return false;
        }
        if (!this.value.equals(other.getValue())) {
            return false;
        }
        if (this.required != other.isRequired()) {
            return false;
        }
        return true;
    }
}
