package org.sahurdayathra.BookShelfLMS.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
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
import org.sahurdayathra.BookShelfLMS.business.custom.BookBO;
import org.sahurdayathra.BookShelfLMS.business.custom.BorrowBO;
import org.sahurdayathra.BookShelfLMS.business.custom.MemberBO;
import org.sahurdayathra.BookShelfLMS.business.custom.UserBO;
import org.sahurdayathra.BookShelfLMS.dto.BorrowDTO;
import org.sahurdayathra.BookShelfLMS.view.util.tblmodel.BorrowTM;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class IssuedReturnedBookManagementController implements Initializable {

    @FXML
    private AnchorPane issuedReturnedBookManagementAnchPane;
    @FXML
    private HBox homeHBox;
    @FXML
    private HBox viewMoreHBox;
    @FXML
    private HBox helpHBox;
    @FXML
    private JFXTextField borrowIDTF;
    @FXML
    private JFXTextField admissionNoTF;
    @FXML
    private JFXTextField bookCode_num1_TF;
    @FXML
    private JFXTextField bookCode_num2_TF;
    @FXML
    private JFXTextField bookCode_txt_TF;
    @FXML
    private JFXTextField bookTitleTF;
    @FXML
    private JFXTextField userNameTF;
    @FXML
    private JFXDatePicker issuedDateDP;
    @FXML
    private JFXDatePicker dueDateDP;
    @FXML
    private JFXDatePicker returnedDateDP;
    @FXML
    private JFXTextField ibRegNo_num_TF;
    @FXML
    private JFXTextField userID_num_TF;
    @FXML
    private Pane btnPane;
    @FXML
    private JFXButton newBtn;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private Pane notReturnedBooksPane;
    @FXML
    private TextField notReturnedBooksPane_searchBoxTF;
    @FXML
    private Label notReturnedBooksPane_countLbl;
    @FXML
    private TableView<BorrowTM> notReturnedBooksPane_borrowDetailsTbl;
    @FXML
    private Pane returnedBooksPane;
    @FXML
    private TextField returnedBooksPane_searchBoxTF;
    @FXML
    private Label returnedBooksPane_countLbl;
    @FXML
    private TableView<BorrowTM> returnedBooksPane_borrowDetailsTbl;

    ObservableList<BorrowTM> notReturnedBooksBorrowTMs;
    ObservableList<BorrowTM> returnedBooksBorrowTMs;

    BorrowBO borrowBO = (BorrowBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BORROW);
    MemberBO memberBO = (MemberBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.MEMBER);
    BookBO bookBO = (BookBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOK);
    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.gc();

        notReturnedBooksPane_borrowDetailsTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        notReturnedBooksPane_borrowDetailsTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        notReturnedBooksPane_borrowDetailsTbl.getColumns().get(2).setStyle("-fx-alignment:center");
        notReturnedBooksPane_borrowDetailsTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("borrowID"));
        notReturnedBooksPane_borrowDetailsTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("libRegNO"));
        notReturnedBooksPane_borrowDetailsTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("bookCode"));

        returnedBooksPane_borrowDetailsTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        returnedBooksPane_borrowDetailsTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        returnedBooksPane_borrowDetailsTbl.getColumns().get(2).setStyle("-fx-alignment:center");
        returnedBooksPane_borrowDetailsTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("borrowID"));
        returnedBooksPane_borrowDetailsTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("libRegNO"));
        returnedBooksPane_borrowDetailsTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("bookCode"));

        setAccessToFeildsAndButtons();

        newBtn.fire();

    }

    @FXML
    private void homeHBox_onMouseClicked(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/mainDash.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) this.issuedReturnedBookManagementAnchPane.getScene().getWindow();
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
                stage.initOwner(issuedReturnedBookManagementAnchPane.getScene().getWindow());
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
                stage.initOwner(issuedReturnedBookManagementAnchPane.getScene().getWindow());
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

    private void setAccessToFeildsAndButtons() {

        newBtn.requestFocus();

        if (LogInController.loggedUser.getState().equals("superadmin")) {

            borrowIDTF.setEditable(false);
            borrowIDTF.setMouseTransparent(true);
            ibRegNo_num_TF.setEditable(true);
            ibRegNo_num_TF.setMouseTransparent(false);
            admissionNoTF.setEditable(false);
            admissionNoTF.setMouseTransparent(true);
            bookCode_txt_TF.setEditable(true);
            bookCode_txt_TF.setMouseTransparent(false);
            bookCode_num1_TF.setEditable(true);
            bookCode_num1_TF.setMouseTransparent(false);
            bookCode_num2_TF.setEditable(true);
            bookCode_num2_TF.setMouseTransparent(false);
            bookTitleTF.setEditable(false);
            bookTitleTF.setMouseTransparent(true);
            issuedDateDP.setEditable(true);
            issuedDateDP.setMouseTransparent(false);
            dueDateDP.setEditable(true);
            dueDateDP.setMouseTransparent(false);
            returnedDateDP.setEditable(true);
            returnedDateDP.setMouseTransparent(false);
            userID_num_TF.setEditable(true);
            userID_num_TF.setMouseTransparent(false);
            userNameTF.setEditable(false);
            userNameTF.setMouseTransparent(true);

        } else {

            borrowIDTF.setEditable(false);
            borrowIDTF.setMouseTransparent(true);
            ibRegNo_num_TF.setEditable(false);
            ibRegNo_num_TF.setMouseTransparent(true);
            admissionNoTF.setEditable(false);
            admissionNoTF.setMouseTransparent(true);
            bookCode_txt_TF.setEditable(false);
            bookCode_txt_TF.setMouseTransparent(true);
            bookCode_num1_TF.setEditable(false);
            bookCode_num1_TF.setMouseTransparent(true);
            bookCode_num2_TF.setEditable(false);
            bookCode_num2_TF.setMouseTransparent(true);
            bookTitleTF.setEditable(false);
            bookTitleTF.setMouseTransparent(true);
            issuedDateDP.setEditable(false);
            issuedDateDP.setMouseTransparent(true);
            dueDateDP.setEditable(false);
            dueDateDP.setMouseTransparent(true);
            returnedDateDP.setEditable(false);
            returnedDateDP.setMouseTransparent(true);
            userID_num_TF.setEditable(false);
            userID_num_TF.setMouseTransparent(true);
            userNameTF.setEditable(false);
            userNameTF.setMouseTransparent(true);

            Alert alert = new Alert(
                    Alert.AlertType.NONE,
                    "\n Only super admin of this system can update or delete borrow details. \n",
                    ButtonType.OK
            );
            alert.setTitle("Information");
            alert.show();

        }

    }

    private void loadAllNotReturnedBorrowDetails() {
        new Thread(() -> {
            try {
                ArrayList<BorrowDTO> borrowDTOs = borrowBO.getNotReturnedBorrows();
                loadNotReturnedBorrowTbl(borrowDTOs);
            } catch (Exception ex) {
                printException(ex);
            }
        }).start();
    }

    private void loadNotReturnedBorrowTbl(ArrayList<BorrowDTO> borrowDTOs) {
        Platform.runLater(() -> {
            notReturnedBooksPane_countLbl.setText(Integer.toString(borrowDTOs.size()));

            notReturnedBooksBorrowTMs = notReturnedBooksPane_borrowDetailsTbl.getItems();
            notReturnedBooksPane_borrowDetailsTbl.getItems().clear();

            notReturnedBooksBorrowTMs.removeAll(notReturnedBooksBorrowTMs);
            notReturnedBooksBorrowTMs.clear();

            for (BorrowDTO borrowDTO : borrowDTOs) {
                notReturnedBooksBorrowTMs.add(new BorrowTM(
                        borrowDTO.getBorrowID(),
                        borrowDTO.getLibRegNO(),
                        borrowDTO.getBookCode()
                ));
            }
        });
    }

    private void loadAllReturnedBorrowDetails() {
        new Thread(() -> {
            try {
                ArrayList<BorrowDTO> borrowDTOs = borrowBO.getReturnedBorrows();
                loadReturnedBorrowTbl(borrowDTOs);
            } catch (Exception ex) {
                printException(ex);
            }
        }).start();
    }

    private void loadReturnedBorrowTbl(ArrayList<BorrowDTO> borrowDTOs) {
        Platform.runLater(() -> {
            returnedBooksPane_countLbl.setText(Integer.toString(borrowDTOs.size()));

            returnedBooksBorrowTMs = returnedBooksPane_borrowDetailsTbl.getItems();
            returnedBooksPane_borrowDetailsTbl.getItems().clear();

            returnedBooksBorrowTMs.removeAll(returnedBooksBorrowTMs);
            returnedBooksBorrowTMs.clear();

            for (BorrowDTO borrowDTO : borrowDTOs) {
                returnedBooksBorrowTMs.add(new BorrowTM(
                        borrowDTO.getBorrowID(),
                        borrowDTO.getLibRegNO(),
                        borrowDTO.getBookCode()
                ));
            }
        });
    }

    @FXML
    private void bookCode_txt_TF_onAction(ActionEvent event) {
        bookCode_num1_TF.requestFocus();
    }

    @FXML
    private void bookCode_num1_TF_onAction(ActionEvent event) {
        bookCode_num2_TF.requestFocus();
    }

    @FXML
    private void bookCode_num2_TF_onAction(ActionEvent event) {
        issuedDateDP.requestFocus();
    }

    @FXML
    private void issuedDateDP_onAction(ActionEvent event) {
        dueDateDP.requestFocus();
    }

    @FXML
    private void dueDateDP_onAction(ActionEvent event) {
        returnedDateDP.requestFocus();
    }

    @FXML
    private void returnedDateDP_onAction(ActionEvent event) {
        userID_num_TF.requestFocus();
    }

    @FXML
    private void libRegNo_num_TF_onAction(ActionEvent event) {
        bookCode_txt_TF.requestFocus();
    }

    @FXML
    private void newBtn_onAction(ActionEvent event) {

        borrowIDTF.setText("");
        ibRegNo_num_TF.setText("");
        ibRegNo_num_TF.setPromptText("000000");
        admissionNoTF.setText("");
        bookCode_txt_TF.setText("");
        bookCode_txt_TF.setPromptText("ABC");
        bookCode_num1_TF.setText("");
        bookCode_num1_TF.setPromptText("000");
        bookCode_num2_TF.setText("");
        bookCode_num2_TF.setPromptText("000");
        bookTitleTF.setText("");
        issuedDateDP.setValue(null);
        issuedDateDP.setPromptText("Issued Date");
        dueDateDP.setValue(null);
        dueDateDP.setPromptText("Due Date");
        returnedDateDP.setValue(null);
        returnedDateDP.setPromptText("Returned Date");
        userID_num_TF.setText("");
        userID_num_TF.setPromptText("0000");
        userNameTF.setText("");

        notReturnedBooksPane_searchBoxTF.setText("");
        returnedBooksPane_searchBoxTF.setText("");

        loadAllNotReturnedBorrowDetails();
        loadAllReturnedBorrowDetails();

        updateBtn.setDisable(true);
        deleteBtn.setDisable(true);

    }

    @FXML
    private void updateBtn_onAction(ActionEvent event) {
        Alert alert = new Alert(
                Alert.AlertType.NONE,
                "\n Borrow detail update function currently not available. \n",
                ButtonType.OK
        );
        alert.setTitle("Information");
        alert.show();
    }

    @FXML
    private void deleteBtn_onAction(ActionEvent event) {
        Alert alert = new Alert(
                Alert.AlertType.NONE,
                "\n Borrow detail delete function currently not available. \n",
                ButtonType.OK
        );
        alert.setTitle("Information");
        alert.show();
    }

    @FXML
    private void notReturnedBooksPane_searchBoxTF_onMouseClicked(MouseEvent event) {
        notReturnedBooksPane_searchBoxTF.selectAll();
    }

    @FXML
    private void notReturnedBooksPane_searchBoxTF_onKeyReleased(KeyEvent event) {
        new Thread(() -> {
            try {

                if (notReturnedBooksPane_searchBoxTF.getText() == null) {
                    loadAllNotReturnedBorrowDetails();
                } else {
                    String searchText = notReturnedBooksPane_searchBoxTF.getText();

                    ArrayList<BorrowDTO> borrowDTOs = borrowBO.searchNotReturnedBorrows(searchText);

                    if (borrowDTOs.isEmpty()) {
                        Platform.runLater(() -> {
                            notReturnedBooksPane_searchBoxTF.setStyle("-fx-text-fill: #D91022");
                        });
                        loadAllNotReturnedBorrowDetails();
                    } else {
                        Platform.runLater(() -> {
                            notReturnedBooksPane_searchBoxTF.setStyle("-fx-text-fill: #000000");
                        });
                        loadNotReturnedBorrowTbl(borrowDTOs);
                    }
                }

            } catch (Exception ex) {
                printException(ex);
            }
        }).start();
    }

    @FXML
    private void notReturnedBooksPane_borrowDetailsTbl_onMouseClicked(MouseEvent event) {
        new Thread(() -> {
            try {

                BorrowTM borrowTM = notReturnedBooksPane_borrowDetailsTbl.getSelectionModel().getSelectedItem();

                if (borrowTM != null) {

                    BorrowDTO borrowDTO = borrowBO.getBorrow(borrowTM.getBorrowID());

                    Platform.runLater(() -> {
                        try {

                            String borrowID = borrowDTO.getBorrowID();
                            String libRegNo = borrowDTO.getLibRegNO();
                            String admissionNo = memberBO.getMember(libRegNo).getAdmissionNO();
                            String bookCode = borrowDTO.getBookCode();
                            String bookTitle = bookBO.getBook(bookCode).getBookTitle();
                            String issuedDate = borrowDTO.getIssuedDate();
                            String dueDate = borrowDTO.getDueDate();
                            String returnedDate = "Not Returned Yet";
                            String userID = borrowDTO.getUserID();
                            String userName = userBO.getUser(userID).getName();

                            borrowIDTF.setText(borrowID);
                            ibRegNo_num_TF.setText(libRegNo.substring(4, 10));
                            admissionNoTF.setText(admissionNo);
                            bookCode_txt_TF.setText(bookCode.substring(0, 3));
                            bookCode_num1_TF.setText(bookCode.substring(4, 7));
                            bookCode_num2_TF.setText(bookCode.substring(8, 11));
                            bookTitleTF.setText(bookTitle);
                            issuedDateDP.setValue(LocalDate.parse(issuedDate));
                            dueDateDP.setValue(LocalDate.parse(dueDate));
                            returnedDateDP.setValue(null);
                            returnedDateDP.setPromptText(returnedDate);
                            userID_num_TF.setText(userID.substring(4, 8));
                            userNameTF.setText(userName);

                            if (LogInController.loggedUser.getState().equals("superadmin")) {
                                updateBtn.setDisable(false);
                                deleteBtn.setDisable(false);
                            } else {
                                updateBtn.setDisable(true);
                                deleteBtn.setDisable(true);
                            }

                        } catch (Exception ex) {
                            printException(ex);
                        }
                    });

                }

            } catch (Exception ex) {
                printException(ex);
            }
        }).start();
    }

    @FXML
    private void returnedBooksPane_searchBoxTF_onMouseClicked(MouseEvent event) {
        returnedBooksPane_searchBoxTF.selectAll();
    }

    @FXML
    private void returnedBooksPane_searchBoxTF_onKeyReleased(KeyEvent event) {
        new Thread(() -> {
            try {

                if (returnedBooksPane_searchBoxTF.getText() == null) {
                    loadAllReturnedBorrowDetails();
                } else {
                    String searchText = returnedBooksPane_searchBoxTF.getText();

                    ArrayList<BorrowDTO> borrowDTOs = borrowBO.searchReturnedBorrows(searchText);

                    if (borrowDTOs.isEmpty()) {
                        Platform.runLater(() -> {
                            returnedBooksPane_searchBoxTF.setStyle("-fx-text-fill: #D91022");
                        });
                        loadAllReturnedBorrowDetails();
                    } else {
                        Platform.runLater(() -> {
                            returnedBooksPane_searchBoxTF.setStyle("-fx-text-fill: #000000");
                        });
                        loadReturnedBorrowTbl(borrowDTOs);
                    }
                }

            } catch (Exception ex) {
                printException(ex);
            }
        }).start();
    }

    @FXML
    private void returnedBooksPane_borrowDetailsTbl_onMouseClicked(MouseEvent event) {
        new Thread(() -> {
            try {

                BorrowTM borrowTM = returnedBooksPane_borrowDetailsTbl.getSelectionModel().getSelectedItem();

                if (borrowTM != null) {

                    BorrowDTO borrowDTO = borrowBO.getBorrow(borrowTM.getBorrowID());

                    Platform.runLater(() -> {
                        try {

                            String borrowID = borrowDTO.getBorrowID();
                            String libRegNo = borrowDTO.getLibRegNO();
                            String admissionNo = memberBO.getMember(libRegNo).getAdmissionNO();
                            String bookCode = borrowDTO.getBookCode();
                            String bookTitle = bookBO.getBook(bookCode).getBookTitle();
                            String issuedDate = borrowDTO.getIssuedDate();
                            String dueDate = borrowDTO.getDueDate();
                            String returnedDate = borrowDTO.getReturnedDate();
                            String userID = borrowDTO.getUserID();
                            String userName = userBO.getUser(userID).getName();

                            borrowIDTF.setText(borrowID);
                            ibRegNo_num_TF.setText(libRegNo.substring(4, 10));
                            admissionNoTF.setText(admissionNo);
                            bookCode_txt_TF.setText(bookCode.substring(0, 3));
                            bookCode_num1_TF.setText(bookCode.substring(4, 7));
                            bookCode_num2_TF.setText(bookCode.substring(8, 11));
                            bookTitleTF.setText(bookTitle);
                            issuedDateDP.setValue(LocalDate.parse(issuedDate));
                            dueDateDP.setValue(LocalDate.parse(dueDate));
                            returnedDateDP.setValue(LocalDate.parse(returnedDate));
                            userID_num_TF.setText(userID.substring(4, 8));
                            userNameTF.setText(userName);

                            if (LogInController.loggedUser.getState().equals("superadmin")) {
                                updateBtn.setDisable(false);
                                deleteBtn.setDisable(false);
                            } else {
                                updateBtn.setDisable(true);
                                deleteBtn.setDisable(true);
                            }

                        } catch (Exception ex) {
                            printException(ex);
                        }
                    });

                }

            } catch (Exception ex) {
                printException(ex);
            }
        }).start();
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
