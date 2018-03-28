Integration Tests

	First integration test is focused on testing each seam in our project. In our case, this will be the interaction between business component and the persistence component. It is composed of a sequence of calls which also includes error and invalid situations.
	Second integration test is focused on testing the external resource. In our case, this will be the interaction between persistence component and the “real” database. It has the same kind of tests as the first integration test.

Acceptance Tests

	MainTest focuses on testing the use of the list on the main screen, and navigating from a channel, to one of its episodes, to playing it.
	PlaylistTest focuses on creating a playlist and adding episodes to it and checking the episodes are in fact, in the playlist.
	SubscriptionTest focuses on subscribing and unsubscribing to channels through both the main screen GUI and the channel GUI.
	SearchTest focuses on a users ability to search for an episode, view its page, and play it.
