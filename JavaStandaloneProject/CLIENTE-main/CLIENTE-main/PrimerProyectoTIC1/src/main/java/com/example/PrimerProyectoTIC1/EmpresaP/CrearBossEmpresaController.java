package com.example.PrimerProyectoTIC1.EmpresaP;

import com.example.PrimerProyectoTIC1.AdminP.OptionPaneController;
import com.example.PrimerProyectoTIC1.BossEmpresaDTO;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.io.IOException;

public class CrearBossEmpresaController {

    @FXML
    private TextField nombreBossID;

    @FXML
    private TextField TelefonoBossEmpID;

    @FXML
    private TextField MailBossEmpID;

    @FXML
    private TextField ContraseñaBossEmpID;

    @FXML
    private TextField EmpresaBossEmpID;

    @FXML
    private Button salirBCEmp;

    public void agregarBossEmpresa(ActionEvent actionEvent) throws IOException {

        BossEmpresaDTO bossEmpresa = new BossEmpresaDTO();
        bossEmpresa.setEmpresaNombre(EmpresaBossEmpID.getText());
        bossEmpresa.setMail(MailBossEmpID.getText());
        bossEmpresa.setPassword(ContraseñaBossEmpID.getText());
        bossEmpresa.setTelefono(Long.parseLong(TelefonoBossEmpID.getText()));
        bossEmpresa.setNombre(nombreBossID.getText());

        Gson gson=new Gson();
        String body= gson.toJson(bossEmpresa);
        HttpResponse<JsonNode> jsonNodeHttpResponse= Unirest.post("http://localhost:8080/manager-empresas/").header("Content-Type","application/json").body(new JsonNode(body)).asJson();//esto te crea y te manda al servidor la empresa
        System.out.println(jsonNodeHttpResponse.getStatus());
        //HttpResponse<String> jsonNodeHttpResponse= Unirest.post("http://localhost:8080/empresa/agregarEmpresa").header("Content-Type","application/json").body(new JsonNode(body)).asString();
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
            Stage stage = (Stage) salirBCEmp.getScene().getWindow();
            System.out.println("Has salido exitosamente.");
            stage.close();
        }
    }

}
