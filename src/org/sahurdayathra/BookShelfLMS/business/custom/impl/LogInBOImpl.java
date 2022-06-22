package org.sahurdayathra.BookShelfLMS.business.custom.impl;

import org.sahurdayathra.BookShelfLMS.business.custom.LogInBO;
import org.sahurdayathra.BookShelfLMS.dto.UserDTO;
import org.sahurdayathra.BookShelfLMS.entity.User;
import org.sahurdayathra.BookShelfLMS.repo.RepoFactory;
import org.sahurdayathra.BookShelfLMS.repo.custom.LogInRepo;

/**
 *
 * @author Thushara Supun
 */
public class LogInBOImpl implements LogInBO {

    LogInRepo logInRepo = (LogInRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.LOGIN);

    @Override
    public boolean initializeDBTables() throws Exception {
        return logInRepo.initializeDBTables();
    }

    @Override
    public UserDTO getLoggedUser(String username, String password) throws Exception {
        User user = logInRepo.getLoggedUser(username, password);
        UserDTO userDTO = null;

        if (user != null) {
            userDTO = new UserDTO(
                    user.getUserID(),
                    user.getName(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getState()
            );
        }

        return userDTO;
    }

}
