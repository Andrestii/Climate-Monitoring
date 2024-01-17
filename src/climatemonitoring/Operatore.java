/* DE PAOLI LORENZO 753577
 * ONESTI ANDREA 754771
 * RIZZO MATTIA 755403
 * WU WEILI 752602 
 * SEDE: VARESE*/

package climatemonitoring;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
// import java.sql.Date;
// import java.io.FileNotFoundException;

import prog.io.ConsoleInputManager;

public class Operatore extends Comune {

    /*di creare una o più aree di interesse (tramite coordinate geografiche), raggrupparle per centro di monitoraggio e annotarle 
    singolarmente con i parametri forniti ad un operatore in una specifica data secondo i parametri dati nella tabella precedente */

    public String nome, cognome, codFiscale, mail, userid, password, nomecMonitoraggio;

    public Operatore (String nome, String cognome, String codFiscale, String mail, String userid, String password, String nomecMonitoraggio) { 
        this.nome = nome;
        this.cognome = cognome;
        this.codFiscale = codFiscale;
        this.mail = mail;
        this.userid = userid;
        this.password = password;
        this.nomecMonitoraggio = nomecMonitoraggio;
    }

    // CercaAreaGeografica(nome) prendi da superclasse comune           OK

    // CercaAreaGeografica(coordinate) prendi da superclasse comune     OK

    // VisualizzaAreaGeografica() prendi da superclasse comune          OK

    public static List<AreaInteresse> CreaListaAreeInteresse() {    // Dal file .csv prende tutte le aree di interesse e le mette in una lista
        
        List<AreaInteresse> areeInteresse = new ArrayList<AreaInteresse>();
        String riga = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader("./CoordinateMonitoraggio.csv"));
            areeInteresse = new ArrayList<AreaInteresse>();
            String[] luoghi = new String[4];

            while ((riga = br.readLine()) != null) {
                luoghi = riga.split(";");
                areeInteresse.add(new AreaInteresse(luoghi[0], luoghi[1], luoghi[2], luoghi[3])); // Riempiamo la lista
            }
            br.close();

        } catch (Exception e) {
            System.err.println(e);
        }
        return areeInteresse;
    }

    public static List<Operatore> CreaListaOperatori() {   // Dal file .csv prende tutti gli operatori e li mette in una lista

        List<Operatore> operatori = new ArrayList<Operatore>();
        String riga = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader("./OperatoriRegistrati.csv"));
            operatori = new ArrayList<Operatore>();
            String[] datiOP = new String[7];

            while ((riga = br.readLine()) != null) {
                datiOP = riga.split(";");
                operatori.add(new Operatore(datiOP[0], datiOP[1], datiOP[2], datiOP[3], datiOP[4], datiOP[5], datiOP[6])); // Riempiamo la lista
            }
            br.close();

        } catch (Exception e) {
            System.err.println(e);
        }
        return operatori;
    }

    public static List<CentroMonitoraggio> CreaListaCentriMonitoraggio() {   // Dal file .csv prende tutti gli operatori e li mette in una lista

        List<CentroMonitoraggio> centriMonitoraggio = new ArrayList<CentroMonitoraggio>();
        String riga = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader("./CentroMonitoraggio.csv"));
            centriMonitoraggio = new ArrayList<CentroMonitoraggio>();
            String[] datiCM = new String[7];

            while ((riga = br.readLine()) != null) {
                datiCM = riga.split(";");
                centriMonitoraggio.add(new CentroMonitoraggio(datiCM[0], datiCM[1], datiCM[2], datiCM[3], datiCM[4], datiCM[5], datiCM[6])); // Riempiamo la lista
            }
            br.close();

        } catch (Exception e) {
            System.err.println(e);
        }
        return centriMonitoraggio;
    }

    public static boolean ControllaUsername(String user) {   // Controlla se ci sono più utenti con lo stesso username

        List<Operatore> operatori = CreaListaOperatori();
        if(!operatori.isEmpty())
            for (Operatore op : operatori) 
                if(user.equals(op.userid))
                    return true;    // Restituisce true se c'è già un utente con quello username
        return false;   // Restituisce false se quello username non è ancora stato occupato da nessuno
    }

    public static boolean ControllaNomeArea(String nomeArea) {   // Controlla se ci sono più aree con lo stesso nome

        List<AreaInteresse> aree = CreaListaAreeInteresse();
        if(!aree.isEmpty())
            for (AreaInteresse a : aree) 
                if(nomeArea.equals(a.nome))
                    return true;    // Restituisce true se c'è già un'area con quel nome
        return false;   // Restituisce false se quell'area non esiste ancora
    }

    public static boolean ControllaNomeCentro(String nomeCentro) {   // Controlla se ci sono più centri con lo stesso nome

        List<CentroMonitoraggio> centri = CreaListaCentriMonitoraggio();
        if(!centri.isEmpty())
            for (CentroMonitoraggio c : centri) 
                if(nomeCentro.equals(c.nome))
                    return true;    // Restituisce true se c'è già un centro con quel nome
        return false;   // Restituisce false se quel centro non esiste ancora
    }

    public void Registrazione() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("./OperatoriRegistrati.csv", true)); 
            bw.write(nome + ";" + cognome + ";" + codFiscale + ";" + mail + ";" + userid + ";" + password + ";" + nomecMonitoraggio + ";" + "\n");
            bw.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    } 

    public static boolean Login(String username, String passwd) {
        List<Operatore> operatori = CreaListaOperatori();
        if(!operatori.isEmpty())
        {
            for (Operatore op : operatori) {
            if(username.equals(op.userid) && passwd.equals(op.password))
                return true;    // Restituisce true se la password è corretta
            }
        }
        return false;   // Restituisce false se username o password sono sbagliati
    }

    public void RegistraCentroAree(CentroMonitoraggio cm) {
        try {   // Aggiorniamo il file con i centri di monitoraggio
            BufferedWriter bw = new BufferedWriter(new FileWriter("./CentroMonitoraggio.csv", true)); 
            bw.write(cm.nome + ";" + cm.indirizzo.via + ";" + cm.indirizzo.ncivico + ";" + cm.indirizzo.cap + ";" + cm.indirizzo.comune + ";" + cm.indirizzo.provincia + ";" + " ;" + "\n");
            bw.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        // Troviamo l'operatore che bisogna aggiornare
        List<Operatore> opRegistrati = CreaListaOperatori();
        for (Operatore o : opRegistrati) {
            if(o.userid.equals(userid)) {
                o.nomecMonitoraggio = cm.nome;
            }
        }   
        try {   // Aggiorniamo il file con gli operatori registrati
            BufferedWriter bw = new BufferedWriter(new FileWriter("./OperatoriRegistrati.csv", true));
            // Svuota il file per poi riaggiornarlo dopo con la lista di operatori aggiornata
            FileWriter fw = new FileWriter("./OperatoriRegistrati.csv");
            fw.write("");
            fw.close();
            // Aggiorno il file con la lista di operatori aggiornata
            for (Operatore o : opRegistrati) {
                bw.write(o.nome + ";" + o.cognome + ";" + o.codFiscale + ";" + o.mail + ";" + o.userid + ";" + o.password + ";" + o.nomecMonitoraggio + ";" + "\n");
            } 
            bw.close(); 
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void RegistraAreaInteresse(String n, String s, String lat, String lon) {
        try {   // Aggiorniamo CoordinateMonitoraggio.csv
            BufferedWriter bw = new BufferedWriter(new FileWriter("./CoordinateMonitoraggio.csv", true)); 
            bw.write(n + ";" + s + ";" + lat + ";" + lon + ";" + "\n");
            bw.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        // Troviamo il centro di monitoraggio che bisogna aggiornare
        List<CentroMonitoraggio> centriMonitoraggio = CreaListaCentriMonitoraggio();
        for (CentroMonitoraggio cm : centriMonitoraggio) {
            if(cm.nome.equals(nomecMonitoraggio)) {
                cm.areeMonitorate.add(n);
            }
        }       
        try {   // Aggiorniamo CentroMonitoraggio.csv
            BufferedWriter bw = new BufferedWriter(new FileWriter("./CentroMonitoraggio.csv", true));
            // Svuota il file per poi riaggiornarlo dopo con la lista di centri aggiornata
            FileWriter fw = new FileWriter("./CentroMonitoraggio.csv");
            fw.write("");
            fw.close();
            // Aggiorno il file con la lista di centri aggiornata
            String riga;
            for (CentroMonitoraggio cm : centriMonitoraggio) {
                riga = cm.nome + ";" + cm.indirizzo.via + ";" + cm.indirizzo.ncivico + ";" + cm.indirizzo.cap + ";" + cm.indirizzo.comune + ";" + cm.indirizzo.provincia + ";";
                for(String ai : cm.areeMonitorate) {
                    if(!ai.equals(" "))
                        riga += ai + ",";
                }
                riga += ";\n";
                bw.write(riga);
            } 
            bw.close(); 
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public boolean ControlloListaAreeMonitorate(String c) { // Controlla se la città è tra quelle monitorate dal centro dell'operatore
        List<CentroMonitoraggio> centriMonitoraggio = CreaListaCentriMonitoraggio();
        for(CentroMonitoraggio cm : centriMonitoraggio) {
            if(cm.nome.equals(nomecMonitoraggio)) {
                for(String a : cm.areeMonitorate) {
                    if(a.equals(c))
                        return true;
                }
            }
        }
        return false;
    }

    public void InserisciParametriClimatici(String c) {
        if(ControlloListaAreeMonitorate(c)) {
            List<AreaInteresse> areeInteresse = CreaListaAreeInteresse();
            // Richiediamo i parametri e controlliamo che siano tra 1 e 5
            ConsoleInputManager in = new ConsoleInputManager();
            System.out.println("Inserisci i valori dei parametri (valore da 1 a 5) e le note (opzionali):");
            String vento, umidita, pressione, temperatura, precipitazioni, altitudineGhiacciai, massaGhiacciai;
            do {
                vento = in.readLine("Vento: ");
            } while (!vento.equals("1") && !vento.equals("2") && !vento.equals("3") && !vento.equals("4") && !vento.equals("5"));
            String noteVento = in.readLine("Note: ");
            do {
                umidita = in.readLine("Umidità: ");
            } while (!umidita.equals("1") && !umidita.equals("2") && !umidita.equals("3") && !umidita.equals("4") && !umidita.equals("5"));        
            String noteUmidita = in.readLine("Note: ");
            do {
                pressione = in.readLine("Pressione: ");
            } while (!pressione.equals("1") && !pressione.equals("2") && !pressione.equals("3") && !pressione.equals("4") && !pressione.equals("5")); 
            String notePressione = in.readLine("Note: ");
            do {
                temperatura = in.readLine("Temperatura: ");
            } while (!temperatura.equals("1") && !temperatura.equals("2") && !temperatura.equals("3") && !temperatura.equals("4") && !temperatura.equals("5")); 
            String noteTemperatura = in.readLine("Note: ");
            do {
                precipitazioni = in.readLine("Precipitazioni: ");
            } while (!precipitazioni.equals("1") && !precipitazioni.equals("2") && !precipitazioni.equals("3") && !precipitazioni.equals("4") && !precipitazioni.equals("5"));  
            String notePrecipitazioni = in.readLine("Note: ");
            do {
                altitudineGhiacciai = in.readLine("Altitudine dei ghiacciai: ");
            } while (!altitudineGhiacciai.equals("1") && !altitudineGhiacciai.equals("2") && !altitudineGhiacciai.equals("3") && !altitudineGhiacciai.equals("4") && !altitudineGhiacciai.equals("5"));
            String noteAltitudine = in.readLine("Note: ");
            do {
                massaGhiacciai = in.readLine("Massa dei ghiacciai: ");
            } while (!massaGhiacciai.equals("1") && !massaGhiacciai.equals("2") && !massaGhiacciai.equals("3") && !massaGhiacciai.equals("4") && !massaGhiacciai.equals("5"));
            String noteMassa = in.readLine("Note: ");
            // Inseriamo i parametri nella propria area di interesse
            for(AreaInteresse ai : areeInteresse) {
                if(ai.nome.equals(c)) {
                    ai.vento = new ParametroClimatico(vento, noteVento);
                    ai.umidita = new ParametroClimatico(umidita, noteUmidita);
                    ai.pressione = new ParametroClimatico(pressione, notePressione);
                    ai.temperatura = new ParametroClimatico(temperatura, noteTemperatura);
                    ai.precipitazioni = new ParametroClimatico(precipitazioni, notePrecipitazioni);
                    ai.altitudineGhiacciai = new ParametroClimatico(altitudineGhiacciai, noteAltitudine);
                    ai.massaGhiacciai = new ParametroClimatico(massaGhiacciai, noteMassa);
                }
            }
            try {   // Carichiamo i parametri nel file
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                BufferedWriter bw = new BufferedWriter(new FileWriter("./ParametriClimatici.csv", true)); 
                bw.write(c + ";" + vento + ";" + noteVento + ";" + umidita + ";" + noteUmidita + ";" + pressione + ";" + notePressione + ";" + temperatura + ";" + noteTemperatura + ";" + precipitazioni + ";" + notePrecipitazioni + ";" + altitudineGhiacciai + ";" + noteAltitudine + ";" + massaGhiacciai + ";" + noteMassa + ";" + java.time.LocalDate.now() + ";" + sdf.format(new Date()) + ";\n");
                bw.close();
            } catch (Exception e) {
                System.err.println(e);
            }
            System.out.println("Parametri inseriti correttamente");
        }
        else
            System.out.println("La città non è tra quelle monitorate dal centro " + nomecMonitoraggio + "!");

    }
}
