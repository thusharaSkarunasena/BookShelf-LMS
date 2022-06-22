package org.sahurdayathra.BookShelfLMS.business.custom.impl;

import java.util.ArrayList;
import org.sahurdayathra.BookShelfLMS.business.custom.UserBO;
import org.sahurdayathra.BookShelfLMS.dto.UserDTO;
import org.sahurdayathra.BookShelfLMS.entity.User;
import org.sahurdayathra.BookShelfLMS.repo.RepoFactory;
import org.sahurdayathra.BookShelfLMS.repo.custom.UserRepo;

/**
 *
 * @author Thushara Supun
 */
public class UserBOImpl implements UserBO {

    UserRepo userRepo = (UserRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.USER);

    @Override
    public boolean saveUser(UserDTO userDTO) throws Exception {
        return userRepo.save(new User(
                userDTO.getUserID(),
                userDTO.getName(),
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getState()
        ));
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws Exception {
        return userRepo.update(new User(
                userDTO.getUserID(),
                userDTO.getName(),
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getState()
        ));
    }

    @Override
    public boolean deleteUser(String userID) throws Exception {
        return userRepo.delete(userID);
    }

    @Override
    public String getNextUserID() throws Exception {
        return userRepo.getNextUserID();
    }

    @Override
    public ArrayList<UserDTO> searchUser(String searchText) throws Exception {
        ArrayList<User> users = userRepo.searchUser(searchText);
        ArrayList<UserDTO> userDTOs = new ArrayList<>();

        for (User user : users) {
            if (!user.getUserID().equals("USR.0000")) {
                userDTOs.add(new UserDTO(
                        user.getUserID(),
                        user.getName(),
                        user.getUsername(),
                        user.getPassword(),
                        user.getState()
                ));
            }
        }

        return userDTOs;
    }

    @Override
    public UserDTO getUser(String userID) throws Exception {
        User user = userRepo.get(userID);
        return new UserDTO(
                user.getUserID(),
                user.getName(),
                user.getUsername(),
                user.getPassword(),
                user.getState()
        );
    }

    @Override
    public ArrayList<UserDTO> getAllUsers() throws Exception {
        ArrayList<User> users = userRepo.getAll();
        ArrayList<UserDTO> userDTOs = new ArrayList<>();

        for (User user : users) {
            if (!user.getUserID().equals("USR.0000")) {
                userDTOs.add(new UserDTO(
                        user.getUserID(),
                        user.getName(),
                        user.getUsername(),
                        user.getPassword(),
                        user.getState()
                ));
            }
        }

        return userDTOs;
    }

}
