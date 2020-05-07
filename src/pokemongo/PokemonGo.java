/*
Usuario: marco
Contraseña: marco
 */
package pokemongo;

import java.util.Scanner;

/**
 *
 * @author marcomorando
 */
public class PokemonGo {

    static String rutaLogo = "logo.pok";
    static String rutaDat = "login_marco.dat";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Creando pokemons

        // Ejecucion del sistema
        System.out.println(Ficheros.Cargar(rutaLogo));
        int op;
        do {
            System.out.println("----------------Login--------------");
            System.out.println("1. Acceder con cuenta");
            System.out.println("2. Registrar nueva cuenta");
            System.out.println("3. Salir");
            op = sc.nextInt();
            switch (op) {
                case 1:
                    Usuario user = Usuario.ObtenerUsuario();
                    if (Usuario.Loguear(user.nomusuario, user.passusuario)) {
                        Menu_inicial.AccesoMenu(user.nomusuario);
                    } else {
                        System.out.println("Login incorrecto");
                    }
                    break;

                case 2:
                    Usuario.RegistrarUsuario();
                    break;
                
                case 3:
                    System.out.println("Has salido del programa");
                    break;
                
                default:
                    System.out.println("Elige una opcion del menu");
                    break;
            }
        } while (op !=3);
    }

}
