package com.example.toolwithcare;

import android.content.Intent;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    CheckBox checkBox;
    ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar =  findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Home Page");
        toolbar.setSubtitle("Welcome User..!");

        checkBox=findViewById(R.id.mycheckBox);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {

                    actionMode = MainActivity.this.startSupportActionMode(new ActionBarCallback());// Action modes can be used to provide alternative interaction modes and replace parts of the normal UI until finished.

                } else {

                    actionMode.finish();

                }
            }
        });
    }

    private class ActionBarCallback implements android.support.v7.view.ActionMode.Callback
    {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(R.menu.contextual_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu)
        {
            actionMode.setTitle("welcome");

            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            Toast.makeText(MainActivity.this,"Deleted Successfully",Toast.LENGTH_SHORT).show();
            // You can add Functionality to your Menu Buttons here.
            // Apply switch case statements in case there are more than one Menu Buttons.
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {

            // This is called when Action Mode is completed.

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id)
        {
            case R.id.action_Setting:
                startActivity(new Intent(Settings.ACTION_DATE_SETTINGS));
            Toast.makeText(MainActivity.this,"Setting Clicked",Toast.LENGTH_SHORT).show();
            break;

            case R.id.action_camera:
                Toast.makeText(MainActivity.this,"Camera Clicked",Toast.LENGTH_SHORT).show();
                Intent invokecam=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(invokecam);
                break;

            case R.id.action_logut:
                Toast.makeText(MainActivity.this,"Logout Clicked",Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}



