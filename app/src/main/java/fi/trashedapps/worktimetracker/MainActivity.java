package fi.trashedapps.worktimetracker;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Widgets
    private Button startStopButton;
    // TODO: Remember to add holder variables for those two TextViews showing the time.
    private TextView workStartTime;
    private TextView workEndTime;

    // TODO: Initialize a WorkTimeController variable here. Stub class for it already created.
    private WorkTimeController wtc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Makes necessary init steps for the app
        initializeApplication();

        startStopButton = (Button)findViewById(R.id.button_start_stop_timer);
        startStopButton.setTag("start");
        startStopButton.setOnClickListener(this);

        // TextViews
        workStartTime = (TextView)findViewById(R.id.textView_work_started);
        workEndTime = (TextView)findViewById(R.id.textView_work_ended);

        // Object for handling work times
        wtc = new WorkTimeController(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // TODO: When activity resumes from the background, calculate how many hours are left of the current working day, and insert that value in its TextView.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        final String status = (String)v.getTag();
        if(status.equals("start")) {
            // Modify button from Start state to Stop state
            startStopButton.setText(R.string.start_button_stop_string);
            startStopButton.setBackgroundResource(R.drawable.round_button_red);
            startStopButton.setTag("stop");

            // Get start time and set time as a string to the TextView
            wtc.startWorkDay();
            workStartTime.setText(wtc.getWorkStartTime());

            // Start a thread which updates the other TextView showing time of working hours left.
            //updateWorkEndingTime();
        }
        else {
            // Modify button from Stop state to Start state
            startStopButton.setText(R.string.start_button_start_string);
            startStopButton.setBackgroundResource(R.drawable.round_button_green);
            startStopButton.setTag("start");

            // Get end time
            wtc.endWorkDay();
            workEndTime.setText(wtc.getWorkEndTime());
        }
    }


    /*private void updateWorkEndingTime() {
        // Update the TextView before starting the thread
        try {
            workEndTime.setText(wtc.calculateWorkingHoursLeft());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    // Update every one minute
                    Thread.sleep(1000 * 60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            workEndTime.setText(wtc.calculateWorkingHoursLeft());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
        new Thread(runnable).start();
    }*/


    private void initializeApplication() {
        // Handle app's first launch. If true, write that value to false.
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        boolean isFirstLaunch = prefs.getBoolean("isFirstLaunch", true);
        if(isFirstLaunch) {
            SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
            editor.putBoolean("isFirstLaunch", false);
            editor.apply();

            // FileHandler - create a template CVS file for storing work time data from now on
            try {
                FileHandler.createTemplateCSV(getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("INIT ERROR", e.getMessage());
            }
        }
    }
}
