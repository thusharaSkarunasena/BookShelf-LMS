package org.sahurdayathra.BookShelfLMS.repo.custom;

import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.entity.BookCategory;
import org.sahurdayathra.BookShelfLMS.repo.CrudRepo;

/**
 *
 * @author Thushara Supun
 */
public interface BookCategoryRepo extends CrudRepo<BookCategory, String> {

    public ArrayList<BookCategory> searchBookCategory(String searchText) throws Exception;

}
