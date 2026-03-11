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
import sn.codeur.tp_assurance_vi.entity.Assurance;
import sn.codeur.tp_assurance_vi.entity.TypeAssurance;
import sn.codeur.tp_assurance_vi.repository.AssuranceRepository;
import sn.codeur.tp_assurance_vi.repository.TypeAssuranceRepository;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AssuranceController implements Initializable {

    private AssuranceRepository assuranceRepository;
    private TypeAssuranceRepository typeAssuranceRepository;
    private ObservableList<Assurance> allAssurances;

    @FXML
    private Button ajouter_btn;
    @FXML
    private Button modifier_btn;
    @FXML
    private Button supprimer_btn;
    @FXML
    private Button search_btn;
    @FXML
    private Button choisir_btn;

    @FXML
    private TextField nom_tf;
    @FXML
    private TextField montant_tf;
    @FXML
    private TextField search_tf;
    @FXML
    private ComboBox<TypeAssurance> type_assurance_cb;

    @FXML
    TableView<Assurance> tab_assurances;

    @FXML
    TableColumn<Assurance, Integer> id_tab;

    @FXML
    TableColumn<Assurance, String> nom_tab;

    @FXML
    TableColumn<Assurance, Double> montant_tab;

    @FXML
    TableColumn<Assurance, String> numero_tab;

    @FXML
    TableColumn<Assurance, String> type_tab;

    public AssuranceController() {}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.assuranceRepository = new AssuranceRepository();
        this.typeAssuranceRepository = new TypeAssuranceRepository();
        loadTypeAssurances();
        printAllAssurance();
        search_tf.textProperty().addListener((observable, oldValue, newValue) -> {
            filterTable(newValue);
        });
    }

    private void loadTypeAssurances() {
        type_assurance_cb.getItems().addAll(typeAssuranceRepository.findAll());
    }

    @FXML
    void filterTable(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            tab_assurances.setItems(allAssurances);
        } else {
            ObservableList<Assurance> filtered = allAssurances.filtered(a -> 
                a.getNomClient().toLowerCase().contains(keyword.toLowerCase()) ||
                a.getNumero().contains(keyword)
            );
            tab_assurances.setItems(filtered);
        }
    }

    @FXML
    public void printAllAssurance()
    {
        allAssurances = assuranceRepository.findAll();

        id_tab.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_tab.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        montant_tab.setCellValueFactory(new PropertyValueFactory<>("montant"));
        numero_tab.setCellValueFactory(new PropertyValueFactory<>("numero"));
        type_tab.setCellValueFactory(new PropertyValueFactory<>("typeAssurance"));

        tab_assurances.setItems(allAssurances);
    }

    @FXML
    void addAssurance(ActionEvent event)
    {
        TypeAssurance selectedType = type_assurance_cb.getSelectionModel().getSelectedItem();
        
        Assurance assurance = new Assurance(nom_tf.getText(), Double.parseDouble(montant_tf.getText()));
        assurance.setTypeAssurance(selectedType);
        assuranceRepository.insert(assurance);
        printAllAssurance();
        clearField(event);
    }

    @FXML
    void editAssurance(ActionEvent event)
    {
        Assurance assurance = tab_assurances.getSelectionModel().getSelectedItem();
        assurance.setNomClient(nom_tf.getText());
        assurance.setMontant(Double.parseDouble(montant_tf.getText()));
        assuranceRepository.update(assurance);
        clearField(event);
        printAllAssurance();
    }

    @FXML
    void deleteAssurance(ActionEvent event)
    {
        Assurance assurance = tab_assurances.getSelectionModel().getSelectedItem();
        assuranceRepository.delete(assurance.getId());
        printAllAssurance();
    }

    @FXML
    void clearField(ActionEvent event){
        nom_tf.setText("");
        montant_tf.setText("");
        type_assurance_cb.getSelectionModel().clearSelection();
    }

    @FXML
    void setFields(ActionEvent event){
        Assurance assurance = tab_assurances.getSelectionModel().getSelectedItem();
        nom_tf.setText(assurance.getNomClient());
        montant_tf.setText(String.valueOf(assurance.getMontant()));
    }

    @FXML
    void goToTypeAssurance(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("type-assurance-view.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}