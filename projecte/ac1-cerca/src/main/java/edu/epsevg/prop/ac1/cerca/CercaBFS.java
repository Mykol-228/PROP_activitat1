package edu.epsevg.prop.ac1.cerca;

import edu.epsevg.prop.ac1.cerca.model.Mapa;
import edu.epsevg.prop.ac1.model.Moviment;
import edu.epsevg.prop.ac1.resultat.ResultatCerca;

import java.util.*;

public class CercaBFS extends Cerca {

    public CercaBFS(boolean usarLNT) {
        super(usarLNT);
    }

    @Override
    public void ferCerca(Mapa inicial, ResultatCerca rc) {
        ArrayDeque<Node> cua = new ArrayDeque<>();
        Set<Mapa> visitats = new HashSet<>();

        Node arrel = new Node(inicial, null, null, 0, 0);
        cua.add(arrel);

        if (usesLNT()) {
            visitats.add(inicial);
        }

        while (!cua.isEmpty()) {
            Node actual = cua.poll();
            rc.actualitzaProfunditat(actual.depth); 

            // Comprovem si és l'estat final
            if (actual.estat.esFinalPartida()) {
                reconstruirCami(actual, rc);
                rc.updateMemoria(cua.size(), usesLNT() ? visitats.size() : 0);
                return;
            }

            rc.incNodesExplorats();

            List<Moviment> accions = actual.estat.getAccionsPossibles();
            for (Moviment mov : accions) {
                Mapa nouEstat = (Mapa) actual.estat.mou(mov);

                if (usesLNT()) {
                    if (!visitats.contains(nouEstat)) {
                        visitats.add(nouEstat);
                        cua.add(new Node(nouEstat, actual, mov, actual.depth + 1, actual.g + 1));
                    } else {
                        rc.incNodesTallats();
                    }
                } else {
                    // Control de cicles dins de la branca actual
                    if (!enBrancaActual(actual, nouEstat)) {
                        cua.add(new Node(nouEstat, actual, mov, actual.depth + 1, actual.g + 1));
                    } else {
                        rc.incNodesTallats();
                    }
                }
            }

            // Actualitzem les mètriques de memòria periòdicament
            rc.updateMemoria(cua.size(), usesLNT() ? visitats.size() : 0);
        }
    }

    private boolean enBrancaActual(Node nodeActual, Mapa estatNou) {
        Node aux = nodeActual;
        while (aux != null) {
            if (aux.estat.equals(estatNou)) {
                return true;
            }
            aux = aux.pare;
        }
        return false;
    }

    private void reconstruirCami(Node solucio, ResultatCerca rc) {
        List<Moviment> cami = new LinkedList<>();
        Node actual = solucio;
        
        while (actual.accio != null) {
            cami.add(0, actual.accio);
            actual = actual.pare;
        }
        
        rc.setSolucio(cami);
    }
    
    /**
     * Torna el nom de la cerca.
     * @return el nom de la cerca.
     */
    public String getNomCerca(){
        return "BFS";
    }    
}
