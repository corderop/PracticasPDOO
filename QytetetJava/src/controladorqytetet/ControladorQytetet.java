/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorqytetet;

import java.util.ArrayList;
import modeloqytetet.EstadoJuego;
import modeloqytetet.MetodoSalirCarcel;
import modeloqytetet.Qytetet;

public class ControladorQytetet {
    private static final ControladorQytetet instance = new ControladorQytetet();
    private static final Qytetet modelo = Qytetet.getInstance();
    private ArrayList<String> nombreJugadores;
    
    private ControladorQytetet(){}
    
    public static ControladorQytetet getInstance(){
        return instance;
    }
    
    public Qytetet getModelo(){
        return modelo;
    }
    
    public ArrayList<String> getNombreJugadores(){
        return nombreJugadores;
    }
    
    public void setNombreJugadores(ArrayList<String> nombres){
        nombreJugadores = nombres;
    }
    
    public ArrayList<Integer> obtenerOperacionesJuegoValidas(){
        ArrayList<Integer> salida = new ArrayList<>();
        
        if(modelo.getJugadores().isEmpty())
                    salida.add(OpcionMenu.INICIARJUEGO.ordinal());
        else
            switch (modelo.getEstadoJuego()) {
                case JA_PREPARADO :  
                    salida.add(OpcionMenu.JUGAR.ordinal());
                    salida.add(OpcionMenu.OBTENERRANKING.ordinal());
                    salida.add(OpcionMenu.MOSTRARJUGADORACTUAL.ordinal());
                    salida.add(OpcionMenu.MOSTRARJUGADORES.ordinal());
                    salida.add(OpcionMenu.MOSTRARTABLERO.ordinal());
                    salida.add(OpcionMenu.TERMINARJUEGO.ordinal());

                    break;
                case JA_PUEDEGESTIONAR:  
                    salida.add(OpcionMenu.VENDERPROPIEDAD.ordinal());
                    salida.add(OpcionMenu.HIPOTECARPROPIEDAD.ordinal());
                    salida.add(OpcionMenu.CANCELARHIPOTECA.ordinal());
                    salida.add(OpcionMenu.EDIFICARCASA.ordinal());
                    salida.add(OpcionMenu.EDIFICARHOTEL.ordinal());
                    salida.add(OpcionMenu.PASARTURNO.ordinal());
                    salida.add(OpcionMenu.OBTENERRANKING.ordinal());
                    salida.add(OpcionMenu.MOSTRARJUGADORACTUAL.ordinal());
                    salida.add(OpcionMenu.MOSTRARJUGADORES.ordinal());
                    salida.add(OpcionMenu.MOSTRARTABLERO.ordinal());
                    salida.add(OpcionMenu.TERMINARJUEGO.ordinal());

                    break;
                case JA_ENCARCELADO:  
                    salida.add(OpcionMenu.PASARTURNO.ordinal());
                    salida.add(OpcionMenu.OBTENERRANKING.ordinal());
                    salida.add(OpcionMenu.MOSTRARJUGADORACTUAL.ordinal());
                    salida.add(OpcionMenu.MOSTRARJUGADORES.ordinal());
                    salida.add(OpcionMenu.MOSTRARTABLERO.ordinal());
                    salida.add(OpcionMenu.TERMINARJUEGO.ordinal());

                    break;
                case JA_CONSORPRESA:
                    salida.add(OpcionMenu.APLICARSORPRESA.ordinal());
                    salida.add(OpcionMenu.OBTENERRANKING.ordinal());
                    salida.add(OpcionMenu.MOSTRARJUGADORACTUAL.ordinal());
                    salida.add(OpcionMenu.MOSTRARJUGADORES.ordinal());
                    salida.add(OpcionMenu.MOSTRARTABLERO.ordinal());
                    salida.add(OpcionMenu.TERMINARJUEGO.ordinal());

                    break;
                case JA_PUEDECOMPRAROGESTIONAR:
                    salida.add(OpcionMenu.VENDERPROPIEDAD.ordinal());
                    salida.add(OpcionMenu.HIPOTECARPROPIEDAD.ordinal());
                    salida.add(OpcionMenu.CANCELARHIPOTECA.ordinal());
                    salida.add(OpcionMenu.EDIFICARCASA.ordinal());
                    salida.add(OpcionMenu.EDIFICARHOTEL.ordinal());
                    salida.add(OpcionMenu.COMPRARTITULOPROPIEDAD.ordinal());
                    salida.add(OpcionMenu.PASARTURNO.ordinal());
                    salida.add(OpcionMenu.OBTENERRANKING.ordinal());
                    salida.add(OpcionMenu.MOSTRARJUGADORACTUAL.ordinal());
                    salida.add(OpcionMenu.MOSTRARJUGADORES.ordinal());
                    salida.add(OpcionMenu.MOSTRARTABLERO.ordinal());
                    salida.add(OpcionMenu.TERMINARJUEGO.ordinal());

                    break;
                case JA_ENCARCELADOCONOPCIONDELIBERTAD:
                    salida.add(OpcionMenu.INTENTARSALIRCARCELPAGANDOLIBERTAD.ordinal());
                    salida.add(OpcionMenu.INTENTARSALIRCARCELTIRANDODADO.ordinal());
                    salida.add(OpcionMenu.OBTENERRANKING.ordinal());
                    salida.add(OpcionMenu.MOSTRARJUGADORACTUAL.ordinal());
                    salida.add(OpcionMenu.MOSTRARJUGADORES.ordinal());
                    salida.add(OpcionMenu.MOSTRARTABLERO.ordinal());
                    salida.add(OpcionMenu.TERMINARJUEGO.ordinal());

                    break;
                case ALGUNJUGADORENBANCARROTA:
                    salida.add(OpcionMenu.OBTENERRANKING.ordinal());

                    break;
                default: 
                    break;
            }
        
        return salida;
    }
    
    public boolean necesitaElegirCasilla(int opcionMenu){
        boolean salida;
        
        switch(OpcionMenu.values()[opcionMenu]){
            case HIPOTECARPROPIEDAD:
            case CANCELARHIPOTECA:
            case EDIFICARCASA:
            case EDIFICARHOTEL:
            case VENDERPROPIEDAD:
                salida = true;
                break;
            default:
                salida = false;
                break;
        }
        
        return salida;
    }
    
    public ArrayList<Integer> obtenerCasillasValidas(int opcionMenu){
        ArrayList<Integer> salida = new ArrayList<>();
        
        switch(OpcionMenu.values()[opcionMenu]){
            case HIPOTECARPROPIEDAD:
                salida = modelo.obtenerPropiedadesJugadorSegunEstadoHipoteca(false);
                break;
            case CANCELARHIPOTECA:
                salida = modelo.obtenerPropiedadesJugadorSegunEstadoHipoteca(true);
                break;
            case EDIFICARCASA:
                salida = modelo.obtenerPropiedadesJugadorSegunEstadoHipoteca(false);
                break;
            case EDIFICARHOTEL:
                salida = modelo.obtenerPropiedadesJugadorSegunEstadoHipoteca(false);
                break;
            case VENDERPROPIEDAD:
                salida = modelo.obtenerPropiedadesJugadorSegunEstadoHipoteca(false);
                break;
            default:
                break;
        }
        
        return salida;
    }
    
    public String realizarOperacion(int opcionElegida, int casillaElegida){
         String salida = "\0";
        
        switch(OpcionMenu.values()[opcionElegida]){
            case INICIARJUEGO:
                modelo.inicializarJuego(nombreJugadores);
                salida = "-------------------"+
                         "\nSe ha iniciado el juego"+
                         "\nEmpieza el jugador: "+modelo.getJugadorActual()+
                         "\n-------------------";
                
                break;
            case JUGAR:
                modelo.jugar();
                salida = "-------------------"+
                         "\nHas tirado el dado y has sacado "+modelo.getValorDado()+
                         "\nHas caído en la casilla: "+modelo.obtenerCasillaJugadorActual()+
                         "\n-------------------";
                
                break;
            case APLICARSORPRESA:
                salida = "-------------------"+
                         "\nHas aplicado la sorpresa: "+modelo.getCartaActual()+
                         "\n-------------------";
                modelo.aplicarSorpresa();
                
                break;
            case INTENTARSALIRCARCELPAGANDOLIBERTAD:
                if(modelo.intentarSalirCarcel(MetodoSalirCarcel.PAGANDOLIBERTAD))   
                    salida = "-------------------"+
                             "\nHas conseguido salir de la carcel :)"+
                             "\n-------------------";
                else
                    salida = "-------------------"+
                             "\nNo has conseguido salir de la carcel por falta de saldo :("+
                             "\n-------------------";
                
                break;
            case INTENTARSALIRCARCELTIRANDODADO:
                if(modelo.intentarSalirCarcel(MetodoSalirCarcel.TIRANDODADO))
                    salida = "-------------------"+
                             "\nHas conseguido salir de la carcel :)"+
                             "\n-------------------";
                else
                    salida = "-------------------"+
                             "\nNo has obtenido el valor necesario y no has conseguido salir de la carcel :("+
                             "\n-------------------";
                
                break;
            case COMPRARTITULOPROPIEDAD:
                if(modelo.comprarTituloPropiedad())
                    salida = "-------------------"+
                             "\nHas comprado la propiedad :)"+
                             "\n-------------------";
                else
                    salida = "-------------------"+
                             "\nNo has podido comprar la propiedad por falta de saldo. :("+
                             "\n-------------------";
                
                break;
            case HIPOTECARPROPIEDAD:
                modelo.hipotecarPropiedad(casillaElegida);
                salida = "-------------------"+
                         "\nPropiedad hipotecada"+
                         "\n-------------------";
                
                break;
            case CANCELARHIPOTECA:
                if(modelo.cancelarHipoteca(casillaElegida))
                    salida = "-------------------"+
                             "\nHipoteca cancelada correctamente"+
                             "\n-------------------";
                else
                    salida = "-------------------"+
                             "\nError. No tienes saldo para cancelarla, no eres propietario "+
                             "\n o no esta hipotecada la propiedad"+
                             "\n-------------------";
                
                break;
            case EDIFICARCASA:
                if(modelo.edificarCasa(casillaElegida))
                    salida = "-------------------"+
                             "\nCasa edificada correctamente"+
                             "\n-------------------";
                else
                    salida = "-------------------"+
                             "\nNo se ha podido edificar la casa"+
                             "\n-------------------";
                
                break;
            case EDIFICARHOTEL:
                if(modelo.edificarHotel(casillaElegida))
                    salida = "-------------------"+
                             "\nHotel edificado correctamente"+
                             "\n-------------------";
                else
                    salida = "-------------------"+
                             "\nNo se ha podido edificar el hotel"+
                             "\n-------------------";
                
                break;
            case VENDERPROPIEDAD:
                modelo.venderPropiedad(casillaElegida);
                salida = "-------------------"+
                         "\nPropiedad vendida"+
                         "\n-------------------";
                break;
            case PASARTURNO:
                modelo.siguienteJugador();
                salida = "-------------------"+
                         "\nTurno de: "+modelo.getJugadorActual()+
                         "\n-------------------";
                
                break;
            case OBTENERRANKING:
                modelo.obtenerRanking();
                break;
            case MOSTRARJUGADORACTUAL:
                salida = "-------------------\n"+
                         modelo.getJugadorActual()+
                         "\n-------------------";
                
                break;
            case MOSTRARJUGADORES:
                salida = "-------------------\n"+
                         modelo.getJugadores()+
                         "\n-------------------";
                
                break;
            case MOSTRARTABLERO:
                salida = "-------------------\n"+
                         modelo.getTablero()+
                         "\n-------------------";
                
                break;
            case TERMINARJUEGO:
                modelo.obtenerRanking();
                salida = "-------------------"+
                         "\nJuego finalizado"+
                         "\n-------------------";
                System.exit(0);
                
                break;
            default:
                break;
        }
        
        return salida;
    }
}
