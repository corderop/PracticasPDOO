/*
 * Francisco Beltrán Sánchez
 * Pablo Cordero Romero
 */
package modeloqytetet;

public class Dado {
    private static final Dado instance = new Dado();
    private int valor;
    
    /**
     *  int tirar();
     */
    
    private Dado(){
        valor = 0;
    
    }
    
    public int getValor(){
        return valor;
    }
    
    public static Dado getInstance(){
        return instance;
    }
}
