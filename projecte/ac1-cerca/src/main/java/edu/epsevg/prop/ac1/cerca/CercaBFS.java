package edu.epsevg.prop.ac1.cerca;

import edu.epsevg.prop.ac1.cerca.model.Mapa;
import edu.epsevg.prop.ac1.model.Moviment;
import edu.epsevg.prop.ac1.resultat.ResultatCerca;

import java.util.*;

public class CercaBFS extends Cerca {

    public CercaBFS(boolean usarLNT) {
        super(usarLNT);
    }

    @Override
    public void ferCerca(Mapa inicial, ResultatCerca rc) {
        Queue<Node> lno = new ArrayDeque<>();
        Set<Mapa> lnt = usarLNT ? new HashSet<>() : null;

        lno.add(new Node(inicial, null, null, 0, 0));

        while (!lno.isEmpty()) {
            Node actual = lno.poll();

            // Control de cicles: per LNT o dins de la branca
            if (esRepetit(actual, lnt)) {
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

            lno.addAll(successors(actual));
            rc.updateMemoria(lno.size(), usarLNT ? lnt.size() : 0);
        }
        // Si la LNO és buida no hi ha solució: la solució queda a null
    }
    
    /**
     * Torna el nom de la cerca.
     * @return el nom de la cerca.
     */
    public String getNomCerca(){
        return "BFS";
    }    
}
