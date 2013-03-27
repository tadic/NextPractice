/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import entity.Inproceedings;
import java.util.List;

/**
 *
 * @author mikahutt
 */
public interface Inproceedingsrepository {

    List<Inproceedings> findAll();

    List<Inproceedings> findAllByAuthor();

    Inproceedings create(Inproceedings inproceedings);

    void delete(Inproceedings inproceedings);

    Inproceedings update(Inproceedings inproceedings);
}
