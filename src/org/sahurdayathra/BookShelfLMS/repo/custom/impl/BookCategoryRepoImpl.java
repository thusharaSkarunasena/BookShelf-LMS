package org.sahurdayathra.BookShelfLMS.repo.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.entity.BookCategory;
import org.sahurdayathra.BookShelfLMS.repo.CrudUtil;
import org.sahurdayathra.BookShelfLMS.repo.custom.BookCategoryRepo;

/**
 *
 * @author Thushara Supun
 */
public class BookCategoryRepoImpl implements BookCategoryRepo {

    @Override
    public boolean save(BookCategory entity) throws Exception {
        return CrudUtil.executeUpdate("CALL saveBookCategory(?, ?, ?)",
                entity.getBookCategoryCode(),
                entity.getCategoryName(),
                entity.getOtherDetails()
        );
    }

    @Override
    public boolean update(BookCategory entity) throws Exception {
        return CrudUtil.executeUpdate("CALL updateBookCategory(?, ?, ?)",
                entity.getBookCategoryCode(),
                entity.getCategoryName(),
                entity.getOtherDetails()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("CALL deleteBookCategory(?)", id);
    }

    @Override
    public BookCategory get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL getBookCategory(?)", id);

        BookCategory bookCategory = null;

        while (rst.next()) {
            bookCategory = new BookCategory(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );
        }

        return bookCategory;

    }

    @Override
    public ArrayList<BookCategory> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL getAllBookCategory()");

        ArrayList<BookCategory> bookCategorys = new ArrayList<>();

        while (rst.next()) {
            bookCategorys.add(new BookCategory(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            ));
        }

        return bookCategorys;
    }

    @Override
    public ArrayList<BookCategory> searchBookCategory(String searchText) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL searchBookCategory(?)", searchText);

        ArrayList<BookCategory> bookCategorys = new ArrayList<>();

        while (rst.next()) {
            bookCategorys.add(new BookCategory(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            ));
        }

        return bookCategorys;
    }

}
