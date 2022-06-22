package org.sahurdayathra.BookShelfLMS.repo.custom.impl;

import java.sql.ResultSet;
import org.sahurdayathra.BookShelfLMS.entity.User;
import org.sahurdayathra.BookShelfLMS.repo.CrudUtil;
import org.sahurdayathra.BookShelfLMS.repo.custom.LogInRepo;

/**
 *
 * @author Thushara Supun
 */
public class LogInRepoImpl implements LogInRepo {

    @Override
    public boolean initializeDBTables() throws Exception {
        return CrudUtil.executeUpdate("CALL inizializeDBTables()");
    }

    @Override
    public User getLoggedUser(String username, String password) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL getLoggedUserAcc(?, ?)", username, password);

        User loggedUser = null;

        if (rst.next()) {
            loggedUser = new User(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
        }

        return loggedUser;
    }

}
