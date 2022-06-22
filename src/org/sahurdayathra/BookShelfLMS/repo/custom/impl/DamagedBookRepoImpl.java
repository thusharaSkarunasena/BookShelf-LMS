package org.sahurdayathra.BookShelfLMS.repo.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.entity.DamagedBook;
import org.sahurdayathra.BookShelfLMS.repo.CrudUtil;
import org.sahurdayathra.BookShelfLMS.repo.custom.DamagedBookRepo;

/**
 *
 * @author Thushara Supun
 */
public class DamagedBookRepoImpl implements DamagedBookRepo {

    @Override
    public ArrayList<DamagedBook> getAllDamagedBooks() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL getAllDamagedBook()");
        ArrayList<DamagedBook> damagedBooks = new ArrayList<>();

        while (rst.next()) {
            damagedBooks.add(new DamagedBook(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getDouble(8),
                    rst.getString(9),
                    rst.getString(10)
            ));
        }

        return damagedBooks;
    }

    @Override
    public ArrayList<DamagedBook> searchDamagedBook(String searchText) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL searchDamagedBook(?)", searchText);
        ArrayList<DamagedBook> damagedBooks = new ArrayList<>();

        while (rst.next()) {
            damagedBooks.add(new DamagedBook(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getDouble(8),
                    rst.getString(9),
                    rst.getString(10)
            ));
        }

        return damagedBooks;
    }

}
