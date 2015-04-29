package com.awesome.supergroup.arduinotp;

import android.content.Context;
import android.hardware.usb.UsbManager;

import java.util.Random;

/**
 * Created by Alx on 29/04/2015.
 */
public class DistanceManager {

    private UsbManager theUsbManager;

    public DistanceManager(UsbManager aUsbManager ){
        theUsbManager = aUsbManager;
    }

    private int generateRandom(){
        Random rand = new Random();
        int randomNum = rand.nextInt((300 - 3) + 1) + 0;
        return randomNum;
    }

    //TODO
    private int recupNumberFromArduino(){
        //theUsbManager.getDeviceList().get(null).;
        return 0;
    }

    //renvoie la distance en int
    public int getDistance() {
        return generateRandom(); //utiliser recupNumberFromArduino
    }

    public String getMetersFromCM( int cms){

        double theDist = (double) cms / 100;
        String distance = "";
        int meter = 0;
        int centimeters = 0;

        if (theDist%1 != 0){
            meter = (int)theDist;
            centimeters = (int)((theDist%1)*100);
        }else{
            meter = (int)theDist;
            centimeters = 0;
        }

        if(meter == 0){
            distance = centimeters+" cm";
        }else{
            distance = meter+" m "+centimeters+" cm";
        }

        return distance;

    }


}
