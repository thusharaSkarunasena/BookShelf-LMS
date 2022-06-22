package org.sahurdayathra.BookShelfLMS.view.util.tblmodel;

/**
 *
 * @author Thushara Supun
 */
public class BookPublisherTM {

    private String bookPublisherID;
    private String name;
    private String address;
    private String contactNO;

    public BookPublisherTM() {
    }

    public BookPublisherTM(String bookPublisherID, String name, String address, String contactNO) {
        this.bookPublisherID = bookPublisherID;
        this.name = name;
        this.address = address;
        this.contactNO = contactNO;
    }

    public String getBookPublisherID() {
        return bookPublisherID;
    }

    public void setBookPublisherID(String bookPublisherID) {
        this.bookPublisherID = bookPublisherID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNO() {
        return contactNO;
    }

    public void setContactNO(String contactNO) {
        this.contactNO = contactNO;
    }

    @Override
    public String toString() {
        return "BookPublisherTM{" + "bookPublisherID=" + bookPublisherID + ", name=" + name + ", address=" + address + ", contactNO=" + contactNO + '}';
    }

}
