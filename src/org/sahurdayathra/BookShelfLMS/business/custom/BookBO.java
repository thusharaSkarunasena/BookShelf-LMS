package org.sahurdayathra.BookShelfLMS.business.custom;

import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.business.SuperBO;
import org.sahurdayathra.BookShelfLMS.dto.BookDTO;

/**
 *
 * @author Thushara Supun
 */
public interface BookBO extends SuperBO {

    public boolean saveBook(BookDTO bookDTO) throws Exception;

    public boolean updateBook(BookDTO bookDTO) throws Exception;

    public boolean deleteBook(String bookCode) throws Exception;

    public ArrayList<BookDTO> searchBook(String searchText) throws Exception;

    public BookDTO getBook(String bookCode) throws Exception;

    public ArrayList<BookDTO> getAllBooks() throws Exception;

    public String getBookCategoryName(String bookCategoryCode) throws Exception;

    public String getBookPublisherName(String bookPublisherID) throws Exception;

}
