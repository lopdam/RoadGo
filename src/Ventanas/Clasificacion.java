/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Documentos.CargarHistorial;
import Objetos.Jugador;
import javafx.scene.control.Button;
/**
 *
 * @author lopdam
 */
public class Clasificacion {

    private static VBox rootClasificacion;
    private static Scene sceneClasificacion;
    private static Label txtClasificacion;
    private static ListView lista;
    private static Button btnregresar;

    private static void InicializaryAgregar() {
        rootClasificacion=new VBox();
        sceneClasificacion=new Scene(rootClasificacion,400,400);
        txtClasificacion=new Label("Clasificacion");
        lista=new ListView();
        lista.getItems().add("Nombre,Tiempo de Juego,Monedas,Fecha,Nivel");
        for(Jugador juga: CargarHistorial.LeerHistorial()){
        lista.getItems().add(juga.Clasificacion());
        }
        btnregresar=new Button("Regresar");
        rootClasificacion.getChildren().addAll(txtClasificacion,lista,btnregresar);
        rootClasificacion.setSpacing(5);
        rootClasificacion.setAlignment(Pos.CENTER);
        
    }

    private static void Eventos(Stage s) {
        btnregresar.setOnAction(e->{
        s.setScene(Inicio.getSceneInicio());
        });
    }

    public static void InicializarTodo(Stage s) {
        InicializaryAgregar();
        Eventos(s);
    }

    public static Scene getSeceneClasificacion() {
        return sceneClasificacion;
    }

}
