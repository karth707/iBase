iBase
=====

To be able to run our project you will need a few things:
- A sql database (our group used Sequel Pro http://www.sequelpro.com/)
- An up-to-date version of mySQL http://dev.mysql.com/downloads/mysql/
- An up-to-date version of Apache Maven http://maven.apache.org/
- An up-to-date version of Apache Tomcat http://tomcat.apache.org/
- The source code. This can be downloaded from GitHub in a variety of ways. Clone the repo or download the zip.
- If you plan to edit the source code, you will also need a Java IDE (our group used both Eclipse and IntelliJ)

Begin by downloading the Maven, Tomcat, mySQL, and Sequel Pro to your computer. Follow the tutorials on the respective sites to get them working on your local machine.

Then you can start downloading the source code from GitHub by simply clicking "Download Zip" in the bottom right corner of our project page.

Make sure to unzip the project and run the queries listed in the dbQueries file in Sequel Pro's query GUI.

Now that the source code is unzipped and your database is ready to store login information, you can change directories until you're in the project and run the command 'mvn clean package' from the terminal.

This will generate a .war file in the target directory of the project. This is the file you will need to upload to tomcat's manager (http://localhost:8080/manager/html) in order to display the content.

Once uploaded, you simply click on the name of the file and it will run in your from your local server giving a URL that looks like: http://localhost:8080/iBase/login .

That should allow you to run the program. 

If you'd like to make changes, you would simply open the project directory in the IDE of your choice, configure it for spring, maven, and tomcat, and you'll be able to make your edits in no time!


