package com.zoobrew.gymtracker;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.res.Resources;
import android.os.Bundle;
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
		ListFragment newFragment = new DummySectionFragment();
		Bundle args = new Bundle();
		args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
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
	/*@Override
	public boolean onNavigationItemSelected(int position, long id) {
		// When the given dropdown item is selected, show its contents in the
		// container view.
		ListFragment fragment = new DummySectionFragment();
		
		Bundle args = new Bundle();
		args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
		fragment.setArguments(args);
		
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.container, fragment).commit();
		return true;
	}
	*/

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends ListFragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}
		
		 @Override
	    public void onActivityCreated(Bundle savedInstanceState) {
	        super.onActivityCreated(savedInstanceState);

	        // Populate list with our static array of titles.
	        //setListAdapter(new ArrayAdapter<String>(getActivity(),
	                //android.R.layout.simple_list_item_activated_1, Shakespeare.TITLES));

	        // Check to see if we have a frame in which to embed the details
	        // fragment directly in the containing UI.
	        //View detailsFrame = getActivity().findViewById(R.id.details);
	        //mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

	        //if (savedInstanceState != null) {
	            // Restore last state for checked position.
	          //  mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
	        //}

	        //if (mDualPane) {
	            // In dual-pane mode, the list view highlights the selected item.
	            //getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	            // Make sure our UI is in the correct state.
	            //showDetails(mCurCheckPosition);
	        //}
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
		    		Menu = res.getStringArray(R.array.menu_section5);
		    		break;
		    }
		    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
		        android.R.layout.simple_list_item_1, Menu);
		    setListAdapter(adapter);
	  }
		  
		/*@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// Create a new TextView and set its text to the fragment's section
			// number argument value.
			TextView textView = new TextView(getActivity());
			textView.setGravity(Gravity.CENTER);
			textView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return textView;
		}
		*/

		  @Override
		  public void onListItemClick(ListView l, View v, int position, long id) {
		    // Do something with the data

		  }
	}

}
