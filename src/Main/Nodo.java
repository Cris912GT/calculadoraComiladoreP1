package Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Cris
 */
public class Nodo {
    //Declaracion de atributos
    private String dato;
    private Nodo next;

    //Constructor
    public Nodo(String dato){
        this.dato=dato;
    }

    //Metodos getter and setters
    public String getDato(){
        return dato;
    }
    public void setDato(String dato){
        this.dato = dato;
    }
    public Nodo getNext(){
        return next;
    }
    public void setNext(Nodo next){
        this.next = next;
    }
 
    //Metodo toString
    public String toString(){
        String s=" "+dato+" ";
        return s;
    }
}
