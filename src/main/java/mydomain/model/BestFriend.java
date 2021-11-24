package mydomain.model;


import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Version;
import javax.jdo.annotations.VersionStrategy;

@PersistenceCapable(table = "BESTFRIEND")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@Discriminator(strategy = DiscriminatorStrategy.CLASS_NAME, column = "TYP", indexed = "true")
@Version(strategy = VersionStrategy.VERSION_NUMBER, column = "VERSN")
public class BestFriend extends Friend {
    public BestFriend(String name) {
        super(name);
    }
}
