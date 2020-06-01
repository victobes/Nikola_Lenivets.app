package com.example.nikola_lenivetsapp.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.nikola_lenivetsapp.Fragments.OtherFragments.GoogleMapFragment;
import com.example.nikola_lenivetsapp.Fragments.OtherFragments.MapFragment;
import com.example.nikola_lenivetsapp.Fragments.OtherFragments.NewsFragment;
import com.example.nikola_lenivetsapp.Fragments.OtherFragments.ProfileFragment;
import com.example.nikola_lenivetsapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    FirebaseAuth mAuth;
    FirebaseUser currentUser ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportActionBar().setTitle(R.string.google_map_title);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new GoogleMapFragment()).commit();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        updateNavHeader();

        getSupportActionBar().setTitle(R.string.map_title);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new MapFragment()).commit();
    }

    public void updateNavHeader() {

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUserName = headerView.findViewById(R.id.nav_username);
        TextView navUserEmail = headerView.findViewById(R.id.nav_user_mail);

        CircleImageView navUserPhoto = headerView.findViewById(R.id.nav_user_photo);
        navUserEmail.setText(currentUser.getEmail());
        navUserName.setText(currentUser.getDisplayName());

        if (currentUser.getPhotoUrl() != null) {
            Glide.with(this).load(currentUser.getPhotoUrl()).into(navUserPhoto);
        } else {
            Glide.with(this).load(R.drawable.lenivets_hello).into(navUserPhoto);
        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_map) {

            getSupportActionBar().setTitle(R.string.map_title);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new MapFragment()).commit();

        } else if (id == R.id.nav_profile) {

            getSupportActionBar().setTitle(R.string.profile_title);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment()).commit();

        } else if (id == R.id.nav_news) {

            getSupportActionBar().setTitle(R.string.news_title);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new NewsFragment()).commit();


        } else if (id == R.id.nav_signout) {

            FirebaseAuth.getInstance().signOut();
            Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginActivity);
            finish();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
