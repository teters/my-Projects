package com.example.PrimerProyectoTIC1;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class DatosUsuarioController{

    @FXML
    private Button guardarBut;

    @FXML
    private ImageView imagen;
    @FXML
    private Button cancelarBut;
    @FXML
    private TextField nombreID;
    @FXML
    private TextField telefonoID;
    @FXML
    private TextField correoID;
    @FXML
    private Label textoSup;

    Stage stage;


    public void usuarioRegister(ActionEvent actionEvent){;
        guardarDatos();
    }

    public void cerrarVentana(ActionEvent event){
        exitWindow();
    }


    private void exitWindow(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Salir");
        alert.setHeaderText("¿Estás seguro que quieres salir?");
        if (alert.showAndWait().get() == ButtonType.OK){

            stage = (Stage) cancelarBut.getScene().getWindow();
            System.out.println("Has salido exitosamente.");
            stage.close();
        }
    }

    private void guardarDatos(){
        String nombre = nombreID.getText();
        String telefono = telefonoID.getText();
        String direccion = correoID.getText();
        Long tel= Long.parseLong(telefono);
        if ((nombre.isEmpty() || telefono.isEmpty() || direccion.isEmpty())){
            textoSup.setText("Datos incompletos, por favor ingréselos");
        }
        else {
            textoSup.setText("Datos guardados exitosamente!");

            stage = (Stage) guardarBut.getScene().getWindow();

            System.out.println("Has salido exitosamente. Tus datos fueron registrados.");
            stage.close();

        }

        Gson gson=new Gson();
        User user =new User(nombre,direccion,tel);
        String body= gson.toJson(user);

        HttpResponse<JsonNode> jsonNodeHttpResponse= Unirest.post("http://localhost:8080/user/").header("Content-Type","application/json").body(new JsonNode(body)).asJson();
        //
    }
}
