package org.datanucleus.test;

import java.util.*;
import org.junit.*;
import javax.jdo.*;

import static org.junit.Assert.*;
import mydomain.model.*;
import org.datanucleus.util.NucleusLogger;

public class SimpleTest
{
    @Test
    public void testSimple()
    {
        NucleusLogger.GENERAL.info(">> test START");
        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("MyTest");

        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try
        {
            tx.begin();

            Person p1 = new Person(1, "First");
            Address a = new House(1, "Home", "street");
            p1.setAddress(a);
            pm.makePersistent(p1);

            tx.commit();
        }
        catch (Throwable thr)
        {
            NucleusLogger.GENERAL.error(">> Exception in test", thr);
            fail("Failed test : " + thr.getMessage());
        }
        finally 
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
        pmf.getDataStoreCache().evictAll();

        pm = pmf.getPersistenceManager();
        tx = pm.currentTransaction();
        try
        {
//            tx.begin();

            NucleusLogger.GENERAL.info(">> Querying");
            Query q = pm.newQuery("SELECT FROM mydomain.model.Person");
            FetchPlan fp = q.getFetchPlan();
            fp.addGroup("Person.all");
            NucleusLogger.GENERAL.info(">> This should create an SQL selecting the FK column, but NOT joining across to the ADDRESS table.");
            NucleusLogger.GENERAL.info(">> It will instantiate the Address object, but not populate it, other than its identity.");
            List<Person> results = q.executeList();

            for (Person p : results)
            {
                NucleusLogger.GENERAL.info(">> p=" + p);
            }

//            tx.commit();
        }
        catch (Throwable thr)
        {
            NucleusLogger.GENERAL.error(">> Exception in test", thr);
            fail("Failed test : " + thr.getMessage());
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }

        pmf.close();
        NucleusLogger.GENERAL.info(">> test END");
    }
}
