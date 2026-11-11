package edu.epsevg.prop.ac1.cerca;

import edu.epsevg.prop.ac1.cerca.model.Mapa;
import edu.epsevg.prop.ac1.resultat.ResultatCerca;

public class CercaIDS extends Cerca {

    public CercaIDS(boolean usarLNT) { super(usarLNT); }

    /**
     * Repeteix una DFS amb límit 0, 1, 2... fins que troba solució. Els nodes explorats
     * s'acumulen a {@code rc} entre iteracions. Si una iteració no ha hagut de deixar cap
     * node sense expandir pel límit, ja s'ha recorregut tot l'arbre i no hi ha solució.
     */
    @Override
    public void ferCerca(Mapa inicial, ResultatCerca rc) {
        for (int limit = 0; ; limit++) {
            CercaDFS dfs = new CercaDFS(usarLNT, limit);
            dfs.ferCerca(inicial, rc);

            if (rc.getSolucio() != null || !dfs.tallatPerProfunditat) return;
        }
    }

    @Override
    public String getNomCerca(){
        return "IDS";
    }
}
