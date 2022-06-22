package org.sahurdayathra.BookShelfLMS.repo.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.entity.User;
import org.sahurdayathra.BookShelfLMS.repo.CrudUtil;
import org.sahurdayathra.BookShelfLMS.repo.custom.UserRepo;

/**
 *
 * @author Thushara Supun
 */
public class UserRepoImpl implements UserRepo {

    @Override
    public boolean save(User entity) throws Exception {
        return CrudUtil.executeUpdate("CALL saveUserAcc(?, ?, ?, ?, ?)",
                entity.getUserID(),
                entity.getName(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getState()
        );
    }

    @Override
    public boolean update(User entity) throws Exception {
        return CrudUtil.executeUpdate("CALL updateUserAcc(?, ?, ?, ?, ?)",
                entity.getUserID(),
                entity.getName(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getState()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("CALL deleteUserAcc(?)", id);
    }

    @Override
    public User get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL getUser(?)", id);
        User user = null;

        while (rst.next()) {
            user = new User(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
        }

        return user;
    }

    @Override
    public ArrayList<User> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL getAllUserAcc()");
        ArrayList<User> users = new ArrayList<>();

        while (rst.next()) {
            users.add(new User(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }

        return users;
    }

    @Override
    public String getNextUserID() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL getLastUserID()");
        String lastUserID = null;
        Integer NumpartDigitCount = 0;

        if (rst.next()) {
            lastUserID = rst.getString(1);

            Integer Numpart = Integer.parseInt(lastUserID.substring(5, 8));
            Numpart = Numpart + 1;
            Integer tempNumpart = Numpart;

            while (tempNumpart != 0) {
                tempNumpart = tempNumpart / 10;
                NumpartDigitCount++;
            }

            String nextUserID = "USR.";

            Integer rounds = 4 - NumpartDigitCount;

            while (rounds != 0) {
                nextUserID = nextUserID + "0";
                rounds--;
            }

            nextUserID = nextUserID + "" + Numpart;

            return nextUserID;
        } else {
            return "USR.0001";
        }
    }

    @Override
    public ArrayList<User> searchUser(String searchText) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL searchUserAcc(?)", searchText);
        ArrayList<User> users = new ArrayList<>();

        while (rst.next()) {
            users.add(new User(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }

        return users;
    }

}
