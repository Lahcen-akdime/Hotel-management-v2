package Util;

import java.util.Scanner;

public class InputUtils {

    Scanner scanner = new Scanner(System.in) ;

    public Integer lireInt(){
        Integer number = null ;
        do {
            try {
                number = scanner.nextInt();
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Tu dois entrer un nombre !");
            }
        }
        while (number.equals(null));
        return number ;
    }

    public Long lireLong(){
        Long number = null ;
        do {
            try {
                number = scanner.nextLong();
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Tu dois entrer un nombre !");
            }
        }
        while (number.equals(null));
        return number ;
    }

    public String lireString(String label){
        System.out.println("Entrer un "+label+" : ");
        String text  ;
        do {
            try {
                text = scanner.nextLine();
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Ce texte est invalid !");
            }
        }
        while (text.isEmpty());
        return text ;
    }

    public Double lireDouble(String label){
        System.out.println("Entrer "+label);
        Double doubleNumber = null ;
        do {
            try {
                doubleNumber = scanner.nextDouble();
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Ce double est invalide !");
            }
        }
        while (doubleNumber.equals(null));
        return doubleNumber ;
    }

}