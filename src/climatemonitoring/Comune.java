/* DE PAOLI LORENZO 753577
 * ONESTI ANDREA 754771
 * RIZZO MATTIA 755403
 * WU WEILI 752602 */

package climatemonitoring;

public class Comune {
    public AreaInteresse CercaAreaGeograficaLuogo(String citta, String stato) {
        // Ricerco nella lista quale area ha nome e stato uguali a quelli inseriti
        if(!ClimateMonitor.areeInteresse.isEmpty()) {  // Controllo se la lista è vuota

            /*
            System.out.print(ClimateMonitor.areeInteresse.size());
            return ClimateMonitor.areeInteresse.stream().filter(x -> x.nome == citta).findFirst().orElse(null);
            */

            for (AreaInteresse a : ClimateMonitor.areeInteresse) {
                /*
                System.out.println(a.nome + a.stato);
                System.out.println(a.nome.toUpperCase());
                System.out.println(citta.toUpperCase());
                System.out.println(a.stato.toUpperCase());
                System.out.println(stato.toUpperCase());
                */
                if(a != null && a.nome != null && a.stato != null)
                {
                    if((a.nome.toUpperCase().equals(citta.toUpperCase())) && (a.stato.toUpperCase().equals(stato.toUpperCase()))) {   // Abbiamo trovato l'area che volevamo
                        return a;
                    }
                }
                
            }
        }
        return null;
    }

    // DA MODIFICARE (?) PER VICINANZA DI COORDINATE

    public AreaInteresse CercaAreaGeograficaCoordinate(String lat, String lon) {
        // Ricerco nella lista quale area ha latitudine e longitudine uguali a quelli inseriti
        if(ClimateMonitor.areeInteresse != null) {  // Controllo se la lista è vuota
            for (AreaInteresse a : ClimateMonitor.areeInteresse) {
                if((a.latitudine == lat) && (a.longitudine == lon)) {   // Abbiamo trovato l'area che volevamo
                    return a;
                }
                else {  // NON abbiamo trovato l'area che volevamo
                    return null;
                }
            }
        }
        return null;
    }

    public void VisualizzaAreaGeografica(AreaInteresse a) {
        if(a != null) {
            System.out.println(a.toString());
        }
        else {
            System.out.println("La città non esiste");
        }
    }
}