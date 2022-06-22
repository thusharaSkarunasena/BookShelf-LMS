package org.sahurdayathra.BookShelfLMS.repo.custom;

import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.entity.BookPublisher;
import org.sahurdayathra.BookShelfLMS.repo.CrudRepo;

/**
 *
 * @author Thushara Supun
 */
public interface BookPublisherRepo extends CrudRepo<BookPublisher, String> {

    public String getNextBookPublisherID() throws Exception;

    public ArrayList<BookPublisher> searchBookPublisher(String searchText) throws Exception;

}
