/*
    Created with the help of an Indragni tutorial at www.youtube.com/watch?v=ZHLCfqN-60A.
 */


package mobile_computing_assignment.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;

import mobile_computing_assignment.myapplication2.db.TaskContract;

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendarDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarDisplay = (CalendarView) findViewById(R.id.calendar);
        calendarDisplay.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                showTaskList(month, dayOfMonth, year);
            }
        });
    }

    public void showTaskList(int taskMonth, int taskDayOfMonth, int taskYear) {
        TaskContract.month = Integer.toString(taskMonth + 1);
        TaskContract.dayOfMonth = Integer.toString(taskDayOfMonth);
        TaskContract.year = Integer.toString(taskYear);

        Intent intent = new Intent(getApplicationContext(), TaskActivity.class);
        startActivity(intent);
    }
}