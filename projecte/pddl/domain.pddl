(define (domain multi-snake)
  (:requirements :typing)
  
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

  (:action moure
    :parameters (?a - agent ?origen ?desti - casella)
    :precondition (and
      (not (ha-sortit ?a))
      (en ?a ?origen)
      (adjacents ?origen ?desti)
      (lliure ?desti)
      (not (es-sortida-de ?desti ?a))
    )
    :effect (and
      (not (en ?a ?origen))
      (en ?a ?desti)
      (not (lliure ?origen))
    )
  )

  (:action arribar-sortida
    :parameters (?a - agent ?origen ?desti - casella)
    :precondition (and
      (not (ha-sortit ?a))
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