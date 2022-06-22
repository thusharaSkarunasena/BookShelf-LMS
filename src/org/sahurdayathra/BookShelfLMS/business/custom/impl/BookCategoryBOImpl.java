package org.sahurdayathra.BookShelfLMS.business.custom.impl;

import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.business.custom.BookCategoryBO;
import org.sahurdayathra.BookShelfLMS.dto.BookCategoryDTO;
import org.sahurdayathra.BookShelfLMS.entity.BookCategory;
import org.sahurdayathra.BookShelfLMS.repo.RepoFactory;
import org.sahurdayathra.BookShelfLMS.repo.custom.BookCategoryRepo;

/**
 *
 * @author Thushara Supun
 */
public class BookCategoryBOImpl implements BookCategoryBO {

    BookCategoryRepo bookCategoryRepo = (BookCategoryRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.BOOKCATEGORY);

    @Override
    public boolean saveBookCategory(BookCategoryDTO bookCategoryDTO) throws Exception {
        return bookCategoryRepo.save(new BookCategory(
                bookCategoryDTO.getBookCategoryCode(),
                bookCategoryDTO.getCategoryName(),
                bookCategoryDTO.getOtherDetails()
        ));
    }

    @Override
    public boolean updateBookCategory(BookCategoryDTO bookCategoryDTO) throws Exception {
        return bookCategoryRepo.update(new BookCategory(
                bookCategoryDTO.getBookCategoryCode(),
                bookCategoryDTO.getCategoryName(),
                bookCategoryDTO.getOtherDetails()
        ));
    }

    @Override
    public boolean deleteBookCategory(String bookCategoryCode) throws Exception {
        return bookCategoryRepo.delete(bookCategoryCode);
    }

    @Override
    public ArrayList<BookCategoryDTO> searchBookCategory(String searchText) throws Exception {
        ArrayList<BookCategory> bookCategorys = bookCategoryRepo.searchBookCategory(searchText);
        ArrayList<BookCategoryDTO> bookCategoryDTOs = new ArrayList<>();

        for (BookCategory bc : bookCategorys) {
            bookCategoryDTOs.add(new BookCategoryDTO(
                    bc.getBookCategoryCode(),
                    bc.getCategoryName(),
                    bc.getOtherDetails()
            ));
        }

        return bookCategoryDTOs;
    }

    @Override
    public BookCategoryDTO getBookCategory(String bookCategoryCode) throws Exception {
        BookCategory bookCategory = bookCategoryRepo.get(bookCategoryCode);
        return new BookCategoryDTO(
                bookCategory.getBookCategoryCode(),
                bookCategory.getCategoryName(),
                bookCategory.getOtherDetails()
        );
    }

    @Override
    public ArrayList<BookCategoryDTO> getAllBookCategories() throws Exception {
        ArrayList<BookCategory> bookCategorys = bookCategoryRepo.getAll();
        ArrayList<BookCategoryDTO> bookCategoryDTOs = new ArrayList<>();

        for (BookCategory bc : bookCategorys) {
            bookCategoryDTOs.add(new BookCategoryDTO(
                    bc.getBookCategoryCode(),
                    bc.getCategoryName(),
                    bc.getOtherDetails()
            ));
        }

        return bookCategoryDTOs;
    }

}
