package edu.epsevg.prop.ac1.cerca;

import edu.epsevg.prop.ac1.cerca.heuristica.Heuristica;
import edu.epsevg.prop.ac1.cerca.model.Mapa;
import edu.epsevg.prop.ac1.resultat.ResultatCerca;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class CercaBeamIterativa extends CercaAStar {

    private static final int[] AMPLADES = {100, 1000};
    private static final int SENSE_LIMIT = Integer.MAX_VALUE;

    private int amplada;

    //PRE: usarLNT indica si es fa servir LNT, heur és l'heurística.
    //POST: crea la cerca amb la LNO ordenada per h.
    public CercaBeamIterativa(boolean usarLNT, Heuristica heur) {
        super(usarLNT, heur);
        this.comparador = new ComparadorH();
    }

    //PRE: inicial és l'estat inicial, rc guarda els resultats.
    //POST: rc conté la solució de la primera cerca que la trobi: Beam amb N = 100, Beam amb N = 1000 o Best-First.
    @Override
    public void ferCerca(Mapa inicial, ResultatCerca rc) {
        for (int n : AMPLADES) {
            amplada = n;
            cercaGenerica(inicial, rc, new PriorityQueue<Node>(comparador));
            if (rc.getSolucio() != null) {
                return;
            }
        }

        amplada = SENSE_LIMIT;
        cercaGenerica(inicial, rc, new PriorityQueue<Node>(comparador));
    }

    //PRE: n és un node on algun agent encara no ha sortit.
    //POST: retorna els fills de n que mouen el primer agent que encara no ha sortit.
    @Override
    protected List<Node> successors(Node n) {
        return successorsPrimerAgent(n);
    }

    //PRE: lno és la llista de nodes oberts, fill és un node.
    //POST: fill s'ha afegit a lno i, si lno passa d'amplada nodes, s'ha tret el pitjor.
    @Override
    protected void afegirALNO(Queue<Node> lno, Node fill) {
        lno.add(fill);
        if (lno.size() > amplada) {
            Node pitjor = null;
            for (Node n : lno) {
                if (pitjor == null || comparador.compare(n, pitjor) > 0) {
                    pitjor = n;
                }
            }
            lno.remove(pitjor);
        }
    }

    //PRE: cert
    //POST: retorna el nom de la cerca.
    @Override
    public String getNomCerca(){
        return "BeamIteratiu("+heur.getNom()+")";
    }

    private static class ComparadorH implements Comparator<Node> {
        //PRE: n1 i n2 són nodes amb h calculada.
        //POST: retorna negatiu si n1 té menor h (o la mateixa h i més g), positiu si va després, 0 si són iguals.
        @Override
        public int compare(Node n1, Node n2) {
            if (n1.h != n2.h) {
                return n1.h - n2.h;
            }
            return n2.g - n1.g;
        }
    }
}
