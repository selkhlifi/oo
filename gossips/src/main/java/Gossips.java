import java.util.*;

public class Gossips {
    
    private Peaple peaple;
   
    public Gossips(String... strs) {
	this.peaple = new Peaple(strs);
    }
    
    public Binder from(String name) {
	return this.new Binder(name);
    }

    public class Binder {
        private String from;
	
	Binder(String from) {
	    this.from = from;
	}

	public Gossips to(String to) {
	    peaple.bind(from, to);
	    return Gossips.this;
	}
    }
}

class Peaple {
    
    List<Person> peaple = new ArrayList<>();

    Peaple(String... strs) {
	for(String str : strs) {
	    String[] splitted = str.split(" ");
	    String civility = splitted[0];
	    String name = splitted[1];
	    peaple.add(new Person(civility, name));
	}
    }

    void bind(String from, String to) {
        byName(from).setNext(byName(to));
    }

    private Person byName(String name) {
	for(Person person : peaple) {
	    if(person.hasName(name))
		return person;
	}
	
	throw new RuntimeException("Person of name " + name + " does not exist");
    }

    static class Person {
	private String civility;
	private String name;
	private Person next;


	Person(String civility, String name) {
	    this.civility = civility;
	    this.name = name;
	}

	void setNext(Person next) {
	    this.next = next;
	}

	boolean hasName(String name) {
	    return this.name.equals(name);
	}

    
    }

}


