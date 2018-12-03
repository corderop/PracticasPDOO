/**
 * Practica de:
 * 
 * Francisco Beltrán Sánchez
 * Pablo Cordero Romero
 */
package modeloqytetet;
import java.util.ArrayList;
import java.util.Scanner;

public class PruebaQytetet {
    private static Qytetet juego = Qytetet.getInstance();
    private static final Scanner in = new Scanner(System.in);
    
    private static ArrayList<String> getNombreJugadores(){
        ArrayList<String> salida = new ArrayList<String>(0);
        
        System.out.print("Introduce número de jugadores: ");
        int tam = in.nextInt();
        String aux;
        
        if( tam>=2 && tam<=4){
            for( int i=0 ; i<tam ; i++){
                System.out.print("Jugador "+ i +": ");
                aux = in.next();
                salida.add(aux);
            }
        }
        else
            System.out.println("Tiene que haber entre 2 y 4 jugadores");
        
        return salida;
    }
    
    public static void main(String[] args) {
        juego.inicializarJuego(PruebaQytetet.getNombreJugadores());
        
    }
}
