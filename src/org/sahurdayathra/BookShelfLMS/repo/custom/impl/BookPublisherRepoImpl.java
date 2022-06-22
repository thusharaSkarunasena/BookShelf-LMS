package org.sahurdayathra.BookShelfLMS.repo.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.entity.BookPublisher;
import org.sahurdayathra.BookShelfLMS.repo.CrudUtil;
import org.sahurdayathra.BookShelfLMS.repo.custom.BookPublisherRepo;

/**
 *
 * @author Thushara Supun
 */
public class BookPublisherRepoImpl implements BookPublisherRepo {

    @Override
    public boolean save(BookPublisher entity) throws Exception {
        return CrudUtil.executeUpdate("CALL savePublisher(?, ?, ?, ?, ?, ?, ?, ?, ?)",
                entity.getBookPublisherID(),
                entity.getName(),
                entity.getAddress_no(),
                entity.getAddress_street(),
                entity.getAddress_village(),
                entity.getAddress_city(),
                entity.getEmail(),
                entity.getContactNO(),
                entity.getOtherDetails()
        );
    }

    @Override
    public boolean update(BookPublisher entity) throws Exception {
        return CrudUtil.executeUpdate("CALL updatePublisher(?, ?, ?, ?, ?, ?, ?, ?, ?)",
                entity.getBookPublisherID(),
                entity.getName(),
                entity.getAddress_no(),
                entity.getAddress_street(),
                entity.getAddress_village(),
                entity.getAddress_city(),
                entity.getEmail(),
                entity.getContactNO(),
                entity.getOtherDetails()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("CALL deletePublisher(?)", id);
    }

    @Override
    public BookPublisher get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL getPublisher(?)", id);

        BookPublisher bookPublisher = null;

        while (rst.next()) {
            bookPublisher = new BookPublisher(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9)
            );
        }

        return bookPublisher;
    }

    @Override
    public ArrayList<BookPublisher> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL getAllPublisher()");
        ArrayList<BookPublisher> bookPublishers = new ArrayList<>();

        while (rst.next()) {
            bookPublishers.add(new BookPublisher(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9)
            ));
        }

        return bookPublishers;
    }

    @Override
    public String getNextBookPublisherID() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL getLastPubID()");
        String lastBookPublisherID = null;
        Integer NumpartDigitCount = 0;

        if (rst.next()) {
            lastBookPublisherID = rst.getString(1);

            Integer Numpart = Integer.parseInt(lastBookPublisherID.substring(5, 8));
            Numpart = Numpart + 1;
            Integer tempNumpart = Numpart;

            while (tempNumpart != 0) {
                tempNumpart = tempNumpart / 10;
                NumpartDigitCount++;
            }

            String nextBookPublisherID = "PUB.";

            Integer rounds = 4 - NumpartDigitCount;

            while (rounds != 0) {
                nextBookPublisherID = nextBookPublisherID + "0";
                rounds--;
            }

            nextBookPublisherID = nextBookPublisherID + "" + Numpart;

            return nextBookPublisherID;
        } else {
            return "PUB.0001";
        }
    }

    @Override
    public ArrayList<BookPublisher> searchBookPublisher(String searchText) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL searchPublisher(?)", searchText);
        ArrayList<BookPublisher> bookPublishers = new ArrayList<>();

        while (rst.next()) {
            bookPublishers.add(new BookPublisher(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9)
            ));
        }

        return bookPublishers;
    }

}
