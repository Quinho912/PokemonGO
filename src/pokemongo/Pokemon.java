/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemongo;

import java.util.Random;


/**
 *
 * @author marcomorando
 */
public class Pokemon {

    String Nombre;
    int CP;
    String Tipo;

    public Pokemon(){};
    
    public static Pokemon GenerarPokemon(String nombre, String tipo) {
        Pokemon aleatorio= new Pokemon();
        aleatorio.Nombre = nombre;
        aleatorio.CP = new Random().nextInt(100) + 1;
        aleatorio.Tipo = tipo;
        return aleatorio;
    }

    public static void Mostrar(Pokemon pok) {
        System.out.println(Ficheros.Cargar(pok.Nombre + ".pok"));
        System.out.println("---------------- Nombre: " + pok.Nombre + " -------------");
        System.out.println("---------------- CP: " + pok.CP + " ----------------------");
        System.out.println("---------------- Tipo: " + pok.Tipo + " -------------");
    }

}
