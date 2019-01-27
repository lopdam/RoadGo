/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Documentos.CargarHistorial;
import Objetos.Imagen;
import Objetos.Jugador;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author lopdam
 */
public class Perdio {

    private Stage sPerdio;
    private StackPane rootPerdio;
    private Scene scenePerdio;
    private ImageView imgFondo;
    private Label txtPerdio;
    private Label txtmensaje;
    private Button btnReintentar;
    private Button btnSalir;
    private HBox hbox;
    private VBox vboxPerdio;

    private Jugador juga;
    private int nivel;
    private ImageView avatar;

    private Stage s;

    public Perdio(Jugador j, int nivel, ImageView ava, Stage s) {
        this.juga = j;
        this.nivel = nivel;
        this.avatar = ava;
        this.s = s;
    }

    private void InicializaryAgregar() {
        sPerdio = new Stage();
        rootPerdio = new StackPane();
        scenePerdio = new Scene(rootPerdio, 300, 300);
        sPerdio.setScene(scenePerdio);
        sPerdio.setTitle("HA PERDIDO");
        Imagen img = new Imagen("imggranja3.jpg", 300, 300);
        imgFondo = img.getImagen();

        txtPerdio = new Label("Ha Perido el Nivel");
        txtPerdio.setTextFill(Color.BLUE);
        txtPerdio.setStyle("-fx-font: bold 25px Tahoma;" +
        "-fx-wrap-text:true;"); 

        txtmensaje = new Label("Desea Rentientarlo");
        txtmensaje.setStyle("-fx-font: bold 15px Tahoma;" +
        "-fx-wrap-text:true;"); 

        btnReintentar = new Button("Reintentar");
        btnSalir = new Button("Salir");
        
        btnReintentar.setStyle("-fx-background-color: #008CBA;" +
"    -fx-font-size: 15px;" +
"    -fx-border-radius: 5;" +
"    -fx-border-color: white;" +
"    -fx-border-width: 5;" +
"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );" +
"    -fx-background-radius: 50; " +
"    -fx-font-weight: bold;" +
"    -fx-padding: 3 10 3 10;");
        btnSalir.setStyle("-fx-background-color: #008CBA;" +
"    -fx-font-size: 15px;" +
"    -fx-border-radius: 5;" +
"    -fx-border-color: white;" +
"    -fx-border-width: 5;" +
"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );" +
"    -fx-background-radius: 50; " +
"    -fx-font-weight: bold;" +
"    -fx-padding: 3 10 3 10;");
        
        hbox = new HBox(btnReintentar, btnSalir);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);

        vboxPerdio = new VBox(txtPerdio, txtmensaje, hbox);
        vboxPerdio.setSpacing(10);
        vboxPerdio.setAlignment(Pos.CENTER);

        rootPerdio.getChildren().addAll(imgFondo, vboxPerdio);

    }

    private void Eventos() {
        btnReintentar.setOnAction(e -> {
            sPerdio.close();
            //s.close();
            if (juga.getNivel() > 1) {
                juga.setDatos(juga.getTiempo(), juga.getMonedas(), nivel - 1);
                CargarHistorial.EscribiHistorial(juga.toString());
            }
            Juego jg = new Juego(juga, avatar, 1, s, 3, 0);
            //s.show();
            s.setScene(jg.getSceneJuego());

        });
        btnSalir.setOnAction(e -> {
            //s.close();
            if (juga.getNivel() > 1) {
                juga.setDatos(juga.getTiempo(), juga.getMonedas(), nivel - 1);
                CargarHistorial.EscribiHistorial(juga.toString());
            }
            sPerdio.close();
            s.setTitle("Crossing Road");
            s.setScene(Inicio.getSceneInicio());
            //s.show();
        });
    }

    public void InicializarTodo() {
        InicializaryAgregar();
        Eventos();
    }

    public Stage getStagePerdio() {

        return sPerdio;
    }

}
