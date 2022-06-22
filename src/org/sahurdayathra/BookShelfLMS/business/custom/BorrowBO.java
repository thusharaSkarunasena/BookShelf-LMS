package org.sahurdayathra.BookShelfLMS.business.custom;

import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.business.SuperBO;
import org.sahurdayathra.BookShelfLMS.dto.BorrowDTO;

/**
 *
 * @author Thushara Supun
 */
public interface BorrowBO extends SuperBO {

    public boolean saveBorrow(BorrowDTO borrowDTO) throws Exception;

    public boolean saveBorrowAndUpdateBook(BorrowDTO borrowDTO) throws Exception;

    public boolean updateBorrow(BorrowDTO borrowDTO) throws Exception;

    public boolean updateBorrowAndUpdateBook(BorrowDTO borrowDTO) throws Exception;

    public boolean deleteBorrow(String borrowID) throws Exception;

    public String getNextBorrowID() throws Exception;

    public ArrayList<BorrowDTO> searchBorrow(String searchText) throws Exception;

    public BorrowDTO getBorrow(String borrowID) throws Exception;

    public ArrayList<BorrowDTO> getAllBorrows() throws Exception;

    public ArrayList<BorrowDTO> getNotReturnedBorrows() throws Exception;

    public ArrayList<BorrowDTO> getReturnedBorrows() throws Exception;

    public ArrayList<BorrowDTO> searchNotReturnedBorrows(String searchText) throws Exception;

    public ArrayList<BorrowDTO> searchReturnedBorrows(String searchText) throws Exception;

}
