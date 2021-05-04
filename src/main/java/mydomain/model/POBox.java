package mydomain.model;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable="true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class POBox extends Address {

    String boxNb;

    public POBox(long id, String name, String boxNb) {
        super(id, name);
        this.boxNb = boxNb;
    }

    public String getBoxNb() {
        return boxNb;
    }

    public void setBoxNb(String boxNb) {
        this.boxNb = boxNb;
    }
}
