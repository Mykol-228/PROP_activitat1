# Estudi: Multi-Snake

## 1. Entorn de proves

Totes les proves s'han fet a la mateixa màquina i en les mateixes condicions, perquè els resultats siguin comparables.

| Característica | Valor |
|---|---|
| Processador | Intel Core i5-10300H a 2,5 GHz |
| Memòria RAM | 16 GB |
| Sistema operatiu | Windows 11 |
| Versió de Java | JDK 25 (codi compilat per a Java 17) |
| Memòria màxima de la JVM | 2 GB (`-Xmx2g`) |
| Temps màxim per cerca | 120 s |

Condicions de les proves:
- Totes les execucions s'han fet amb l'opció `-Xmx2g`, que limita a 2 GB la memòria que pot fer servir la JVM.
- Una cerca que supera els 120 s es considera que no acaba.
- Una cerca que esgota els 2 GB de memòria s'indica com a OUT OF MEMORY.

## 2. Primera part: PDDL

Pla trobat amb el planificador LAMA-first a editor.planning.domains per al Mapa A:

```
(moure agb c3_1 c3_2)
(moure agb c3_2 c3_3)
(moure aga c1_6 c1_5)
(moure aga c1_5 c1_4)
(moure aga c1_4 c1_3)
(moure aga c1_3 c1_2)
(moure aga c1_2 c1_1)
(arribar-sortida aga c1_1 c1_0)
(moure agb c3_3 c3_4)
(moure agb c3_4 c3_5)
(moure agb c3_5 c3_6)
(arribar-sortida agb c3_6 c3_7)
```

**Longitud: 12 accions.**


## 3. Preguntes

**[1] Feu un quadre de resum dels resultats obtinguts per tots els mapes i algorismes.**

Totes les dades s'han obtingut a la màquina de l'apartat 1, amb `-Xmx2g` i un límit de 120 s.


**Mapa A**

| Algorisme | LNT | Troba solució | Vàlida | Longitud | Explorats | Tallats | Memòria LNO | Memòria LNT | Memòria total | Temps (ms) |
|---|---|---|---|---|---|---|---|---|---|---|
| BFS | No | Sí | Sí | 12 | 697.814 | 0 | 471.259 | 0 | 471.259 | 1.162 |
| BFS | Sí | Sí | Sí | 12 | 5.892 | 5.377 | 2.355 | 5.892 | 7.257 | 58 |
| DFS | No | Sí | Sí | 12 | 12 | 0 | 23 | 0 | 23 | 1 |
| DFS | Sí | Sí | Sí | 12 | 12 | 0 | 23 | 12 | 35 | 1 |
| IDS | No | Sí | Sí | 12 | 993.965 | 0 | 31 | 0 | 31 | 665 |
| IDS | Sí | Sí | Sí | 12 | 15.768 | 13.547 | 29 | 5.051 | 5.051 | 30 |
| A*(H.bas) | No | Sí | Sí | 12 | 12 | 0 | 23 | 0 | 23 | 2 |
| A*(H.bas) | Sí | Sí | Sí | 12 | 12 | 0 | 23 | 12 | 35 | 0 |
| A*(H.av) | No | Sí | Sí | 12 | 12 | 0 | 20 | 0 | 20 | 1 |
| A*(H.av) | Sí | Sí | Sí | 12 | 12 | 0 | 20 | 12 | 32 | 2 |
| BeamIteratiu(H.bas) | No | Sí | Sí | 12 | 12 | 0 | 11 | 0 | 11 | 1 |
| BeamIteratiu(H.bas) | Sí | Sí | Sí | 12 | 12 | 0 | 11 | 12 | 23 | 1 |
| BeamIteratiu(H.av) | No | Sí | Sí | 12 | 12 | 0 | 8 | 0 | 8 | 1 |
| BeamIteratiu(H.av) | Sí | Sí | Sí | 12 | 12 | 0 | 8 | 12 | 20 | 2 |

**Mapa B1**

| Algorisme | LNT | Troba solució | Vàlida | Longitud | Explorats | Tallats | Memòria LNO | Memòria LNT | Memòria total | Temps (ms) |
|---|---|---|---|---|---|---|---|---|---|---|
| BFS | No | No (out of memory) | - | - | 845.844 | 0 | 2.538.967 | 0 | 2.538.967 | 20.411 |
| BFS | Sí | No (out of memory) | - | - | 1.194.232 | 1.140.045 | 2.105.903 | 1.194.231 | 3.300.134 | 41.409 |
| DFS | No | Sí | Sí | 19 | 128 | 0 | 67 | 0 | 67 | 4 |
| DFS | Sí | Sí | Sí | 19 | 128 | 0 | 67 | 128 | 189 | 1 |
| IDS | No | No (>120 s) | - | - | - | - | - | - | - | >120.000 |
| IDS | Sí | No (out of memory) | - | - | 8.742.038 | 8.404.041 | 80 | 3.509.943 | 3.509.960 | 87.373 |
| A*(H.bas) | No | Sí | Sí | 17 | 27 | 0 | 66 | 0 | 66 | 2 |
| A*(H.bas) | Sí | Sí | Sí | 17 | 27 | 0 | 66 | 27 | 93 | 1 |
| A*(H.av) | No | Sí | Sí | 17 | 17 | 0 | 56 | 0 | 56 | 9 |
| A*(H.av) | Sí | Sí | Sí | 17 | 17 | 0 | 56 | 17 | 73 | 4 |
| BeamIteratiu(H.bas) | No | Sí | Sí | 19 | 19 | 0 | 31 | 0 | 31 | 1 |
| BeamIteratiu(H.bas) | Sí | Sí | Sí | 19 | 19 | 0 | 31 | 19 | 50 | 0 |
| BeamIteratiu(H.av) | No | Sí | Sí | 17 | 17 | 0 | 28 | 0 | 28 | 1 |
| BeamIteratiu(H.av) | Sí | Sí | Sí | 17 | 17 | 0 | 28 | 17 | 45 | 1 |

**Mapa B2**

| Algorisme | LNT | Troba solució | Vàlida | Longitud | Explorats | Tallats | Memòria LNO | Memòria LNT | Memòria total | Temps (ms) |
|---|---|---|---|---|---|---|---|---|---|---|
| BFS | No | No (out of memory) | - | - | 808.213 | 0 | 2.576.166 | 0 | 2.576.166 | 22.110 |
| BFS | Sí | No (out of memory) | - | - | 1.129.160 | 1.047.645 | 2.168.480 | 1.129.159 | 3.297.639 | 32.692 |
| DFS | No | Sí | Sí | 31 | 24.930.926 | 0 | 95 | 0 | 95 | 19.556 |
| DFS | Sí | Sí | Sí | 31 | 8.049 | 7.115 | 95 | 8.049 | 8.126 | 25 |
| IDS | No | No (>120 s) | - | - | - | - | - | - | - | >120.000 |
| IDS | Sí | No (out of memory) | - | - | 8.435.967 | 7.861.438 | 71 | 3.498.374 | 3.498.395 | 89.760 |
| A*(H.bas) | No | Sí | Sí | 19 | 648.327 | 0 | 1.061.058 | 0 | 1.061.058 | 2.416 |
| A*(H.bas) | Sí | Sí | Sí | 19 | 4.766 | 4.012 | 9.090 | 4.766 | 13.856 | 45 |
| A*(H.av) | No | Sí | Sí | 19 | 1.452 | 0 | 5.316 | 0 | 5.316 | 274 |
| A*(H.av) | Sí | Sí | Sí | 19 | 226 | 140 | 765 | 226 | 991 | 26 |
| BeamIteratiu(H.bas) | No | Sí | Sí | 25 | 22.380 | 0 | 355 | 0 | 355 | 38 |
| BeamIteratiu(H.bas) | Sí | Sí | Sí | 25 | 18.243 | 1.204 | 326 | 9.307 | 9.307 | 42 |
| BeamIteratiu(H.av) | No | Sí | Sí | 25 | 43 | 0 | 21 | 0 | 21 | 1 |
| BeamIteratiu(H.av) | Sí | Sí | Sí | 25 | 43 | 0 | 21 | 43 | 64 | 1 |

**Mapa C**

| Algorisme | LNT | Troba solució | Vàlida | Longitud | Explorats | Tallats | Memòria LNO | Memòria LNT | Memòria total | Temps (ms) |
|---|---|---|---|---|---|---|---|---|---|---|
| BFS | No | No (out of memory) | - | - | 27.355 | 0 | 224.326 | 0 | 224.326 | 3.665 |
| BFS | Sí | No (out of memory) | - | - | 31.848 | 39.204 | 219.648 | 31.847 | 251.495 | 5.387 |
| DFS | No | No (>120 s) | - | - | - | - | - | - | - | >120.000 |
| DFS | Sí | No (out of memory) | - | - | 252.289 | 355.639 | 500 | 252.281 | 252.766 | 9.169 |
| IDS | No | No (>120 s) | - | - | - | - | - | - | - | >120.000 |
| IDS | Sí | No (out of memory) | - | - | 486.211 | 686.714 | 82 | 252.736 | 252.752 | 14.385 |
| A*(H.bas) | No | Sí | Sí | 112 | 112 | 0 | 547 | 0 | 547 | 23 |
| A*(H.bas) | Sí | Sí | Sí | 112 | 112 | 0 | 547 | 112 | 659 | 18 |
| A*(H.av) | No | Sí | Sí | 112 | 112 | 0 | 541 | 0 | 541 | 309 |
| A*(H.av) | Sí | Sí | Sí | 112 | 112 | 0 | 541 | 112 | 653 | 179 |
| BeamIteratiu(H.bas) | No | Sí | Sí | 112 | 112 | 0 | 100 | 0 | 100 | 2 |
| BeamIteratiu(H.bas) | Sí | Sí | Sí | 112 | 112 | 0 | 100 | 112 | 212 | 4 |
| BeamIteratiu(H.av) | No | Sí | Sí | 112 | 112 | 0 | 100 | 0 | 100 | 77 |
| BeamIteratiu(H.av) | Sí | Sí | Sí | 112 | 112 | 0 | 100 | 112 | 212 | 69 |

**Mapa D**

| Algorisme | LNT | Troba solució | Vàlida | Longitud | Explorats | Tallats | Memòria LNO | Memòria LNT | Memòria total | Temps (ms) |
|---|---|---|---|---|---|---|---|---|---|---|
| BFS | No | No (out of memory) | - | - | 472.345 | 0 | 1.570.587 | 0 | 1.570.587 | 13.861 |
| BFS | Sí | No (out of memory) | - | - | 626.755 | 555.225 | 1.388.062 | 626.754 | 2.014.816 | 20.453 |
| DFS | No | No (>120 s) | - | - | - | - | - | - | - | >120.000 |
| DFS | Sí | No (out of memory) | - | - | 2.083.914 | 1.876.460 | 174 | 2.083.913 | 2.084.060 | 61.270 |
| IDS | No | No (>120 s) | - | - | - | - | - | - | - | >120.000 |
| IDS | Sí | No (out of memory) | - | - | 4.647.517 | 4.116.922 | 64 | 2.095.726 | 2.095.760 | 46.991 |
| A*(H.bas) | No | No (out of memory) | - | - | 741.803 | 0 | 1.336.054 | 0 | 1.336.054 | 12.556 |
| A*(H.bas) | Sí | No (out of memory) | - | - | 688.642 | 497.197 | 1.349.166 | 688.641 | 2.037.807 | 18.559 |
| A*(H.av) | No | No (out of memory) | - | - | 775.696 | 0 | 1.296.219 | 0 | 1.296.219 | 92.854 |
| A*(H.av) | Sí | Sí | Sí | 42 | 50.010 | 48.113 | 72.482 | 50.010 | 122.492 | 2.022 |
| BeamIteratiu(H.bas) | No | No (out of memory) | - | - | 6.168.953 | 0 | 450.074 | 0 | 450.074 | 25.541 |
| BeamIteratiu(H.bas) | Sí | No (out of memory) | - | - | 1.958.582 | 251.627 | 141.088 | 1.909.422 | 2.050.509 | 27.173 |
| BeamIteratiu(H.av) | No | Sí | Sí | 42 | 42 | 0 | 32 | 0 | 32 | 1 |
| BeamIteratiu(H.av) | Sí | Sí | Sí | 42 | 42 | 0 | 32 | 42 | 74 | 2 |

**Mapa E**

| Algorisme | LNT | Troba solució | Vàlida | Longitud | Explorats | Tallats | Memòria LNO | Memòria LNT | Memòria total | Temps (ms) |
|---|---|---|---|---|---|---|---|---|---|---|
| BFS | No | No (out of memory) | - | - | 57.558 | 0 | 405.270 | 0 | 405.270 | 4.986 |
| BFS | Sí | No (out of memory) | - | - | 68.977 | 92.182 | 393.087 | 68.976 | 462.063 | 7.773 |
| DFS | No | No (>120 s) | - | - | - | - | - | - | - | >120.000 |
| DFS | Sí | No (out of memory) | - | - | 465.789 | 668.119 | 407 | 465.785 | 466.133 | 13.598 |
| IDS | No | No (>120 s) | - | - | - | - | - | - | - | >120.000 |
| IDS | Sí | No (out of memory) | - | - | 808.967 | 1.125.114 | 80 | 466.347 | 466.382 | 13.801 |
| A*(H.bas) | No | No (out of memory) | - | - | 149.195 | 0 | 315.213 | 0 | 315.213 | 4.601 |
| A*(H.bas) | Sí | No (out of memory) | - | - | 118.284 | 114.726 | 344.487 | 118.283 | 462.770 | 5.394 |
| A*(H.av) | No | No (out of memory) | - | - | 138.920 | 0 | 325.573 | 0 | 325.573 | 156.300 |
| A*(H.av) | Sí | No (out of memory) | - | - | 123.455 | 106.457 | 339.612 | 123.454 | 463.066 | 126.808 |
| BeamIteratiu(H.bas) | No | No (out of memory) | - | - | 1.155.897 | 0 | 228.132 | 0 | 228.132 | 13.669 |
| BeamIteratiu(H.bas) | Sí | No (out of memory) | - | - | 542.343 | 5.611 | 1.000 | 464.003 | 465.003 | 14.856 |
| BeamIteratiu(H.av) | No | Sí | Sí | 151 | 3.683 | 0 | 100 | 0 | 100 | 1.240 |
| BeamIteratiu(H.av) | Sí | Sí | Sí | 153 | 3.574 | 16 | 100 | 3.574 | 3.674 | 1.103 |

**Mapa F**

| Algorisme | LNT | Troba solució | Vàlida | Longitud | Explorats | Tallats | Memòria LNO | Memòria LNT | Memòria total | Temps (ms) |
|---|---|---|---|---|---|---|---|---|---|---|
| BFS | No | No (out of memory) | - | - | 141.488 | 0 | 896.052 | 0 | 896.052 | 9.788 |
| BFS | Sí | No (out of memory) | - | - | 178.504 | 268.318 | 854.976 | 178.503 | 1.033.479 | 14.940 |
| DFS | No | No (>120 s) | - | - | - | - | - | - | - | >120.000 |
| DFS | Sí | No (out of memory) | - | - | 1.054.219 | 1.405.708 | 427 | 1.054.215 | 1.054.604 | 17.534 |
| IDS | No | No (>120 s) | - | - | - | - | - | - | - | >120.000 |
| IDS | Sí | No (out of memory) | - | - | 1.936.092 | 3.079.198 | 102 | 1.055.208 | 1.055.231 | 28.335 |
| A*(H.bas) | No | No (out of memory) | - | - | 293.652 | 0 | 753.453 | 0 | 753.453 | 6.830 |
| A*(H.bas) | Sí | No (out of memory) | - | - | 258.072 | 253.955 | 779.223 | 258.071 | 1.037.294 | 11.716 |
| A*(H.av) | No | No (out of memory) | - | - | 210.953 | 0 | 834.098 | 0 | 834.098 | 209.900 |
| A*(H.av) | Sí | No (out of memory) | - | - | 207.882 | 381.346 | 830.044 | 207.881 | 1.037.925 | 177.348 |
| BeamIteratiu(H.bas) | No | No (out of memory) | - | - | 6.884.743 | 0 | 353.693 | 0 | 353.693 | 22.951 |
| BeamIteratiu(H.bas) | Sí | No (out of memory) | - | - | 1.080.412 | 103.144 | 19.524 | 1.028.077 | 1.045.846 | 20.245 |
| BeamIteratiu(H.av) | No | Sí | Sí | 119 | 404.706 | 0 | 21.363 | 0 | 21.363 | 21.800 |
| BeamIteratiu(H.av) | Sí | Sí | Sí | 119 | 341.874 | 5.553 | 18.218 | 314.356 | 327.204 | 18.861 |

**[2] Per problema de cerca Multi-snake, té sentit buscar cicles en l'arbre de cerca pujant per l'arbre dins de la branca actual (usarLNT=false)? Justifiqueu-ho i comproveu-ho amb el comptador de nodes tallats.**

No té sentit. Cada moviment converteix la casella d'origen en paret, de manera que un fill sempre té més parets que qualsevol dels seus ancestres i no hi pot coincidir mai. Per tant, pujar per la branca buscant cicles no talla mai res i només fa perdre temps.

Ho confirma el comptador: amb usarLNT=false hi ha 0 nodes tallats a tots els mapes i a totes les cerques.

El que sí que hi ha són estats repetits en branques diferents, quan els agents es mouen en un altre ordre. Això només ho detecta la LNT. Sense LNT es gasta menys memòria, però es repeteix molta feina. Exemple amb la DFS al mapa B2:

| DFS mapa B2 	| Explorats 	| Tallats 	| Memòria total | 	Temps 	|
|	---	|	---	|	---	|	---	|	---	|
| Sense LNT 	| 24.930.926 	| 	0	| 	95	| 19.556 ms 	|
| Amb LNT 	| 8.049 	| 	7.115 	| 	8.126 	| 	25 ms 	|

Sense LNT la DFS no talla cap node i explora gairebé 25 milions de nodes. Amb LNT en talla 7.115 i és unes 780 vegades més ràpida, a canvi de gastar més memòria. Totes dues troben la mateixa solució de 31 moviments.


**[3] Calculeu el factor d'embrancament mitjà (real) del mapa A per BFS usant LNT i sense fer-la servir. Justifiqueu els avantatges d'usar la LNT respecte a no fer-la servir.**

| BFS mapa A | Moviments | Explorats | Tallats | Memòria (LNO / LNT / total) | Temps |
|---|---|---|---|---|---|
| Sense LNT | 12 | 697.814 | 0 | 471.259 / 0 / 471.259 | 1.162 ms |
| Amb LNT | 12 | 5.892 | 5.377 | 2.355 / 5.892 / 7.257 | 58 ms |

La formula és N=1+b+(b)^2+...+(b)^d on d és l'altura de l'arbre en aquest cas és 12 ja que els dos algoritmes necessiten 12 moviment per arribar a la solució. N son nodes explorats, en cas de LNT = fals son 697814 en cas de LNT = cert son 5892, entent aquestes dades podem deduir que embrancament al segon cas és menor ja que en 12 nivells es miren menys nodes que al primer. És a dir, que en general cada nivell de l'arbre conte menys nodes explorats. 


697814=1+b+(b)^2+...+(b)^12 provant diferents valors de b dona 2,97
5892=1+b+(b)^2+...+(b)^12 provant diferents valors de b dona 1,94

Avantatge d'utilitzar la LNT consisteix en que guardem els estats ja visitats, l'algorisme evita re-explorar estats repetits que es generen en moure els agents en diferent ordre (poda 5.377 nodes). Això fa que el factor d'embrancament real baixi, reduint el temps d'execució (de 1.162 ms a 58 ms) i també la memòria total consumida (de 471.259 a 7.257 nodes, ja que evita que la llista d'oberts creixi exponencialment). A més, en algorismes com DFS impedeix quedar atrapat en cicles infinits.

**[4] Expliqueu les diferències de resultats entre el mapa B1 i B2.**

La diferencia principal consisteix en que en el mapa B2 l'agent A talla el pas al agent B que complica molt la búsqueda.

DFS: al Map B1 troba solució immediatament explorant només 128 nodes (4 ms, 19 moviments), mentre que al Map B2 (sense LNT) cau repetidament en configuracions de bloqueig mutu i ha de fer backtracking massiu, disparant-se a 24.930.926 nodes explorats (19.556 ms) i una solució subòptima de 31 moviments. 
A*: com que l'heurística bàsica (H.bas) ignora els bloquejos mutus i les parets, al Map B2 sense LNT explora 648.327 nodes (2.416 ms) enfront dels només 27 nodes del Map B1. Fins i tot amb H.av i LNT, el Map B2 requereix 226 nodes explorats (26 ms) en comparació amb els 17 nodes (4 ms) del Map B1.

**[5] Expliqueu de forma justificada si les heurístiques proposades són admissibles. Són també consistents? Quina conseqüència té la consistència per a A* amb LNT?**

Són admissibles ja que cada moviment té cost 1 i cap de les dues heurístiques sobreestima mai el cost real total fins a l'objectiu: la bàsica calcula la distància de Manhattan (ignorant parets i serps) i l'avançada calcula el camí mínim amb parets, per tant el nombre real de moviments sempre serà igual o superior. També són consistents, ja que l'estimació es basa en càlculs de la graella i avançar una casella pel tauler mai reduirà la distància total estimada cap a la meta en més d'1 punt de cop.
La conseqüència per a A* amb LNT és que qualsevol node que ja s'hagi ficat a la LNT (perquè ja s'hi ha arribat pel camí òptim) pot ser descartat immediatament si l'algorisme s'hi torna a topar més endavant. No cal reobrir-lo mai. Això fa que l'algorisme processi menys nodes, estalviant temps i memòria massivament, tot garantint que al final ens donarà la solució més curta.

**[6] Quina heurística dona millors resultats? Per què?**

L'heurística Avançada dona millors resultats pel que fa a nodes explorats. En tenir en compte les parets i descartar estats on la sortida queda bloquejada, està molt més informada i poda l'arbre de cerca molt abans. Tanmateix, el cost computacional per calcular heurística (fer un BFS per cada estat) empitjora el temps total d'execució respecte a la bàsica en alguns casos.

**[7] Expliqueu la influència de les heurístiques al mapa C.**

Al Map C, la solució òptima té una longitud molt llarga (112 moviments), fet que provoca que els algorismes de cerca cega no puguin resoldre el problema dins dels límits de temps o memòria (la BFS i la IDS amb LNT es queden sense memòria, i la DFS i la IDS sense LNT superen els 120 s).
En canvi, les heurístiques guien el camí de manera òptima i permeten ignorar la resta de nodes, arribant a la solució explorant exactament 112 nodes (sense cap desviació). Això fa que l'algorisme sigui molt ràpid i utilitzi poca memòria, que és essencial quan el mapa és gran com el Map C. A més, com que totes dues exploren els mateixos 112 nodes, la heurística bàsica (H.bas) acaba sent més ràpida que l'avançada (18 ms contra 179 ms amb LNT) perquè costa menys de calcular.

**[8] Expliqueu la influència de les heurístiques al mapa D.**

El mapa D té la solució òptima a 42 moviments, i només el resol l'A* amb l'heurística avançada i LNT:

| 	Cerca 		| 		Resultat 								|
|	---		|		---									|
| A*(H.bas) sense LNT 	| 		Out of memory (741.803 explorats, 12.556 ms) 							|
| A*(H.bas) amb LNT 	| 		Out of memory (688.642 explorats, 18.559 ms) 							|
| A*(H.av) sense LNT 	| 		Out of memory (775.696 explorats, 92.854 ms)							|
| A*(H.av) amb LNT 	| 42 moviments, 50.010 explorats, 48.113 tallats, memòria total 122.492, 2.022 ms	|

L'heurística avançada funciona per dos motius:

1. És molt més precisa. A l'inici, H.bas val 18 i H.av val 38, i el cost real és 42. La bàsica ignora la paret de la fila 7, que obliga els agents a fer la volta, i l'A* ha d'explorar molts estats que semblen bons i no ho són.
2. Descarta estats sense solució. Les dues sortides són sota un passadís d'una sola casella d'amplada. Si un agent passa per sobre de la sortida de l'altre, la deixa inaccessible. L'avançada ho detecta i l'estat es descarta, mentre que la bàsica continua explorant-lo.

Sense LNT no acaba ni amb H.av, perquè el mateix estat s'hi arriba moltes vegades canviant l'ordre dels agents. Amb LNT es tallen 48.113 nodes repetits.

La cerca del repte mostra el mateix: amb H.av resol el mapa D explorant 42 nodes, i amb H.bas es queda sense memòria.


**[9] Per què els algorismes estàndard no són capaços de donar una solució als mapes E i F? Justifiqueu-ho numèricament.**

Són mapes més grans, amb 3 agents i solucions molt més llargues. L'heurística avançada, que mai sobreestima, dona 125 a l'inici del mapa E i 85 al F, així que les solucions tenen com a mínim aquests moviments.

Cerques cegues: amb el factor d'embrancament del mapa A, una BFS fins a profunditat 125 hauria de generar uns 10⁵⁹ nodes sense LNT i uns 10³⁶ amb LNT, i amb 2 GB de memòria només hi caben uns 460.000 estats del mapa E. La DFS i la IDS, a més, tenen el límit de 50 i no poden arribar a solucions de més de 85 moviments. La taula [1] ho confirma: la BFS es queda sense memòria en menys de 15 s, i la DFS i la IDS superen els 120 s o es queden sense memòria.

A*: es queda sense memòria en tots els casos.

| Mapa | Cerca | LNT | Explorats | Memòria total | Temps fins a esgotar la memòria |
|---|---|---|---|---|---|
| E | A*(H.bas) | No | 149.195 | 315.213 | 4.601 ms |
| E | A*(H.bas) | Sí | 118.284 | 462.770 | 5.394 ms |
| E | A*(H.av) | No | 138.920 | 325.573 | 156.300 ms |
| E | A*(H.av) | Sí | 123.455 | 463.066 | 126.808 ms |
| F | A*(H.bas) | No | 293.652 | 753.453 | 6.830 ms |
| F | A*(H.bas) | Sí | 258.072 | 1.037.294 | 11.716 ms |
| F | A*(H.av) | No | 210.953 | 834.098 | 209.900 ms |
| F | A*(H.av) | Sí | 207.882 | 1.037.925 | 177.348 ms |

Amb H.av, a més, passa dels 120 s abans d'esgotar la memòria. El problema és que l'heurística calcula la distància de cada agent per separat i no veu que es poden barrar el pas. Als dos mapes hi ha passos d'una sola casella: quan un agent hi passa, el deixa convertit en paret i la h d'un altre agent puja de cop. Llavors l'A* ha de tornar enrere i provar totes les variants del camí anterior.

A més, cada solució es pot fer en un nombre enorme d'ordres diferents dels agents (vegeu [11]): per a la solució de 151 moviments del mapa E n'hi ha uns 10⁶⁶.


**[10] Quin altre enfocament podríem prendre per solucionar E i F? Si heu implementat el repte, discutiu si el vostre algorisme és complet i si garanteix la solució òptima.**

Hem implementat una Beam Search amb amplada creixent (CercaBeamIterativa), que combina tres idees:

1. Restricció d'ordre dels agents: només es mou el primer agent que encara no ha sortit. Això elimina les seqüències equivalents sense perdre cap solució (vegeu [11]).
2. Heurística avançada: la LNO s'ordena per h, com a la Best-First, i es descarten els estats on alguna sortida és inabastable.
3. Amplada creixent, com la IDS però amb l'amplada: primer una Beam Search que només guarda els 100 millors nodes; si no troba solució, una que en guarda 1.000; i si tampoc, una Best-First sense límit.

Resultats amb H.av:

| Mapa | LNT | Moviments | Explorats | Memòria (LNO / LNT / total) | Temps |
|---|---|---|---|---|---|
| A | No / Sí | 12 / 12 | 12 / 12 | 8 / 0 / 8 — 8 / 12 / 20 | 1 ms / 2 ms |
| B1 | No / Sí | 17 / 17 | 17 / 17 | 28 / 0 / 28 — 28 / 17 / 45 | 1 ms / 1 ms |
| B2 | No / Sí | 25 / 25 | 43 / 43 | 21 / 0 / 21 — 21 / 43 / 64 | 1 ms / 1 ms |
| C | No / Sí | 112 / 112 | 112 / 112 | 100 / 0 / 100 — 100 / 112 / 212 | 77 ms / 69 ms |
| D | No / Sí | 42 / 42 | 42 / 42 | 32 / 0 / 32 — 32 / 42 / 74 | 1 ms / 2 ms |
| **E** | No | **151** | 3.683 | 100 / 0 / 100 | **1.240 ms** |
| **E** | Sí | **153** | 3.574 | 100 / 3.574 / 3.674 | **1.103 ms** |
| **F** | No | **119** | 404.706 | 21.363 / 0 / 21.363 | **21.800 ms** |
| **F** | Sí | **119** | 341.874 | 18.218 / 314.356 / 327.204 | **18.861 ms** |

El mapa E es resol al primer intent i el F a l'últim, tots dos molt per sota dels 120 s. Amb H.bas es queda sense memòria als mapes D, E i F, perquè no descarta els estats sense solució.

És completa, perquè l'últim intent és una Best-First amb LNT i l'espai d'estats és finit. No és òptima, perquè ordena només per h: al mapa B2 troba 25 moviments quan l'òptim és 19. Als mapes E i F no coneixem l'òptim, però és com a mínim 125 i 85, així que les nostres solucions de 151 i 119 hi són com a molt a 26 i 34 moviments.

Altres enfocaments possibles serien un A* ponderat, una heurística que detecti els passos d'una sola casella o planificar els agents un per un.


**[11] Demostreu que, en aquest problema, l'ordre en què es mouen els agents no afecta la validesa ni la longitud d'una solució (pista: els camins de dos agents diferents mai comparteixen cap casella). Si els agents fan N moviments cadascun, quantes seqüències de moviments diferents representen la mateixa solució? Com podríeu aprofitar-ho per reduir l'espai de cerca?**

Els camins de dos agents no comparteixen cap casella. Si un agent passa per una casella, mentre hi és l'altre no hi pot entrar, i quan se'n va queda convertida en paret. El mateix passa amb la sortida quan l'agent surt.

Per tant, si canviem l'ordre en què es mouen els agents, mantenint l'ordre dels moviments de cadascun, cada moviment continua sent vàlid: la casella on va no pot haver estat tapada ni ocupada per un altre agent. El nombre de moviments és el mateix, així que la longitud tampoc canvia.

Si hi ha k agents i cadascun fa N moviments, el nombre de seqüències que representen la mateixa solució és:

(k·N)! / (N!)ᵏ

Al mapa A, amb 2 agents de 6 moviments, són 924 seqüències, i al mapa D ja en són uns 5·10¹¹.

Per aprofitar-ho n'hi ha prou amb fixar un ordre: moure sempre el primer agent que encara no ha sortit. Així cada solució apareix una sola vegada a l'arbre i el factor d'embrancament baixa, sense perdre cap solució ni l'optimalitat. És el que fa la nostra cerca del repte. La LNT també ho aprofita en part: al mapa A, la BFS passa de 697.814 a 5.892 nodes explorats.


**[12] Quins dels algorismes implementats (BFS, DFS, IDS, A\* amb cada heurística) garanteixen trobar la solució òptima en aquest problema? Especifica la resposta quan s'usa o no la LNT.**

En aquest problema un mateix estat sempre es troba a la mateixa profunditat, perquè cada moviment afegeix una paret. Per això la LNT mai descarta un camí més curt, i fer-la servir no canvia si un algorisme és òptim o no.

| Algorisme | Sense LNT | Amb LNT | Justificació |
|---|---|---|---|
| **BFS** | Òptim | Òptim | Tots els moviments costen 1 i explora per nivells, així que la primera solució que troba és la més curta. Amb LNT continua sent òptim per la propietat anterior. Però a partir del mapa B1 es queda sense memòria (vegeu [1]) |
| **DFS** | No | No | Retorna la primera solució que troba en profunditat. Al mapa B1 troba 19 moviments quan l'òptim és 17, i al B2 en troba 31 quan l'òptim és 19. A més, amb el límit de 50 no pot trobar solucions més llargues (no és completa per als mapes C, E i F) |
| **IDS** | Òptim (fins a 50) | Òptim (fins a 50) | Prova límits 1, 2, 3…, així que troba la solució menys profunda. Amb LNT continua sent òptim per la mateixa propietat. Però amb el tope de 50 no pot trobar solucions més llargues (per exemple, el mapa C, de 112) |
| **A\* H.bas** | Òptim | Òptim | H.bas és admissible, així que l'A* troba l'òptim. Amb LNT, a més, cal que sigui consistent, i ho és (pregunta [5]) |
| **A\* H.av** | Òptim | Òptim | El mateix argument. A més, només descarta estats amb h = INF, que no tenen cap solució |
| BeamIteratiu (repte) | No | No | Ordena només per h i, en els primers intents, llença nodes (pregunta [10]) |

Ho confirmen els resultats: quan la BFS, la IDS o l'A* acaben, troben la mateixa longitud a tots els mapes. La DFS i la BeamIteratiu, en canvi, poden donar solucions més llargues, com al mapa B2, on en troben de 31 i 25 moviments en lloc de 19.


**[13] Compareu la memòria i el temps de BFS i IDS en els mapes on tots dos acaben. S'ajusten els resultats al que prediu la teoria?**

Només acaben totes dues al mapa A.

| Mapa A | Explorats | Memòria pic (LNO / LNT / total) | Temps |
|---|---|---|---|
| BFS sense LNT | 697.814 | 471.259 / 0 / 471.259 | 1.162 ms |
| IDS sense LNT | 993.965 | 31 / 0 / 31 | 665 ms |
| BFS amb LNT | 5.892 | 2.355 / 5.892 / 7.257 | 58 ms |
| IDS amb LNT | 15.768 | 29 / 5.051 / 5.051 | 30 ms |

Segons la teoria, la BFS gasta memòria exponencial i la IDS lineal, i la IDS explora més nodes perquè repeteix els nivells superiors a cada iteració.

- Memòria: es compleix. Sense LNT, la BFS arriba a 471.259 nodes i la IDS només a 31. Amb LNT la diferència és menor, perquè la LNT de la IDS també guarda tots els estats explorats.
- Nodes explorats: es compleix. Sense LNT la IDS n'explora 1,42 vegades més, molt a prop de l'1,51 que preveu la teoria. Amb LNT en són 2,68, una mica més del previst, perquè la LNT es buida a cada iteració.
- Temps: no es compleix. La IDS és més ràpida tot i explorar més nodes, perquè la BFS ha de crear i guardar centenars de milers de nodes, i això té un cost que la teoria no té en compte.

