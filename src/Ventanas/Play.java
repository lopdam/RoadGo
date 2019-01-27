/*
 Esta ventana pide imgresar el monbre del jugador
Hay dos botones Play y Regresar
Play inicia el Juego 
Regresar retorna a la ventana anterior
 */
package Ventanas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author lopdam
 */
public class Play {

    private static StackPane rootPlay;
    private static Scene scenePlay;
    private static FileInputStream fileimg;
    private static Image imagen;
    private static ImageView imgPlay;
    private static Label txtnombre;
    private static TextField boxnombre;
    private static Button btnPlay;
    private static Button btnRegresar;
    private static Label txtmensaje;
    private static VBox vboxPlay;

    private static void InicializaryAgregar() {
        rootPlay = new StackPane();
        scenePlay = new Scene(rootPlay, 400, 400);
        fileimg = null;
        try {
            fileimg = new FileInputStream("src/Recursos/imggranja3.jpg");

        } catch (FileNotFoundException e) {
            System.out.println("Imagen Play no existe");
        }

        imagen = new Image(fileimg);
        imgPlay = new ImageView(imagen);
        imgPlay.setFitWidth(400);
        imgPlay.setFitHeight(400);
        txtnombre = new Label("Ingrese su Nombre");
        txtnombre.setTextFill(Color.BLUE);

        txtnombre.setStyle("-fx-font: bold 35px Tahoma;"
                + "-fx-wrap-text:true;");

        txtnombre.getStyleClass().add("txtRegister");

        boxnombre = new TextField();

        btnPlay = new Button("Play");
        btnRegresar = new Button("Regresar");
        btnPlay.setStyle("-fx-background-color: #008CBA;"
                + "    -fx-font-size: 15px;"
                + "    -fx-border-radius: 5;"
                + "    -fx-border-color: white;"
                + "    -fx-border-width: 5;"
                + "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );"
                + "    -fx-background-radius: 50; "
                + "    -fx-font-weight: bold;"
                + "    -fx-padding: 3 10 3 10;");
        btnRegresar.setStyle("-fx-background-color: #008CBA;"
                + "    -fx-font-size: 15px;"
                + "    -fx-border-radius: 5;"
                + "    -fx-border-color: white;"
                + "    -fx-border-width: 5;"
                + "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );"
                + "    -fx-background-radius: 50; "
                + "    -fx-font-weight: bold;"
                + "    -fx-padding: 3 10 3 10;");
        btnPlay.getStyleClass().add("btnJugar");
        btnRegresar.getStyleClass().add("btnJugar");

        txtmensaje = new Label(" ");
        vboxPlay = new VBox(txtnombre, boxnombre, btnPlay, btnRegresar, txtmensaje);
        vboxPlay.setSpacing(10);
        vboxPlay.setAlignment(Pos.CENTER);
        rootPlay.getChildren().addAll(imgPlay, vboxPlay);

    }

    private static void Eventos(Stage s) {
        btnPlay.setOnAction(e -> {
            Elegir sceneelegir = new Elegir(boxnombre.getText(), s);
            sceneelegir.IniciarTodo();
            if (boxnombre.getText().length() > 0) {
                boxnombre.setText("");
                s.setScene(sceneelegir.getSceneElegir());

            } else {
                txtmensaje.setTextFill(Color.RED);
                txtmensaje.setText("Ingrese su Nombre");
                txtmensaje.getStyleClass().add("btnAvertenciaNombre");
            }
        });
        btnRegresar.setOnAction(e -> {
            s.setScene(Inicio.getSceneInicio());
        });
    }

    public static void InicializarTodo(Stage s) {
        InicializaryAgregar();
        Eventos(s);
    }

    public static Scene getScenePlay() {
        return scenePlay;
    }

}
