package hu.petrik.calculator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Objects;


public class CalculatorController {
    @FXML
    private Label resultLabel;

    @FXML
    private TextField input1;

    @FXML
    private TextField input2;

    @FXML
    private void initialize() {

        input1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*\\.?\\d*")) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setContentText("Csak számot adhat meg!");
                    alert.showAndWait();
                    input1.setText(input1.getText().substring(0, input1.getText().length() - 1));
                }
            }
        });
        input2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*\\.?\\d*")) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setContentText("Csak számot adhat meg!");
                    alert.showAndWait();
                    input2.setText(input2.getText().substring(0, input2.getText().length() - 1));
                }
            }
        });
    }

    @FXML
    protected void onOperationClick(ActionEvent event) {
        String Bid = ((Button)event.getSource()).getId();
        if(Objects.equals(input1.getText(), "") || Objects.equals(input2.getText(), "")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Mind a kettő bemeneti mezőben kell számnak szerepelnie!");
            alert.showAndWait();
        }else{
            Double n1 = Double.parseDouble(input1.getText());
            Double n2 = Double.parseDouble(input2.getText());
            switch (Bid) {
                case "plusB":
                    resultLabel.setText(String.valueOf(n1 + n2));
                    break;
                case "minusB":
                    resultLabel.setText(String.valueOf((double)Math.round((n1 - n2) * 100) / 100));
                    break;
                case "multB":
                    resultLabel.setText(String.valueOf((double)Math.round(n1 * n2 * 100) / 100));
                    break;
                case "divB":
                    resultLabel.setText(String.valueOf((double) Math.round(n1 / n2 * 100) / 100));
                    break;
                case "modB":
                    resultLabel.setText(String.valueOf((double)Math.round(n1 % n2 * 10) / 10));
                    break;
                default:
                    Alert a = new Alert( Alert.AlertType.ERROR );
                    a.setTitle( "Problem with the operator button" );
                    a.setContentText("Id of button "+Bid+" not found in switch!");
                    a.showAndWait();
                    break;
        }

        }

    }
}