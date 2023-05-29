package com.example.PrimerProyectoTIC1.EmpleadoP;

import com.example.PrimerProyectoTIC1.CentroDeportivoP.Actividad;
import com.example.PrimerProyectoTIC1.Imagen;
import com.example.PrimerProyectoTIC1.ReservaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import kong.unirest.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VentanaActividadController {


    @FXML
    private ImageView imgAct;

    @FXML
    private Label nombreAct;

    @FXML
    private Label centroDepYLugar;

    @FXML
    private BorderPane borderPane;

    @FXML
    private ChoiceBox<String> diaAct;

    @FXML
    private ChoiceBox<String> horarioAct;


    @FXML
    private Label descripcion;

    @FXML
    private Label tipoAct;

    @FXML
    private Label precio;
    
    @FXML
    private Button reservar;

    private Actividad actividad;



    public static final String CURRENCY = "$";

    public void setData(Actividad actividad){
        this.actividad = actividad;
        nombreAct.setText(actividad.getNombre());
        tipoAct.setText(actividad.getTipoActividad());
        descripcion.setText(actividad.getDescripcion());
        //ObservableList<String> centrosDeportivos = FXCollections.observableArrayList(nombreDeCentrosDeportivos(actividad.getCentroDeportivo().getNombre()));
        Long centro_id=actividad.getCentro_deportivo_1_id();
        HttpResponse<String> response = Unirest.get("http://localhost:8080/centrodeportivo/"+centro_id+"/").
                header("Content-Type","application/json").asString();
        ObjectMapper mapper=new ObjectMapper();
        try {
            Image i=new Image(getFotos(actividad.getNombre()));
            imgAct.setImage(i);
        }catch (NullPointerException n ) {

        }

        String nombre_centro=null;
       //     nombre_centro=mapper.readValue(response.getBody().toString(),String.class);
        nombre_centro=response.getBody();

        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
        centroDepYLugar.setText(nombre_centro + ",");
        precio.setText(VentanaActividadController.CURRENCY + actividad.getPrecio());
        ObservableList<String> list =  FXCollections.observableArrayList(obtenerDiaAct(actividad.getNombre()));


        diaAct.setItems(list);
        if (actividad.getReserva()==true){
            reservar.setVisible(true);
            reservar.setOnAction(this::reserva);
            ObservableList<String> horarios = FXCollections.observableArrayList(obtenerHorarioConNombreCentroYNombreActividad(actividad.getNombre(),nombre_centro));
            horarioAct.setItems(horarios);
        } else {
            horarioAct.setValue("Libre horario");
            reservar.setVisible(false);
        }

    }

    public void reserva(ActionEvent event){

        ReservaDTO reservaDTO=new ReservaDTO();
        reservaDTO.setActividadId(actividad.getId());
        reservaDTO.setCentroId(actividad.getCentro_deportivo_1_id());

        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LLLL/yyyy");
        String formattedString = localDate.format(formatter);
        reservaDTO.setFecha(formattedString);
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/empleado/getEmpleadoInicio").
                header("Content-Type","application/json").asJson();
        ObjectMapper mapper=new ObjectMapper();
        Empleado empleado=null;

        try {
            empleado=mapper.readValue(response.getBody().toString(),Empleado.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        reservaDTO.setMail_empleado(empleado.getMail());
        posteoReserva(reservaDTO);

    }
    public void posteoReserva(ReservaDTO reservaDTO){
        Gson gson=new Gson();
        String body= gson.toJson(reservaDTO);
        HttpResponse<String> response = Unirest.post("http://localhost:8080/reserva/").
                header("Content-Type","application/json").
                body(new JsonNode(body)).asString();
        System.out.println(response.getBody());
        if ((response.getBody()).equals("true")){
            Alert alerta = new Alert(Alert.AlertType.NONE);
            alerta.setTitle("Reserva");
            alerta.setHeaderText("Su reserva ha sido ingresada con exito");
            alerta.setContentText("Lo esperamos!");
            ButtonType buttonType = new ButtonType("Salir", ButtonBar.ButtonData.CANCEL_CLOSE);
            alerta.getButtonTypes().setAll(buttonType);
            Optional<ButtonType> result = alerta.showAndWait();
        } else if ((response.getBody()).equals("false")){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Reserva");
            alerta.setHeaderText("No se pudo reservar");
            alerta.setContentText("No quedan cupos :(");
            ButtonType buttonType = new ButtonType("Salir", ButtonBar.ButtonData.CANCEL_CLOSE);
            alerta.getButtonTypes().setAll(buttonType);
            Optional<ButtonType> result = alerta.showAndWait();
        }
    }

    public List<String> nombreDeCentrosDeportivos(String nombredeactividad){
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/actividad/"+nombredeactividad+"/centros").
                header("Content-Type","application/json").asJson();
        ObjectMapper mapper=new ObjectMapper();
        List<String> centros=null;
        try {
            String[] nombres=mapper.readValue(response.getBody().toString(),String[].class);
            centros= Arrays.asList(nombres);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return centros;
    }
    public List<String> horariosDeActividad(String nombredeact){
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/actividad/"+nombredeact+"/horarios").
                header("Content-Type","application/json").asJson();
        ObjectMapper mapper=new ObjectMapper();
        List<String> horarios=null;
        try {
            String[] horariosArray=mapper.readValue(response.getBody().toString(),String[].class);
            horarios= Arrays.asList(horariosArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return horarios;
    }

    public List<String> obtenerHorarioConNombreCentroYNombreActividad(String actividad,String centro){
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/actividad/"+actividad+"/"+centro+"/horarios").
                header("Content-Type","application/json").asJson();
        ObjectMapper mapper=new ObjectMapper();
        List<String> horarios=null;
        try {
            String[] horariosArray=mapper.readValue(response.getBody().toString(),String[].class);
            horarios= Arrays.asList(horariosArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return horarios;
    }
    public List<String> obtenerDiaAct(String nombreAct){
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/actividad/"+nombreAct+"/dias/").
                header("Content-Type","application/json").asJson();
        ObjectMapper mapper=new ObjectMapper();
        List<String> dias=null;
        try {
            String[] horariosArray=mapper.readValue(response.getBody().toString(),String[].class);
            dias= Arrays.asList(horariosArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dias;

    }
    public List<String> obtenerCentrosAPartirDeUnHorariodeActividad(String actividad, String horario){
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/actividad/"+actividad+"/"+horario+"/centro").
                header("Content-Type","application/json").asJson();
        ObjectMapper mapper=new ObjectMapper();
        List<String> centro=null;
        try {
            String[] horariosArray=mapper.readValue(response.getBody().toString(),String[].class);
            centro= Arrays.asList(horariosArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return centro;
    }
    public ByteArrayInputStream getFotos(String nombreAct){
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/imagen/"+nombreAct+"/").
                header("Content-Type","application/json").asJson();
        ObjectMapper mapper=new ObjectMapper();
        //List<Imagen> imagens=null;
        Imagen imagen=null;
        try {
            imagen=mapper.readValue(response.getBody().toString(),Imagen.class);
            //imagens= Arrays.asList(horariosArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] decodedBytes;
        decodedBytes=Base64.getDecoder().decode(imagen.getContent());
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decodedBytes);


        return byteArrayInputStream;
    }
    }




