/* DE PAOLI LORENZO 753577
 * ONESTI ANDREA 754771
 * RIZZO MATTIA 755403
 * WU WEILI 752602 
 * SEDE: VARESE */

package climatemonitoring;

public class AreaInteresse {

    public String nome, stato, latitudine, longitudine;
    public ParametroClimatico vento, umidita, pressione, temperatura, precipitazioni, altitudineGhiacciai, massaGhiacciai;

    public AreaInteresse(String nome, String stato, String latitudine, String longitudine) {
        this.nome = nome;
        this.stato = stato;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    @Override
    public String toString() {
        return "\nCittà: " + nome + "\nStato: " + stato + "\nLatitudine: " + latitudine + "\nLongitudine " + longitudine;
    }
}
