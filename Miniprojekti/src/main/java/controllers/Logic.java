package controllers;

import entity.BaseEntity;
import entity.Field;
import entity.Inproceedings;
import entity.ReferenceFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import repositories.GenericRepository;

public class Logic implements LogicInterface {

    private GenericRepository repository;
    private ReferenceFactory RFactory;

    public Logic() {
        RFactory = new ReferenceFactory();
    }

    public Inproceedings getInproceedings() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Field> getFields(String referenceType) {
        return RFactory.getFields(referenceType);
    }

    public Set<String> getReferenceTypes() {
        return RFactory.getReferenceTypes();
    }

    public <T extends BaseEntity> T createReference(String referenceType, List<Field> fields) {
        BaseEntity created = repository.create(RFactory.createReference(referenceType, fields));
        return (T) created;
    }

    @Override
    public String[][] getRequiredFields() {
        List<Field> fields = RFactory.getFields("inproceedings", true);
        String[][] requiredFields = new String[fields.size()][2];
        for (int i = 0; i < fields.size(); i++) {
            requiredFields[i][0] = fields.get(i).getKey();
            requiredFields[i][1] = "";
        }
        return requiredFields;
    }

    @Override
    public String[][] getOptionalFields() {
        List<Field> fields = RFactory.getFields("inproceedings", false);
        String[][] optionalFields = new String[fields.size()][2];
        for (int i = 0; i < fields.size(); i++) {
            optionalFields[i][0] = fields.get(i).getKey();
            optionalFields[i][1] = "";
        }
        return optionalFields;
    }

    @Override
    public Inproceedings createReference(String[][] required, String[][] optional) {
        List<Field> fields = new ArrayList<Field>();
        for (String[] row : required) {
            fields.add(new Field(row[0], row[1], true));
        }
        for (String[] row : optional) {
            fields.add(new Field(row[0], row[1], false));
        }
        return createReference("inproceedings", fields);
    }
}
