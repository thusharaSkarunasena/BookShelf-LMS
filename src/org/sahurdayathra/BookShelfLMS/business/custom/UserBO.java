package org.sahurdayathra.BookShelfLMS.business.custom;

import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.business.SuperBO;
import org.sahurdayathra.BookShelfLMS.dto.UserDTO;

/**
 *
 * @author Thushara Supun
 */
public interface UserBO extends SuperBO {

    public boolean saveUser(UserDTO userDTO) throws Exception;

    public boolean updateUser(UserDTO userDTO) throws Exception;

    public boolean deleteUser(String userID) throws Exception;

    public String getNextUserID() throws Exception;

    public ArrayList<UserDTO> searchUser(String searchText) throws Exception;

    public UserDTO getUser(String userID) throws Exception;

    public ArrayList<UserDTO> getAllUsers() throws Exception;

}
