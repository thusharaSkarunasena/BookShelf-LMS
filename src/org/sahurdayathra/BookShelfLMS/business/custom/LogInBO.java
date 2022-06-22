package org.sahurdayathra.BookShelfLMS.business.custom;

import org.sahurdayathra.BookShelfLMS.business.SuperBO;
import org.sahurdayathra.BookShelfLMS.dto.UserDTO;

/**
 *
 * @author Thushara Supun
 */
public interface LogInBO extends SuperBO {

    public boolean initializeDBTables() throws Exception;

    public UserDTO getLoggedUser(String username, String password) throws Exception;

}
