package com.awesome.supergroup.arduinotp;

import android.content.Context;
import android.hardware.usb.UsbManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;



public class MainActivity extends ActionBarActivity {
    Thread displayManagerThread;

    DistanceManager managerDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        managerDistance = new DistanceManager((UsbManager) getSystemService(Context.USB_SERVICE));
        DisplayManager display = new DisplayManager(this,managerDistance);
        displayManagerThread = new Thread(display);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void getValuesAndPrint(View view){
         if (!this.displayManagerThread.isAlive()){
             this.displayManagerThread.start();
         }
    }


}
