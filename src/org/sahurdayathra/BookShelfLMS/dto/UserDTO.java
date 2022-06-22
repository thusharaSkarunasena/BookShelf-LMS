package org.sahurdayathra.BookShelfLMS.dto;

/**
 *
 * @author Thushara Supun
 */
public class UserDTO {

    private String userID;
    private String name;
    private String username;
    private String password;
    private String state;

    public UserDTO() {
    }

    public UserDTO(String userID, String name, String username, String password, String state) {
        this.userID = userID;
        this.name = name;
        this.username = username;
        this.password = password;
        this.state = state;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "userID=" + userID + ", name=" + name + ", username=" + username + ", password=" + password + ", state=" + state + '}';
    }

}
