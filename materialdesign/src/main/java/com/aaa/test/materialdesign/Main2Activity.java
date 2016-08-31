package com.aaa.test.materialdesign;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    Snackbar sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FloatingActionButton fab_cover = (FloatingActionButton) findViewById(R.id.fab_main2_cover);
        fab_cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                //Snackbar.make(til_et_test,"aaaaaaaaaaa",Snackbar.LENGTH_INDEFINITE).show();
                sb=Snackbar.make(view, "Snack Bar Text", Snackbar.LENGTH_INDEFINITE).setAction("close",new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Main2Activity.this,Main4Activity.class));
                        sb.dismiss();
                    }
                }).setActionTextColor(Color.WHITE);
                sb.show();
            }
        });



        FloatingActionButton fab_anim = (FloatingActionButton) findViewById(R.id.fab_main2_anim);

    }
}
