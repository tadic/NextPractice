/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
//editor, volume/number, series, pages, address, month, organization, publisher, note, key

public class Inproceedings extends BaseEntity {

    private String referenceId;
    private String author;
    private String title;
    private String booktitle;
    private int year;
    private String editor;
    private String volumeNumber;
    private String series;
    private String pages;
    private String address;
    private String month;
    private String organization;
    private String publisher;
    private String note;
    private String key;

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public void setVolumeNumber(String volumeNumber) {
        this.volumeNumber = volumeNumber;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public String getEditor() {
        return editor;
    }

    public String getVolumeNumber() {
        return volumeNumber;
    }

    public String getSeries() {
        return series;
    }

    public String getPages() {
        return pages;
    }

    public String getAddress() {
        return address;
    }

    public String getMonth() {
        return month;
    }

    public String getOrganization() {
        return organization;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getNote() {
        return note;
    }

    public String getKey() {
        return key;
    }

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
