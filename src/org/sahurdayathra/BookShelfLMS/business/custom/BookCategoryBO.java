package org.sahurdayathra.BookShelfLMS.business.custom;

import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.business.SuperBO;
import org.sahurdayathra.BookShelfLMS.dto.BookCategoryDTO;

/**
 *
 * @author Thushara Supun
 */
public interface BookCategoryBO extends SuperBO {

    public boolean saveBookCategory(BookCategoryDTO bookCategoryDTO) throws Exception;

    public boolean updateBookCategory(BookCategoryDTO bookCategoryDTO) throws Exception;

    public boolean deleteBookCategory(String bookCategoryCode) throws Exception;

    public ArrayList<BookCategoryDTO> searchBookCategory(String searchText) throws Exception;

    public BookCategoryDTO getBookCategory(String bookCategoryCode) throws Exception;

    public ArrayList<BookCategoryDTO> getAllBookCategories() throws Exception;

}
