package org.example.proyectoisw1213;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class NavDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]
    private GoogleSignInClient mGoogleSignInClient;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // [START config_signin]
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // [END config_signin]

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_agregar) {
            Intent intent = new Intent(this,AgregarActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_lugares) {
            Intent intent = new Intent(this,lugarRecycleVActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_preferencias) {
            Intent intent = new Intent(this,PreferenciasActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_salir) {
            revokeAccess();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void revokeAccess() {
        // Firebase sign out
        mAuth.signOut();

        // Google revoke access
        mGoogleSignInClient.revokeAccess().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        salir();
                    }
                });
    }

    private void salir(){
        finish();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }

    public void setPreferences (View v){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String color = pref.getString("fondo", "1");
        Boolean fondo = pref.getBoolean("fondoPantalla", false);

        int selectedColor = 0;
        if (color.equals("1")){
            selectedColor =  pref.getInt("color",getResources().getColor(R.color.colorRed));
        }
        if(color.equals("2")){
            selectedColor = pref.getInt("color",getResources().getColor(R.color.colorGreen));
        }
        if (color.equals("3")){
            selectedColor = pref.getInt("color",getResources().getColor(R.color.colorYellow));
        }
        if (color.equals("4")){
            selectedColor = pref.getInt("color",getResources().getColor(R.color.colorPrimary));
        }

        toolbar.setBackgroundColor(selectedColor);

       if (fondo){
            ImageView img = (ImageView)findViewById(R.id.ivBackground);
            img.setImageResource(R.drawable.imgfondo);
        }else{
           ImageView img = (ImageView)findViewById(R.id.ivBackground);
           img.setImageResource(R.drawable.imgpref);
       }
    }



}
