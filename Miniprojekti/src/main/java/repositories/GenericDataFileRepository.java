package repositories;

import entity.FType;
import entity.Reference;
import exception.RepositoryException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Data file implementation of GenericRepository. This class hold's a map of all
 * entities, provides a common search and CRUD methods for them and serializes
 * them to and from file.
 *
 * @author jarno
 */
public class GenericDataFileRepository implements GenericRepository {

    private Map<Integer, Reference> objects = new HashMap<Integer, Reference>();
    private static GenericDataFileRepository instance;
    private static int lastId;

    /**
     * This method is used to get an instance of GenenericRepository.
     *
     * @return GenericFileRepository singleton
     */
    public static GenericRepository getInstance() {
        if (instance == null) {
            instance = new GenericDataFileRepository();
        }
        return instance;
    }

    /**
     * Private constructor to make sure that only one instance is used
     */
    private GenericDataFileRepository() {
    }

    @Override
    public List<Class<?>> findAllClassess() {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        for (Object o : objects.values()) {
            if (!classes.contains(o.getClass())) {
                classes.add(o.getClass());
            }
        }
        return classes;
    }
    
    

    /**
     * Loads data objects from file.
     *
     * @param file where data is stored
     * @throws RepositoryException if serializable objects were not found or
     * data fiel could not be read
     */
    @Override
    public void loadDataFromFile(File file) throws RepositoryException {
        this.objects = populateObjectsFromFile(file);
    }

    /**
     * Populates data objects from file, calculates the last id.
     *
     * @param file where data is stored
     * @return Map of Entities stored as BaseEntities
     * @throws RepositoryException if serializable objects were not found or
     * data fiel could not be read
     */
    private Map<Integer, Reference> populateObjectsFromFile(File file) throws RepositoryException {
        Map<Integer, Reference> retreivedObjects = null;
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
            retreivedObjects = (Map<Integer, Reference>) inputStream.readObject();
            inputStream.close();
            lastId = maxId();
        } catch (ClassNotFoundException e) {
            throw new RepositoryException("Serializable objets was not found.", e);
        } catch (IOException e) {
            throw new RepositoryException("Could not read data file.", e);
        }
        return retreivedObjects;
    }

    /**
     * Finds out the biggest id int data objects
     *
     * @return max id as int
     */
    private int maxId() {
        int max = 0;
        for (Integer i : objects.keySet()) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    /**
     * Writes data objects to file
     *
     * @param file where data is stored
     * @throws RepositoryException if data fiel could not be read
     */
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

    /**
     * Returns count of specified entities in repository
     *
     * @param <T> Type of entity
     * @param type class repsesenting the entity
     * @return count of specified entities in repository
     */
    @Override
    public <T extends Reference> int count(Class<T> type) {
        int count = 0;
        for (Object o : objects.values()) {
            if (o.getClass().isAssignableFrom(type)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Finds all specified entities in repository
     *
     * @param <T> Type of entity
     * @param type class repsesenting the entity
     * @return List of specified entities in repository
     */
    @Override
    public <T extends Reference> List<T> findAll(Class<T> type) {
        List<T> list = new ArrayList<T>();
        for (Object o : objects.values()) {
            if (o.getClass().isAssignableFrom(type)) {
                list.add(type.cast(o));
            }
        }
        return list;
    }
    
    /**
     * Finds all references in repository
     * @return List of references
     */
    @Override
    public List<Reference> findAll() {
        return new ArrayList(objects.values());
    }

    
    /**
     * Finds all specified entities in repository having specified field value
     *
     * @param <T> Type of entity
     * @param type Class repsesenting the entity
     * @param fieldName The entity's field name as string to be used in search.
     * @param value The search value as an object
     * @return List of specified entities in repository that match search
     * criteria. If there are no results, an empty list is returned.
     * @throws RepositoryException if field name was not found in specified
     * entity or if the field is inaccessible.
     */
    @Override
    public <T extends Reference> List<T> findByField(Class<T> type, FType fieldName, Object value) throws RepositoryException {
        List<T> list = new ArrayList<T>();
        for (Object o : objects.values()) {
            if (o.getClass().isAssignableFrom(type)) {
                Reference be = type.cast(o);
                if (be.getFieldValue(fieldName).equals(value)) {
                    list.add((T) be);
                }
            }
        }
        return list;
    }

    /**
     * Finds specified entity by it's id
     *
     * @param <T> Type of entity
     * @param type Class repsesenting the entity
     * @param id Entity's id number
     * @return Found entity of null if entity was not found
     */
    @Override
    public <T extends Reference> T findOne(Class<T> type, int id) {
        T found = null;
        Reference b = objects.get(id);
        if (b != null && b.getClass().isAssignableFrom(type)) {
            found = type.cast(b);
        }
        return found;
    }

    /**
     * Creates ne entity in the repository by giving it an unique id nubmer and
     * storing it to objects map.
     *
     * @param <T> Type of entity
     * @param entity New entity to be created
     * @return Created entity
     */
    @Override
    public <T extends Reference> T create(T entity) {
        int id = ++lastId;
        entity.setId(id);
        objects.put(id, entity);
        return entity;
    }

    /**
     * Updates specified entity and returns an updated entity.
     *
     * @param <T> Type of entity
     * @param id Id of an entity to be updated
     * @param entity Entity holding the new values
     * @return Updated entity
     * @throws RepositoryException if an entity with specified id was not found
     */
    @Override
    public <T extends Reference> T update(int id, T entity) throws RepositoryException {
        T found = (T) findOne(entity.getClass(), id);
        if (found != null) {
            entity.setId(id);
            objects.put(found.getId(), entity);
        } else {
            throw new RepositoryException("Entity with id " + id + " not found");
        }
        return entity;
    }

    /**
     * Deletes an entity with specified id from repository
     *
     * @param <T> Type of entity
     * @param id Id nubmer of an etity to be deleted
     * @throws RepositoryException if an entity with specified id was not found
     */
    @Override
    public <T extends Reference> void delete(int id) throws RepositoryException {
        if (!objects.containsKey(id)) {
            throw new RepositoryException("Entity with id " + id + " not found");
        }
        objects.remove(id);
    }

    /**
     * Clears all entity objects from reposritory and sets lastId to zero. This
     * method is mostly for testing purposes.
     */
    public void clearAll() {
        objects.clear();
        lastId = 0;
    }
}
