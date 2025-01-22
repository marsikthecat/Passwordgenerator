package com.example.passwortgenerator;

import java.security.SecureRandom;

/**
 * Password Object.
 */

public class Password {

  private final String content;

  /**
  * Constructor.
  */

  public Password(int length, boolean withnumbers, boolean withsymbols) {
    this.content = generatepassword(length, withnumbers, withsymbols);
  }

  private String generatepassword(int length, boolean withnumbers, boolean withsymbols) {
    String letters = "abcdefghijklmnopqrstuvwxyz";
    String numbers = "1234567890";
    String symbols = "!§$%&/()=?+*~'#-_:.;;µ<>|€@^{[]}°";
    StringBuilder characterset = new StringBuilder(letters);
    if (withnumbers) {
      characterset.append(numbers);
    }
    if (withsymbols) {
      characterset.append(symbols);
    }
    SecureRandom random = new SecureRandom();
    StringBuilder password = new StringBuilder();
    for (int i = 0; i < length; i++) {
      password.append(characterset.charAt(random.nextInt(characterset.length())));
    }
    return password.toString();
  }

  public String getPassword() {
    return content;
  }
}