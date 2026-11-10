package edu.epsevg.prop.ac1.cerca;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import edu.epsevg.prop.ac1.cerca.model.Mapa;
import edu.epsevg.prop.ac1.model.Moviment;
import edu.epsevg.prop.ac1.resultat.ResultatCerca;

public class CercaIDS extends Cerca {
    private static final int LIMIT_PER_DEFECTE = 50;
    
    public CercaIDS(boolean usarLNT) { super(usarLNT); }

    @Override
    public void ferCerca(Mapa inicial, ResultatCerca rc) {
        // Iterem incrementant el límit de profunditat de 1 en 1
        for (int limit = 1; limit <= LIMIT_PER_DEFECTE; ++limit) {
            
            // A cada iteració de profunditat reinicialitzem la pila i els visitats
            ArrayDeque<Node> pila = new ArrayDeque<>();
            Set<Mapa> visitats = new HashSet<>();

            Node arrel = new Node(inicial, null, null, 0, 0);
            pila.push(arrel);

            if (usesLNT()) {
                visitats.add(inicial);
            }

            while (!pila.isEmpty()) {
                Node actual = pila.pop();
                rc.actualitzaProfunditat(actual.depth); 

                // Comprovem si és l'estat final
                if (actual.estat.esFinalPartida()) {
                    reconstruirCami(actual, rc);
                    rc.updateMemoria(pila.size(), usesLNT() ? visitats.size() : 0);
                    return;
                }

                // Si hem assolit el límit actual, no generem els seus fills
                if (actual.depth >= limit) {
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

                rc.updateMemoria(pila.size(), usesLNT() ? visitats.size() : 0);
            }
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

    @Override
    public String getNomCerca(){
        return "IDS";
    }
}