module com.example.mnprojekt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires mysql.connector.java;

    exports com.example.mnprojekt;
    opens com.example.mnprojekt;
    opens com.example.mnprojekt.login;
    opens com.example.mnprojekt.register;
    opens com.example.mnprojekt.solve;
    opens com.example.mnprojekt.table;
}
//
// requires javafx.controls;
//         requires javafx.fxml;
//         requires java.sql;
//         requires javafx.graphics;
//         requires mysql.connector.java;
//
//         opens com.example.EquationSolver;
//         opens com.example.EquationSolver.login;
//         opens com.example.EquationSolver.register;
//         opens com.example.EquationSolver.Connector;