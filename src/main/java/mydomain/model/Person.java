package mydomain.model;

import javax.jdo.annotations.*;

import static javax.jdo.annotations.SequenceStrategy.NONTRANSACTIONAL;

@PersistenceCapable(detachable="true")
@DatastoreIdentity(strategy= IdGeneratorStrategy.SEQUENCE, sequence="jdoid_seq", column="ID")
@Version(strategy=VersionStrategy.VERSION_NUMBER, column="VERSN")
@Sequence(name="jdoid_seq", datastoreSequence="JDOID_SEQUENCE", strategy = NONTRANSACTIONAL, allocationSize=1)
@Discriminator(strategy=DiscriminatorStrategy.CLASS_NAME, column="TYP", indexed="true")
public class Person
{
    String name;

    @Column(name = "buddy")
    Friend friend;


    public Person(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }
}
