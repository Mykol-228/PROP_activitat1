package edu.epsevg.prop.ac1.cerca;

import edu.epsevg.prop.ac1.cerca.model.Mapa;
import edu.epsevg.prop.ac1.resultat.ResultatCerca;

public class CercaIDS extends Cerca {
    private static final int LIMIT_PER_DEFECTE = 50;

    public CercaIDS(boolean usarLNT) { super(usarLNT); }

    @Override
    public void ferCerca(Mapa inicial, ResultatCerca rc) {
        // Iterem incrementant el límit de profunditat de 1 en 1
        for (int limit = 1; limit <= LIMIT_PER_DEFECTE; ++limit) {

            // A cada iteració fem una DFS nova amb el límit actual
            CercaDFS dfs = new CercaDFS(usarLNT, limit);
            dfs.ferCerca(inicial, rc);

            if (rc.getSolucio() != null) {
                return;
            }
        }
    }

    @Override
    public String getNomCerca(){
        return "IDS";
    }
}
