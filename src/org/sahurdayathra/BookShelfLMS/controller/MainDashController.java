package org.sahurdayathra.BookShelfLMS.controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class MainDashController implements Initializable {

    @FXML
    private AnchorPane mainDashAnchPane;
    @FXML
    private JFXHamburger mainDashHamburger;
    @FXML
    private Label timeTxt;
    @FXML
    private Label dateTxt;
    @FXML
    private JFXDrawer mainDashDrawer;
    @FXML
    private ImageView memberIcon;
    @FXML
    private ImageView userIcon;
    @FXML
    private ImageView booksIcon;
    @FXML
    private ImageView issuedReturnedBookIcon;
    @FXML
    private ImageView issueReturnBookIcon;
    @FXML
    private Label titleLbl;
    @FXML
    private Label descriptionLbl;

    public static boolean isFirstTime = true;

    public static boolean isShownUserProfileWindow = false;
    public static boolean isShownNotificationWindow = false;
    public static boolean isShownViewMoreWindow = false;
    public static boolean isShownSettingsWindow = false;
    public static boolean isShownHelpWindow = false;
    public static boolean isShownAboutWindow = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.gc();

        titleLbl.setText("Welcome");
        descriptionLbl.setText("Please select one of above main operations to proceed");

        if (isFirstTime) {
            FadeTransition trans = new FadeTransition(Duration.millis(800), mainDashAnchPane);
            trans.setFromValue(0.0);
            trans.setToValue(1.00);
            trans.play();

            if (LogInController.loggedUser.getState().equals("superadmin")) {
                Alert alert = new Alert(
                        Alert.AlertType.NONE,
                        "\n Hi, Thushara ; "
                        + "\n\n Welcome to Book Shelf LMS."
                        + "\n You are the Super Admin of this System.",
                        ButtonType.OK
                );
                alert.setTitle("Welcome !");
                alert.show();
            } else {
                Alert alert = new Alert(
                        Alert.AlertType.NONE,
                        "\n Hi, " + LogInController.loggedUser.getName() + " ; "
                        + "\n\n Welcome to Book Shelf LMS."
                        + "\n Visit 'Help' or 'About' for More Details...",
                        ButtonType.OK
                );
                alert.setTitle("Welcome !");
                alert.show();
            }

            isFirstTime = false;
        }

        Timeline dateNtime = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeTxt.setText(LocalTime.now().toString().substring(0, 8));
            dateTxt.setText(LocalDate.now().toString() + " " + LocalDate.now().getDayOfWeek().toString().substring(0, 3));
        }));

        dateNtime.setCycleCount(Animation.INDEFINITE);
        dateNtime.play();

        try {
            Pane drawerPane = FXMLLoader.load(getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/mainDashDrawer.fxml"));
            mainDashDrawer.setSidePane(drawerPane);

            for (Node node : drawerPane.getChildren()) {
                if (node.getAccessibleText() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                        switch (node.getAccessibleText()) {

                            case "userProfileHBox": {
                                try {
                                    loadUserProfileHBoxAction();
                                } catch (IOException ex) {
                                    printException(ex);
                                }
                            }
                            break;

                            case "notificationHBox": {
                                try {
                                    loadNotificationsHBoxAction();
                                } catch (IOException ex) {
                                    printException(ex);
                                }
                            }
                            break;

                            case "viewMoreHBox": {
                                try {
                                    loadViewMoreHBoxAction();
                                } catch (IOException ex) {
                                    printException(ex);
                                }
                            }
                            break;

                            case "logoutHBox": {
                                try {
                                    loadLogoutHBoxAction();
                                } catch (IOException ex) {
                                    printException(ex);
                                }
                            }
                            break;

                            case "exitHBox":
                                loadExitHBoxAction();
                                break;

                            case "settingsHBox": {
                                try {
                                    loadSettingsHBoxAction();
                                } catch (IOException ex) {
                                    printException(ex);
                                }
                            }
                            break;

                            case "helpHBox": {
                                try {
                                    loadHelpHBoxAction();
                                } catch (IOException ex) {
                                    printException(ex);
                                }
                            }
                            break;

                            case "aboutHBox": {
                                try {
                                    loadAboutHBoxAction();
                                } catch (IOException ex) {
                                    printException(ex);
                                }
                            }
                            break;
                        }
                    });
                }
            }

            HamburgerBackArrowBasicTransition hambtrans = new HamburgerBackArrowBasicTransition(mainDashHamburger);
            hambtrans.setRate(-1);
            mainDashHamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (evt) -> {
                hambtrans.setRate(hambtrans.getRate() * -1);
                hambtrans.play();

                if (mainDashDrawer.isShown()) {
                    mainDashDrawer.close();

                    memberIcon.setDisable(false);
                    booksIcon.setDisable(false);
                    issueReturnBookIcon.setDisable(false);
                    issuedReturnedBookIcon.setDisable(false);
                    userIcon.setDisable(false);

                    FadeTransition trans1 = new FadeTransition(Duration.millis(600), memberIcon);
                    trans1.setFromValue(0.4);
                    trans1.setToValue(1.0);
                    trans1.play();

                    FadeTransition trans2 = new FadeTransition(Duration.millis(600), booksIcon);
                    trans2.setFromValue(0.4);
                    trans2.setToValue(1.0);
                    trans2.play();

                    FadeTransition trans3 = new FadeTransition(Duration.millis(600), issueReturnBookIcon);
                    trans3.setFromValue(0.4);
                    trans3.setToValue(1.0);
                    trans3.play();

                    FadeTransition trans4 = new FadeTransition(Duration.millis(600), issuedReturnedBookIcon);
                    trans4.setFromValue(0.4);
                    trans4.setToValue(1.0);
                    trans4.play();

                    FadeTransition trans5 = new FadeTransition(Duration.millis(600), userIcon);
                    trans5.setFromValue(0.4);
                    trans5.setToValue(1.0);
                    trans5.play();

                    FadeTransition trans6 = new FadeTransition(Duration.millis(600), titleLbl);
                    trans6.setFromValue(0.4);
                    trans6.setToValue(1.0);
                    trans6.play();

                    FadeTransition trans7 = new FadeTransition(Duration.millis(600), descriptionLbl);
                    trans7.setFromValue(0.4);
                    trans7.setToValue(1.0);
                    trans7.play();
                } else {
                    mainDashDrawer.open();

                    FadeTransition trans1 = new FadeTransition(Duration.millis(600), memberIcon);
                    trans1.setFromValue(1.0);
                    trans1.setToValue(0.4);
                    trans1.play();

                    FadeTransition trans2 = new FadeTransition(Duration.millis(600), booksIcon);
                    trans2.setFromValue(1.0);
                    trans2.setToValue(0.4);
                    trans2.play();

                    FadeTransition trans3 = new FadeTransition(Duration.millis(600), issueReturnBookIcon);
                    trans3.setFromValue(1.0);
                    trans3.setToValue(0.4);
                    trans3.play();

                    FadeTransition trans4 = new FadeTransition(Duration.millis(600), issuedReturnedBookIcon);
                    trans4.setFromValue(1.0);
                    trans4.setToValue(0.4);
                    trans4.play();

                    FadeTransition trans5 = new FadeTransition(Duration.millis(600), userIcon);
                    trans5.setFromValue(1.0);
                    trans5.setToValue(0.4);
                    trans5.play();

                    FadeTransition trans6 = new FadeTransition(Duration.millis(600), titleLbl);
                    trans6.setFromValue(1.0);
                    trans6.setToValue(0.4);
                    trans6.play();

                    FadeTransition trans7 = new FadeTransition(Duration.millis(600), descriptionLbl);
                    trans7.setFromValue(1.0);
                    trans7.setToValue(0.4);
                    trans7.play();

                    memberIcon.setDisable(true);
                    booksIcon.setDisable(true);
                    issueReturnBookIcon.setDisable(true);
                    issuedReturnedBookIcon.setDisable(true);
                    userIcon.setDisable(true);
                }
            });
        } catch (Exception ex) {
            printException(ex);
        }

    }

    private void loadUserProfileHBoxAction() throws IOException {
        if (!isShownUserProfileWindow) {
            isShownUserProfileWindow = true;
            Parent root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/userProfileWindow.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("User Profile");
            stage.getIcons().add(new Image("org/sahurdayathra/BookShelfLMS/asset/BookShelf_png.png"));
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.initOwner(mainDashAnchPane.getScene().getWindow());
            stage.setOnCloseRequest((WindowEvent event) -> {
                isShownUserProfileWindow = false;
            });
            stage.show();

            TranslateTransition trans = new TranslateTransition(Duration.millis(350), scene.getRoot());
            trans.setFromY(-scene.getHeight());
            trans.setToY(0);
            trans.play();
        }
    }

    private void loadNotificationsHBoxAction() throws IOException {
        if (!isShownNotificationWindow) {
            isShownNotificationWindow = true;
            Parent root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/notificationWindow.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Notification");
            stage.getIcons().add(new Image("org/sahurdayathra/BookShelfLMS/asset/BookShelf_png.png"));
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.initOwner(mainDashAnchPane.getScene().getWindow());
            stage.setOnCloseRequest((WindowEvent event) -> {
                isShownNotificationWindow = false;
            });
            stage.show();

            TranslateTransition trans = new TranslateTransition(Duration.millis(350), scene.getRoot());
            trans.setFromY(-scene.getHeight());
            trans.setToY(0);
            trans.play();
        }
    }

    private void loadViewMoreHBoxAction() throws IOException {
        if (!isShownViewMoreWindow) {
            isShownViewMoreWindow = true;
            Parent root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/viewMoreWindow.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("View More");
            stage.getIcons().add(new Image("org/sahurdayathra/BookShelfLMS/asset/BookShelf_png.png"));
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.initOwner(mainDashAnchPane.getScene().getWindow());
            stage.setOnCloseRequest((WindowEvent event) -> {
                isShownViewMoreWindow = false;
            });
            stage.show();

            TranslateTransition trans = new TranslateTransition(Duration.millis(350), scene.getRoot());
            trans.setFromY(-scene.getHeight());
            trans.setToY(0);
            trans.play();
        }
    }

    private void loadLogoutHBoxAction() throws IOException {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are You Sure to Logout?",
                ButtonType.OK,
                ButtonType.NO
        );
        alert.setTitle("Logout ?");
        Button yesButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        yesButton.setDefaultButton(false);
        Button noButton = (Button) alert.getDialogPane().lookupButton(ButtonType.NO);
        noButton.setDefaultButton(true);
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            isFirstTime = true;
            Parent root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/logIn.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) this.mainDashAnchPane.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

            TranslateTransition trans = new TranslateTransition(Duration.millis(300), scene.getRoot());
            trans.setFromY(-scene.getHeight());
            trans.setToY(0);
            trans.play();
        }
    }

    private void loadExitHBoxAction() {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are You Sure to Exit ?",
                ButtonType.OK,
                ButtonType.NO
        );
        alert.setTitle("Exit ?");
        Button yesButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        yesButton.setDefaultButton(false);
        Button noButton = (Button) alert.getDialogPane().lookupButton(ButtonType.NO);
        noButton.setDefaultButton(true);
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }
    }

    private void loadSettingsHBoxAction() throws IOException {
        if (!isShownSettingsWindow) {
            isShownSettingsWindow = true;
            Parent root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/settingsWindow.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Settings");
            stage.getIcons().add(new Image("org/sahurdayathra/BookShelfLMS/asset/BookShelf_png.png"));
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.initOwner(mainDashAnchPane.getScene().getWindow());
            stage.setOnCloseRequest((WindowEvent event) -> {
                isShownSettingsWindow = false;
            });
            stage.show();

            TranslateTransition trans = new TranslateTransition(Duration.millis(300), scene.getRoot());
            trans.setFromY(-scene.getHeight());
            trans.setToY(0);
            trans.play();
        }
    }

    private void loadHelpHBoxAction() throws IOException {
        if (!isShownHelpWindow) {
            isShownHelpWindow = true;
            Parent root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/helpWindow.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Help");
            stage.getIcons().add(new Image("org/sahurdayathra/BookShelfLMS/asset/BookShelf_png.png"));
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.initOwner(mainDashAnchPane.getScene().getWindow());
            stage.setOnCloseRequest((WindowEvent event) -> {
                isShownHelpWindow = false;
            });
            stage.show();

            TranslateTransition trans = new TranslateTransition(Duration.millis(300), scene.getRoot());
            trans.setFromY(-scene.getHeight());
            trans.setToY(0);
            trans.play();
        }
    }

    private void loadAboutHBoxAction() throws IOException {
        if (!isShownAboutWindow) {
            isShownAboutWindow = true;
            Parent root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/aboutWindow.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("About");
            stage.getIcons().add(new Image("org/sahurdayathra/BookShelfLMS/asset/BookShelf_png.png"));
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.initOwner(mainDashAnchPane.getScene().getWindow());
            stage.setOnCloseRequest((WindowEvent event) -> {
                isShownAboutWindow = false;
            });
            stage.show();

            TranslateTransition trans = new TranslateTransition(Duration.millis(300), scene.getRoot());
            trans.setFromY(-scene.getHeight());
            trans.setToY(0);
            trans.play();
        }
    }

    @FXML
    private void mainDashIcon_onMouseEntered(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            switch (icon.getId()) {
                case "memberIcon":
                    titleLbl.setText("Manage Members");
                    descriptionLbl.setText("Click to add, edit, delete, search or view members");
                    break;
                case "booksIcon":
                    titleLbl.setText("Manage Books");
                    descriptionLbl.setText("Click to add, edit, delete, search or view books");
                    break;
                case "issueReturnBookIcon":
                    titleLbl.setText("Issue / Return Books");
                    descriptionLbl.setText("Click to manage issue / return books");
                    break;
                case "issuedReturnedBookIcon":
                    titleLbl.setText("Issued / Returned Books");
                    descriptionLbl.setText("Click to view & manage issued / returned books");
                    break;
                case "userIcon":
                    titleLbl.setText("Manage Users");
                    descriptionLbl.setText("Click to add, edit, delete, search or view users of this system");
                    break;
            }

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.rgb(0, 153, 204));
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

    @FXML
    private void mainDashIcon_onMouseExited(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();
            icon.setEffect(null);

            titleLbl.setText("Welcome");
            descriptionLbl.setText("Please select one of above main operations to proceed");
        }
    }

    @FXML
    private void mainDashIcon_onMouseClicked(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            try {
                ImageView icon = (ImageView) event.getSource();

                Parent root = null;

                switch (icon.getId()) {
                    case "memberIcon":
                        root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/memberManagement.fxml"));
                        break;
                    case "booksIcon":
                        root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/bookManagement.fxml"));
                        break;
                    case "issueReturnBookIcon":
                        root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/issueReturnBookManagement.fxml"));
                        break;
                    case "issuedReturnedBookIcon":
                        root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/issuedReturnedBookManagement.fxml"));
                        break;
                    case "userIcon":
                        if (LogInController.loggedUser.getState().equals("admin") | LogInController.loggedUser.getState().equals("superadmin")) {
                            root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/userManagement.fxml"));
                        } else {
                            root = null;
                            Alert alert = new Alert(
                                    Alert.AlertType.NONE,
                                    "\n Only Admins of this System Can Access User Accounts and Modify Accounts. \n",
                                    ButtonType.OK
                            );
                            alert.setTitle("Access Denied !");
                            alert.show();
                        }
                        break;
                }

                if (root != null) {
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) this.mainDashAnchPane.getScene().getWindow();
                    stage.setScene(scene);
                    stage.centerOnScreen();

                    TranslateTransition trans = new TranslateTransition(Duration.millis(300), scene.getRoot());
                    trans.setFromX(-scene.getHeight());
                    trans.setToX(0);
                    trans.play();

                }
            } catch (IOException ex) {
                printException(ex);
            }
        }
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
