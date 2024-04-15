//import java.util.Scanner;

import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
        double resultat = Calculatrice.lancementCalcul();
        System.out.println("Le résultat est : " + resultat);
        Calculatrice.fermerScanner();
    }
}