package com.zoobrew.gymtracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class ExerciseDetailActivity extends Activity implements OnSeekBarChangeListener{
	
	private SeekBar repsbar1, repsbar2, repsbar3;
	private TextView prog1, prog2, prog3, prog4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exercise_detail);
		repsbar1 = (SeekBar) findViewById(R.id.seekBar1);
		//repsbar2 = (SeekBar) findViewById(R.id.seekbar2);
		//repsbar3 = (SeekBar) findViewById(R.id.seekbar3);
		repsbar1.setOnSeekBarChangeListener(this);
		//repsbar2.setOnSeekBarChangeListener(this);
		//repsbar3.setOnSeekBarChangeListener(this);
		
		prog1 = (TextView)findViewById(R.id.textView1);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_exercise_detail, menu);
		return true;
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		//Change the number of reps to match the progress bar
		prog1.setText(""+ progress);
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
	}

}
