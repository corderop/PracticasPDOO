/**
 * Practica de:
 * 
 * Francisco Beltrán Sánchez
 * Pablo Cordero Romero
 */
package modeloqytetet;
import java.util.Random;

public class Dado {
    private static final Dado instance = new Dado();
    private int valor;
    
    int tirar(){
        Random rnd = new Random();
        valor = rnd.nextInt(6)+1;
        
        return valor;
    }
    
    private Dado(){
        valor = 0;
    
    }
    
    public int getValor(){
        return valor;
    }
    
    public static Dado getInstance(){
        return instance;
    }
    
    @Override
    public String toString() {
        return "\n------\nTablero" + "\n\tvalor=" + valor ;
    }
}
