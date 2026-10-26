package edu.epsevg.prop.ac1.cerca.heuristica;

import edu.epsevg.prop.ac1.cerca.model.Mapa;
import edu.epsevg.prop.ac1.model.Posicio;

import java.util.List;

/**
 * Suma, per cada agent que encara no hagi sortit, la distància Manhattan des de la seva
 * posició actual fins a la seva pròpia sortida. És admissible perquè, com que cada
 * moviment mou un únic agent una casella, calen com a mínim tants moviments com la suma
 * de les distàncies pendents de tots els agents.
 */
public class HeuristicaBasica implements Heuristica {



    public HeuristicaBasica() {
        //@TODO: implementar 
    }

    @Override
    public int h(Mapa estat) {
        //@TODO: implementar
        int sumaDistancies = 0;

        List<Posicio> agents = estat.getAgents();
        for (int i = 0; i < agents.size(); i++) {
            if(estat.esAgentActiu(i)) {
                Posicio actual = agents.get(i);
                Posicio sortida = estat.getSortida(i);
                
                sumaDistancies += Math.abs(sortida.x - actual.x) + Math.abs(sortida.y - actual.y);
            }
        }
        return sumaDistancies;
    }

    
    @Override
    public String getNom() {
        return "H.bas";
    }

    // Podeu implementar aquí d'altres métodes que considereu necessaris
    // ...

}
