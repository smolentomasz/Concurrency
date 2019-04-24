package concurrency.controller;

import concurrency.DrawerTask;
import concurrency.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;


public class MainViewController {
    private DrawerTask task;
    private double var;
    @FXML
    private ProgressBar calculationsProgress;

    public static TextField getPointsLabel() {
        return pointsLabel;
    }

    @FXML
    private static TextField pointsLabel;

    @FXML
    private static TextField resultLabel;

    @FXML
    private void handleRunBtnAction(){
        calculationsProgress = (ProgressBar) Main.getMainScene().lookup("#calculationsProgress");
        pointsLabel = (TextField) Main.getMainScene().lookup("#pointsLabel");
        resultLabel = (TextField) Main.getMainScene().lookup("#resultLabel");
        if(task != null)
            task.cancel();
        task = new DrawerTask();
        calculationsProgress.progressProperty().bind(task.progressProperty());
        task.setOnSucceeded(event -> {
            var = task.getValue();
            resultLabel.setText(String.format("%.2f", var));
        });
        Platform.runLater(() -> new Thread(task).start());
    }
    @FXML
    private void handleStopBtnAction(){
        task.cancel();
    }

}
