package org.sahurdayathra.BookShelfLMS.business.custom.impl;

import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.business.custom.BookPublisherBO;
import org.sahurdayathra.BookShelfLMS.dto.BookPublisherDTO;
import org.sahurdayathra.BookShelfLMS.entity.BookPublisher;
import org.sahurdayathra.BookShelfLMS.repo.RepoFactory;
import org.sahurdayathra.BookShelfLMS.repo.custom.BookPublisherRepo;

/**
 *
 * @author Thushara Supun
 */
public class BookPublisherBOImpl implements BookPublisherBO {

    BookPublisherRepo bookPublisherRepo = (BookPublisherRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.BOOKPUBLISHER);

    @Override
    public boolean saveBookPublisher(BookPublisherDTO bookPublisherDTO) throws Exception {
        return bookPublisherRepo.save(new BookPublisher(
                bookPublisherDTO.getBookPublisherID(),
                bookPublisherDTO.getName(),
                bookPublisherDTO.getAddress_no(),
                bookPublisherDTO.getAddress_street(),
                bookPublisherDTO.getAddress_village(),
                bookPublisherDTO.getAddress_city(),
                bookPublisherDTO.getEmail(),
                bookPublisherDTO.getContactNO(),
                bookPublisherDTO.getOtherDetails()
        ));
    }

    @Override
    public boolean updateBookPublisher(BookPublisherDTO bookPublisherDTO) throws Exception {
        return bookPublisherRepo.update(new BookPublisher(
                bookPublisherDTO.getBookPublisherID(),
                bookPublisherDTO.getName(),
                bookPublisherDTO.getAddress_no(),
                bookPublisherDTO.getAddress_street(),
                bookPublisherDTO.getAddress_village(),
                bookPublisherDTO.getAddress_city(),
                bookPublisherDTO.getEmail(),
                bookPublisherDTO.getContactNO(),
                bookPublisherDTO.getOtherDetails()
        ));
    }

    @Override
    public boolean deleteBookPublisher(String bookPublisherID) throws Exception {
        return bookPublisherRepo.delete(bookPublisherID);
    }

    @Override
    public String getNextBookPublisherID() throws Exception {
        return bookPublisherRepo.getNextBookPublisherID();
    }

    @Override
    public ArrayList<BookPublisherDTO> searchBookPublisher(String searchText) throws Exception {
        ArrayList<BookPublisher> bookPublishers = bookPublisherRepo.searchBookPublisher(searchText);
        ArrayList<BookPublisherDTO> bookPublisherDTOs = new ArrayList<>();

        for (BookPublisher bp : bookPublishers) {
            bookPublisherDTOs.add(new BookPublisherDTO(
                    bp.getBookPublisherID(),
                    bp.getName(),
                    bp.getAddress_no(),
                    bp.getAddress_street(),
                    bp.getAddress_village(),
                    bp.getAddress_city(),
                    bp.getEmail(),
                    bp.getContactNO(),
                    bp.getOtherDetails()
            ));
        }

        return bookPublisherDTOs;
    }

    @Override
    public BookPublisherDTO getBookPublisher(String bookPublisherID) throws Exception {
        BookPublisher bookPublisher = bookPublisherRepo.get(bookPublisherID);
        return new BookPublisherDTO(
                bookPublisher.getBookPublisherID(),
                bookPublisher.getName(),
                bookPublisher.getAddress_no(),
                bookPublisher.getAddress_street(),
                bookPublisher.getAddress_village(),
                bookPublisher.getAddress_city(),
                bookPublisher.getEmail(),
                bookPublisher.getContactNO(),
                bookPublisher.getOtherDetails()
        );
    }

    @Override
    public ArrayList<BookPublisherDTO> getAllBookPublishers() throws Exception {
        ArrayList<BookPublisher> bookPublishers = bookPublisherRepo.getAll();
        ArrayList<BookPublisherDTO> bookPublisherDTOs = new ArrayList<>();

        for (BookPublisher bp : bookPublishers) {
            bookPublisherDTOs.add(new BookPublisherDTO(
                    bp.getBookPublisherID(),
                    bp.getName(),
                    bp.getAddress_no(),
                    bp.getAddress_street(),
                    bp.getAddress_village(),
                    bp.getAddress_city(),
                    bp.getEmail(),
                    bp.getContactNO(),
                    bp.getOtherDetails()
            ));
        }

        return bookPublisherDTOs;
    }

}
