package edu.epsevg.prop.ac1.cerca;

import edu.epsevg.prop.ac1.cerca.model.Mapa;
import edu.epsevg.prop.ac1.model.Moviment;
import edu.epsevg.prop.ac1.resultat.ResultatCerca;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Contracte comú de tots els algorismes de cerca. {@code usarLNT} indica si l'algorisme
 * ha de mantenir una llista de nodes tancats/visitats (LNT) per detectar i tallar
 * estats repetits, o bé si només ha d'evitar tornar directament a un ancestre del propi
 * camí (detecció de cicles més feble i barata en memòria).
 * <p>
 * BFS, DFS i A* comparteixen el mateix bucle de cerca ({@link #cercaGenerica}); només
 * canvia el tipus de LNO que fan servir (cua, pila o cua de prioritat).
 */
public abstract class Cerca {
    protected final boolean usarLNT;

    /** Cert si l'última cerca ha deixat algun node sense expandir pel límit de profunditat. */
    protected boolean tallatPerProfunditat;

    public Cerca(boolean usarLNT) {
        this.usarLNT = usarLNT;
    }

    /**
     * Indica si la cerca fa servir LNT
     * @return si la cerca fa servir LNT
     */
    public boolean usesLNT(){
        return this.usarLNT;
    }

    /**
     * Cerca un camí des de {@code inicial} fins a un estat meta i n'informa a
     * {@code rc} (camí trobat, nodes explorats/tallats, pic de memòria...).
     */
    public abstract void ferCerca(Mapa inicial, ResultatCerca rc);

    /**
     * Torna el nom de la cerca.
     * @return el nom de la cerca.
     */
    public abstract String getNomCerca();

    /**
     * Bucle de cerca comú. L'ordre en què s'exploren els nodes el decideix la LNO que
     * es passa per paràmetre (FIFO per BFS, LIFO per DFS, prioritat per A*).
     * <p>
     * Els nodes tallats ({@code incNodesTallats}) són només els descartats per cicles o
     * estats repetits; els que no s'expandeixen pel límit de profunditat no hi compten.
     * @param inicial estat inicial
     * @param rc on es desen els resultats
     * @param lno llista de nodes oberts (buida)
     */
    protected void cercaGenerica(Mapa inicial, ResultatCerca rc, Queue<Node> lno) {
        Set<Mapa> lnt = usarLNT ? new HashSet<>() : null;
        tallatPerProfunditat = false;

        lno.add(new Node(inicial, null, null, 0, 0));

        while (!lno.isEmpty()) {
            Node actual = lno.poll();

            // Amb LNT, un mateix estat pot ser a la LNO més d'un cop: si ja s'ha explorat, es talla
            if (usarLNT && lnt.contains(actual.estat)) {
                rc.incNodesTallats();
                continue;
            }

            if (actual.estat.esFinalPartida()) {
                rc.setSolucio(actual.getCami());
                return;
            }

            rc.incNodesExplorats();
            rc.actualitzaProfunditat(actual.depth);
            if (usarLNT) lnt.add(actual.estat);

            if (!expandible(actual)) {
                tallatPerProfunditat = true;
                continue;
            }

            for (Node fill : successors(actual)) {
                if (esRepetit(fill, lnt)) {
                    rc.incNodesTallats();
                } else {
                    lno.add(fill);
                }
            }
            rc.updateMemoria(lno.size(), usarLNT ? lnt.size() : 0);
        }
        // Si la LNO queda buida no hi ha solució: la solució queda a null
    }

    /**
     * Indica si un node es pot expandir. Per defecte sempre; la DFS ho sobreescriu per
     * aplicar el límit de profunditat.
     */
    protected boolean expandible(Node n) {
        return true;
    }

    /**
     * Control de cicles comú a totes les cerques. Amb LNT mira si l'estat ja s'ha
     * explorat; sense LNT mira si l'estat es repeteix dins de la branca (pujant pels pares).
     * @param n node a comprovar
     * @param lnt llista de nodes tancats ({@code null} si no s'usa LNT)
     * @return true si el node s'ha de tallar
     */
    protected boolean esRepetit(Node n, Set<Mapa> lnt) {
        if (lnt != null) return lnt.contains(n.estat);
        return n.pare != null && n.pare.estaEnBranca(n.estat);
    }

    /**
     * Genera els nodes fills de {@code n} aplicant tots els moviments possibles.
     * @param n node a expandir
     * @return la llista de fills
     */
    protected List<Node> successors(Node n) {
        List<Node> fills = new ArrayList<>();
        for (Moviment mov : n.estat.getAccionsPossibles()) {
            Mapa fill = (Mapa) n.estat.mou(mov);
            fills.add(new Node(fill, n, mov, n.depth + 1, n.g + 1));
        }
        return fills;
    }
}
