package org.sahurdayathra.BookShelfLMS.view.util.tblmodel;

/**
 *
 * @author Thushara Supun
 */
public class BorrowTM {

    private String borrowID;
    private String libRegNO;
    private String bookCode;

    public BorrowTM() {
    }

    public BorrowTM(String borrowID, String libRegNO, String bookCode) {
        this.borrowID = borrowID;
        this.libRegNO = libRegNO;
        this.bookCode = bookCode;
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

    @Override
    public String toString() {
        return "BorrowTM{" + "borrowID=" + borrowID + ", libRegNO=" + libRegNO + ", bookCode=" + bookCode + '}';
    }

}
