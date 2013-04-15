/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ivantadic
 */
public class Article extends Reference{
     public Article(List<Field> fields) {
        super(fields);
    }

    public Article() {
        super();
    }

    @Override
    protected void initMyFields() {
        fields = myFields();
    }
    @Override
    protected void initTypeAndTags() {
        tags = new ArrayList<String>();
        refType = "article";
    }



    private List<Field> myFields() {
        List<Field> myFields = new ArrayList<Field>();
        myFields.add(new Field(FType.referenceId, true));
        myFields.add(new Field(FType.author, true));
        myFields.add(new Field(FType.title, true));
        myFields.add(new Field(FType.journal, true));
        myFields.add(new Field(FType.year, true));

        myFields.add(new Field(FType.volume, false));
        myFields.add(new Field(FType.number, false));
        myFields.add(new Field(FType.pages, false));
        myFields.add(new Field(FType.month, false));
        myFields.add(new Field(FType.note, false));
        myFields.add(new Field(FType.key, false));

        return myFields;
    }

}
