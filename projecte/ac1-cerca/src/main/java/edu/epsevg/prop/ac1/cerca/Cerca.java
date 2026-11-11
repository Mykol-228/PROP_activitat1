package edu.epsevg.prop.ac1.cerca;

import edu.epsevg.prop.ac1.cerca.model.Mapa;
import edu.epsevg.prop.ac1.model.Moviment;
import edu.epsevg.prop.ac1.resultat.ResultatCerca;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Contracte comú de tots els algorismes de cerca. {@code usarLNT} indica si l'algorisme
 * ha de mantenir una llista de nodes tancats/visitats (LNT) per detectar i tallar
 * estats repetits, o bé si només ha d'evitar tornar directament a un ancestre del propi
 * camí (detecció de cicles més feble i barata en memòria).
 */
public abstract class Cerca {
    protected final boolean usarLNT;

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
     * Bucle de cerca comú per BFS, DFS i A*. Només canvia on s'afegeixen els fills a la LNO.
     */
    protected void cercaGenerica(Mapa inicial, ResultatCerca rc, List<Node> lno) {
        Set<Mapa> lnt = null;
        if (usarLNT) {
            lnt = new HashSet<>();
        }

        Node arrel = new Node(inicial, null, null, 0, 0);
        if (!descartar(arrel)) {
            lno.add(arrel);
        }

        while (!lno.isEmpty()) {
            Node actual = lno.remove(0);

            // Un mateix estat pot estar repetit a la LNO
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
            nodeExplorat(actual, rc);
            if (usarLNT) {
                lnt.add(actual.estat);
            }

            // Límit de profunditat: no ho comptem com a tallat
            if (!expandible(actual)) {
                continue;
            }

            for (Node fill : successors(actual)) {
                if (esRepetit(fill, lnt)) {
                    rc.incNodesTallats();
                } else if (!descartar(fill)) {
                    afegirALNO(lno, fill);
                }
            }

            int midaLNT = 0;
            if (usarLNT) {
                midaLNT = lnt.size();
            }
            rc.updateMemoria(lno.size(), midaLNT);
        }
    }

    // Per defecte afegeix al final (cua). La DFS i l'A* ho sobreescriuen.
    protected void afegirALNO(List<Node> lno, Node fill) {
        lno.add(fill);
    }

    // Per defecte tots els nodes es poden expandir. La DFS ho sobreescriu pel límit.
    protected boolean expandible(Node n) {
        return true;
    }

    // Per defecte no es descarta cap node. L'A* ho sobreescriu (h infinita).
    protected boolean descartar(Node n) {
        return false;
    }

    // Per defecte no fa res. L'A* ho sobreescriu per informar de l'heurística.
    protected void nodeExplorat(Node n, ResultatCerca rc) {
    }

    /**
     * Control de cicles: amb LNT mira si l'estat ja s'ha explorat, i sense LNT mira si
     * l'estat ja apareix a la branca.
     */
    protected boolean esRepetit(Node n, Set<Mapa> lnt) {
        if (lnt != null) {
            return lnt.contains(n.estat);
        }
        return n.pare != null && n.pare.estaEnBranca(n.estat);
    }

    // Genera tots els fills del node
    protected List<Node> successors(Node n) {
        List<Node> fills = new ArrayList<>();
        for (Moviment mov : n.estat.getAccionsPossibles()) {
            Mapa fill = (Mapa) n.estat.mou(mov);
            fills.add(new Node(fill, n, mov, n.depth + 1, n.g + 1));
        }
        return fills;
    }
}
