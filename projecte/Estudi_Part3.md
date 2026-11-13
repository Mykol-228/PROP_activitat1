**[1] Feu un quadre de resum dels resultats obtinguts per tots els mapes i algorismes.**

*Map A*
BFS;NO;mapA;YES;YES;12;697814;0;471259;0;471259;1204
BFS;YES;mapA;YES;YES;12;5892;5377;2355;5892;7257;62
DFS;NO;mapA;YES;YES;12;12;0;23;0;23;0
DFS;YES;mapA;YES;YES;12;12;0;23;12;35;1
IDS;NO;mapA;YES;YES;12;993965;0;31;0;31;668
IDS;YES;mapA;YES;YES;12;15768;13547;29;5051;5051;24
A*(H.bas);NO;mapA;YES;YES;12;12;0;23;0;23;0
A*(H.bas);YES;mapA;YES;YES;12;12;0;23;12;35;0
A*(H.av);NO;mapA;YES;YES;12;12;0;20;0;20;2
A*(H.av);YES;mapA;YES;YES;12;12;0;20;12;32;1


*Map B1*
BFS;NO;mapB1;NO(OUT OF MEMORY ERROR);-;-;845939;0;2539220;0;2539220;17103
BFS;YES;mapB1;NO(OUT OF MEMORY ERROR);-;-;1194281;1140092;2105961;1194280;3300241;35816
DFS;NO;mapB1;YES;YES;19;128;0;67;0;67;5
DFS;YES;mapB1;YES;YES;19;128;0;67;128;189;2
IDS;NO;mapB1;NO         //s'ha passat de 120 segons
IDS;YES;mapB1;NO(OUT OF MEMORY ERROR);-;-;8742156;8404233;80;3510062;3510081;91713
A*(H.bas);NO;mapB1;YES;YES;17;27;0;66;0;66;63
A*(H.bas);YES;mapB1;YES;YES;17;27;0;66;27;93;2
A*(H.av);NO;mapB1;YES;YES;17;17;0;56;0;56;13
A*(H.av);YES;mapB1;YES;YES;17;17;0;56;17;73;8


*Map B2*
BFS;NO;mapB2;NO(OUT OF MEMORY ERROR);-;-;808301;0;2576406;0;2576406;22595
BFS;YES;mapB2;NO(OUT OF MEMORY ERROR);-;-;1129330;1047774;2168731;1129329;3298060;35375
DFS;NO;mapB2;YES;YES;31;24930926;0;95;0;95;19487
DFS;YES;mapB2;YES;YES;31;8049;7115;95;8049;8126;28
IDS;NO;mapB2;           //s'ha passat de 120 segons
IDS;YES;mapB2;NO(OUT OF MEMORY ERROR);-;-;8435834;7861239;71;3498240;3498265;92339
A*(H.bas);NO;mapB2;YES;YES;19;469867;0;780309;0;780309;311809  //s'ha passat de 120 segons
A*(H.bas);YES;mapB2;YES;YES;19;6006;4702;11541;6006;17547;68
A*(H.av);NO;mapB2;YES;YES;19;1443;0;5302;0;5302;143
A*(H.av);YES;mapB2;YES;YES;19;305;195;1046;305;1351;84


*Map C*
DFS;NO;mapC;    //s'ha passat de 120 segons
DFS;YES;mapC;   //s'ha passat de 120 segons
A*(H.bas);NO;mapC;YES;YES;112;112;0;547;0;547;25
A*(H.bas);YES;mapC;YES;YES;112;112;0;547;112;659;11
A*(H.av);NO;mapC;YES;YES;112;112;0;541;0;541;249
A*(H.av);YES;mapC;YES;YES;112;112;0;541;112;653;145


*Map D*
A*(H.bas);NO;mapD;      //s'ha passat de 120 segons
A*(H.bas);YES;mapD;     //s'ha passat de 120 segons
A*(H.av);NO;mapD;       //s'ha passat de 120 segons
A*(H.av);YES;mapD;YES;YES;42;50010;48113;72482;50010;122492;2851

**[2] Per problema de cerca Multi-snake, té sentit buscar cicles en l'arbre de cerca pujant per l'arbre dins de la branca actual (usarLNT=false)? Justifiqueu-ho i comproveu-ho amb el comptador de nodes tallats.**

Té sentit per estalviar memòria, ja que comprovar l'arbre només cap amunt fins a l'arrel consumeix memòria lineal, mentre que utilitzar una LNT per registrar tots els estats visitats consumeix memòria exponencial i pot causar desbordaments. No obstant això, el control exclusiu per la branca actual només detecta cicles mirant als pares i és incapaç de detectar cicles dels altres nodes. Això provoca que s'explora camins redundants de forma massiva.   

El primer cas LTN és fals i al segon true:

Executant CercaDFS sobre mapB2
[DFS/mapB2] 20s: explorats=24468601 tallats=0 profunditatMax=36 memòria(oberts/tancats/total)=95/0/95
> SOLUCIO: [Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> AVALL, Agent 1 -> ESQUERRA, Agent 1 -> ESQUERRA, Agent 1 -> ESQUERRA, Agent 1 -> ESQUERRA, Agent 1 -> ESQUERRA, Agent 1 -> ESQUERRA, Agent 1 -> AVALL, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> AVALL, Agent 1 -> DRETA, Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 0 -> AVALL, Agent 0 -> AVALL, Agent 0 -> AVALL, Agent 0 -> ESQUERRA]
        CercaDFS -> Resultat: 31 moviments, explorats=24930926, tallats=0, profunditatMax=36, memòria(oberts/tancats/total)=95/0/95, temps=20395ms

Executant CercaDFS sobre mapB2
> SOLUCIO: [Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> AVALL, Agent 1 -> ESQUERRA, Agent 1 -> ESQUERRA, Agent 1 -> ESQUERRA, Agent 1 -> ESQUERRA, Agent 1 -> ESQUERRA, Agent 1 -> ESQUERRA, Agent 1 -> AVALL, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> AVALL, Agent 1 -> DRETA, Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 0 -> AVALL, Agent 0 -> AVALL, Agent 0 -> AVALL, Agent 0 -> ESQUERRA]
        CercaDFS -> Resultat: 31 moviments, explorats=8049, tallats=7115, profunditatMax=36, memòria(oberts/tancats/total)=95/8049/8126, temps=61ms

Com es pot veure, el primer té 0 nodes tallats (és a dir ningún node pare no s'ha repetit) i ha tardat molt més perquè ha hagut d'explorar molts nodes repetits, mentre que l'altre ha gastat més memòria per guardar la LNT (8049 tancats) però ha tallat 7115 nodes i ha sigut molt més ràpid (8049 explorats en 61ms comparant amb 24930926 en 20395ms).

**[3] Calculeu el factor d'embrancament mitjà (real) del mapa A per BFS usant LNT i sense fer-la servir. Justifiqueu els avantatges d'usar la LNT respecte a no fer-la servir.**

Executant CercaBFS sobre mapA
> SOLUCIO: [Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA]
        CercaBFS -> Resultat: 12 moviments, explorats=697814, tallats=0, profunditatMax=12, memòria(oberts/tancats/total)=471259/0/471259, temps=1161ms

Executant CercaBFS sobre mapA
> SOLUCIO: [Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA]
        CercaBFS -> Resultat: 12 moviments, explorats=5892, tallats=5377, profunditatMax=12, memòria(oberts/tancats/total)=2355/5892/7257, temps=50ms

La formula és N=1+b+(b)^2+...+(b)^d on d és l'altura de l'arbre en aquest cas és 12 ja que els dos algoritmes necessiten 12 moviment per arribar a la solució. N son nodes explorats, en cas de LNT = fals son 697814 en cas de LNT = cert son 5892, entent aquestes dades podem deduir que embrancament al segon cas és menor ja que en 12 nivells es miren menys nodes que al primer. És a dir, que en general cada nivell de l'arbre conte menys nodes explorats. 


697814=1+b+(b)^2+...+(b)^12 provant diferents valors de b dona 2,97
5892=1+b+(b)^2+...+(b)^12 provant diferents valors de b dona 1,94

Avantatge d'utilitzar la LNT consisteix en que guardem els estats ja visitats, l'algorisme evita re-explorar estats repetits que es generen en moure els agents en diferent ordre (poda 5.377 nodes). Això fa que el factor d'embrancament real baixi, reduint el temps d'execució (de 1.161ms a 50ms) i també la memòria total consumida (de 471.259 a 7.257 nodes, ja que evita que la llista d'oberts creixi exponencialment). A més, en algorismes com DFS impedeix quedar atrapat en cicles infinits.

**[4] Expliqueu les diferències de resultats entre el mapa B1 i B2.**

La diferencia principal consisteix en que en el mapa B2 l'agent A talla el pas al agent B que complica molt la búsqueda.

DFS: al Map B1 troba solució immediatament explorant només 128 nodes (5ms, 19moviments), mentre que al Map B2 (sense LNT) cau repetidament en configuracions de bloqueig mutu i ha de fer backtracking massiu, disparant-se a 24.930.926 nodes explorats (19487ms) i una solució subòptima de 31 moviments. 
A*: com que l'heurística bàsica (H.bas) ignora els bloquejos mutus i les parets, al Map B2 sense LNT explora 469.867 nodes (>120s) enfront dels només 27 nodes del Map B1. Fins i tot amb H.av i LNT, el Map B2 requereix 305 nodes explorats (84ms) en comparació amb els 17 nodes (8ms) del Map B1.

**[5] Expliqueu de forma justificada si les heurístiques proposades són admissibles. Són també consistents? Quina conseqüència té la consistència per a A* amb LNT?**

Són admissibles ja que cada moviment té cost 1 i cap de les dues heurístiques sobreestima mai el cost real total fins a l'objectiu: la bàsica calcula la distància de Manhattan (ignorant parets i serps) i l'avançada calcula el camí mínim amb parets, per tant el nombre real de moviments sempre serà igual o superior. També són consistents, ja que l'estimació es basa en càlculs de la graella i avançar una casella pel tauler mai reduirà la distància total estimada cap a la meta en més d'1 punt de cop.
La conseqüència per a A* amb LNT és que qualsevol node que ja s'hagi ficat a la LNT (perquè ja s'hi ha arribat pel camí òptim) pot ser descartat immediatament si l'algorisme s'hi torna a topar més endavant. No cal reobrir-lo mai. Això fa que l'algorisme processi menys nodes, estalviant temps i memòria massivament, tot garantint que al final ens donarà la solució més curta.

**[6] Quina heurística dona millors resultats? Per què?**

L'heurística Avançada dona millors resultats pel que fa a nodes explorats. En tenir en compte les parets i descartar estats on la sortida queda bloquejada, està molt més informada i poda l'arbre de cerca molt abans. Tanmateix, el cost computacional per calcular heurística (fer un BFS per cada estat) empitjora el temps total d'execució respecte a la bàsica en alguns casos.

**[7] Expliqueu la influència de les heurístiques al mapa C.**

Al Map C, la solució òptima té una longitud molt llarga (112 moviments), fet que provoca que els algorismes de cerca cega no puguin resoldre el problema dins dels límits de temps o memòria (DFS supera els 120s).
En canvi, les heurístiques guien el camí de manera òptima i permeten ignorar la resta de nodes, arribant a la solució explorant exactament 112 nodes (sense cap desviació). Això fa que l'algorisme sigui molt ràpid i utilitzi poca memòria, que és essencial quan el mapa és gran com el Map C. A més, com que totes dues exploren els mateixos 112 nodes, la heurística bàsica (H.bas) acaba sent més ràpida que l'avançada (11ms vs 145ms amb LNT) perquè costa menys de calcular.

**[8] Expliqueu la influència de les heurístiques al mapa D.**

**[9] Per què els algorismes estàndard no són capaços de donar una solució als mapes E i F? Justifiqueu-ho numèricament.**

**[10] Quin altre enfocament podríem prendre per solucionar E i F? Si heu implementat el repte, discutiu si el vostre algorisme és complet i si garanteix la solució òptima.**

**[11] Demostreu que, en aquest problema, l'ordre en què es mouen els agents no afecta la validesa ni la longitud d'una solució (pista: els camins de dos agents diferents mai comparteixen cap casella). Si els agents fan N moviments cadascun, quantes seqüències de moviments diferents representen la mateixa solució? Com podríeu aprofitar-ho per reduir l'espai de cerca?**

**[12] Quins dels algorismes implementats (BFS, DFS, IDS, A* amb cada heurística) garanteixen trobar la solució òptima en aquest problema? Especifica la resposta quan s'usa o no la LNT.**

**[13] Compareu la memòria i el temps de BFS i IDS en els mapes on tots dos acaben. S'ajusten els resultats al que prediu la teoria?**

Solament els dos acaben al mapA.

Executant CercaBFS sobre mapA
> SOLUCIO: [Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA]

`CercaBFS -> Resultat: 12 moviments, explorats = 5892, tallats = 5377, profunditatMax = 12, memòria (oberts/tancats/total)=2355/5892/7257, temps=65ms`

Executant CercalDS sobre mapA
> SOLUCIO: [Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 1 -> DRETA, Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA, Agent 0 -> ESQUERRA]

`CercalDS -> Resultat: 12 moviments, explorats = 15768, tallats = 13547, profunditatMax = 11, memòria (oberts/tancats/total)=29/5051/5051, temps=68ms`

Tant la Cerca BFS com la Cerca IDS garanteixen trobar la solució òptima, però difereixen en el consum de recursos. La BFS consumeix memòria exponencial (pic de 2355 nodes oberts i 7257 en total) perquè guarda tota l'amplada de l'arbre a la Frontera. L'IDS, en fer successives cerques DFS, només gasta memòria lineal per a la branca actual (pic de 29 nodes oberts), tot i que en usar LNT la memòria total arriba a 5051 nodes pels tancats; sense LNT aquest estalvi es veu encara més clar (31 nodes totals a IDS enfront dels 471259 de BFS). A canvi d'aquest estalvi d'espai, l'IDS penalitza els nodes explorats (15768 vs 5892 amb LNT, i 993965 vs 697814 sense LNT) i el temps d'execució (68ms vs 65ms) perquè re-explora els nivells superiors a cada iteració. Els resultats s'ajusten bé al que prediu la teoria d'aquests algorismes.