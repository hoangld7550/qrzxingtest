package hoangpoly.vn.qrzxing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.ViewfinderView;

import java.util.Random;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScannerView;
    private Button btnzxing;
    private Button btnzxing2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA},
                    50); }
        btnzxing2= findViewById(R.id.btnzxing2);
        btnzxing= findViewById(R.id.btnzxing);
        btnzxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCam();
            }
        });
        btnzxing2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCamFlash();
            }
        });
        mScannerView = findViewById(R.id.scannerView);   // Programmatically initialize the scanner view

        setCam();
    }

    private void setCam() {
        mScannerView.clearFocus();
        mScannerView.stopCamera();
        mScannerView.stopCameraPreview();
        mScannerView.destroyDrawingCache();
        mScannerView.setFlash(false);
        mScannerView.startCamera(0);
    }
    private void setCamFlash() {mScannerView.stopCamera();
        mScannerView.stopCameraPreview();
        mScannerView.destroyDrawingCache();
        mScannerView.setFlash(true);
        mScannerView.startCamera(0);
    }


    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result her
        Log.e("sd", rawResult.getText()); // Prints scan results
        Log.e("sd", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
    }

/*
//    private void scanCode() {
//        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
//        intentIntegrator.setCaptureActivity(CaptureAct.class);
//        intentIntegrator.setCameraId(0);
//        intentIntegrator.setOrientationLocked(false);
//        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
//        intentIntegrator.setPrompt("Scaning Code");
//        intentIntegrator.initiateScan();
//
//
//    }
//    */

/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result= IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            if (result.getContents() != null){
                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                result.getContents();
                builder.setMessage(result.getContents());
                builder.setTitle("Scaning Result");
                builder.setNegativeButton("Scan again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        scanCode();
                    }
                });

                builder.setNegativeButton("finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                AlertDialog alertDialog= builder.create();
                alertDialog.show();
            }
            else {
                Toast.makeText(this, "No result", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
*/

}
