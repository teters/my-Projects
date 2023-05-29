package com.example.PrimerProyectoTIC1.CentroDeportivoP;

import com.example.PrimerProyectoTIC1.EmpleadoP.Reserva;
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
import kong.unirest.GenericType;
import kong.unirest.Unirest;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ReservasTableController implements Initializable {

    @FXML
    private TableColumn<Reserva, Long> colActividadID;

    @FXML
    private TableColumn<Reserva, String> colDia;

    @FXML
    private TableColumn<Reserva, Long> colEmpID;

    @FXML
    private TableColumn<Reserva, LocalDate> colFecha;

    @FXML
    private TableColumn<Reserva, String> colHora;

    @FXML
    private TableView<Reserva> empresaTabla;

    @FXML
    private Button salirBEmp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         List<Reserva> items= Unirest.get("http://localhost:8080/managercentrodep/reservas/centro/").
                header("Content-Type","application/json").
                asObject(new GenericType<List<Reserva>>(){}).getBody();

        ObservableList<Reserva> listaReservas = FXCollections.observableArrayList(items);
        colActividadID.setCellValueFactory(new PropertyValueFactory<>("actividadId"));
        colEmpID.setCellValueFactory(new PropertyValueFactory<>("empleadoId"));
        colDia.setCellValueFactory(new PropertyValueFactory<>("dia"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        empresaTabla.setItems(listaReservas);
    }

    @FXML
    void salir(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Salir");
        alerta.setHeaderText("¿Estás seguro que quieres salir?");
        if (alerta.showAndWait().get() == ButtonType.OK){
            Stage stage = (Stage) salirBEmp.getScene().getWindow();
            System.out.println("Has salido exitosamente.");
            stage.close();
        }
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(CentroDepPaneController.class.getResource("centro-dep-pane.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
