package org.sahurdayathra.BookShelfLMS.repo.custom;

import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.entity.Member;
import org.sahurdayathra.BookShelfLMS.repo.CrudRepo;

/**
 *
 * @author Thushara Supun
 */
public interface MemberRepo extends CrudRepo<Member, String> {

    public String getNextLibRegNO() throws Exception;

    public ArrayList<Member> searchMember(String searchText) throws Exception;

}
