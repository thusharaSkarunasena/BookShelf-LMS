package org.sahurdayathra.BookShelfLMS.entity;

/**
 *
 * @author Thushara Supun
 */
public class BookPublisher {

    private String bookPublisherID;
    private String name;
    private String address_no;
    private String address_street;
    private String address_village;
    private String address_city;
    private String email;
    private String contactNO;
    private String otherDetails;

    public BookPublisher() {
    }

    public BookPublisher(String bookPublisherID, String name, String address_no, String address_street, String address_village, String address_city, String email, String contactNO, String otherDetails) {
        this.bookPublisherID = bookPublisherID;
        this.name = name;
        this.address_no = address_no;
        this.address_street = address_street;
        this.address_village = address_village;
        this.address_city = address_city;
        this.email = email;
        this.contactNO = contactNO;
        this.otherDetails = otherDetails;
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

    public String getAddress_no() {
        return address_no;
    }

    public void setAddress_no(String address_no) {
        this.address_no = address_no;
    }

    public String getAddress_street() {
        return address_street;
    }

    public void setAddress_street(String address_street) {
        this.address_street = address_street;
    }

    public String getAddress_village() {
        return address_village;
    }

    public void setAddress_village(String address_village) {
        this.address_village = address_village;
    }

    public String getAddress_city() {
        return address_city;
    }

    public void setAddress_city(String address_city) {
        this.address_city = address_city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNO() {
        return contactNO;
    }

    public void setContactNO(String contactNO) {
        this.contactNO = contactNO;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    @Override
    public String toString() {
        return "BookPublisher{" + "bookPublisherID=" + bookPublisherID + ", name=" + name + ", address_no=" + address_no + ", address_street=" + address_street + ", address_village=" + address_village + ", address_city=" + address_city + ", email=" + email + ", contactNO=" + contactNO + ", otherDetails=" + otherDetails + '}';
    }

}
