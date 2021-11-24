package org.datanucleus.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CompletableFuture;

import org.h2.tools.Server;
import org.junit.*;
import javax.jdo.*;

import static org.junit.Assert.*;
import mydomain.model.*;
import org.datanucleus.util.NucleusLogger;

public class SimpleTest
{

    Server h2Server;

    @Before
    public void setupH2(){
            try {
                h2Server = Server.createWebServer();
                if (!h2Server.isRunning(false)) {
                    h2Server.start();
                }
            } catch (SQLException throwables) {
                NucleusLogger.GENERAL.error("Unable to start the H2 WebServer");
                NucleusLogger.GENERAL.debug(throwables);
            }

        NucleusLogger.GENERAL.info("H2 Web Status: " + h2Server.getStatus());
    }

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
            Connection connection = ((Connection)pm.getDataStoreConnection());

            // use Int as ID
            createData(connection, Integer.MAX_VALUE, "EricInt");

            // use Long as ID
            createData(connection, 17950196510000L, "EricLong");

            connection.close();
            tx.commit();
        }
        catch (Throwable thr)
        {
            NucleusLogger.GENERAL.error(">> Exception in test", thr);
            thr.printStackTrace();
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
            tx.begin();


//            Person person = pm.newQuery(Person.class).filter("name == 'EricInt'").executeUnique();
//
//            Assert.assertEquals("Found correct object", person.getName(), "EricInt");
//            Assert.assertEquals("Found correct object", person.getFriend().getName(), "EricInt_Neighbour");

            Person person = pm.newQuery(Person.class).filter("name == 'EricLong'").executeUnique();
            Assert.assertEquals("Found correct object", person.getName(), "EricLong");
            Assert.assertEquals("Found correct object", person.getFriend().getName(), "EricLong_Neighbour");

            tx.commit();
        }
        catch (Throwable thr)
        {
            NucleusLogger.GENERAL.error(">> Exception in test", thr);
            thr.printStackTrace();
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

        NucleusLogger.GENERAL.info(">> test END");
    }


    private void createData(Connection con, long id, String name) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT into Neighbour (id, name, car, VERSN, TYP, CREATEDAT ) values( ?, ?, 'green', 1,'mydomain.model.Neighbour', 1637698763)");
        ps.setLong(1, id);
        ps.setString(2, name + "_Neighbour");
        ps.execute();

        ps = con.prepareStatement("INSERT into Person (id, name, buddy, VERSN, TYP ) values( ?, ?, ?, 1, 'mydomain.model.Person')");
        ps.setLong(1, id);
        ps.setLong(3, id);
        ps.setString(2, name);
        ps.execute();

    }
}
