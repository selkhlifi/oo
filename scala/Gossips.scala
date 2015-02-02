

class Gossips() {

  var peaple: Peaple = null


  def this(str1:String, str2:String, str3:String) {
    this()
    this.peaple = new Peaple(this, create(Array(str1, str2, str3): _*))
  }

  def this(peaple: Peaple) {
    this()
    this.peaple = peaple
  }



  def create(strs: String*): List[Person] = {
    def createPerson(arr: Array[String]) =
      new SomeBody(arr head, arr last)

    strs map(s => createPerson(s split " ")) toList
  }


  def from(name: String) = peaple from name

  override def toString = peaple.toString


}


class Peaple(val gossips: Gossips, val peaple: List[Person]) {

  def getBy(name: String): Person = {
    peaple.find(_.name.equals(name)).getOrElse(NoBody)
  }


  def from(name: String) : PersonBuilder = {
    new PersonBuilder(gossips, getBy(name))
  }

  def add(person: Person) : Peaple = {
   val id = person.name
    new Peaple(gossips, peaple.map {case SomeBody(_, id, _, _) => person; case x => x})

  }

  override def toString = peaple.toString

}

class PersonBuilder(val gossips: Gossips, val person: Person) {
  def to(name: String): Gossips = {
    val p = person.to(gossips.peaple.getBy(name))
    gossips.peaple = gossips.peaple.add(p)
    gossips
  }
    
}

trait Person {
  def to(name: Person): Person
  def name: String
}

case class SomeBody(
  val civility: String,
  val name: String,
  val info: String,
  val next: Option[Person])  extends Person {

  def this(civility: String, name: String) {
    this(civility, name, "", None)
  }

  def to(next: Person) =
    new SomeBody(civility, name, info, Some(next))

  override def toString = s"($civility, $name, $info, $next)"

}

case object  NoBody extends Person {

  def to(next: Person): Person=
    throw new Error("NoBody.next !")

  def name = throw new Error("NoBody.name")
}


