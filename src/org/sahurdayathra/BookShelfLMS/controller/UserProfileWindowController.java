package org.sahurdayathra.BookShelfLMS.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.sahurdayathra.BookShelfLMS.business.BOFactory;
import org.sahurdayathra.BookShelfLMS.business.custom.UserBO;
import org.sahurdayathra.BookShelfLMS.dto.UserDTO;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class UserProfileWindowController implements Initializable {

    @FXML
    private AnchorPane userProfileWindowAnchPane;
    @FXML
    private Label userNameTF;
    @FXML
    private Label userAccountTypeLbl;
    @FXML
    private Label DateNTimeLbl;
    @FXML
    private JFXTextField oldUsernameTF;
    @FXML
    private JFXTextField newUsernameTF;
    @FXML
    private JFXTextField confirmNewUsernameTF;
    @FXML
    private Label viewAdvanceProfileSettingsLbl;
    @FXML
    private JFXButton changeUsernameApplyBtn;
    @FXML
    private JFXButton changePasswordApplyBtn;
    @FXML
    private JFXButton OKBtn;
    @FXML
    private JFXTextField oldPasswordTF;
    @FXML
    private JFXTextField newPasswordTF;
    @FXML
    private JFXTextField confirmNewPasswordTF;

    UserDTO loggedUser = LogInController.loggedUser;
    String loggedDateTime = LogInController.loggedDateTime;

    String oldUsername;
    String oldPassword;

    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.gc();

        userNameTF.setText(loggedUser.getName());
        userAccountTypeLbl.setText(loggedUser.getState() + " User Account");
        DateNTimeLbl.setText(loggedDateTime);

        oldUsername = loggedUser.getUsername();
        oldPassword = loggedUser.getPassword();

        resetTextFieldsAndButtons();

        if (loggedUser.getState().equals("superadmin")) {
            Alert alert = new Alert(
                    Alert.AlertType.NONE,
                    "\n You Can't Update Super Admin User Account. \n",
                    ButtonType.OK
            );
            alert.setTitle("Message");
            alert.show();
            oldUsernameTF.setDisable(true);
            oldPasswordTF.setDisable(true);
        }

    }

    @FXML
    private void oldUsernameTF_onAction(ActionEvent event) {
        String oldUsernameTFValue = oldUsernameTF.getText();
        boolean matches = oldUsernameTFValue.matches("[a-z A-Z 0-9 ! @ # $ % ^ & * ( ) - _ < > : , ; ' / + ~ ` . \\s]{3,}");
        if (matches) {
            if (oldUsername.equals(oldUsernameTFValue)) {
                newUsernameTF.setDisable(false);
                newUsernameTF.requestFocus();
            } else {
                new Alert(
                        Alert.AlertType.ERROR,
                        "Old Username Which you Entered is Incorrect.",
                        ButtonType.OK
                ).show();
                oldUsernameTF.requestFocus();
                oldUsernameTF.selectAll();
            }
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Old Username.",
                    ButtonType.OK
            ).show();
            oldUsernameTF.requestFocus();
            oldUsernameTF.selectAll();
        }
    }

    @FXML
    private void newUsernameTF_onAction(ActionEvent event) {
        String newUsername = newUsernameTF.getText();
        boolean matches = newUsername.matches("[a-z A-Z 0-9 ! @ # $ % ^ & * ( ) - _ < > : , ; ' / + ~ ` . \\s]{3,}");
        if (matches) {
            if (!oldUsername.equals(newUsername)) {
                confirmNewUsernameTF.setDisable(false);
                confirmNewUsernameTF.requestFocus();
            } else {
                new Alert(
                        Alert.AlertType.ERROR,
                        "New Username Can't be the Old Username.",
                        ButtonType.OK
                ).show();
                newUsernameTF.requestFocus();
                newUsernameTF.selectAll();
            }
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in New Username.",
                    ButtonType.OK
            ).show();
            newUsernameTF.requestFocus();
            newUsernameTF.selectAll();
        }
    }

    @FXML
    private void confirmNewUsernameTF_onAction(ActionEvent event) {
        String confirmNewUsername = confirmNewUsernameTF.getText();
        boolean matches = confirmNewUsername.matches("[a-z A-Z 0-9 ! @ # $ % ^ & * ( ) - _ < > : , ; ' / + ~ ` . \\s]{3,}");
        if (matches) {
            if (newUsernameTF.getText().equals(confirmNewUsername)) {
                changeUsernameApplyBtn.setDisable(false);
                changeUsernameApplyBtn.fire();
            } else {
                new Alert(
                        Alert.AlertType.ERROR,
                        "Confirm New Username doesn't Match with New Username.",
                        ButtonType.OK
                ).show();
                confirmNewUsernameTF.requestFocus();
                confirmNewUsernameTF.selectAll();
            }
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Confirm New Username.",
                    ButtonType.OK
            ).show();
            confirmNewUsernameTF.requestFocus();
            confirmNewUsernameTF.selectAll();
        }
    }

    @FXML
    private void changeUsernameApplyBtn_onAction(ActionEvent event) {

        Platform.runLater(() -> {
            try {

                String newUsername = newUsernameTF.getText();

                UserDTO userDTO = new UserDTO(
                        loggedUser.getUserID(),
                        loggedUser.getUsername(),
                        newUsername,
                        loggedUser.getPassword(),
                        loggedUser.getState()
                );

                boolean result = false;

                if (isValidUsernameTextFields()) {

                    Alert alert = new Alert(
                            Alert.AlertType.CONFIRMATION,
                            "Are You Sure to Apply Changes to Username ?",
                            ButtonType.OK,
                            ButtonType.NO
                    );
                    alert.setTitle("Apply ?");
                    Button yesButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                    yesButton.setDefaultButton(false);
                    Button noButton = (Button) alert.getDialogPane().lookupButton(ButtonType.NO);
                    noButton.setDefaultButton(true);
                    Optional<ButtonType> action = alert.showAndWait();

                    if (action.get() == ButtonType.OK) {
                        result = userBO.updateUser(userDTO);

                        if (result) {
                            new Alert(
                                    Alert.AlertType.INFORMATION,
                                    "Username of the User '" + loggedUser.getUserID() + "' has been Changed Successfully.",
                                    ButtonType.OK
                            ).showAndWait();
                            resetTextFieldsAndButtons();
                            LogInController.loggedUser = userDTO;
                            OKBtn.fire();
                        } else {
                            new Alert(
                                    Alert.AlertType.ERROR,
                                    "Failed to Change the Username of the User '" + loggedUser.getUserID() + "'.",
                                    ButtonType.OK
                            ).show();
                        }
                    }

                }

            } catch (Exception ex) {
                printException(ex);
            }
        });

    }

    private boolean isValidUsernameTextFields() {

        String oldUsernameTFValue = oldUsernameTF.getText();
        String newUsername = newUsernameTF.getText();
        String confirmNewUsername = confirmNewUsernameTF.getText();

        boolean matches1 = oldUsernameTFValue.matches("[a-z A-Z 0-9 ! @ # $ % ^ & * ( ) - _ < > : , ; ' / + ~ ` . \\s]{3,}");
        boolean matches2 = newUsername.matches("[a-z A-Z 0-9 ! @ # $ % ^ & * ( ) - _ < > : , ; ' / + ~ ` . \\s]{3,}");
        boolean matches3 = confirmNewUsername.matches("[a-z A-Z 0-9 ! @ # $ % ^ & * ( ) - _ < > : , ; ' / + ~ ` . \\s]{3,}");

        boolean isValidTextFields = false;

        if (matches1) {
            if (oldUsername.equals(oldUsernameTF.getText())) {
                newUsernameTF.setDisable(false);
                if (matches2) {
                    if (!oldUsername.equals(newUsername)) {
                        confirmNewUsernameTF.setDisable(false);
                        if (matches3) {
                            if (newUsernameTF.getText().equals(confirmNewUsername)) {
                                changeUsernameApplyBtn.setDisable(false);
                                isValidTextFields = true;
                            } else {
                                new Alert(
                                        Alert.AlertType.ERROR,
                                        "Confirm New Username doesn't Match with New Username.",
                                        ButtonType.OK
                                ).show();
                                confirmNewUsernameTF.requestFocus();
                                confirmNewUsernameTF.selectAll();
                            }
                        } else {
                            new Alert(
                                    Alert.AlertType.ERROR,
                                    "Oops... Something Wrong in Confirm New Username.",
                                    ButtonType.OK
                            ).show();
                            confirmNewUsernameTF.requestFocus();
                            confirmNewUsernameTF.selectAll();
                        }
                    } else {
                        new Alert(
                                Alert.AlertType.ERROR,
                                "New Username Can't be the Old Username.",
                                ButtonType.OK
                        ).show();
                        newUsernameTF.requestFocus();
                        newUsernameTF.selectAll();
                    }
                } else {
                    new Alert(
                            Alert.AlertType.ERROR,
                            "Oops... Something Wrong in New Username.",
                            ButtonType.OK
                    ).show();
                    newUsernameTF.requestFocus();
                    newUsernameTF.selectAll();
                }
            } else {
                new Alert(
                        Alert.AlertType.ERROR,
                        "Old Username Which you Entered is Incorrect.",
                        ButtonType.OK
                ).show();
                oldUsernameTF.requestFocus();
                oldUsernameTF.selectAll();
            }
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Old Username.",
                    ButtonType.OK
            ).show();
            oldUsernameTF.requestFocus();
            oldUsernameTF.selectAll();
        }

        return isValidTextFields;

    }

    @FXML
    private void oldPasswordTF_onAction(ActionEvent event) {
        String oldPasswordTFValue = oldPasswordTF.getText();
        boolean matches = oldPasswordTFValue.matches("[a-z A-Z 0-9 ! @ # $ % ^ & * ( ) - _ < > : , ; ' / + ~ ` . \\s]{3,}");
        if (matches) {
            if (oldPassword.equals(oldPasswordTFValue)) {
                newPasswordTF.setDisable(false);
                newPasswordTF.requestFocus();
            } else {
                new Alert(
                        Alert.AlertType.ERROR,
                        "Old Password Which you Entered is Incorrect.",
                        ButtonType.OK
                ).show();
                oldPasswordTF.requestFocus();
                oldPasswordTF.selectAll();
            }
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Old Password.",
                    ButtonType.OK
            ).show();
            oldPasswordTF.requestFocus();
            oldPasswordTF.selectAll();
        }

    }

    @FXML
    private void newPasswordTF_onAction(ActionEvent event) {
        String newPassword = newPasswordTF.getText();
        boolean matches = newPassword.matches("[a-z A-Z 0-9 ! @ # $ % ^ & * ( ) - _ < > : , ; ' / + ~ ` . \\s]{3,}");
        if (matches) {
            if (!oldPassword.equals(newPassword)) {
                confirmNewPasswordTF.setDisable(false);
                confirmNewPasswordTF.requestFocus();
            } else {
                new Alert(
                        Alert.AlertType.ERROR,
                        "New Password Can't be the Old Password.",
                        ButtonType.OK
                ).show();
                newPasswordTF.requestFocus();
                newPasswordTF.selectAll();
            }
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in New Password.",
                    ButtonType.OK
            ).show();
            newPasswordTF.requestFocus();
            newPasswordTF.selectAll();
        }
    }

    @FXML
    private void confirmNewPasswordTF_onAction(ActionEvent event) {
        String confirmNewPassword = confirmNewPasswordTF.getText();
        boolean matches = confirmNewPassword.matches("[a-z A-Z 0-9 ! @ # $ % ^ & * ( ) - _ < > : , ; ' / + ~ ` . \\s]{3,}");
        if (matches) {
            if (newPasswordTF.getText().equals(confirmNewPassword)) {
                changePasswordApplyBtn.setDisable(false);
                changePasswordApplyBtn.fire();
            } else {
                new Alert(
                        Alert.AlertType.ERROR,
                        "Confirm New Password doesn't Match with New Password.",
                        ButtonType.OK
                ).show();
                confirmNewPasswordTF.requestFocus();
                confirmNewPasswordTF.selectAll();
            }
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Confirm New Password.",
                    ButtonType.OK
            ).show();
            confirmNewPasswordTF.requestFocus();
            confirmNewPasswordTF.selectAll();
        }
    }

    @FXML
    private void changePasswordApplyBtn_onAction(ActionEvent event) {

        Platform.runLater(() -> {
            try {

                String newPassword = newPasswordTF.getText();

                UserDTO userDTO = new UserDTO(
                        loggedUser.getUserID(),
                        loggedUser.getUsername(),
                        loggedUser.getUsername(),
                        newPassword,
                        loggedUser.getState()
                );

                boolean result = false;

                if (isValidPasswordTextFields()) {

                    Alert alert = new Alert(
                            Alert.AlertType.CONFIRMATION,
                            "Are You Sure to Apply Changes to Password ?",
                            ButtonType.OK,
                            ButtonType.NO
                    );
                    alert.setTitle("Apply ?");
                    Button yesButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                    yesButton.setDefaultButton(false);
                    Button noButton = (Button) alert.getDialogPane().lookupButton(ButtonType.NO);
                    noButton.setDefaultButton(true);
                    Optional<ButtonType> action = alert.showAndWait();

                    if (action.get() == ButtonType.OK) {
                        result = userBO.updateUser(userDTO);

                        if (result) {
                            new Alert(
                                    Alert.AlertType.INFORMATION,
                                    "Password of the User '" + loggedUser.getUserID() + "' has been Changed Successfully.",
                                    ButtonType.OK
                            ).showAndWait();
                            resetTextFieldsAndButtons();
                            LogInController.loggedUser = userDTO;
                            OKBtn.fire();
                        } else {
                            new Alert(
                                    Alert.AlertType.ERROR,
                                    "Failed to Change the Password of the User '" + loggedUser.getUserID() + "'.",
                                    ButtonType.OK
                            ).show();
                        }
                    }

                }

            } catch (Exception ex) {
                printException(ex);
            }
        });

    }

    private boolean isValidPasswordTextFields() {

        String oldPasswordTFValue = oldPasswordTF.getText();
        String newPassword = newPasswordTF.getText();
        String confirmNewPassword = confirmNewPasswordTF.getText();

        boolean matches1 = oldPasswordTFValue.matches("[a-z A-Z 0-9 ! @ # $ % ^ & * ( ) - _ < > : , ; ' / + ~ ` . \\s]{3,}");
        boolean matches2 = newPassword.matches("[a-z A-Z 0-9 ! @ # $ % ^ & * ( ) - _ < > : , ; ' / + ~ ` . \\s]{3,}");
        boolean matches3 = confirmNewPassword.matches("[a-z A-Z 0-9 ! @ # $ % ^ & * ( ) - _ < > : , ; ' / + ~ ` . \\s]{3,}");

        boolean isValidTextFields = false;

        if (matches1) {
            if (oldPassword.equals(oldPasswordTF.getText())) {
                newPasswordTF.setDisable(false);
                if (matches2) {
                    if (!oldPassword.equals(newPassword)) {
                        confirmNewPasswordTF.setDisable(false);
                        if (matches3) {
                            if (newPasswordTF.getText().equals(confirmNewPassword)) {
                                changePasswordApplyBtn.setDisable(false);
                                isValidTextFields = true;
                            } else {
                                new Alert(
                                        Alert.AlertType.ERROR,
                                        "Confirm New Password doesn't Match with New Password.",
                                        ButtonType.OK
                                ).show();
                                confirmNewPasswordTF.requestFocus();
                                confirmNewPasswordTF.selectAll();
                            }
                        } else {
                            new Alert(
                                    Alert.AlertType.ERROR,
                                    "Oops... Something Wrong in Confirm New Password.",
                                    ButtonType.OK
                            ).show();
                            confirmNewPasswordTF.requestFocus();
                            confirmNewPasswordTF.selectAll();
                        }
                    } else {
                        new Alert(
                                Alert.AlertType.ERROR,
                                "New Password Can't be the Old Password.",
                                ButtonType.OK
                        ).show();
                        newPasswordTF.requestFocus();
                        newPasswordTF.selectAll();
                    }
                } else {
                    new Alert(
                            Alert.AlertType.ERROR,
                            "Oops... Something Wrong in New Password.",
                            ButtonType.OK
                    ).show();
                    newPasswordTF.requestFocus();
                    newPasswordTF.selectAll();
                }
            } else {
                new Alert(
                        Alert.AlertType.ERROR,
                        "Old Password Which you Entered is Incorrect.",
                        ButtonType.OK
                ).show();
                oldPasswordTF.requestFocus();
                oldPasswordTF.selectAll();
            }
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Old Password.",
                    ButtonType.OK
            ).show();
            oldPasswordTF.requestFocus();
            oldPasswordTF.selectAll();
        }

        return isValidTextFields;

    }

    private void resetTextFieldsAndButtons() {
        oldUsernameTF.setText("");
        newUsernameTF.setText("");
        confirmNewUsernameTF.setText("");
        oldPasswordTF.setText("");
        newPasswordTF.setText("");
        confirmNewPasswordTF.setText("");

        newUsernameTF.setDisable(true);
        confirmNewUsernameTF.setDisable(true);
        newPasswordTF.setDisable(true);
        confirmNewPasswordTF.setDisable(true);

        changeUsernameApplyBtn.setDisable(true);
        changePasswordApplyBtn.setDisable(true);
    }

    @FXML
    private void viewAdvanceProfileSettingsLbl_onMouseClicked(MouseEvent event) {
        Alert alert = new Alert(
                Alert.AlertType.NONE,
                "\n Advance Profile Settings Currently not Available. \n",
                ButtonType.OK
        );
        alert.setTitle("Message");
        alert.show();
    }

    @FXML
    private void OKBtn_onAction(ActionEvent event) {
        MainDashController.isShownUserProfileWindow = false;
        Stage stage = (Stage) this.userProfileWindowAnchPane.getScene().getWindow();
        stage.close();
    }

    private void printException(Exception ex) {
        ex.printStackTrace();
        Platform.runLater(() -> {
            Alert alert = new Alert(
                    Alert.AlertType.ERROR,
                    ex.toString()
                    + "\n\n Please Contact Developer of this System to Resolve this Error."
                    + "\n Visit 'Help' or 'About' for More Details..."
                    + "", ButtonType.OK
            );
            alert.setTitle("A Exception has Occured!");
            alert.show();
        });
    }

}
