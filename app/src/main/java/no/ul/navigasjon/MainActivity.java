package no.ul.navigasjon;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;




public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout skuffa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        skuffa = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, skuffa, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        skuffa.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProgramFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_Program);
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_Program:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProgramFragment()).commit();
                break;
            case R.id.nav_Seminar:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SeminarFragment()).commit();
                break;
            case R.id.nav_Aktiviteter:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AktivitetFragment()).commit();
                break;
            case R.id.nav_Facebook:
                String facebookUrl = "https://www.facebook.com/events/246464819412107/";

                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);


                //Toast.makeText(this, "Facebook", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_Twitter:
                //gå til Twitter
                Toast.makeText(this, "Twitter", Toast.LENGTH_SHORT).show();

                String twitterUrl = "https://twitter.com/unglandsmote";

                Intent twitterIntent = new Intent(Intent.ACTION_VIEW);
                twitterIntent.setData(Uri.parse(twitterUrl));
                startActivity(twitterIntent);

                break;
        }

        skuffa.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (skuffa.isDrawerOpen(GravityCompat.START)){
            skuffa.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

}
