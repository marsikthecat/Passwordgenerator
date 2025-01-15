package com.example.passwortgenerator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/***
 * <p> </p>
 * Passwordgenerator.
 */

public class Main extends Application {

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) {
    Slider lengthSlider = new Slider(0, 30, 15);
    lengthSlider.setShowTickLabels(true);
    lengthSlider.setShowTickMarks(true);
    lengthSlider.setMajorTickUnit(10);
    lengthSlider.setMinorTickCount(5);
    lengthSlider.setBlockIncrement(1);

    Label selectedAgeLabel = new Label("Länge des Passworts: " + (int) lengthSlider.getValue());
    lengthSlider.valueProperty().addListener((observable, oldValue, newValue)
            -> selectedAgeLabel.setText("Gewählte Länge: " + newValue.intValue()));

    CheckBox numbers = new CheckBox("Mit Zahlen");
    CheckBox symbols = new CheckBox("Mit Sonderzeichen");

    Button btn = new Button("Passwort erzeugen");
    TextField field = new TextField();
    btn.setOnAction(e -> generatePasswort(field, (int) lengthSlider.getValue(),
            numbers.isSelected(), symbols.isSelected()));

    Button copybtn = new Button("Passwort kopieren");
    style(btn, copybtn);
    Label l = new Label("Passwort kopiert");
    l.setVisible(false);
    copybtn.setOnAction(e -> copypasswort(field, l));

    HBox btnbox = new HBox(5);
    btn.setOnMouseClicked(e -> {
      if (l.isVisible()) {
        l.setVisible(false);
      }
    });

    btnbox.setPadding(new Insets(5, 0, 0, 0));
    btnbox.setAlignment(Pos.CENTER);
    btnbox.getChildren().addAll(btn, copybtn);
    VBox content = new VBox(5);
    content.getChildren().addAll(lengthSlider, selectedAgeLabel,
            numbers, symbols, field, btnbox, l);
    content.setPadding(new Insets(10));

    Scene scene = new Scene(content, 320, 215);
    stage.setResizable(false);
    stage.setTitle("Passwortgenerator");
    stage.setScene(scene);
    stage.show();
  }

  private void generatePasswort(TextField field, int l, boolean z, boolean s) {
    Password password = new Password(l, z, s);
    field.setText(password.getPassword());
  }

  private void copypasswort(TextField f, Label l) {
    final Clipboard clipboard = Clipboard.getSystemClipboard();
    final ClipboardContent content = new ClipboardContent();
    content.putString(f.getText());
    clipboard.setContent(content);
    l.setVisible(true);
  }

  private void style(Button ... buttons) {
    for (Button button : buttons) {
      button.setPrefWidth(Button.USE_COMPUTED_SIZE);
      button.setStyle("-fx-background-color: #4CAF50; " + "-fx-text-fill: white; "
              + "-fx-font-size: 14px;" + "-fx-font-weight: bold");
      button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #4DDD50; "
              + "-fx-text-fill: white; " + "-fx-font-size: 14px;" + "-fx-font-weight: bold"));
      button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #4CAF50; "
              + "-fx-text-fill: white; " + "-fx-font-size: 14px;" + "-fx-font-weight: bold"));
    }
  }
}