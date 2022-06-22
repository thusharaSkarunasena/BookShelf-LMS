package org.sahurdayathra.BookShelfLMS.business.custom;

import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.business.SuperBO;
import org.sahurdayathra.BookShelfLMS.dto.BookPublisherDTO;

/**
 *
 * @author Thushara Supun
 */
public interface BookPublisherBO extends SuperBO {

    public boolean saveBookPublisher(BookPublisherDTO bookPublisherDTO) throws Exception;

    public boolean updateBookPublisher(BookPublisherDTO bookPublisherDTO) throws Exception;

    public boolean deleteBookPublisher(String bookPublisherID) throws Exception;

    public String getNextBookPublisherID() throws Exception;

    public ArrayList<BookPublisherDTO> searchBookPublisher(String searchText) throws Exception;

    public BookPublisherDTO getBookPublisher(String bookPublisherID) throws Exception;

    public ArrayList<BookPublisherDTO> getAllBookPublishers() throws Exception;

}
