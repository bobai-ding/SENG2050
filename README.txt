SENG2050 - Web Engineering
Final Project
Simeon Pento - c3282938
William Paterson - c3280751
Lachlan McRae - c3283344

-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
Installation instructions:
-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

0. Make sure tomcat 8.5.38 is installed and %CLASSPATH% and %JAVA_HOME% are defined

1. First need to use SQL version 5.1.36
    - https://downloads.mysql.com/archives/community/

2. Make sure to click add to firewall and add to windows PATH (leave everything else as default)

3. Set the root password of the SQL to 'pass'
    - Make sure it is the root password being set (should ask for it during install)

4. Next, change the lines (starting line 144) in tomcat-install/conf/server.xml:

<Realm className="org.apache.catalina.realm.UserDatabaseRealm"
	               resourceName="UserDatabase"/>
</Realm>



To:


	
<Realm className="org.apache.catalina.realm.JDBCRealm"
	driverName="com.mysql.jdbc.Driver"
	connectionURL="jdbc:mysql://localhost:3306/Will_Simi_Lachlan"
	connectionName="root" 
	connectionPassword="pass"
	userTable="tomcat_users" 
	userNameCol="user_name" 
	userCredCol="password"
	userRoleTable="tomcat_users_roles" 
	roleNameCol="role_name" />
</Realm>

Note: it may be possible to just copy the server.xml file from c3282938_c3280751_c3283344_FinalProject/SETUP/BACKUP into tomcat-install/conf/
but this is not recommended as it has proven to not work consistently across different computers.

Note: in case a different root password was used during the setup of MySQL it can be changed using: connectionPassword="NEW_PASSWORD_HERE"


5. Cut and paste lib folder from c3282938_c3280751_c3283344_FinalProject/SETUP/  and paste in tomcat-install (to add necessary libraries to tomcat to enable SQL database) 

6. Open SQL command line, copy contents of createTables.sql (c3282938_c3280751_c3283344_FinalProject/SETUP/) into command line
    - Test this worked by going:
          USE will_simi_lachlan;
          SHOW TABLES;
		  

7. Compile Java files in c3282938_c3280751_c3283344_FinalProject/WEB_INF/classes/ass3Package/ using "javac *"

8. Run tomcat as normal (using startup.bat)

9. Open browser and access: http://localhost:8080/c3282938_c3280751_c3283344_FinalProject/

10. While testing application use the following accounts:

Username	|	Password	|
staff		|	staff		| //staff has staff role and has access to staff only features
user1		|	user1		|
user2		|	user2		|
user3		|	user3		|
user4		|	user4		|

-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
Completed Additional Requirements:
-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

 3.“Sometimes the language that IT staff use is complicated, maybe users should be able to add comments to the Knowledge-Base articles to help other users.” (weight 10)
 
	Users and staff have the ability to create comments on completed and resolved reports when they are in the knowledge base.
 
 14. “We should be able to view Knowledge-Base articles sorted by their categories” – User. (weight 5)
 
	Users and staff have the ability to sort reports by category (alphabetically)
 
 15. “We should be able to sort the issues by the date that they were reported” – IT staff. (weight 5)
 
	Users and staff have the ability to sort reports by date and time (oldest first)
 
 16. “It would be nice if we could have key-word search capabilities” – User. (weight 20)
 
	Users have the ability to search title and contents of their submitted reports.
	Users and staff have the ability to search title and contents of all reports in knowledge base.
 
 17. “Not all incidents will make it to the Knowledge-Base right away. We need a useful way to search over the old incidents that aren’t in the Knowledge-Base” – IT staff. (weight 20)
 
	Staff have the ability to search title and contents of all reports.
	
 Total: 60/30
 
-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
GitHub Link:
-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

https://github.com/Overlord360/SENG2050


-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
Documentation:
-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

all documentation is located in c3282938_c3280751_c3283344_FinalProject/documentation/NEW