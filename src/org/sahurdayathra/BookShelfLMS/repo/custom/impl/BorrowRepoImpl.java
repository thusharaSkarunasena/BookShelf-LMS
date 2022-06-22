package org.sahurdayathra.BookShelfLMS.repo.custom.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.db.DBConnection;
import org.sahurdayathra.BookShelfLMS.entity.Borrow;
import org.sahurdayathra.BookShelfLMS.repo.CrudUtil;
import org.sahurdayathra.BookShelfLMS.repo.custom.BorrowRepo;

/**
 *
 * @author Thushara Supun
 */
public class BorrowRepoImpl implements BorrowRepo {

    @Override
    public boolean save(Borrow entity) throws Exception {
        return CrudUtil.executeUpdate("CALL saveBorrow(?, ?, ?, ?, ?, ?, ?)",
                entity.getBorrowID(),
                entity.getLibRegNO(),
                entity.getBookCode(),
                entity.getUserID(),
                entity.getIssuedDate(),
                entity.getDueDate(),
                entity.getReturnedDate()
        );
    }

    @Override
    public boolean saveBorrowAndUpdateBook(Borrow borrow) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        boolean result1 = false;
        boolean result2 = false;

        try {
            connection.setAutoCommit(false);

            result1 = CrudUtil.executeUpdate("CALL saveBorrow(?, ?, ?, ?, ?, ?, ?)",
                    borrow.getBorrowID(),
                    borrow.getLibRegNO(),
                    borrow.getBookCode(),
                    borrow.getUserID(),
                    borrow.getIssuedDate(),
                    borrow.getDueDate(),
                    borrow.getReturnedDate()
            );

            result2 = CrudUtil.executeUpdate("CALL updateBookWithSaveBorrow(?, ?)",
                    borrow.getLibRegNO(),
                    borrow.getBookCode()
            );

            if (result1 & result2) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }

        } finally {
            connection.setAutoCommit(true);
        }

    }

    @Override
    public boolean update(Borrow entity) throws Exception {
        return CrudUtil.executeUpdate("CALL updateBorrow(?, ?, ?, ?, ?, ?, ?)",
                entity.getBorrowID(),
                entity.getLibRegNO(),
                entity.getBookCode(),
                entity.getUserID(),
                entity.getIssuedDate(),
                entity.getDueDate(),
                entity.getReturnedDate()
        );
    }

    @Override
    public boolean updateBorrowAndUpdateBook(Borrow borrow) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        boolean result1 = false;
        boolean result2 = false;

        try {
            connection.setAutoCommit(false);

            result1 = CrudUtil.executeUpdate("CALL updateBorrow(?, ?, ?, ?, ?, ?, ?)",
                    borrow.getBorrowID(),
                    borrow.getLibRegNO(),
                    borrow.getBookCode(),
                    borrow.getUserID(),
                    borrow.getIssuedDate(),
                    borrow.getDueDate(),
                    borrow.getReturnedDate()
            );

            result2 = CrudUtil.executeUpdate("CALL updateBookWithUpdateBorrow(?, ?)",
                    "STU.000000",
                    borrow.getBookCode()
            );

            if (result1 & result2) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }

        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("CALL deleteBorrow(?)", id);
    }

    @Override
    public Borrow get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL getBorrow(?)", id);

        Borrow borrow = null;

        while (rst.next()) {
            borrow = new Borrow(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDate(5).toString(),
                    rst.getDate(6).toString(),
                    rst.getDate(7).toString()
            );
        }

        return borrow;
    }

    @Override
    public ArrayList<Borrow> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL getAllBorrow()");
        ArrayList<Borrow> borrows = new ArrayList<>();

        while (rst.next()) {
            borrows.add(new Borrow(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDate(5).toString(),
                    rst.getDate(6).toString(),
                    rst.getDate(7).toString()
            ));
        }

        return borrows;
    }

    @Override
    public String getNextBorrowID() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL getLastBorrowID()");
        String lastBorrowID = null;
        Integer NumpartDigitCount = 0;

        if (rst.next()) {
            lastBorrowID = rst.getString(1);

            Integer Numpart = Integer.parseInt(lastBorrowID);
            Numpart = Numpart + 1;
            Integer tempNumpart = Numpart;

            while (tempNumpart != 0) {
                tempNumpart = tempNumpart / 10;
                NumpartDigitCount++;
            }

            String nextBorrowID = "";

            Integer rounds = 10 - NumpartDigitCount;

            while (rounds != 0) {
                nextBorrowID = nextBorrowID + "0";
                rounds--;
            }

            nextBorrowID = nextBorrowID + "" + Numpart;

            return nextBorrowID;
        } else {
            return "0000000001";
        }
    }

    @Override
    public ArrayList<Borrow> searchBorrow(String searchText) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL searchBorrow(?)", searchText);
        ArrayList<Borrow> borrows = new ArrayList<>();

        while (rst.next()) {
            borrows.add(new Borrow(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDate(5).toString(),
                    rst.getDate(6).toString(),
                    rst.getDate(7).toString()
            ));
        }

        return borrows;
    }

    @Override
    public ArrayList<Borrow> getNotReturnedBorrows() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL getNotReturnedBorrow()");
        ArrayList<Borrow> borrows = new ArrayList<>();

        while (rst.next()) {
            borrows.add(new Borrow(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDate(5).toString(),
                    rst.getDate(6).toString(),
                    rst.getDate(7).toString()
            ));
        }

        return borrows;
    }

    @Override
    public ArrayList<Borrow> getReturnedBorrows() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL getReturnedBorrow()");
        ArrayList<Borrow> borrows = new ArrayList<>();

        while (rst.next()) {
            borrows.add(new Borrow(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDate(5).toString(),
                    rst.getDate(6).toString(),
                    rst.getDate(7).toString()
            ));
        }

        return borrows;
    }

    @Override
    public ArrayList<Borrow> searchNotReturnedBorrows(String searchText) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL searchNotReturnedBorrow(?)", searchText);
        ArrayList<Borrow> borrows = new ArrayList<>();

        while (rst.next()) {
            borrows.add(new Borrow(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDate(5).toString(),
                    rst.getDate(6).toString(),
                    rst.getDate(7).toString()
            ));
        }

        return borrows;
    }

    @Override
    public ArrayList<Borrow> searchReturnedBorrows(String searchText) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL searchReturnedBorrow(?)", searchText);
        ArrayList<Borrow> borrows = new ArrayList<>();

        while (rst.next()) {
            borrows.add(new Borrow(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDate(5).toString(),
                    rst.getDate(6).toString(),
                    rst.getDate(7).toString()
            ));
        }

        return borrows;
    }

}
