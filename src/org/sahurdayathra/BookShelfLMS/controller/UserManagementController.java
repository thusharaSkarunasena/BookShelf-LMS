package org.sahurdayathra.BookShelfLMS.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import org.sahurdayathra.BookShelfLMS.business.BOFactory;
import org.sahurdayathra.BookShelfLMS.business.custom.BorrowBO;
import org.sahurdayathra.BookShelfLMS.business.custom.UserBO;
import org.sahurdayathra.BookShelfLMS.dto.BorrowDTO;
import org.sahurdayathra.BookShelfLMS.dto.UserDTO;
import org.sahurdayathra.BookShelfLMS.view.util.tblmodel.UserTM;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class UserManagementController implements Initializable {

    @FXML
    private AnchorPane userManagementAnchPane;
    @FXML
    private HBox homeHBox;
    @FXML
    private HBox viewMoreHBox;
    @FXML
    private HBox helpHBox;
    @FXML
    private JFXTextField nameTF;
    @FXML
    private JFXTextField userIDTF;
    @FXML
    private JFXTextField usernameTF;
    @FXML
    private JFXTextField passwordTF;
    @FXML
    private TextField searchBoxTF;
    @FXML
    private Label countLbl;
    @FXML
    private TableView<UserTM> userDetailsTbl;
    @FXML
    private Pane btnPane;
    @FXML
    private JFXButton newBtn;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private JFXComboBox<String> stateComBox;

    ObservableList<UserTM> userTMs;

    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);
    BorrowBO borrowBO = (BorrowBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BORROW);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.gc();

        userDetailsTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        userDetailsTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        userDetailsTbl.getColumns().get(2).setStyle("-fx-alignment:center");
        userDetailsTbl.getColumns().get(3).setStyle("-fx-alignment:center");
        userDetailsTbl.getColumns().get(4).setStyle("-fx-alignment:center");
        userDetailsTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("userID"));
        userDetailsTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        userDetailsTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("username"));
        userDetailsTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("password"));
        userDetailsTbl.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("state"));

        newBtn.fire();

    }

    @FXML
    private void homeHBox_onMouseClicked(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/mainDash.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) this.userManagementAnchPane.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

            TranslateTransition trans = new TranslateTransition(Duration.millis(300), scene.getRoot());
            trans.setFromX(+scene.getHeight());
            trans.setToX(0);
            trans.play();
        } catch (IOException ex) {
            printException(ex);
        }
    }

    @FXML
    private void viewMoreHBox_onMouseClicked(MouseEvent event) {
        try {
            if (!MainDashController.isShownViewMoreWindow) {
                MainDashController.isShownViewMoreWindow = true;
                Parent root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/viewMoreWindow.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("View More");
                stage.getIcons().add(new Image("org/sahurdayathra/BookShelfLMS/asset/BookShelf_png.png"));
                stage.setResizable(false);
                stage.centerOnScreen();
                stage.initOwner(userManagementAnchPane.getScene().getWindow());
                stage.setOnCloseRequest((WindowEvent wEvent) -> {
                    MainDashController.isShownViewMoreWindow = false;
                });
                stage.show();

                TranslateTransition trans = new TranslateTransition(Duration.millis(350), scene.getRoot());
                trans.setFromY(-scene.getHeight());
                trans.setToY(0);
                trans.play();
            }
        } catch (IOException ex) {
            printException(ex);
        }
    }

    @FXML
    private void helpHBox_onMouseClicked(MouseEvent event) {
        try {
            if (!MainDashController.isShownHelpWindow) {
                MainDashController.isShownHelpWindow = true;
                Parent root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/helpWindow.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Help");
                stage.getIcons().add(new Image("org/sahurdayathra/BookShelfLMS/asset/BookShelf_png.png"));
                stage.setResizable(false);
                stage.centerOnScreen();
                stage.initOwner(userManagementAnchPane.getScene().getWindow());
                stage.setOnCloseRequest((WindowEvent wEvent) -> {
                    MainDashController.isShownHelpWindow = false;
                });
                stage.show();

                TranslateTransition trans = new TranslateTransition(Duration.millis(300), scene.getRoot());
                trans.setFromY(-scene.getHeight());
                trans.setToY(0);
                trans.play();
            }
        } catch (IOException ex) {
            printException(ex);
        }
    }

    private void generateNextUserID() {
        new Thread(() -> {
            try {
                String userID = userBO.getNextUserID();
                Platform.runLater(() -> {
                    userIDTF.setText(userID);
                });
            } catch (Exception ex) {
                printException(ex);
            }
        }).start();
    }

    private void loadComboBox() {
        stateComBox.getItems().clear();
        stateComBox.getItems().addAll("Local", "Admin");
    }

    private void loadAllUserDetails() {
        new Thread(() -> {
            try {
                ArrayList<UserDTO> userDTOs = userBO.getAllUsers();
                loadUserDetailsTbl(userDTOs);
            } catch (Exception ex) {
                printException(ex);
            }
        }).start();
    }

    @FXML
    private void nameTF_onAction(ActionEvent event) {
        String name = nameTF.getText();
        boolean matches = name.matches("[a-z A-Z 0-9 . \\s]{3,}");
        if (matches) {
            usernameTF.requestFocus();
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Name.",
                    ButtonType.OK
            ).show();
            nameTF.requestFocus();
            nameTF.selectAll();
        }
    }

    @FXML
    private void usernameTF_onAction(ActionEvent event) {
        String username = usernameTF.getText();
        boolean matches = username.matches("[a-z A-Z 0-9 ! @ # $ % ^ & * ( ) - _ < > : , ; ' / + ~ ` . \\s]{3,}");
        if (matches) {
            passwordTF.requestFocus();
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Username.",
                    ButtonType.OK
            ).show();
            usernameTF.requestFocus();
            usernameTF.selectAll();
        }
    }

    @FXML
    private void passwordTF_onAction(ActionEvent event) {
        String password = passwordTF.getText();
        boolean matches = password.matches("[a-z A-Z 0-9 ! @ # $ % ^ & * ( ) - _ < > : , ; ' / + ~ ` . \\s]{3,}");
        if (matches) {
            stateComBox.requestFocus();
            stateComBox.show();
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Password.",
                    ButtonType.OK
            ).show();
            passwordTF.requestFocus();
            passwordTF.selectAll();
        }
    }

    @FXML
    private void searchBoxTF_onMouseClicked(MouseEvent event) {
        searchBoxTF.selectAll();
    }

    @FXML
    private void searchBoxTF_onKeyReleased(KeyEvent event) {
        new Thread(() -> {
            try {

                if (searchBoxTF.getText() == null) {
                    loadAllUserDetails();
                } else {
                    String searchText = searchBoxTF.getText();

                    ArrayList<UserDTO> userDTOs = userBO.searchUser(searchText);

                    if (userDTOs.isEmpty()) {
                        Platform.runLater(() -> {
                            searchBoxTF.setStyle("-fx-text-fill: #D91022");
                        });
                        loadAllUserDetails();
                    } else {
                        Platform.runLater(() -> {
                            searchBoxTF.setStyle("-fx-text-fill: #000000");
                        });
                        loadUserDetailsTbl(userDTOs);
                    }
                }

            } catch (Exception ex) {
                printException(ex);
            }
        }).start();
    }

    @FXML
    private void userDetailsTbl_onMouseClicked(MouseEvent event) {
        new Thread(() -> {
            try {

                UserTM userTM = userDetailsTbl.getSelectionModel().getSelectedItem();

                if (userTM != null) {

                    String userID = userTM.getUserID();

                    UserDTO userDTO = userBO.getUser(userID);

                    Platform.runLater(() -> {
                        userIDTF.setText(userDTO.getUserID());
                        nameTF.setText(userDTO.getName());
                        usernameTF.setText(userDTO.getUsername());
                        passwordTF.setText(userDTO.getPassword());
                        stateComBox.setValue(userDTO.getState());

                        newBtn.setDisable(false);
                        saveBtn.setDisable(true);
                        updateBtn.setDisable(false);
                        deleteBtn.setDisable(false);
                    });

                }

            } catch (Exception ex) {
                printException(ex);
            }
        }).start();
    }

    @FXML
    private void newBtn_onAction(ActionEvent event) {

        nameTF.setText("");
        usernameTF.setText("");
        passwordTF.setText("");
        stateComBox.setValue("");

        generateNextUserID();
        loadComboBox();
        loadAllUserDetails();

        nameTF.requestFocus();

        Platform.runLater(() -> {
            newBtn.setDisable(false);
            saveBtn.setDisable(false);
            updateBtn.setDisable(true);
            deleteBtn.setDisable(true);
        });

    }

    @FXML
    private void saveBtn_onAction(ActionEvent event) {

        Platform.runLater(() -> {

            boolean isAlreadyHave = false;

            userTMs = userDetailsTbl.getItems();

            for (UserTM utm : userTMs) {
                if (utm.getUserID().equals(userIDTF.getText()) | utm.getName().equals(nameTF.getText())) {
                    isAlreadyHave = true;
                    break;
                } else if (utm.getUsername().equals(usernameTF.getText()) & utm.getPassword().equals(passwordTF.getText())) {
                    isAlreadyHave = true;
                    break;
                }
            }

            if (isAlreadyHave) {
                new Alert(
                        Alert.AlertType.ERROR,
                        "The user you are going to save is already in user table.",
                        ButtonType.OK
                ).show();
            } else {
                try {

                    String userID = userIDTF.getText();
                    String name = nameTF.getText();
                    String username = usernameTF.getText();
                    String password = passwordTF.getText();
                    String state = stateComBox.getValue().toLowerCase();

                    UserDTO userDTO = new UserDTO(
                            userID,
                            name,
                            username,
                            password,
                            state
                    );

                    boolean result = false;

                    if (isValidTextFields()) {
                        result = userBO.saveUser(userDTO);
                    }

                    if (result) {
                        new Alert(
                                Alert.AlertType.INFORMATION,
                                "User '" + userDTO.getUserID() + "' has been Saved Successfully.",
                                ButtonType.OK
                        ).show();
                        newBtn.fire();
                    } else {
                        new Alert(
                                Alert.AlertType.ERROR,
                                "Failed to Save the User '" + userDTO.getUserID() + "'.",
                                ButtonType.OK
                        ).show();
                    }

                } catch (Exception ex) {
                    printException(ex);
                }
            }

        });

    }

    @FXML
    private void updateBtn_onAction(ActionEvent event) {

        Platform.runLater(() -> {

            boolean isAlreadyHave = false;

            userTMs = userDetailsTbl.getItems();

            for (UserTM utm : userTMs) {
                if (utm.getUserID().equals(userIDTF.getText()) | utm.getName().equals(nameTF.getText())) {
                    isAlreadyHave = true;
                    break;
                } else if (utm.getUsername().equals(usernameTF.getText()) & utm.getPassword().equals(passwordTF.getText())) {
                    isAlreadyHave = true;
                    break;
                }
            }

            if (!isAlreadyHave) {
                new Alert(
                        Alert.AlertType.ERROR,
                        "The user you are going to update is not avilable in user table.",
                        ButtonType.OK
                ).show();
            } else {
                try {

                    String userID = userIDTF.getText();
                    String name = nameTF.getText();
                    String username = usernameTF.getText();
                    String password = passwordTF.getText();
                    String state = stateComBox.getValue().toLowerCase();

                    UserDTO userDTO = new UserDTO(
                            userID,
                            name,
                            username,
                            password,
                            state
                    );

                    boolean result = false;

                    if (isValidTextFields()) {

                        Alert alert = new Alert(
                                Alert.AlertType.CONFIRMATION,
                                "Are You Sure to Update User " + userDTO.getUserID() + " ?",
                                ButtonType.OK,
                                ButtonType.NO
                        );
                        alert.setTitle("Update ?");
                        Button yesButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                        yesButton.setDefaultButton(false);
                        Button noButton = (Button) alert.getDialogPane().lookupButton(ButtonType.NO);
                        noButton.setDefaultButton(true);
                        Optional<ButtonType> action = alert.showAndWait();

                        if (action.get() == ButtonType.OK) {
                            result = userBO.updateUser(userDTO);

                            if (result) {
                                if (LogInController.loggedUser.getUserID().equals(userDTO.getUserID())) {
                                    new Alert(
                                            Alert.AlertType.INFORMATION,
                                            "User '" + userDTO.getUserID() + "' has been Updated Successfully.",
                                            ButtonType.OK
                                    ).showAndWait();

                                    MainDashController.isFirstTime = true;

                                    Parent root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/logIn.fxml"));
                                    Scene scene = new Scene(root);
                                    Stage stage = (Stage) this.userManagementAnchPane.getScene().getWindow();
                                    stage.setScene(scene);
                                    stage.centerOnScreen();
                                    stage.show();

                                    TranslateTransition trans = new TranslateTransition(Duration.millis(300), scene.getRoot());
                                    trans.setFromY(-scene.getHeight());
                                    trans.setToY(0);
                                    trans.play();
                                } else {
                                    new Alert(
                                            Alert.AlertType.INFORMATION,
                                            "User '" + userDTO.getUserID() + "' has been Updated Successfully.",
                                            ButtonType.OK
                                    ).show();
                                    newBtn.fire();
                                }
                            } else {
                                new Alert(
                                        Alert.AlertType.ERROR,
                                        "Failed to Update the User '" + userDTO.getUserID() + "'.",
                                        ButtonType.OK
                                ).show();
                            }
                        }

                    }

                } catch (Exception ex) {
                    printException(ex);
                }
            }

        });

    }

    private boolean isValidTextFields() {

        String userID = userIDTF.getText();
        String name = nameTF.getText();
        String username = usernameTF.getText();
        String password = passwordTF.getText();

        boolean matches1 = userID.matches("USR[.]{1}[0-9]{4}");
        boolean matches2 = name.matches("[a-z A-Z 0-9 . \\s]{3,}");
        boolean matches3 = username.matches("[a-z A-Z 0-9 ! @ # $ % ^ & * ( ) - _ < > : , ; ' / + ~ ` . \\s]{3,}");
        boolean matches4 = password.matches("[a-z A-Z 0-9 ! @ # $ % ^ & * ( ) - _ < > : , ; ' / + ~ ` . \\s]{3,}");

        boolean isValidTextFields = false;

        if (matches1) {
            if (matches2) {
                if (matches3) {
                    if (matches4) {
                        if (!stateComBox.getValue().isEmpty()) {
                            isValidTextFields = true;
                        } else {
                            new Alert(
                                    Alert.AlertType.ERROR,
                                    "Oops... Something Wrong in User State.",
                                    ButtonType.OK
                            ).show();
                            stateComBox.requestFocus();
                            stateComBox.show();
                        }
                    } else {
                        new Alert(
                                Alert.AlertType.ERROR,
                                "Oops... Something Wrong in Password.",
                                ButtonType.OK
                        ).show();
                        passwordTF.requestFocus();
                        passwordTF.selectAll();
                    }
                } else {
                    new Alert(
                            Alert.AlertType.ERROR,
                            "Oops... Something Wrong in Username.",
                            ButtonType.OK
                    ).show();
                    usernameTF.requestFocus();
                    usernameTF.selectAll();
                }
            } else {
                new Alert(
                        Alert.AlertType.ERROR,
                        "Oops... Something Wrong in Name.",
                        ButtonType.OK
                ).show();
                nameTF.requestFocus();
                nameTF.selectAll();
            }
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in User ID.",
                    ButtonType.OK
            ).show();
            newBtn.fire();
        }

        return isValidTextFields;

    }

    @FXML
    private void deleteBtn_onAction(ActionEvent event) {

        Platform.runLater(() -> {

            boolean isAlreadyHave = false;

            userTMs = userDetailsTbl.getItems();

            for (UserTM utm : userTMs) {
                if (utm.getUserID().equals(userIDTF.getText()) | utm.getName().equals(nameTF.getText())) {
                    isAlreadyHave = true;
                    break;
                } else if (utm.getUsername().equals(usernameTF.getText()) & utm.getPassword().equals(passwordTF.getText())) {
                    isAlreadyHave = true;
                    break;
                }
            }

            if (!isAlreadyHave) {
                new Alert(
                        Alert.AlertType.ERROR,
                        "The user you are going to delete is not avilable in user table.",
                        ButtonType.OK
                ).show();
            } else {
                try {

                    String userID = userIDTF.getText();
                    boolean matches = userID.matches("USR[.]{1}[0-9]{4}");

                    boolean result = false;

                    if (matches) {

                        Alert alert = new Alert(
                                Alert.AlertType.CONFIRMATION,
                                "Are You Sure to Delete User " + userID + " ?",
                                ButtonType.OK,
                                ButtonType.NO
                        );
                        alert.setTitle("Delete ?");
                        Button yesButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                        yesButton.setDefaultButton(false);
                        Button noButton = (Button) alert.getDialogPane().lookupButton(ButtonType.NO);
                        noButton.setDefaultButton(true);
                        Optional<ButtonType> action = alert.showAndWait();

                        if (action.get() == ButtonType.OK) {

                            boolean isAlreadyUsed = false;

                            ArrayList<BorrowDTO> borrowDTOs = borrowBO.getAllBorrows();

                            for (BorrowDTO bdto : borrowDTOs) {
                                if (bdto.getUserID().equals(userID)) {
                                    isAlreadyUsed = true;
                                    break;
                                }
                            }

                            if (isAlreadyUsed) {
                                new Alert(
                                        Alert.AlertType.ERROR,
                                        "Sorry, You can't Delete this Already Used User '" + userID + "'",
                                        ButtonType.OK
                                ).show();
                            } else {
                                result = userBO.deleteUser(userID);

                                if (result) {
                                    if (LogInController.loggedUser.getUserID().equals(userID)) {
                                        new Alert(
                                                Alert.AlertType.INFORMATION,
                                                "User '" + userID + "' has been Deleted Successfully.",
                                                ButtonType.OK
                                        ).showAndWait();

                                        if (LogInController.loggedUser.getUserID().equals(userID)) {
                                            MainDashController.isFirstTime = true;

                                            Parent root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/logIn.fxml"));
                                            Scene scene = new Scene(root);
                                            Stage stage = (Stage) this.userManagementAnchPane.getScene().getWindow();
                                            stage.setScene(scene);
                                            stage.centerOnScreen();
                                            stage.show();

                                            TranslateTransition trans = new TranslateTransition(Duration.millis(300), scene.getRoot());
                                            trans.setFromY(-scene.getHeight());
                                            trans.setToY(0);
                                            trans.play();
                                        }
                                    } else {
                                        new Alert(
                                                Alert.AlertType.INFORMATION,
                                                "User '" + userID + "' has been Deleted Successfully.",
                                                ButtonType.OK
                                        ).show();
                                        newBtn.fire();
                                    }
                                } else {
                                    new Alert(
                                            Alert.AlertType.ERROR,
                                            "Failed to Delete the User '" + userID + "'.",
                                            ButtonType.OK
                                    ).show();
                                }
                            }

                        }

                    }

                } catch (Exception ex) {
                    printException(ex);
                }
            }

        });

    }

    private void loadUserDetailsTbl(ArrayList<UserDTO> userDTOs) {

        Platform.runLater(() -> {
            countLbl.setText(Integer.toString(userDTOs.size()));

            userTMs = userDetailsTbl.getItems();
            userDetailsTbl.getItems().clear();

            userTMs.removeAll(userTMs);
            userTMs.clear();

            for (UserDTO userDTO : userDTOs) {
                userTMs.add(new UserTM(
                        userDTO.getUserID(),
                        userDTO.getName(),
                        userDTO.getUsername(),
                        userDTO.getPassword(),
                        userDTO.getState()
                ));
            }
        });

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
