# COMP 3350 - Group K
### Students: Michael Reimer, Nicko Jose, Gareth Wiebe, Jackson Barker, Russell Thiessen

All of the packages are found under java/comp3350/podcast.
Application package – Found under comp3350/podcast/application. It contains classes that initialize and terminate a database. It also contains a function that allows switching between a real and stub databases.
Business package – Found under comp3350/podcast/business. It contains the interface to the database which retrieves the information the presentation needs. It also has the sort class that sorts a list based on a particular type and a search class that returns the most similar values/objects to the entered key. 
Objects package – Found under comp3350/podcast/objects. It contains all the domain-specific objects such as episodes and channels.
Persistence package – Found under comp3350/podcast/persistence. It contains the real database (HSQLDB) and a common interface for the real and stub databases
Presentation package – Found under comp3350/podcast/presentation. It contains all GUIs activities currently used on the emulator.
Script package - Found under app/assets/db/Podcast.script (app) and under database/Podcast.script (local) before app. It contains a script for the database. A copy of the untouched database is found under app/database/Podcast.script

Unit tests are found under java/comp3350/podcast(test)
Business test package – Found under comp3350/podcast(test)/business. It contains unit tests for the sort, search and access classes.
Objects test package – Found under comp3350/podcast(test)/objects. It contains unit tests for the objects implemented.
Persistence test package - Found under comp3350/podcast(test)/persistence. It contains the stub database and unit test for the common database interface.
Integration test package - Found under comp3350/podcast(test)/integration. It contains integration tests across the seam and for the external resource.
RunUnitTests.java – Found under comp3350/podcast(test). It a test suite that runs all of the unit tests
RunIntegrationTests.java - Found under comp3350/podcast(test). It a test suite that runs all of the integration tests

The log file can found under the name log.txt.

To run all the unit tests, select RunUnitTests.java -> run.
To run all the integration tests, select RunIntegrationTests.java -> run.

To run the project, download the zip file -> file (android studio) -> new -> import project -> select SoftEngProject-master -> select podcast -> select build.gradle.

Please note that while android studio labels most methods in the Access classes as "never used", this turned out to not be the case once
the methods were deleted, and they could not easily be replaced with other functions, so we left them in.

We added a function inside the services.class that allows switching between the real and the stub database dynamically if needed (currently not being used). The system is currently using the real database as the default database. Running the system for the first time will copy the database on the device. After successfully launching the project on the Nexus 7, a menu containing: a search bar, recommended episodes, playlists, and a few button will show up on the screen. Clicking the appropriate button will display a list of either episodes or channels below the menu. Selecting one of the episodes in either the recommended list at the bottom of the page, or from the list of all episodes below the menu bar, will display all the information regarding that particular episode. After selecting a channel from the main menu the Channel gui will appear and display all the information about the currently selected channel and a scrollable list of all the episodes the channel has.
For iteration two, we also added the ability to search for podcasts by their title. The search looks for similar matches between titles and search queries, this allows the user to get results without necessarily using correct spelling. We also added a simulation of a video player.

On the main activity, channels in the lists can be long clicked to pull up a popup menu that allows for the channel to be subscribed / unsubscribed to.

