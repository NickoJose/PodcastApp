# COMP 3350 - Group K
### Students: Michael Reimer, Nicko Jose, Gareth Wiebe, Jackson Barker, Russell Thiessen

All of the packages are found under java/comp3350/podcast.
Application package – Found under comp3350/podcast/application. It contains classes that initializes and terminates the stub database.
Business package – Found under comp3350/podcast/business. It contains the interface to the database which retrieves the information the presentation needs. It also has the sort class that sorts a list based on a particular type.
Objects package – Found under comp3350/podcast/objects. It contains all the domain-specific objects such as episodes and channels.
Persistence package – Found under comp3350/podcast/persistence. It contains the stub database that is implemented by using a non-persistent storage with static content.
Representation package – Found under comp3350/podcast/representation. It contains all GUIs activities currently used on the emulator.

Unit tests are found under java/comp3350/podcast (test)
AllTests – Found under comp3350/podcast (test)/tests. It a test suite that runs all of the unit tests
Business test package – Found under comp3350/podcast (test)/tests/business. It contains unit tests for the sort class.
Objects test package – Found under comp3350/podcast (test)/tests/objects. It contains unit tests for the objects implemented.

The log file can found under the name log.txt.

To run all the unit tests, select AllTests.java -> run.
To run the project, download the zip file -> file (android studio) -> new -> import project -> select SoftEngProject-master -> select build.gradle.

After successfully launching the project on the Nexus 7, a search bar, recommended episodes, playlists, and “create new playlist” button will show up on the screen. Selecting one of the episodes in the recommended list will display all the information regarding that particular episode. Selecting the “back to channel” on the episode gui will switch the screen to the channel gui which displays all the information about the channel of the currently selected episode. It will also display all the episodes the channel has.

