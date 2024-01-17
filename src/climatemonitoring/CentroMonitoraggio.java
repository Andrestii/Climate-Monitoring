/* DE PAOLI LORENZO 753577
 * ONESTI ANDREA 754771
 * RIZZO MATTIA 755403
 * WU WEILI 752602
 * SEDE: VARESE */

package climatemonitoring;

import java.util.ArrayList;
import java.util.List;

public class CentroMonitoraggio {

    public String nome;
    public Indirizzo indirizzo;
    public List<String> areeMonitorate = new ArrayList<String>();

    public CentroMonitoraggio(String nome, String via, String ncivico, String cap, String comune, String provincia, String listaAreeMonitorate) {
        this.nome = nome;
        this.indirizzo = new Indirizzo(via, ncivico, cap, comune, provincia);
        String[] areeInteresse = listaAreeMonitorate.split(",");
        for(int i=0;i<areeInteresse.length; i++) {
            areeMonitorate.add(areeInteresse[i]); 
        }
    } 
}
