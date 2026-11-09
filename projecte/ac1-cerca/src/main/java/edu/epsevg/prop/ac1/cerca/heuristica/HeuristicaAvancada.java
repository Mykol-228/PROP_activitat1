package edu.epsevg.prop.ac1.cerca.heuristica;

import edu.epsevg.prop.ac1.cerca.model.Mapa;
import edu.epsevg.prop.ac1.model.MapaBase;
import edu.epsevg.prop.ac1.model.Posicio;

import java.util.List;

/**
 * Com {@link HeuristicaBasica} suma per cada agent actiu  la distància fins a la seva
 * pròpia sortida, però usant la distància real sobre la graella actual calculada amb BFS
 * tenint en compte les parets/rastres ja existents en lloc de la distància Manhattan.
 */
public class HeuristicaAvancada implements Heuristica {
    
  
    public HeuristicaAvancada() {
        //@TODO: implementar       
    }

    @Override
    public int h(Mapa estat) {
        int sumaDistancies = 0;
        List<Posicio> agents = estat.getAgents();

        for (int i = 0; i < agents.size(); i++) {
            if (estat.esAgentActiu(i)) {
                Posicio actual = agents.get(i);
                Posicio sortida = estat.getSortida(i);

                // Calculem la matriu de distànciesBFS des de la posició actual de l'agent

                /* Falta implementar distanciesFrom
                int[][] distMat = estat.distancesFrom(actual); 
                int d = distMat[sortida.x][sortida.y];
                

                // Si la sortida és inabastable, l'estat és invàlid retornem INF
                if (d >= MapaBase.INF) {
                    return MapaBase.INF;
                }

                sumaDistancies += d;
                */
            }
        }
        
        return sumaDistancies;
    }
    
    @Override
    public String getNom() {
        return "H.av";
    }

        // Podeu implementar aquí d'altres métodes que considereu necessaris
    // ...
}
