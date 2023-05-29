package com.example.PrimerProyectoTIC1.AdminP;

import com.example.PrimerProyectoTIC1.CentroDeportivoP.CentroDepController;
import com.example.PrimerProyectoTIC1.CentroDeportivoP.CrearCentroDepController;
import com.example.PrimerProyectoTIC1.EmpresaP.CrearEmpresaController;
import com.example.PrimerProyectoTIC1.EmpresaP.EmpresaPaneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class OptionPaneController {

    @FXML
    private Button empresaBtn;

    @FXML
    private Button centroDepBtn;


    @FXML
    private Button salirBtn;





    public void empresaPane(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(EmpresaPaneController.class.getResource("empresa.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void centroDepPane(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(CentroDepController.class.getResource("centro-dep.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void crearEmpresaPane(ActionEvent actionEvent) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(CrearEmpresaController.class.getResource("crear-empresa.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void crearCentroDepPane(ActionEvent a) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(CrearCentroDepController.class.getResource("crear-centro-dep.fxml"));
        Stage stage = (Stage)((Node)a.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void volver(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(OptionPaneController.class.getResource("Login.fxml"));
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
                Stage stage = (Stage) salirBtn.getScene().getWindow();
            System.out.println("Has salido exitosamente.");
            stage.close();
        }
    }

}
