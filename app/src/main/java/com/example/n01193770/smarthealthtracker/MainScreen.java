package com.example.n01193770.smarthealthtracker;

        import android.support.annotation.NonNull;
        import android.support.design.widget.BottomNavigationView;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;
        import android.widget.Switch;
        import android.widget.Toast;
        import android.content.Intent;

public class MainScreen extends AppCompatActivity {

    ImageButton button1,button2,button3,button4,button5;

    Button b1,b2,b3,b4;

    private BottomNavigationView bottomNav;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {

                    case R.id.bottom_quit:
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);



                    case R.id.bottom_device:
                        Intent intent1 = new Intent(MainScreen.this,device.class);
                        startActivity(intent1);
                        return true;

                    case R.id.bottom_settings:
                        Intent intent2 = new Intent(MainScreen.this,Settings.class);
                        startActivity(intent2);
                        return true;
                }


                return false;
            }
        });









        // Buttons
        b1 = (Button) findViewById(R.id.heart_rate);
        b2 = (Button) findViewById(R.id.distance);
        b3 = (Button) findViewById(R.id.calhr);
        b4 = (Button) findViewById(R.id.no_steps);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainScreen.this,HeartRate.class);
                startActivity(intent1);

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainScreen.this,Distance.class);
                startActivity(intent1);             }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainScreen.this,Calburnt.class);
                startActivity(intent1);            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainScreen.this,Steps.class);
                startActivity(intent1);            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.prof, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.prof) {
            Intent intent1 = new Intent(MainScreen.this,Main2Activity.class);
            startActivity(intent1);


        }
        return super.onOptionsItemSelected(item);
    }


}
