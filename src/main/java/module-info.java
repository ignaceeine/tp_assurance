module sn.codeur.tp_assurance_vi {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;


    opens sn.codeur.tp_assurance_vi to javafx.fxml;
    opens sn.codeur.tp_assurance_vi.entity;
    exports sn.codeur.tp_assurance_vi.entity to org.hibernate.orm.core;
    exports sn.codeur.tp_assurance_vi.utils;
    opens sn.codeur.tp_assurance_vi.utils to javafx.fxml;

    exports sn.codeur.tp_assurance_vi;
}