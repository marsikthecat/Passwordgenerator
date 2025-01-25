package com.example.passwortgenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 * Changes the language of all Ui elements.
 */

public class LanguageManager {

  private final List<Node> elements = new ArrayList<>();
  private final Locale[] locale = {
      new Locale("de", "DE"),
      new Locale("en", "US")
  };
  private int index = 0;
  private ResourceBundle bundle = ResourceBundle.getBundle("bundles", locale[index]);
  private static final LanguageManager myself = new LanguageManager();

  private LanguageManager() {}

  public static LanguageManager getInstance() {
    return myself;
  }

  public void registerUiElements(Node ... nodes) {
    Collections.addAll(elements, nodes);
    applyLanguage();
  }

  /**
   * iterates through all nodes and sets the text according to the Bundle.
   */
  public void applyLanguage() {
    for (Node node : elements) {
      if (node instanceof Label labelToChange) {
        labelToChange.setText(bundle.getString(labelToChange.getId()));
      } else if (node instanceof Button buttonToChange) {
        buttonToChange.setText(bundle.getString(buttonToChange.getId()));
      } else if (node instanceof CheckBox checkBoxToChange) {
        checkBoxToChange.setText(bundle.getString(checkBoxToChange.getId()));
      }
    }
  }

  public void switchLanguage() {
    this.bundle = ResourceBundle.getBundle("bundles", locale[index = 1 - index]);
    applyLanguage();
  }
}