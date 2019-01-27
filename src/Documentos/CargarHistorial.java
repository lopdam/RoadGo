/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Documentos;

import Objetos.Jugador;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;


/*
 * @author lopdam
 */
public class CargarHistorial {

    public static TreeSet<Jugador> LeerHistorial() {
        TreeSet<Jugador> lista;
        lista = new TreeSet<Jugador>();

        try (FileReader in = new FileReader("src/Documentos/historial.txt");
                BufferedReader bfin = new BufferedReader(in)) {
            String line = bfin.readLine();
            line = bfin.readLine();
            while (line != null) {

                String lis[] = line.split(",");
                //nombre,tiempo de juego,monedas,fecha,nivel

                lista.add(new Jugador(lis[0], Integer.parseInt(lis[1]), Integer.parseInt(lis[2]), lis[3], Integer.parseInt(lis[4])));
                line = bfin.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo Historial no encontrado");
        } catch (IOException e) {
            System.out.println("IOException Reader");
        }
        return lista;

    }

    public static void EscribiHistorial(String linea) {
        try (FileWriter out = new FileWriter("src/Documentos/historial.txt", true);
                BufferedWriter bfout = new BufferedWriter(out)) {
            bfout.write(linea);
        } catch (IOException ex) {
            System.out.println("IOExeptio Writer");
        }

    }

}
