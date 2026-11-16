package edu.epsevg.prop.ac1.cerca;

import edu.epsevg.prop.ac1.cerca.model.Mapa;
import edu.epsevg.prop.ac1.resultat.ResultatCerca;

import java.util.*;

public class CercaBFS extends Cerca {

    public CercaBFS(boolean usarLNT) {
        super(usarLNT);
    }

    //PRE: inicial és l'estat inicial, rc guarda els resultats.
    //POST: rc conté el resultat de la cerca en amplada.
    @Override
    public void ferCerca(Mapa inicial, ResultatCerca rc) {
        cercaGenerica(inicial, rc, new LinkedList<Node>());
    }

    /**
     * Torna el nom de la cerca.
     * @return el nom de la cerca.
     */
    public String getNomCerca(){
        return "BFS";
    }
}
