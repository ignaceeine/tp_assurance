package sn.codeur.tp_assurance_vi;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sn.codeur.tp_assurance_vi.entity.TypeAssurance;
import sn.codeur.tp_assurance_vi.repository.TypeAssuranceRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class TypeAssuranceController implements Initializable {

    private TypeAssuranceRepository typeAssuranceRepository;
    private ObservableList<TypeAssurance> allTypes;

    @FXML
    private Button ajouter_btn;
    @FXML
    private Button modifier_btn;
    @FXML
    private Button supprimer_btn;
    @FXML
    private Button choisir_btn;

    @FXML
    private TextField libelle_tf;
    @FXML
    private TextField search_tf;

    @FXML
    TableView<TypeAssurance> tab_types;

    @FXML
    TableColumn<TypeAssurance, Integer> id_tab;

    @FXML
    TableColumn<TypeAssurance, String> libelle_tab;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.typeAssuranceRepository = new TypeAssuranceRepository();
        printAllTypes();
        search_tf.textProperty().addListener((observable, oldValue, newValue) -> {
            filterTable(newValue);
        });
    }

    @FXML
    void filterTable(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            tab_types.setItems(allTypes);
        } else {
            ObservableList<TypeAssurance> filtered = allTypes.filtered(t -> 
                t.getLabel().toLowerCase().contains(keyword.toLowerCase())
            );
            tab_types.setItems(filtered);
        }
    }

    @FXML
    public void printAllTypes() {
        allTypes = typeAssuranceRepository.findAll();
        
        id_tab.setCellValueFactory(new PropertyValueFactory<>("id"));
        libelle_tab.setCellValueFactory(new PropertyValueFactory<>("label"));
        
        tab_types.setItems(allTypes);
    }

    @FXML
    void addTypeAssurance(ActionEvent event) {
        TypeAssurance typeAssurance = new TypeAssurance(libelle_tf.getText());
        typeAssuranceRepository.insert(typeAssurance);
        printAllTypes();
        clearField();
    }

    @FXML
    void editTypeAssurance(ActionEvent event) {
        TypeAssurance typeAssurance = tab_types.getSelectionModel().getSelectedItem();
        typeAssurance.setLabel(libelle_tf.getText());
        typeAssuranceRepository.update(typeAssurance);
        clearField();
        printAllTypes();
    }

    @FXML
    void deleteTypeAssurance(ActionEvent event) {
        TypeAssurance typeAssurance = tab_types.getSelectionModel().getSelectedItem();
        typeAssuranceRepository.delete(typeAssurance.getId());
        printAllTypes();
    }

    @FXML
    void clearField() {
        libelle_tf.setText("");
    }

    @FXML
    void setFields(ActionEvent event) {
        TypeAssurance typeAssurance = tab_types.getSelectionModel().getSelectedItem();
        libelle_tf.setText(typeAssurance.getLabel());
    }

    @FXML
    void goToAssurance(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}