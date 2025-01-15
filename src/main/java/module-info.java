module com.example.passwortgenerator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.passwortgenerator to javafx.fxml;
    exports com.example.passwortgenerator;
}