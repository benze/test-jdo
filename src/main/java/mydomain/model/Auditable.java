package mydomain.model;

import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Version;
import javax.jdo.annotations.VersionStrategy;

@PersistenceCapable
@Inheritance(strategy= InheritanceStrategy.SUBCLASS_TABLE)
@DatastoreIdentity(strategy= IdGeneratorStrategy.SEQUENCE, sequence="jdoid_seq", column="ID")
@Version(strategy= VersionStrategy.VERSION_NUMBER, column="VERSN")
public abstract class Auditable {

    private long createdAt;

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}

