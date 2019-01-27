/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Objetos.Imagen;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author lopdam
 */
public class Pausa {

    private Stage spausa;
    private StackPane rootPausa;
    private Scene scenePausa;
    private ImageView imagenPausa;
    private Label txtPausa;
    private Button btnReanudar;
    private VBox vboxr;
    private Label lb;
    private Juego juego;

    public Pausa(Juego juego) {
        this.juego = juego;
    }

    private void InicializaryAgregar() {

        spausa = new Stage();
        spausa.setTitle("Juego Pausado");
        
        rootPausa = new StackPane();

        scenePausa = new Scene(rootPausa, 300, 200);
        spausa.setScene(scenePausa);

        Imagen img = new Imagen("imggranja1.jpg", 300, 200);

        imagenPausa = img.getImagen();

        txtPausa = new Label("Juego Pausado");
        txtPausa.setTextFill(Color.BLUE);
        txtPausa.setStyle("-fx-font: bold 25px Tahoma;" +
        "-fx-wrap-text:true;"); 
        btnReanudar = new Button("Reanudar");
        btnReanudar.setStyle("-fx-background-color: #008CBA;" +
"    -fx-font-size: 15px;" +
"    -fx-border-radius: 5;" +
"    -fx-border-color: white;" +
"    -fx-border-width: 5;" +
"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );" +
"    -fx-background-radius: 50; " +
"    -fx-font-weight: bold;" +
"    -fx-padding: 3 10 3 10;");
        lb = new Label("");
        vboxr = new VBox(txtPausa, lb, btnReanudar);
        vboxr.setAlignment(Pos.CENTER);
        vboxr.setSpacing(30);
        rootPausa.getChildren().addAll(imagenPausa, vboxr);
    }

    private void Eventos() {
        btnReanudar.setOnAction(e -> {
            juego.ReanudarTodo();
            spausa.close();
        });
    }

    public void InicializarTodo() {
        InicializaryAgregar();
        Eventos();
    }

    public Stage getStagePausa() {

        return spausa;
    }
}