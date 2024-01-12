/* DE PAOLI LORENZO 753577
 * ONESTI ANDREA 754771
 * RIZZO MATTIA 755403
 * WU WEILI 752602 */

package climatemonitoring;

import java.util.List;

public class CentroMonitoraggio {

    public String nome;
    public Indirizzo indirizzo;
    public List<AreaInteresse> areeMonitorate;

    public CentroMonitoraggio(String nome, String via, String ncivico, String cap, String comune, String provincia) {
        this.nome = nome;
        this.indirizzo = new Indirizzo(via, ncivico, cap, comune, provincia);
    } 
}
