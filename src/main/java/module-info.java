module sn.codeur.tp_assurance_vi {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;


    opens sn.codeur.tp_assurance_vi to javafx.fxml;
    exports sn.codeur.tp_assurance_vi;
}