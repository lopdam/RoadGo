/*
* Esta es la ventana de inicio de la applicacion
*va a contener los botones
*JUGAR,CLASIFICACION,ACERCADE Y SALIR que dirigiran a las correspondientes scenas y ventanas.
 */
package Ventanas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class Inicio {

    private static StackPane rootInicio;
    private static Scene sceneInicio;
    private static FileInputStream fileimgInicio;
    private static Image imagenInicio;
    private static ImageView imgInicio;
    private static Label txtCrossing;
    private static Button btnJugar;
    private static Button btnClasificacion;
    private static Button btnAcercaDe;
    private static Button btnSalir;
    private static VBox vboxInicio;

    private static void InicializaryAgregar() {
        rootInicio = new StackPane();
        sceneInicio = new Scene(rootInicio, 400, 400);
        fileimgInicio = null;
        try {
            fileimgInicio = new FileInputStream("src/Recursos/imggranja1.jpg");
        } catch (FileNotFoundException e) {
            System.err.println("Img no encontrada");
        }
        imagenInicio = new Image(fileimgInicio);
        imgInicio = new ImageView(imagenInicio);
        imgInicio.setFitWidth(400);
        imgInicio.setFitHeight(400);
        txtCrossing = new Label("Road Go !!!");
        txtCrossing.setTextFill(Color.BLUE);
        txtCrossing.setStyle("-fx-font: bold 35px Tahoma;"
                + "-fx-wrap-text:true;");

        txtCrossing.getStyleClass().add("textInicio");

        btnJugar = new Button("Jugar");
        btnClasificacion = new Button("Clasificacion");
        btnAcercaDe = new Button("Acerca De");
        btnSalir = new Button("Salir");
        btnJugar.setStyle("-fx-background-color: #008CBA;"
                + "    -fx-font-size: 15px;"
                + "    -fx-border-radius: 5;"
                + "    -fx-border-color: white;"
                + "    -fx-border-width: 5;"
                + "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );"
                + "    -fx-background-radius: 50; "
                + "    -fx-font-weight: bold;"
                + "    -fx-padding: 3 10 3 10;");
        btnClasificacion.setStyle("-fx-background-color: #008CBA;"
                + "    -fx-font-size: 15px;"
                + "    -fx-border-radius: 5;"
                + "    -fx-border-color: white;"
                + "    -fx-border-width: 5;"
                + "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );"
                + "    -fx-background-radius: 50; "
                + "    -fx-font-weight: bold;"
                + "    -fx-padding: 3 10 3 10;");
        btnAcercaDe.setStyle("-fx-background-color: #008CBA;"
                + "    -fx-font-size: 15px;"
                + "    -fx-border-radius: 5;"
                + "    -fx-border-color: white;"
                + "    -fx-border-width: 5;"
                + "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );"
                + "    -fx-background-radius: 50; "
                + "    -fx-font-weight: bold;"
                + "    -fx-padding: 3 10 3 10;");
        btnSalir.setStyle("-fx-background-color: #008CBA;"
                + "    -fx-font-size: 15px;"
                + "    -fx-border-radius: 5;"
                + "    -fx-border-color: white;"
                + "    -fx-border-width: 5;"
                + "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );"
                + "    -fx-background-radius: 50; "
                + "    -fx-font-weight: bold;"
                + "    -fx-padding: 3 10 3 10;");
        btnAcercaDe.getStyleClass().add("btnJugar");
        btnClasificacion.getStyleClass().add("btnJugar");
        btnJugar.getStyleClass().add("btnJugar");
        btnSalir.getStyleClass().add("btnJugar");

        vboxInicio = new VBox(txtCrossing, btnJugar, btnClasificacion, btnAcercaDe, btnSalir);
        vboxInicio.setAlignment(Pos.CENTER);
        vboxInicio.setSpacing(10);

        rootInicio.getChildren().addAll(imgInicio, vboxInicio);

    }

    private static void Eventos(Stage s) {
        btnJugar.setOnAction(e -> {
            s.setScene(Play.getScenePlay());
        });
        btnClasificacion.setOnAction(e -> {
            System.out.println("El primer criterio de Ordenamiento es el Nivel\nEl segundo es el Tiempo\nEl tercero es el numero de Monedas");
            s.setScene(Clasificacion.getSeceneClasificacion());
        });
        btnAcercaDe.setOnAction(e -> {
            AcercaDe.getSAcercaDe().show();
        });
        btnSalir.setOnAction(e -> s.close());

    }

    public static void InicializarTodo(Stage s) {
        InicializaryAgregar();
        Play.InicializarTodo(s);
        AcercaDe.InicializarTodo();
        Clasificacion.InicializarTodo(s);
        Eventos(s);
    }

    public static Scene getSceneInicio() {
        return sceneInicio;
    }

}
