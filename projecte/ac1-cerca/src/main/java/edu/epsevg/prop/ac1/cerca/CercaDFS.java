package edu.epsevg.prop.ac1.cerca;

import edu.epsevg.prop.ac1.cerca.model.Mapa;
import edu.epsevg.prop.ac1.resultat.ResultatCerca;

import java.util.*;

public class CercaDFS extends Cerca {
    private static final int LIMIT_PER_DEFECTE = 50;
    private int limit = LIMIT_PER_DEFECTE;

    public CercaDFS(boolean usarLNT) { super(usarLNT); }

    // Constructor amb un altre límit (el fa servir la IDS)
    public CercaDFS(boolean usarLNT, int limit) {
        super(usarLNT);
        this.limit = limit;
    }

    @Override
    public void ferCerca(Mapa inicial, ResultatCerca rc) {
        cercaGenerica(inicial, rc, new LinkedList<Node>());
    }

    // Afegim al principi perquè es tregui primer l'últim afegit (pila)
    @Override
    protected void afegirALNO(List<Node> lno, Node fill) {
        lno.add(0, fill);
    }

    // Comprovem si no hem passat el límit
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
