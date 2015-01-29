
class Gossips(strs: String*) {

  val peaple = strs map(_ split " " last) toList

  def from(name:String) = ???    

}
