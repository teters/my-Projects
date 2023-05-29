package com.example.PrimerProyectoTIC1.CentroDeportivoP;


import com.example.PrimerProyectoTIC1.Imagen;
import com.google.gson.Gson;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kong.unirest.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;

public class CrearActividadController implements Initializable{
    @FXML
    private TextField cuposActividad;

    @FXML
    private TextArea descripcionActividad;

    @FXML
    private ComboBox<String> fechaActividad;

    @FXML
    private TextField horariosActividad;

    @FXML
    private TextField nombreActividad;

    @FXML
    private TextField precioActividad;

    @FXML
    private TextField tipoActividad;

    @FXML
    private CheckBox reserva;

    @FXML
    private Button salirBEmp;

    Stage stage = new Stage();


    public CentroDeportivo1 obtenerCentroDelManager(){
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/managercentrodep/centroDelManagerLoggeado").
                header("Content-Type","application/json").asJson();
        com.fasterxml.jackson.databind.ObjectMapper mapper=new com.fasterxml.jackson.databind.ObjectMapper();
        CentroDeportivo1 centro=null;
        try {
            centro=mapper.readValue(response.getBody().toString(),CentroDeportivo1.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return centro;

    }

    public void altaActividad(ActionEvent event) {
        String cuposString = cuposActividad.getText();
        String descripcion = descripcionActividad.getText();
        String fechaAct = fechaActividad.getValue();
        String horarios = horariosActividad.getText();
        String nombre = nombreActividad.getText();
        String precioString = precioActividad.getText();
        String tipo = tipoActividad.getText();
        Boolean reservaBool=reserva.isSelected();
        String nombrecentro=obtenerCentroDelManager().getNombre();



        ActividadDTO actividad1 = new ActividadDTO();
        int cupos=Integer.parseInt(cuposString);
        Float precio=Float.parseFloat(precioString);

        actividad1.setCupos(cupos);
        actividad1.setDescripcion(descripcion);
        actividad1.setHorario(horarios);
        actividad1.setDia(fechaAct);
        actividad1.setNombre(nombre);
        actividad1.setPrecio(precio);
        actividad1.setTipoActividad(tipo);
        actividad1.setNombreCentro(nombrecentro);

        actividad1.setReservaBool(reservaBool);

        //Actividad actividad1 = new Actividad(cupos, descripcion, fechaAct, horarios, nombre, precio, tipo);}

        Gson gson=new Gson();
        String body= gson.toJson(actividad1);
        HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/actividad/").
                header("Content-Type","application/json").
                body(new JsonNode(body)).asJson();
        }

    public void subirFotos(ActionEvent event){
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file =fileChooser.showOpenDialog(stage);
        if (file!=null){
            System.out.println("tengo file");
        }
        try{
            byte[] fileContent= FileUtils.readFileToByteArray(file);
            String encodedString= Base64.getEncoder().encodeToString(fileContent);
            Imagen imagen=new Imagen();
            imagen.setContent(encodedString);
            String nombre=nombreActividad.getText();
            imagen.setNombreActividad(nombre);
            Gson gson=new Gson();
            String body= gson.toJson(imagen);
            HttpResponse<JsonNode> response=Unirest.post("http://localhost:8080/imagen/").
                    header("Content-Type","application/json").body(new JsonNode(body)).
                    asJson();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void volverEmp(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(CrearActividadController.class.getResource("centro-dep-pane.fxml"));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> fecha = FXCollections.observableArrayList("Lunes","Martes","Miercoles",
                "Jueves", "Viernes","Sabado");
        fechaActividad.setItems(fecha);
    }


}
