package org.sahurdayathra.BookShelfLMS.view.util.tblmodel;

/**
 *
 * @author Thushara Supun
 */
public class MemberTM {

    private String libRegNO;
    private String admissionNO;
    private String name;
    private String address;

    public MemberTM() {
    }

    public MemberTM(String libRegNO, String admissionNO, String name, String address) {
        this.libRegNO = libRegNO;
        this.admissionNO = admissionNO;
        this.name = name;
        this.address = address;
    }

    public String getLibRegNO() {
        return libRegNO;
    }

    public void setLibRegNO(String libRegNO) {
        this.libRegNO = libRegNO;
    }

    public String getAdmissionNO() {
        return admissionNO;
    }

    public void setAdmissionNO(String admissionNO) {
        this.admissionNO = admissionNO;
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

    @Override
    public String toString() {
        return "StudentTM{" + "libRegNO=" + libRegNO + ", admissionNO=" + admissionNO + ", name=" + name + ", address=" + address + '}';
    }

}
