package com.example.zebrascanproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.symbol.emdk.*;
import com.symbol.emdk.EMDKManager.EMDKListener;
import com.symbol.emdk.barcode.BarcodeManager;
import com.symbol.emdk.barcode.ScannerException;

import org.w3c.dom.Text;


public class MainActivity extends Activity implements  EMDKListener{


    //Assign the profile name used in EMDKConfig.xml
    private String profileName = "DataCaptureProfile";

    //Declare a variable to store ProfileManager object
    private ProfileManager mProfileManager = null;

    //Declare a variable to store EMDKManager object
    private EMDKManager emdkManager = null;


    private BarcodeManager barcodeManager = null;

    private TextView textViewBarcode=null;

    private Button buttonMSR=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        EMDKResults results = EMDKManager.getEMDKManager(
                getApplicationContext(), this);

        System.out.println(" ---------------------> START SCAN <---------------\n");

        if (results.statusCode != EMDKResults.STATUS_CODE.SUCCESS) {
            Toast.makeText(this,"EMDKManager Request Failed",Toast.LENGTH_SHORT).show();
        }

        textViewBarcode = (TextView)findViewById(R.id.textViewBarcode);

        buttonMSR = (Button)findViewById(R.id.buttonMSR);

       // buttonMSR.setOnClickListener(buttonMSRonClick);

        Intent i = getIntent();
        handleDecodeData(i);


    }

    @Override
    public void onNewIntent(Intent i){
        handleDecodeData(i);
    }

    public void handleDecodeData(Intent intent){

    }

//    private View.OnClickListener buttonMSRonClick = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Intent myIntent = new Intent(MainActivity.this,MSRActivity.class);
//            startActivity(myIntent);
//        }
//
//    }
    @Override
    public void onOpened(EMDKManager emdkmanager)
    {

        this.emdkManager = emdkManager;
        barcodeManager = (BarcodeManager) this.emdkManager
                .getInstance(EMDKManager.FEATURE_TYPE.BARCODE);

        if (barcodeManager != null){
//            System.out.println("----> barcodeManager != null");


        }
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //Clean up the objects created by EMDK manager
        emdkManager.release();
    }


    @Override
    public void onClosed() {

    }
}
