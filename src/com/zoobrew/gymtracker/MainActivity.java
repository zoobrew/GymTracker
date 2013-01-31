package com.zoobrew.gymtracker;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends FragmentActivity implements
		ActionBar.OnNavigationListener {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current dropdown position.
	 */
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	
	boolean mExternalStorageAvailable = false;
	boolean mExternalStorageWriteable = false;
	String state;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar to show a dropdown list.
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		// Set up the dropdown list navigation in the action bar.
		actionBar.setListNavigationCallbacks(
		// Specify a SpinnerAdapter to populate the dropdown list.
				new ArrayAdapter<String>(actionBar.getThemedContext(),
						android.R.layout.simple_list_item_1,
						android.R.id.text1, new String[] {
								getString(R.string.title_section1),
								getString(R.string.title_section2),
								getString(R.string.title_section3), 
								getString(R.string.title_section4),
								getString(R.string.title_section5)}), this);
		
		
		state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
		    // We can read and write the media
		    mExternalStorageAvailable = mExternalStorageWriteable = true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
		    // We can only read the media
		    mExternalStorageAvailable = true;
		    mExternalStorageWriteable = false;
		} else {
		    // Something else is wrong. It may be one of many other states, but all we need
		    //  to know is we can neither read nor write
		    mExternalStorageAvailable = mExternalStorageWriteable = false;
		}
	}
	

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current dropdown position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current dropdown position.
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
				.getSelectedNavigationIndex());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onNavigationItemSelected(int position, long itemId) {
		// Create new fragment from our own Fragment class
		ListFragment newFragment = new ExerciseSelectionFragment();
		Bundle args = new Bundle();
		args.putInt(ExerciseSelectionFragment.ARG_SECTION_NUMBER, position + 1);
		newFragment.setArguments(args);
		FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
		// Replace whatever is in the fragment_container view with this fragment,
		// and add the transaction to the back stack
		fragTransaction.replace(R.id.container, newFragment);
		fragTransaction.addToBackStack(null);
		// Apply changes
		fragTransaction.commit();
		
		return true;
	}

	/**
	 * A fragment representing a selection of day's sets.
	 */
	public static class ExerciseSelectionFragment extends ListFragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";
		public static final String ARG_EXCERCISE_NUMBER = "exercise_number";

		public ExerciseSelectionFragment() {
		}
		
		 @Override
	    public void onActivityCreated(Bundle savedInstanceState) {
	        super.onActivityCreated(savedInstanceState);
	    }
		
	    @Override
	    public void onAttach(Activity activity) {
	    	super.onAttach(activity);
		    int number = getArguments().getInt(ARG_SECTION_NUMBER);
		    Resources res = getResources();
		    String Menu[];
		    switch (number){
		    	case 1:
		    		Menu = res.getStringArray(R.array.menu_section1);
		    		break;
		    	case 2:
		    		Menu = res.getStringArray(R.array.menu_section2);
		    		break;
		    	case 3:
		    		Menu = res.getStringArray(R.array.menu_section3);
		    		break;
		    	case 4:
		    		Menu = res.getStringArray(R.array.menu_section4);
		    		break;
		    	case 5:
		    		Menu = res.getStringArray(R.array.menu_section5);
		    		break;
		    	default:
		    		Menu = res.getStringArray(R.array.menu_section1);
		    		break;
		    }
		    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
		        android.R.layout.simple_list_item_1, Menu);
		    setListAdapter(adapter);
	  }
		  
		/*@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
		}
		*/

		  @Override
		  public void onListItemClick(ListView l, View v, int position, long id) {
			  // Do something with the data
			  Intent intent = new Intent(getActivity(), ExerciseDetailActivity.class);
			  intent.putExtra(ARG_EXCERCISE_NUMBER, position);
			  int number = getArguments().getInt(ARG_SECTION_NUMBER);
			  intent.putExtra(ARG_SECTION_NUMBER, number);
			  startActivity(intent);

		  }
	}

}
