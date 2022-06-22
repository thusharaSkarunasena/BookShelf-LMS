package org.sahurdayathra.BookShelfLMS.repo.custom;

import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.entity.Book;
import org.sahurdayathra.BookShelfLMS.repo.CrudRepo;

/**
 *
 * @author Thushara Supun
 */
public interface BookRepo extends CrudRepo<Book, String> {

    public ArrayList<Book> searchBook(String searchText) throws Exception;

}
