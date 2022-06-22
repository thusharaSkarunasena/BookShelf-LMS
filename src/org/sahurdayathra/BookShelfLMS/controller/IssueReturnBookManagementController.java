package org.sahurdayathra.BookShelfLMS.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import org.sahurdayathra.BookShelfLMS.business.custom.BookCategoryBO;
import org.sahurdayathra.BookShelfLMS.business.custom.BorrowBO;
import org.sahurdayathra.BookShelfLMS.business.custom.MemberBO;
import org.sahurdayathra.BookShelfLMS.dto.BookCategoryDTO;
import org.sahurdayathra.BookShelfLMS.dto.BookDTO;
import org.sahurdayathra.BookShelfLMS.dto.BorrowDTO;
import org.sahurdayathra.BookShelfLMS.dto.MemberDTO;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class IssueReturnBookManagementController implements Initializable {

    @FXML
    private AnchorPane issueReturnBookManagementAnchPane;
    @FXML
    private HBox homeHBox;
    @FXML
    private HBox viewMoreHBox;
    @FXML
    private HBox helpHBox;
    @FXML
    private ImageView sahurdaYathraTextImg;
    @FXML
    private Pane issueBooksPane;
    @FXML
    private JFXButton issueBooksPane_issueBtn;
    @FXML
    private JFXButton issueBooksPane_clearBtn;
    @FXML
    private JFXTextField issueBooksPane_borrowIDTF;
    @FXML
    private JFXTextField returnBooksPane_borrowIDTF;
    @FXML
    private JFXTextField issueBooksPane_libRegNo_num_TF;
    @FXML
    private JFXTextField issueBooksPane_admissionNoTF;
    @FXML
    private JFXTextField issueBooksPane_memberNameTF;
    @FXML
    private JFXTextField issueBooksPane_bookTitleTF;
    @FXML
    private JFXTextField issueBooksPane_authorNameTF;
    @FXML
    private JFXTextField issueBooksPane_bookCode_num1_TF;
    @FXML
    private JFXTextField issueBooksPane_bookCode_num2_TF;
    @FXML
    private JFXTextField issueBooksPane_bookCode_txt_TF;
    @FXML
    private Label issueBooksPane_issueDateLbl;
    @FXML
    private Label issueBooksPane_dueDateLbl;
    @FXML
    private Pane returnBooksPane;
    @FXML
    private JFXButton returnBooksPane_returnBtn;
    @FXML
    private JFXButton returnBooksPane_clearBtn;
    @FXML
    private JFXTextField returnBooksPane_libRegNo_num_TF;
    @FXML
    private JFXTextField returnBooksPane_admissionNoTF;
    @FXML
    private JFXTextField returnBooksPane_memberNameTF;
    @FXML
    private JFXTextField returnBooksPane_bookCode_num1_TF;
    @FXML
    private JFXTextField returnBooksPane_bookTitleTF;
    @FXML
    private JFXTextField returnBooksPane_authorNameTF;
    @FXML
    private JFXTextField returnBooksPane_bookCode_num2_TF;
    @FXML
    private JFXTextField returnBooksPane_bookCode_txt_TF;
    @FXML
    private Label returnBooksPane_issuedDateLbl;
    @FXML
    private Label returnBooksPane_dueDateLbl;
    @FXML
    private Label returnBooksPane_returnedDateLbl;

    BorrowBO borrowBO = (BorrowBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BORROW);
    MemberBO memberBO = (MemberBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.MEMBER);
    BookBO bookBO = (BookBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOK);
    BookCategoryBO bookCategoryBO = (BookCategoryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOKCATEGORY);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.gc();

        issueBooksPane_clearBtn.fire();
        returnBooksPane_clearBtn.fire();

    }

    @FXML
    private void homeHBox_onMouseClicked(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/mainDash.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) this.issueReturnBookManagementAnchPane.getScene().getWindow();
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
                stage.initOwner(issueReturnBookManagementAnchPane.getScene().getWindow());
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
                stage.initOwner(issueReturnBookManagementAnchPane.getScene().getWindow());
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

    private void generateNextBorrowID() {
        new Thread(() -> {
            try {
                String borrowID = borrowBO.getNextBorrowID();
                Platform.runLater(() -> {
                    issueBooksPane_borrowIDTF.setText(borrowID);
                });
            } catch (Exception ex) {
                printException(ex);
            }
        }).start();
    }

    @FXML
    private void issueBooksPane_clearBtn_onAction(ActionEvent event) {

        issueBooksPane_libRegNo_num_TF.setText("");
        issueBooksPane_libRegNo_num_TF.setPromptText("000000");
        issueBooksPane_admissionNoTF.setText("");
        issueBooksPane_memberNameTF.setText("");

        issueBooksPane_bookCode_txt_TF.setText("");
        issueBooksPane_bookCode_txt_TF.setPromptText("ABC");
        issueBooksPane_bookCode_num1_TF.setText("");
        issueBooksPane_bookCode_num1_TF.setPromptText("000");
        issueBooksPane_bookCode_num2_TF.setText("");
        issueBooksPane_bookCode_num2_TF.setPromptText("000");
        issueBooksPane_bookTitleTF.setText("");
        issueBooksPane_authorNameTF.setText("");

        issueBooksPane_issueDateLbl.setText("__.__.__");
        issueBooksPane_dueDateLbl.setText("__.__.__");

        issueBooksPane_bookCode_num1_TF.setDisable(true);
        issueBooksPane_bookCode_num2_TF.setDisable(true);
        issueBooksPane_issueBtn.setDisable(true);

        generateNextBorrowID();

    }

    @FXML
    private void issueBooksPane_libRegNo_num_TF_keyReleased(KeyEvent event) {
        String issueBooksPane_libRegNo_num = issueBooksPane_libRegNo_num_TF.getText().trim();
        if (!issueBooksPane_libRegNo_num.isEmpty()) {
            if (issueBooksPane_libRegNo_num.length() > 5) {
                issueBooksPane_libRegNo_num_TF.fireEvent(new ActionEvent());
            }
        }
    }

    @FXML
    private void issueBooksPane_libRegNo_num_TF_onAction(ActionEvent event) {
        String issueBooksPane_libRegNo_num = issueBooksPane_libRegNo_num_TF.getText();
        boolean matches = issueBooksPane_libRegNo_num.matches("[0-9]{6}");
        if (matches) {
            boolean isConfirm = isConfirmLibRegNo(issueBooksPane_libRegNo_num);
            if (isConfirm) {
                boolean isTaken = isTakenMemberAlready(issueBooksPane_libRegNo_num);
                if (!isTaken) {
                    displayMemberDetails(issueBooksPane_libRegNo_num);
                    issueBooksPane_bookCode_num1_TF.setDisable(false);
                    issueBooksPane_bookCode_num1_TF.requestFocus();
                } else {
                    new Alert(
                            Alert.AlertType.ERROR,
                            "Sorry, This Member has Borrowed a Book Already.",
                            ButtonType.OK
                    ).show();
                    issueBooksPane_admissionNoTF.setText("");
                    issueBooksPane_memberNameTF.setText("");
                    issueBooksPane_libRegNo_num_TF.requestFocus();
                    issueBooksPane_libRegNo_num_TF.selectAll();
                }
            } else {
                new Alert(
                        Alert.AlertType.ERROR,
                        "Sorry, There isn't a Such Like a Member."
                        + "\n Please Enter an Existing Library Registration Number.",
                        ButtonType.OK
                ).show();
                issueBooksPane_admissionNoTF.setText("");
                issueBooksPane_memberNameTF.setText("");
                issueBooksPane_libRegNo_num_TF.requestFocus();
                issueBooksPane_libRegNo_num_TF.selectAll();
            }
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Library Registration Number.",
                    ButtonType.OK
            ).show();
            issueBooksPane_admissionNoTF.setText("");
            issueBooksPane_memberNameTF.setText("");
            issueBooksPane_libRegNo_num_TF.requestFocus();
            issueBooksPane_libRegNo_num_TF.selectAll();
        }
    }

    private boolean isConfirmLibRegNo(String libRegNo) {

        boolean isConfirm = false;

        try {

            MemberDTO memberDTO = null;

            ArrayList<MemberDTO> memberDTOs = memberBO.searchMember("MEM." + libRegNo);

            for (MemberDTO mdto : memberDTOs) {
                if (mdto.getLibRegNO().equals("MEM." + libRegNo)) {
                    memberDTO = mdto;
                    break;
                }
            }

            if (memberDTO != null) {
                isConfirm = true;
            }

        } catch (Exception ex) {
            printException(ex);
        }

        return isConfirm;

    }

    private boolean isTakenMemberAlready(String libRegNo) {

        boolean isTaken = false;

        try {

            ArrayList<BookDTO> bookDTOs = bookBO.getAllBooks();

            for (BookDTO bookDTO : bookDTOs) {
                if (bookDTO.getStatus().equals("MEM." + libRegNo)) {
                    isTaken = true;
                    break;
                }
            }

        } catch (Exception ex) {
            printException(ex);
        }

        return isTaken;

    }

    private void displayMemberDetails(String libRegNo) {
        try {
            MemberDTO memberDTO = memberBO.getMember("MEM." + libRegNo);
            issueBooksPane_admissionNoTF.setText(memberDTO.getAdmissionNO());
            issueBooksPane_memberNameTF.setText(memberDTO.getName());
        } catch (Exception ex) {
            printException(ex);
        }
    }

    @FXML
    private void issueBooksPane_bookCode_num1_TF_onKeyReleased(KeyEvent event) {
        String issueBooksPane_bookCode_num1 = issueBooksPane_bookCode_num1_TF.getText().trim();
        if (!issueBooksPane_bookCode_num1.isEmpty()) {
            if (issueBooksPane_bookCode_num1.length() > 2) {
                issueBooksPane_bookCode_num1_TF.fireEvent(new ActionEvent());
            }
        }
    }

    @FXML
    private void issueBooksPane_bookCode_num1_TF_onAction(ActionEvent event) {
        String issueBooksPane_bookCode_num1 = issueBooksPane_bookCode_num1_TF.getText();
        boolean matches = issueBooksPane_bookCode_num1.matches("[0-9]{3}");
        if (matches) {
            boolean isConfirm = isConfirmBookCategoryCode(issueBooksPane_bookCode_num1);
            if (isConfirm) {
                issueBooksPane_bookCode_num2_TF.setDisable(false);
                issueBooksPane_bookCode_num2_TF.requestFocus();
            } else {
                new Alert(
                        Alert.AlertType.ERROR,
                        "Sorry, There isn't a Such Like a Book Category."
                        + "\n Please Enter an Existing Book Category Code.",
                        ButtonType.OK
                ).show();
                issueBooksPane_bookCode_txt_TF.setText("");
                issueBooksPane_bookCode_txt_TF.setPromptText("ABC");
                issueBooksPane_bookCode_num2_TF.setText("");
                issueBooksPane_bookCode_num2_TF.setPromptText("000");
                issueBooksPane_bookTitleTF.setText("");
                issueBooksPane_authorNameTF.setText("");
                issueBooksPane_issueDateLbl.setText("__.__.__");
                issueBooksPane_dueDateLbl.setText("__.__.__");
                issueBooksPane_bookCode_num1_TF.requestFocus();
                issueBooksPane_bookCode_num1_TF.selectAll();
            }
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Book Code's Book Category Number.",
                    ButtonType.OK
            ).show();
            issueBooksPane_bookCode_txt_TF.setText("");
            issueBooksPane_bookCode_txt_TF.setPromptText("ABC");
            issueBooksPane_bookCode_num2_TF.setText("");
            issueBooksPane_bookCode_num2_TF.setPromptText("000");
            issueBooksPane_bookTitleTF.setText("");
            issueBooksPane_authorNameTF.setText("");
            issueBooksPane_issueDateLbl.setText("__.__.__");
            issueBooksPane_dueDateLbl.setText("__.__.__");
            issueBooksPane_bookCode_num1_TF.requestFocus();
            issueBooksPane_bookCode_num1_TF.selectAll();
        }
    }

    private boolean isConfirmBookCategoryCode(String bookCategoryCodeNumPart) {

        boolean isConfirm = false;

        try {

            BookCategoryDTO bookCategoryDTO = null;

            ArrayList<BookCategoryDTO> bookCategoryDTOs = bookCategoryBO.searchBookCategory(bookCategoryCodeNumPart);

            for (BookCategoryDTO bookCatDTO : bookCategoryDTOs) {
                if (bookCatDTO.getBookCategoryCode().substring(4, 7).equals(bookCategoryCodeNumPart)) {
                    bookCategoryDTO = bookCatDTO;
                    break;
                }
            }

            if (bookCategoryDTO != null) {
                displayBookCatCodeTextPart(bookCategoryDTO.getBookCategoryCode().substring(0, 3));
                isConfirm = true;
            }

        } catch (Exception ex) {
            printException(ex);
        }

        return isConfirm;

    }

    private void displayBookCatCodeTextPart(String bookCategoryCodeTextPart) {
        issueBooksPane_bookCode_txt_TF.setText(bookCategoryCodeTextPart);
    }

    @FXML
    private void issueBooksPane_bookCode_num2_TF_onKeyReleased(KeyEvent event) {
        String issueBooksPane_bookCode_num2 = issueBooksPane_bookCode_num2_TF.getText().trim();
        if (!issueBooksPane_bookCode_num2.isEmpty()) {
            if (issueBooksPane_bookCode_num2.length() > 2) {
                issueBooksPane_bookCode_num2_TF.fireEvent(new ActionEvent());
            }
        }
    }

    @FXML
    private void issueBooksPane_bookCode_num2_TF_onAction(ActionEvent event) {
        String issueBooksPane_bookCode_num2 = issueBooksPane_bookCode_num2_TF.getText();
        boolean matches = issueBooksPane_bookCode_num2.matches("[0-9]{3}");
        String bookCode = issueBooksPane_bookCode_txt_TF.getText()
                + "."
                + issueBooksPane_bookCode_num1_TF.getText()
                + "."
                + issueBooksPane_bookCode_num2;
        if (matches) {
            boolean isConfirm = isConfirmBookCode(bookCode);
            if (isConfirm) {
                boolean isTaken = isTakenBookAlready(bookCode);
                if (!isTaken) {
                    issueBooksPane_issueBtn.setDisable(false);
                    issueBooksPane_issueBtn.requestFocus();
                } else {
                    new Alert(
                            Alert.AlertType.ERROR,
                            "Sorry, This Book has been Already Issued.",
                            ButtonType.OK
                    ).show();
                    issueBooksPane_bookTitleTF.setText("");
                    issueBooksPane_authorNameTF.setText("");
                    issueBooksPane_issueDateLbl.setText("__.__.__");
                    issueBooksPane_dueDateLbl.setText("__.__.__");
                    issueBooksPane_issueBtn.setDisable(true);
                    issueBooksPane_bookCode_num2_TF.requestFocus();
                    issueBooksPane_bookCode_num2_TF.selectAll();
                }
            } else {
                new Alert(
                        Alert.AlertType.ERROR,
                        "Sorry, There isn't a Such Like a Book."
                        + "\n Please Enter an Existing Book Code.",
                        ButtonType.OK
                ).show();
                issueBooksPane_bookTitleTF.setText("");
                issueBooksPane_authorNameTF.setText("");
                issueBooksPane_issueDateLbl.setText("__.__.__");
                issueBooksPane_dueDateLbl.setText("__.__.__");
                issueBooksPane_issueBtn.setDisable(true);
                issueBooksPane_bookCode_num2_TF.requestFocus();
                issueBooksPane_bookCode_num2_TF.selectAll();
            }
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Book Code.",
                    ButtonType.OK
            ).show();
            issueBooksPane_bookTitleTF.setText("");
            issueBooksPane_authorNameTF.setText("");
            issueBooksPane_issueDateLbl.setText("__.__.__");
            issueBooksPane_dueDateLbl.setText("__.__.__");
            issueBooksPane_issueBtn.setDisable(true);
            issueBooksPane_bookCode_num2_TF.requestFocus();
            issueBooksPane_bookCode_num2_TF.selectAll();
        }
    }

    private boolean isConfirmBookCode(String bookCode) {

        boolean isConfirm = false;

        try {

            ArrayList<BookDTO> bookDTOs = bookBO.searchBook(bookCode);

            for (BookDTO bkDTO : bookDTOs) {
                if (bkDTO.getBookCode().equals(bookCode)) {
                    isConfirm = true;
                    break;
                }
            }

        } catch (Exception ex) {
            printException(ex);
        }

        return isConfirm;

    }

    private boolean isTakenBookAlready(String bookCode) {

        boolean isTaken = false;

        try {

            ArrayList<BookDTO> bookDTOs = bookBO.getAllBooks();

            BookDTO bookDTO = null;

            for (BookDTO bkDTO : bookDTOs) {
                if (bkDTO.getBookCode().equals(bookCode)) {
                    bookDTO = bkDTO;
                    break;
                }
            }

            if (bookDTO != null) {
                if (bookDTO.getStatus().equals("MEM.000000")) {
                    isTaken = false;
                } else {
                    isTaken = true;
                }
            }

            if (!isTaken) {
                displayBookDetails(
                        issueBooksPane_bookCode_txt_TF.getText()
                        + "."
                        + issueBooksPane_bookCode_num1_TF.getText()
                        + "."
                        + issueBooksPane_bookCode_num2_TF.getText()
                );
            }

        } catch (Exception ex) {
            printException(ex);
        }

        return isTaken;

    }

    private void displayBookDetails(String bookCode) {
        try {
            BookDTO bookDTO = bookBO.getBook(bookCode);
            issueBooksPane_bookTitleTF.setText(bookDTO.getBookTitle());
            issueBooksPane_authorNameTF.setText(bookDTO.getAuthorName());
            displayIssueBooksPaneDateDetails();
        } catch (Exception ex) {
            printException(ex);
        }
    }

    private void displayIssueBooksPaneDateDetails() {
        issueBooksPane_issueDateLbl.setText(LocalDate.now().toString());
        issueBooksPane_dueDateLbl.setText(LocalDate.now().plusWeeks(1).toString());
    }

    @FXML
    private void issueBooksPane_issueBtn_onAction(ActionEvent event) {

        Platform.runLater(() -> {
            try {

                String issueBooksPane_libRegNo_num = issueBooksPane_libRegNo_num_TF.getText();
                String issueBooksPane_bookCode_num1 = issueBooksPane_bookCode_num1_TF.getText();
                String issueBooksPane_bookCode_num2 = issueBooksPane_bookCode_num2_TF.getText();

                boolean matches1 = issueBooksPane_libRegNo_num.matches("[0-9]{6}");
                boolean matches2 = issueBooksPane_bookCode_num1.matches("[0-9]{3}");
                boolean matches3 = issueBooksPane_bookCode_num2.matches("[0-9]{3}");

                String bookCode = issueBooksPane_bookCode_txt_TF.getText()
                        + "."
                        + issueBooksPane_bookCode_num1_TF.getText()
                        + "."
                        + issueBooksPane_bookCode_num2_TF.getText();

                boolean isConfirmMember = isConfirmLibRegNo(issueBooksPane_libRegNo_num);
                boolean isTakenMember = isTakenMemberAlready(issueBooksPane_libRegNo_num);
                boolean isConfirmBookCatCode = isConfirmBookCategoryCode(issueBooksPane_bookCode_num1);
                boolean isConfirmBookCode = isConfirmBookCode(bookCode);
                boolean isTaken = isTakenBookAlready(bookCode);

                boolean result = false;

                if (matches1) {
                    if (isConfirmMember) {
                        if (!isTakenMember) {
                            if (matches2) {
                                if (isConfirmBookCatCode) {
                                    if (matches3) {
                                        if (isConfirmBookCode) {
                                            if (!isTaken) {
                                                BorrowDTO borrowDTO = new BorrowDTO(
                                                        issueBooksPane_borrowIDTF.getText(),
                                                        "MEM." + issueBooksPane_libRegNo_num_TF.getText(),
                                                        issueBooksPane_bookCode_txt_TF.getText()
                                                        + "."
                                                        + issueBooksPane_bookCode_num1_TF.getText()
                                                        + "."
                                                        + issueBooksPane_bookCode_num2_TF.getText(),
                                                        LogInController.loggedUser.getUserID(),
                                                        issueBooksPane_issueDateLbl.getText(),
                                                        issueBooksPane_dueDateLbl.getText(),
                                                        "1970-01-01"
                                                );
                                                result = borrowBO.saveBorrowAndUpdateBook(borrowDTO);
                                            } else {
                                                new Alert(
                                                        Alert.AlertType.ERROR,
                                                        "Sorry, This Book has been Already Issued.",
                                                        ButtonType.OK
                                                ).show();
                                                issueBooksPane_bookTitleTF.setText("");
                                                issueBooksPane_authorNameTF.setText("");
                                                issueBooksPane_issueDateLbl.setText("__.__.__");
                                                issueBooksPane_dueDateLbl.setText("__.__.__");
                                                issueBooksPane_issueBtn.setDisable(true);
                                                issueBooksPane_bookCode_num2_TF.requestFocus();
                                                issueBooksPane_bookCode_num2_TF.selectAll();
                                            }
                                        } else {
                                            new Alert(
                                                    Alert.AlertType.ERROR,
                                                    "Sorry, There isn't a Such Like a Book."
                                                    + "\n Please Enter an Existing Book Code.",
                                                    ButtonType.OK
                                            ).show();
                                            issueBooksPane_bookTitleTF.setText("");
                                            issueBooksPane_authorNameTF.setText("");
                                            issueBooksPane_issueDateLbl.setText("__.__.__");
                                            issueBooksPane_dueDateLbl.setText("__.__.__");
                                            issueBooksPane_issueBtn.setDisable(true);
                                            issueBooksPane_bookCode_num2_TF.requestFocus();
                                            issueBooksPane_bookCode_num2_TF.selectAll();
                                        }
                                    } else {
                                        new Alert(
                                                Alert.AlertType.ERROR,
                                                "Oops... Something Wrong in Book Code.",
                                                ButtonType.OK
                                        ).show();
                                        issueBooksPane_bookTitleTF.setText("");
                                        issueBooksPane_authorNameTF.setText("");
                                        issueBooksPane_issueDateLbl.setText("__.__.__");
                                        issueBooksPane_dueDateLbl.setText("__.__.__");
                                        issueBooksPane_issueBtn.setDisable(true);
                                        issueBooksPane_bookCode_num2_TF.requestFocus();
                                        issueBooksPane_bookCode_num2_TF.selectAll();
                                    }
                                } else {
                                    new Alert(
                                            Alert.AlertType.ERROR,
                                            "Sorry, There isn't a Such Like a Book Category."
                                            + "\n Please Enter an Existing Book Category Code.",
                                            ButtonType.OK
                                    ).show();
                                    issueBooksPane_bookCode_txt_TF.setText("");
                                    issueBooksPane_bookCode_txt_TF.setPromptText("ABC");
                                    issueBooksPane_bookCode_num2_TF.setText("");
                                    issueBooksPane_bookCode_num2_TF.setPromptText("000");
                                    issueBooksPane_bookTitleTF.setText("");
                                    issueBooksPane_authorNameTF.setText("");
                                    issueBooksPane_issueDateLbl.setText("__.__.__");
                                    issueBooksPane_dueDateLbl.setText("__.__.__");
                                    issueBooksPane_bookCode_num1_TF.requestFocus();
                                    issueBooksPane_bookCode_num1_TF.selectAll();
                                }
                            } else {
                                new Alert(
                                        Alert.AlertType.ERROR,
                                        "Oops... Something Wrong in Book Code's Book Category Number.",
                                        ButtonType.OK
                                ).show();
                                issueBooksPane_bookCode_txt_TF.setText("");
                                issueBooksPane_bookCode_txt_TF.setPromptText("ABC");
                                issueBooksPane_bookCode_num2_TF.setText("");
                                issueBooksPane_bookCode_num2_TF.setPromptText("000");
                                issueBooksPane_bookTitleTF.setText("");
                                issueBooksPane_authorNameTF.setText("");
                                issueBooksPane_issueDateLbl.setText("__.__.__");
                                issueBooksPane_dueDateLbl.setText("__.__.__");
                                issueBooksPane_bookCode_num1_TF.requestFocus();
                                issueBooksPane_bookCode_num1_TF.selectAll();
                            }
                        } else {
                            new Alert(
                                    Alert.AlertType.ERROR,
                                    "Sorry, This Member has Borrowed a Book Already.",
                                    ButtonType.OK
                            ).show();
                            issueBooksPane_admissionNoTF.setText("");
                            issueBooksPane_memberNameTF.setText("");
                            issueBooksPane_libRegNo_num_TF.requestFocus();
                            issueBooksPane_libRegNo_num_TF.selectAll();
                        }
                    } else {
                        new Alert(
                                Alert.AlertType.ERROR,
                                "Sorry, There isn't a Such Like a Member."
                                + "\n Please Enter an Existing Library Registration Number.",
                                ButtonType.OK
                        ).show();
                        issueBooksPane_admissionNoTF.setText("");
                        issueBooksPane_memberNameTF.setText("");
                        issueBooksPane_libRegNo_num_TF.requestFocus();
                        issueBooksPane_libRegNo_num_TF.selectAll();
                    }
                } else {
                    new Alert(
                            Alert.AlertType.ERROR,
                            "Oops... Something Wrong in Library Registration Number.",
                            ButtonType.OK
                    ).show();
                    issueBooksPane_admissionNoTF.setText("");
                    issueBooksPane_memberNameTF.setText("");
                    issueBooksPane_libRegNo_num_TF.requestFocus();
                    issueBooksPane_libRegNo_num_TF.selectAll();
                }

                if (result) {
                    new Alert(
                            Alert.AlertType.INFORMATION,
                            "Book has been Issued Successfully.",
                            ButtonType.OK
                    ).show();
                    issueBooksPane_clearBtn.fire();
                } else {
                    new Alert(
                            Alert.AlertType.ERROR,
                            "Failed to Issue the Book.",
                            ButtonType.OK
                    ).show();
                }

            } catch (Exception ex) {
                printException(ex);
            }
        });

    }

    @FXML
    private void returnBooksPane_clearBtn_onAction(ActionEvent event) {

        returnBooksPane_libRegNo_num_TF.setText("");
        returnBooksPane_libRegNo_num_TF.setPromptText("000000");
        returnBooksPane_borrowIDTF.setText("");
        returnBooksPane_borrowIDTF.setPromptText("0000000000");
        returnBooksPane_admissionNoTF.setText("");
        returnBooksPane_memberNameTF.setText("");

        returnBooksPane_bookCode_txt_TF.setText("");
        returnBooksPane_bookCode_txt_TF.setPromptText("ABC");
        returnBooksPane_bookCode_num1_TF.setText("");
        returnBooksPane_bookCode_num1_TF.setPromptText("000");
        returnBooksPane_bookCode_num2_TF.setText("");
        returnBooksPane_bookCode_num2_TF.setPromptText("000");
        returnBooksPane_bookTitleTF.setText("");
        returnBooksPane_authorNameTF.setText("");

        returnBooksPane_issuedDateLbl.setText("__.__.__");
        returnBooksPane_dueDateLbl.setText("__.__.__");
        returnBooksPane_returnedDateLbl.setText("__.__.__");

        returnBooksPane_returnBtn.setDisable(true);

    }

    @FXML
    private void returnBooksPane_libRegNo_num_TF_keyReleased(KeyEvent event) {
        String returnBooksPane_libRegNo_num = returnBooksPane_libRegNo_num_TF.getText().trim();
        if (!returnBooksPane_libRegNo_num.isEmpty()) {
            if (returnBooksPane_libRegNo_num.length() > 5) {
                returnBooksPane_libRegNo_num_TF.fireEvent(new ActionEvent());
            }
        }
    }

    @FXML
    private void returnBooksPane_libRegNo_num_TF_onAction(ActionEvent event) {
        String returnBooksPane_libRegNo_num = returnBooksPane_libRegNo_num_TF.getText();
        boolean matches = returnBooksPane_libRegNo_num.matches("[0-9]{6}");
        if (matches) {
            boolean isConfirm = isConfirmLibRegNo(returnBooksPane_libRegNo_num);
            if (isConfirm) {
                boolean isTaken = isTakenMemberAlready(returnBooksPane_libRegNo_num);
                if (isTaken) {

                    BorrowDTO borrowDTO = null;

                    try {
                        ArrayList<BorrowDTO> borrowDTOs = borrowBO.getNotReturnedBorrows();
                        for (BorrowDTO bdto : borrowDTOs) {
                            if (bdto.getLibRegNO().equals("MEM." + returnBooksPane_libRegNo_num)) {
                                borrowDTO = bdto;
                                break;
                            }
                        }
                    } catch (Exception ex) {
                        printException(ex);
                    }
                    if (borrowDTO != null) {
                        displayReturnBookPaneDetails(returnBooksPane_libRegNo_num);
                        returnBooksPane_returnBtn.setDisable(false);
                        returnBooksPane_returnBtn.requestFocus();
                    }
                } else {
                    new Alert(
                            Alert.AlertType.ERROR,
                            "Sorry, This Member has not Borrowed a Book.",
                            ButtonType.OK
                    ).show();
                    returnBooksPane_clearBtn.fire();
                    returnBooksPane_libRegNo_num_TF.requestFocus();
                    returnBooksPane_libRegNo_num_TF.selectAll();
                }
            } else {
                new Alert(
                        Alert.AlertType.ERROR,
                        "Sorry, There isn't a Such Like a Member."
                        + "\n Please Enter an Existing Library Registration Number.",
                        ButtonType.OK
                ).show();
                returnBooksPane_clearBtn.fire();
                returnBooksPane_libRegNo_num_TF.requestFocus();
                returnBooksPane_libRegNo_num_TF.selectAll();
            }
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Library Registration Number.",
                    ButtonType.OK
            ).show();
            returnBooksPane_clearBtn.fire();
            returnBooksPane_libRegNo_num_TF.requestFocus();
            returnBooksPane_libRegNo_num_TF.selectAll();
        }
    }

    private void displayReturnBookPaneDetails(String libRegNoNumPart) {
        try {

            BorrowDTO borrowDTO = null;

            ArrayList<BorrowDTO> borrowDTOs = borrowBO.getNotReturnedBorrows();
            for (BorrowDTO bdto : borrowDTOs) {
                if (bdto.getLibRegNO().equals("MEM." + libRegNoNumPart)) {
                    borrowDTO = bdto;
                    break;
                }
            }

            String borrowID = borrowDTO.getBorrowID();

            MemberDTO memberDTO = memberBO.getMember(borrowDTO.getLibRegNO());

            String admissionNo = memberDTO.getAdmissionNO();
            String memberName = memberDTO.getName();

            BookDTO bookDTO = bookBO.getBook(borrowDTO.getBookCode());

            String bookCode = bookDTO.getBookCode();
            String bookName = bookDTO.getBookTitle();
            String authorName = bookDTO.getAuthorName();

            String issuedDate = borrowDTO.getIssuedDate();
            String dueDate = borrowDTO.getDueDate();
            String returnedDate = LocalDate.now().toString();

            returnBooksPane_borrowIDTF.setText(borrowID);
            returnBooksPane_admissionNoTF.setText(admissionNo);
            returnBooksPane_memberNameTF.setText(memberName);
            returnBooksPane_bookCode_txt_TF.setText(bookCode.substring(0, 3));
            returnBooksPane_bookCode_num1_TF.setText(bookCode.substring(4, 7));
            returnBooksPane_bookCode_num2_TF.setText(bookCode.substring(8, 11));
            returnBooksPane_bookTitleTF.setText(bookName);
            returnBooksPane_authorNameTF.setText(authorName);
            returnBooksPane_issuedDateLbl.setText(issuedDate);
            returnBooksPane_dueDateLbl.setText(dueDate);
            returnBooksPane_returnedDateLbl.setText(returnedDate);

        } catch (Exception ex) {

        }
    }

    @FXML
    private void returnBooksPane_returnBtn_onAction(ActionEvent event) {

        Platform.runLater(() -> {
            try {

                String returnBooksPane_libRegNo_num = returnBooksPane_libRegNo_num_TF.getText();
                boolean matches = returnBooksPane_libRegNo_num.matches("[0-9]{6}");

                boolean isConfirm = isConfirmLibRegNo(returnBooksPane_libRegNo_num);
                boolean isTaken = isTakenMemberAlready(returnBooksPane_libRegNo_num);

                boolean result = false;

                if (matches) {
                    if (isConfirm) {
                        if (isTaken) {
                            BorrowDTO borrowDTO = null;

                            ArrayList<BorrowDTO> borrowDTOs = borrowBO.getNotReturnedBorrows();
                            for (BorrowDTO bdto : borrowDTOs) {
                                if (bdto.getLibRegNO().equals("MEM." + returnBooksPane_libRegNo_num_TF.getText())) {
                                    borrowDTO = bdto;
                                    break;
                                }
                            }

                            BorrowDTO brwDTO = new BorrowDTO(
                                    borrowDTO.getBorrowID(),
                                    borrowDTO.getLibRegNO(),
                                    borrowDTO.getBookCode(),
                                    borrowDTO.getUserID(),
                                    borrowDTO.getIssuedDate(),
                                    borrowDTO.getDueDate(),
                                    returnBooksPane_returnedDateLbl.getText()
                            );
                            result = borrowBO.updateBorrowAndUpdateBook(brwDTO);
                        } else {
                            new Alert(
                                    Alert.AlertType.ERROR,
                                    "Sorry, This Member has not Borrowed a Book.",
                                    ButtonType.OK
                            ).show();
                            returnBooksPane_clearBtn.fire();
                            returnBooksPane_libRegNo_num_TF.requestFocus();
                            returnBooksPane_libRegNo_num_TF.selectAll();
                        }
                    } else {
                        new Alert(
                                Alert.AlertType.ERROR,
                                "Sorry, There isn't a Such Like a Member."
                                + "\n Please Enter an Existing Library Registration Number.",
                                ButtonType.OK
                        ).show();
                        returnBooksPane_clearBtn.fire();
                        returnBooksPane_libRegNo_num_TF.requestFocus();
                        returnBooksPane_libRegNo_num_TF.selectAll();
                    }
                } else {
                    new Alert(
                            Alert.AlertType.ERROR,
                            "Oops... Something Wrong in Library Registration Number.",
                            ButtonType.OK
                    ).show();
                    returnBooksPane_clearBtn.fire();
                    returnBooksPane_libRegNo_num_TF.requestFocus();
                    returnBooksPane_libRegNo_num_TF.selectAll();
                }

                if (result) {
                    new Alert(
                            Alert.AlertType.INFORMATION,
                            "Book has been Returned Successfully.",
                            ButtonType.OK
                    ).show();
                    returnBooksPane_clearBtn.fire();
                } else {
                    new Alert(
                            Alert.AlertType.ERROR,
                            "Failed to Return the Book.",
                            ButtonType.OK
                    ).show();
                }

            } catch (Exception ex) {
                printException(ex);
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
