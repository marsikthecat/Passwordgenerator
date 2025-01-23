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
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Passwordgenerator that supports german and english.
 * Main: 129 lines.
 * Password: 43 lines.
 * LanguageManager: 58 lines.
 * 230 lines.
 */

public class Main extends Application {

  private final LanguageManager languageManager = LanguageManager.getInstance();

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

    Label lengthLabel = new Label();
    lengthLabel.setId("lengthlabel");

    Label choosenLength = new Label();
    lengthSlider.valueProperty().addListener((observable, oldValue, newValue)
            -> choosenLength.setText(": " + newValue.intValue()));

    CheckBox num = new CheckBox();
    num.setId("withNumbers");
    CheckBox symbol = new CheckBox();
    symbol.setId("withSymbols");

    Button btn = new Button();
    btn.setId("generatePassword");

    TextField field = new TextField();
    btn.setOnAction(e -> generatePasswort(field, (int) lengthSlider.getValue(),
            num.isSelected(), symbol.isSelected()));

    Button copybtn = new Button();
    copybtn.setId("copyPassword");
    style(btn, copybtn);

    Label l = new Label();
    l.setId("copied");
    l.setVisible(false);

    copybtn.setOnAction(e -> copypasswort(field, l));

    btn.setOnMouseClicked(e -> {
      if (l.isVisible()) {
        l.setVisible(false);
      }
    });
    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);

    Label lang = new Label();
    lang.setId("langswitch");
    lang.setOnMouseClicked(e -> languageManager.switchLanguage());

    HBox btnbox = new HBox(5);
    btnbox.setPadding(new Insets(5, 0, 0, 0));
    btnbox.setAlignment(Pos.CENTER);
    btnbox.getChildren().addAll(btn, copybtn);

    VBox content = new VBox(5);
    content.getChildren().addAll(lengthSlider, new HBox(lengthLabel, choosenLength),
            num, symbol, field, btnbox, new HBox(l, spacer, lang));
    content.setPadding(new Insets(10));

    languageManager.registerUiElements(lengthLabel, num, symbol, btn, copybtn, l, lang);

    Scene scene = new Scene(content, 320, 215);
    stage.setResizable(false);
    stage.setTitle("Passwordgenerator");
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