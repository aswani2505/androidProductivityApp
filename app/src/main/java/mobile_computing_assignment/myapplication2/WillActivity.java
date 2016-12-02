
package mobile_computing_assignment.myapplication2;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;

import mobile_computing_assignment.myapplication2.db.TaskContract;

public class WillActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showTodayTasks();
        this.finish();
    }
    public void showTaskList(int taskMonth, int taskDayOfMonth, int taskYear) {
        TaskContract.month = Integer.toString(taskMonth + 1);
        TaskContract.dayOfMonth = Integer.toString(taskDayOfMonth);
        TaskContract.year = Integer.toString(taskYear);

        Intent intent = new Intent(getApplicationContext(), TaskActivity.class);
        startActivity(intent);
    }

    public void showTodayTasks(){
        Calendar today = Calendar.getInstance();
        showTaskList(today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.YEAR));
    }
}
