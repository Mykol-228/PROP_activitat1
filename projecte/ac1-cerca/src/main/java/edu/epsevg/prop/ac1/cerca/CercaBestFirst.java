package edu.epsevg.prop.ac1.cerca;

import edu.epsevg.prop.ac1.cerca.heuristica.Heuristica;

import java.util.Comparator;
import java.util.List;

/**
 * Best-First per al repte (mapes E i F). És com l'A*, però la LNO s'ordena només per h,
 * i només es mou el primer agent que encara no ha sortit.
 * Amb LNT és completa (l'espai d'estats és finit), però no és òptima.
 */
public class CercaBestFirst extends CercaAStar {

    public CercaBestFirst(boolean usarLNT, Heuristica heur) {
        super(usarLNT, heur);
        this.comparador = new ComparadorH();
    }

    @Override
    protected List<Node> successors(Node n) {
        return successorsPrimerAgent(n);
    }

    @Override
    public String getNomCerca(){
        return "BestFirst("+heur.getNom()+")";
    }

    // Ordena per h de menor a major. Si empaten, primer el de g més gran.
    private static class ComparadorH implements Comparator<Node> {
        @Override
        public int compare(Node n1, Node n2) {
            if (n1.h != n2.h) {
                return n1.h - n2.h;
            }
            return n2.g - n1.g;
        }
    }
}
