package org.sahurdayathra.BookShelfLMS.repo.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.entity.Book;
import org.sahurdayathra.BookShelfLMS.repo.CrudUtil;
import org.sahurdayathra.BookShelfLMS.repo.custom.BookRepo;

/**
 *
 * @author Thushara Supun
 */
public class BookRepoImpl implements BookRepo {

    @Override
    public boolean save(Book entity) throws Exception {
        return CrudUtil.executeUpdate("CALL saveBook(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                entity.getBookCode(),
                entity.getIsbnNO(),
                entity.getBookTitle(),
                entity.getBookEdition(),
                entity.getBookCategoryCode(),
                entity.getAuthorName(),
                entity.getPublisherID(),
                entity.getPrice(),
                entity.getOtherDetails(),
                entity.getStatus()
        );
    }

    @Override
    public boolean update(Book entity) throws Exception {
        return CrudUtil.executeUpdate("CALL updateBook(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                entity.getBookCode(),
                entity.getIsbnNO(),
                entity.getBookTitle(),
                entity.getBookEdition(),
                entity.getBookCategoryCode(),
                entity.getAuthorName(),
                entity.getPublisherID(),
                entity.getPrice(),
                entity.getOtherDetails(),
                entity.getStatus()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("CALL deleteBook(?)", id);
    }

    @Override
    public Book get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL getBook(?)", id);

        Book book = null;

        while (rst.next()) {
            book = new Book(
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
            );
        }

        return book;
    }

    @Override
    public ArrayList<Book> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL getAllBook()");
        ArrayList<Book> books = new ArrayList<>();

        while (rst.next()) {
            books.add(new Book(
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

        return books;
    }

    @Override
    public ArrayList<Book> searchBook(String searchText) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL searchBook(?)", searchText);
        ArrayList<Book> books = new ArrayList<>();

        while (rst.next()) {
            books.add(new Book(
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

        return books;
    }
    
}
