/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author lopdam
 */
public class AcercaDe {
    
    private static Stage sAcercaDe;
    private static VBox rootAcercaDe;
    private static Scene sceneAcercaDe;
    private static Label txtAcerca;
    private static Label txtmensaje;
    private static Button btnAceptar;
    
    private static void InicializaryAgregar(){
    
    sAcercaDe=new Stage();
    rootAcercaDe=new VBox();
    sceneAcercaDe=new Scene(rootAcercaDe,250,175);
    sAcercaDe.setScene(sceneAcercaDe);
    sAcercaDe.setTitle("Acerca De");
    
    rootAcercaDe.setAlignment(Pos.CENTER);
    rootAcercaDe.setSpacing(5);
    
    txtAcerca=new Label("Acerca De");
    txtAcerca.setTextFill(Color.BLUE);
    
    txtmensaje=new Label("Este Software fue desarrollado \npor estudiantes de \nPogramacion Orientada a Objetos.");
    btnAceptar=new Button("Aceptar");
    
    rootAcercaDe.getChildren().addAll(txtAcerca,txtmensaje,btnAceptar);
    }
    
    private static void Eventos(){
    btnAceptar.setOnAction(e->{ 
    sAcercaDe.close();
    });
    }
    
    public static void InicializarTodo(){
    InicializaryAgregar();
    Eventos();
    }
    
    public static Stage getSAcercaDe(){
    return sAcercaDe;
    }
}
