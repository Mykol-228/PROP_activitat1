package edu.epsevg.prop.ac1.cerca;

import edu.epsevg.prop.ac1.cerca.model.Mapa;
import edu.epsevg.prop.ac1.resultat.ResultatCerca;

public class CercaIDS extends Cerca {
    private static final int LIMIT_PER_DEFECTE = 50;

    public CercaIDS(boolean usarLNT) { super(usarLNT); }

    //PRE: inicial és l'estat inicial, rc guarda els resultats.
    //POST: rc conté el resultat de fer DFS amb límit 1, 2, ... fins a 50 o fins que es troba la solució.
    @Override
    public void ferCerca(Mapa inicial, ResultatCerca rc) {
        for (int limit = 1; limit <= LIMIT_PER_DEFECTE; ++limit) {

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
