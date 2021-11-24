package mydomain.model;

import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Sequence;
import javax.jdo.annotations.Version;
import javax.jdo.annotations.VersionStrategy;

import static javax.jdo.annotations.SequenceStrategy.NONTRANSACTIONAL;

@PersistenceCapable
@Inheritance(strategy= InheritanceStrategy.SUBCLASS_TABLE)
@Version(strategy=VersionStrategy.VERSION_NUMBER, column="VERSN")
abstract public class Friend extends Auditable{

    private String name;

    public Friend(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
