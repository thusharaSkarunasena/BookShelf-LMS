package org.sahurdayathra.BookShelfLMS.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.sahurdayathra.BookShelfLMS.db.DBConnection;

/**
 * FXML Controller class
 *
 * @author Thushara Supun
 */
public class LoadingController implements Initializable {
    
    @FXML
    private AnchorPane loadingAnchPane;
    @FXML
    private Label copyrightLbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        System.gc();
        
        String currentYear = Integer.toString(LocalDate.now().getYear());
        copyrightLbl.setText("Copyright       " + currentYear + " Sahurda Yathra");
        
        try {
            Connection connection = DBConnection.getInstance().getConnection();
        } catch (Exception ex) {
            printException(ex);
        }
        
        applyFadeTransition(loadingAnchPane, Duration.seconds(3), (evt) -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/logIn.fxml"));
                Scene temp = new Scene(root);
                Stage stage = (Stage) this.loadingAnchPane.getScene().getWindow();
                stage.setScene(temp);
                stage.centerOnScreen();
                
                TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
                trans.setFromY(-temp.getHeight());
                trans.setToY(0);
                trans.play();
                
            } catch (IOException ex) {
                printException(ex);
            }
        });
        
    }
    
    public void applyFadeTransition(Node node, Duration duration, EventHandler<ActionEvent> event) {
        javafx.animation.FadeTransition fadeIn = new javafx.animation.FadeTransition(duration, node);
        fadeIn.setCycleCount(1);
        fadeIn.setFromValue(0.8);
        fadeIn.setToValue(1);
        fadeIn.setAutoReverse(true);
        fadeIn.setOnFinished(event);
        
        javafx.animation.FadeTransition fadeOut = new javafx.animation.FadeTransition(duration, node);
        fadeOut.setCycleCount(1);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0.8);
        fadeOut.setAutoReverse(true);
        
        fadeOut.play();
        fadeOut.setOnFinished((evt) -> {
            fadeIn.play();
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
