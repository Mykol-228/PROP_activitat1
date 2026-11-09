(define (domain multi-snake)
  (:requirements :typing :negative-preconditions)

  (:types
    agent casella
  )

  (:predicates
    (adjacents ?c1 ?c2 - casella)
    (en ?a - agent ?c - casella)
    (lliure ?c - casella)
    (es-sortida-de ?c - casella ?a - agent)
    (ha-sortit ?a - agent)
  )

  ;; Moure's a una casella lliure que no és la pròpia sortida.
  ;; La casella d'origen queda com a rastre (paret) i la de destí passa a estar ocupada.
  (:action moure
    :parameters (?a - agent ?origen ?desti - casella)
    :precondition (and
      (en ?a ?origen)
      (adjacents ?origen ?desti)
      (lliure ?desti)
      (not (es-sortida-de ?desti ?a))
    )
    :effect (and
      (not (en ?a ?origen))
      (en ?a ?desti)
      (not (lliure ?origen))
      (not (lliure ?desti))
    )
  )

  ;; Moure's a la pròpia sortida: l'agent surt del tauler i la sortida queda segellada.
  (:action arribar-sortida
    :parameters (?a - agent ?origen ?desti - casella)
    :precondition (and
      (en ?a ?origen)
      (adjacents ?origen ?desti)
      (lliure ?desti)
      (es-sortida-de ?desti ?a)
    )
    :effect (and
      (ha-sortit ?a)
      (not (en ?a ?origen))
      (not (lliure ?origen))
      (not (lliure ?desti))
    )
  )
)
