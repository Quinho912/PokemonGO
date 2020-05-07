/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemongo;

import java.util.*;

/**
 *
 * @author marcomorando
 */
public class Menu_inicial {

    public static Record nuevo = new Record();

    public static void AccesoMenu(String usuario) {

        System.out.println("Login correcto");
        System.out.println("-----------------------------------------");
        Pokemon[] p = new Pokemon[3];
        p[0] = Pokemon.GenerarPokemon("bulbasaur", "Hierba");
        p[1] = Pokemon.GenerarPokemon("charmander", "Fuego");
        p[2] = Pokemon.GenerarPokemon("pikachu", "Eléctrico");

        int opcion;
        Ficheros.ExisteArchivo("Records.dat");

        do {
            System.out.println("1. Cazar Pokemon");
            System.out.println("2. Listar mochila pokemons cazados");
            System.out.println("3. Récord");
            System.out.println("4. Salir");
            System.out.println("Elija una opción");
            Scanner opcionScanner = new Scanner(System.in);
            opcion = opcionScanner.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("========================= CAZAR ======================");
                    int rnd = new Random().nextInt(3);
                    Pokemon pk = p[rnd];//Encuentra Pokemon de la lista
                    AdivinarOMultiplicar(pk, usuario);
                    System.out.println("----------------------------------------------------------");
                    break;

                case 2:
                    System.out.println("===============MOCHILA " + usuario.toUpperCase() + "===============");
                    Usuario.OrdenarMochila(usuario);
                    break;

                case 3:
                    Record compara = Record.CompararRecord(nuevo);
                    Record.MostrarRecord(compara);
                    break;

                case 4:
                    System.out.println("Cerraste tu usuario");
                    break;

                default:
                    System.out.println("Saliste del programa");
                    break;
            }
        } while (opcion != 4);

    }

    public static void AdivinarOMultiplicar(Pokemon pk, String usuario) {

        Scanner sc = new Scanner(System.in);
        System.out.println("1. Version NORMAL");
        System.out.println("2. Version PREMIUM");
        int nop = sc.nextInt();

        switch (nop) {
            case 1:
                System.out.println("===================== VERSION NORMAL ====================");
                if (Usuario.AdivinarCP(pk)) {
                    Pokemon.Mostrar(pk);
                    Ficheros.GenerarLista(usuario, pk);
                }
                break;
            case 2:
                System.out.println("===================== VERSION PREMIUM ====================");
                long rep = pk.CP / 10;
                if (pk.CP < 10) {
                    rep = 1;//evita division por cero (cuando CP del pokemon es menor a 10)
                }
                long fin = Usuario.ControlarTiempo(pk, usuario, rep);
                long promedio = fin / rep;
                System.out.println("Lo hiciste en: " + fin + " segundos");
                System.out.println("Tu promedio es de: " + promedio + " segundos");
                nuevo = Record.GuardarRecord(fin, usuario);

                break;
        }
    }
}
