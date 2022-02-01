module com.example.mnprojekt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires mysql.connector.java;
    requires exp4j;

    exports com.example.mnprojekt;
    opens com.example.mnprojekt;
    opens com.example.mnprojekt.login;
    opens com.example.mnprojekt.register;
    opens com.example.mnprojekt.solve;
    opens com.example.mnprojekt.table;
    opens com.example.mnprojekt.methods;
}
