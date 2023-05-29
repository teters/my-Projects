package com.example.PrimerProyectoTIC1.CentroDeportivoP;

import com.example.PrimerProyectoTIC1.AdminP.OptionPaneController;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.io.IOException;


public class CrearCentroDepController {
    @FXML
    private TextField nombreCentroDep;

    @FXML
    private TextField direccionCentroDep;



    public void agregarCentroDeportivo(ActionEvent actionEvent) throws Exception{
        CentroDeportivo1 centroDeportivo = new CentroDeportivo1(nombreCentroDep.getText(),direccionCentroDep.getText());
        Gson gson=new Gson();
        String body= gson.toJson(centroDeportivo);
        HttpResponse<kong.unirest.JsonNode> jsonNodeHttpResponse= Unirest.post("http://localhost:8080/centrodeportivo/").header("Content-Type","application/json").body(new JsonNode(body)).asJson();
    }


    public void crearBossCD(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(CrearBossCDController.class.getResource("crear-boss-CD1.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void volver(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(OptionPaneController.class.getResource("option-pane.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void salir(ActionEvent actionEvent){

    }

}
