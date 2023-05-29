package com.example.PrimerProyectoTIC1;

import com.example.PrimerProyectoTIC1.AdminP.Admin;
import com.example.PrimerProyectoTIC1.AdminP.OptionPaneController;
import com.example.PrimerProyectoTIC1.CentroDeportivoP.BossCD1;
import com.example.PrimerProyectoTIC1.CentroDeportivoP.CentroDepPaneController;
import com.example.PrimerProyectoTIC1.CentroDeportivoP.CentroDeportivo1;
import com.example.PrimerProyectoTIC1.EmpleadoP.AplicacionEmpleadoController;
import com.example.PrimerProyectoTIC1.EmpleadoP.Empleado;
import com.example.PrimerProyectoTIC1.EmpleadoP.VistaEmpleadoController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import kong.unirest.*;

import java.io.IOException;


public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginBtn;

    @FXML
    private Label textoError;

    @FXML
    private Button salirBtn;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Empleado empleado;
    public void adminAceptado(ActionEvent e) throws IOException {
        BossCD1 managerCD=null;

        boolean login = false;
        Admin admin=new Admin("eduardo@correo.com","Eduardo");
        String mail = username.getText();
        String passwordLogin = password.getText();
        User user=new User(mail,passwordLogin);
        Gson gson=new Gson();
        String body= gson.toJson(user);
        if (mail.equals(admin.getMail())  && passwordLogin.equals(admin.getContrasena()) ) {
            login = true;
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(OptionPaneController.class.getResource("option-pane.fxml"));
            //Parent root = loader.load(getClass().getResource("option-pane.fxml"));
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            return;
        }
        //VER SI ES UN BOSSCD
        try{
            HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/managercentrodep/iniciosesion").
                    header("Content-Type","application/json").
                    body(new JsonNode(body)).asJson();
            com.fasterxml.jackson.databind.ObjectMapper mapper= new com.fasterxml.jackson.databind.ObjectMapper();
            String bod=response.getBody().toString();
            managerCD=mapper.readValue(response.getBody().toString(),new TypeReference<BossCD1>(){});
        }catch (Exception n){
            managerCD=new BossCD1(new CentroDeportivo1(),"co","dk",2L,"");

        }
        BossCD1 bossPredeterminado=new BossCD1(new CentroDeportivo1(),"co","dk",2L,"");
        if(!managerCD.getMail().equals(bossPredeterminado.getMail())&& !managerCD.getPassword().equals(bossPredeterminado.getPassword())){
            login=true;
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(CentroDepPaneController.class.getResource("centro-dep-pane.fxml"));
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }


        try{
            HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/empleado/iniciosesion").
                    header("Content-Type","application/json").
                    body(new JsonNode(body)).asJson();
            com.fasterxml.jackson.databind.ObjectMapper mapper= new com.fasterxml.jackson.databind.ObjectMapper();
            String bod=response.getBody().toString();
            empleado=mapper.readValue(response.getBody().toString(),new TypeReference<Empleado>(){});
        }catch (Exception n){

        }


        if (empleado!=null){
            login=true;
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(AplicacionEmpleadoController.class.getResource("aplicacion-empleado.fxml"));
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        if (login==false){
            textoError.setText("Usuario o contraseña incorrectos");
        }


    }

    public void closeWindow(ActionEvent event){
        exitWindow();
    }

    private void exitWindow(){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Salir");
        alerta.setHeaderText("¿Estás seguro que quieres salir?");
        if (alerta.showAndWait().get() == ButtonType.OK){
            stage = (Stage) salirBtn.getScene().getWindow();
            System.out.println("Has salido exitosamente.");
            stage.close();
        }

    }


    public void sobreNos(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(CentroDepPaneController.class.getResource("sobre-nosotros.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }


}
