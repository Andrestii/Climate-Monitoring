/* DE PAOLI LORENZO 753577
 * ONESTI ANDREA 754771
 * RIZZO MATTIA 755403
 * WU WEILI 752602 */

package climatemonitoring;
import java.io.BufferedReader;
import java.io.BufferedWriter;
// import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

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

    public static boolean ControllaUsername(String user) {   // Controlla se ci sono più utenti con lo stesso username

        List<Operatore> operatori = CreaListaOperatori();
        if(!operatori.isEmpty())
            for (Operatore op : operatori) 
                if(user.equals(op.userid))
                    return true;    // Restituisce true se c'è già un utente con quello username
        return false;   // Restituisce false se quello username non è ancora stato occupato da nessuno
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
            bw.write(cm.nome + ";" + cm.indirizzo.via + ";" + cm.indirizzo.ncivico + ";" + cm.indirizzo.cap + ";" + cm.indirizzo.comune + ";" + cm.indirizzo.provincia + ";" + "\n");
            bw.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        List<Operatore> opRegistrati = CreaListaOperatori();
        for (Operatore o : opRegistrati) {
            if(o.userid.equals(userid)) {
                o.nomecMonitoraggio = cm.nome;
            }   // CAPIRE COME AGGIORNARE 
        }
                
        try {   // Aggiorniamo il file con gli operatori registrati

            BufferedWriter bw = new BufferedWriter(new FileWriter("./OperatoriRegistrati.csv", true)); 
            bw.write(cm.nome + ";" + cm.indirizzo.via + ";" + cm.indirizzo.ncivico + ";" + cm.indirizzo.cap + ";" + cm.indirizzo.comune + ";" + cm.indirizzo.provincia + ";" + "\n");
            bw.close();
            
            
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    // RegistraCentroAree() crea centro monitoraggio // devono essere salvati sul file CentroMonitoraggio.dati (.txt o .csv), e bisogna aggiornare OperatoriRegistrati.dati
                                                     // con un riferimento al centro di monitoraggio appena creato, che sarà il centro di riferimento dell'operatore
    // RegistraAreaInteresse() 

    // InserisciParametriClimatici(AreaInteresse) // i paramentri inseriti andranno nel file ParametriClimatici.dati
}
