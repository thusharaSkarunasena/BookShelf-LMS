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
import org.sahurdayathra.BookShelfLMS.business.custom.DamagedBookBO;
import org.sahurdayathra.BookShelfLMS.dto.BookDTO;
import org.sahurdayathra.BookShelfLMS.dto.DamagedBookDTO;
import org.sahurdayathra.BookShelfLMS.view.util.tblmodel.DamagedBookTM;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class DamagedBookController implements Initializable {

    @FXML
    private AnchorPane damagedBookAnchPane;
    @FXML
    private HBox homeHBox;
    @FXML
    private HBox helpHBox;
    @FXML
    private HBox viewMoreHBox;
    @FXML
    private HBox backHBox;
    @FXML
    private JFXComboBox<String> bookCategoryComBox;
    @FXML
    private JFXTextField bookCode_catTxt_TF;
    @FXML
    private JFXTextField ISBNTF;
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
    private JFXComboBox<String> bookPublisherComBox;
    @FXML
    private JFXTextField bookCode_catNum_TF;
    @FXML
    private JFXTextField bookCode_bookNum_TF;
    @FXML
    private Label bookStatusLbl2;
    @FXML
    private Label bookStatusLbl1;
    @FXML
    private TextField searchBoxTF;
    @FXML
    private TableView<DamagedBookTM> damagedBookDetailsTbl;
    @FXML
    private Label countLbl;
    @FXML
    private Pane btnPane;
    @FXML
    private JFXButton moveBtn;
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private JFXButton clearBtn;

    ObservableList<DamagedBookTM> damagedBookTMs;

    DamagedBookBO damagedBookBO = (DamagedBookBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DAMAGEDBOOK);
    BookBO bookBO = (BookBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOK);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.gc();

        damagedBookDetailsTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        damagedBookDetailsTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        damagedBookDetailsTbl.getColumns().get(2).setStyle("-fx-alignment:center");
        damagedBookDetailsTbl.getColumns().get(3).setStyle("-fx-alignment:center");
        damagedBookDetailsTbl.getColumns().get(4).setStyle("-fx-alignment:center");
        damagedBookDetailsTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("bookCode"));
        damagedBookDetailsTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        damagedBookDetailsTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("bookCategory"));
        damagedBookDetailsTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("authorName"));
        damagedBookDetailsTbl.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("publisher"));

        clearBtn.fire();

        if (!(LogInController.loggedUser.getState().equals("superadmin") | LogInController.loggedUser.getState().equals("admin"))) {
            Alert alert = new Alert(
                    Alert.AlertType.NONE,
                    "\n Only Admins of this System can Move Damage Books to Book or Delete Damaged Books Forever. \n",
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
            Stage stage = (Stage) this.damagedBookAnchPane.getScene().getWindow();
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
            Stage stage = (Stage) this.damagedBookAnchPane.getScene().getWindow();
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
                stage.initOwner(damagedBookAnchPane.getScene().getWindow());
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
                stage.initOwner(damagedBookAnchPane.getScene().getWindow());
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

    private void loadAllDamagedBookDetails() {
        new Thread(() -> {
            try {
                ArrayList<DamagedBookDTO> damagedBookDTOs = damagedBookBO.getAllDamagedBooks();
                loadDamagedBookDetailsTbl(damagedBookDTOs);
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
                    loadAllDamagedBookDetails();
                } else {
                    String searchText = searchBoxTF.getText();

                    ArrayList<DamagedBookDTO> damagedBookDTOs = damagedBookBO.searchDamagedBook(searchText);

                    if (damagedBookDTOs.isEmpty()) {
                        Platform.runLater(() -> {
                            searchBoxTF.setStyle("-fx-text-fill: #D91022");
                        });
                        loadAllDamagedBookDetails();
                    } else {
                        Platform.runLater(() -> {
                            searchBoxTF.setStyle("-fx-text-fill: #000000");
                        });
                        loadDamagedBookDetailsTbl(damagedBookDTOs);
                    }
                }

            } catch (Exception ex) {
                printException(ex);
            }
        }).start();
    }

    @FXML
    private void damagedBookDetailsTbl_onMouseClicked(MouseEvent event) {
        new Thread(() -> {
            try {

                DamagedBookTM damagedBookTM = damagedBookDetailsTbl.getSelectionModel().getSelectedItem();

                if (damagedBookTM != null) {
                    String bookCode = damagedBookTM.getBookCode();

                    BookDTO bookDTO = bookBO.getBook(bookCode);

                    Platform.runLater(() -> {
                        try {
                            String bCode = bookDTO.getBookCode();
                            bookCode_catTxt_TF.setText(bCode.substring(0, 3));
                            bookCode_catNum_TF.setText(bCode.substring(4, 7));
                            bookCode_bookNum_TF.setText(bCode.substring(8, 11));
                            ISBNTF.setText(bookDTO.getIsbnNO());
                            bookTitleTF.setText(bookDTO.getBookTitle());
                            bookEditionTF.setText(bookDTO.getBookEdition());
                            bookCategoryComBox.setValue(bookDTO.getBookCategoryCode() + " - " + bookBO.getBookCategoryName(bookDTO.getBookCategoryCode()));
                            authorNameTF.setText(bookDTO.getAuthorName());
                            bookPublisherComBox.setValue(bookDTO.getPublisherID() + " - " + bookBO.getBookPublisherName(bookDTO.getPublisherID()));
                            priceTF.setText(Double.toString(bookDTO.getPrice()));
                            otherDetailsTA.setText(bookDTO.getOtherDetails());

                            bookStatusLbl1.setVisible(true);
                            bookStatusLbl2.setVisible(true);

                            bookStatusLbl2.setText(" Book Damaged. ");

                            clearBtn.setDisable(false);

                            if (LogInController.loggedUser.getState().equals("admin") | LogInController.loggedUser.getState().equals("superadmin")) {
                                moveBtn.setDisable(false);
                                deleteBtn.setDisable(false);

                            } else {
                                moveBtn.setDisable(true);
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
    private void moveBtn_onAction(ActionEvent event) {
        try {

            String bookCode = bookCode_catTxt_TF.getText() + "." + bookCode_catNum_TF.getText() + "." + bookCode_bookNum_TF.getText();

            boolean result = false;

            Alert alert = new Alert(
                    Alert.AlertType.CONFIRMATION,
                    "Are You Sure to Move Damaged Book " + bookCode + " to Books?",
                    ButtonType.OK,
                    ButtonType.NO
            );
            alert.setTitle("Move to Books ?");
            Button yesButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            yesButton.setDefaultButton(false);
            Button noButton = (Button) alert.getDialogPane().lookupButton(ButtonType.NO);
            noButton.setDefaultButton(true);
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                BookDTO bookDTO = bookBO.getBook(bookCode);
                bookDTO.setStatus("MEM.000000");
                result = bookBO.updateBook(bookDTO);

                if (result) {
                    new Alert(
                            Alert.AlertType.INFORMATION,
                            "Damaged Book '" + bookCode + "' has been Moved to Books Successfully.",
                            ButtonType.OK
                    ).show();
                    clearBtn.fire();
                } else {
                    new Alert(
                            Alert.AlertType.ERROR,
                            "Failed to Move the Book '" + bookCode + "'.",
                            ButtonType.OK
                    ).show();
                }
            }

        } catch (Exception ex) {
            printException(ex);
        }
    }

    @FXML
    private void deleteBtn_onAction(ActionEvent event) {
        Platform.runLater(() -> {

            damagedBookTMs = damagedBookDetailsTbl.getItems();

            try {
                String bookCode = bookCode_catTxt_TF.getText() + "." + bookCode_catNum_TF.getText() + "." + bookCode_bookNum_TF.getText();

                boolean result = false;

                Alert alert = new Alert(
                        Alert.AlertType.CONFIRMATION,
                        "Are You Sure to Delete Damaged Book " + bookCode + " ?",
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
                                "Damaged Book '" + bookCode + "' has been Deleted Successfully.",
                                ButtonType.OK
                        ).show();
                        clearBtn.fire();
                    } else {
                        new Alert(
                                Alert.AlertType.ERROR,
                                "Failed to Delete the Damaged Book '" + bookCode + "'.",
                                ButtonType.OK
                        ).show();
                    }
                }

            } catch (Exception ex) {
                printException(ex);
            }

        });
    }

    @FXML
    private void clearBtn_onAction(ActionEvent event) {

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

        loadAllDamagedBookDetails();

        bookCode_catTxt_TF.requestFocus();

        Platform.runLater(() -> {
            if (LogInController.loggedUser.getState().equals("admin") | LogInController.loggedUser.getState().equals("superadmin")) {
                clearBtn.setDisable(false);
                moveBtn.setDisable(true);
                deleteBtn.setDisable(true);
            } else {
                clearBtn.setDisable(false);
                moveBtn.setDisable(true);
                deleteBtn.setDisable(true);
            }
        });

    }

    private void loadDamagedBookDetailsTbl(ArrayList<DamagedBookDTO> damagedBookDTOs) {

        Platform.runLater(() -> {
            try {
                countLbl.setText(Integer.toString(damagedBookDTOs.size()));

                damagedBookTMs = damagedBookDetailsTbl.getItems();
                damagedBookDetailsTbl.getItems().clear();

                damagedBookTMs.removeAll(damagedBookTMs);
                damagedBookTMs.clear();

                for (DamagedBookDTO dmgBookDto : damagedBookDTOs) {
                    damagedBookTMs.add(new DamagedBookTM(
                            dmgBookDto.getBookCode(),
                            dmgBookDto.getBookTitle(),
                            bookBO.getBookCategoryName(dmgBookDto.getBookCategoryCode()),
                            dmgBookDto.getAuthorName(),
                            bookBO.getBookPublisherName(dmgBookDto.getPublisherID())
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
