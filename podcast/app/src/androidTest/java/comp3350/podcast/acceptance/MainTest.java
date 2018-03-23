package comp3350.podcast.acceptance;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import junit.framework.Assert;

import comp3350.podcast.presentation.MainActivity;

public class MainTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;

	public MainTest()
	{
		super(MainActivity.class);
	}

	public void setUp() throws Exception
	{
		solo = new Solo(getInstrumentation(), getActivity());
		
		// Disable this for full acceptance test
		// System.out.println("Injecting stub database.");
		// Services.createDataAccess(new DataAccessStub(Main.dbName));
	}
	
	@Override
	public void tearDown() throws Exception
	{
		solo.finishOpenedActivities();
	}
	
	// Please note again that this is not a complete set of acceptance tests


}
