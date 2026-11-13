package edu.epsevg.prop.ac1.cerca.model;

import edu.epsevg.prop.ac1.model.Direccio;
import edu.epsevg.prop.ac1.model.MapaBase;
import edu.epsevg.prop.ac1.model.Posicio;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Implementació concreta de {@link MapaBase}. Aquí és on cal decidir quan dos estats
 * són "el mateix" a efectes de detecció de cicles/repeticions (LNT): com que cada
 * moviment converteix la casella d'origen en paret de manera permanent, dos estats amb
 * els mateixos agents però un rastre de parets diferent <b>no</b> són el mateix estat
 * (podrien tenir moviments futurs possibles diferents). Per tant {@link #equals(Object)}
 * i {@link #hashCode()} han de tenir en compte tant la posició de cada agent com el
 * contingut complet de la graella.
 */
public class Mapa extends MapaBase {


    public Mapa(Path fitxer) throws IOException { super(fitxer); }

    public Mapa(Mapa other) { super(other); }

    @Override
    protected MapaBase clona() { return new Mapa(this); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Mapa other = (Mapa) o;

        if (!this.agents.equals(other.agents)) return false;

        //deepEquals permet comparar matrius
        if(!Arrays.deepEquals(this.grid, other.grid)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hash = java.util.Objects.hash(agents);
        hash = 31 * hash + java.util.Arrays.deepHashCode(this.grid);
        return hash;
    }

    /**
     * Distàncies mínimes (en nombre de passos,  tenint en compte de no passar per les parets
     * actuals ni trepitjar els agents) des de l'agent a la posició {@code src} a cada casella de la graella,
     * calculades amb BFS.
     * Útil per construir heurístiques informades..
     * @return matriu de distàncies indexat per {@code x, y}; Retorn {@code Integer.MAX_VALUE/4}
     *         si la casella és inabastable
     */
    @Override
    public int[][] distancesFrom(Posicio src) {
        // Inicialitzem la matriu de distàncies a INF
        int[][] dist = new int[this.n][this.m];
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                dist[i][j] = MapaBase.INF;
            }
        }

        // Comprovem la validesa de l'origen
        if (src == null || src.x < 0 || src.x >= this.n || src.y < 0 || src.y >= this.m) {
            return dist;
        }

        // Inicialitzem la cua BFS
        LinkedList<Posicio> cua = new LinkedList<>();
        dist[src.x][src.y] = 0;
        cua.add(src);

        // Bucle principal BFS
        while (!cua.isEmpty()) {
            Posicio actual = cua.poll();

            // Recorrem les 4 direccions possibles
            for (Direccio d : Direccio.values()) {
                Posicio seguent = actual.translate(d);

                // Comprovem límits de la graella
                if (seguent.x >= 0 && seguent.x < this.n && seguent.y >= 0 && seguent.y < this.m) {
                    
                    // Comprovem que no sigui paret
                    if (this.grid[seguent.x][seguent.y] != MapaBase.PARET) {
                        
                        // Comprovem si hi ha cap altre agent a la casella
                        boolean casellaOcupada = false;
                        for (int i = 0; i < this.agents.size() && !casellaOcupada; i++) {
                            Posicio agent = this.agents.get(i);
                            if (agent != null && agent.equals(seguent)) {
                                casellaOcupada = true;
                            }
                        }

                        // Si és transitable i no visitada
                        if (!casellaOcupada && dist[seguent.x][seguent.y] == MapaBase.INF) {
                            dist[seguent.x][seguent.y] = dist[actual.x][actual.y] + 1;
                            cua.add(seguent);
                        }
                    }
                }
            }
        }

        return dist;
    }
}
