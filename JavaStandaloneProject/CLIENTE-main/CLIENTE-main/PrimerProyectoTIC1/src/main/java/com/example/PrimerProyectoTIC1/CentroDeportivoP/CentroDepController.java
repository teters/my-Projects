package com.example.PrimerProyectoTIC1.CentroDeportivoP;

import com.example.PrimerProyectoTIC1.AdminP.OptionPaneController;
import com.example.PrimerProyectoTIC1.EmpresaP.Empresa;
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
import kong.unirest.GenericType;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CentroDepController implements Initializable{



    @FXML
    private TableView<CentroDeportivo1> centroDepTabla;

    @FXML
    private TableColumn<CentroDeportivo1, String> colNombre;

    @FXML
    private TableColumn<CentroDeportivo1,String> colDireccion;

    @FXML
    private Button volverButton;

    @FXML
    private Button salirButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<CentroDeportivo1> items = Unirest.get("http://localhost:8080/centrodeportivo/").
                header("Content-Type","application/json")
                .asObject(new GenericType<List<CentroDeportivo1>>(){})
                .getBody();

        ObservableList<CentroDeportivo1> listaCentroDep = FXCollections.observableArrayList(items);

        colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));


        centroDepTabla.setItems(listaCentroDep);
    }

    public void guardarCentroDep(ActionEvent actionEvent) {

    }

    public void volver(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(OptionPaneController.class.getResource("option-pane.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void salir(ActionEvent event){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Salir");
        alerta.setHeaderText("¿Estás seguro que quieres salir?");
        if (alerta.showAndWait().get() == ButtonType.OK){
            Stage stage = (Stage) salirButton.getScene().getWindow();
            System.out.println("Has salido exitosamente.");
            stage.close();
        }
    }

}
