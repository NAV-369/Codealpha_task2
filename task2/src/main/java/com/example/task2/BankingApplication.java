package com.example.task2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BankingApplication extends Application {
    private double balance = 0.0;

    @Override
    public void start(Stage stage) {
        // Create UI Elements
        Label balanceLabel = new Label("Current Balance: $" + balance);
        TextField amountField = new TextField();
        amountField.setPromptText("Enter amount");

        Button depositButton = new Button("Deposit");
        Button withdrawButton = new Button("Withdraw");
        Button checkBalanceButton = new Button("Check Balance");
        Button exitButton = new Button("Exit");

        // Set Button Actions
        depositButton.setOnAction(e -> {
            String input = amountField.getText();
            try {
                double depositAmount = Double.parseDouble(input);
                if (depositAmount > 0) {
                    balance += depositAmount;
                    balanceLabel.setText("Current Balance: $" + balance);
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Successfully deposited: $" + depositAmount);
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Invalid amount. Please enter a positive value.");
                }
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Error", "Invalid input. Please enter a numeric value.");
            }
            amountField.clear();
        });

        withdrawButton.setOnAction(e -> {
            String input = amountField.getText();
            try {
                double withdrawAmount = Double.parseDouble(input);
                if (withdrawAmount > 0 && withdrawAmount <= balance) {
                    balance -= withdrawAmount;
                    balanceLabel.setText("Current Balance: $" + balance);
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Successfully withdrew: $" + withdrawAmount);
                } else if (withdrawAmount > balance) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Insufficient balance. You only have: $" + balance);
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Invalid amount. Please enter a positive value.");
                }
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Error", "Invalid input. Please enter a numeric value.");
            }
            amountField.clear();
        });

        checkBalanceButton.setOnAction(e -> {
            showAlert(Alert.AlertType.INFORMATION, "Balance", "Your current balance is: $" + balance);
        });

        exitButton.setOnAction(e -> {
            stage.close();
        });

        // Layout
        VBox vbox = new VBox(10, balanceLabel, amountField, depositButton, withdrawButton, checkBalanceButton, exitButton);
        vbox.setPadding(new Insets(20));
        vbox.setSpacing(10);

        // Scene
        Scene scene = new Scene(vbox, 300, 300);
        stage.setTitle("Banking Application");
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}
