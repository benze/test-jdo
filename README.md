test-jdo
========

Template project for any user testcase using JDO.

Shows the problem with using native MS SQL Sequences. 
Defines a sequence for a datastore-identity, and tries to persist a single entity.  

Fails with error message indicating that the sequence name is invalid because it is enclosed in quotes.



To run this, simply type "mvn clean compile test"
