test-jdo
========

Template project for any user testcase using JDO.
To create a DataNucleus test simply fork this project, and add/edit as 
necessary to add your model and persistence commands. The files that you'll likely need to edit are

* <a href="https://github.com/datanucleus/test-jdo/tree/master/src/main/java/mydomain/model">src/main/java/mydomain/model/</a>   **[Put your model classes here]**
* <a href="https://github.com/datanucleus/test-jdo/blob/master/src/main/resources/META-INF/persistence.xml">src/main/resources/META-INF/persistence.xml</a>   **[Put your datastore details in here]**
* <a href="https://github.com/datanucleus/test-jdo/blob/master/src/test/java/org/datanucleus/test/SimpleTest.java">src/test/java/org/datanucleus/test/SimpleTest.java</a>   **[Edit this if a single-thread test is required]**
* <a href="https://github.com/datanucleus/test-jdo/blob/master/src/test/java/org/datanucleus/test/MultithreadTest.java">src/test/java/org/datanucleus/test/MultithreadTest.java</a>   **[Edit this if a multi-thread test is required]**

To run this, simply type "mvn clean compile test"


---

Sample to demonstrate a problem with Abstract class representing N-1 relation with implicit transactions.  This causes infinite loop StackOverflows for some reason.
Similarly, the "fetch-fk-only" does not seem to make any difference.

Using implicit transactions (ie: no defined `tx.begin()` or `tx.commit()`) causes an infinite loop when DN attempts to load the `Person` object. 