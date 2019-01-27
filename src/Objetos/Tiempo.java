/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Ventanas.Perdio;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author lopdam
 */
public class Tiempo extends Label implements Runnable {

    private Thread HiloTiempo;
    private int tiempo;

    private Jugador jugador;
    private int nivel;
    private ImageView avatar;
    private Stage s;
    private boolean kill;
    private boolean pausar;

    public Tiempo(Jugador jugador, int nivel, ImageView avatar, Stage s) {
        super();
        this.jugador = jugador;
        this.nivel = nivel;
        this.avatar = avatar;
        this.s = s;
    }

    public void startTimeTask() {

        Runnable task = () -> run();
        HiloTiempo = new Thread(task);
        HiloTiempo.setDaemon(true);
        HiloTiempo.start();
    }

    public void PausarTiempo() {

        pausar = true;
    }

    public void ReanudarTiempo() {
        pausar = false;
    }

    public void kill() {
        kill = true;
    }

    @Override
    public void run() {
        kill=false;
        pausar=false;

        try {
            for (tiempo = 0; tiempo <=60; tiempo++) {
                 while(pausar){}
                String tim = String.valueOf(tiempo);
                Platform.runLater(() -> {
                    super.setText(tim);
                    if (tiempo == 60) {
                        Perdio per = new Perdio(jugador, nivel, avatar, s);
                        per.InicializarTodo();
                        per.getStagePerdio().show();
                    }
                });
                while (kill) {
                    tiempo = 61;
                }

                Thread.sleep(1000);

            }

        } catch (InterruptedException e) {
            System.out.println("Hilo time error");
        }

    }

}
