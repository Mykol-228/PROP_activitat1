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

    //PRE: inicial és l'estat inicial, rc guarda els resultats, lno és la llista de nodes oberts buida.
    //POST: rc conté la solució si n'hi ha, i els nodes explorats, els tallats, la profunditat i la memòria.
    protected void cercaGenerica(Mapa inicial, ResultatCerca rc, Queue<Node> lno) {
        Set<Mapa> lnt = null;
        if (usarLNT) {
            lnt = new HashSet<>();
        }

        Node arrel = new Node(inicial, null, null, 0, 0);
        if (!descartar(arrel)) {
            lno.add(arrel);
        }

        while (!lno.isEmpty()) {
            Node actual = lno.poll();

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

    //PRE: lno és la llista de nodes oberts, fill és un node.
    //POST: fill s'ha afegit a lno.
    protected void afegirALNO(Queue<Node> lno, Node fill) {
        lno.add(fill);
    }

    //PRE: n és un node.
    //POST: retorna TRUE.
    protected boolean expandible(Node n) {
        return true;
    }

    //PRE: n és un node.
    //POST: retorna FALSE.
    protected boolean descartar(Node n) {
        return false;
    }

    //PRE: n és el node explorat, rc guarda els resultats.
    //POST: no fa res.
    protected void nodeExplorat(Node n, ResultatCerca rc) {
    }

    //PRE: n és un node, lnt és la llista de nodes tancats o null si no es fa servir LNT.
    //POST: retorna TRUE si l'estat de n ja és a lnt, o a la branca de n si lnt és null, FALSE altrament.
    protected boolean esRepetit(Node n, Set<Mapa> lnt) {
        if (lnt != null) {
            return lnt.contains(n.estat);
        }
        return n.pare != null && n.pare.estaEnBranca(n.estat);
    }

    //PRE: n és un node.
    //POST: retorna els fills de n, un per cada moviment possible.
    protected List<Node> successors(Node n) {
        List<Node> fills = new ArrayList<>();
        for (Moviment mov : n.estat.getAccionsPossibles()) {
            Mapa fill = (Mapa) n.estat.mou(mov);
            fills.add(new Node(fill, n, mov, n.depth + 1, n.g + 1));
        }
        return fills;
    }

    //PRE: n és un node on algun agent encara no ha sortit.
    //POST: retorna els fills de n que mouen el primer agent que encara no ha sortit.
    protected List<Node> successorsPrimerAgent(Node n) {
        int primer = 0;
        while (!n.estat.esAgentActiu(primer)) {
            primer++;
        }

        List<Node> fills = new ArrayList<>();
        for (Moviment mov : n.estat.getAccionsPossibles()) {
            if (mov.getAgentId() == primer) {
                Mapa fill = (Mapa) n.estat.mou(mov);
                fills.add(new Node(fill, n, mov, n.depth + 1, n.g + 1));
            }
        }
        return fills;
    }
}
