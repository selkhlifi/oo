signature SHAPES =
sig
    val area : string * string -> int
    val perimeter : string * string -> int
    exception NoSuchShapeException
end

structure Shapes :> SHAPES =
		    struct
		    
		    datatype shape = Square of int
				   | Rectangle of int * int
				   | Triangle of int * int
							   
		    exception NoSuchShapeException


		    fun create_shape (name, dims) =
			let
			    val dims_arr_of_str = String.tokens (fn c => c = #",") dims
			    val dims_arr_of_ints = map (valOf o Int.fromString) dims_arr_of_str
			in
			    case name of
				"SQUARE" => Square(hd dims_arr_of_ints)
			      | "RECTANGLE" => let val l::w::_ = dims_arr_of_ints
					       in Rectangle(l, w)
					       end
			      | "TRIANGLE" => let val l::b::_ = dims_arr_of_ints
					      in Triangle (l, b)
					      end
			      | _ => raise NoSuchShapeException
			end

		    fun calculate_area shape =
			case shape of
			    Square (l) => l*l
			  | Rectangle (l, w) => l*w
			  | Triangle (l, b) => (l*b) div 2

		    fun calculate_perimeter shape =
			case shape of
			    Square (l) => 4*l
			  | Rectangle (l, w) => 2*(l+w)
			  | Triangle (l, b) => 3*b

		    val area  = calculate_area o create_shape

		    val perimeter  = calculate_perimeter o create_shape
							       
		    end
