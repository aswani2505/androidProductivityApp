package mobile_computing_assignment.myapplication2;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.database.sqlite.*;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.Calendar;

public class TommyActivity extends Activity implements OnMapReadyCallback {

    MapView mapView;
    TextView schedule;
    int hour, min, bud, memo;
    Cursor res;
    SQLiteDatabase db;
    String currentTableName, temp;
    String[] building = {"CAVC", "MU", "BrickYard", "Coor", "Noble", "Hayden", "Gammage"};
    double[] xx = {33.423573, 33.417797, 33.423570, 33.420143, 33.420001, 33.418940, 33.416357};
    double[] yy = {-111.935320, -111.934325, -111.939694, -111.9378475, -111.930676, -111.934565, -111.93809};
    String[] thing = {"Meeting", "Class", "Study", "Discuss", "Career"};
    Marker searchP;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tommy);

        spinner = (Spinner) findViewById(R.id.spinner);
        schedule = (TextView)findViewById(R.id.schedule);

        db = this.openOrCreateDatabase("schedule", MODE_PRIVATE, null);
        currentTableName = "mySchedule7";
        try {
            db.execSQL("CREATE TABLE " + currentTableName + "(hour INT, min INT, bud INT, memo INT);");
        } catch (SQLiteException e) {
            System.out.println("F");
        }

        try {
            res = db.rawQuery("SELECT hour, min, bud, memo FROM " + currentTableName, null);
            res.moveToFirst();
            int dbCount = 0;
            while(res.moveToNext())
            {
                dbCount++;
            }

            if(dbCount < 20) {
                db.execSQL("INSERT INTO " + currentTableName + "(hour, min, bud, memo) values (" + 0 + "," + 10 + "," + 1 + "," + 4 + ");");
                db.execSQL("INSERT INTO " + currentTableName + "(hour, min, bud, memo) values (" + 0 + "," + 20 + "," + 0 + "," + 3 + ");");
                db.execSQL("INSERT INTO " + currentTableName + "(hour, min, bud, memo) values (" + 1 + "," + 30 + "," + 2 + "," + 0 + ");");
                db.execSQL("INSERT INTO " + currentTableName + "(hour, min, bud, memo) values (" + 2 + "," + 50 + "," + 3 + "," + 2 + ");");
                db.execSQL("INSERT INTO " + currentTableName + "(hour, min, bud, memo) values (" + 5 + "," + 30 + "," + 5 + "," + 2 + ");");

                db.execSQL("INSERT INTO " + currentTableName + "(hour, min, bud, memo) values (" + 6 + "," + 10 + "," + 4 + "," + 4 + ");");
                db.execSQL("INSERT INTO " + currentTableName + "(hour, min, bud, memo) values (" + 6 + "," + 30 + "," + 3 + "," + 0 + ");");
                db.execSQL("INSERT INTO " + currentTableName + "(hour, min, bud, memo) values (" + 7 + "," + 10 + "," + 0 + "," + 1 + ");");
                db.execSQL("INSERT INTO " + currentTableName + "(hour, min, bud, memo) values (" + 8 + "," + 30 + "," + 1 + "," + 2 + ");");
                db.execSQL("INSERT INTO " + currentTableName + "(hour, min, bud, memo) values (" + 9 + "," + 50 + "," + 2 + "," + 3 + ");");

                db.execSQL("INSERT INTO " + currentTableName + "(hour, min, bud, memo) values (" + 10 + "," + 10 + "," + 4 + "," + 4 + ");");
                db.execSQL("INSERT INTO " + currentTableName + "(hour, min, bud, memo) values (" + 12 + "," + 20 + "," + 0 + "," + 2 + ");");
                db.execSQL("INSERT INTO " + currentTableName + "(hour, min, bud, memo) values (" + 13 + "," + 30 + "," + 1 + "," + 0 + ");");
                db.execSQL("INSERT INTO " + currentTableName + "(hour, min, bud, memo) values (" + 14 + "," + 42 + "," + 4 + "," + 4 + ");");
                db.execSQL("INSERT INTO " + currentTableName + "(hour, min, bud, memo) values (" + 14 + "," + 50 + "," + 2 + "," + 2 + ");");

                db.execSQL("INSERT INTO " + currentTableName + "(hour, min, bud, memo) values (" + 15 + "," + 25 + "," + 4 + "," + 4 + ");");
                db.execSQL("INSERT INTO " + currentTableName + "(hour, min, bud, memo) values (" + 16 + "," + 10 + "," + 1 + "," + 1 + ");");
                db.execSQL("INSERT INTO " + currentTableName + "(hour, min, bud, memo) values (" + 17 + "," + 20 + "," + 0 + "," + 3 + ");");
                db.execSQL("INSERT INTO " + currentTableName + "(hour, min, bud, memo) values (" + 18 + "," + 30 + "," + 5 + "," + 0 + ");");
                db.execSQL("INSERT INTO " + currentTableName + "(hour, min, bud, memo) values (" + 20 + "," + 50 + "," + 3 + "," + 1 + ");");

                db.execSQL("INSERT INTO " + currentTableName + "(hour, min, bud, memo) values (" + 21 + "," + 10 + "," + 1 + "," + 4 + ");");
                db.execSQL("INSERT INTO " + currentTableName + "(hour, min, bud, memo) values (" + 22 + "," + 45 + "," + 0 + "," + 3 + ");");
                db.execSQL("INSERT INTO " + currentTableName + "(hour, min, bud, memo) values (" + 23 + "," + 55 + "," + 5 + "," + 2 + ");");
            }

        } catch (SQLiteException e) {
            System.out.println("G");
        }

        mapView = (MapView)findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onMapReady(final GoogleMap map)
    {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(33.4234577, -111.9295291), 14));
        searchP = map.addMarker(new MarkerOptions().position(new LatLng(0, 0)));

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        }
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    searchP.setVisible(false);
                    searchP = map.addMarker(new MarkerOptions()
                            .position(new LatLng(xx[position - 1], yy[position - 1]))
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                    searchP.setVisible(true);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(xx[position - 1], yy[position - 1]), 16));
                } else if (position == 0) {
                    searchP.setVisible(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.clear();
                spinner.setSelection(0);

                Calendar rightNow = Calendar.getInstance();
                int count = 3;
                temp = "";
                res = db.rawQuery("SELECT hour, min, bud, memo FROM " + currentTableName, null);
                res.moveToFirst();

                while(res.moveToNext())
                {
                    hour = res.getInt(0);
                    min = res.getInt(1);
                    bud = res.getInt(2);
                    memo = res.getInt(3);

                    if (count > 0 && (
                            hour - rightNow.get(Calendar.HOUR_OF_DAY) > 0
                                    || (hour == rightNow.get(Calendar.HOUR_OF_DAY) && min > rightNow.get(Calendar.MINUTE))
                    )) {
                        Marker m = map.addMarker(new MarkerOptions()
                                .position(new LatLng(xx[bud], yy[bud]))
                                .title(building[bud])
                                .snippet(Integer.toString(hour) + ":" + Integer.toString(min) + "  " + thing[memo]));
                        if(count == 3)
                            m.showInfoWindow();

                        temp = temp + "\n" + Integer.toString(hour) + ":" + Integer.toString(min) + "  " + thing[memo] + "@" + building[bud];
                        count--;
                    }

                }

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(33.4234577, -111.9295291), 14));
                schedule.setText(temp);
            }
        });
    }

    @Override
    public final void onDestroy()
    {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public final void onLowMemory()
    {
        mapView.onLowMemory();
        super.onLowMemory();
    }

    @Override
    public final void onPause()
    {
        mapView.onPause();
        super.onPause();
    }
}
