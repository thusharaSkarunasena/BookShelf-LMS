package org.sahurdayathra.BookShelfLMS.business.custom.impl;

import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.business.custom.MemberBO;
import org.sahurdayathra.BookShelfLMS.dto.MemberDTO;
import org.sahurdayathra.BookShelfLMS.entity.Member;
import org.sahurdayathra.BookShelfLMS.repo.RepoFactory;
import org.sahurdayathra.BookShelfLMS.repo.RepoFactory.RepoTypes;
import org.sahurdayathra.BookShelfLMS.repo.custom.MemberRepo;

/**
 *
 * @author Thushara Supun
 */
public class MemberBOImpl implements MemberBO {

    MemberRepo memberRepo = (MemberRepo) RepoFactory.getInstance().getRepo(RepoTypes.MEMBER);

    @Override
    public boolean saveMember(MemberDTO memberDTO) throws Exception {
        return memberRepo.save(new Member(
                memberDTO.getLibRegNO(),
                memberDTO.getAdmissionNO(),
                memberDTO.getName(),
                memberDTO.getAddress_no(),
                memberDTO.getAddress_street(),
                memberDTO.getAddress_village(),
                memberDTO.getAddress_city(),
                memberDTO.getContactNO(),
                memberDTO.getOtherDetail()
        ));
    }

    @Override
    public boolean updateMember(MemberDTO memberDTO) throws Exception {
        return memberRepo.update(new Member(
                memberDTO.getLibRegNO(),
                memberDTO.getAdmissionNO(),
                memberDTO.getName(),
                memberDTO.getAddress_no(),
                memberDTO.getAddress_street(),
                memberDTO.getAddress_village(),
                memberDTO.getAddress_city(),
                memberDTO.getContactNO(),
                memberDTO.getOtherDetail()
        ));
    }

    @Override
    public boolean deleteMember(String libRegNO) throws Exception {
        return memberRepo.delete(libRegNO);
    }

    @Override
    public String getNextLibRegNO() throws Exception {
        return memberRepo.getNextLibRegNO();
    }

    @Override
    public ArrayList<MemberDTO> searchMember(String searchText) throws Exception {
        ArrayList<Member> members = memberRepo.searchMember(searchText);
        ArrayList<MemberDTO> memberDTOs = new ArrayList<>();

        for (Member member : members) {
            memberDTOs.add(new MemberDTO(
                    member.getLibRegNO(),
                    member.getAdmissionNO(),
                    member.getName(),
                    member.getAddress_no(),
                    member.getAddress_street(),
                    member.getAddress_village(),
                    member.getAddress_city(),
                    member.getContactNO(),
                    member.getOtherDetail()
            ));
        }

        return memberDTOs;
    }

    @Override
    public MemberDTO getMember(String libRegNO) throws Exception {
        Member member = memberRepo.get(libRegNO);
        return new MemberDTO(
                member.getLibRegNO(),
                member.getAdmissionNO(),
                member.getName(),
                member.getAddress_no(),
                member.getAddress_street(),
                member.getAddress_village(),
                member.getAddress_city(),
                member.getContactNO(),
                member.getOtherDetail()
        );
    }

    @Override
    public ArrayList<MemberDTO> getAllMembers() throws Exception {
        ArrayList<Member> members = memberRepo.getAll();
        ArrayList<MemberDTO> memberDTOs = new ArrayList<>();

        for (Member member : members) {
            memberDTOs.add(new MemberDTO(
                    member.getLibRegNO(),
                    member.getAdmissionNO(),
                    member.getName(),
                    member.getAddress_no(),
                    member.getAddress_street(),
                    member.getAddress_village(),
                    member.getAddress_city(),
                    member.getContactNO(),
                    member.getOtherDetail()
            ));
        }

        return memberDTOs;
    }

}
