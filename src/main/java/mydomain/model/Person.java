package mydomain.model;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.FetchGroup;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable="true")
@FetchGroup(name="Person.all", members={@Persistent(name="id"), @Persistent(name="name"), @Persistent(name="address")})
public class Person
{
    @PrimaryKey
    Long id;

    String name;

    @Extension(vendorName="datanucleus", key="fetch-fk-only", value="true")
    Address address;

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

    public Address getAddress()
    {
        return address;
    }
    public void setAddress(Address addr)
    {
        this.address = addr;
    }

    public String toString()
    {
        return "Person [" + id + "] address=" + address;
    }
}
