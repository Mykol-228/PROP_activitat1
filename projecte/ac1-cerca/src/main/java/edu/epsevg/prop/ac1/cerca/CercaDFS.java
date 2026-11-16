package edu.epsevg.prop.ac1.cerca;

import edu.epsevg.prop.ac1.cerca.model.Mapa;
import edu.epsevg.prop.ac1.resultat.ResultatCerca;

import java.util.*;

public class CercaDFS extends Cerca {
    private static final int LIMIT_PER_DEFECTE = 50;
    private int limit = LIMIT_PER_DEFECTE;
    private LinkedList<Node> pila;

    public CercaDFS(boolean usarLNT) { super(usarLNT); }

    //PRE: usarLNT indica si es fa servir LNT, limit és la profunditat màxima.
    //POST: crea una DFS amb aquest límit.
    public CercaDFS(boolean usarLNT, int limit) {
        super(usarLNT);
        this.limit = limit;
    }

    //PRE: inicial és l'estat inicial, rc guarda els resultats.
    //POST: rc conté el resultat de la cerca en profunditat.
    @Override
    public void ferCerca(Mapa inicial, ResultatCerca rc) {
        pila = new LinkedList<>();
        cercaGenerica(inicial, rc, pila);
    }

    //PRE: lno és la pila de la cerca, fill és un node.
    //POST: fill s'ha afegit al principi de la pila.
    @Override
    protected void afegirALNO(Queue<Node> lno, Node fill) {
        pila.addFirst(fill);
    }

    //PRE: n és un node.
    //POST: retorna TRUE si la profunditat de n és menor que el límit, FALSE altrament.
    @Override
    protected boolean expandible(Node n) {
        return n.depth < limit;
    }

    /**
     * Torna el nom de la cerca.
     * @return el nom de la cerca.
     */
    public String getNomCerca(){
        return "DFS";
    }
}
