module com.example.employeespair {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.employees to javafx.fxml;
    exports com.employees;
}