/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemongo;

import java.io.*;

/**
 *
 * @author marcomorando
 */
public class Ficheros {
    

    public static String Cargar(String fichero) {
        String texto = "";
        try {
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);
            String temp = "", bread;
            while ((bread = br.readLine()) != null) {
                temp += bread + "\n";
            }
            texto = temp;
        } catch (IOException e) {
            System.out.println(e);
        }
        return texto;
    }

    public static void GenerarLista(String usuario,Pokemon pk) {
        File lista =ExisteArchivo("mochila_" + usuario + ".dat");
        try{
            FileWriter texto= new FileWriter(lista,true);
            BufferedWriter out = new BufferedWriter(texto); 
            out.write(pk.Nombre+","+pk.CP+","+pk.Tipo+"\n"); 
            out.close(); 
            System.out.println("Pokemon ingresado en la mochila");
        }
        catch(IOException e){
            System.out.println("La mochila está vacía");
        }
    }

    public static File ExisteArchivo(String ruta) {

        File lista = new File(ruta);
        boolean exists = lista.exists();
        try{
        if (!exists) {
            lista.createNewFile();
        }
        }
        catch(IOException e){
            System.out.println(e);
        }
        return lista;
    }
    public static void GenerarRecord(Record a) {
        File record =ExisteArchivo("Records.dat");
        try{
            FileWriter texto= new FileWriter(record);
            BufferedWriter out = new BufferedWriter(texto); 
            out.write(a.usuario+","+a.tiempo); 
            out.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    
}
