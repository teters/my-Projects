package com.example.PrimerProyectoTIC1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader();
        //fxmlLoader.setControllerFactory(PrimerProyectoTic1Application.getContext()::getBean);
        Parent root = FXMLLoader.load(Main.class.getResource("Login.fxml"));
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/logo-fit.png"))));
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("FITNESS APP");
        stage.setScene(scene);
        stage.show();
    }



}

