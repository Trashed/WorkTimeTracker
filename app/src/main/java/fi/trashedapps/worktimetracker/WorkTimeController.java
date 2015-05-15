package fi.trashedapps.worktimetracker;

import android.content.Context;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Jose on 15.5.2015.
 */
public class WorkTimeController {

    // Member variables
    private Date startTimeAsDate;
    private Date endTimeAsDate;
    private Calendar calendar;
    private String startTime;
    private String endTime;

    /**
     * Constructor
     * @param appContext
     */
    public WorkTimeController(Context appContext) {
        // TODO: Add stuff here if needed
    }

    // TODO: Create a method that checks if user tries to log multiple hours within the same day.

    /**
     * startWorkDay
     *
     * Gets current time (HH:mm) and stores it
     */
    public void startWorkDay() {
        // TODO: Needs implementation
        startTimeAsDate = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm", Locale.getDefault());
    }

    /**
     * endWorkDay
     */
    public void endWorkDay() {
        // TODO: Needs implementation
    }

    /**
     * getWorkStartTime
     * @return
     */
    public String getWorkStartTime() {
        // TODO: Create a static variable or store the work start time value into SharedPrefs.
        return null;
    }

    /**
     * getWorkEndTime
     * @return
     */
    public String getWorkEndTime() {
        // TODO: Create a static variable or store the work end time value into SharedPrefs.
        return null;
    }
}
