package entity;

/**
 * Stores a field of an reference.
 */
public class Field extends BaseEntity {

    private String key;
    private String value;
    private boolean required;

    /**
     * Constructor with all arguments.
     *
     * @param key Name of the field
     * @param value Value for the field
     * @param required Is the field required for the reference
     */
    public Field(String key, String value, boolean required) {
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
    public Field(String key, boolean required) {
        this(key, "", required);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
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
        if (this.key.equals(other.getKey())) {
            return false;
        }
        if (this.value.equals(other.getValue())) {
            return false;
        }
        if (this.required != other.isRequired()) {
            return false;
        }
        return true;
    }
}
