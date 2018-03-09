package com.example.ysc8v.nextchatapp.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.ysc8v.nextchatapp.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, LoginActivity.class));
        setContentView(R.layout.activity_main);
        setBottomNavigationBar();
    }


    private void setBottomNavigationBar(){
        BottomNavigationBar mBottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_message, "Chats").setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.white))
                .addItem(new BottomNavigationItem(R.drawable.ic_me, "Me").setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.white))
                .addItem(new BottomNavigationItem(R.drawable.ic_contact, "Contacts").setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.white))
                .setFirstSelectedPosition(0) // default item
                .initialise();

    }
}
