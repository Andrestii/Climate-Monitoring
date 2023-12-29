/* DE PAOLI LORENZO 753577
 * ONESTI ANDREA 754771
 * RIZZO MATTIA 755403
 * WU WEILI 752602 */

package climatemonitoring;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import prog.io.ConsoleInputManager;

public class ClimateMonitor { // Classe main

    static String path = "./CoordinateMonitoraggio.csv"; // Percorso relativo file csv
    static String riga = "";
    public static List<AreaInteresse> areeInteresse;
    public static List<CentroMonitoraggio> centriMonitoraggio;

    public static void main(String[] args) throws Exception {
        
        // System.out.println("Working Directory = " + System.getProperty("user.dir"));
        // Inserisco tutte le aree di interesse nella lista
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            areeInteresse = new ArrayList<AreaInteresse>();

            String[] luoghi = new String[4];

            while ((riga = br.readLine()) != null) {
                luoghi = riga.split(";");
                // System.out.print(luoghi[0] + " " + luoghi[1] + " " + luoghi[2] + " " + luoghi[3] + "\n"); // STAMPA DI CONTROLLO
                areeInteresse.add(new AreaInteresse(luoghi[0], luoghi[1], luoghi[2], luoghi[3])); // Riempiamo la lista
                // areeInteresse.forEach(System.out::println); // STAMPA DI CONTROLLO
            }

            // System.out.print(areeInteresse.size()); // STAMPA DI CONTROLLO (Dimensione lista)

            br.close();

        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
        // PROGRAMMA
        ConsoleInputManager in = new ConsoleInputManager();
        System.out.println("\nBENVENUTO! Che cosa vuoi fare?\n1)Cerca area geografica per nome\n2)Cerca area geografica per coordinate\n3)Registrati\n4)Login\n5)Esci");
        String s = in.readLine("Scelta: ");

        String citta, stato, latitudine, longitudine;
        Comune c = new Comune();

        switch(s) {
            case "1":
                citta = in.readLine("Inserire città: ");
                stato = in.readLine("Inserire stato: ");
                System.out.println(c.CercaAreaGeograficaLuogo(citta, stato).toString());
                break;
            case "2":
                latitudine = in.readLine("Inserire latitudine: ");
                longitudine = in.readLine("Inserire longitudine: ");
                System.out.println(c.CercaAreaGeograficaCoordinate(latitudine, longitudine).toString());
        }

        // CERCA AREA GEOGRAFICA (NOME O COORDINATE) --> VISUALIZZA AREA GEOGRAFICA
        // REGISTRAZIONE e LOGIN --> INTERFACCIA OPERATORE

    }
}
