package org.sahurdayathra.BookShelfLMS.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import org.sahurdayathra.BookShelfLMS.business.custom.BookBO;
import org.sahurdayathra.BookShelfLMS.business.custom.BookCategoryBO;
import org.sahurdayathra.BookShelfLMS.business.custom.BookPublisherBO;
import org.sahurdayathra.BookShelfLMS.business.custom.BorrowBO;
import org.sahurdayathra.BookShelfLMS.business.custom.MemberBO;
import org.sahurdayathra.BookShelfLMS.dto.BookCategoryDTO;
import org.sahurdayathra.BookShelfLMS.dto.BookDTO;
import org.sahurdayathra.BookShelfLMS.dto.BookPublisherDTO;
import org.sahurdayathra.BookShelfLMS.dto.BorrowDTO;
import org.sahurdayathra.BookShelfLMS.view.util.tblmodel.BookTM;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class BookManagementController implements Initializable {

    @FXML
    private AnchorPane bookManagementAnchPane;
    @FXML
    private HBox homeHBox;
    @FXML
    private HBox publisherHBox;
    @FXML
    private HBox damagedBookHBox;
    @FXML
    private HBox helpHBox;
    @FXML
    private HBox bookCategoryHBox;
    @FXML
    private HBox viewMoreHBox;
    @FXML
    private JFXTextField ISBNTF;
    @FXML
    private JFXTextField bookCode_catTxt_TF;
    @FXML
    private JFXTextField bookCode_catNum_TF;
    @FXML
    private JFXTextField bookCode_bookNum_TF;
    @FXML
    private Label bookStatusLbl1;
    @FXML
    private Label bookStatusLbl2;
    @FXML
    private JFXTextField bookTitleTF;
    @FXML
    private JFXTextField priceTF;
    @FXML
    private JFXTextArea otherDetailsTA;
    @FXML
    private JFXTextField bookEditionTF;
    @FXML
    private JFXTextField authorNameTF;
    @FXML
    private JFXComboBox<String> bookCategoryComBox;
    @FXML
    private JFXComboBox<String> bookPublisherComBox;
    @FXML
    private TextField searchBoxTF;
    @FXML
    private Label countLbl;
    @FXML
    private TableView<BookTM> bookDetailsTbl;
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
    private JFXButton damageBtn;

    ObservableList<BookTM> bookTMs;

    BookBO bookBO = (BookBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOK);
    BookCategoryBO bookCategoryBO = (BookCategoryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOKCATEGORY);
    BookPublisherBO bookPublisherBO = (BookPublisherBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOKPUBLISHER);
    MemberBO memberBO = (MemberBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.MEMBER);
    BorrowBO borrowBO = (BorrowBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BORROW);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.gc();

        bookDetailsTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        bookDetailsTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        bookDetailsTbl.getColumns().get(2).setStyle("-fx-alignment:center");
        bookDetailsTbl.getColumns().get(3).setStyle("-fx-alignment:center");
        bookDetailsTbl.getColumns().get(4).setStyle("-fx-alignment:center");
        bookDetailsTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("bookCode"));
        bookDetailsTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        bookDetailsTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("bookCategory"));
        bookDetailsTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("authorName"));
        bookDetailsTbl.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("publisher"));

        newBtn.fire();

        if (!(LogInController.loggedUser.getState().equals("superadmin") | LogInController.loggedUser.getState().equals("admin"))) {
            Alert alert = new Alert(
                    Alert.AlertType.NONE,
                    "\n Only Admins of this System can Delete Books or Move Books to Damage Books. \n",
                    ButtonType.OK
            );
            alert.setTitle("Information");
            alert.show();
        }

    }

    @FXML
    private void homeHBox_onMouseClicked(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/mainDash.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) this.bookManagementAnchPane.getScene().getWindow();
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
    private void bookCategoryHBox_onMouseClicked(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/bookCategory.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) this.bookManagementAnchPane.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

            TranslateTransition trans = new TranslateTransition(Duration.millis(300), scene.getRoot());
            trans.setFromX(-scene.getHeight());
            trans.setToX(0);
            trans.play();
        } catch (IOException ex) {
            printException(ex);
        }
    }

    @FXML
    private void publisherHBox_onMouseClicked(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/bookPublisher.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) this.bookManagementAnchPane.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

            TranslateTransition trans = new TranslateTransition(Duration.millis(300), scene.getRoot());
            trans.setFromX(-scene.getHeight());
            trans.setToX(0);
            trans.play();
        } catch (IOException ex) {
            printException(ex);
        }
    }

    @FXML
    private void damagedBookHBox_onMouseClicked(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/damagedBook.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) this.bookManagementAnchPane.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

            TranslateTransition trans = new TranslateTransition(Duration.millis(300), scene.getRoot());
            trans.setFromX(-scene.getHeight());
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
                stage.initOwner(bookManagementAnchPane.getScene().getWindow());
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
                stage.initOwner(bookManagementAnchPane.getScene().getWindow());
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

    private void loadComboBoxes() {

        new Thread(() -> {
            try {
                loadAllBookCategoryComboBox();
                loadAllBookPublisherComboBox();
            } catch (Exception ex) {
                printException(ex);
            }
        }).start();

    }

    private void loadAllBookCategoryComboBox() throws Exception {
        ArrayList<BookCategoryDTO> bookCategoryDTOs = bookCategoryBO.getAllBookCategories();
        loadBookCategoryComboBox(bookCategoryDTOs);
    }

    private void loadAllBookPublisherComboBox() throws Exception {
        ArrayList<BookPublisherDTO> bookPublisherDTOs = bookPublisherBO.getAllBookPublishers();
        loadBookPublisherComboBox(bookPublisherDTOs);
    }

    private void loadBookCategoryComboBox(ArrayList<BookCategoryDTO> bookCategoryDTOs) throws Exception {
        Platform.runLater(() -> {
            bookCategoryComBox.getItems().clear();
        });
        for (BookCategoryDTO bcdto : bookCategoryDTOs) {
            Platform.runLater(() -> {
                bookCategoryComBox.getItems().add(bcdto.getBookCategoryCode() + " - " + bcdto.getCategoryName());
            });
        }
    }

    private void loadBookPublisherComboBox(ArrayList<BookPublisherDTO> bookPublisherDTOs) throws Exception {

        Platform.runLater(() -> {
            bookPublisherComBox.getItems().clear();
        });
        for (BookPublisherDTO bpdto : bookPublisherDTOs) {
            Platform.runLater(() -> {
                bookPublisherComBox.getItems().add(bpdto.getBookPublisherID() + " - " + bpdto.getName());
            });
        }
    }

    private void loadAllBookDetails() {
        new Thread(() -> {
            try {
                ArrayList<BookDTO> bookDTOs = bookBO.getAllBooks();
                loadBookDetailsTbl(bookDTOs);
            } catch (Exception ex) {
                printException(ex);
            }
        }).start();
    }

    @FXML
    private void bookCode_bookNum_TF_onAction(ActionEvent event) {
        String bookCode_bookNum = bookCode_bookNum_TF.getText();
        boolean matches = bookCode_bookNum.matches("[0-9]{3}");
        if (matches) {

            boolean isAlreadyHave = false;

            String bookCode = bookCode_catTxt_TF.getText()
                    + "."
                    + bookCode_catNum_TF.getText()
                    + "."
                    + bookCode_bookNum_TF.getText();

            bookTMs = bookDetailsTbl.getItems();

            for (BookTM btm : bookTMs) {
                if (btm.getBookCode().equals(bookCode)) {
                    isAlreadyHave = true;
                    break;
                }
            }

            if (isAlreadyHave) {
                new Alert(
                        Alert.AlertType.ERROR,
                        "This Book has been saved Already.",
                        ButtonType.OK
                ).show();
                bookCode_bookNum_TF.requestFocus();
                bookCode_bookNum_TF.selectAll();
            } else {
                ISBNTF.requestFocus();
            }

        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Book Codes' Book Number.",
                    ButtonType.OK
            ).show();
            bookCode_bookNum_TF.requestFocus();
            bookCode_bookNum_TF.selectAll();
        }
    }

    @FXML
    private void bookCode_bookNum_TF_onKeyReleased(KeyEvent event) {
        String bookCode_bookNum = bookCode_bookNum_TF.getText().trim();
        if (!bookCode_bookNum.isEmpty()) {
            if (bookCode_bookNum.length() > 2) {
                bookCode_bookNum_TF.fireEvent(new ActionEvent());
            }
        }
    }

    @FXML
    private void ISBNNOTF_onAction(ActionEvent event) {
        String isbnNO = ISBNTF.getText();
        boolean matches = isbnNO.matches("([0-9 -]{10,})?");
        if (matches) {
            bookTitleTF.requestFocus();
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in ISBN Number.",
                    ButtonType.OK
            ).show();
            ISBNTF.requestFocus();
            ISBNTF.selectAll();
        }
    }

    @FXML
    private void bookTitleTF_onAction(ActionEvent event) {
        String bookTitle = bookTitleTF.getText();
        boolean matches = bookTitle.matches("[a-z A-Z 0-9 - . \\s]{2,}");
        if (matches) {
            bookEditionTF.requestFocus();
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Book Title.",
                    ButtonType.OK
            ).show();
            bookTitleTF.requestFocus();
            bookTitleTF.selectAll();
        }
    }

    @FXML
    private void priceTF_onAction(ActionEvent event) {
        String price = priceTF.getText();
        boolean matches = price.matches("([0-9 . \\s]{1,})?");
        if (matches) {
            otherDetailsTA.requestFocus();
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Price.",
                    ButtonType.OK
            ).show();
            priceTF.requestFocus();
            priceTF.selectAll();
        }
    }

    @FXML
    private void bookEditionTF_onAction(ActionEvent event) {
        String bookEdition = bookEditionTF.getText();
        boolean matches = bookEdition.matches("([a-z A-Z 0-9 - . \\s]{1,})?");
        if (matches) {
            authorNameTF.requestFocus();
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Book Edition.",
                    ButtonType.OK
            ).show();
            bookEditionTF.requestFocus();
            bookEditionTF.selectAll();
        }
    }

    @FXML
    private void authorNameTF_onAction(ActionEvent event) {
        String authorName = authorNameTF.getText();
        boolean matches = authorName.matches("[a-z A-Z 0-9 - . \\s]{2,}");
        if (matches) {
            bookPublisherComBox.requestFocus();
            bookPublisherComBox.show();
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Author Name.",
                    ButtonType.OK
            ).show();
            authorNameTF.requestFocus();
            authorNameTF.selectAll();
        }
    }

    @FXML
    private void bookCategoryComBox_onAction(ActionEvent event) {
        try {
            BookCategoryDTO bookCategoryDTO = null;
            if (bookCategoryComBox.getValue() != null && !bookCategoryComBox.getValue().equals("")) {
                if (bookCategoryComBox.getValue().length() > 12) {
                    if (bookCategoryComBox.getValue().substring(0, 7).matches("[A-Z]{3}[.]{1}[0-9]{3}")) {
                        bookCategoryDTO = bookCategoryBO.getBookCategory(bookCategoryComBox.getValue().substring(0, 7));
                    } else {
                        bookCode_catTxt_TF.setText("");
                        bookCode_catNum_TF.setText("");
                        bookCode_bookNum_TF.setText("");
                    }
                } else {
                    bookCode_catTxt_TF.setText("");
                    bookCode_catNum_TF.setText("");
                    bookCode_bookNum_TF.setText("");
                }
            }
            if (bookCategoryDTO != null) {
                bookCode_catTxt_TF.setText(bookCategoryDTO.getBookCategoryCode().substring(0, 3));
                bookCode_catNum_TF.setText(bookCategoryDTO.getBookCategoryCode().substring(4, 7));
                bookCode_bookNum_TF.requestFocus();
            } else {
                bookCategoryComBox.requestFocus();
            }
        } catch (Exception ex) {
            printException(ex);
        }
    }

    @FXML
    private void bookCategoryComBox_onKeyReleased(KeyEvent event) {
        new Thread(() -> {
            try {

                if (bookCategoryComBox.getValue() == null) {
                    loadAllBookCategoryComboBox();
                    Platform.runLater(() -> {
                        bookCategoryComBox.show();
                    });
                } else {
                    String searchText = bookCategoryComBox.getValue();

                    ArrayList<BookCategoryDTO> bookCategoryDTOs = bookCategoryBO.searchBookCategory(searchText);

                    loadBookCategoryComboBox(bookCategoryDTOs);
                    Platform.runLater(() -> {
                        bookCategoryComBox.show();
                    });

                }

            } catch (Exception ex) {
                printException(ex);
            }
        }).start();
    }

    @FXML
    private void bookPublisherComBox_onAction(ActionEvent event) {
//        priceTF.requestFocus();
    }

    @FXML
    private void bookPublisherComBox_onKeyReleased(KeyEvent event) {
        new Thread(() -> {
            try {

                if (bookPublisherComBox.getValue() == null) {
                    loadAllBookPublisherComboBox();
                    Platform.runLater(() -> {
                        bookPublisherComBox.show();
                    });
                } else {
                    String searchText = bookPublisherComBox.getValue();

                    ArrayList<BookPublisherDTO> bookPublisherDTOs = bookPublisherBO.searchBookPublisher(searchText);

                    loadBookPublisherComboBox(bookPublisherDTOs);
                    Platform.runLater(() -> {
                        bookPublisherComBox.show();
                    });

                }

            } catch (Exception ex) {
                printException(ex);
            }
        }).start();
    }

    @FXML
    private void bookDetailsTbl_onMouseClicked(MouseEvent event) {
        new Thread(() -> {
            try {

                BookTM bookTM = bookDetailsTbl.getSelectionModel().getSelectedItem();

                if (bookTM != null) {
                    String bookCode = bookTM.getBookCode();

                    BookDTO bookDTO = bookBO.getBook(bookCode);

                    Platform.runLater(() -> {
                        try {
                            String bCode = bookDTO.getBookCode();
                            bookCode_catTxt_TF.setText(bCode.substring(0, 3));
                            bookCode_catNum_TF.setText(bCode.substring(4, 7));
                            bookCode_bookNum_TF.setText(bCode.substring(8, 11));
                            if (bookDTO.getIsbnNO() == null) {
                                ISBNTF.setText("");
                            } else {
                                ISBNTF.setText(bookDTO.getIsbnNO());
                            }
                            bookTitleTF.setText(bookDTO.getBookTitle());
                            if (bookDTO.getBookEdition() == null) {
                                bookEditionTF.setText("");
                            } else {
                                bookEditionTF.setText(bookDTO.getBookEdition());
                            }
                            bookCategoryComBox.setValue(bookDTO.getBookCategoryCode() + " - " + bookBO.getBookCategoryName(bookDTO.getBookCategoryCode()));
                            authorNameTF.setText(bookDTO.getAuthorName());
                            bookPublisherComBox.setValue(bookDTO.getPublisherID() + " - " + bookBO.getBookPublisherName(bookDTO.getPublisherID()));
                            if (Double.toString(bookDTO.getPrice()) == null) {
                                priceTF.setText("0.00");
                            } else {
                                priceTF.setText(Double.toString(bookDTO.getPrice()));
                            }
                            if (bookDTO.getOtherDetails() == null) {
                                otherDetailsTA.setText("");
                            } else {
                                otherDetailsTA.setText(bookDTO.getOtherDetails());
                            }

                            bookStatusLbl1.setVisible(true);
                            bookStatusLbl2.setVisible(true);

                            String status = bookDTO.getStatus();
                            if (status.equals("MEM.000000")) {
                                bookStatusLbl2.setText(" Not Issued Yet. ");
                            } else {
                                bookStatusLbl2.setText(status + " - " + memberBO.getMember(status).getName());
                            }

                            boolean isIssuedAlready = false;

                            ArrayList<BorrowDTO> borrowDTOs = borrowBO.getNotReturnedBorrows();

                            for (BorrowDTO borrowDTO : borrowDTOs) {
                                if (borrowDTO.getBookCode().equals(bCode)) {
                                    isIssuedAlready = true;
                                    break;
                                }
                            }

                            newBtn.setDisable(false);
                            saveBtn.setDisable(true);
                            updateBtn.setDisable(false);

                            if (LogInController.loggedUser.getState().equals("admin") | LogInController.loggedUser.getState().equals("superadmin")) {
                                if (isIssuedAlready) {
                                    deleteBtn.setDisable(true);
                                } else {
                                    deleteBtn.setDisable(false);
                                }

                                if (bookDTO.getStatus().equals("MEM.000000")) {
                                    damageBtn.setDisable(false);
                                } else {
                                    damageBtn.setDisable(true);
                                }
                            } else {
                                deleteBtn.setDisable(true);
                                damageBtn.setDisable(true);
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
    private void searchBoxTF_onMouseClicked(MouseEvent event) {
        searchBoxTF.selectAll();
    }

    @FXML
    private void searchBoxTF_onKeyReleased(KeyEvent event) {
        new Thread(() -> {
            try {

                if (searchBoxTF.getText() == null) {
                    loadAllBookDetails();
                } else {
                    String searchText = searchBoxTF.getText();

                    ArrayList<BookDTO> bookDTOs = bookBO.searchBook(searchText);

                    if (bookDTOs.isEmpty()) {
                        Platform.runLater(() -> {
                            searchBoxTF.setStyle("-fx-text-fill: #D91022");
                        });
                        loadAllBookDetails();
                    } else {
                        Platform.runLater(() -> {
                            searchBoxTF.setStyle("-fx-text-fill: #000000");
                        });
                        loadBookDetailsTbl(bookDTOs);
                    }
                }

            } catch (Exception ex) {
                printException(ex);
            }
        }).start();
    }

    @FXML
    private void newBtn_onAction(ActionEvent event) {

        bookCode_catTxt_TF.setText("");
        bookCode_catNum_TF.setText("");
        bookCode_bookNum_TF.setText("");
        bookCode_catTxt_TF.setPromptText("ABC");
        bookCode_catNum_TF.setPromptText("123");
        bookCode_bookNum_TF.setPromptText("001");
        ISBNTF.setText("");
        bookTitleTF.setText("");
        bookEditionTF.setText("");
        bookCategoryComBox.setValue("");
        authorNameTF.setText("");
        bookPublisherComBox.setValue("");
        priceTF.setText("");
        otherDetailsTA.setText("");

        bookStatusLbl1.setText("Book Status : ");
        bookStatusLbl2.setText("");
        bookStatusLbl1.setVisible(false);
        bookStatusLbl2.setVisible(false);

        searchBoxTF.setText("");

        loadComboBoxes();
        loadAllBookDetails();

        bookCode_catTxt_TF.requestFocus();

        Platform.runLater(() -> {
            if (LogInController.loggedUser.getState().equals("admin") | LogInController.loggedUser.getState().equals("superadmin")) {
                newBtn.setDisable(false);
                saveBtn.setDisable(false);
                updateBtn.setDisable(true);
                deleteBtn.setDisable(true);
                damageBtn.setDisable(true);
            } else {
                newBtn.setDisable(false);
                saveBtn.setDisable(false);
                updateBtn.setDisable(true);
                deleteBtn.setDisable(true);
                damageBtn.setDisable(true);
            }
        });

    }

    @FXML
    private void saveBtn_onAction(ActionEvent event) {

        Platform.runLater(() -> {

            boolean isAlreadyHave = false;

            bookTMs = bookDetailsTbl.getItems();

            for (BookTM bookTM : bookTMs) {
                if (bookTM.getBookCode().equals(
                        bookCode_catTxt_TF.getText()
                        + "." + bookCode_catNum_TF.getText()
                        + "." + bookCode_bookNum_TF.getText()
                )) {
                    isAlreadyHave = true;
                    break;
                }
            }

            if (isAlreadyHave) {
                new Alert(
                        Alert.AlertType.ERROR,
                        "The book you are going to save is already in book table.",
                        ButtonType.OK
                ).show();
            } else {
                try {

                    String bookCode = bookCode_catTxt_TF.getText() + "." + bookCode_catNum_TF.getText() + "." + bookCode_bookNum_TF.getText();
                    String isbnNO = ISBNTF.getText();
                    String bookTitle = bookTitleTF.getText();
                    String bookEdition = bookEditionTF.getText();
                    String bookCategory = bookCategoryComBox.getValue();
                    String authorName = authorNameTF.getText();
                    String publisher = bookPublisherComBox.getValue();
                    String price = priceTF.getText();
                    String otherDetails = otherDetailsTA.getText();
                    String status = "MEM.000000";

                    if (price.isEmpty()) {
                        price = "0.0";
                    }

                    BookDTO bookDTO = new BookDTO(
                            bookCode,
                            isbnNO,
                            bookTitle,
                            bookEdition,
                            bookCategory.substring(0, 7),
                            authorName,
                            publisher.substring(0, 8),
                            Double.parseDouble(price),
                            otherDetails,
                            status
                    );

                    boolean result = false;

                    if (isValidTextFields()) {
                        result = bookBO.saveBook(bookDTO);
                    }

                    if (result) {
                        new Alert(
                                Alert.AlertType.INFORMATION,
                                "Book '" + bookDTO.getBookCode() + "' has been Saved Successfully.",
                                ButtonType.OK
                        ).show();
                        newBtn.fire();
                    } else {
                        new Alert(
                                Alert.AlertType.ERROR,
                                "Failed to Save the Book '" + bookDTO.getBookCode() + "'.",
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

            bookTMs = bookDetailsTbl.getItems();

            for (BookTM bookTM : bookTMs) {
                if (bookTM.getBookCode().equals(
                        bookCode_catTxt_TF.getText()
                        + "." + bookCode_catNum_TF.getText()
                        + "." + bookCode_bookNum_TF.getText()
                )) {
                    isAlreadyHave = true;
                    break;
                }
            }

            if (!isAlreadyHave) {
                new Alert(
                        Alert.AlertType.ERROR,
                        "The book you are going to update is not avilable in book table.",
                        ButtonType.OK
                ).show();
            } else {
                try {

                    String bookCode = bookCode_catTxt_TF.getText() + "." + bookCode_catNum_TF.getText() + "." + bookCode_bookNum_TF.getText();
                    String isbnNO = ISBNTF.getText();
                    String bookTitle = bookTitleTF.getText();
                    String bookEdition = bookEditionTF.getText();
                    String bookCategory = bookCategoryComBox.getValue();
                    String authorName = authorNameTF.getText();
                    String publisher = bookPublisherComBox.getValue();
                    String price = priceTF.getText();
                    String otherDetails = otherDetailsTA.getText();
                    String status;

                    if (bookStatusLbl2.getText().substring(0, 4).equals("MEM.")) {
                        status = bookStatusLbl2.getText().substring(0, 10);
                    } else {
                        status = "MEM.000000";
                    }

                    if (price.isEmpty()) {
                        price = "0.0";
                    }

                    BookDTO bookDTO = new BookDTO(
                            bookCode,
                            isbnNO,
                            bookTitle,
                            bookEdition,
                            bookCategory.substring(0, 7),
                            authorName,
                            publisher.substring(0, 8),
                            Double.parseDouble(price),
                            otherDetails,
                            status
                    );

                    boolean result = false;

                    if (isValidTextFields()) {

                        Alert alert = new Alert(
                                Alert.AlertType.CONFIRMATION,
                                "Are You Sure to Update Book " + bookDTO.getBookCode() + " ?",
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
                            result = bookBO.updateBook(bookDTO);

                            if (result) {
                                new Alert(
                                        Alert.AlertType.INFORMATION,
                                        "Book '" + bookDTO.getBookCode() + "' has been Updated Successfully.",
                                        ButtonType.OK
                                ).show();
                                newBtn.fire();
                            } else {
                                new Alert(
                                        Alert.AlertType.ERROR,
                                        "Failed to Update the Book '" + bookDTO.getBookCode() + "'.",
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

        String bookCode_catTxt = bookCode_catTxt_TF.getText();
        String bookCode_catNum = bookCode_catNum_TF.getText();
        String bookCode_bookNum = bookCode_bookNum_TF.getText();
        String isbnNO = ISBNTF.getText();
        String bookTitle = bookTitleTF.getText();
        String bookEdition = bookEditionTF.getText();
        String authorName = authorNameTF.getText();
        String price = priceTF.getText();
        String otherDetails = otherDetailsTA.getText();

        boolean matches1 = bookCode_bookNum.matches("[0-9]{3}");
        boolean matches2 = isbnNO.matches("([a-z A-Z 0-9 - . \\s]{10,})?");
        boolean matches3 = bookTitle.matches("[a-z A-Z 0-9 - . \\s]{2,}");
        boolean matches4 = bookEdition.matches("([a-z A-Z 0-9 - . \\s]{1,})?");
        boolean matches5 = authorName.matches("[a-z A-Z 0-9 - . \\s]{2,}");
        boolean matches6 = price.matches("([0-9 . \\s]{1,})?");
        boolean matches7 = otherDetails.matches("([0-9 a-z A-Z . , / ' \" \\s]{1,})?");

        boolean isValidTextFields = false;

        if (matches1) {
            if (matches2) {
                if (!bookCategoryComBox.getValue().isEmpty()) {
                    if (matches3) {
                        if (!bookPublisherComBox.getValue().isEmpty()) {
                            if (matches4) {
                                if (matches5) {
                                    if (matches6) {
                                        if (matches7) {
                                            if ((bookCode_catTxt + "." + bookCode_catNum).equals(bookCategoryComBox.getValue().substring(0, 7))) {
                                                isValidTextFields = true;
                                            } else {
                                                new Alert(
                                                        Alert.AlertType.ERROR,
                                                        "Oops... Book Code and Book Category Code doesn't Match.",
                                                        ButtonType.OK
                                                ).show();
                                            }
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
                                                "Oops... Something Wrong in Price.",
                                                ButtonType.OK
                                        ).show();
                                        priceTF.requestFocus();
                                        priceTF.selectAll();
                                    }
                                } else {
                                    new Alert(
                                            Alert.AlertType.ERROR,
                                            "Oops... Something Wrong in Publisher.",
                                            ButtonType.OK
                                    ).show();
                                    bookPublisherComBox.requestFocus();
                                }
                            } else {
                                new Alert(
                                        Alert.AlertType.ERROR,
                                        "Oops... Something Wrong in Author Name.",
                                        ButtonType.OK
                                ).show();
                                authorNameTF.requestFocus();
                                authorNameTF.selectAll();
                            }
                        } else {
                            new Alert(
                                    Alert.AlertType.ERROR,
                                    "Oops... Something Wrong in Book Category.",
                                    ButtonType.OK
                            ).show();
                            bookCategoryComBox.requestFocus();
                        }
                    } else {
                        new Alert(
                                Alert.AlertType.ERROR,
                                "Oops... Something Wrong in Book Edition.",
                                ButtonType.OK
                        ).show();
                        bookEditionTF.requestFocus();
                        bookEditionTF.selectAll();
                    }
                } else {
                    new Alert(
                            Alert.AlertType.ERROR,
                            "Oops... Something Wrong in Book Title.",
                            ButtonType.OK
                    ).show();
                    bookTitleTF.requestFocus();
                    bookTitleTF.selectAll();
                }
            } else {
                new Alert(
                        Alert.AlertType.ERROR,
                        "Oops... Something Wrong in ISBN Number.",
                        ButtonType.OK
                ).show();
                ISBNTF.requestFocus();
                ISBNTF.selectAll();
            }
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Book Codes' Book Number.",
                    ButtonType.OK
            ).show();
            bookCode_bookNum_TF.requestFocus();
            bookCode_bookNum_TF.selectAll();
        }

        return isValidTextFields;

    }

    @FXML
    private void deleteBtn_onAction(ActionEvent event) {

        Platform.runLater(() -> {

            boolean isAlreadyHave = false;

            bookTMs = bookDetailsTbl.getItems();

            for (BookTM bookTM : bookTMs) {
                if (bookTM.getBookCode().equals(
                        bookCode_catTxt_TF.getText()
                        + "." + bookCode_catNum_TF.getText()
                        + "." + bookCode_bookNum_TF.getText()
                )) {
                    isAlreadyHave = true;
                    break;
                }
            }

            if (!isAlreadyHave) {
                new Alert(
                        Alert.AlertType.ERROR,
                        "The book you are going to delete is not avilable in book table.",
                        ButtonType.OK
                ).show();
            } else {
                try {

                    String bookCode_catTxt = bookCode_catTxt_TF.getText();
                    String bookCode_catNum = bookCode_catNum_TF.getText();
                    String bookCode_bookNum = bookCode_bookNum_TF.getText();
                    boolean matches1 = bookCode_catTxt.matches("[A-Z]{3}");
                    boolean matches2 = bookCode_catNum.matches("[0-9]{3}");
                    boolean matches3 = bookCode_bookNum.matches("[0-9]{3}");

                    String bookCode = bookCode_catTxt_TF.getText() + "." + bookCode_catNum_TF.getText() + "." + bookCode_bookNum_TF.getText();

                    boolean result = false;

                    if (matches1 & matches2 & matches3) {

                        Alert alert = new Alert(
                                Alert.AlertType.CONFIRMATION,
                                "Are You Sure to Delete Book " + bookCode + " ?",
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
                            result = bookBO.deleteBook(bookCode);

                            if (result) {
                                new Alert(
                                        Alert.AlertType.INFORMATION,
                                        "Book '" + bookCode + "' has been Deleted Successfully.",
                                        ButtonType.OK
                                ).show();
                                newBtn.fire();
                            } else {
                                new Alert(
                                        Alert.AlertType.ERROR,
                                        "Failed to Delete the Book '" + bookCode + "'.",
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

    @FXML
    private void damageBtn_onAction(ActionEvent event) {
        try {
            String bookCode_catTxt = bookCode_catTxt_TF.getText();
            String bookCode_catNum = bookCode_catNum_TF.getText();
            String bookCode_bookNum = bookCode_bookNum_TF.getText();
            boolean matches1 = bookCode_catTxt.matches("[A-Z]{3}");
            boolean matches2 = bookCode_catNum.matches("[0-9]{3}");
            boolean matches3 = bookCode_bookNum.matches("[0-9]{3}");

            String bookCode = bookCode_catTxt_TF.getText() + "." + bookCode_catNum_TF.getText() + "." + bookCode_bookNum_TF.getText();

            boolean result = false;

            if (matches1 & matches2 & matches3) {

                Alert alert = new Alert(
                        Alert.AlertType.CONFIRMATION,
                        "Are You Sure to Move Book " + bookCode + " to Damage Books?",
                        ButtonType.OK,
                        ButtonType.NO
                );
                alert.setTitle("Move to Damage Books ?");
                Button yesButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                yesButton.setDefaultButton(false);
                Button noButton = (Button) alert.getDialogPane().lookupButton(ButtonType.NO);
                noButton.setDefaultButton(true);
                Optional<ButtonType> action = alert.showAndWait();

                if (action.get() == ButtonType.OK) {
                    BookDTO bookDTO = bookBO.getBook(bookCode);
                    bookDTO.setStatus("MEM.999999");
                    result = bookBO.updateBook(bookDTO);

                    if (result) {
                        new Alert(
                                Alert.AlertType.INFORMATION,
                                "Book '" + bookCode + "' has been Moved to Damaged Books Successfully.",
                                ButtonType.OK
                        ).show();
                        newBtn.fire();
                    } else {
                        new Alert(
                                Alert.AlertType.ERROR,
                                "Failed to Move the Book '" + bookCode + "'.",
                                ButtonType.OK
                        ).show();
                    }
                }

            }

        } catch (Exception ex) {
            printException(ex);
        }
    }

    private void loadBookDetailsTbl(ArrayList<BookDTO> bookDTOs) {

        Platform.runLater(() -> {
            try {
                countLbl.setText(Integer.toString(bookDTOs.size()));

                bookTMs = bookDetailsTbl.getItems();
                bookDetailsTbl.getItems().clear();

                bookTMs.removeAll(bookTMs);
                bookTMs.clear();

                for (BookDTO bookDTO : bookDTOs) {
                    bookTMs.add(new BookTM(
                            bookDTO.getBookCode(),
                            bookDTO.getBookTitle(),
                            bookBO.getBookCategoryName(bookDTO.getBookCategoryCode()),
                            bookDTO.getAuthorName(),
                            bookBO.getBookPublisherName(bookDTO.getPublisherID())
                    ));
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
