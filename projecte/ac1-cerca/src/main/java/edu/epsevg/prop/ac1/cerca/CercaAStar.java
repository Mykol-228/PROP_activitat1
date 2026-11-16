package edu.epsevg.prop.ac1.cerca;

import edu.epsevg.prop.ac1.cerca.heuristica.Heuristica;
import edu.epsevg.prop.ac1.cerca.model.Mapa;
import edu.epsevg.prop.ac1.model.MapaBase;
import edu.epsevg.prop.ac1.resultat.ResultatCerca;

import java.util.*;

public class CercaAStar extends Cerca {

    protected Heuristica heur;
    protected Comparator<Node> comparador;

    public CercaAStar(boolean usarLNT, Heuristica heur) {
        super(usarLNT);
        this.heur = heur;
        this.comparador = new ComparadorF();
    }


    //PRE: inicial és l'estat inicial, rc guarda els resultats.
    //POST: rc conté el resultat de la cerca A*.
    @Override
    public void ferCerca(Mapa inicial, ResultatCerca rc) {
        cercaGenerica(inicial, rc, new PriorityQueue<Node>(comparador));
    }

    //PRE: n és un node.
    //POST: n.h guarda l'heurística de n, retorna TRUE si és INF, FALSE altrament.
    @Override
    protected boolean descartar(Node n) {
        n.h = heur.h(n.estat);
        return n.h >= MapaBase.INF;
    }

    //PRE: n és el node explorat amb n.h calculada, rc guarda els resultats.
    //POST: s'ha guardat l'heurística de n a rc.
    @Override
    protected void nodeExplorat(Node n, ResultatCerca rc) {
        rc.actualitzaHeuristica(n.h);
    }

    /**
     * Torna el nom de la cerca.
     * @return el nom de la cerca.
     */
    public String getNomCerca(){
        return "A*("+heur.getNom()+")";
    }

    private static class ComparadorF implements Comparator<Node> {
        //PRE: n1 i n2 són nodes amb h calculada.
        //POST: retorna negatiu si n1 té menor f (o la mateixa f i més g), positiu si va després, 0 si són iguals.
        @Override
        public int compare(Node n1, Node n2) {
            int f1 = n1.g + n1.h;
            int f2 = n2.g + n2.h;
            if (f1 != f2) {
                return f1 - f2;
            }
            return n2.g - n1.g;
        }
    }
}
