package mydomain.model;

import javax.jdo.annotations.*;

import static javax.jdo.annotations.SequenceStrategy.NONTRANSACTIONAL;

@PersistenceCapable(detachable="true")
@DatastoreIdentity(strategy= IdGeneratorStrategy.SEQUENCE, sequence="toto" )
@Sequence(name="toto", datastoreSequence = "jdo_sequence", strategy=NONTRANSACTIONAL)
public class Person
{
    Long id;

    String name;

    public Person(long id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public Long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }
}
