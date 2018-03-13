# COMP 3350 - Group K
### Students: Michael Reimer, Nicko Jose, Gareth Wiebe, Jackson Barker, Russell Thiessen

All of the packages are found under java/comp3350/podcast.
Application package – Found under comp3350/podcast/application. It contains classes that initialize and terminate a database. It also contains a function that allows switching between a real and stub databases.
Business package – Found under comp3350/podcast/business. It contains the interface to the database which retrieves the information the presentation needs. It also has the sort class that sorts a list based on a particular type and a search class that returns the most similar values/objects to the entered key. 
Objects package – Found under comp3350/podcast/objects. It contains all the domain-specific objects such as episodes and channels.
Persistence package – Found under comp3350/podcast/persistence. It contains the real database (HSQLDB) and a common interface for the real and stub databases
Presentation package – Found under comp3350/podcast/presentation. It contains all GUIs activities currently used on the emulator.
Script package - Found under app/assets/db/Podcast.script and under database/Podcast.script (before app). It contains a script for the database.

Unit tests are found under java/comp3350/podcast (test)
AllTests – Found under comp3350/podcast (test)/tests. It a test suite that runs all of the unit tests
Business test package – Found under comp3350/podcast (test)/tests/business. It contains unit tests for the sort, search and access classes.
Objects test package – Found under comp3350/podcast (test)/tests/objects. It contains unit tests for the objects implemented.
Persistence test package - Found under comp3350/podcast (test)/tests/persistence. It contains the stub database and unit test for the common database interface.

The log file can found under the name log.txt.

To run all the unit tests, select AllTests.java -> run.
To run the project, download the zip file -> file (android studio) -> new -> import project -> select SoftEngProject-master -> select build.gradle.

After successfully launching the project on the Nexus 7, a search bar, recommended episodes, playlists, and “create new playlist” button will show up on the screen. Selecting one of the episodes in the recommended list will display all the information regarding that particular episode. Selecting the “back to channel” on the episode gui will switch the screen to the channel gui which displays all the information about the channel of the currently selected episode. It will also display all the episodes the channel has.

