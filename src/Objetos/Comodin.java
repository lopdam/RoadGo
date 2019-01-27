/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author lopdam
 */
public class Comodin extends ImageView {

    private Thread hilo;
    private String nombre;
    private final Imagen copo = new Imagen("copo.png", 30, 30);
    private final Imagen escudo = new Imagen("escudo.png", 30, 30);
    private final Imagen moneda = new Imagen("moneda.png", 30, 30);
    private boolean kill;
    private boolean pausar;

    public Comodin() {
        super();
        super.setFitWidth(30);
        super.setFitHeight(30);
        super.setVisible(false);
        super.setImage(moneda.getImage());
        nombre = "moneda";
    }

    public void startComodin() {
        Runnable task = () -> runComodin();
        hilo = new Thread(task);
        hilo.setDaemon(true);
        hilo.start();
    }

    public void runComodin() {
        kill = false;
        pausar=false;
        try {
            Thread.sleep(3000);
            super.setVisible(true);
        } catch (InterruptedException ex) {
            System.out.println("Class Comodin: Hilo Comodin");
        }
        while (!kill) {
            while (!pausar) {
                Timeline line;

                Random al = new Random();
                int numer = al.nextInt(3);
                int numer2 = al.nextInt(300);
                int numer3 = al.nextInt(3);
                int numer4 = al.nextInt(2);
                if (numer4 == 1) {
                    numer2 = numer2 * (-1);
                } else {
                    numer2 = numer2;
                }

                if (numer == 0) {
                    super.setImage(copo.getImage());
                    setNombre("copo");
                } else if (numer == 1) {
                    super.setImage(escudo.getImage());
                    setNombre("escudo");
                } else {
                    super.setImage(moneda.getImage());
                    setNombre("moneda");
                }

                if (numer3 == 0) {
                    KeyValue kv1x = new KeyValue(super.translateXProperty(), numer2);
                    KeyFrame kf1x = new KeyFrame(Duration.ZERO, kv1x);
                    KeyValue kv2x = new KeyValue(super.translateXProperty(), numer2);
                    KeyFrame kf2x = new KeyFrame(Duration.millis(1000), kv2x);
                    KeyValue kv1y = new KeyValue(super.translateYProperty(), -135);
                    KeyFrame kf1y = new KeyFrame(Duration.ZERO, kv1y);
                    KeyValue kv2y = new KeyValue(super.translateYProperty(), -135);
                    KeyFrame kf2y = new KeyFrame(Duration.millis(1000), kv2y);
                    line = new Timeline(kf1x, kf2x, kf1y, kf2y);

                    super.setY(-135);
                    super.setX(super.getTranslateX());
                    line.setCycleCount(1);

                } else if (numer3 == 1) {
                    KeyValue kv1x = new KeyValue(super.translateXProperty(), numer2);
                    KeyFrame kf1x = new KeyFrame(Duration.ZERO, kv1x);
                    KeyValue kv2x = new KeyValue(super.translateXProperty(), numer2);
                    KeyFrame kf2x = new KeyFrame(Duration.millis(1000), kv2x);
                    KeyValue kv1y = new KeyValue(super.translateYProperty(), 0);
                    KeyFrame kf1y = new KeyFrame(Duration.ZERO, kv1y);
                    KeyValue kv2y = new KeyValue(super.translateYProperty(), 0);
                    KeyFrame kf2y = new KeyFrame(Duration.millis(1000), kv2y);
                    line = new Timeline(kf1x, kf2x, kf1y, kf2y);
                    super.setY(0);
                    super.setX(super.getTranslateX());
                    line.setCycleCount(1);

                } else {
                    KeyValue kv1x = new KeyValue(super.translateXProperty(), numer2);
                    KeyFrame kf1x = new KeyFrame(Duration.ZERO, kv1x);
                    KeyValue kv2x = new KeyValue(super.translateXProperty(), numer2);
                    KeyFrame kf2x = new KeyFrame(Duration.millis(1000), kv2x);
                    KeyValue kv1y = new KeyValue(super.translateYProperty(), 135);
                    KeyFrame kf1y = new KeyFrame(Duration.ZERO, kv1y);
                    KeyValue kv2y = new KeyValue(super.translateYProperty(), 135);
                    KeyFrame kf2y = new KeyFrame(Duration.millis(1000), kv2y);
                    line = new Timeline(kf1x, kf2x, kf1y, kf2y);
                    super.setY(135);
                    super.setX(super.getTranslateX());
                    line.setCycleCount(1);

                }
                Platform.runLater(() -> {
                    line.play();

                });

                try {
                    super.setVisible(true);
                    Thread.sleep(4000);
                    super.setVisible(false);
                    Thread.sleep(6000);
                } catch (InterruptedException ex) {
                    System.out.println("Class Comodin: Hilo Comodin");
                }

            }
        }
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void kill() {
        kill = true;
        pausar = true;
    }

    public void Pausar() {
        pausar = true;
    }

    public void Reanudar() {
        pausar = false;
    }

}
