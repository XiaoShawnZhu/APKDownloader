package edu.robustnet.xiao.apkdownloader;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.Exception;

import android.os.Environment;
import android.os.SystemClock;
import android.provider.Settings;
import android.test.InstrumentationTestCase;
import android.util.Log;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiao on 12/22/17.
 */

public class PlayStore extends Activity {

    private static final String TAG="PlayStore";
    private String apkName;
    private String apkListPath = "/sdcard/apkList.txt";
    private BufferedReader apkList;
    private static final long LOG_TIME = TimeUnit.MINUTES.toMillis(5);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            apkList = new BufferedReader(new FileReader(apkListPath));
            Log.d(TAG, "apkList opened.");
        }
        catch (Exception e){
        }

        long startTime = System.currentTimeMillis();
        while(System.currentTimeMillis() - startTime < LOG_TIME){
            try{
                apkName = apkList.readLine();
                if(apkName==null){
                    break;
                }
                String appUrl = "market://details?id="+apkName;
                gotoApp(appUrl);
                SystemClock.sleep(30000);
            }
            catch (Exception e){
            }
        }
    }

    public void gotoApp(String url){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }


}
