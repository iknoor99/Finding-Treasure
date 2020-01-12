(defn read_file []
   (with-open [rdr (clojure.java.io/reader "map.txt")]
   ;;(println count(line-seq rdr))
   (reduce conj [] (line-seq rdr))
   )
   )


(defn stop_func[mat rootarr_x rootarr_y rate_x rate_y height width] 

		(aset rootarr_x 0 0 -8)
  (aset rootarr_y 0 0 -8)

		(def addition [])


  (def x rate_x)
  (def y rate_y)

   (while (and (> x -8) (> y -8)) 
      (do 

      		(def addition(conj addition [x,y]))

      		(def a(aget rootarr_x x y))
      		(def b(aget rootarr_y x y))

      		(def x a)
      		(def y b)


      )
   )


			(doseq [cord addition]

				(def x_cord (first cord))
				(def y_cord (last cord))
				
				(aset mat x_cord y_cord \+)
   )

			(aset mat rate_x rate_y \@)


			(println " ")

			(loop [x 0] 
				(when (< x height)
						(loop [y 0]
								(when (< y width)

												(if ( = (aget mat x y ) \!)
							      		(print "!")
							      		
							     )

							     (if ( = (aget mat x y ) \-)
							      		(print "-")
							      		
							     )

							     (if ( = (aget mat x y) \#)
							      		(print "#")
							      		
							     )

							     (if ( = (aget mat x y ) \+)
							      		(print "+")
							      		
							     )

							     (if ( = (aget mat x y ) \@)
							     (do

							      		(print "@")
							     

							     ) 		
							     )
											
									(recur (+ y 1))
									);; when y
									);; loop y
									(println " ")

				(recur (+ x 1))
				);; when x
			);; loop x   

  (System/exit 0)

)

(defn free_path[mat x y height width rootarr_x rootarr_y rate_x rate_y]
			
					(if ( = (get-in mat [x (+ y 1)]) \@)
													(do(println "Woo hoo, I found the treasure :-)")


													(aset rootarr_x x (+ y 1) x)
  											(aset rootarr_y x (+ y 1) y)


													(stop_func mat rootarr_x rootarr_y rate_x rate_y height width)
							  
							      )
					)		

					(if ( = (get-in mat [x (- y 1)]) \@)
													(do(println "Woo hoo, I found the treasure :-)")


							      (aset rootarr_x x (- y 1) x)
  											(aset rootarr_y x (- y 1) y)

													(stop_func mat rootarr_x rootarr_y rate_x rate_y height width)
							   
							      )
					)

					(if ( = (get-in mat [(+ x 1) y]) \@)
													(do(println "Woo hoo, I found the treasure :-)")


							      (aset rootarr_x (+ x 1) y x)
  											(aset rootarr_y (+ x 1) y y)

													(stop_func mat rootarr_x rootarr_y rate_x rate_y height width)
							  
							      )
					)

					(if ( = (get-in mat [(- x 1) y]) \@)
													(do(println "Woo hoo, I found the treasure :-)")

							      (aset rootarr_x (- x 1) y x)
  											(aset rootarr_y (- x 1) y y)

													(stop_func mat rootarr_x rootarr_y rate_x rate_y height width)
							  
							      )
					)

				
					(if ( and (= (get-in mat [x (- y 1)]) \-) (and (< x height) (>= x 0) ) (and (< (- y 1) width) (>= (- y 1) 0) )
									)

							      (do

							      (aset rootarr_x x (- y 1) x)
  											(aset rootarr_y x (- y 1) y)

							      (aset mat x (- y 1) \!)
							      
							      (free_path mat x (- y 1) height width rootarr_x rootarr_y rate_x rate_y)

							      
							      )
					)		

					(if ( and (= (get-in mat [(- x 1) y]) \-) (and (< (- x 1) height) (>= (- x 1) 0) ) (and (< y width) (>= y 0) )
									)
							      (do

							      (aset rootarr_x (- x 1) y x)
  											(aset rootarr_y (- x 1) y y)

  											;(swap! milgya inc)
  											;(println @milgya)

							      (aset mat (- x 1) y \!)
							     ;	(println "UP")
							      
							      ;(def milgyafound 1)
							      (free_path mat (- x 1) y height width rootarr_x rootarr_y rate_x rate_y)

							      )
					)		


					(if ( and (= (get-in mat [x (+ y 1)]) \-) (and (< x height) (>= x 0) ) (and (< (+ y 1) width) (>= (+ y 1) 0) )
										)
							      (do			


  									  (aset rootarr_x x (+ y 1) x)
  											(aset rootarr_y x (+ y 1) y)

							     	(aset mat x (+ y 1) \!)

							      (free_path mat x (+ y 1) height width rootarr_x rootarr_y rate_x rate_y)
							      
							      )
					)	


					(if ( and (= (get-in mat [(+ x 1) y]) \-) (and (< (+ x 1) height) (>= (+ x 1) 0) ) (and (< y width) (>= y 0) )
									)
							      (do

							      (aset rootarr_x (+ x 1) y x)
  											(aset rootarr_y (+ x 1) y y)

  											;(swap! milgya inc)
  											;(println @milgya)

							      (aset mat (+ x 1) y \!)
							      ;(println "DOWN")
							     ;	(def milgyafound 1)
							      
							      (free_path mat (+ x 1) y height width rootarr_x rootarr_y rate_x rate_y)

							      
							      )
					)

					mat);; free_path

 (defn Start []
	(def mat (read_file))

	;;(println (get-in mat 7))
	(def height(count(subvec mat 0)))	
	;;(def check(count mat))
 (def width (count (nth mat 0)))

	(dotimes [n height]

				(if(not=(count (nth mat n)) width)
					(do
								(println "Error Not a Valid Map")
									(System/exit 0)
					)
  )

	)			

	(def mat_array(make-array Character/TYPE height width))

	(println "This is my challenge:")
	(println " ")


			(loop [x 0] 
				(when (< x height)
						(loop [y 0]
								(when (< y width)

												(if ( = (get-in mat [x y]) \-)
												(do
							      		(aset mat_array x y \-)
							      		(print "-")
							     )
							     )

							     (if ( = (get-in mat [x y]) \#)
							     (do
							      		(aset mat_array x y \#)
							      		(print "#")
							     )
							     )

							     (if ( = (get-in mat [x y]) \@)
							     (do

															(print "@")
							     			(def rate_x x)
							     			(def rate_y y)
							      		(aset mat_array x y \@)
							      		
							     ) 		
							     )
											
									(recur (+ y 1))
									);; when y
									);; loop y
									(println " ")

				(recur (+ x 1))
				);; when x
			);; loop x


(println " ")
 
(def rootarr_x(make-array Long/TYPE height width))
(def rootarr_y(make-array Long/TYPE height width))

;(def milgyafound 0)
(def mat(free_path mat_array 0 0 height width rootarr_x rootarr_y rate_x rate_y))

(println "Uh oh, I could not find the treasure :-(")
(println " ")

			(loop [x 0] 
				(when (< x height)
						(loop [y 0]
								(when (< y width)

												(if ( = (aget mat x y) \-)
												(do
							      		
							      		(print "-")
							     )
							     )

							     (if ( = (aget mat x y) \#)
							     (do
							      		
							      		(print "#")
							     )
							     )

								    (if ( = (aget mat x y) \!)
							     (do
							      		
							      		(print "!")
							     )
							     )						     

							     (if ( = (aget mat x y) \@)
							     (do

															(print "@")
							      		
							      		
							     ) 		
							     )
											
									(recur (+ y 1))
									);; when y
									);; loop y
									(println " ")

				(recur (+ x 1))
				);; when x
			);; loop x

)
(Start)





