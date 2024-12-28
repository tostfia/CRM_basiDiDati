package it.crm.bd.other;

public class Printer {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET1= "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    private Printer(){}
    public static void print(String s){
        System.out.print(s);
    }
    public static void println(String s){
        System.out.println(s);
    }
    public static void printlnBlue(String s){
        System.out.println(ANSI_BLUE+s+ANSI_RESET1);
    }
    public static void printBlue(String s){
        System.out.print(ANSI_BLUE+s+ANSI_RESET1);
    }
    public static void printlnGreen(String s){System.out.println(ANSI_GREEN+s+ANSI_RESET1);}
    public static void printGreen(String s){System.out.print(ANSI_GREEN+s+ANSI_RESET1);}
    public static void errorPrint(String s){
        System.out.println(ANSI_RED+s+ANSI_RESET);
    }
}
