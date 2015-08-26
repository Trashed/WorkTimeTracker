package fi.trashedapps.worktimetracker;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;


/**
 * Created by Jose on 15.5.2015.
 */
public class FileHandler {

    // Class constants
    public final static String WORK_DATA_FILE_NAME = "work_log.csv";

    /**
     * Default constructor
     */
    public FileHandler() {

    }

    /**
     * createTemplateCSV
     */
    public static void createTemplateCSV(Context context) throws Exception {
        //File templateFile = new File(context.getFilesDir(), WORK_DATA_FILE_NAME);
        String line = "Year,Month,Day,Start time,Stop time,Time worked,Difference";
        FileOutputStream os;

        try {
            Log.v("INFO", "Internal storage path: " + context.getFilesDir().getAbsolutePath());
            os = context.openFileOutput(WORK_DATA_FILE_NAME, Context.MODE_PRIVATE);
            os.write(line.getBytes());
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
