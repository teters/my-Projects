package com.example.PrimerProyectoTIC1.EmpleadoP;

import com.example.PrimerProyectoTIC1.AdminP.OptionPaneController;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.apache.http.HttpRequest;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PerfilEmpleadoController implements Initializable {


    @FXML
    private Label empresa;

    @FXML
    private Label fechaVenc;

    @FXML
    private Label mail;

    @FXML
    private Label nombreEmpleado;

    @FXML
    private Label saldo;

    @FXML
    private Label telefono;

    @FXML
    private Button  salirButton;

    public void volver(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(PerfilEmpleadoController.class.getResource("aplicacion-empleado.fxml"));
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


    public Empleado obtenerEmpleadoDelLogin(){
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/empleado/getEmpleadoInicio").
                header("Content-Type","application/json").asJson();
        ObjectMapper mapper=new ObjectMapper();
        Empleado empleado=null;

        try {
            empleado=mapper.readValue(response.getBody().toString(),Empleado.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return empleado;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nombreEmpleado.setText(obtenerEmpleadoDelLogin().getNombre());
        mail.setText(obtenerEmpleadoDelLogin().getMail());
        saldo.setText(Long.toString(obtenerEmpleadoDelLogin().getSaldo()));
        telefono.setText(Long.toString(obtenerEmpleadoDelLogin().getTelefono()));
    }
}
