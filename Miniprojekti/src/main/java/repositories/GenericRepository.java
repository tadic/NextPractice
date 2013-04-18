/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import entity.FType;
import entity.Reference;
import exception.RepositoryException;
import java.io.File;
import java.util.List;

/**
 *
 * @author jarno
 */
public interface GenericRepository {

    <T extends Reference> int count(Class<T> type);

    <T extends Reference> T create(T entity);

    <T extends Reference> void delete(int id) throws RepositoryException;

    <T extends Reference> List<T> findAll(Class<T> type);
    
    List<Reference> findAll();

    <T extends Reference> List<T> findByField(Class<T> type, FType fieldName, Object value) throws RepositoryException;

    <T extends Reference> T findOne(Class<T> type, int id);

    void loadDataFromFile(File file) throws RepositoryException;

    void saveDataToFile(File file) throws RepositoryException;

    <T extends Reference> T update(int id, T entity) throws RepositoryException;
    
    void clearAll();
}
