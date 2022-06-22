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
import org.sahurdayathra.BookShelfLMS.business.custom.BookPublisherBO;
import org.sahurdayathra.BookShelfLMS.dto.BookPublisherDTO;
import org.sahurdayathra.BookShelfLMS.view.util.tblmodel.BookPublisherTM;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class BookPublisherController implements Initializable {

    @FXML
    private AnchorPane bookPublisherAnchPane;
    @FXML
    private HBox homeHBox;
    @FXML
    private HBox helpHBox;
    @FXML
    private HBox viewMoreHBox;
    @FXML
    private HBox backHBox;
    @FXML
    private JFXTextField nameTF;
    @FXML
    private JFXTextField bookPublisherIDTF;
    @FXML
    private JFXTextField contactNumberTF;
    @FXML
    private JFXTextArea otherDetailsTA;
    @FXML
    private JFXTextField addressVillageTF;
    @FXML
    private JFXTextField addressStreetTF;
    @FXML
    private JFXTextField addressNoTF;
    @FXML
    private JFXTextField addressCityTF;
    @FXML
    private JFXTextField emailTF;
    @FXML
    private TextField searchBoxTF;
    @FXML
    private Label countLbl;
    @FXML
    private TableView<BookPublisherTM> bookPublisherDetailsTbl;
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

    ObservableList<BookPublisherTM> bookPublisherTMs;

    BookPublisherBO bookPublisherBO = (BookPublisherBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOKPUBLISHER);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.gc();

        bookPublisherDetailsTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        bookPublisherDetailsTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        bookPublisherDetailsTbl.getColumns().get(2).setStyle("-fx-alignment:center");
        bookPublisherDetailsTbl.getColumns().get(3).setStyle("-fx-alignment:center");
        bookPublisherDetailsTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("bookPublisherID"));
        bookPublisherDetailsTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        bookPublisherDetailsTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        bookPublisherDetailsTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contactNO"));

        newBtn.fire();

    }

    @FXML
    private void homeHBox_onMouseClicked(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/mainDash.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) this.bookPublisherAnchPane.getScene().getWindow();
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
    private void backHBox_onMouseClicked(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/bookManagement.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) this.bookPublisherAnchPane.getScene().getWindow();
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
                stage.initOwner(bookPublisherAnchPane.getScene().getWindow());
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
                stage.initOwner(bookPublisherAnchPane.getScene().getWindow());
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

    private void generateBookPublisherID() {
        new Thread(() -> {
            try {
                String bookPublisherID = bookPublisherBO.getNextBookPublisherID();
                Platform.runLater(() -> {
                    bookPublisherIDTF.setText(bookPublisherID);
                });
            } catch (Exception ex) {
                printException(ex);
            }
        }).start();
    }

    private void loadAllBookPublisherDetails() {
        new Thread(() -> {
            try {
                ArrayList<BookPublisherDTO> bookPublisherDTOs = bookPublisherBO.getAllBookPublishers();
                loadBookPublisherDetailsTbl(bookPublisherDTOs);
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
            emailTF.requestFocus();
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
    private void emailTF_onAction(ActionEvent event) {
        String addressCity = emailTF.getText();
        boolean matches = addressCity.matches("([a-z A-Z 0-9]+[@]{1}[a-z]{3,}[.]{1}[com | org | net | biz | lk]{2,3})?");
        if (matches) {
            contactNumberTF.requestFocus();
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in E-Mail.",
                    ButtonType.OK
            ).show();
            emailTF.requestFocus();
            emailTF.selectAll();
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
                    loadAllBookPublisherDetails();
                } else {
                    String searchText = searchBoxTF.getText();

                    ArrayList<BookPublisherDTO> bookPublisherDTOs = bookPublisherBO.searchBookPublisher(searchText);

                    if (bookPublisherDTOs.isEmpty()) {
                        Platform.runLater(() -> {
                            searchBoxTF.setStyle("-fx-text-fill: #D91022");
                        });
                        loadAllBookPublisherDetails();
                    } else {
                        Platform.runLater(() -> {
                            searchBoxTF.setStyle("-fx-text-fill: #000000");
                        });
                        loadBookPublisherDetailsTbl(bookPublisherDTOs);
                    }
                }

            } catch (Exception ex) {
                printException(ex);
            }
        }).start();
    }

    @FXML
    private void bookPublisherDetailsTbl_onMouseClicked(MouseEvent event) {
        new Thread(() -> {
            try {

                BookPublisherTM bookPublisherTM = bookPublisherDetailsTbl.getSelectionModel().getSelectedItem();

                if (bookPublisherTM != null) {
                    String bookPublisherID = bookPublisherTM.getBookPublisherID();

                    BookPublisherDTO bookPublisherDTO = bookPublisherBO.getBookPublisher(bookPublisherID);

                    Platform.runLater(() -> {
                        bookPublisherIDTF.setText(bookPublisherDTO.getBookPublisherID());
                        nameTF.setText(bookPublisherDTO.getName());
                        if (bookPublisherDTO.getAddress_no() == null) {
                            addressNoTF.setText("");
                        } else {
                            addressNoTF.setText(bookPublisherDTO.getAddress_no());
                        }
                        if (bookPublisherDTO.getAddress_street() == null) {
                            addressStreetTF.setText("");
                        } else {
                            addressStreetTF.setText(bookPublisherDTO.getAddress_street());
                        }
                        if (bookPublisherDTO.getAddress_village() == null) {
                            addressVillageTF.setText("");
                        } else {
                            addressVillageTF.setText(bookPublisherDTO.getAddress_village());
                        }
                        if (bookPublisherDTO.getAddress_city() == null) {
                            addressCityTF.setText("");
                        } else {
                            addressCityTF.setText(bookPublisherDTO.getAddress_city());
                        }
                        if (bookPublisherDTO.getEmail() == null) {
                            emailTF.setText("");
                        } else {
                            emailTF.setText(bookPublisherDTO.getEmail());
                        }
                        if (bookPublisherDTO.getContactNO() == null) {
                            contactNumberTF.setText("");
                        } else {
                            contactNumberTF.setText(bookPublisherDTO.getContactNO());
                        }
                        if (bookPublisherDTO.getOtherDetails() == null) {
                            otherDetailsTA.setText("");
                        } else {
                            otherDetailsTA.setText(bookPublisherDTO.getOtherDetails());
                        }

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

        bookPublisherIDTF.setText("");
        nameTF.setText("");
        addressNoTF.setText("");
        addressStreetTF.setText("");
        addressVillageTF.setText("");
        addressCityTF.setText("");
        emailTF.setText("");
        contactNumberTF.setText("");
        otherDetailsTA.setText("");

        generateBookPublisherID();
        loadAllBookPublisherDetails();

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

            bookPublisherTMs = bookPublisherDetailsTbl.getItems();

            for (BookPublisherTM bptm : bookPublisherTMs) {
                if (bptm.getBookPublisherID().equals(bookPublisherIDTF.getText()) | bptm.getName().equals(nameTF.getText())) {
                    isAlreadyHave = true;
                    break;
                }
            }

            if (isAlreadyHave) {
                new Alert(
                        Alert.AlertType.ERROR,
                        "The publisher you are going to save is already in publisher table.",
                        ButtonType.OK
                ).show();
            } else {
                try {

                    String bookPublisherID = bookPublisherIDTF.getText();
                    String name = nameTF.getText();
                    String addressNo = addressNoTF.getText();
                    String addressStreet = addressStreetTF.getText();
                    String addressVillage = addressVillageTF.getText();
                    String addressCity = addressCityTF.getText();
                    String email = emailTF.getText();
                    String contactNumber = contactNumberTF.getText();
                    String otherDetails = otherDetailsTA.getText();

                    BookPublisherDTO bookPublisherDTO = new BookPublisherDTO(
                            bookPublisherID,
                            name,
                            addressNo,
                            addressStreet,
                            addressVillage,
                            addressCity,
                            email,
                            contactNumber,
                            otherDetails
                    );

                    boolean result = false;

                    if (isValidTextFields()) {
                        result = bookPublisherBO.saveBookPublisher(bookPublisherDTO);
                    }

                    if (result) {
                        new Alert(
                                Alert.AlertType.INFORMATION,
                                "Publisher '" + bookPublisherDTO.getBookPublisherID() + "' has been Saved Successfully.",
                                ButtonType.OK
                        ).show();
                        newBtn.fire();
                    } else {
                        new Alert(
                                Alert.AlertType.ERROR,
                                "Failed to Save the Publisher '" + bookPublisherDTO.getBookPublisherID() + "'.",
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

            bookPublisherTMs = bookPublisherDetailsTbl.getItems();

            for (BookPublisherTM bptm : bookPublisherTMs) {
                if (bptm.getBookPublisherID().equals(bookPublisherIDTF.getText()) | bptm.getName().equals(nameTF.getText())) {
                    isAlreadyHave = true;
                    break;
                }
            }

            if (!isAlreadyHave) {
                new Alert(
                        Alert.AlertType.ERROR,
                        "The publisher you are going to update is not avilable in publisher table.",
                        ButtonType.OK
                ).show();
            } else {
                try {

                    String bookPublisherID = bookPublisherIDTF.getText();
                    String name = nameTF.getText();
                    String addressNo = addressNoTF.getText();
                    String addressStreet = addressStreetTF.getText();
                    String addressVillage = addressVillageTF.getText();
                    String addressCity = addressCityTF.getText();
                    String email = emailTF.getText();
                    String contactNumber = contactNumberTF.getText();
                    String otherDetails = otherDetailsTA.getText();

                    BookPublisherDTO bookPublisherDTO = new BookPublisherDTO(
                            bookPublisherID,
                            name,
                            addressNo,
                            addressStreet,
                            addressVillage,
                            addressCity,
                            email,
                            contactNumber,
                            otherDetails
                    );

                    boolean result = false;

                    if (isValidTextFields()) {

                        if (bookPublisherID.equals("PUB.0000")) {
                            new Alert(
                                    Alert.AlertType.ERROR,
                                    "You can't Update Default Publisher 'PUB.0000'.",
                                    ButtonType.OK
                            ).show();
                        } else {
                            Alert alert = new Alert(
                                    Alert.AlertType.CONFIRMATION,
                                    "Are You Sure to Update Publisher " + bookPublisherDTO.getBookPublisherID() + " ?",
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
                                result = bookPublisherBO.updateBookPublisher(bookPublisherDTO);

                                if (result) {
                                    new Alert(
                                            Alert.AlertType.INFORMATION,
                                            "Publisher '" + bookPublisherDTO.getBookPublisherID() + "' has been Updated Successfully.",
                                            ButtonType.OK
                                    ).show();
                                    newBtn.fire();
                                } else {
                                    new Alert(
                                            Alert.AlertType.ERROR,
                                            "Failed to Update the Publisher '" + bookPublisherDTO.getBookPublisherID() + "'.",
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

    private boolean isValidTextFields() {

        String bookPublisherID = bookPublisherIDTF.getText();
        String name = nameTF.getText();
        String addressNo = addressNoTF.getText();
        String addressStreet = addressStreetTF.getText();
        String addressVillage = addressVillageTF.getText();
        String addressCity = addressCityTF.getText();
        String email = emailTF.getText();
        String contactNumber = contactNumberTF.getText();
        String otherDetails = otherDetailsTA.getText();

        boolean matches1 = bookPublisherID.matches("PUB[.]{1}[0-9]{4}");
        boolean matches2 = name.matches("[a-z A-Z . \\s]{2,}");
        boolean matches3 = addressNo.matches("([0-9 A-Z]{1,})?([/]?[A-Z 0-9])?([/]?[A-Z 0-9])?");
        boolean matches4 = addressStreet.matches("([a-z A-Z 0-9 \\s]{5,})?");
        boolean matches5 = addressVillage.matches("([a-z A-Z \\s]{5,})?");
        boolean matches6 = addressCity.matches("([a-z A-Z \\s]{5,})?");
        boolean matches7 = email.matches("([a-z A-Z 0-9]+[@]{1}[a-z]{3,}[.]{1}[com | org | net | biz | lk]{2,3})?");
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
                                            "Oops... Something Wrong in E-Mail.",
                                            ButtonType.OK
                                    ).show();
                                    emailTF.requestFocus();
                                    emailTF.selectAll();
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
                    "Oops... Something Wrong in Book Publisher ID.",
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

            bookPublisherTMs = bookPublisherDetailsTbl.getItems();

            for (BookPublisherTM bptm : bookPublisherTMs) {
                if (bptm.getBookPublisherID().equals(bookPublisherIDTF.getText()) | bptm.getName().equals(nameTF.getText())) {
                    isAlreadyHave = true;
                    break;
                }
            }

            if (!isAlreadyHave) {
                new Alert(
                        Alert.AlertType.ERROR,
                        "The publisher you are going to delete is not avilable in publisher table.",
                        ButtonType.OK
                ).show();
            } else {
                try {

                    String bookPublisherID = bookPublisherIDTF.getText();
                    boolean matches = bookPublisherID.matches("PUB[.]{1}[0-9]{4}");

                    boolean result = false;

                    if (matches) {

                        if (bookPublisherID.equals("PUB.0000")) {
                            new Alert(
                                    Alert.AlertType.ERROR,
                                    "You can't Delete Default Publisher 'PUB.0000'.",
                                    ButtonType.OK
                            ).show();
                        } else {
                            Alert alert = new Alert(
                                    Alert.AlertType.CONFIRMATION,
                                    "Are You Sure to Delete Publisher " + bookPublisherID + " ?",
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
                                result = bookPublisherBO.deleteBookPublisher(bookPublisherID);

                                if (result) {
                                    new Alert(
                                            Alert.AlertType.INFORMATION,
                                            "Publisher '" + bookPublisherID + "' has been Deleted Successfully.",
                                            ButtonType.OK
                                    ).show();
                                    newBtn.fire();
                                } else {
                                    new Alert(
                                            Alert.AlertType.ERROR,
                                            "Failed to Delete the Publisher '" + bookPublisherID + "'.",
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

    private void loadBookPublisherDetailsTbl(ArrayList<BookPublisherDTO> bookPublisherDTOs) {

        Platform.runLater(() -> {
            countLbl.setText(Integer.toString(bookPublisherDTOs.size()));

            bookPublisherTMs = bookPublisherDetailsTbl.getItems();
            bookPublisherDetailsTbl.getItems().clear();

            bookPublisherTMs.removeAll(bookPublisherTMs);
            bookPublisherTMs.clear();

            for (BookPublisherDTO bookPublisherDTO : bookPublisherDTOs) {
                bookPublisherTMs.add(new BookPublisherTM(
                        bookPublisherDTO.getBookPublisherID(),
                        bookPublisherDTO.getName(),
                        bookPublisherDTO.getAddress_no()
                        + ", " + bookPublisherDTO.getAddress_street()
                        + ", " + bookPublisherDTO.getAddress_village()
                        + ", " + bookPublisherDTO.getAddress_city(),
                        bookPublisherDTO.getContactNO()
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
