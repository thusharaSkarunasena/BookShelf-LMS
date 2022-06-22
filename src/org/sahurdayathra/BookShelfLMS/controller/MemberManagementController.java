package org.sahurdayathra.BookShelfLMS.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
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
import org.sahurdayathra.BookShelfLMS.business.custom.MemberBO;
import org.sahurdayathra.BookShelfLMS.dto.BorrowDTO;
import org.sahurdayathra.BookShelfLMS.dto.MemberDTO;
import org.sahurdayathra.BookShelfLMS.view.util.tblmodel.MemberTM;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class MemberManagementController implements Initializable {

    @FXML
    private AnchorPane memberManagementAnchPane;
    @FXML
    private HBox homeHBox;
    @FXML
    private HBox viewMoreHBox;
    @FXML
    private HBox helpHBox;
    @FXML
    private JFXTextField nameTF;
    @FXML
    private JFXTextField libRegNOTF;
    @FXML
    private JFXTextField addressVillageTF;
    @FXML
    private JFXTextField addressStreetTF;
    @FXML
    private JFXTextField addressNoTF;
    @FXML
    private JFXTextField addressCityTF;
    @FXML
    private JFXTextField admissionNOTF;
    @FXML
    private JFXTextField contactNumberTF;
    @FXML
    private JFXTextArea otherDetailsTA;
    @FXML
    private TextField searchBoxTF;
    @FXML
    private Label countLbl;
    @FXML
    private TableView<MemberTM> memberDetailsTbl;
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

    ObservableList<MemberTM> memberTMs;

    MemberBO memberBO = (MemberBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.MEMBER);
    BorrowBO borrowBO = (BorrowBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BORROW);

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {

        System.gc();

        memberDetailsTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        memberDetailsTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        memberDetailsTbl.getColumns().get(2).setStyle("-fx-alignment:center");
        memberDetailsTbl.getColumns().get(3).setStyle("-fx-alignment:center");
        memberDetailsTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("libRegNO"));
        memberDetailsTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("admissionNO"));
        memberDetailsTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("name"));
        memberDetailsTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));

        newBtn.fire();

    }

    @FXML
    private void homeHBox_onMouseClicked(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/mainDash.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) this.memberManagementAnchPane.getScene().getWindow();
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
                stage.initOwner(memberManagementAnchPane.getScene().getWindow());
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
                stage.initOwner(memberManagementAnchPane.getScene().getWindow());
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

    private void generateNextLibRegNO() {
        new Thread(() -> {
            try {
                String libRegNO = memberBO.getNextLibRegNO();
                if (libRegNO.equals("MEM.999999")) {
                    new Alert(
                            Alert.AlertType.ERROR,
                            "You Can't Add More Members to the Database.",
                            ButtonType.OK
                    ).show();
                    saveBtn.setDisable(true);
                } else {
                    Platform.runLater(() -> {
                        libRegNOTF.setText(libRegNO);
                    });
                }
            } catch (Exception ex) {
                printException(ex);
            }
        }).start();
    }

    private void loadAllMemberDetails() {
        new Thread(() -> {
            try {
                ArrayList<MemberDTO> memberDTOs = memberBO.getAllMembers();
                loadMemberDetailsTbl(memberDTOs);
            } catch (Exception ex) {
                printException(ex);
            }
        }).start();
    }

    @FXML
    private void nameTF_onAction(ActionEvent event) {
        String name = nameTF.getText();
        boolean matches = name.matches("[a-z A-Z . \\s]{3,}");
        if (matches) {
            addressNoTF.requestFocus();
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
    private void addressVillageTF_onAction(ActionEvent event) {
        String addressVillage = addressVillageTF.getText();
        boolean matches = addressVillage.matches("([a-z A-Z \\s]{5,})?");
        if (matches) {
            addressCityTF.requestFocus();
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Address Village.",
                    ButtonType.OK
            ).show();
            addressVillageTF.requestFocus();
            addressVillageTF.selectAll();
        }
    }

    @FXML
    private void addressStreetTF_onAction(ActionEvent event) {
        String addressStreet = addressStreetTF.getText();
        boolean matches = addressStreet.matches("([a-z A-Z 0-9 \\s]{5,})?");
        if (matches) {
            addressVillageTF.requestFocus();
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Address Street.",
                    ButtonType.OK
            ).show();
            addressStreetTF.requestFocus();
            addressStreetTF.selectAll();
        }
    }

    @FXML
    private void addressNoTF_onAction(ActionEvent event) {
        String addressNo = addressNoTF.getText();
        boolean matches = addressNo.matches("([0-9 A-Z]{1,})?([/]?[A-Z 0-9])?([/]?[A-Z 0-9])?");
        if (matches) {
            addressStreetTF.requestFocus();
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Address Number.",
                    ButtonType.OK
            ).show();
            addressNoTF.requestFocus();
            addressNoTF.selectAll();
        }
    }

    @FXML
    private void addressCityTF_onAction(ActionEvent event) {
        String addressCity = addressCityTF.getText();
        boolean matches = addressCity.matches("([a-z A-Z \\s]{5,})?");
        if (matches) {
            contactNumberTF.requestFocus();
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Address City.",
                    ButtonType.OK
            ).show();
            addressCityTF.requestFocus();
            addressCityTF.selectAll();
        }
    }

    @FXML
    private void admissionNOTF_onAction(ActionEvent event) {
        String admissionNO = admissionNOTF.getText();
        boolean matches = admissionNO.matches("[0-9 \\s]{3,}");
        if (matches) {
            nameTF.requestFocus();
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Admission Number.",
                    ButtonType.OK
            ).show();
            admissionNOTF.requestFocus();
            admissionNOTF.selectAll();
        }
    }

    @FXML
    private void contactNumberTF_onAction(ActionEvent event) {
        String contactNumber = contactNumberTF.getText();
        boolean matches = contactNumber.matches("([0-9]{10})?");
        if (matches) {
            otherDetailsTA.requestFocus();
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Contact Number.",
                    ButtonType.OK
            ).show();
            contactNumberTF.requestFocus();
            contactNumberTF.selectAll();
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
                    loadAllMemberDetails();
                } else {
                    String searchText = searchBoxTF.getText();

                    ArrayList<MemberDTO> memberDTOs = memberBO.searchMember(searchText);

                    if (memberDTOs.isEmpty()) {
                        Platform.runLater(() -> {
                            searchBoxTF.setStyle("-fx-text-fill: #D91022");
                        });
                        loadAllMemberDetails();
                    } else {
                        Platform.runLater(() -> {
                            searchBoxTF.setStyle("-fx-text-fill: #000000");
                        });
                        loadMemberDetailsTbl(memberDTOs);
                    }
                }

            } catch (Exception ex) {
                printException(ex);
            }
        }).start();
    }

    @FXML
    private void memberDetailsTbl_onMouseClicked(MouseEvent event) {
        new Thread(() -> {
            try {

                MemberTM memberTM = memberDetailsTbl.getSelectionModel().getSelectedItem();

                if (memberTM != null) {

                    String libRegNO = memberTM.getLibRegNO();

                    MemberDTO memberDTO = memberBO.getMember(libRegNO);

                    Platform.runLater(() -> {
                        libRegNOTF.setText(memberDTO.getLibRegNO());
                        admissionNOTF.setText(memberDTO.getAdmissionNO());
                        nameTF.setText(memberDTO.getName());
                        if (memberDTO.getAddress_no() == null) {
                            addressNoTF.setText("");
                        } else {
                            addressNoTF.setText(memberDTO.getAddress_no());
                        }
                        if (memberDTO.getAddress_street() == null) {
                            addressStreetTF.setText("");
                        } else {
                            addressStreetTF.setText(memberDTO.getAddress_street());
                        }
                        if (memberDTO.getAddress_village() == null) {
                            addressVillageTF.setText("");
                        } else {
                            addressVillageTF.setText(memberDTO.getAddress_village());
                        }
                        if (memberDTO.getAddress_city() == null) {
                            addressCityTF.setText("");
                        } else {
                            addressCityTF.setText(memberDTO.getAddress_city());
                        }
                        if (memberDTO.getContactNO() == null) {
                            contactNumberTF.setText("");
                        } else {
                            contactNumberTF.setText(memberDTO.getContactNO());
                        }
                        if (memberDTO.getOtherDetail() == null) {
                            otherDetailsTA.setText("");
                        } else {
                            otherDetailsTA.setText(memberDTO.getOtherDetail());
                        }

                        newBtn.setDisable(false);
                        saveBtn.setDisable(true);
                        updateBtn.setDisable(false);

                        boolean isAlreadyTaken = false;

                        try {
                            ArrayList<BorrowDTO> borrowDTOs = borrowBO.getAllBorrows();

                            for (BorrowDTO bdto : borrowDTOs) {
                                if (bdto.getLibRegNO().equals(libRegNO)) {
                                    isAlreadyTaken = true;
                                    break;
                                }
                            }
                        } catch (Exception ex) {
                            printException(ex);
                        }

                        if (isAlreadyTaken) {
                            deleteBtn.setDisable(true);
                        } else {
                            deleteBtn.setDisable(false);
                        }

                    });

                }

            } catch (Exception ex) {
                printException(ex);
            }
        }).start();
    }

    @FXML
    private void newBtn_onAction(ActionEvent event) {

        admissionNOTF.setText("");
        nameTF.setText("");
        addressNoTF.setText("");
        addressStreetTF.setText("");
        addressVillageTF.setText("");
        addressCityTF.setText("");
        contactNumberTF.setText("");
        otherDetailsTA.setText("");
        searchBoxTF.setText("");

        generateNextLibRegNO();
        loadAllMemberDetails();

        admissionNOTF.requestFocus();

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

            memberTMs = memberDetailsTbl.getItems();

            for (MemberTM mtm : memberTMs) {
                if (mtm.getLibRegNO().equals(libRegNOTF.getText()) | mtm.getAdmissionNO().equals(admissionNOTF.getText()) | mtm.getName().equals(nameTF.getText())) {
                    isAlreadyHave = true;
                    break;
                }
            }

            if (isAlreadyHave) {
                new Alert(
                        Alert.AlertType.ERROR,
                        "The Member you are going to save is already in Member table.",
                        ButtonType.OK
                ).show();
            } else {
                try {

                    String libRegNO = libRegNOTF.getText();
                    String admissionNO = admissionNOTF.getText();
                    String name = nameTF.getText();
                    String addressNo = addressNoTF.getText();
                    String addressStreet = addressStreetTF.getText();
                    String addressVillage = addressVillageTF.getText();
                    String addressCity = addressCityTF.getText();
                    String contactNumber = contactNumberTF.getText();
                    String otherDetails = otherDetailsTA.getText();

                    MemberDTO memberDTO = new MemberDTO(
                            libRegNO,
                            admissionNO,
                            name,
                            addressNo,
                            addressStreet,
                            addressVillage,
                            addressCity,
                            contactNumber,
                            otherDetails
                    );

                    boolean result = false;

                    if (isValidTextFields()) {
                        result = memberBO.saveMember(memberDTO);
                    }

                    if (result) {
                        new Alert(
                                Alert.AlertType.INFORMATION,
                                "Member '" + memberDTO.getLibRegNO() + "' has been Saved Successfully.",
                                ButtonType.OK
                        ).show();
                        newBtn.fire();
                    } else {
                        new Alert(
                                Alert.AlertType.ERROR,
                                "Failed to Save the Member '" + memberDTO.getLibRegNO() + "'.",
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

            memberTMs = memberDetailsTbl.getItems();

            for (MemberTM mtm : memberTMs) {
                if (mtm.getLibRegNO().equals(libRegNOTF.getText())) {
                    isAlreadyHave = true;
                    break;
                }
            }

            if (!isAlreadyHave) {
                new Alert(
                        Alert.AlertType.ERROR,
                        "The member you are going to update is not avilable in member table.",
                        ButtonType.OK
                ).show();
            } else {
                try {

                    String libRegNO = libRegNOTF.getText();
                    String admissionNO = admissionNOTF.getText();
                    String name = nameTF.getText();
                    String addressNo = addressNoTF.getText();
                    String addressStreet = addressStreetTF.getText();
                    String addressVillage = addressVillageTF.getText();
                    String addressCity = addressCityTF.getText();
                    String contactNumber = contactNumberTF.getText();
                    String otherDetails = otherDetailsTA.getText();

                    MemberDTO memberDTO = new MemberDTO(
                            libRegNO,
                            admissionNO,
                            name,
                            addressNo,
                            addressStreet,
                            addressVillage,
                            addressCity,
                            contactNumber,
                            otherDetails
                    );

                    boolean result = false;

                    if (isValidTextFields()) {

                        Alert alert = new Alert(
                                Alert.AlertType.CONFIRMATION,
                                "Are You Sure to Update Member " + memberDTO.getLibRegNO() + " ?",
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
                            result = memberBO.updateMember(memberDTO);

                            if (result) {
                                new Alert(
                                        Alert.AlertType.INFORMATION,
                                        "Member '" + memberDTO.getLibRegNO() + "' has been Updated Successfully.",
                                        ButtonType.OK
                                ).show();
                                newBtn.fire();
                            } else {
                                new Alert(
                                        Alert.AlertType.ERROR,
                                        "Failed to Update the Member '" + memberDTO.getLibRegNO() + "'.",
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

        String libRegNO = libRegNOTF.getText();
        String admissionNO = admissionNOTF.getText();
        String name = nameTF.getText();
        String addressNo = addressNoTF.getText();
        String addressStreet = addressStreetTF.getText();
        String addressVillage = addressVillageTF.getText();
        String addressCity = addressCityTF.getText();
        String contactNumber = contactNumberTF.getText();
        String otherDetails = otherDetailsTA.getText();

        boolean matches1 = libRegNO.matches("MEM[.]{1}[0-9]{6}");
        boolean matches2 = admissionNO.matches("[0-9 \\s]{3,}");
        boolean matches3 = name.matches("[a-z A-Z . \\s]{3,}");
        boolean matches4 = addressNo.matches("([0-9 A-Z]{1,})?([/]?[A-Z 0-9])?([/]?[A-Z 0-9])?");
        boolean matches5 = addressStreet.matches("([a-z A-Z 0-9 \\s]{5,})?");
        boolean matches6 = addressVillage.matches("([a-z A-Z \\s]{5,})?");
        boolean matches7 = addressCity.matches("([a-z A-Z \\s]{5,})?");
        boolean matches8 = contactNumber.matches("([0-9]{10})?");
        boolean matches9 = otherDetails.matches("([0-9 a-z A-Z . , / ' \" \\s]{1,})?");

        boolean isValidTextFields = false;

        if (matches1) {
            if (matches2) {
                if (matches3) {
                    if (matches4) {
                        if (matches5) {
                            if (matches6) {
                                if (matches7) {
                                    if (matches8) {
                                        if (matches9) {
                                            isValidTextFields = true;
                                        } else {
                                            new Alert(
                                                    Alert.AlertType.ERROR,
                                                    "Oops... Something Wrong in Other Details.",
                                                    ButtonType.OK
                                            ).show();
                                            otherDetailsTA.requestFocus();
                                            otherDetailsTA.selectAll();
                                        }
                                    } else {
                                        new Alert(
                                                Alert.AlertType.ERROR,
                                                "Oops... Something Wrong in Contact Number.",
                                                ButtonType.OK
                                        ).show();
                                        contactNumberTF.requestFocus();
                                        contactNumberTF.selectAll();
                                    }
                                } else {
                                    new Alert(
                                            Alert.AlertType.ERROR,
                                            "Oops... Something Wrong in Address City.",
                                            ButtonType.OK
                                    ).show();
                                    addressCityTF.requestFocus();
                                    addressCityTF.selectAll();
                                }
                            } else {
                                new Alert(
                                        Alert.AlertType.ERROR,
                                        "Oops... Something Wrong in Address Village.",
                                        ButtonType.OK
                                ).show();
                                addressVillageTF.requestFocus();
                                addressVillageTF.selectAll();
                            }
                        } else {
                            new Alert(
                                    Alert.AlertType.ERROR,
                                    "Oops... Something Wrong in Address Street.",
                                    ButtonType.OK
                            ).show();
                            addressStreetTF.requestFocus();
                            addressStreetTF.selectAll();
                        }
                    } else {
                        new Alert(
                                Alert.AlertType.ERROR,
                                "Oops... Something Wrong in Address Number.",
                                ButtonType.OK
                        ).show();
                        addressNoTF.requestFocus();
                        addressNoTF.selectAll();
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
                        "Oops... Something Wrong in Admission Number.",
                        ButtonType.OK
                ).show();
                admissionNOTF.requestFocus();
                admissionNOTF.selectAll();
            }
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Library Register Number.",
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

            memberTMs = memberDetailsTbl.getItems();

            for (MemberTM mtm : memberTMs) {
                if (mtm.getLibRegNO().equals(libRegNOTF.getText())) {
                    isAlreadyHave = true;
                    break;
                }
            }

            if (!isAlreadyHave) {
                new Alert(
                        Alert.AlertType.ERROR,
                        "The member you are going to delete is not avilable in member table.",
                        ButtonType.OK
                ).show();
            } else {
                try {

                    String libRegNO = libRegNOTF.getText();
                    boolean matches = libRegNO.matches("MEM[.]{1}[0-9]{6}");

                    boolean result = false;

                    if (matches) {

                        Alert alert = new Alert(
                                Alert.AlertType.CONFIRMATION,
                                "Are You Sure to Delete Member " + libRegNO + " ?",
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
                            result = memberBO.deleteMember(libRegNO);

                            if (result) {
                                new Alert(
                                        Alert.AlertType.INFORMATION,
                                        "Member '" + libRegNO + "' has been Deleted Successfully.",
                                        ButtonType.OK
                                ).show();
                                newBtn.fire();
                            } else {
                                new Alert(
                                        Alert.AlertType.ERROR,
                                        "Failed to Delete the Member '" + libRegNO + "'.",
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

    private void loadMemberDetailsTbl(ArrayList<MemberDTO> memberDTOs) {

        Platform.runLater(() -> {
            countLbl.setText(Integer.toString(memberDTOs.size()));

            memberTMs = memberDetailsTbl.getItems();
            memberDetailsTbl.getItems().clear();

            memberTMs.removeAll(memberTMs);
            memberTMs.clear();

            for (MemberDTO memberDTO : memberDTOs) {
                memberTMs.add(new MemberTM(
                        memberDTO.getLibRegNO(),
                        memberDTO.getAdmissionNO(),
                        memberDTO.getName(),
                        memberDTO.getAddress_no()
                        + ", " + memberDTO.getAddress_street()
                        + ", " + memberDTO.getAddress_village()
                        + ", " + memberDTO.getAddress_city()
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
