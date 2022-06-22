package org.sahurdayathra.BookShelfLMS.dto;

/**
 *
 * @author Thushara Supun
 */
public class DamagedBookDTO {
    
    private String bookCode;
    private String isbnNO;
    private String bookTitle;
    private String bookEdition;
    private String bookCategoryCode;
    private String authorName;
    private String publisherID;
    private double price;
    private String otherDetails;
    private String status;

    public DamagedBookDTO() {
    }

    public DamagedBookDTO(String bookCode, String isbnNO, String bookTitle, String bookEdition, String bookCategoryCode, String authorName, String publisherID, double price, String otherDetails, String status) {
        this.bookCode = bookCode;
        this.isbnNO = isbnNO;
        this.bookTitle = bookTitle;
        this.bookEdition = bookEdition;
        this.bookCategoryCode = bookCategoryCode;
        this.authorName = authorName;
        this.publisherID = publisherID;
        this.price = price;
        this.otherDetails = otherDetails;
        this.status = status;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getIsbnNO() {
        return isbnNO;
    }

    public void setIsbnNO(String isbnNO) {
        this.isbnNO = isbnNO;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookEdition() {
        return bookEdition;
    }

    public void setBookEdition(String bookEdition) {
        this.bookEdition = bookEdition;
    }

    public String getBookCategoryCode() {
        return bookCategoryCode;
    }

    public void setBookCategoryCode(String bookCategoryCode) {
        this.bookCategoryCode = bookCategoryCode;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(String publisherID) {
        this.publisherID = publisherID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DamagedBookDTO{" + "bookCode=" + bookCode + ", isbnNO=" + isbnNO + ", bookTitle=" + bookTitle + ", bookEdition=" + bookEdition + ", bookCategoryCode=" + bookCategoryCode + ", authorName=" + authorName + ", publisherID=" + publisherID + ", price=" + price + ", otherDetails=" + otherDetails + ", status=" + status + '}';
    }
    
}
