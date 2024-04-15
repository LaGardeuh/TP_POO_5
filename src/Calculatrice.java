import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Calculatrice {
    private FileWriter writer;
    public static Scanner scanner = new Scanner(System.in);

    public Calculatrice() throws IOException {
        scanner = new Scanner(System.in);
        writer = new FileWriter("log.txt", true); // Création du FileWriter avec l'option pour ajouter à la fin du
                                                  // fichier
    }

    public static double SaisieNombre() {
        try {
            System.out.print("Ecrire un nombre : ");
            double nombre = scanner.nextDouble();
            return nombre;

        } catch (Exception e) {
            System.out.print("Erreur de saisi\n");
            return SaisieNombre();
        } finally {
        }
    }

    public static double lancementCalcul() throws IOException {
        Calculatrice calc = new Calculatrice(); // Création d'une instance de Calculatrice
        double Nb1 = SaisieNombre();
        String operation = SaisieOperation();
        double Nb2 = SaisieNombre();
        double result = 0;

        try {
            switch (operation) {
                case "+":
                    result = Nb1 + Nb2;
                    break;
                case "-":
                    result = Nb1 - Nb2;
                    break;
                case "*":
                    result = Nb1 * Nb2;
                    break;
                case "/":
                    result = Nb1 / Nb2;
                    break;
                default:
                    throw new OperationException("L'opération n'est pas valide.");
            }
            calc.sauvegarde(operation, result, Nb1, Nb2); // Appel de la méthode sur l'instance de
                                                          // Calculatrice
        } catch (OperationException e) {
            System.out.println(e.getMessage());
        } finally {
            calc.fermerWriter();
        }

        return result;
    }

    public static String SaisieOperation() {
        System.out.print("Veuillez saisir une opération (+, -, *, /) : ");
        return scanner.next();
    }

    public static void fermerScanner() {
        scanner.close();
    }

    // Méthode pour sauvegarder une opération dans le fichier de log
    private void sauvegarde(String operation, double resultat, double Nb1, double Nb2) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(new Date());
        writer.write("Date : " + date + ", Calcul : " + Nb1 + " " + operation + " " + Nb2 + ", Résultat : " + resultat
                + "\n");
    }

    private void fermerWriter() throws IOException {
        writer.close();
    }

}
