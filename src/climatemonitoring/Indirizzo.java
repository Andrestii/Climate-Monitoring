/* DE PAOLI LORENZO 753577
 * ONESTI ANDREA 754771
 * RIZZO MATTIA 755403
 * WU WEILI 752602 */

package climatemonitoring;

public class Indirizzo { // Indirizzo fisico dei centri di monitoraggio

    public String via, comune, provincia;
    public int ncivico, cap;

    public Indirizzo(String via, int ncivico, int cap, String comune, String provincia) {
        via = this.via;
        ncivico = this.ncivico;
        cap = this.cap;
        comune = this.comune;
        provincia = this.provincia;
    }
}
