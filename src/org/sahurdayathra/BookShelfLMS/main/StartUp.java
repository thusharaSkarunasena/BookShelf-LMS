package org.sahurdayathra.BookShelfLMS.main;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 *
 * @author Thushara Supun
 */
public class StartUp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

//        try {
//            final String folderPath = "../BookShelfLMS/src/org/sahurdayathra/BookShelfLMS/";
//
//            long totalLineCount = 0;
//            final List<File> folderList = new LinkedList<>();
//            folderList.add(new File(folderPath));
//            while (!folderList.isEmpty()) {
//                final File folder = folderList.remove(0);
//                if (folder.isDirectory() && folder.exists()) {
//                    System.out.println("Scanning " + folder.getName());
//                    final File[] fileList = folder.listFiles();
//                    for (final File file : fileList) {
//                        if (file.isDirectory()) {
//                            folderList.add(file);
//                        } else if (file.getName().endsWith(".java")
//                                || file.getName().endsWith(".sql")
//                                || file.getName().endsWith(".css")) {
//                            long lineCount = 0;
//                            final Scanner scanner = new Scanner(file);
//                            while (scanner.hasNextLine()) {
//                                scanner.nextLine();
//                                lineCount++;
//                            }
//                            totalLineCount += lineCount;
//                            final String lineCountString;
//                            if (lineCount > 99999) {
//                                lineCountString = "" + lineCount;
//                            } else {
//                                final String temp = ("     " + lineCount);
//                                lineCountString = temp.substring(temp.length() - 5);
//                            }
//                            System.out.println(lineCountString + " lines in " + file.getName());
//                        }
//                    }
//                }
//            }
//            System.out.println("Scan Complete: " + totalLineCount + " lines total");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        
        Parent root = FXMLLoader.load(this.getClass().getResource("/org/sahurdayathra/BookShelfLMS/view/loading.fxml"));
        Scene temp = new Scene(root);
        primaryStage.setScene(temp);
        primaryStage.getIcons().add(new Image("org/sahurdayathra/BookShelfLMS/asset/BookShelf_png.png"));
        primaryStage.setTitle(" BookShelf LMS ~ Sahurda Yathra ");
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.setOnCloseRequest((WindowEvent event) -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure to Exit ?", ButtonType.OK, ButtonType.NO);
            alert.setTitle("Exit ?");
            Button yesButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            yesButton.setDefaultButton(false);
            Button noButton = (Button) alert.getDialogPane().lookupButton(ButtonType.NO);
            noButton.setDefaultButton(true);
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                Platform.exit();
                System.exit(0);
            } else {
                event.consume();
            }
        });

        FadeTransition trans = new FadeTransition(Duration.millis(1000), root);
        trans.setFromValue(0.02);
        trans.setToValue(1);
        trans.play();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
