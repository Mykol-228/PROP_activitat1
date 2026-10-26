(define (problem mapa-a)
  (:domain multi-snake)
  
  (:objects 
    ;; 1. Declara aquí tots els agents i les caselles del teu mapA.map
    a1 - agent
    c0_0 c0_1 c0_2 c1_0 c1_1 c1_2 - casella
  )

  (:init
    ;; 2. Posició inicial de cada agent
    (en a1 c0_0)
    
    ;; 3. Caselles que es poden trepitjar (no incloguis les parets ni la casella on comença l'agent)
    (lliure c0_1)
    (lliure c0_2)
    (lliure c1_0)
    (lliure c1_1)
    (lliure c1_2)
    
    ;; 4. Casella de sortida assignada a cada agent
    (es-sortida-de c1_2 a1)
    
    ;; 5. Connexions del taulell (adjacències bidireccionals per moure's en les 4 direccions)
    (adjacents c0_0 c0_1) (adjacents c0_1 c0_0)
    (adjacents c0_1 c0_2) (adjacents c0_2 c0_1)
    
    (adjacents c0_0 c1_0) (adjacents c1_0 c0_0)
    (adjacents c0_1 c1_1) (adjacents c1_1 c0_1)
    (adjacents c0_2 c1_2) (adjacents c1_2 c0_2)
    
    (adjacents c1_0 c1_1) (adjacents c1_1 c1_0)
    (adjacents c1_1 c1_2) (adjacents c1_2 c1_1)
  )

  (:goal 
    ;; 6. L'objectiu és que tots els agents hagin sortit
    (and 
      (ha-sortit a1)
      ;; Afegeix (ha-sortit a2), etc., si hi ha més d'un agent.
    )
  )
)