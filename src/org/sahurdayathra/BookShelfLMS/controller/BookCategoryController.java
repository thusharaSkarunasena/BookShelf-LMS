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
import org.sahurdayathra.BookShelfLMS.business.custom.BookCategoryBO;
import org.sahurdayathra.BookShelfLMS.dto.BookCategoryDTO;
import org.sahurdayathra.BookShelfLMS.view.util.tblmodel.BookCategoryTM;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class BookCategoryController implements Initializable {

    @FXML
    private AnchorPane bookCategoryAnchPane;
    @FXML
    private HBox homeHBox;
    @FXML
    private HBox backHBox;
    @FXML
    private HBox helpHBox;
    @FXML
    private HBox viewMoreHBox;
    @FXML
    private JFXTextField categoryNameTF;
    @FXML
    private JFXTextField bookCatCode_txt_TF;
    @FXML
    private JFXTextField bookCatCode_num_TF;
    @FXML
    private JFXTextArea otherDetailsTA;
    @FXML
    private TextField searchBoxTF;
    @FXML
    private Label countLbl;
    @FXML
    private TableView<BookCategoryTM> bookCategoryDetailsTbl;
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

    ObservableList<BookCategoryTM> bookCategoryTMs;

    BookCategoryBO bookCategoryBO = (BookCategoryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOKCATEGORY);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.gc();

        bookCategoryDetailsTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        bookCategoryDetailsTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        bookCategoryDetailsTbl.getColumns().get(2).setStyle("-fx-alignment:center");
        bookCategoryDetailsTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("bookCategoryCode"));
        bookCategoryDetailsTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        bookCategoryDetailsTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("otherDetails"));

        newBtn.fire();

        if (!(LogInController.loggedUser.getState().equals("superadmin") | LogInController.loggedUser.getState().equals("admin"))) {
            Alert alert = new Alert(
                    Alert.AlertType.NONE,
                    "\n Only Admins of this system can update book category details. \n",
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
            Stage stage = (Stage) this.bookCategoryAnchPane.getScene().getWindow();
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
            Stage stage = (Stage) this.bookCategoryAnchPane.getScene().getWindow();
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
                stage.initOwner(bookCategoryAnchPane.getScene().getWindow());
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
                stage.initOwner(bookCategoryAnchPane.getScene().getWindow());
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

    private void loadAllBookCategoryDetails() {
        new Thread(() -> {
            try {
                ArrayList<BookCategoryDTO> bookCategoryDTOs = bookCategoryBO.getAllBookCategories();
                loadBookCategoryDetailTbl(bookCategoryDTOs);
            } catch (Exception ex) {
                printException(ex);
            }
        }).start();
    }

    @FXML
    private void bookCatCode_txt_TF_onAction(ActionEvent event) {
        String bookCategoryCode_txt = bookCatCode_txt_TF.getText();
        boolean matches = bookCategoryCode_txt.matches("[A-Z]{3}");
        if (matches) {
            bookCatCode_num_TF.requestFocus();
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Category Name Text Part.",
                    ButtonType.OK
            ).show();
            bookCatCode_txt_TF.requestFocus();
            bookCatCode_txt_TF.selectAll();
        }
    }

    @FXML
    private void bookCatCode_txt_TF_onKeyReleased(KeyEvent event) {
        String bookCatCode_txt = bookCatCode_txt_TF.getText().trim();
        if (!bookCatCode_txt.isEmpty()) {
            if (bookCatCode_txt.length() > 2) {
                bookCatCode_txt_TF.fireEvent(new ActionEvent());
            }
        }
    }

    @FXML
    private void bookCatCode_num_TF_onAction(ActionEvent event) {
        String bookCategoryCode_num = bookCatCode_num_TF.getText();
        boolean matches = bookCategoryCode_num.matches("[0-9]{3}");
        if (matches) {
            categoryNameTF.requestFocus();
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Category Name Number Part.",
                    ButtonType.OK
            ).show();
            bookCatCode_num_TF.requestFocus();
            bookCatCode_num_TF.selectAll();
        }
    }

    @FXML
    private void bookCatCode_num_TF_onKeyReleased(KeyEvent event) {
        String bookCatCode_num = bookCatCode_num_TF.getText().trim();
        if (!bookCatCode_num.isEmpty()) {
            if (bookCatCode_num.length() > 2) {
                bookCatCode_num_TF.fireEvent(new ActionEvent());
            }
        }
    }

    @FXML
    private void categoryNameTF_onAction(ActionEvent event) {
        String categoryName = categoryNameTF.getText();
        boolean matches = categoryName.matches("[a-z A-Z . \\s]{3,}");
        if (matches) {
            otherDetailsTA.requestFocus();
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Category Name.",
                    ButtonType.OK
            ).show();
            categoryNameTF.requestFocus();
            categoryNameTF.selectAll();
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
                    loadAllBookCategoryDetails();
                } else {
                    String searchText = searchBoxTF.getText();

                    ArrayList<BookCategoryDTO> bookCategoryDTOs = bookCategoryBO.searchBookCategory(searchText);

                    if (bookCategoryDTOs.isEmpty()) {
                        Platform.runLater(() -> {
                            searchBoxTF.setStyle("-fx-text-fill: #D91022");
                        });
                        loadAllBookCategoryDetails();
                    } else {
                        Platform.runLater(() -> {
                            searchBoxTF.setStyle("-fx-text-fill: #000000");
                        });
                        loadBookCategoryDetailTbl(bookCategoryDTOs);
                    }
                }

            } catch (Exception ex) {
                printException(ex);
            }
        }).start();
    }

    @FXML
    private void bookCategoryDetailsTbl_onMouseClicked(MouseEvent event) {
        new Thread(() -> {
            try {

                BookCategoryTM bookCategoryTM = bookCategoryDetailsTbl.getSelectionModel().getSelectedItem();

                if (bookCategoryTM != null) {

                    String bookCategoryCode = bookCategoryTM.getBookCategoryCode();

                    BookCategoryDTO bookCategoryDTO = bookCategoryBO.getBookCategory(bookCategoryCode);

                    Platform.runLater(() -> {
                        String bookCatCode = bookCategoryDTO.getBookCategoryCode();
                        bookCatCode_txt_TF.setText(bookCatCode.substring(0, 3));
                        bookCatCode_num_TF.setText(bookCatCode.substring(4, 7));
                        categoryNameTF.setText(bookCategoryDTO.getCategoryName());
                        if (bookCategoryDTO.getOtherDetails() == null) {
                            otherDetailsTA.setText("");
                        } else {
                            otherDetailsTA.setText(bookCategoryDTO.getOtherDetails());
                        }

                        if (LogInController.loggedUser.getState().equals("admin") | LogInController.loggedUser.getState().equals("superadmin")) {
                            newBtn.setDisable(false);
                            saveBtn.setDisable(true);
                            updateBtn.setDisable(false);
                            deleteBtn.setDisable(false);
                        } else {
                            newBtn.setDisable(false);
                            saveBtn.setDisable(true);
                            updateBtn.setDisable(true);
                            deleteBtn.setDisable(true);
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

        bookCatCode_txt_TF.setText("");
        bookCatCode_num_TF.setText("");
        bookCatCode_txt_TF.setPromptText("ABC");
        bookCatCode_num_TF.setPromptText("123");
        categoryNameTF.setText("");
        otherDetailsTA.setText("");

        loadAllBookCategoryDetails();

        bookCatCode_txt_TF.requestFocus();

        Platform.runLater(() -> {
            if (LogInController.loggedUser.getState().equals("admin") | LogInController.loggedUser.getState().equals("superadmin")) {
                newBtn.setDisable(false);
                saveBtn.setDisable(false);
                updateBtn.setDisable(true);
                deleteBtn.setDisable(true);
            } else {
                newBtn.setDisable(false);
                saveBtn.setDisable(true);
                updateBtn.setDisable(true);
                deleteBtn.setDisable(true);
            }
        });

    }

    @FXML
    private void saveBtn_onAction(ActionEvent event) {

        Platform.runLater(() -> {

            boolean isAlreadyHave = false;

            bookCategoryTMs = bookCategoryDetailsTbl.getItems();

            for (BookCategoryTM bctm : bookCategoryTMs) {
                if (bctm.getBookCategoryCode().equals(bookCatCode_txt_TF.getText() + "." + bookCatCode_num_TF.getText())) {
                    isAlreadyHave = true;
                    break;
                }
            }

            if (isAlreadyHave) {
                new Alert(
                        Alert.AlertType.ERROR,
                        "The book Category you are going to save is already in book category table.",
                        ButtonType.OK
                ).show();
            } else {
                try {

                    String bookCategoryCode = bookCatCode_txt_TF.getText() + "." + bookCatCode_num_TF.getText();
                    String categoryName = categoryNameTF.getText();
                    String otherDetails = otherDetailsTA.getText();

                    BookCategoryDTO bookCategoryDTO = new BookCategoryDTO(
                            bookCategoryCode,
                            categoryName,
                            otherDetails
                    );

                    boolean result = false;

                    if (isVAlidTextFields()) {
                        result = bookCategoryBO.saveBookCategory(bookCategoryDTO);
                    }

                    if (result) {
                        new Alert(
                                Alert.AlertType.INFORMATION,
                                "Book Category '" + bookCategoryDTO.getBookCategoryCode() + "' has been Saved Successfully.",
                                ButtonType.OK
                        ).show();
                        newBtn.fire();
                    } else {
                        new Alert(
                                Alert.AlertType.ERROR,
                                "Failed to Save the Book Category '" + bookCategoryDTO.getBookCategoryCode() + "'.",
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

            bookCategoryTMs = bookCategoryDetailsTbl.getItems();

            for (BookCategoryTM bctm : bookCategoryTMs) {
                if (bctm.getBookCategoryCode().equals(bookCatCode_txt_TF.getText() + "." + bookCatCode_num_TF.getText()) | bctm.getCategoryName().equals(categoryNameTF.getText())) {
                    isAlreadyHave = true;
                    break;
                }
            }

            if (!isAlreadyHave) {
                new Alert(
                        Alert.AlertType.ERROR,
                        "The Book Category you are going to update is not avilable in Book Category table.",
                        ButtonType.OK
                ).show();
            } else {
                try {

                    String bookCategoryCode = bookCatCode_txt_TF.getText() + "." + bookCatCode_num_TF.getText();
                    String categoryName = categoryNameTF.getText();
                    String otherDetails = otherDetailsTA.getText();

                    BookCategoryDTO bookCategoryDTO = new BookCategoryDTO(
                            bookCategoryCode,
                            categoryName,
                            otherDetails
                    );

                    boolean result = false;

                    if (isVAlidTextFields()) {

                        Alert alert = new Alert(
                                Alert.AlertType.CONFIRMATION,
                                "Are You Sure to Update Book Category " + bookCategoryDTO.getBookCategoryCode() + " ?",
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
                            result = bookCategoryBO.updateBookCategory(bookCategoryDTO);

                            if (result) {
                                new Alert(
                                        Alert.AlertType.INFORMATION,
                                        "Book Category '" + bookCategoryDTO.getBookCategoryCode() + "' has been Updated Successfully.",
                                        ButtonType.OK
                                ).show();
                                newBtn.fire();
                            } else {
                                new Alert(
                                        Alert.AlertType.ERROR,
                                        "Failed to Update the Book Category '" + bookCategoryDTO.getBookCategoryCode() + "'.",
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

    private boolean isVAlidTextFields() {

        String bookCategoryCode_txt = bookCatCode_txt_TF.getText();
        String bookCategoryCode_num = bookCatCode_num_TF.getText();
        String categoryName = categoryNameTF.getText();
        String otherDetails = otherDetailsTA.getText();

        boolean matches1 = bookCategoryCode_txt.matches("[A-Z]{3}");
        boolean matches2 = bookCategoryCode_num.matches("[0-9]{3}");
        boolean matches3 = categoryName.matches("[a-z A-Z 0-9 . \\s]{3,}");
        boolean matches4 = otherDetails.matches("([0-9 a-z A-Z . , / ' \" \\s]{1,})?");

        boolean isValidTextFields = false;

        if (matches1) {
            if (matches2) {
                if (matches3) {
                    if (matches4) {
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
                            "Oops... Something Wrong in Category Name.",
                            ButtonType.OK
                    ).show();
                    categoryNameTF.requestFocus();
                    categoryNameTF.selectAll();
                }
            } else {
                new Alert(
                        Alert.AlertType.ERROR,
                        "Oops... Something Wrong in Category Name Number Part.",
                        ButtonType.OK
                ).show();
                bookCatCode_num_TF.requestFocus();
                bookCatCode_num_TF.selectAll();
            }
        } else {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Oops... Something Wrong in Category Name Text Part.",
                    ButtonType.OK
            ).show();
            bookCatCode_txt_TF.requestFocus();
            bookCatCode_txt_TF.selectAll();
        }

        return isValidTextFields;

    }

    @FXML
    private void deleteBtn_onAction(ActionEvent event) {

        Platform.runLater(() -> {
            boolean isAlreadyHave = false;

            bookCategoryTMs = bookCategoryDetailsTbl.getItems();

            for (BookCategoryTM bctm : bookCategoryTMs) {
                if (bctm.getBookCategoryCode().equals(bookCatCode_txt_TF.getText() + "." + bookCatCode_num_TF.getText()) | bctm.getCategoryName().equals(categoryNameTF.getText())) {
                    isAlreadyHave = true;
                    break;
                }
            }

            if (!isAlreadyHave) {
                new Alert(
                        Alert.AlertType.ERROR,
                        "The Book Category you are going to update is not avilable in Book Category table.",
                        ButtonType.OK
                ).show();
            } else {
                try {

                    String bookCategoryCode_txt = bookCatCode_txt_TF.getText();
                    String bookCategoryCode_num = bookCatCode_num_TF.getText();

                    boolean matches1 = bookCategoryCode_txt.matches("[A-Z]{3}");
                    boolean matches2 = bookCategoryCode_num.matches("[0-9]{3}");

                    String bookCategoryCode = bookCatCode_txt_TF.getText() + "." + bookCatCode_num_TF.getText();

                    boolean result = false;

                    if (matches1 & matches2) {
                        Alert alert = new Alert(
                                Alert.AlertType.CONFIRMATION,
                                "Are You Sure to Delete Book Category " + bookCategoryCode + " ?",
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
                            result = bookCategoryBO.deleteBookCategory(bookCategoryCode);

                            if (result) {
                                new Alert(
                                        Alert.AlertType.INFORMATION,
                                        "Book Category '" + bookCategoryCode + "' has been Deleted Successfully.",
                                        ButtonType.OK
                                ).show();
                                newBtn.fire();
                            } else {
                                new Alert(
                                        Alert.AlertType.ERROR,
                                        "Failed to Delete the Book Category '" + bookCategoryCode + "'.",
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

    private void loadBookCategoryDetailTbl(ArrayList<BookCategoryDTO> bookCategoryDTOs) {

        Platform.runLater(() -> {
            countLbl.setText(Integer.toString(bookCategoryDTOs.size()));

            bookCategoryTMs = bookCategoryDetailsTbl.getItems();
            bookCategoryDetailsTbl.getItems().clear();

            bookCategoryTMs.removeAll(bookCategoryTMs);
            bookCategoryTMs.clear();

            for (BookCategoryDTO bookCategoryDTO : bookCategoryDTOs) {
                bookCategoryTMs.add(new BookCategoryTM(
                        bookCategoryDTO.getBookCategoryCode(),
                        bookCategoryDTO.getCategoryName(),
                        bookCategoryDTO.getOtherDetails()
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
