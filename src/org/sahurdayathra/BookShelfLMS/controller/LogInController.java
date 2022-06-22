package org.sahurdayathra.BookShelfLMS.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.sahurdayathra.BookShelfLMS.business.BOFactory;
import org.sahurdayathra.BookShelfLMS.business.custom.LogInBO;
import org.sahurdayathra.BookShelfLMS.dto.UserDTO;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class LogInController implements Initializable {

    @FXML
    private AnchorPane logInAnchPane;
    @FXML
    private JFXTextField usernameTF;
    @FXML
    private JFXPasswordField passwordPF;
    @FXML
    private Label errorMessageLbl;
    @FXML
    private JFXButton logInBtn;

    public static String loggedDateTime;

    public static UserDTO loggedUser;

    LogInBO logInBO = (LogInBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.LOGIN);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.gc();

        try {
            boolean result = logInBO.initializeDBTables();
            if (!result) {
                Alert alert = new Alert(
                        Alert.AlertType.ERROR,
                        " Failed to Initialize the Data Base Tables."
                        + "\n\n Please Contact Developer of this System to Resolve this Error."
                        + "\n Visit 'Help' or 'About' for More Details...",
                        ButtonType.OK
                );
                alert.setTitle("Initialization Failed!");
                alert.show();
            }
        } catch (Exception ex) {
            printException(ex);
        }

    }

    @FXML
    private void usernameTF_onAction(ActionEvent event) {
        passwordPF.requestFocus();
    }

    @FXML
    private void passwordPF_onAction(ActionEvent event) {
        logInBtn.fire();
    }

    @FXML
    private void logInBtn_onAction(ActionEvent event) {
        try {

            String username = usernameTF.getText();
            String password = passwordPF.getText();

            UserDTO userDTO = logInBO.getLoggedUser(username, password);

            if (userDTO != null) {
                loggedDateTime = new SimpleDateFormat("dd-MM-yyyy   HH:mm:ss").format(Calendar.getInstance().getTime());
                loggedUser = userDTO;

                Parent root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/mainDash.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) this.logInAnchPane.getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
            } else {
                errorMessageLbl.setText("The Username or Password you \n entered  is incorrect.");
                usernameTF.setText("");
                passwordPF.setText("");
                usernameTF.requestFocus();
            }

        } catch (Exception ex) {
            printException(ex);
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
