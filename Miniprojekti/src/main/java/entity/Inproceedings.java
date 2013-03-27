/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


public class Inproceedings{

    private String author;
    private String title;
    private String booktitle;
    private int year;

    /**
     * JAVADOC
     * @return 
     */
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
