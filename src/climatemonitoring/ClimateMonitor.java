/* DE PAOLI LORENZO 753577
 * ONESTI ANDREA 754771
 * RIZZO MATTIA 755403
 * WU WEILI 752602 
 * SEDE: VARESE */

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
        // PROGRAMMA
        ConsoleInputManager in = new ConsoleInputManager();
        Comune c = new Comune();
        String s;
        do {
            // Inserisco tutte le aree di interesse nella lista
            areeInteresse = Operatore.CreaListaAreeInteresse();
            System.out.println("\n------------------------------------------\nBENVENUTO! Che cosa vuoi fare?\n1) Cerca area geografica per nome\n2) Cerca area geografica per coordinate\n3) Registrati\n4) Login\n5) Esci\n------------------------------------------");
            s = in.readLine("\nScelta: ");
            switch(s) {
            case "1":   // CERCA AREA GEOGRAFICA PER NOME
                String citta = in.readLine("Inserire città: ");
                String stato = in.readLine("Inserire stato: ");
                c.VisualizzaAreaGeografica(c.CercaAreaGeograficaLuogo(citta, stato));
                break;
            case "2":   // CERCA AREA GEOGRAFICA PER COORDINATE
                String latitudine = in.readLine("Inserire latitudine: ");
                String longitudine = in.readLine("Inserire longitudine: ");
                c.VisualizzaAreaGeografica(c.CercaAreaGeograficaCoordinate(latitudine, longitudine));
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
                Operatore op = new Operatore(nome, cognome, codFiscale, mail, userid, password, " ");
                op.Registrazione();
                System.out.println("Registrazione avvenuta con successo!");
                break;
            case "4":   // LOGIN
                String username = in.readLine("Inserire username: ");
                String passwd = in.readLine("Inserire password: ");
                if(Operatore.Login(username, passwd)) {
                    System.out.println("Login effettuato con successo");
                    // Menù operatore registrato ...
                    String scelta;
                    do {
                        System.out.println("\n------------------------------------------\nBENVENUTO OPERATORE! Che cosa vuoi fare?\n1) Registra nuovo centro di monitoraggio\n2) Registra nuova area di interesse\n3) Inserisci dei parametri climatici\n4) Indietro\n------------------------------------------");
                        scelta = in.readLine("\nScelta: ");
                        // Trovo l'operatore che ha effettuato il login
                        List<Operatore> opRegistrati = Operatore.CreaListaOperatori();
                        Operatore opRegistrato = null;
                        for (Operatore o : opRegistrati) {
                            if(o.userid.equals(username))
                                opRegistrato = o;
                        } 
                        switch (scelta) {
                            case "1":   // REGISTRA NUOVO CENTRO DI MONITORAGGIO
                                if(opRegistrato.nomecMonitoraggio.equals(" ")) { // Se l'operatore NON ha già un centro di monitoraggio associato
                                    String nomeCM;
                                    do {    // Controllo che non ci sia già un altro centro di monitoraggio con lo stesso nome
                                    nomeCM = in.readLine("Inserire il nome del centro di monitoraggio: ");
                                    if(Operatore.ControllaNomeCentro(nomeCM))
                                        System.out.println("Centro già esistente!");
                                } while(Operatore.ControllaNomeCentro(nomeCM));
                                    String viaCM = in.readLine("Inserire la via del centro di monitoraggio: ");
                                    String ncivicoCM = in.readLine("Inserire il numero civico del centro di monitoraggio: ");
                                    String capCM = in.readLine("Inserire il cap del centro di monitoraggio: ");
                                    String comuneCM = in.readLine("Inserire il comune del centro di monitoraggio: ");
                                    String provinciaCM = in.readLine("Inserire la provincia del centro di monitoraggio: ");
                                    opRegistrato.RegistraCentroAree(new CentroMonitoraggio(nomeCM, viaCM, ncivicoCM, capCM, comuneCM, provinciaCM, ""));
                                    System.out.println("Centro di monitoraggio registrato con successo!");
                                }
                                else {
                                    System.out.println("L'operatore " + username + " ha già un centro di monitoraggio associato!"); 
                                }
                                break;   
                            case "2":   // REGISTRA NUOVA AREA DI INTERESSE
                            if(opRegistrato.nomecMonitoraggio.equals(" ")) { // Se l'operatore NON ha già un centro di monitoraggio associato
                                System.out.println("L'operatore " + username + " NON ha un centro di monitoraggio associato!");
                            }
                            else {  // L'operatore ha un centro di monitoraggio associato e quindi può creare le aree di interesse che monitora quel centro
                                String nomeArea;
                                do {    // Controllo che non ci sia già un'altra area di interesse con lo stesso nome
                                    nomeArea = in.readLine("Inserire il nome dell'area di interesse: ");
                                    if(Operatore.ControllaNomeArea(nomeArea))
                                        System.out.println("Area già esistente!");
                                } while(Operatore.ControllaNomeArea(nomeArea));
                                String statoArea = in.readLine("Inserire lo stato dell'area di interesse: ");
                                String latArea = in.readLine("Inserire la latitudine dell'area di interesse: ");
                                String longArea = in.readLine("Inserire la longitudine dell'area di interesse: ");
                                opRegistrato.RegistraAreaInteresse(nomeArea, statoArea, latArea, longArea);
                                System.out.println("Registrazione effettuata con successo");
                            }   
                                break;
                            case "3":   // INSERISCI PARAMETRI CLIMATICI
                                if(opRegistrato.nomecMonitoraggio.equals(" ")) { // Se l'operatore NON ha già un centro di monitoraggio associato
                                System.out.println("L'operatore " + username + " NON ha un centro di monitoraggio associato!");
                            }
                            else {  // L'operatore ha un centro di monitoraggio associato e quindi può creare le aree di interesse che monitora quel centro
                                String city = in.readLine("Inserire città: ");
                                opRegistrato.InserisciParametriClimatici(city);
                            } 
                                break;
                            default:
                                break;
                        }
                    } while (Integer.parseInt(scelta) != 4);
                }  
                else
                    System.out.println("Username o password errati");
                break;
            default:
                break;
            }
        } while(Integer.parseInt(s) != 5);
    }
}