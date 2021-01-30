package org.pazyesperanza.paztoral;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private static boolean transition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);


//        NavigationView navigationView = findViewById(R.id.nav_view);

        setNavigationViewListener();

//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home/*, R.id.nav_gallery, R.id.nav_slideshow*/)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);


        // Check if we're running on Android 5.0 or higher for transition animations
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) this.transition = true;
        else this.transition = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    /* Added by me vvv */

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {

            case R.id.signin: {
                Intent intent = new Intent(MainActivity.this, SigninActivity.class);
                startActivity(intent);
                break;
            }
        }
        //close navigation drawer
        // mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home/*, R.id.nav_gallery, R.id.nav_slideshow*/)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
    }






    /* transition getter */
    public static boolean checkTransition() {
        return transition;
    }

    /* Main Menu Buttons */

    /* Button 1 - La violencia: introducción */
    public void B1Listener(View view) {
        Intent intent = new Intent(this, B1Activity.class);
        startActivity(intent);
        if (transition) {
            this.overridePendingTransition(R.anim.animation_enter, R.anim.hold);
        } else this.overridePendingTransition(0, 0);
    }

    /* Button 2 - Violencia en la pareja */
    public void B2Listener(View view) {
        Intent intent = new Intent(this, B2Activity.class);
        startActivity(intent);
        if (transition) {
            this.overridePendingTransition(R.anim.animation_enter, R.anim.hold);
        } else this.overridePendingTransition(0, 0);    }

    /* Button 3 - Maltrato infantil */
    public void B3Listener(View view) {
        Intent intent = new Intent(this, B3Activity.class);
        startActivity(intent);
        if (transition) {
            this.overridePendingTransition(R.anim.animation_enter, R.anim.hold);
        } else this.overridePendingTransition(0, 0);    }

    /* Button 4 - Solución de conflictos */
    public void B4Listener(View view) {
        Intent intent = new Intent(this, B4Activity.class);
        startActivity(intent);
        if (transition) {
            this.overridePendingTransition(R.anim.animation_enter, R.anim.hold);
        } else this.overridePendingTransition(0, 0);    }

    /* Button 5 - Crianza positiva */
    public void B5Listener(View view) {
        Intent intent = new Intent(this, B5Activity.class);
        startActivity(intent);
        if (transition) {
            this.overridePendingTransition(R.anim.animation_enter, R.anim.hold);
        } else this.overridePendingTransition(0, 0);    }

    /* Button 6 - Recursos bíblicos */
    public void B6Listener(View view) {
        Intent intent = new Intent(this, B6Activity.class);
        startActivity(intent);
        if (transition) {
            this.overridePendingTransition(R.anim.animation_enter, R.anim.hold);
        } else this.overridePendingTransition(0, 0);    }

    /* Button 7 - Denunciando la violencia */
    public void B7Listener(View view) {
        Intent intent = new Intent(this, B7Activity.class);
        startActivity(intent);
        if (transition) {
            this.overridePendingTransition(R.anim.animation_enter, R.anim.hold);
        } else this.overridePendingTransition(0, 0);    }

    /* Button 8 - [Otro] */
    public void B8Listener(View view) {
        Intent intent = new Intent(this, B8Activity.class);
        startActivity(intent);
        if (transition) {
            this.overridePendingTransition(R.anim.animation_enter, R.anim.hold);
        } else this.overridePendingTransition(0, 0);    }


}