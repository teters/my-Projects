package com.example.PrimerProyectoTIC1.CentroDeportivoP;

import com.example.PrimerProyectoTIC1.EmpleadoP.Reserva;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class ReservasEnCentrosController {

    @FXML
    private ChoiceBox<String> dias;

    @FXML
    private Label esValida;

    @FXML
    private ChoiceBox<Reserva> reservas;

    public void validar(ActionEvent event) {

        esValida.setText("Reserva validada, puede ingresar");
    }

}