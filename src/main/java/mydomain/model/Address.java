package mydomain.model;

import javax.jdo.annotations.FetchGroup;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable="true")
@FetchGroup(name="Address.all", members={@Persistent(name="id"), @Persistent(name="name")})
@FetchGroup(name="Address.pk", members={@Persistent(name="id")})
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class Address
{
    @PrimaryKey
    Long id;

    String name;

    public Address(long id, String name)
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
