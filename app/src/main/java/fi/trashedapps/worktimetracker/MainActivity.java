package fi.trashedapps.worktimetracker;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Widgets
    private Button startStopButton;
    // TODO: Remember to add holder variables for those two TextViews showing the time.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startStopButton = (Button)findViewById(R.id.button_start_stop_timer);
        startStopButton.setTag("start");
        startStopButton.setOnClickListener(this);
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
            startStopButton.setText(R.string.start_button_stop_string);
            startStopButton.setBackgroundResource(R.drawable.round_button_red);
            startStopButton.setTag("stop");
        }
        else {
            startStopButton.setText(R.string.start_button_start_string);
            startStopButton.setBackgroundResource(R.drawable.round_button_green);
            startStopButton.setTag("start");
        }
    }
}
