package org.sahurdayathra.BookShelfLMS.repo.custom;

import org.sahurdayathra.BookShelfLMS.entity.User;
import org.sahurdayathra.BookShelfLMS.repo.SuperRepo;

/**
 *
 * @author Thushara Supun
 */
public interface LogInRepo extends SuperRepo {

    public boolean initializeDBTables() throws Exception;

    public User getLoggedUser(String username, String password) throws Exception;

}
