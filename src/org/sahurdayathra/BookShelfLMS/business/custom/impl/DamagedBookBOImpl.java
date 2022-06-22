package org.sahurdayathra.BookShelfLMS.business.custom.impl;

import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.business.custom.DamagedBookBO;
import org.sahurdayathra.BookShelfLMS.dto.DamagedBookDTO;
import org.sahurdayathra.BookShelfLMS.entity.DamagedBook;
import org.sahurdayathra.BookShelfLMS.repo.RepoFactory;
import org.sahurdayathra.BookShelfLMS.repo.custom.DamagedBookRepo;

/**
 *
 * @author Thushara Supun
 */
public class DamagedBookBOImpl implements DamagedBookBO {

    DamagedBookRepo damagedBookRepo = (DamagedBookRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.DAMAGEDBOOK);

    @Override
    public ArrayList<DamagedBookDTO> searchDamagedBook(String searchText) throws Exception {
        ArrayList<DamagedBook> damagedBooks = damagedBookRepo.searchDamagedBook(searchText);
        ArrayList<DamagedBookDTO> damagedBookDTOs = new ArrayList<>();

        for (DamagedBook dmgBook : damagedBooks) {
            damagedBookDTOs.add(new DamagedBookDTO(
                    dmgBook.getBookCode(),
                    dmgBook.getIsbnNO(),
                    dmgBook.getBookTitle(),
                    dmgBook.getBookEdition(),
                    dmgBook.getBookCategoryCode(),
                    dmgBook.getAuthorName(),
                    dmgBook.getPublisherID(),
                    dmgBook.getPrice(),
                    dmgBook.getOtherDetails(),
                    dmgBook.getStatus()
            ));
        }

        return damagedBookDTOs;
    }

    @Override
    public ArrayList<DamagedBookDTO> getAllDamagedBooks() throws Exception {
        ArrayList<DamagedBook> damagedBooks = damagedBookRepo.getAllDamagedBooks();
        ArrayList<DamagedBookDTO> damagedBookDTOs = new ArrayList<>();

        for (DamagedBook dmgBook : damagedBooks) {
            damagedBookDTOs.add(new DamagedBookDTO(
                    dmgBook.getBookCode(),
                    dmgBook.getIsbnNO(),
                    dmgBook.getBookTitle(),
                    dmgBook.getBookEdition(),
                    dmgBook.getBookCategoryCode(),
                    dmgBook.getAuthorName(),
                    dmgBook.getPublisherID(),
                    dmgBook.getPrice(),
                    dmgBook.getOtherDetails(),
                    dmgBook.getStatus()
            ));
        }

        return damagedBookDTOs;
    }

}
