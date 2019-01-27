/*
 *Se Inicia el juego mostando el nivel 1
 */
package Ventanas;

import Objetos.Auto;
import Objetos.Comodin;
import Objetos.Imagen;
import Objetos.Jugador;
import Objetos.Tiempo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author lopdam
 */
public class Juego {

    private VBox rootJuego;
    private Scene sceneJuego;

    //se agrego VBox de pausa
    Circle circle_Pausa;

    private ImageView imgCorazon1;
    private ImageView imgCorazon2;
    private ImageView imgCorazon3;
    private ImageView imgEscudo;
    private ImageView imgCopo;
    private Tiempo txtTiempo;
    private ImageView imgPausa;
    private HBox hboxPanel;
    private boolean muerto=false;
    private boolean gano = false;

    private StackPane panelCentral;
    private ImageView imgCentral;

    private ImageView avatar;
    private Jugador jugador;
    private int posx;
    private int posy;
    private int posax;
    private int posay;
    private int nivel;
    private int tiempo;

    private Auto auto1;

    private Auto auto2;
    private Auto auto3;
    private Auto auto4;

    public Stage s;

    private boolean kill;
    private boolean pausar;

    private Thread HiloVidas;
    private int vidas;
    private int monedas;
    private Comodin comodin;

    public Juego(Jugador jugador, ImageView avatar, int nivel, Stage s, int vidas, int monedas) {
        this.jugador = jugador;
        this.avatar = avatar;
        this.nivel = nivel;
        this.s = s;
        this.monedas = monedas;
        this.vidas = vidas;
        this.s.setTitle("Road Go !!! -- Level " + String.valueOf(nivel));
    }

    public void InicializaryAgregar() {
        monedas = 0;
        rootJuego = new VBox();
        sceneJuego = new Scene(rootJuego, 600, 410);
        avatar.setFitWidth(40);
        avatar.setFitHeight(40);
        Imagen imagenCorazon1 = new Imagen("corazon.png", 50, 50);
        Imagen imagenCorazon2 = new Imagen("corazon.png", 50, 50);
        Imagen imagenCorazon3 = new Imagen("corazon.png", 50, 50);
        Imagen imagenEscudo = new Imagen("escudoblanco.jpg", 50, 50);
        Imagen imagenCopo = new Imagen("copoblanco.png", 50, 50);
        Imagen imagenPausa = new Imagen("pausa.png", 50, 50);
        imgCorazon1 = imagenCorazon1.getImagen();
        imgCorazon2 = imagenCorazon2.getImagen();
        imgCorazon3 = imagenCorazon3.getImagen();
        imgEscudo = imagenEscudo.getImagen();
        imgCopo = imagenCopo.getImagen();
        imgPausa = imagenPausa.getImagen();

        //v se instancio el Circle "circle_Pausa"  que agregé
        circle_Pausa = new Circle();
        circle_Pausa.setFill(new ImagePattern(imgPausa.getImage()));
        circle_Pausa.setRadius(25);

        //^
        txtTiempo = new Tiempo(jugador, nivel, avatar, s);
        txtTiempo.startTimeTask();

        // cambie node "imagen imgPausa" en HBox por nodo "Circle  circle_Pausa " 
        hboxPanel = new HBox(imgCorazon1, imgCorazon2, imgCorazon3, imgEscudo, imgCopo, txtTiempo, circle_Pausa);
        hboxPanel.setSpacing(30);
        hboxPanel.setAlignment(Pos.CENTER);

        Imagen imagenCentral = new Imagen("autopista.png", 600, 360);
        imgCentral = imagenCentral.getImagen();
        posax = 0;
        posay = 155;
        posx = 0;
        posy = 155;
        //ImagePattern pat;
        //pat = new ImagePattern(avatar.getImage());
        //imagenCentral.getImagen().setFill(pat);
        auto1 = new Auto(nivel, 1, "Rojo");
        auto1.startAutoTask();
        auto2 = new Auto(nivel, 2, "Amarillo");
        auto2.startAutoTask();
        auto3 = new Auto(nivel, 3, "Azul");
        auto3.startAutoTask();
        auto4 = new Auto(nivel, 4, "Rojo");
        auto4.startAutoTask();
        comodin = new Comodin();
        comodin.startComodin();
        panelCentral = new StackPane(imgCentral, avatar, auto1, auto2, auto3, auto4);
        panelCentral.getChildren().add(comodin);
        rootJuego.getChildren().addAll(hboxPanel, panelCentral);
        rootJuego.setAlignment(Pos.CENTER);

        if (vidas == 1) {
            Imagen cor3 = new Imagen("corazonblanco.png", 50, 50);
            imgCorazon3.setImage(cor3.getImage());
            Imagen cor2 = new Imagen("corazonblanco.png", 50, 50);
            imgCorazon2.setImage(cor2.getImage());
        } else if (vidas == 2) {
            Imagen cor3 = new Imagen("corazonblanco.png", 50, 50);
            imgCorazon3.setImage(cor3.getImage());

        }
        startVidas();
        KeyValue kv1y = new KeyValue(avatar.translateYProperty(), 0);
        KeyFrame kf1y = new KeyFrame(Duration.ZERO, kv1y);

        KeyValue kv2y = new KeyValue(avatar.translateYProperty(), 170);
        KeyFrame kf2y = new KeyFrame(Duration.millis(1), kv2y);
        Timeline line = new Timeline(kf1y, kf2y);
        line.setCycleCount(1);
        line.play();
  
    }

    public void Eventos() {
        System.out.println("Mover Arriba: W\nMover Abajo: S");
        System.out.println("Mover a la Derecha: A\nMover la Izquierda: D");

        sceneJuego.setOnKeyTyped(e -> {
            

            if (e.getCharacter().equals("s")) {

                KeyValue kv1y = new KeyValue(avatar.translateYProperty(), posay);
                KeyFrame kf1y = new KeyFrame(Duration.ZERO, kv1y);
                posy = posy + 7;
                KeyValue kv2y = new KeyValue(avatar.translateYProperty(), posy);
                KeyFrame kf2y = new KeyFrame(Duration.millis(10), kv2y);

                Timeline line = new Timeline(kf1y, kf2y);
                avatar.setY(posy);

                line.setCycleCount(1);
                line.play();
                posay = posy;
            } else if (e.getCharacter().equals("w")) {

                posay = posy;
                KeyValue kv1y = new KeyValue(avatar.translateYProperty(), posay);
                KeyFrame kf1y = new KeyFrame(Duration.ZERO, kv1y);
                posy = posy - 7;
                KeyValue kv2y = new KeyValue(avatar.translateYProperty(), posy);
                KeyFrame kf2y = new KeyFrame(Duration.millis(10), kv2y);
                Timeline line = new Timeline(kf1y, kf2y);
                avatar.setY(posy);
                line.setCycleCount(1);
                line.play();

            } else if (e.getCharacter().equals("d")) {

                posax = posx;
                KeyValue kv1y = new KeyValue(avatar.translateXProperty(), posax);
                KeyFrame kf1y = new KeyFrame(Duration.ZERO, kv1y);
                posx = posx + 7;
                KeyValue kv2y = new KeyValue(avatar.translateXProperty(), posx);
                KeyFrame kf2y = new KeyFrame(Duration.millis(10), kv2y);
                Timeline line = new Timeline(kf1y, kf2y);
                avatar.setX(posx);

                line.setCycleCount(1);
                line.play();

            } else if (e.getCharacter().equals("a")) {

                posax = posx;
                KeyValue kv1y = new KeyValue(avatar.translateXProperty(), posax);
                KeyFrame kf1y = new KeyFrame(Duration.ZERO, kv1y);
                posx = posx - 7;
                KeyValue kv2y = new KeyValue(avatar.translateXProperty(), posx);
                KeyFrame kf2y = new KeyFrame(Duration.millis(10), kv2y);
                Timeline line = new Timeline(kf1y, kf2y);
                avatar.setX(posx);

                line.setCycleCount(1);
                line.play();

            }

            if (avatar.getY() < -145 && !gano) {
                jugador.setDatos(Integer.parseInt(txtTiempo.getText()), monedas, nivel);
                gano = true;
                KillAll();
                Gano gan = new Gano(jugador, s, nivel, avatar, vidas, monedas);
                gan.InicializarTodo();
                gan.getStageGano().show();
            }

        });

        //$$%$$//trabajar sobre este
        circle_Pausa.setOnMouseClicked(e -> {
            //PausarTodo();
            System.out.println("Juego Pausado");
            Pausa pausa=new Pausa(this);
            pausa.InicializarTodo();
            pausa.getStagePausa().show();

        });
    }

    public void InicializarTodo() {
        InicializaryAgregar();
        Eventos();
    }

    public Scene getSceneJuego() {
        return sceneJuego;
    }

    public void setAvatar(ImageView avatar) {
        this.avatar = avatar;
    }

    public void startVidas() {
        Runnable tas = () -> runVidas();
        HiloVidas = new Thread(tas);
        HiloVidas.setDaemon(true);
        HiloVidas.start();
    }

    public void runVidas() {
        kill = false;
        pausar = false;
        while (!kill) {
            while (!pausar) {
                try {
                    Thread.sleep(1000);       
                Platform.runLater(() -> {
                    try{
                    float posx = (float) avatar.getTranslateX();
                    float posy = (float) avatar.getTranslateY();
                    
                    boolean col1 = ((-70 > posy) && (posy > -140) && (auto1.getTranslateX() - 70 < posx) && (auto1.getTranslateX() + 70 > posx));
                    boolean col2 = ((-20 > posy) && (posy > -90) && (auto2.getTranslateX() - 70 < posx) && (auto2.getTranslateX() + 70 > posx));
                    boolean col3 = ((100 > posy) && (posy > 20) && (auto3.getTranslateX() - 70 < posx) && (auto3.getTranslateX() + 70 > posx));
                    boolean col4 = ((150 > posy) && (posy > 80) && (auto4.getTranslateX() - 70 < posx) && (auto4.getTranslateX() + 70 > posx));
                    
                    if ((comodin.getTranslateX() - 65 < posx) && (comodin.getTranslateX() + 65 > posx) && (comodin.getTranslateY() - 65 < posy) && (comodin.getTranslateY() + 65 > posy)) {
                        
                        if (comodin.getNombre().equals("moneda")) {
                            monedas = monedas + 1;
                            comodin.setVisible(false);
                        } else if (comodin.getNombre().equals("copo")) {
                            Imagen esc = new Imagen("copo.png", 50, 50);
                            imgCopo.setImage(esc.getImage());
                            comodin.setVisible(false);
                            PausarTodo();
                            Thread.sleep(2000);
                            ReanudarTodo();
                            Imagen imagencopo = new Imagen("copoblanco.png", 50, 50);
                            imgCopo.setImage(imagencopo.getImage());
                        } else {
                            Imagen es = new Imagen("escudo.png", 50, 50);
                            imgEscudo.setImage(es.getImage());
                            comodin.setVisible(false);
                            Thread.sleep(2000);
                            Imagen imagenEscudo = new Imagen("escudoblanco.jpg", 50, 50);
                            imgEscudo.setImage(imagenEscudo.getImage());
                        }
                    }
                    
                    if (col1 || col2 || col3 || col4) {
                        vidas = vidas - 1;
                        
                        if (vidas == 2) {
                            Imagen cor3 = new Imagen("corazonblanco.png", 50, 50);
                            imgCorazon3.setImage(cor3.getImage());
                            
                        } else if (vidas == 1) {
                            Imagen cor2 = new Imagen("corazonblanco.png", 50, 50);
                            imgCorazon2.setImage(cor2.getImage());
                            
                        } else if (vidas == 0 && !muerto) {
                            Imagen cor1 = new Imagen("corazonblanco.png", 50, 50);
                            imgCorazon1.setImage(cor1.getImage());
                            muerto=true;
                            //DetenerTodo();
                            KillAll();
                            Perdio per = new Perdio(jugador, nivel, avatar, s);
                            per.InicializarTodo();
                            per.getStagePerdio().show();
                            
                        }
                                  Thread.sleep(700);
                    }
                    }
                    catch(InterruptedException e){System.out.println("Class Juego: Platform");}
                });
                
                Thread.sleep(0, 1);
                } catch (InterruptedException ex) {
                    System.out.println("Class Juego: Hilo runVidas");
                }
            }
        }
    }

    public void PausarTodo() {
        txtTiempo.PausarTiempo();
        PausarVidas();
        comodin.Pausar();
        auto1.pausar();
        auto2.pausar();
        auto3.pausar();
        auto4.pausar();

    }

    public void KillAll() {
        txtTiempo.kill();
        comodin.kill();
        KillVidas();
        auto1.kill();
        auto2.kill();
        auto3.kill();
        auto4.kill();

    }

    public void ReanudarTodo() {
        System.out.println("Reanudo");
        txtTiempo.ReanudarTiempo();
        comodin.Reanudar();
        ReanudarVidas();
        auto1.reanudar();
        auto2.reanudar();
        auto3.reanudar();
        auto4.reanudar();

    }

    public void KillVidas() {
        kill = true;
        pausar = true;
    }

    public void PausarVidas() {
        pausar = true;
    }

    public void ReanudarVidas() {
        pausar = false;
    }

}
