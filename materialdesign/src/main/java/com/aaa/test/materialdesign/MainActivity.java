package com.aaa.test.materialdesign;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.aaa.test.materialdesign.tab.BehaviorTest2Activity;
import com.aaa.test.materialdesign.tab.BehaviorTestActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView rv_test;
    EditText et_test;
    TextInputEditText tiet_test;
    TextInputLayout til_et_test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 将toolbar 设置成系统状态栏
         */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initEditText();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                //Snackbar.make(til_et_test,"aaaaaaaaaaa",Snackbar.LENGTH_INDEFINITE).show();
                Snackbar.make(til_et_test, "Snack Bar Text", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Go to next", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                               startActivity(new Intent(MainActivity.this,Main2Activity.class));
                            }
                        }) .setActionTextColor(Color.WHITE)
                        .setCallback(new Snackbar.Callback(){
                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                super.onDismissed(snackbar, event);
                                if (event == DISMISS_EVENT_SWIPE ||
                                        event == DISMISS_EVENT_TIMEOUT ||
                                        event ==DISMISS_EVENT_CONSECUTIVE) {

                                }
                                Toast.makeText(MainActivity.this,"clicked snack bar",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void initEditText()
    {
        tiet_test=(TextInputEditText)findViewById(R.id.tiet_test);
        tiet_test.setError(getString(R.string.error_text));

        til_et_test=(TextInputLayout)findViewById(R.id.til_et_test);
        et_test=(EditText)findViewById(R.id.et_test);
        et_test.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count==0){
                    et_test.setError(null);
                    til_et_test.setError(null);
                    //til_et_test.setErrorEnable(false);
                }else{
                    til_et_test.setError(getString(R.string.error_text));
                    //et_test.setError(getString(R.string.error_text));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
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
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(MainActivity.this, Main3Activity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(MainActivity.this, Main4Activity.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
             Intent intent=new Intent(this, BehaviorTestActivity.class);
             startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent=new Intent(this, BehaviorTest2Activity.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
