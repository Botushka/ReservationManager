module com.example.ohjelmistotuotanto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ohjelmistotuotanto.NakymaHallinta.MokkiHallinta to javafx.fxml;
    exports com.example.ohjelmistotuotanto;

}