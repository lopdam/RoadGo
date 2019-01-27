/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author lopdam
 */
public class Auto extends ImageView implements Runnable {

    private Thread hilo;

    private int tipomovimiento;//el tipo de movimiento es en el carril que se encuentra
    //ya que la via esta dividida en cuatro
    private int nivel;
    private String color;
    private Timeline line;

    private boolean kill = false;
    private boolean pausar = false;

    public Auto(int nivel, int tipo, String color) {
        super();
        tipomovimiento = tipo;
        this.nivel = nivel;
        this.color = color;
        if (color.equals("Amarillo")) {
            Imagen img = new Imagen("autoamarillo.png", 30, 30);
            super.setImage(img.getImage());
        } else if (color.equals("Azul")) {
            Imagen img = new Imagen("autoazul.png", 30, 30);
            super.setImage(img.getImage());
        } else {
            Imagen img = new Imagen("autorojo.png", 30, 30);
            super.setImage(img.getImage());
        }

    }

    public void startAutoTask() {

        Runnable taskauto = () -> run();
        hilo = new Thread(taskauto);
        hilo.setDaemon(true);
        hilo.start();
    }

    @Override
    public void run() {

        if (tipomovimiento == 1) {

            KeyValue kv1x = new KeyValue(super.translateXProperty(), 330);
            KeyFrame kf1x = new KeyFrame(Duration.ZERO, kv1x);
            KeyValue kv2x = new KeyValue(super.translateXProperty(), -330);
            KeyFrame kf2x = new KeyFrame(Duration.millis(3700 - (nivel * 150)), kv2x);
            KeyValue kv1y = new KeyValue(super.translateYProperty(), -105);
            KeyFrame kf1y = new KeyFrame(Duration.ZERO, kv1y);
            KeyValue kv2y = new KeyValue(super.translateYProperty(), -105);
            KeyFrame kf2y = new KeyFrame(Duration.millis(3700 - (nivel * 150)), kv2y);
            
            // $$ & $$ //
            line = new Timeline(kf1x, kf2x, kf1y, kf2y);

            super.setY(-105);
            super.setX(super.getTranslateX());

            line.setCycleCount(20 + nivel);
            Platform.runLater(() -> {
                line.play();

            });

        } else if (tipomovimiento == 2) {

            KeyValue kv1x = new KeyValue(super.translateXProperty(), 320);
            KeyFrame kf1x = new KeyFrame(Duration.ZERO, kv1x);
            KeyValue kv2x = new KeyValue(super.translateXProperty(), -320);
            KeyFrame kf2x = new KeyFrame(Duration.millis(3250 - (nivel * 150)), kv2x);
            KeyValue kv1y = new KeyValue(super.translateYProperty(), -55);
            KeyFrame kf1y = new KeyFrame(Duration.ZERO, kv1y);
            KeyValue kv2y = new KeyValue(super.translateYProperty(), -55);
            KeyFrame kf2y = new KeyFrame(Duration.millis(3250 - (nivel * 150)), kv2y);
            Timeline line = new Timeline(kf1x, kf2x, kf1y, kf2y);
            super.setY(-55);
            super.setX(super.getTranslateX());
            line.setCycleCount(20 + nivel);

            Platform.runLater(() -> {
                line.play();
            });

        } else if (tipomovimiento == 3) {

            KeyValue kv1x = new KeyValue(super.translateXProperty(), -320);
            KeyFrame kf1x = new KeyFrame(Duration.ZERO, kv1x);
            KeyValue kv2x = new KeyValue(super.translateXProperty(), 320);
            KeyFrame kf2x = new KeyFrame(Duration.millis(3000 - (nivel * 150)), kv2x);
            KeyValue kv1y = new KeyValue(super.translateYProperty(), 65);
            KeyFrame kf1y = new KeyFrame(Duration.ZERO, kv1y);
            KeyValue kv2y = new KeyValue(super.translateYProperty(), 65);
            KeyFrame kf2y = new KeyFrame(Duration.millis(3000 - (nivel * 150)), kv2y);
            Timeline line = new Timeline(kf1x, kf2x, kf1y, kf2y);
            super.setY(65);
            super.setX(super.getTranslateX());
            line.setCycleCount(20 + nivel);
            Platform.runLater(() -> {
                line.play();
            });

        } else {

            KeyValue kv1x = new KeyValue(super.translateXProperty(), -325);
            KeyFrame kf1x = new KeyFrame(Duration.ZERO, kv1x);
            KeyValue kv2x = new KeyValue(super.translateXProperty(), 325);
            KeyFrame kf2x = new KeyFrame(Duration.millis(3500 - (nivel * 150)), kv2x);
            KeyValue kv1y = new KeyValue(super.translateYProperty(), 115);
            KeyFrame kf1y = new KeyFrame(Duration.ZERO, kv1y);
            KeyValue kv2y = new KeyValue(super.translateYProperty(), 115);
            KeyFrame kf2y = new KeyFrame(Duration.millis(3500 - (nivel * 150)), kv2y);
            Timeline line = new Timeline(kf1x, kf2x, kf1y, kf2y);
            super.setY(200);
            super.setX(super.getTranslateX());
            line.setCycleCount(20 + nivel);

            Platform.runLater(() -> {

                line.play();

            });

        }

    }

    public void kill() {
      kill = true;
        pausar = true;
        //line.stop();
    }

    public void pausar() {
        pausar = true;
        //line.stop();
    }

    public void reanudar() {
        pausar = false;
        //line.play();
    }

}
