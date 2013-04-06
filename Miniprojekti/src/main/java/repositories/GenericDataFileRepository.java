/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import entity.BaseEntity;
import exception.RepositoryException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jarno
 */
public class GenericDataFileRepository implements GenericRepository {

    private Map<Integer, BaseEntity> objects = new HashMap<Integer, BaseEntity>();
    private static GenericDataFileRepository instance;
    private static int nextId;

    public static GenericRepository getInstance() {
        if (instance == null) {
            instance = new GenericDataFileRepository();
        }
        return instance;
    }

    private GenericDataFileRepository() {
    }

    @Override
    public void loadDataFromFile(File file) throws RepositoryException {
        this.objects = populateObjectsFromFile(file);
    }

    private Map<Integer, BaseEntity> populateObjectsFromFile(File file) throws RepositoryException {
        Map<Integer, BaseEntity> retreivedObjects = null;
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
            retreivedObjects = (Map<Integer, BaseEntity>) inputStream.readObject();
            inputStream.close();
            nextId = maxId();
        } catch (ClassNotFoundException e) {
            throw new RepositoryException("Serializable objets was not found.", e);
        } catch (IOException e) {
            throw new RepositoryException("Could not read data file.", e);
        }
        return retreivedObjects;
    }

    private int maxId() {
        int max = 0;
        for (Integer i : objects.keySet()) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    @Override
    public void saveDataToFile(File file) throws RepositoryException {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            outputStream.writeObject(objects);
            outputStream.close();
        } catch (IOException e) {
            throw new RepositoryException("Could not write data to file.", e);
        }

    }

    @Override
    public <T extends BaseEntity> int count(Class<T> type) {
        int count = 0;
        for (Object o : objects.values()) {
            if (o.getClass().isAssignableFrom(type)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public <T extends BaseEntity> List<T> findAll(Class<T> type) {
        List<T> list = new ArrayList<T>();
        for (Object o : objects.values()) {
            if (o.getClass().isAssignableFrom(type)) {
                list.add(type.cast(o));
            }
        }
        return list;
    }

    @Override
    public <T extends BaseEntity> List<T> findByField(Class<T> type, String fieldName, Object value) throws RepositoryException {
        List<T> list = new ArrayList<T>();
        for (Object o : objects.values()) {
            try {
                if (o.getClass().isAssignableFrom(type)) {
                    Field f = o.getClass().getDeclaredField(fieldName);
                    f.setAccessible(true);
                    Object fieldValue = f.get(o);
                    if (fieldValue.equals(value)) {
                        list.add(type.cast(o));
                    }
                }
            } catch (NoSuchFieldException e) {
                throw new RepositoryException("Field " + fieldName + " was not found in entity " + type.getName(), e);
            } catch (IllegalAccessException e) {
                throw new RepositoryException("Field " + fieldName + " in entity " + type.getName() + " is inaccessible", e);
            }
        }
        return list;
    }

    @Override
    public <T extends BaseEntity> T findOne(Class<T> type, int id) {
        T found = null;
        BaseEntity b = objects.get(id);
        if (b != null && b.getClass().isAssignableFrom(type)) {
            found = type.cast(b);
        }
        return found;
    }

    @Override
    public <T extends BaseEntity> T create(T entity) {
        int id = ++nextId;
        entity.setId(id);
        objects.put(id, entity);
        return entity;
    }

    @Override
    public <T extends BaseEntity> T update(int id, T entity) throws RepositoryException {
        T found = (T) findOne(entity.getClass(), id);
        if (found != null) {
            entity.setId(id);
            objects.put(found.getId(), entity);
        } else {
            throw new RepositoryException("Entity with id " + id + " not found");
        }
        return entity;
    }

    @Override
    public <T extends BaseEntity> void delete(int id) throws RepositoryException {
        if (!objects.containsKey(id)) {
            throw new RepositoryException("Entity with id " + id + " not found");
        }
        objects.remove(id);
    }

    public void clearAll() {
        objects.clear();
        nextId = 0;
    }
}
