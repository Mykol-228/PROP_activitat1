package edu.epsevg.prop.ac1.cerca;

import edu.epsevg.prop.ac1.cerca.heuristica.Heuristica;
import edu.epsevg.prop.ac1.cerca.model.Mapa;
import edu.epsevg.prop.ac1.model.MapaBase;
import edu.epsevg.prop.ac1.resultat.ResultatCerca;

import java.util.*;

/**
 * Beam Search per al repte (mapes E i F). Avança per nivells i a cada nivell només es
 * queda els K nodes amb menor h. Només es mou el primer agent que encara no ha sortit.
 * No és completa (pot descartar el camí bo) ni òptima.
 */
public class CercaBeam extends Cerca {

    private Heuristica heur;
    private int amplada;   // K: nodes que es guarden a cada nivell

    public CercaBeam(boolean usarLNT, Heuristica heur, int amplada) {
        super(usarLNT);
        this.heur = heur;
        this.amplada = amplada;
    }

    @Override
    public void ferCerca(Mapa inicial, ResultatCerca rc) {
        // A la Beam marquem els estats quan es generen, perquè no es repeteixin dins d'un nivell
        Set<Mapa> lnt = null;
        if (usarLNT) {
            lnt = new HashSet<>();
            lnt.add(inicial);
        }

        List<Node> nivell = new ArrayList<>();
        Node arrel = new Node(inicial, null, null, 0, 0);
        arrel.h = heur.h(inicial);
        if (arrel.h < MapaBase.INF) {
            nivell.add(arrel);
        }

        while (!nivell.isEmpty()) {
            List<Node> fills = new ArrayList<>();

            for (Node actual : nivell) {
                if (actual.estat.esFinalPartida()) {
                    rc.setSolucio(actual.getCami());
                    return;
                }

                rc.incNodesExplorats();
                rc.actualitzaProfunditat(actual.depth);
                rc.actualitzaHeuristica(actual.h);

                for (Node fill : successorsPrimerAgent(actual)) {
                    if (esRepetit(fill, lnt)) {
                        rc.incNodesTallats();
                    } else {
                        if (usarLNT) {
                            lnt.add(fill.estat);
                        }
                        fill.h = heur.h(fill.estat);
                        if (fill.h < MapaBase.INF) {
                            fills.add(fill);
                        }
                    }
                }
            }

            // Ens quedem els K fills amb menor h
            Collections.sort(fills, new ComparadorH());
            nivell = new ArrayList<>();
            for (int i = 0; i < fills.size() && i < amplada; i++) {
                nivell.add(fills.get(i));
            }

            int midaLNT = 0;
            if (usarLNT) {
                midaLNT = lnt.size();
            }
            rc.updateMemoria(nivell.size(), midaLNT);
        }
    }

    @Override
    public String getNomCerca(){
        return "Beam("+heur.getNom()+",K="+amplada+")";
    }

    // Ordena per h de menor a major
    private static class ComparadorH implements Comparator<Node> {
        @Override
        public int compare(Node n1, Node n2) {
            return n1.h - n2.h;
        }
    }
}
