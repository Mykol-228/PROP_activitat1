package edu.epsevg.prop.ac1.cerca;

import edu.epsevg.prop.ac1.cerca.model.Mapa;
import edu.epsevg.prop.ac1.resultat.ResultatCerca;

import java.util.ArrayDeque;
import java.util.Collections;

public class CercaDFS extends Cerca {
    private static final int LIMIT_PER_DEFECTE = 50;

    private final int limit;

    public CercaDFS(boolean usarLNT) { this(usarLNT, LIMIT_PER_DEFECTE); }

    /**
     * DFS amb un límit de profunditat qualsevol (el fa servir la IDS).
     * @param limit els nodes d'aquesta profunditat ja no s'expandeixen
     */
    CercaDFS(boolean usarLNT, int limit) {
        super(usarLNT);
        this.limit = limit;
    }

    @Override
    public void ferCerca(Mapa inicial, ResultatCerca rc) {
        // LNO com a pila (LIFO): s'explora primer el node més nou
        cercaGenerica(inicial, rc, Collections.asLifoQueue(new ArrayDeque<>()));
    }

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
