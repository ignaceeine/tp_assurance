package sn.codeur.tp_assurance_vi;

import javafx.application.Application;
import sn.codeur.tp_assurance_vi.utils.JpaUtil;

public class Launcher {
    public static void main(String[] args) {
        JpaUtil.getEntityManager();
        Application.launch(HelloApplication.class, args);
    }
}
