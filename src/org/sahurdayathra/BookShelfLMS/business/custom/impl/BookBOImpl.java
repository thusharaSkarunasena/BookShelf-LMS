package org.sahurdayathra.BookShelfLMS.business.custom.impl;

import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.business.custom.BookBO;
import org.sahurdayathra.BookShelfLMS.dto.BookDTO;
import org.sahurdayathra.BookShelfLMS.entity.Book;
import org.sahurdayathra.BookShelfLMS.entity.BookCategory;
import org.sahurdayathra.BookShelfLMS.entity.BookPublisher;
import org.sahurdayathra.BookShelfLMS.repo.RepoFactory;
import org.sahurdayathra.BookShelfLMS.repo.custom.BookCategoryRepo;
import org.sahurdayathra.BookShelfLMS.repo.custom.BookPublisherRepo;
import org.sahurdayathra.BookShelfLMS.repo.custom.BookRepo;

/**
 *
 * @author Thushara Supun
 */
public class BookBOImpl implements BookBO {

    BookRepo bookRepo = (BookRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.BOOK);
    BookCategoryRepo bookCategoryRepo = (BookCategoryRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.BOOKCATEGORY);
    BookPublisherRepo bookPublisherRepo = (BookPublisherRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.BOOKPUBLISHER);

    @Override
    public boolean saveBook(BookDTO bookDTO) throws Exception {
        return bookRepo.save(new Book(
                bookDTO.getBookCode(),
                bookDTO.getIsbnNO(),
                bookDTO.getBookTitle(),
                bookDTO.getBookEdition(),
                bookDTO.getBookCategoryCode(),
                bookDTO.getAuthorName(),
                bookDTO.getPublisherID(),
                bookDTO.getPrice(),
                bookDTO.getOtherDetails(),
                bookDTO.getStatus()
        ));
    }

    @Override
    public boolean updateBook(BookDTO bookDTO) throws Exception {
        return bookRepo.update(new Book(
                bookDTO.getBookCode(),
                bookDTO.getIsbnNO(),
                bookDTO.getBookTitle(),
                bookDTO.getBookEdition(),
                bookDTO.getBookCategoryCode(),
                bookDTO.getAuthorName(),
                bookDTO.getPublisherID(),
                bookDTO.getPrice(),
                bookDTO.getOtherDetails(),
                bookDTO.getStatus()
        ));
    }

    @Override
    public boolean deleteBook(String bookCode) throws Exception {
        return bookRepo.delete(bookCode);
    }

    @Override
    public ArrayList<BookDTO> searchBook(String searchText) throws Exception {
        ArrayList<Book> books = bookRepo.searchBook(searchText);
        ArrayList<BookDTO> bookDTOs = new ArrayList<>();

        for (Book book : books) {
            bookDTOs.add(new BookDTO(
                    book.getBookCode(),
                    book.getIsbnNO(),
                    book.getBookTitle(),
                    book.getBookEdition(),
                    book.getBookCategoryCode(),
                    book.getAuthorName(),
                    book.getPublisherID(),
                    book.getPrice(),
                    book.getOtherDetails(),
                    book.getStatus()
            ));
        }

        return bookDTOs;
    }

    @Override
    public BookDTO getBook(String bookCode) throws Exception {
        Book book = bookRepo.get(bookCode);
        return new BookDTO(
                book.getBookCode(),
                book.getIsbnNO(),
                book.getBookTitle(),
                book.getBookEdition(),
                book.getBookCategoryCode(),
                book.getAuthorName(),
                book.getPublisherID(),
                book.getPrice(),
                book.getOtherDetails(),
                book.getStatus()
        );
    }

    @Override
    public ArrayList<BookDTO> getAllBooks() throws Exception {
        ArrayList<Book> books = bookRepo.getAll();
        ArrayList<BookDTO> bookDTOs = new ArrayList<>();

        for (Book book : books) {
            bookDTOs.add(new BookDTO(
                    book.getBookCode(),
                    book.getIsbnNO(),
                    book.getBookTitle(),
                    book.getBookEdition(),
                    book.getBookCategoryCode(),
                    book.getAuthorName(),
                    book.getPublisherID(),
                    book.getPrice(),
                    book.getOtherDetails(),
                    book.getStatus()
            ));
        }

        return bookDTOs;
    }

    @Override
    public String getBookCategoryName(String bookCategoryCode) throws Exception {
        ArrayList<BookCategory> bookCategorys = bookCategoryRepo.getAll();
        String bookCategoryName = null;
        for (BookCategory bookCategory : bookCategorys) {
            if (bookCategory.getBookCategoryCode().equals(bookCategoryCode)) {
                bookCategoryName = bookCategory.getCategoryName();
                break;
            }
        }
        return bookCategoryName;
    }

    @Override
    public String getBookPublisherName(String bookPublisherID) throws Exception {
        ArrayList<BookPublisher> bookPublishers = bookPublisherRepo.getAll();
        String bookPublisherName = null;
        for (BookPublisher bookPublisher : bookPublishers) {
            if (bookPublisher.getBookPublisherID().equals(bookPublisherID)) {
                bookPublisherName = bookPublisher.getName();
                break;
            }
        }
        return bookPublisherName;
    }

}
