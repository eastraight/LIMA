package com.example.lima;

/**** Added by me *****/
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
/**********************/
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

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
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home/*, R.id.nav_gallery, R.id.nav_slideshow*/)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

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

    /* transition getter */
    public static boolean checkTransition() {
        return transition;
    }

    /* Main Menu Buttons */

    /* Button 1 - Violencia Familiar */
    public void B1Listener(View view) {
        Intent intent = new Intent(this, VFActivity.class);
        startActivity(intent);
        if (transition) {
            this.overridePendingTransition(R.anim.animation_enter, R.anim.hold);
        } else this.overridePendingTransition(0, 0);
    }

    /* Button 2 - Solución de Conflictos */
    public void B2Listener(View view) {
        Intent intent = new Intent(this, SCActivity.class);
        startActivity(intent);
        if (transition) {
            this.overridePendingTransition(R.anim.animation_enter, R.anim.hold);
        } else this.overridePendingTransition(0, 0);    }

    /* Button 3 - Crianza Positiva */
    public void B3Listener(View view) {
        Intent intent = new Intent(this, CPActivity.class);
        startActivity(intent);
        if (transition) {
            this.overridePendingTransition(R.anim.animation_enter, R.anim.hold);
        } else this.overridePendingTransition(0, 0);    }

    /* Button 4 - Recursos Bíblicos y Teológicos */
    public void B4Listener(View view) {
        Intent intent = new Intent(this, RBActivity.class);
        startActivity(intent);
        if (transition) {
            this.overridePendingTransition(R.anim.animation_enter, R.anim.hold);
        } else this.overridePendingTransition(0, 0);    }

    /* Button 5 - Denuncia */
    public void B5Listener(View view) {
        Intent intent = new Intent(this, DActivity.class);
        startActivity(intent);
        if (transition) {
            this.overridePendingTransition(R.anim.animation_enter, R.anim.hold);
        } else this.overridePendingTransition(0, 0);    }

    /* Button 6 - Prevención de la Violencia */
    public void B6Listener(View view) {
        Intent intent = new Intent(this, PActivity.class);
        startActivity(intent);
        if (transition) {
            this.overridePendingTransition(R.anim.animation_enter, R.anim.hold);
        } else this.overridePendingTransition(0, 0);    }

    /* Button 7 - Autoevaluación */
    public void B7Listener(View view) {
        Intent intent = new Intent(this, AActivity.class);
        startActivity(intent);
        if (transition) {
            this.overridePendingTransition(R.anim.animation_enter, R.anim.hold);
        } else this.overridePendingTransition(0, 0);    }

    /* Button 8 - Contacto */
    public void B8Listener(View view) {
        Intent intent = new Intent(this, CActivity.class);
        startActivity(intent);
        if (transition) {
            this.overridePendingTransition(R.anim.animation_enter, R.anim.hold);
        } else this.overridePendingTransition(0, 0);    }


}