package org.sahurdayathra.BookShelfLMS.business.custom;

import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.business.SuperBO;
import org.sahurdayathra.BookShelfLMS.dto.MemberDTO;

/**
 *
 * @author Thushara Supun
 */
public interface MemberBO extends SuperBO {

    public boolean saveMember(MemberDTO memberDTO) throws Exception;

    public boolean updateMember(MemberDTO memberDTO) throws Exception;

    public boolean deleteMember(String libRegNO) throws Exception;

    public String getNextLibRegNO() throws Exception;

    public ArrayList<MemberDTO> searchMember(String searchText) throws Exception;

    public MemberDTO getMember(String libRegNO) throws Exception;

    public ArrayList<MemberDTO> getAllMembers() throws Exception;

}
