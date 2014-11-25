iBase
=====

To be able to run our project you will need a few things:
- A sql database (our group used Sequel Pro http://www.sequelpro.com/)
- An up-to-date version of mySQL http://dev.mysql.com/downloads/mysql/
- An up-to-date version of Apache Maven http://maven.apache.org/
- An up-to-date version of Apache Tomcat http://tomcat.apache.org/
- The source code. This can be downloaded from GitHub in a variety of ways. Clone the repo or download the zip.
- If you plan to edit the source code, you will also need a Java IDE (our group used both Eclipse and IntelliJ)

WINDOWS:

1) Download Maven 
- Unzip the distribution archive, i.e. apache-maven-3.2.3-bin.zip to the directory you wish to install Maven 3.2.3. These        instructions assume you chose C:\Program Files\Apache Software Foundation. The subdirectory apache-maven-3.2.3 will be         created from the archive.
- Add the M2_HOME environment variable by opening up the system properties (WinKey + Pause), selecting the "Advanced" tab, and   the "Environment Variables" button, then adding the M2_HOME variable in the user variables with the value C:\Program           Files\Apache Software Foundation\apache-maven-3.2.3. Be sure to omit any quotation marks around the path even if it contains   spaces. Note: For Maven 2.0.9, also be sure that the M2_HOME doesn't have a '\' as last character.
- In the same dialog, add the M2 environment variable in the user variables with the value %M2_HOME%\bin.
- Optional: In the same dialog, add the MAVEN_OPTS environment variable in the user variables to specify JVM properties, e.g.    the value -Xms256m -Xmx512m. This environment variable can be used to supply extra options to Maven.
- In the same dialog, update/create the Path environment variable in the user variables and prepend the value %M2% to add Maven   available in the command line.
-	In the same dialog, make sure that JAVA_HOME exists in your user variables or in the system variables and it is set to the     location of your JDK, e.g. C:\Program Files\Java\jdk1.7.0_51 and that %JAVA_HOME%\bin is in your Path environment variable.
- Open a new command prompt (Winkey + R then type cmd) and run mvn --version to verify that it is correctly installed.

2)	Download Tomat
-	Goto http://tomcat.apache.org ⇒ Downloads ⇒ Tomcat 8.0 ⇒ "8.0.{xx}" (where {xx} is the latest upgrade number) ⇒ Binary         Distributions ⇒ Core ⇒ "zip" package (e.g., "apache-tomcat-8.0.{xx}.zip", about 8 MB).
-	UNZIP into a directory of your choice. DO NOT unzip onto the Desktop (because its path is hard to locate). I suggest using     "d:\myProject". Tomcat will be unzipped into directory "d:\myProject\apache-tomcat-8.0.{xx}". For ease of use, we shall        shorten and rename this directory to "d:\myProject\tomcat". Take note of Your Tomcat Installed Directory. Hereafter, I shall   refer to the Tomcat installed directory as <TOMCAT_HOME> (or <CATALINA_HOME> - "Catalina" is the codename for Tomcat 5 and     above).

3)	Download MySQL Installer 

4)	Download Sequel Pro

5)	Then you can start downloading the source code from GitHub by simply clicking "Download Zip" in the bottom right corner of our project page.

6)	Make sure to unzip the project and run the queries listed in the dbQueries file in Sequel Pro's query GUI.

7)	Now that the source code is unzipped and your database is ready to store login information, you can change directories until you're in the project and run the command 'mvn clean package' from the terminal.

8)	This will generate a .war file in the target directory of the project. This is the file you will need to upload to tomcat's manager (http://localhost:8080/manager/html) in order to display the content.

9)	Once uploaded, you simply click on the name of the file and it will run in your from your local server giving a URL that looks like: http://localhost:8080/iBase/login. Thus allowing you to run the program :)

10)	Download/Install Selenium
- Open Mozilla Firefox Browser.
-	Type URL : http://seleniumhq.org/download/ in your browser.Selenium IDE download page will get open then click on              latestDownload version link (Here is 1.3.0) as shown in image bellow. click on that link.
-	When you click on latest Selenium IDE Download version link, Firefox will show one popup saying do you want to allow Mozilla   Firefox to install selenium IDE or not. Click on Allow button as shown in image bellow.
-	When you click on allow button, Firefox will automatically install Selenium IDE software. after completion of Selenium IDE     installation, it will show one pop up saying Selenium IDE installation completed. you need to restart your browser to use      selenium IDE. Click on Restart Now button as show in image bellow.
-	When you click on allow button, Firefox will automatically install Selenium IDE software. after completion of Selenium IDE     installation, it will show one pop up saying Selenium IDE installation completed. you need to restart your browser to use      selenium IDE. Click on Restart Now button as show in image bellow.
-	Click on Selenium IDE as shown in image above. It will launch Selenium IDE software window as shown in image bellow. 
- Once you have the IDE open, you can click on File > Open... or File > Open Test Suite... to open the tests.
- Once the tests are loaded into the IDE you can then click the Play Entire Test Suite to run through all the tests.
- The tests will turn green or red depending on whether they pass or fail. **See NOTE 1**
- The functional tests will also be run when you build the project. **See NOTE 2**

MACINTOSH:

1)Download Maven 
-	Extract the distribution archive, i.e. apache-maven-3.2.3-bin.tar.gz to the directory you wish to install Maven 3.2.3. These   instructions assume you chose/usr/local/apache-maven. The subdirectory apache-maven-3.2.3 will be created from the archive.
-	In a command terminal, add the M2_HOME environment variable, e.g. export M2_HOME=/usr/local/apache-maven/apache-maven-3.2.3.
-	Add the M2 environment variable, e.g. export M2=$M2_HOME/bin.
-	Optional: Add the MAVEN_OPTS environment variable to specify JVM properties, e.g. export MAVEN_OPTS="-Xms256m -Xmx512m". This   environment variable can be used to supply extra options to Maven.
-	Add M2 environment variable to your path, e.g. export PATH=$M2:$PATH.
-	Make sure that JAVA_HOME is set to the location of your JDK, e.g. export JAVA_HOME=/usr/java/jdk1.7.0_51 and that              $JAVA_HOME/bin is in your PATH environment variable.
-	Run mvn --version to verify that it is correctly installed.

2)	Download Tomat
-	Download a binary distribution of the core module: apache-tomcat-7.0.47.tar.gz from here. I picked the tar.gz in Binary        Distributions / Core section.
-	Opening/unarchiving the archive will create a folder structure in your Downloads folder: (btw, this free Unarchiver app is     perfect for all kinds of compressed files and superior to the built-in Archive Utility.app)
  ~/Downloads/apache-tomcat-7.0.47
-	Open to Terminal app to move the unarchived distribution to /usr/local:
  sudo mkdir -p /usr/local
  sudo mv ~/Downloads/apache-tomcat-7.0.47 /usr/local
-	To make it easy to replace this release with future releases, we are going to create a symbolic link that we are going to use   when referring to Tomcat (after removing the old link, you might have from installing a previous version):
  sudo rm -f /Library/Tomcat
  sudo ln -s /usr/local/apache-tomcat-7.0.47 /Library/Tomcat
-	Change ownership of the /Library/Tomcat folder hierarchy:
  sudo chown -R <your_username> /Library/Tomcat
-	Make all scripts executable:
  sudo chmod +x /Library/Tomcat/bin/*.sh

3)	Download MySQL Installer 

4)	Download Sequel Pro

5)	Then you can start downloading the source code from GitHub by simply clicking "Download Zip" in the bottom right corner of our project page.

6)	Make sure to unzip the project and run the queries listed in the dbQueries file in Sequel Pro's query GUI.

7)	Now that the source code is unzipped and your database is ready to store login information, you can change directories until you're in the project and run the command 'mvn clean package' from the terminal.

8)	This will generate a .war file in the target directory of the project. This is the file you will need to upload to tomcat's manager (http://localhost:8080/manager/html) in order to display the content.

9)	Once uploaded, you simply click on the name of the file and it will run in your from your local server giving a URL that looks like: http://localhost:8080/iBase/login. Thus allowing you to run the program :)

10)	Download/Install Selenium
- Open Mozilla Firefox Browser.
-	Type URL : http://seleniumhq.org/download/ in your browser.Selenium IDE download page will get open then click on              latestDownload version link (Here is 1.3.0) as shown in image bellow. click on that link.
-	When you click on latest Selenium IDE Download version link, Firefox will show one popup saying do you want to allow Mozilla   Firefox to install selenium IDE or not. Click on Allow button as shown in image below.
-	When you click on allow button, Firefox will automatically install Selenium IDE software. after completion of Selenium IDE     installation, it will show one pop up saying Selenium IDE installation completed. you need to restart your browser to use      selenium IDE. Click on Restart Now button as show in image bellow.
-	When you click on allow button, Firefox will automatically install Selenium IDE software. after completion of Selenium IDE     installation, it will show one pop up saying Selenium IDE installation completed. you need to restart your browser to use      selenium IDE. Click on Restart Now button as show in image bellow.
-	Click on Selenium IDE as shown in image above. It will launch Selenium IDE software window as shown in image below. 
-	Once you have the IDE open, you can click on File > Open... or File > Open Test Suite... to open the tests.
-	Once the tests are loaded into the IDE you can then click the Play Entire Test Suite to run through all the tests.
-	The tests will turn green or red depending on whether they pass or fail. **See NOTE 1**
-	The functional tests will also be run when you build the project. **See NOTE 2**

Note 1: Our functional tests for the UploadPassedTest and SettingsPassedTest involve us uploading an image from our local harddrives. Since the test will go to the location to check for an image and try uploading it, you will have to change the location to a .jpg file on your local machine. 

Note 2: An even more important note: The functional tests are set to run when you build from the command line using Maven, so you should see the tests running prior to creating the .war file to upload to Tomcat. 

**If you'd like to make changes, you would simply open the project directory in the IDE of your choice, configure it for spring, maven, and tomcat, and you'll be able to make your edits in no time!**

**Here are the test cases running on a terminal: https://www.youtube.com/watch?v=UkckRuDRIU8**

**And here are the test cases running via Selenium IDE:https://www.youtube.com/watch?v=YyKnp7Op6nM**









