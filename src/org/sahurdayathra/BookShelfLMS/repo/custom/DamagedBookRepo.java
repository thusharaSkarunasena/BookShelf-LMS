package org.sahurdayathra.BookShelfLMS.repo.custom;

import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.entity.DamagedBook;
import org.sahurdayathra.BookShelfLMS.repo.SuperRepo;

/**
 *
 * @author Thushara Supun
 */
public interface DamagedBookRepo extends SuperRepo{
    
    public ArrayList<DamagedBook> getAllDamagedBooks() throws Exception;
    
    public ArrayList<DamagedBook> searchDamagedBook(String searchText) throws Exception;
    
}
