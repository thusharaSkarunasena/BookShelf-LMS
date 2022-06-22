package org.sahurdayathra.BookShelfLMS.repo.custom;

import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.entity.User;
import org.sahurdayathra.BookShelfLMS.repo.CrudRepo;

/**
 *
 * @author Thushara Supun
 */
public interface UserRepo extends CrudRepo<User, String> {

    public String getNextUserID() throws Exception;

    public ArrayList<User> searchUser(String searchText) throws Exception;

}
