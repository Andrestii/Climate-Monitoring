/* DE PAOLI LORENZO 753577
 * ONESTI ANDREA 754771
 * RIZZO MATTIA 755403
 * WU WEILI 752602 */

package climatemonitoring;

public class Comune {
    public AreaInteresse CercaAreaGeograficaLuogo(String citta, String stato) {
        // Ricerco nella lista quale area ha nome e stato uguali a quelli inseriti
        if(!ClimateMonitor.areeInteresse.isEmpty()) {  // Controllo se la lista non è vuota

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
        return null;    // EXCEPTION DA METTERE
    }

    // DA MODIFICARE (?) PER VICINANZA DI COORDINATE

    public AreaInteresse CercaAreaGeograficaCoordinate(String lat, String lon) {
        // Ricerco nella lista quale area ha latitudine e longitudine uguali a quelle inserite
        if(!ClimateMonitor.areeInteresse.isEmpty()) {  // Controllo se la lista non è vuota
            for (AreaInteresse a : ClimateMonitor.areeInteresse) { 
                if(a != null && a.latitudine != null && a.longitudine != null)
                {

                    if((a.latitudine.equals(lat)) && (a.longitudine.equals(lon))) {   // Abbiamo trovato l'area che volevamo
                        return a;
                    }
                } 
            }
            AreaInteresse areaPiuVicina = ClimateMonitor.areeInteresse.get(0);  // Prendo la prima della lista

            for (AreaInteresse a : ClimateMonitor.areeInteresse) { // Allora cerchiamo per le coordinate più vicine
                double latDouble = Double.parseDouble(lat), lonDouble = Double.parseDouble(lon);    // Che inserisce l'utente
                double aLat = Double.parseDouble(a.latitudine), aLon = Double.parseDouble(a.longitudine);   // Dalla lista
                double latVicina = Double.parseDouble(areaPiuVicina.latitudine), lonVicina = Double.parseDouble(areaPiuVicina.longitudine);
                if(Math.abs(latDouble - aLat) + Math.abs(lonDouble - aLon) < Math.abs(latDouble - latVicina) + Math.abs(lonDouble - lonVicina)) {
                    areaPiuVicina = a;  // Se è più vicina l'areaInteresse "a" allora aggiorno "areaPiuVicina"
                }
            }
            return areaPiuVicina;
        }
        return null;    // EXCEPTION DA METTERE 
    }
}