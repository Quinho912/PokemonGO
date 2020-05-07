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
public class Record {

    String usuario;
    long tiempo;

    public static Record GuardarRecord(long time, String user) {
        Record a = new Record();
        a.tiempo = time;
        a.usuario = user;
        return a;
    }

    public static Record CompararRecord(Record nuevo) {
        Record actual=Record.LeerRecord();
        if(actual.tiempo==0){
            Ficheros.GenerarRecord(nuevo);
            return nuevo;    
        }else if (nuevo.tiempo < actual.tiempo) {
            System.out.println("=========================================================");
            System.out.println("Felicidades conseguiste un nuevo record!!!");
            Ficheros.GenerarRecord(nuevo);
            return nuevo;
        }else {
            System.out.println("=========================================================");
            System.out.println("Lo siento el record sigue siendo de: " + actual.usuario);
            Ficheros.GenerarRecord(actual);
            return actual;
        }
    }

    public static void MostrarRecord(Record a) {
        System.out.println("--------------RECORD ACTUAL--------------");
        System.out.println(a.usuario + " = " + a.tiempo + " segundos.");
        System.out.println("=========================================");
    }

    public static Record LeerRecord() {
        Record actual = new Record();
        try {
            FileReader fr = new FileReader("Records.dat");
            BufferedReader br = new BufferedReader(fr);
            String bread;
            while ((bread = br.readLine()) != null) {
                String[] temp = bread.split(",");
                actual.usuario= temp[0];
                actual.tiempo = Integer.parseInt(temp[1]);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return actual;
    }
}
