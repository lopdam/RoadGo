/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.stage.Stage;

//Importar paquetes del proyecto
import Ventanas.*;

/**
 *
 * @author lopdam
 */
public class CrossingRoads extends Application {
    
    @Override
    public void start(Stage s) {
     Inicio.InicializarTodo(s);
     Inicio.InicializarTodo(s);
     
     Inicio.getSceneInicio().getStylesheets().add(getClass().getResource("Estilos.css").toExternalForm());
     Play.getScenePlay().getStylesheets().add(getClass().getResource("Estilos.css").toExternalForm());
     
     
     s.setScene(Inicio.getSceneInicio());
     s.setTitle("Road Go !!!");
     s.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
    
}
