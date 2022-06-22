package org.sahurdayathra.BookShelfLMS.dto;

/**
 *
 * @author Thushara Supun
 */
public class BorrowDTO {

    private String borrowID;
    private String libRegNO;
    private String bookCode;
    private String userID;
    private String issuedDate;
    private String dueDate;
    private String returnedDate;

    public BorrowDTO() {
    }

    public BorrowDTO(String borrowID, String libRegNO, String bookCode, String userID, String issuedDate, String dueDate, String returnedDate) {
        this.borrowID = borrowID;
        this.libRegNO = libRegNO;
        this.bookCode = bookCode;
        this.userID = userID;
        this.issuedDate = issuedDate;
        this.dueDate = dueDate;
        this.returnedDate = returnedDate;
    }

    public String getBorrowID() {
        return borrowID;
    }

    public void setBorrowID(String borrowID) {
        this.borrowID = borrowID;
    }

    public String getLibRegNO() {
        return libRegNO;
    }

    public void setLibRegNO(String libRegNO) {
        this.libRegNO = libRegNO;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(String returnedDate) {
        this.returnedDate = returnedDate;
    }

    @Override
    public String toString() {
        return "BorrowDTO{" + "borrowID=" + borrowID + ", libRegNO=" + libRegNO + ", bookCode=" + bookCode + ", userID=" + userID + ", issuedDate=" + issuedDate + ", dueDate=" + dueDate + ", returnedDate=" + returnedDate + '}';
    }

}
