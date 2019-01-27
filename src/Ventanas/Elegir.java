/*Cuando se presiona play de la ventana Play se inicia esta ventana.
 Esta Ventana muestra las mascotas disponibles para jugar.
Se elige la masconta y Inicia el juego.
 */
package Ventanas;

/**
 *
 * @author lopdam
 */
import Objetos.Imagen;
import Objetos.Jugador;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Elegir {

    private StackPane rootElegir;
    private Scene sceneElegir;
    private ImageView imgfondo;

    private Label txtElegir;
    private ImageView imgPollo;
    private ImageView imgCerdo;
    private ImageView imgVaca;
    private HBox hboximgs;

    private ToggleGroup rbtns;
    private RadioButton rbtnPollo;
    private RadioButton rbtnCerdo;
    private RadioButton rbtnVaca;
    private HBox hboxrbtns;
    private Button btnIniciar;
    private Label txtmensaje;
    private VBox vbox;

    private ImageView avatar;
    private Jugador jugador;
    private Stage s;

    public Elegir(String nombre, Stage s) {
        this.s = s;
        jugador = new Jugador(nombre);
    }

    private void IniciaryAgregar() {
        rootElegir = new StackPane();
        sceneElegir = new Scene(rootElegir, 400, 400);
        
        Imagen imagenFondo = new Imagen("imggranja2.jpg", 400, 400);
        imgfondo = imagenFondo.getImagen();

        txtElegir = new Label("Elegir Avatar");
        txtElegir.setTextFill(Color.BLUE);
        txtElegir.setStyle("-fx-font: bold 35px Tahoma;" +
        "-fx-wrap-text:true;"); 
        
        Imagen imagenPollo = new Imagen("pollo.png", 120, 160);
        imgPollo = new ImageView();
        imgPollo = imagenPollo.getImagen();

        Imagen imagenCerdo = new Imagen("cerdo.png", 120, 160);
        imgCerdo = new ImageView();
        imgCerdo = imagenCerdo.getImagen();

        Imagen imagenVaca = new Imagen("vaca.png", 120, 160);
        imgVaca = new ImageView();
        imgVaca = imagenVaca.getImagen();
        hboximgs = new HBox(imgPollo, imgCerdo, imgVaca);
        hboximgs.setAlignment(Pos.CENTER);
        hboximgs.setSpacing(10);

        rbtns = new ToggleGroup();

        rbtnPollo = new RadioButton();
        rbtnPollo.setSelected(true);
        rbtnCerdo = new RadioButton();
        rbtnVaca = new RadioButton();
        rbtnPollo.setToggleGroup(rbtns);
        rbtnCerdo.setToggleGroup(rbtns);
        rbtnVaca.setToggleGroup(rbtns);
        hboxrbtns = new HBox(rbtnPollo, rbtnCerdo, rbtnVaca);
        hboxrbtns.setAlignment(Pos.CENTER);
        hboxrbtns.setSpacing(100);
        btnIniciar = new Button("Iniciar");
        
        btnIniciar.setStyle("-fx-background-color: #008CBA;" +
"    -fx-font-size: 15px;" +
"    -fx-border-radius: 5;" +
"    -fx-border-color: white;" +
"    -fx-border-width: 5;" +
"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );" +
"    -fx-background-radius: 50; " +
"    -fx-font-weight: bold;" +
"    -fx-padding: 3 10 3 10;");
        
        txtmensaje = new Label(" ");

        vbox = new VBox(txtElegir, hboximgs, hboxrbtns, btnIniciar, txtmensaje);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);


        rootElegir.getChildren().addAll(imgfondo, vbox);

    }

    private void Eventos() {
        btnIniciar.setOnAction(e -> {
            if (rbtnPollo.isSelected()) {
                Imagen imagenAvatar = new Imagen("polloarriba.png", 40, 40);
                avatar = imagenAvatar.getImagen();
            } else if (rbtnCerdo.isSelected()) {
                Imagen imagenAvatar = new Imagen("cerdoarriba.png", 40, 40);
                avatar = imagenAvatar.getImagen();
            } else {
                Imagen imagenAvatar = new Imagen("vacaarriba.png", 40, 40);
                avatar = imagenAvatar.getImagen();
            }
            Juego scenejuego = new Juego(jugador, avatar, 1, s, 3, 0);
            scenejuego.InicializarTodo();

            if (rbtnPollo.isSelected() || rbtnCerdo.isSelected() || rbtnVaca.isSelected()) {

                s.setScene(scenejuego.getSceneJuego());
            } else {
                txtmensaje.setTextFill(Color.RED);
                txtmensaje.setText("Elegir Avatar");
                        txtmensaje.setStyle("-fx-font: bold 18px  Tahoma;" +
"    -fx-background-color: red;" +
"    -fx-text-fill: white;");
            }
        });
    }

    public void IniciarTodo() {
        IniciaryAgregar();
        Eventos();

    }

    public Scene getSceneElegir() {
        return sceneElegir;
    }

    public ImageView getAvatar() {
        return avatar;
    }
}
