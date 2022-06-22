package org.sahurdayathra.BookShelfLMS.repo.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.entity.Member;
import org.sahurdayathra.BookShelfLMS.repo.CrudUtil;
import org.sahurdayathra.BookShelfLMS.repo.custom.MemberRepo;

/**
 *
 * @author Thushara Supun
 */
public class MemberRepoImpl implements MemberRepo {

    @Override
    public boolean save(Member entity) throws Exception {
        return CrudUtil.executeUpdate("CALL saveMember(?, ?, ?, ?, ?, ?, ?, ?, ?)",
                entity.getLibRegNO(),
                entity.getAdmissionNO(),
                entity.getName(),
                entity.getAddress_no(),
                entity.getAddress_street(),
                entity.getAddress_village(),
                entity.getAddress_city(),
                entity.getContactNO(),
                entity.getOtherDetail()
        );
    }

    @Override
    public boolean update(Member entity) throws Exception {
        return CrudUtil.executeUpdate("CALL updateMember(?, ?, ?, ?, ?, ?, ?, ?, ?)",
                entity.getLibRegNO(),
                entity.getAdmissionNO(),
                entity.getName(),
                entity.getAddress_no(),
                entity.getAddress_street(),
                entity.getAddress_village(),
                entity.getAddress_city(),
                entity.getContactNO(),
                entity.getOtherDetail()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("CALL deleteMember(?)", id);
    }

    @Override
    public Member get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL getMember(?)", id);

        Member member = null;

        while (rst.next()) {
            member = new Member(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9)
            );
        }

        return member;
    }

    @Override
    public ArrayList<Member> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL getAllMember()");
        ArrayList<Member> members = new ArrayList<>();

        while (rst.next()) {
            members.add(new Member(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9)
            ));
        }

        return members;
    }

    @Override
    public String getNextLibRegNO() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL getLastLibRegNO()");
        String lastLibRegNO = null;
        Integer NumpartDigitCount = 0;

        if (rst.next()) {
            lastLibRegNO = rst.getString(1);

            Integer Numpart = Integer.parseInt(lastLibRegNO.substring(5, 10));
            Numpart = Numpart + 1;
            Integer tempNumpart = Numpart;

            while (tempNumpart != 0) {
                tempNumpart = tempNumpart / 10;
                NumpartDigitCount++;
            }

            String nextLibRegNO = "MEM.";

            Integer rounds = 6 - NumpartDigitCount;

            while (rounds != 0) {
                nextLibRegNO = nextLibRegNO + "0";
                rounds--;
            }

            nextLibRegNO = nextLibRegNO + "" + Numpart;

            return nextLibRegNO;
        } else {
            return "MEM.000001";
        }
    }

    @Override
    public ArrayList<Member> searchMember(String searchText) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("CALL searchMember(?)", searchText);
        ArrayList<Member> members = new ArrayList<>();

        while (rst.next()) {
            members.add(new Member(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9)
            ));
        }

        return members;
    }

}
