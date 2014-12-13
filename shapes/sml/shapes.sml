signature SHAPES =
sig
    val area : string -> string -> int
    exception ShapeNotSupported
end

structure Shapes :> SHAPES =
		    struct
		    datatype shape = Square of int
				   | Rectangle of int * int
				   | Triangle of int * int
							   
		    exception ShapeNotSupported

		    fun calculate_area shape =
			case shape of
			    Square (l) => l*l
			  | Rectangle (l, w) => l*w
			  | Triangle (l, b) => (l*b) div 2
							     
		    fun create_shape name dims =
			let
			    val tokens = map (fn s => valOf (Int.fromString s)) (String.tokens (fn c => c = #",") dims)
			in
			    case name of
				"SQUARE" => Square(hd tokens)
			      | "RECTANGLE" => let val l = hd tokens
						   val w = hd (tl tokens)
					       in Rectangle(l, w)
					       end
			      | "TRIANGLE" => let val l = hd tokens
						  val b = hd (tl tokens)
					      in Triangle (l, b)
					      end
			      | _ => raise ShapeNotSupported
			end
		    fun area name dims =
			calculate_area (create_shape name dims)
				       
		    end
