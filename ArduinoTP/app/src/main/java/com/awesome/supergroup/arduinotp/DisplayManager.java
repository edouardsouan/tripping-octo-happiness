package com.awesome.supergroup.arduinotp;

import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Alx on 29/04/2015.
 */
public class DisplayManager implements Runnable {

    //gestion de la distance en entrée
    private DistanceManager distance;
    //on y print la distance
    private final TextView t ;
    private final MainActivity mainActivity;
    //l'image de radar a mettre a jour
    private ImageView iv;

    public DisplayManager(MainActivity theActivity, DistanceManager managerDistance) {
        this.mainActivity = theActivity;
        iv= (ImageView)mainActivity.findViewById(R.id.radarImage);
        t = (TextView)mainActivity.findViewById(R.id.Distance);
        distance = managerDistance;
    }

    @Override
    public void run() {
        while (true){
            mainActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int theDistance = distance.getDistance();
                    t.setText(distance.getMetersFromCM(theDistance));
                    if (theDistance >= 250) {
                        iv.setImageResource(R.mipmap.green_signal);
                    } else if (theDistance < 250 && theDistance >= 150) {
                        iv.setImageResource(R.mipmap.yellow_signal);
                    } else if (theDistance < 150 && theDistance >= 75) {
                        iv.setImageResource(R.mipmap.orange_signal);
                    } else if (theDistance < 75) {
                        iv.setImageResource(R.mipmap.red_signal);
                    }
                }
            });
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
