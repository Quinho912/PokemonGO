/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemongo;

import java.io.*;
import java.util.*;

/**
 *
 * @author marcomorando
 */
public class Usuario {

    String nomusuario;
    String passusuario;

    public static Usuario ObtenerUsuario() {

        Usuario nuevo = new Usuario();
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el nombre del usuario: ");
        nuevo.nomusuario = sc.nextLine();
        System.out.print("Ingrese la contraseña del usuario: ");
        nuevo.passusuario = sc.nextLine();
        return nuevo;
    }

    public static void RegistrarUsuario() {
        Usuario nuevo = ObtenerUsuario();
        File registrou = Ficheros.ExisteArchivo("login_" + nuevo.nomusuario + ".dat");
        try {
            FileWriter texto = new FileWriter(registrou);
            texto.write(nuevo.passusuario);
            texto.close();
            System.out.println("Usuario " + nuevo.nomusuario + " creado");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static Boolean Loguear(String usuario, String password) {
        File registrou = Ficheros.ExisteArchivo("login_" + usuario + ".dat");
        String texto = "";
        try {
            FileReader fr = new FileReader(registrou);
            BufferedReader br = new BufferedReader(fr);
            String temp = "", bread;
            while ((bread = br.readLine()) != null) {
                temp += bread;
            }
            texto = temp;
        } catch (IOException e) {
            System.out.println(e);
        }
        return texto.equals(password);
    }

    public static List<Pokemon> LeerMochila(String usuario) {
        List<Pokemon> pokemons = new ArrayList<>();
        try {
            FileReader fr = new FileReader("mochila_" + usuario + ".dat");
            BufferedReader br = new BufferedReader(fr);
            String bread;
            while ((bread = br.readLine()) != null) {
                String[] temp = bread.split(","); //Separa el registro de pokemons por comas
                Pokemon p = new Pokemon();
                p.Nombre = temp[0];
                p.CP = Integer.parseInt(temp[1]);
                p.Tipo = temp[2];
                pokemons.add(p);
            }
        } catch (IOException e) {
            System.out.println("La mochila está vacía");
        }

        return pokemons;
    }

    public static boolean AdivinarCP(Pokemon pk) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa un numero del 1 al 10, para cazar al pokemon!");
        System.out.println(pk.CP / 10);//Comprobar si caza al POKEMON
        int numusuario = sc.nextInt();
        if ((pk.CP / 10) == numusuario) {
            System.out.println("POKEMON CAZADO!");
            return true;
        } else {
            System.out.println("Se te escapo el pokemon");
            return false;
        }
        //return pk.CP/10==numusuario?true:false; condicional de una sola linea
    }

    public static void OrdenarMochila(String usuario) {

        System.out.println("A. Mostrar ordenado por nombre");
        System.out.println("B. Mostrar ordenado por CP");
        Scanner sc = new Scanner(System.in);
        String menumochila = sc.nextLine();
        switch (menumochila.toUpperCase()) {
            case "A":
                System.out.println("-------ORDENADOS POR NOMBRE-------");
                List<Pokemon> pornom = LeerMochila(usuario);
                Pokemon[] poknom = BurbujaNom(pornom);
                for (Pokemon pok : poknom) {
                    Pokemon.Mostrar(pok);
                }
                System.out.println("============================================");
                break;
            case "B":
                System.out.println("---------ORDENADOS POR CP---------");
                List<Pokemon> porcp = LeerMochila(usuario);
                Pokemon[] pokcp = BurbujaCP(porcp);
                for (Pokemon pok : pokcp) {
                    Pokemon.Mostrar(pok);
                }
                System.out.println("============================================");
                break;
        }
    }

    public static Pokemon[] BurbujaCP(List<Pokemon> desordenado) {
        Pokemon auxiliar;
        Pokemon[] pk = new Pokemon[desordenado.size()];
        desordenado.toArray(pk);
        Pokemon[] ordenado = new Pokemon[desordenado.size()];

        for (int i = 0; i < pk.length - 1; i++) {
            for (int j = 0; j < pk.length - 1; j++) {
                if (pk[j].CP < pk[j + 1].CP) {
                    auxiliar = pk[j];
                    pk[j] = pk[j + 1];
                    pk[j + 1] = auxiliar;
                }
            }
        }
        ordenado = pk;
        return ordenado;
    }

    public static Pokemon[] BurbujaNom(List<Pokemon> desordenado) {
        Pokemon auxiliar;
        Pokemon[] pk = new Pokemon[desordenado.size()];
        desordenado.toArray(pk);
        Pokemon[] ordenado = new Pokemon[desordenado.size()];

        for (int j = 0; j < pk.length - 1; j++) {
            for (int i = j + 1; i < pk.length; i++) {
                if (pk[j].Nombre.compareTo(pk[i].Nombre) > 0) {
                    auxiliar = pk[j];
                    pk[j] = pk[i];
                    pk[i] = auxiliar;
                }
            }
        }
        ordenado = pk;
        return ordenado;
    }

    public static int TablasMultiplicar() {

        int x = new Random().nextInt(10) + 1;
        int y = new Random().nextInt(10) + 1;
        System.out.println(x + " x " + y);
        return x * y;
    }

    public static boolean ResponderMultiplicaciones(long rep) {

        System.out.println("=================================================================");
        System.out.println("Acierta las multiplicaciones para cazar al pokemon: ");
        
        for (int i = 0; i < rep; i++) {
            int multi = TablasMultiplicar();
            System.out.print("La respuesta es: ");
            Scanner sc = new Scanner(System.in);
            int numusuario = sc.nextInt();
            if (multi != numusuario) {
                System.out.println("Respuesta incorrecta, se te escapó el pokemon");
                return false;
            }
            System.out.println("----------------------------------------------");
        }
        return true;
    }

    public static long ControlarTiempo(Pokemon pk, String usuario,long rep) {

        long inicio = System.currentTimeMillis();
        long fin =0;
        if (ResponderMultiplicaciones(rep)) {
            fin = (System.currentTimeMillis() - inicio) / 1000;
            if(fin<60){
            System.out.println("HAS CAZADO AL POKEMON: ");
            Pokemon.Mostrar(pk);
            Ficheros.GenerarLista(usuario, pk);
            }else{
                System.out.println("HAS PERDIDO AL POKEMON: ");
                Pokemon.Mostrar(pk);
                System.out.println("El equipo Rocket te quitó el pokemon");
                System.out.println("========================================================");
            } 
        }
               
        return fin;
    }

}