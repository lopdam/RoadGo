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
public class Gano {
    
    private Stage sGano;
    private StackPane rootGano;
    private Scene sceneGando;
    private ImageView imgFondo;
    private Label txtGano;
    private Label txtmensaje;
    
    private Button btnContinuar;
    private Button btnSalir;
    private HBox hbox;
    private VBox vboxGano;
    
    private Stage s;
    private Jugador j;
    private int nivel;
    private ImageView avatar;
    private int vidas;
    private int monedas;
    
    public Gano(Jugador j, Stage s, int nivel, ImageView avatar,int vidas,int monedas) {
        this.avatar = avatar;
        this.nivel = nivel;
        this.monedas=monedas;
        this.j = j;
        this.vidas=vidas;
        this.s = s;
    }
    
    private void InicializaryAgregar() {
        sGano = new Stage();
        sGano.setTitle("Gano");
        
        rootGano = new StackPane();
        sceneGando = new Scene(rootGano, 300, 300);
        sGano.setScene(sceneGando);
        
        Imagen img = new Imagen("imggranja3.jpg", 300, 300);
        imgFondo = img.getImagen();
        
        txtGano = new Label("Ha Ganado el Nivel");
        txtGano.setTextFill(Color.BLUE);
        txtGano.setStyle("-fx-font: bold 25px Tahoma;" +
        "-fx-wrap-text:true;"); 
        
        txtmensaje = new Label("Felicitaciones\n" + "Jugador: " + j.getNombre() + "\nFecha: " + j.getFecha() + "\nMonedas: " + j.getMonedas() + "Nivel: " + j.getNivel() + "nTiempo: " + j.getTiempo());
        txtmensaje.setTextFill(Color.RED);
        txtmensaje.setStyle("    -fx-font: bold 18px  Tahoma;" +
"    -fx-background-color: red;" +
"    -fx-text-fill: white;");
        btnContinuar = new Button("Continuar");
        btnSalir = new Button("Salir");
        btnContinuar.setStyle("-fx-background-color: #008CBA;" +
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
        
        hbox = new HBox(btnContinuar, btnSalir);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        
        vboxGano = new VBox(txtGano, txtmensaje, hbox);
        vboxGano.setSpacing(10);
        vboxGano.setAlignment(Pos.CENTER);
        
        rootGano.getChildren().addAll(imgFondo, vboxGano);
        
    }
    
    private void Eventos() {
        btnContinuar.setOnAction(e -> {
            sGano.close();
            Juego ju = new Juego(j, avatar, nivel + 1, s,vidas,monedas);
            ju.InicializarTodo();
            s.setScene(ju.getSceneJuego());
        });
        btnSalir.setOnAction(e -> {
            sGano.close();
            s.setTitle("Crossing Road");
            s.setScene(Inicio.getSceneInicio());
            CargarHistorial.EscribiHistorial(j.toString());
        });
    }
    
    public void InicializarTodo() {
        InicializaryAgregar();
        Eventos();
    }
    
    public Stage getStageGano() {
        
        return sGano;
    }
    
}
