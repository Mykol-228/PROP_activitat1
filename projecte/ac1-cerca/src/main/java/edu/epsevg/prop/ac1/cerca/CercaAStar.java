package edu.epsevg.prop.ac1.cerca;

import edu.epsevg.prop.ac1.cerca.heuristica.Heuristica;
import edu.epsevg.prop.ac1.cerca.model.Mapa;
import edu.epsevg.prop.ac1.model.MapaBase;
import edu.epsevg.prop.ac1.resultat.ResultatCerca;

import java.util.*;

public class CercaAStar extends Cerca {

    private Heuristica heur;
    private Comparator<Node> comparador;

    public CercaAStar(boolean usarLNT, Heuristica heur) {
        super(usarLNT);
        this.heur = heur;
        this.comparador = new ComparadorF();
    }


    @Override
    public void ferCerca(Mapa inicial, ResultatCerca rc) {
        // LNO ordenada per f = g + h
        cercaGenerica(inicial, rc, new ArrayList<Node>());
    }

    // Inserció ordenada amb cerca binària. Si n'hi ha d'iguals, el posem darrere.
    @Override
    protected void afegirALNO(List<Node> lno, Node fill) {
        int index = Collections.binarySearch(lno, fill, comparador);
        if (index < 0) {
            index = -(index + 1);
        } else {
            while (index < lno.size() && comparador.compare(lno.get(index), fill) == 0) {
                index++;
            }
        }
        lno.add(index, fill);
    }

    // Si alguna sortida és inabastable (h infinita), l'estat no porta a cap solució
    @Override
    protected boolean descartar(Node n) {
        n.h = heur.h(n.estat);
        return n.h >= MapaBase.INF;
    }

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

    // Ordena per f de menor a major. Si empaten, primer el de g més gran.
    private static class ComparadorF implements Comparator<Node> {
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
