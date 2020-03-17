/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import vistas.LexicoJframe;

/**
 *
 * @author Cristian Castillo, Bryan Herrera, Rony Baran, Benjamin Grajeda
 */
public class Main {
    //             VARIABLES QUE DEFINEN LOS COLORES DE LA CONSOLA
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";
    
    
    /*Definicion de variables publicas-------------------------------------------------*/
    static String cadenaProcesada1 = "";
    static boolean startNum = true;
    static boolean insertnum = true;
    static String num =  "";
    static Colas ColaTokens = new Colas();
    static String cadena1;
    static String texto;
    static String error = "\n                           --------------Error Léxico--------------";
    static String errors = "\n \n                       --------------Error Sintactico--------------";
    static String sintactic= "";
    static int NoDeToken=1;
    static String nextToken="";
    static String nextVal = "";
    static String Temp = "";
    static String concerr = "";
    
    public static void main(String [] Args) throws IOException{
        /*Creacion del archivo de texto a utilizar*/
        File f = new File("file.txt");
        if (!f.exists()) {
            f.createNewFile();
        }
        else{
            f.delete();
            f.createNewFile();
        }
         for(UIManager.LookAndFeelInfo laf:UIManager.getInstalledLookAndFeels()){
            if("Nimbus".equals(laf.getName()))
                try {
                UIManager.setLookAndFeel(laf.getClassName());
            } catch (Exception ex) {
            }
        }
         LexicoJframe ejecucion =new LexicoJframe();
         
       
        ejecucion.setVisible(true);
        
       /* ColaTokens.insertar(" ");
        String cadena1 = JOptionPane.showInputDialog("Ingrese la operacion a procesar","Insertar ecuacion");
        SeparacionTokens(cadena1);
        MostrarACP1();
        //comprobarSimbolosDeContencion(cadena1);*//*bryan lo comento*/
        
        System.out.println("\n\n\n");
        //primercaracter();
    }
    
     public static void Main(String Cadena) {/*Recibo la cadena de la caja de texto*/
        ColaTokens.insertar(" ");
        cadena1 = Cadena;
        SeparacionTokens(cadena1);
        MostrarACP1();
        analisisSintactico();
        }
    
    
    /*Metodo utilizado para realizar la validacion de tokens y eliminar espacios en la cadena*/
    private static void SeparacionTokens(String cadena1){
        // Declaracion de la cantidad de tokens analizados
        
        
        //Variable que almacena el valor de los caracteres analizados
        String cadena = cadena1;
        
        //Variable que almacena el valor del caracter a analizar antes de convertirse en token
        String PreToken = "";
        
        //Variables que almacenan los valores de los tokens que deben ser reconocidos
        String tokenSuma = "+";
        String Suma="";
        String tokenResta = "-";
        String Resta="";
        String tokenMultiplicacion = "*";
        String Multiplicacion="";
        String tokenDivision = "/";
        String Division="";
        String tokenParentesisAper = "(";
        String PAper = "";
        String tokenParentesisClaus = ")";
        String PClaus = "";
        String tokenCorcheteAper = "[";
        String CAper = "";
        String tokenCorhceteClaus = "]";
        String CClaus = "";
        String tokenLlaveAper = "{";
        String LLAper = "";
        String tokenLlaveClaus = "}";
        String LLClaus = "";
        String tokenRaiz = "#";
        String Raiz = "";
        String tokenPotencia = "^";
        String Potencia = "";
        
        //Variables de prueba o de uso temporales
        //int numActual=0;
        
        for (int i = 0; i < (cadena.length()); i++){
            char letra = cadena.charAt(i);
            String letraR = Character.toString(letra);
            PreToken = PreToken + letra; 
            //          INSERTAR OPERADORES MATEMATICOS A LA COLA
            if (PreToken.equals(tokenSuma)) {
                insertNumCola();
                Suma="\n El token reconocido es:  +  ";
                texto = texto + Suma;
                //System.out.println("El token reconocido es: " + PreToken);
                insertarACP1(PreToken);
                insertarEnColaTokens(PreToken);
                PreToken = "";
            }
            if (PreToken.equals(tokenResta)) {
                Resta="\n El token reconocido es:  - ";
                texto = texto + Suma;
                insertNumCola();
                //System.out.println("El token reconocido es: " + PreToken);
                insertarACP1(PreToken);
                insertarEnColaTokens(PreToken);
                PreToken = "";
            }
            if (PreToken.equals(tokenMultiplicacion)) {
                insertNumCola();
                Multiplicacion=" \n El token reconocido es:  * ";
                texto = texto + Multiplicacion;
                //System.out.println("El token reconocido es: " + PreToken);
                insertarACP1(PreToken);
                insertarEnColaTokens(PreToken);
                PreToken = "";
            }
            if (PreToken.equals(tokenDivision)) {
                insertNumCola();
                Division="\n El token reconocido es:  /  ";
                texto = texto + Division;
                //System.out.println("El token reconocido es: " + PreToken);
                insertarACP1(PreToken);
                insertarEnColaTokens(PreToken);
                PreToken = "";
            }
            
            
            
            //            INSERTAR SIGNOS DE AGRUPACION A LA COLA
            if (PreToken.equals(tokenParentesisAper)) {
                insertNumCola();
                PAper = "\n El token reconocido es: ( ";
                texto = texto + PAper;
                //LexicoJframe.AnalisisTF.setText("El token reconocido es:" + PreToken);
                //System.out.println("El token reconocido es: " + PreToken);
                insertarACP1(PreToken);
                insertarEnColaTokens(PreToken);
                PreToken = "";
            }
            if (PreToken.equals(tokenParentesisClaus)) {
                insertNumCola();
                PClaus = "\n El token reconocido es: ) ";
                texto = texto + PClaus;
                //System.out.println("El token reconocido es: " + PreToken);
                insertarACP1(PreToken);
                insertarEnColaTokens(PreToken);
                PreToken = "";
            }
            if (PreToken.equals(tokenCorcheteAper)) {
                insertNumCola();
                CAper = "\n El token reconocido es: ["; 
                texto = texto + CAper;
                //System.out.println("El token reconocido es: " + PreToken);
                insertarACP1(PreToken);
                insertarEnColaTokens(PreToken);
                PreToken = "";
            }
            if (PreToken.equals(tokenCorhceteClaus)) {
                insertNumCola();
                CClaus = "\n El token reconocido es: ]"; 
                texto = texto + CClaus;
                //System.out.println("El token reconocido es: " + PreToken);
                insertarACP1(PreToken);
                insertarEnColaTokens(PreToken);
                PreToken = "";
            }
            if (PreToken.equals(tokenLlaveAper)) {
                insertNumCola();
                LLAper = "\n El token reconocido es {";
                texto = texto + LLAper;
                //System.out.println("El token reconocido es: " + PreToken);
                insertarACP1(PreToken);
                insertarEnColaTokens(PreToken);
                PreToken = "";
            }
            if (PreToken.equals(tokenLlaveClaus)) {
                insertNumCola();
                LLClaus = "\n El token reconocido es }";
                texto = texto + LLClaus;
                //System.out.println("El token reconocido es: " + PreToken);
                insertarACP1(PreToken);
                insertarEnColaTokens(PreToken);
                PreToken = "";
            }
            
            
            
            //              ELIMINACION DE ESPACIOS EN BLANCO
            if (PreToken.equals(" ")) {
                String blanco = ("\n Se ha eliminado un espacio en blanco " + PreToken);
                texto = texto +blanco;
                //System.out.println("Se ha eliminado un espacio en blanco" + PreToken);
                PreToken = "";
            }
            
            
            
            // INSERTAR UN NUMERO A LA VARIABLE num PARA SU POSETERIOR INSERCION
            if (esNumero(PreToken)){
                //System.out.println("El token reconocido es un valor numerico: " + PreToken);
                num = num + PreToken;
                //System.out.println("Se ha agragado el digito " + PreToken + " al numero: " + num);
                insertnum = false;
                PreToken = "";
            }
            
            
            
            //                 IGNORA LOS VALORES VACIOS
            if (PreToken.equals("")) {
                PreToken = "";
            }
            
            
            
            //                 IGNORA LOS VALORES NULOS
            if (PreToken.equals(null)) {
                PreToken = "";
            }
            
            
            
            //       LANZA UN ERROR DEBIDO A QUE NO SE ADMITEN LETRAS
            if (esLetra(letraR)){
                String ErrLetra =(" \n ERROR. El caracter en la pocicion " 
                                    + NoDeToken + " es una letra por lo que no se"
                                    + " puede realizar el procesamiento. ");
                error = error + ErrLetra;
                
                /*System.out.println(ANSI_RED + "ERROR. El caracter en la pocicion "    
                                    + NoDeToken + " es una letra por lo que no se"
                                    + " puede realizar el procesamiento."+ ANSI_RESET);*/
                PreToken = "";
            }
            
            
            else{
                //System.out.println("Token no reconocido (" + PreToken + ")");
                PreToken = "";
            }
            
            NoDeToken = NoDeToken +1;
            System.out.println("\n");
            
        }
        LexicoJframe.ErrorTP.setText(error);
        LexicoJframe.LexicoTP.setText(texto);
        NoDeToken = 1;
        insertNumCola();
    }
    
    private static void insertNumCola(){
        if (num != "") {
            insertarACP1(num);
            insertarEnColaTokens(num);
            num ="";
        }
        else{
        }
    }
    /*Metodo para validar si el caracter actual es un numero------------------*/
    private static boolean esNumero(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
    
    /*Metodo para validar si el caracter actual es una letra------------------*/
    private static boolean esLetra(String cadena){
        if (Character.isLetter(cadena.charAt(0))) {
            return true;
        }
        else{
            return false;
        }
    }
    
    /*Metodo para insertar valores en la variable de la primera cadena procesada*/
    public static void insertarACP1(String PreToken){
        cadenaProcesada1 = cadenaProcesada1 + PreToken;
    }

    /*Metodo para mostrar la cadena almacenada en la variable de la primera cadena procesada*/
    public static void MostrarACP1(){
        //LexicoJframe.AnalisisTF.setText("La Cadena procesada 1 es: " + cadenaProcesada1);
        //System.out.println("La Cadena procesada 1 es: " + cadenaProcesada1);
    }
    
    /*Metodo para revisar que los parentesis, corchetes y llaves esten equilibrados*/
    public static void comprobarSimbolosDeContencion(String cadena1){
        String cadena = cadena1;
        String PreToken = "";
        int NoDeParentesis=0;
        int NoDeCorchetes=0;
        int NoDeLlaves=0;
        
        for (int i = 0; i < (cadena.length()); i++){
            char letra = cadena.charAt(i);
            PreToken = PreToken + letra;
            
            if (PreToken.equals("(")) {
                NoDeParentesis = NoDeParentesis + 1;
                PreToken = "";
            }
            else if (PreToken.equals(")")) {
                NoDeParentesis = NoDeParentesis - 1;
                PreToken = "";
            }
            else if (PreToken.equals("[")) {
                NoDeCorchetes = NoDeCorchetes + 1;
                PreToken = "";
            }
            else if (PreToken.equals("]")) {
                NoDeCorchetes = NoDeCorchetes - 1;
                PreToken = "";
            }
            else if (PreToken.equals("{")) {
                NoDeLlaves = NoDeLlaves + 1;
                PreToken = "";
            }
            else if (PreToken.equals("}")) {
                NoDeLlaves = NoDeLlaves - 1;
                PreToken = "";
            }
            else{
                PreToken = "";
            }
        }
        System.out.println("Cantidad de parentesis: " + NoDeParentesis);
        System.out.println("Cantidad de corchetes: " + NoDeCorchetes);
        System.out.println("Cantidad de llaves: " + NoDeLlaves);
    }
    
    public static void insertarEnColaTokens(String pretoken){
        String token = pretoken;
        ColaTokens.insertar(token);
        String Prueba = ("\n Los tokens almacenados en cola son: " + token);
        texto = texto + Prueba;
        //System.out.println("Los tokens almacenados en cola son: " + ColaTokens.toString());
    }
    
    public static void acumularNumero(String token){
        if (startNum) {
            startNum = false;
            num = num + token;
        }
        else {
            num = num + token;
        }
    }
    
    //VERIFICAMOS QUE EL PRIMER CARACTER SEA VALIDO
    public static boolean validarPrimerCaracter(){
        if(primercaracter().equals("er")){
            String Temp = ("ERROR EL PRIMER CARACTER NO ES VALIDO");
            error= error + Temp;
            return true;
        }
        if(primercaracter().equals("")){
        }
        return true;
    }
    
    //ESTABLECEMOS LAS REGLAS QUE SEGUIRA EL COMPILADOR----------------------------------------------------------------------
    public static String primercaracter(){
        //          DEFINIMOS LAS VARIABLES A UTILIZAR
        String tokenActual = "";
        ColaTokens.extraer();
        tokenActual = ColaTokens.extraer();
        
        if((esNumero(tokenActual))){
            return "num";
        }
        else if(tokenActual.equals("(")){
            return "pA";
        }
        else if(tokenActual.equals("{")){
            return "lA";
        }
        else if(tokenActual.equals("[")){
            return "cA";
        }
        else if(tokenActual.equals("-")){
            return "sM";
        }
        else {
            String Temp = ("ERROR: El primer caracter de la operacion no es valido....");
            error = error + Temp;
            //LexicoJframe.ErrorTP.setText( "ERROR: El primer caracter de la operacion no es valido....");
            //System.out.println(ANSI_RED + " ERROR: El primer caracter de la operacion no es valido....");
            
            return "er";
        }
        /*
        try {
            
        }
        catch(Exception exc){
        }
        */  
        
    }
    
    public static void analisisSintactico(){
        
        String token="";
        
        int lim = ColaTokens.contar()-1;
        
        System.out.println("limite: " + lim);
        
        for ( int k = 0; k <= lim; k++){
            token = ColaTokens.extraer();
            nextVal = validar(token, k);
            token = "";
        }
    }
    
    public static String validar(String token, int notoken){
        String tokenA = token;
        String tokenB ="";
        String esp;
        int nopos = notoken;
        
        if (nopos == 0){
            return "";
        }
        
        if (nopos == 1){
            if (esNumero(tokenA)) {
                esp = " + - * / ^ ( { [ ";
                Temp = ("\n Valor reconocido: " + tokenA + " valores esperado: " + esp);
                sintactic = sintactic + Temp;
                //System.out.println("Valor reconocido: [" + tokenA + "] valores esperado: " + esp);
                return esp;
            }
            if (tokenA.equals("(")) {
                esp = " - ( { [ @ ";
                Temp = ("\n Valor reconocido: " + tokenA + " valores esperado: " + esp);
                sintactic = sintactic + Temp;
                //System.out.println("Valor reconocido: [" + tokenA + "] valores esperado: " + esp);
                return esp;
            }
            if (tokenA.equals("{")) {
                esp = " - ( { [ @ ";
                Temp = ("\n Valor reconocido: " + tokenA + " valores esperado: " + esp);
                sintactic = sintactic + Temp;
                //System.out.println("Valor reconocido: [" + tokenA + "] valores esperado: " + esp);
                return esp;
            }
            if (tokenA.equals("[")) {
                esp = " - ( { [ @ ";
                Temp = ("\n Valor reconocido: " + tokenA + " valores esperado: " + esp);
                sintactic = sintactic + Temp;
                //System.out.println("Valor reconocido: [" + tokenA + "] valores esperado: " + esp);
                return esp;
            }
            if (tokenA.equals("-")) {
                esp = " @ ( { [ ";
                Temp = ("\n Valor reconocido: " + tokenA + " valores esperado: " + esp);
                sintactic = sintactic + Temp;
                //System.out.println("Valor reconocido: [" + tokenA + "] valores esperado: " + esp);
                return esp;
            }
            else {
                String temp = ("\n ERROR el token "+tokenA+" en la pocicion "+ nopos +" es invalido en la estructura.");
                errors = errors + temp;
                //System.out.println(ANSI_RED + "ERROR el token "+tokenA+" en la pocicion "+ nopos +" es invalido en la estructura." + ANSI_RESET);
            }  
        }
        
        else {
            for (int i = 0; i < (nextVal.length()); i++){
                char letra = nextVal.charAt(i);
                tokenB = Character.toString(letra);
                
                //REGLAS APLICADAS A NUMEROS
                if (tokenB == "@"){
                    if (tokenA == tokenB){
                        esp = " + - * / ^ # (  { [ ) } ] ";
                        Temp = ("\n Valor reconocido: " + tokenA + " valores esperado: " + esp);
                        sintactic = sintactic + Temp;
                        return esp;
                    }
                }
                
                // REGLAS APLICADAS A OPERADORES
                if (tokenB == "+"){
                    if (tokenA == tokenB){
                        esp = " @ ( { [ ";
                        Temp = ("\n Valor reconocido: " + tokenA + " valores esperado: " + esp);
                        sintactic = sintactic + Temp;
                        return esp;
                    }
                }
                if (tokenB == "-"){
                    if (tokenA == tokenB){
                        esp = " @ ( { [ ";
                        Temp = ("\n Valor reconocido: " + tokenA + " valores esperado: " + esp);
                        sintactic = sintactic + Temp;
                        return esp;
                        //return "@({[";
                    }
                }
                if (tokenB == "*"){
                    if (tokenA == tokenB){
                        esp = " @ ( { [ - ";
                        Temp = ("\n Valor reconocido: " + tokenA + " valores esperado: " + esp);
                        sintactic = sintactic + Temp;
                        return esp;
                        //return "@({[-";
                    }
                }
                if (tokenB == "/"){
                    if (tokenA == tokenB){
                        esp = " @ ( { [ - ";
                        Temp = ("\n Valor reconocido: " + tokenA + " valores esperado: " + esp);
                        sintactic = sintactic + Temp;
                        return esp;
                        //return "@({[-";
                    }
                }
                if (tokenB == "^"){
                    if (tokenA == tokenB){
                        esp = " @ ( { [ ";
                        Temp = ("\n Valor reconocido: " + tokenA + " valores esperado: " + esp);
                        sintactic = sintactic + Temp;
                        return esp;
                        //return "@({[";
                    }
                }
                if (tokenB == "#"){
                    if (tokenA == tokenB){
                        esp = " @ ( { [ ";
                        Temp = ("\n Valor reconocido: " + tokenA + " valores esperado: " + esp);
                        sintactic = sintactic + Temp;
                        return esp;
                        //return "@({[";
                    }
                }
                
                // REGLAS QUE SE APLICAN A LOS SIMBOLOS DE AGRUPACION
                if (tokenB == "("){
                    if (tokenA == tokenB){
                        esp = " @ ( { [ - ";
                        Temp = ("\n Valor reconocido: " + tokenA + " valores esperado: " + esp);
                        sintactic = sintactic + Temp;
                        return esp;
                        //return "@({[-";
                    }
                }
                if (tokenB == "{"){
                    if (tokenA == tokenB){
                        esp = " @ ( { [ - ";
                        Temp = ("\n Valor reconocido: " + tokenA + " valores esperado: " + esp);
                        sintactic = sintactic + Temp;
                        return esp;
                        //return "@({[-";
                    }
                }
                if (tokenB == "["){
                    if (tokenA == tokenB){
                        esp = " @ ( { [ - ";
                        Temp = ("Valor reconocido: " + tokenA + " valores esperado: " + esp);
                        sintactic = sintactic + Temp;
                        return esp;
                        //return "@({[-";
                    }
                }
                if (tokenB == ")"){
                    if (tokenA == tokenB){
                        esp = " @ ( { [ - ";
                        Temp = ("\n Valor reconocido: " + tokenA + " valores esperado: " + esp);
                        sintactic = sintactic + Temp;
                        return esp;
                        //return "@({[-";
                    }
                }
                if (tokenB == "}"){
                    if (tokenA == tokenB){
                        esp = " @ ( { [ - ";
                        Temp = ("\n Valor reconocido: " + tokenA + " valores esperado: " + esp);
                        sintactic = sintactic + Temp;
                        return esp;
                        //return "@({[-";
                    }
                }
                if (tokenB == "]"){
                    if (tokenA == tokenB){
                        esp = " @ ( { [ - ";
                        Temp = ("\n Valor reconocido: [ " + tokenA + " ] valores esperado: " + esp);
                        sintactic = sintactic + Temp;
                        return esp;
                        //return "@({[-";
                    }
                }
            }
        }
        LexicoJframe.SintacticoTP.setText(sintactic);
        concerr = error + errors;
        LexicoJframe.ErrorTP.setText(concerr);
        return "error";
    }
    
}
