package com.example.PrimerProyectoTIC1.CentroDeportivoP;

import com.example.PrimerProyectoTIC1.AdminP.OptionPaneController;
import com.example.PrimerProyectoTIC1.CheckIn;

import com.example.PrimerProyectoTIC1.CheckinDTO;
import com.example.PrimerProyectoTIC1.EmpleadoP.Empleado;

import com.example.PrimerProyectoTIC1.EmpleadoP.Reserva;
import com.example.PrimerProyectoTIC1.ReservaDTO;
import com.example.PrimerProyectoTIC1.ReservaDTOConDia;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import javafx.stage.Stage;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EmpleadosEnCentrosController implements Initializable {

    @FXML
    private ChoiceBox<String> actividadesList;

    @FXML
    private ChoiceBox<String> dias;

    @FXML
    private TextField mailDeEmp;

    @FXML
    private Button validarReserva;

    @FXML
    private Label esValida;

    @FXML
    private Button salirBtn;


    List<String> listaActividades = new ArrayList<>();



    public List<Actividad> obtenerActividadesConCentro(CentroDeportivo1 centro){
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/centrodeportivo/"+centro.getId()+"/actividades").
                header("Content-Type","application/json").asJson();
        ObjectMapper mapper=new ObjectMapper();
        List<Actividad> actividades=null;
        try {
            Actividad[] nombres=mapper.readValue(response.getBody().toString(),Actividad[].class);
            actividades= Arrays.asList(nombres);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return actividades;
    }
    public List<ReservaDTOConDia> obtenerReservasConMail(String mail){
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/reserva/"+mail+"/").
                header("Content-Type","application/json").asJson();
        ObjectMapper mapper=new ObjectMapper();
        List<ReservaDTOConDia> reservas=null;
        try {
            ReservaDTOConDia[] reservasarray=mapper.readValue(response.getBody().toString(),ReservaDTOConDia[].class);
            reservas= Arrays.asList(reservasarray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reservas;

    }
    public Empleado obtenerEmpleadoConMail(String mail){
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/empleado/"+mail+"/").
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
    public CentroDeportivo1 obtenerCentroDelManager(){
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/managercentrodep/centroDelManagerLoggeado").
                header("Content-Type","application/json").asJson();
        ObjectMapper mapper=new ObjectMapper();
        CentroDeportivo1 centro=null;
        try {
            centro=mapper.readValue(response.getBody().toString(),CentroDeportivo1.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return centro;

    }

    public void validar(ActionEvent event){
        /*for (int i = 0; i < obtenerActividadesConCentro(obtenerCentroDelManager()).size(); i++) {
            if (){}
        }*/
        boolean seHizo=false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LLLL/yyyy");
        LocalDate date=null;
        List<ReservaDTOConDia> reservas=obtenerReservasConMail(mailDeEmp.getText());
        Long actividadId=obtenerActividadConNombre(actividadesList.getValue()).getId();
        String mail=obtenerEmpleadoConMail(mailDeEmp.getText()).getMail();
        for (int i = 0; i < reservas.size(); i++) {
            String dia=reservas.get(i).getDia();
            Long id=reservas.get(i).getActividadId();
            String dias1=dias.getValue();
            if(dias.getValue().equals(dia) && actividadId.equals(id) && reservas.get(i).getMail_empleado().equals(mail)){
                //validarReserva.setVisible(false);
                seHizo=true;
            }
        }
        if(seHizo){
            esValida.setText("Reserva valida");

        }else {
            esValida.setText("No hay ninguna reserva del usuario ingresado para ese dia");
        }


        //validarReserva.setVisible(false);
    }
    public Actividad obtenerActividadConNombre(String nombre){
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/actividad/"+nombre+"/").
                header("Content-Type","application/json").asJson();
        ObjectMapper mapper=new ObjectMapper();
        Actividad actividad=null;
        try {
            actividad=mapper.readValue(response.getBody().toString(),Actividad.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return actividad;


    }

    public void checkIn(ActionEvent event){
        CheckIn checkIn=new CheckIn();
        Empleado empleado=obtenerEmpleadoConMail(mailDeEmp.getText());
        CheckinDTO checkindto=new CheckinDTO();
        checkIn.setEmpleado(empleado);
        checkIn.setHora(LocalDateTime.now());
        checkIn.setActividad(obtenerActividadConNombre(actividadesList.getValue()));
        Actividad actividad=obtenerActividadConNombre(actividadesList.getValue());
        checkindto.setMail_empleado(empleado.getMail());
        checkindto.setHora("2:00");
        checkindto.setId_actividad(actividad.getId());
        Gson gson=new Gson();
        String body= gson.toJson(checkindto);
        HttpResponse<String> response = Unirest.post("http://localhost:8080/checkin/").
                header("Content-Type","application/json").header("Accept","application/json").
                body(new JsonNode(body)).asString();
        System.out.println(response.getBody());
        if ((response.getBody()).equals("si")){
            Alert alerta = new Alert(Alert.AlertType.NONE);
            alerta.setTitle("CHECK-IN");
            alerta.setHeaderText("Se hizo el checkin");
            alerta.setContentText("Bien ahi");
            ButtonType buttonType = new ButtonType("Salir", ButtonBar.ButtonData.CANCEL_CLOSE);
            alerta.getButtonTypes().setAll(buttonType);
            Optional<ButtonType> result = alerta.showAndWait();
        } else if ((response.toString()).equals("no")) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("CHECK-IN");
            alerta.setHeaderText("No se hizo");
            alerta.setContentText("Revisar carnet de salud");
            ButtonType buttonType = new ButtonType("Salir", ButtonBar.ButtonData.CANCEL_CLOSE);
            alerta.getButtonTypes().setAll(buttonType);
            Optional<ButtonType> result = alerta.showAndWait();
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("Lunes", "Martes", "Miercoles", "Jueves", "Jiernes", "Sabado", "Domingo");
        dias.setItems(list);
        ObservableList<String> actividadesCD = FXCollections.observableArrayList();
        CentroDeportivo1 centroManager=obtenerCentroDelManager();
        List<Actividad> actividades=obtenerActividadesConCentro(centroManager);
        for (int i = 0; i < actividades.size(); i++) {
            actividadesCD.add(actividades.get(i).getNombre());
        }
        actividadesList.setItems(actividadesCD);


    }

    public void volver(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(CentroDepPaneController.class.getResource("centro-dep-pane.fxml"));
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
