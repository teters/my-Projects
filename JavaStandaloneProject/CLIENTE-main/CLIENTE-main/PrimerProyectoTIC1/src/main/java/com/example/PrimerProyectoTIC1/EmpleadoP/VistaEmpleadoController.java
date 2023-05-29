package com.example.PrimerProyectoTIC1.EmpleadoP;

import com.example.PrimerProyectoTIC1.AdminP.OptionPaneController;
import com.example.PrimerProyectoTIC1.CentroDeportivoP.Actividad;
import com.example.PrimerProyectoTIC1.CentroDeportivoP.CentroDeportivo1;
import com.example.PrimerProyectoTIC1.EmpresaP.Empresa;
import com.example.PrimerProyectoTIC1.LoginController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import kong.unirest.GenericType;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class VistaEmpleadoController implements Initializable {

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    @FXML
    private Button saldoButton;

    private List<Actividad> actividades = new LinkedList<>();

    private Stage stage;

    private static final String CURRENCY = "$";

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

    public void saldo(ActionEvent event){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Saldo");
        alerta.setHeaderText("Saldo disponible:");
        alerta.setContentText( CURRENCY + obtenerEmpleadoDelLogin().getSaldo());
        ButtonType buttonType = new ButtonType("Salir", ButtonBar.ButtonData.CANCEL_CLOSE);
        alerta.getButtonTypes().setAll(buttonType);
        Optional<ButtonType> result = alerta.showAndWait();
    }

    public void verDatos(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(PerfilEmpleadoController.class.getResource("perfil-empleado.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void volver(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(AplicacionEmpleadoController.class.getResource("aplicacion-empleado.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public List<Actividad> getData(){
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/actividad/todas").
                header("Content-Type","application/json").asJson();
        ObjectMapper mapper=new ObjectMapper();
        List<Actividad> actividades1=null;

        try {
            Actividad[] actividadsArray=mapper.readValue(response.getBody().toString(),Actividad[].class);
            actividades1=new ArrayList<>(Arrays.asList(actividadsArray)) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> horarios = new ArrayList<>();
        for (int i = 0; i < actividades1.size(); i++) {

            horarios.add(actividades1.get(i).getHorario());

            actividades1.get(i).setHorarios(horarios);
        }

        /*for (int i = 0; i < Objects.requireNonNull(actividades1).size(); i++) {
            VentanaActividadController ventanaActividadController=new VentanaActividadController();
            ventanaActividadController.setData(actividades1.get(i));

        }*/

        return actividades1;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        actividades.addAll(getData());

        int column = 0;
        int row = 1;
        try{
            for (int i=0;i< actividades.size();i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(VentanaActividadController.class.getResource("ventana-actividad.fxml"));
                BorderPane borderPane = fxmlLoader.load();

                VentanaActividadController ventana = fxmlLoader.getController();
                ventana.setData(actividades.get(i));

                if (column == 2){
                    column =0;
                    row++;
                }

                grid.add(borderPane, column++, row);
                GridPane.setMargin(borderPane,new Insets(10));
            }
        } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

