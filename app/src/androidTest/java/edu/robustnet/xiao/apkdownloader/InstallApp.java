package edu.robustnet.xiao.apkdownloader;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.test.ActivityUnitTestCase;
import android.util.Log;


import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiao on 12/22/17.
 */

@RunWith(AndroidJUnit4.class)
public class InstallApp  {

    private static final String TAG="InstallApp";
    private UiDevice mDevice;
    private static final long LOG_TIME = TimeUnit.MINUTES.toMillis(60);

    @Test
    public void init() throws Exception {

        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.wakeUp();

        long startTime = System.currentTimeMillis();
        while(System.currentTimeMillis() - startTime < LOG_TIME){
            runTest();
            SystemClock.sleep(1000);
        }

    }

    public void runTest() throws UiObjectNotFoundException, IOException {

        //Log.d(TAG, "running test");

        UiObject2 installButton = mDevice.findObject(By.text("INSTALL"));
        // wait until intent received
        if(installButton!=null){
            Log.d(TAG, "Install button appears, clicking");
            installButton.click();
        }
        else{
            Log.d(TAG, "Install button not appear yet");
            // in case where app downloading is large
            UiObject2 proceedButton = mDevice.findObject(By.text("PROCEED"));
            if(proceedButton!=null){
                proceedButton.click();
            }
            else{
                Log.d(TAG, "no WiFi alert as well");
            }
            return;
        }

        // in case where app needs permission
        SystemClock.sleep(2000);
        UiObject2 acceptButton = mDevice.findObject(By.text("ACCEPT"));
        if(acceptButton!=null){
            acceptButton.click();
        }
        else{
            Log.d(TAG, "no permission required");
        }
//        SystemClock.sleep(28000);

    }
}
