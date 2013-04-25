
package entity;

public enum FType {
    referenceId ("referenceId"),
    address("address"),
    annote("annote"),
    author("author"), 
    booktitle("booktitle"),
    chapter("chapter"),
    crossref("crossref"),
    edition("edition"),
    editor("editor"),
    eprint("eprint"),
    howpublished("howpublished"),
    institution("institution"),
    journal("journal"),
    key("key"),
    month("month"),
    note("note"),
    number("number"),
    organization("organization"),
    pages("pages"),
    publisher("publisher"),
    school("school"),
    series("series"),
    title("title"),
    type("type"),
    url("url"),
    volume("volume/number"),
    year("year");
    
    private FType(String name){
        this.name = name;
    }
    
    private final String name;
    
    @Override
    public String toString() {
        return name;
    }
}
