package fi.trashedapps.worktimetracker;

import android.content.Context;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Jose on 15.5.2015.
 */
public class WorkTimeController {

    private Calendar calendar;
    private String startTime;
    private Date startTimeAsDate;
    private String endTime;
    private Date endTimeAsDate;
    private SimpleDateFormat sdf;

    /**
     * Constructor
     * @param appContext
     */
    public WorkTimeController(Context appContext) {
        // TODO: Add stuff here if needed
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        sdf = new SimpleDateFormat("HH:mm");
        sdf.setCalendar(calendar);
    }

    // TODO: Create a method that checks if user tries to log multiple hours within the same day.

    /**
     * startWorkDay
     *
     * Gets current time from Calendar, formats into HH:mm and stores it in a variable as well as
     * saves the time value (HH:mm) into a file.
     */
    public void startWorkDay() {
        startTimeAsDate = calendar.getTime();
        //SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        startTime = sdf.format(startTimeAsDate);
    }

    /**
     * endWorkDay
     */
    public void endWorkDay() {
        endTimeAsDate = calendar.getTime();

        endTime = sdf.format(endTimeAsDate);
    }

    /**
     * getWorkStartTime
     * @return String startTime
     */
    public String getWorkStartTime() {
        return startTime;
    }

    /**
     * getWorkEndTime
     * @return
     */
    // TODO: Is this method needed?
    public String getWorkEndTime() {
        // TODO: Create a static variable or store the work end time value into SharedPrefs.
        return null;
    }

    /**
     * calculateWorkingHoursLeft
     *
     * Calculates the time difference between start time and estimated end time and returns the
     * difference as a string.
     *
     * @return Hours left of current work day in a String variable.
     * @throws ParseException
     */
    public String calculateWorkingHoursLeft() throws ParseException {
        // Calendar instance for calculating the end time.
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(startTimeAsDate.getTime() + 28800000 - System.currentTimeMillis());
        Date end = c.getTime();

        return sdf.format(end);
    }


    /*private static String convertSecondsToHMmSs(long seconds) {
        long m = (seconds / 60) % 60;
        long h = (seconds / (60 * 60)) % 24;
        return String.format("%d:%02d", h, m);
    }

    private static long convertHoursToMs() {
        return 0L;
    }*/
}
