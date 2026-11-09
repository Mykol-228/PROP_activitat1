;; Mapa A (cXY = casella fila X, columna Y)
;;
;;   ########
;;   a·····A#
;;   #··#···#
;;   #B·····b
;;   ########

(define (problem mapa-a)
  (:domain multi-snake)

  (:objects
    agA agB - agent
    c1_0 c1_1 c1_2 c1_3 c1_4 c1_5 c1_6
         c2_1 c2_2      c2_4 c2_5 c2_6
         c3_1 c3_2 c3_3 c3_4 c3_5 c3_6 c3_7 - casella
  )

  (:init
    ;; Posició inicial dels agents
    (en agA c1_6)
    (en agB c3_1)

    ;; Caselles lliures (totes les transitables menys les ocupades pels agents)
    (lliure c1_0) (lliure c1_1) (lliure c1_2) (lliure c1_3) (lliure c1_4) (lliure c1_5)
    (lliure c2_1) (lliure c2_2) (lliure c2_4) (lliure c2_5) (lliure c2_6)
    (lliure c3_2) (lliure c3_3) (lliure c3_4) (lliure c3_5) (lliure c3_6) (lliure c3_7)

    ;; Sortides
    (es-sortida-de c1_0 agA)
    (es-sortida-de c3_7 agB)

    ;; Adjacències horitzontals
    (adjacents c1_0 c1_1) (adjacents c1_1 c1_0)
    (adjacents c1_1 c1_2) (adjacents c1_2 c1_1)
    (adjacents c1_2 c1_3) (adjacents c1_3 c1_2)
    (adjacents c1_3 c1_4) (adjacents c1_4 c1_3)
    (adjacents c1_4 c1_5) (adjacents c1_5 c1_4)
    (adjacents c1_5 c1_6) (adjacents c1_6 c1_5)

    (adjacents c2_1 c2_2) (adjacents c2_2 c2_1)
    (adjacents c2_4 c2_5) (adjacents c2_5 c2_4)
    (adjacents c2_5 c2_6) (adjacents c2_6 c2_5)

    (adjacents c3_1 c3_2) (adjacents c3_2 c3_1)
    (adjacents c3_2 c3_3) (adjacents c3_3 c3_2)
    (adjacents c3_3 c3_4) (adjacents c3_4 c3_3)
    (adjacents c3_4 c3_5) (adjacents c3_5 c3_4)
    (adjacents c3_5 c3_6) (adjacents c3_6 c3_5)
    (adjacents c3_6 c3_7) (adjacents c3_7 c3_6)

    ;; Adjacències verticals
    (adjacents c1_1 c2_1) (adjacents c2_1 c1_1)
    (adjacents c1_2 c2_2) (adjacents c2_2 c1_2)
    (adjacents c1_4 c2_4) (adjacents c2_4 c1_4)
    (adjacents c1_5 c2_5) (adjacents c2_5 c1_5)
    (adjacents c1_6 c2_6) (adjacents c2_6 c1_6)

    (adjacents c2_1 c3_1) (adjacents c3_1 c2_1)
    (adjacents c2_2 c3_2) (adjacents c3_2 c2_2)
    (adjacents c2_4 c3_4) (adjacents c3_4 c2_4)
    (adjacents c2_5 c3_5) (adjacents c3_5 c2_5)
    (adjacents c2_6 c3_6) (adjacents c3_6 c2_6)
  )

  (:goal
    (and
      (ha-sortit agA)
      (ha-sortit agB)
    )
  )
)
