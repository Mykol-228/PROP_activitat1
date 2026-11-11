package edu.epsevg.prop.ac1.cerca;

import edu.epsevg.prop.ac1.cerca.model.Mapa;
import edu.epsevg.prop.ac1.resultat.ResultatCerca;

import java.util.ArrayDeque;

public class CercaBFS extends Cerca {

    public CercaBFS(boolean usarLNT) {
        super(usarLNT);
    }

    @Override
    public void ferCerca(Mapa inicial, ResultatCerca rc) {
        // LNO com a cua (FIFO): s'explora primer el node més antic
        cercaGenerica(inicial, rc, new ArrayDeque<>());
    }

    /**
     * Torna el nom de la cerca.
     * @return el nom de la cerca.
     */
    public String getNomCerca(){
        return "BFS";
    }
}
