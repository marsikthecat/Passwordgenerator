package com.example.passwortgenerator;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Interacts with the UI to change the language of all Strings.
 */

public class ViewModel {

  private final StringProperty selectedLength = new SimpleStringProperty();
  private final StringProperty chosenLength = new SimpleStringProperty();
  private final StringProperty withNumbers = new SimpleStringProperty();
  private final StringProperty withSymbols = new SimpleStringProperty();
  private final StringProperty generatePassword = new SimpleStringProperty();
  private final StringProperty copyPassword = new SimpleStringProperty();
  private final StringProperty copied = new SimpleStringProperty();
  private final StringProperty lang = new SimpleStringProperty();
  private final Locale[] locale = {
    new Locale("en", "US"), new Locale("de", "DE")
  };
  private ResourceBundle bundle = ResourceBundle.getBundle("bundles", locale[0]);

  /**
   * Sets all properties to the default language (english US).
   */

  public ViewModel() {
    selectedLength.set(bundle.getString("selectedLengthLabelText"));
    chosenLength.set(bundle.getString("chosenLength"));
    withNumbers.set(bundle.getString("withNumbers"));
    withSymbols.set(bundle.getString("withSymbols"));
    generatePassword.set(bundle.getString("generatePassword"));
    copyPassword.set(bundle.getString("copyPassword"));
    copied.set(bundle.getString("copied"));
    lang.set(bundle.getString("lang"));
  }

  public StringProperty selectedLengthProperty() {
    return selectedLength;
  }

  public StringProperty chosenLengthProperty() {
    return chosenLength;
  }

  public StringProperty withNumbersProperty() {
    return withNumbers;
  }

  public StringProperty withSymbolsProperty() {
    return withSymbols;
  }

  public StringProperty generatePasswordProperty() {
    return generatePassword;
  }

  public StringProperty copyPasswordProperty() {
    return copyPassword;
  }

  public StringProperty copiedProperty() {
    return copied;
  }

  public StringProperty langProperty() {
    return lang;
  }

  /**
   * loads the new language bundle and applies it to all properties.
   */

  public void switchLanguage() {
    if (bundle.getLocale().equals(new Locale("en", "US"))) {
      bundle = ResourceBundle.getBundle("bundles", locale[1]);
    } else {
      bundle = ResourceBundle.getBundle("bundles", locale[0]);
    }
    selectedLength.set(bundle.getString("selectedLengthLabelText"));
    chosenLength.set(bundle.getString("chosenLength"));
    withNumbers.set(bundle.getString("withNumbers"));
    withSymbols.set(bundle.getString("withSymbols"));
    generatePassword.set(bundle.getString("generatePassword"));
    copyPassword.set(bundle.getString("copyPassword"));
    copied.set(bundle.getString("copied"));
    lang.set(bundle.getString("lang"));
  }
}