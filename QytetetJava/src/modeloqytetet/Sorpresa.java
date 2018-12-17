/**
 * Practica de:
 * 
 * Francisco Beltrán Sánchez
 * Pablo Cordero Romero
 */
package modeloqytetet;

public class Sorpresa {
    private String texto;
    private TipoSorpresa tipo;
    private int valor;
    
    Sorpresa(String tx, int vl, TipoSorpresa tp){
        this.texto = tx;
        this.tipo = tp;
        this.valor = vl;
    }
    
    String getTexto(){
        return texto;
    }
    
    TipoSorpresa getTipo(){
        return tipo;
    }
    
    int getValor(){
        return valor;
    }
    
    @Override
    public String toString() {
        return "\n-----\nSorpresa:\n" + "\ttexto=" + texto + "\n\tvalor=" + Integer.toString(valor) + "\n\ttipo=" + tipo;
    }

}
