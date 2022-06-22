package org.sahurdayathra.BookShelfLMS.view.util.tblmodel;

/**
 *
 * @author Thushara Supun
 */
public class BookCategoryTM {

    private String bookCategoryCode;
    private String categoryName;
    private String otherDetails;

    public BookCategoryTM() {
    }

    public BookCategoryTM(String bookCategoryCode, String categoryName, String otherDetails) {
        this.bookCategoryCode = bookCategoryCode;
        this.categoryName = categoryName;
        this.otherDetails = otherDetails;
    }

    public String getBookCategoryCode() {
        return bookCategoryCode;
    }

    public void setBookCategoryCode(String bookCategoryCode) {
        this.bookCategoryCode = bookCategoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    @Override
    public String toString() {
        return "BookCategoryTM{" + "bookCategoryCode=" + bookCategoryCode + ", categoryName=" + categoryName + ", otherDetails=" + otherDetails + '}';
    }

}
