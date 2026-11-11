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
    public int h;       // heurística (només A*)

    public Node(Mapa estat, Node pare, Moviment accio, int depth, int g) {
        this.estat = estat;
        this.pare = pare;
        this.accio = accio;
        this.depth = depth;
        this.g = g;
    }

    // Retorna els moviments des de l'arrel fins a aquest node
    public List<Moviment> getCami() {
        List<Moviment> cami = new LinkedList<>();
        Node aux = this;
        while (aux.pare != null) {
            cami.add(0, aux.accio);
            aux = aux.pare;
        }
        return cami;
    }

    // Mira si l'estat e és en aquest node o en algun dels seus pares
    public boolean estaEnBranca(Mapa e) {
        Node aux = this;
        while (aux != null) {
            if (aux.estat.equals(e)) {
                return true;
            }
            aux = aux.pare;
        }
        return false;
    }
}
