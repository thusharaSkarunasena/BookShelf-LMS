package org.sahurdayathra.BookShelfLMS.repo.custom;

import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.entity.Borrow;
import org.sahurdayathra.BookShelfLMS.repo.CrudRepo;

/**
 *
 * @author Thushara Supun
 */
public interface BorrowRepo extends CrudRepo<Borrow, String> {

    public boolean saveBorrowAndUpdateBook(Borrow borrow) throws Exception;

    public boolean updateBorrowAndUpdateBook(Borrow borrow) throws Exception;

    public String getNextBorrowID() throws Exception;

    public ArrayList<Borrow> searchBorrow(String searchText) throws Exception;

    public ArrayList<Borrow> getNotReturnedBorrows() throws Exception;

    public ArrayList<Borrow> getReturnedBorrows() throws Exception;

    public ArrayList<Borrow> searchNotReturnedBorrows(String searchText) throws Exception;

    public ArrayList<Borrow> searchReturnedBorrows(String searchText) throws Exception;

}
