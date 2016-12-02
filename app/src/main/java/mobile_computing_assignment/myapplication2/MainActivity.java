package mobile_computing_assignment.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mapbutton = (Button)findViewById(R.id.mapbutton);
        mapbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TommyActivity.class);
                startActivity(intent);
            }
        });

        Button chatbutton = (Button)findViewById(R.id.chatbutton);
        chatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AniketActivity.class);
                startActivity(intent);
            }
        });

        Button calenderbutton = (Button)findViewById(R.id.calenderbutton);
        calenderbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WillActivity.class);
                startActivity(intent);
            }
        });
    }
}
