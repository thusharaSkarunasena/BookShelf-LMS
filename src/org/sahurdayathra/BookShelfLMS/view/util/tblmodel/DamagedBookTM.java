package org.sahurdayathra.BookShelfLMS.view.util.tblmodel;

/**
 *
 * @author Thushara Supun
 */
public class DamagedBookTM {
    
    private String bookCode;
    private String bookTitle;
    private String bookCategory;
    private String authorName;
    private String publisher;

    public DamagedBookTM() {
    }

    public DamagedBookTM(String bookCode, String bookTitle, String bookCategory, String authorName, String publisher) {
        this.bookCode = bookCode;
        this.bookTitle = bookTitle;
        this.bookCategory = bookCategory;
        this.authorName = authorName;
        this.publisher = publisher;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "DamagedBookTM{" + "bookCode=" + bookCode + ", bookTitle=" + bookTitle + ", bookCategory=" + bookCategory + ", authorName=" + authorName + ", publisher=" + publisher + '}';
    }
    
}
