/* DE PAOLI LORENZO 753577
 * ONESTI ANDREA 754771
 * RIZZO MATTIA 755403
 * WU WEILI 752602 */

package climatemonitoring;

public class Indirizzo { // Indirizzo fisico dei centri di monitoraggio

    public String via, ncivico, cap, comune, provincia;

    public Indirizzo(String via, String ncivico, String cap, String comune, String provincia) {
        this.via = via;
        this.ncivico = ncivico;
        this.cap = cap;
        this.comune = comune;
        this.provincia = provincia;
    }
}
