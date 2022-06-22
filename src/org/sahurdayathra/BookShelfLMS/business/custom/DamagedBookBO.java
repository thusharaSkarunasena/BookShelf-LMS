package org.sahurdayathra.BookShelfLMS.business.custom;

import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.business.SuperBO;
import org.sahurdayathra.BookShelfLMS.dto.DamagedBookDTO;

/**
 *
 * @author Thushara Supun
 */
public interface DamagedBookBO extends SuperBO {

    public ArrayList<DamagedBookDTO> searchDamagedBook(String searchText) throws Exception;

    public ArrayList<DamagedBookDTO> getAllDamagedBooks() throws Exception;

}
