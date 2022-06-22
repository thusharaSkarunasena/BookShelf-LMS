package org.sahurdayathra.BookShelfLMS.business.custom.impl;

import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.business.custom.BorrowBO;
import org.sahurdayathra.BookShelfLMS.dto.BorrowDTO;
import org.sahurdayathra.BookShelfLMS.entity.Borrow;
import org.sahurdayathra.BookShelfLMS.repo.RepoFactory;
import org.sahurdayathra.BookShelfLMS.repo.custom.BorrowRepo;

/**
 *
 * @author Thushara Supun
 */
public class BorrowBOImpl implements BorrowBO {

    BorrowRepo borrowRepo = (BorrowRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.BORROW);

    @Override
    public boolean saveBorrow(BorrowDTO borrowDTO) throws Exception {
        return borrowRepo.save(new Borrow(
                borrowDTO.getBorrowID(),
                borrowDTO.getLibRegNO(),
                borrowDTO.getBookCode(),
                borrowDTO.getUserID(),
                borrowDTO.getIssuedDate(),
                borrowDTO.getDueDate(),
                borrowDTO.getReturnedDate()
        ));
    }

    @Override
    public boolean saveBorrowAndUpdateBook(BorrowDTO borrowDTO) throws Exception {
        return borrowRepo.saveBorrowAndUpdateBook(new Borrow(
                borrowDTO.getBorrowID(),
                borrowDTO.getLibRegNO(),
                borrowDTO.getBookCode(),
                borrowDTO.getUserID(),
                borrowDTO.getIssuedDate(),
                borrowDTO.getDueDate(),
                borrowDTO.getReturnedDate()
        ));
    }

    @Override
    public boolean updateBorrow(BorrowDTO borrowDTO) throws Exception {
        return borrowRepo.update(new Borrow(
                borrowDTO.getBorrowID(),
                borrowDTO.getLibRegNO(),
                borrowDTO.getBookCode(),
                borrowDTO.getUserID(),
                borrowDTO.getIssuedDate(),
                borrowDTO.getDueDate(),
                borrowDTO.getReturnedDate()
        ));
    }

    @Override
    public boolean updateBorrowAndUpdateBook(BorrowDTO borrowDTO) throws Exception {
        return borrowRepo.updateBorrowAndUpdateBook(new Borrow(
                borrowDTO.getBorrowID(),
                borrowDTO.getLibRegNO(),
                borrowDTO.getBookCode(),
                borrowDTO.getUserID(),
                borrowDTO.getIssuedDate(),
                borrowDTO.getDueDate(),
                borrowDTO.getReturnedDate()
        ));
    }

    @Override
    public boolean deleteBorrow(String borrowID) throws Exception {
        return borrowRepo.delete(borrowID);
    }

    @Override
    public String getNextBorrowID() throws Exception {
        return borrowRepo.getNextBorrowID();
    }

    @Override
    public ArrayList<BorrowDTO> searchBorrow(String searchText) throws Exception {
        ArrayList<Borrow> borrows = borrowRepo.searchBorrow(searchText);
        ArrayList<BorrowDTO> borrowDTOs = new ArrayList<>();

        for (Borrow borrow : borrows) {
            borrowDTOs.add(new BorrowDTO(
                    borrow.getBorrowID(),
                    borrow.getLibRegNO(),
                    borrow.getBookCode(),
                    borrow.getUserID(),
                    borrow.getIssuedDate(),
                    borrow.getDueDate(),
                    borrow.getReturnedDate()
            ));
        }

        return borrowDTOs;
    }

    @Override
    public BorrowDTO getBorrow(String borrowID) throws Exception {
        Borrow borrow = borrowRepo.get(borrowID);
        return new BorrowDTO(
                borrow.getBorrowID(),
                borrow.getLibRegNO(),
                borrow.getBookCode(),
                borrow.getUserID(),
                borrow.getIssuedDate(),
                borrow.getDueDate(),
                borrow.getReturnedDate()
        );
    }

    @Override
    public ArrayList<BorrowDTO> getAllBorrows() throws Exception {
        ArrayList<Borrow> borrows = borrowRepo.getAll();
        ArrayList<BorrowDTO> borrowDTOs = new ArrayList<>();

        for (Borrow borrow : borrows) {
            borrowDTOs.add(new BorrowDTO(
                    borrow.getBorrowID(),
                    borrow.getLibRegNO(),
                    borrow.getBookCode(),
                    borrow.getUserID(),
                    borrow.getIssuedDate(),
                    borrow.getDueDate(),
                    borrow.getReturnedDate()
            ));
        }

        return borrowDTOs;
    }

    @Override
    public ArrayList<BorrowDTO> getNotReturnedBorrows() throws Exception {
        ArrayList<Borrow> borrows = borrowRepo.getNotReturnedBorrows();
        ArrayList<BorrowDTO> borrowDTOs = new ArrayList<>();

        for (Borrow borrow : borrows) {
            borrowDTOs.add(new BorrowDTO(
                    borrow.getBorrowID(),
                    borrow.getLibRegNO(),
                    borrow.getBookCode(),
                    borrow.getUserID(),
                    borrow.getIssuedDate(),
                    borrow.getDueDate(),
                    borrow.getReturnedDate()
            ));
        }

        return borrowDTOs;
    }

    @Override
    public ArrayList<BorrowDTO> getReturnedBorrows() throws Exception {
        ArrayList<Borrow> borrows = borrowRepo.getReturnedBorrows();
        ArrayList<BorrowDTO> borrowDTOs = new ArrayList<>();

        for (Borrow borrow : borrows) {
            borrowDTOs.add(new BorrowDTO(
                    borrow.getBorrowID(),
                    borrow.getLibRegNO(),
                    borrow.getBookCode(),
                    borrow.getUserID(),
                    borrow.getIssuedDate(),
                    borrow.getDueDate(),
                    borrow.getReturnedDate()
            ));
        }

        return borrowDTOs;
    }

    @Override
    public ArrayList<BorrowDTO> searchNotReturnedBorrows(String searchText) throws Exception {
        ArrayList<Borrow> borrows = borrowRepo.searchNotReturnedBorrows(searchText);
        ArrayList<BorrowDTO> borrowDTOs = new ArrayList<>();

        for (Borrow borrow : borrows) {
            borrowDTOs.add(new BorrowDTO(
                    borrow.getBorrowID(),
                    borrow.getLibRegNO(),
                    borrow.getBookCode(),
                    borrow.getUserID(),
                    borrow.getIssuedDate(),
                    borrow.getDueDate(),
                    borrow.getReturnedDate()
            ));
        }

        return borrowDTOs;
    }

    @Override
    public ArrayList<BorrowDTO> searchReturnedBorrows(String searchText) throws Exception {
        ArrayList<Borrow> borrows = borrowRepo.searchReturnedBorrows(searchText);
        ArrayList<BorrowDTO> borrowDTOs = new ArrayList<>();

        for (Borrow borrow : borrows) {
            borrowDTOs.add(new BorrowDTO(
                    borrow.getBorrowID(),
                    borrow.getLibRegNO(),
                    borrow.getBookCode(),
                    borrow.getUserID(),
                    borrow.getIssuedDate(),
                    borrow.getDueDate(),
                    borrow.getReturnedDate()
            ));
        }

        return borrowDTOs;
    }

}
