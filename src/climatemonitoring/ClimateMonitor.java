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

    static String riga = "";
    public static List<AreaInteresse> areeInteresse;
    public static List<CentroMonitoraggio> centriMonitoraggio;

    public static void main(String[] args) throws Exception {
        
        // System.out.println("Working Directory = " + System.getProperty("user.dir"));
        // Inserisco tutte le aree di interesse nella lista
        try {
            BufferedReader br = new BufferedReader(new FileReader("./CoordinateMonitoraggio.csv"));
            areeInteresse = new ArrayList<AreaInteresse>();

            String[] luoghi = new String[4];

            while ((riga = br.readLine()) != null) {
                luoghi = riga.split(";");
                areeInteresse.add(new AreaInteresse(luoghi[0], luoghi[1], luoghi[2], luoghi[3])); // Riempiamo la lista
            }
            br.close();

        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
        // PROGRAMMA
        ConsoleInputManager in = new ConsoleInputManager();
        Comune c = new Comune();
        String s;

        do {
            System.out.println("\nBENVENUTO! Che cosa vuoi fare?\n1)Cerca area geografica per nome\n2)Cerca area geografica per coordinate\n3)Registrati\n4)Login\n5)Esci");
            s = in.readLine("Scelta: ");
            switch(s) {
            case "1":   // CERCA AREA GEOGRAFICA PER NOME
                String citta = in.readLine("Inserire città: ");
                String stato = in.readLine("Inserire stato: ");
                System.out.println(c.CercaAreaGeograficaLuogo(citta, stato).toString());
                break;
            case "2":   // CERCA AREA GEOGRAFICA PER COORDINATE
                String latitudine = in.readLine("Inserire latitudine: ");
                String longitudine = in.readLine("Inserire longitudine: ");
                System.out.println(c.CercaAreaGeograficaCoordinate(latitudine, longitudine).toString());
                break;
            case "3":   // REGISTRAZIONE
                String nome = in.readLine("Inserire nome: ");
                String cognome = in.readLine("Inserire cognome: ");
                String codFiscale = in.readLine("Inserire codice fiscale: ");
                String mail = in.readLine("Inserire una e-mail: ");
                String userid;
                do {    // Controllo sullo username (se è già preso)
                userid = in.readLine("Inserire uno username: ");
                if(Operatore.ControllaUsername(userid))
                    System.out.println("Username già esistente!");
                } while(Operatore.ControllaUsername(userid));
                String password = in.readLine("Inserire una password: ");
                String nomeCM = in.readLine("Inserire il nome del centro di monitoraggio di afferenza: ");
                String viaCM = in.readLine("Inserire la via del centro di monitoraggio: ");
                String ncivicoCM = in.readLine("Inserire il numero civico del centro di monitoraggio: ");
                String capCM = in.readLine("Inserire il cap del centro di monitoraggio: ");
                String comuneCM = in.readLine("Inserire il comune del centro di monitoraggio: ");
                String provinciaCM = in.readLine("Inserire la provincia del centro di monitoraggio: ");
                // Ogni volta che registriamo un operatore gli assegnamo un NUOVO centro di monitoraggio
                Operatore op = new Operatore(nome, cognome, codFiscale, mail, userid, password, nomeCM);
                CentroMonitoraggio cm = new CentroMonitoraggio(nomeCM, viaCM, ncivicoCM, capCM, comuneCM, provinciaCM);
                op.Registrazione();
                // op.RegistraCentroAree(cm);
                System.out.println("Registrazione avvenuta con successo!");
                break;
            case "4":   // LOGIN
                String username = in.readLine("Inserire username: ");
                String passwd = in.readLine("Inserire password: ");
                if(Operatore.Login(username, passwd)) {
                    System.out.println("Login effettuato con successo");
                }
                    // Menù operatore registrato ...

                else
                    System.out.println("Username o password errati");
                break;

            }
        } while(Integer.parseInt(s) <= 5 && Integer.parseInt(s) > 0);
    }
}