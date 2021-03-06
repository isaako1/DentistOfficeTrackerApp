package com.example.froggy.dentistofficetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * This class is designed to display and manage the different items
 * in the navigation drawer to change between the differences interfaces
 * in the application.
 */
public class NavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String type;
    private String username;


    private static final String TAG= "Navigation Drawer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        // This type is used to determine which activities the user has access to
        type = getIntent().getExtras().getString("type");
        username = getIntent().getExtras().getString("username");

        setDrawer();
        setNavigationMenu();
    }

    // Creates the drawer and toolbar
    public void setDrawer(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    // Creates the navigation menu depending on the user type
    public void setNavigationMenu(){

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().clear();

        // The navigation menu depends on the UserType
        if(type.compareToIgnoreCase("admin") == 0)
            navigationView.inflateMenu(R.menu.admin_menu);
        else if(type.compareToIgnoreCase("patient") == 0)
            navigationView.inflateMenu(R.menu.patient_menu);
        else if(type.compareToIgnoreCase("dentist") == 0)
            navigationView.inflateMenu(R.menu.dentist_menu);

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_register_Patient) {
            Intent intent = new Intent(this, Add_Patient.class);
            Log.v(TAG, "Starting Register Patient activity");
            startActivity(intent);
        } else if (id == R.id.nav_patient_balance) {
           Intent intent = new Intent(this, BillPatientSearch.class);
            Log.v(TAG, "Starting Patients' Balance activity");
            startActivity(intent);
        } else if (id == R.id.nav_calendar) {
            //Handle the calendar
            Intent intent = new Intent(this, CalendarApp.class);
            Log.v(TAG, "Starting Calendar activity");
            intent.putExtra("username", username);
            intent.putExtra("type", type);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent i = new Intent(getApplicationContext(), Settings.class);
            Log.v(TAG, "Starting Settings activity");
            i.putExtra("username", username);
            startActivity(i);
        } else if (id == R.id.nav_share) {
            // For later use
        } else if (id == R.id.nav_send) {
            // For later use
        } else if (id == R.id.nav_view_patient){
            Intent i = new Intent(getApplicationContext(), Search_Patient.class);
            Log.v(TAG, "Starting Search_Patient Activity");
            i.putExtra("type", type);
            i.putExtra("username", username);
            startActivity(i);
        } else if (id == R.id.nav_register_dentist){
            Intent i = new Intent(getApplicationContext(), AddDentist.class);
            Log.v(TAG, "Starting RegisterDentist Activity");
            startActivity(i);
        } else  if(id == R.id.EditProfile){
            Intent i = new Intent(getApplicationContext(), EditInfo.class);
            Log.v(TAG, "Starting EditInfo Activity");
            i.putExtra("username", username);
            startActivity(i);
        } else if(id == R.id.nav_patient_payment_history){
            Intent i = new Intent(getApplicationContext(), ViewBills.class);
            Log.v(TAG, "Starting ViewBills Activity");
            i.putExtra("username", username);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
