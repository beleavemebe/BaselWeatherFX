module com.beleavemebe {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.beleavemebe to javafx.fxml;
    exports com.beleavemebe;
}