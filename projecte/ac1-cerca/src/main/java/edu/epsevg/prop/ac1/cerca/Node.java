package edu.epsevg.prop.ac1.cerca;

import edu.epsevg.prop.ac1.cerca.model.Mapa;
import edu.epsevg.prop.ac1.model.Moviment;

import java.util.LinkedList;
import java.util.List;

/**
 * Node de l'arbre de cerca: guarda un estat ({@link Mapa}), el node pare, l'acció
 * (moviment) que l'ha generat, la profunditat respecte l'arrel i el cost acumulat
 * {@code g} (nombre de moviments fets fins arribar-hi).
 */
public class Node {
    public final Mapa estat;
    public final Node pare;
    public final Moviment accio;
    public final int depth;
    public final int g; // cost

    public Node(Mapa estat, Node pare, Moviment accio, int depth, int g) {
        this.estat = estat;
        this.pare = pare;
        this.accio = accio;
        this.depth = depth;
        this.g = g;
    }

    /**
     * Reconstrueix el camí des de l'arrel fins a aquest node pujant pels pares.
     * @return la llista de moviments en ordre (buida si és l'arrel)
     */
    public List<Moviment> getCami() {
        LinkedList<Moviment> cami = new LinkedList<>();
        for (Node n = this; n.pare != null; n = n.pare) {
            cami.addFirst(n.accio);
        }
        return cami;
    }

    /**
     * Mira si {@code e} apareix en aquest node o en algun dels seus ancestres
     * (control de cicles dins de la branca actual).
     */
    public boolean estaEnBranca(Mapa e) {
        for (Node n = this; n != null; n = n.pare) {
            if (n.estat.equals(e)) return true;
        }
        return false;
    }
}
