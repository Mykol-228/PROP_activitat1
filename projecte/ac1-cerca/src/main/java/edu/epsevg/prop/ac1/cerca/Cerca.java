package edu.epsevg.prop.ac1.cerca;

import edu.epsevg.prop.ac1.cerca.model.Mapa;
import edu.epsevg.prop.ac1.model.Moviment;
import edu.epsevg.prop.ac1.resultat.ResultatCerca;

import java.util.ArrayList;
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
