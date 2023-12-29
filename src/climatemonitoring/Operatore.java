/* DE PAOLI LORENZO 753577
 * ONESTI ANDREA 754771
 * RIZZO MATTIA 755403
 * WU WEILI 752602 */

package climatemonitoring;

import java.io.FileWriter;

public class Operatore extends Comune {

    /*di creare una o più aree di interesse (tramite coordinate geografiche), raggrupparle per centro di monitoraggio e annotarle 
    singolarmente con i parametri forniti ad un operatore in una specifica data secondo i parametri dati nella tabella precedente */

    public String nome, cognome, codFiscale, mail, userid, password;

    public Operatore (String nome, String cognome, String codFiscale, String mail, String userid, String password) { 
        nome = this.nome;
        cognome = this.cognome;
        codFiscale = this.codFiscale;
        mail = this.mail;
        userid = this.userid;
        password = this.password;
    }

    // CercaAreaGeografica(nome) prendi da superclasse comune           OK

    // CercaAreaGeografica(coordinate) prendi da superclasse comune     OK

    // VisualizzaAreaGeografica() prendi da superclasse comune          OK

    public void Registrazione(CentroMonitoraggio cMonitoraggio) {   // boolean (?)
        try {
            // Ogni volta che registriamo un operatore gli assegnamo un NUOVO centro di monitoraggio
            // Da implementare con RegistraCentroAree()

            FileWriter fw = new FileWriter("./OperatoriRegistrati.csv"); 
            fw.write(nome + ";" + cognome + ";" + codFiscale + ";" + mail + ";" + userid + ";" + password + ";");
        } catch (Exception e) {
            System.err.println(e);
        }
    } 

    // Login()

    // RegistraCentroAree() crea centro monitoraggio // devono essere salvati sul file CentroMonitoraggio.dati (.txt o .csv), e bisogna aggiornare OperatoriRegistrati.dati
                                                     // con un riferimento al centro di monitoraggio appena creato, che sarà il centro di riferimento dell'operatore
    // RegistraAreaInteresse() 

    // InserisciParametriClimatici(AreaInteresse) // i paramentri inseriti andranno nel file ParametriClimatici.dati
}
