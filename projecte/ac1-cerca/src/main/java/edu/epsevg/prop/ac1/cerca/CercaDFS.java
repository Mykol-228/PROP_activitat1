package edu.epsevg.prop.ac1.cerca;

import edu.epsevg.prop.ac1.cerca.model.Mapa;
import edu.epsevg.prop.ac1.model.Moviment;
import edu.epsevg.prop.ac1.resultat.ResultatCerca;

import java.util.*;

public class CercaDFS extends Cerca {
    private static final int LIMIT_PER_DEFECTE = 50;
    
    public CercaDFS(boolean usarLNT) { super(usarLNT); }

    @Override
    public void ferCerca(Mapa inicial, ResultatCerca rc) {
        ArrayDeque<Node> pila = new ArrayDeque<>();
        Set<Mapa> visitats = new HashSet<>();

        Node arrel = new Node(inicial, null, null, 0, 0);
        pila.push(arrel);

        if (usesLNT()) {
            visitats.add(inicial);
        }

        while (!pila.isEmpty()) {
            Node actual = pila.pop(); // Traiem l'últim afegit
            rc.actualitzaProfunditat(actual.depth); 

            // Comprovem si és l'estat final
            if (actual.estat.esFinalPartida()) {
                reconstruirCami(actual, rc);
                rc.updateMemoria(pila.size(), usesLNT() ? visitats.size() : 0);
                return;
            }

            // Comprovem si no hem passat el límit
            if (actual.depth >= LIMIT_PER_DEFECTE) {
                rc.incNodesTallats();
                continue;
            }

            rc.incNodesExplorats();

            List<Moviment> accions = actual.estat.getAccionsPossibles();
            for (Moviment mov : accions) {
                Mapa nouEstat = (Mapa) actual.estat.mou(mov);

                if (usesLNT()) {
                    if (!visitats.contains(nouEstat)) {
                        visitats.add(nouEstat);
                        pila.push(new Node(nouEstat, actual, mov, actual.depth + 1, actual.g + 1));
                    } else {
                        rc.incNodesTallats();
                    }
                } else {
                    // Control de cicles dins de la branca actual
                    if (!enBrancaActual(actual, nouEstat)) {
                        pila.push(new Node(nouEstat, actual, mov, actual.depth + 1, actual.g + 1));
                    } else {
                        rc.incNodesTallats();
                    }
                }
            }

            // Actualitzem les mètriques de memòria periòdicament
            rc.updateMemoria(pila.size(), usesLNT() ? visitats.size() : 0);
        }
    }

    //Post: retorna true si troba algun node anterior que té la mateixa mapa
    private boolean enBrancaActual(Node nodeActual, Mapa estatNou) {
        boolean cicleTrobat = false;
        Node aux = nodeActual;
        while (aux != null && !cicleTrobat) {
            if (aux.estat.equals(estatNou)) {
                cicleTrobat = true;
            }
            aux = aux.pare;
        }
        return cicleTrobat;
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
        return "DFS";
    }    
}
