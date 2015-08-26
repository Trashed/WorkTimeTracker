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

    // Variables and objects needed by WorkTimeController
    private Calendar calendar;
    private Date startTimeAsDate;
    private Date endTimeAsDate;
    private Date lunchStartTimeAsDate;
    private Date lunchStopTimeAsDate;
//    private String startTime;
//    private String endTime;
    private SimpleDateFormat sdf;

    private FileHandler fileHandler;

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
        //startTime = sdf.format(startTimeAsDate);
    }

    /**
     * endWorkDay
     */
    public void endWorkDay() {
        endTimeAsDate = calendar.getTime();
        //endTime = sdf.format(endTimeAsDate);
    }

    public void startLunch() {
        lunchStartTimeAsDate = calendar.getTime();
    }

    public void stopLunch() {
        lunchStopTimeAsDate = calendar.getTime();
    }

    /**
     * getWorkStartTime
     * @return String startTime
     */
    public String getWorkStartTime() {
        return sdf.format(startTimeAsDate);
    }

    /**
     * getWorkEndTime
     * @return
     */
    public String getWorkEndTime() {
        return sdf.format(endTimeAsDate);
    }

    public String getLunchStopTime() {
        return sdf.format(lunchStopTimeAsDate);
    }

    public String getLunchStartTime() {
        return sdf.format(lunchStartTimeAsDate);
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
