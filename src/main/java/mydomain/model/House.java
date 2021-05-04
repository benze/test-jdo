package mydomain.model;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable="true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class House extends Address{

    private String street;

    public House(long id, String name, String street) {
        super(id, name);
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
