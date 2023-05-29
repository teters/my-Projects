package com.example.PrimerProyectoTIC1.EmpresaP;

import com.example.PrimerProyectoTIC1.AdminP.OptionPaneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import kong.unirest.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EmpresaController implements Initializable {
    @FXML
    private TableView<Empresa> empresaTabla;
    @FXML
    private TableColumn<Empresa, Long> colTelefono;
    @FXML
    private TableColumn<Empresa, String> colNombreE;
    @FXML
    private TableColumn<Empresa, String> colDireccionE;

    @FXML
    private Button salirBEmp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Empresa> items= Unirest.get("http://localhost:8080/empresas/").
                header("Content-Type","application/json").
                asObject(new GenericType<List<Empresa>>(){}).getBody();

        ObservableList<Empresa> listaEmpresas = FXCollections.observableArrayList(items);
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colNombreE.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDireccionE.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        empresaTabla.setItems(listaEmpresas);

    }

    public void volverEmp(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(OptionPaneController.class.getResource("option-pane.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void salirEmp(ActionEvent e){
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Salir");
            alerta.setHeaderText("¿Estás seguro que quieres salir?");
            if (alerta.showAndWait().get() == ButtonType.OK){
                Stage stage = (Stage) salirBEmp.getScene().getWindow();
                System.out.println("Has salido exitosamente.");
                stage.close();
            }

        }

}
