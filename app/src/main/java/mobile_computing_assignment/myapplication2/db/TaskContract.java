/**
 * Adapted from a program by Aldo Ziflaj at the SitePoint website
 * http://www.sitepoint.com/starting-android-development-creating-todo-app/.
 */

package mobile_computing_assignment.myapplication2.db;
import android.provider.BaseColumns;

public class TaskContract {
    public static final int DB_VERSION = 1;

    // Section created by William Boyd.
    public static String month;
    public static String dayOfMonth;
    public static String year;

    public static String dbName() {
        return "com.example.seeker.tasklist.db" + month + "0" + dayOfMonth + year;
    }

    public static String tableTitle(){
        // Returns a title unique to the date.
        String titleOfTable;
        titleOfTable = "tasklist" + month + "0" + dayOfMonth + year;
        return titleOfTable;
    }
    // End section created by William Boyd

    public class TaskEntry implements BaseColumns {
        public static final String TABLE = "tasks";

        public static final String COL_TASK_TITLE = "title";
    }
}
