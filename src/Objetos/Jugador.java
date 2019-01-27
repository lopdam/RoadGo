/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.time.LocalDate;

/**
 *
 * @author lopdam
 */
public class Jugador implements Comparable<Jugador> {

    private String nombre;
    private int tiempo;
    private int monedas;
    private LocalDate fecha;
    private int nivel;
    private String fech;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.fecha = LocalDate.now();
    }

    public Jugador(String nombre, int tiempo, int monedas, String fecha, int nivel) {
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.monedas = monedas;
        this.fech = fecha;
        this.nivel = nivel;
    }

    public void setDatos(int tiempo, int monedas, int nivel) {
        this.tiempo = tiempo;
        this.monedas = monedas;
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return nombre + "," + tiempo + "," + monedas + "," + fecha.toString() + "," + nivel + "\n";
    }
     public String Clasificacion() {
        return nombre + "<>" + tiempo + "<>" + monedas + "<>" + fech + "<>" + nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTiempo() {
        return tiempo;
    }

    public int getMonedas() {
        return monedas;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getNivel() {
        return nivel;
    }

    @Override
    public int compareTo(Jugador o) {
        if (this.getNivel() > o.getNivel()) {
            return -1;
        } else if (this.getNivel() < o.getNivel()) {
            return 1;
        } else {
            if (this.getTiempo() > o.getTiempo()) {
                return 1;
            } else if (this.getTiempo() < o.getTiempo()) {
                return -1;
            } else {

                if (this.getMonedas() > o.getMonedas()) {
                    return -1;
                } else if (this.getMonedas() < o.getMonedas()) {
                    return 1;
                } else {

                    return 0;
                }
            }

        }

    }

}
