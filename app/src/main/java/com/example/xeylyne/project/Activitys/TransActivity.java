package com.example.xeylyne.project.Activitys;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.xeylyne.project.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class TransActivity extends AppCompatActivity {
    public Intent OpenMaps;
    public Intent LokasiMaps;
    public Intent OpenBrowser;
    public Intent UrlBrowser;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    String no_telp, harga_lap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans);
        ButterKnife.bind(this);

        Spinner spinner1 = findViewById(R.id.Spinner);
        spinner1.getSelectedItem();
        Intent intent = getIntent();
        no_telp = intent.getStringExtra("no_telp");
        harga_lap = intent.getStringExtra("harga_lap");
    }

    @BindView(R.id.nama1)
    AutoCompleteTextView nama;

    @OnClick(R.id.send)
    public void sendsms(){
        checkForSmsPermission();
    }

    @OnClick(R.id.call)
    public void call3(){
        checkForPhonePermission();
    }

    @OnClick(R.id.call1)
    public void call2(){
        checkForPhonePermission();
    }

    @OnClick(R.id.wa)
    public void wa2(){
        OpenBrowser = new Intent(Intent.ACTION_VIEW);
        OpenBrowser.setData(Uri.parse("https://kirimwa.id/" + no_telp));
        UrlBrowser = Intent.createChooser(OpenBrowser, "Choose a Map App");
        startActivity(OpenBrowser);
    }

    @OnClick(R.id.wa2)
    public void wa22(){
        OpenBrowser = new Intent(Intent.ACTION_VIEW);
        OpenBrowser.setData(Uri.parse("https://kirimwa.id/" + no_telp));
        UrlBrowser = Intent.createChooser(OpenBrowser, "Choose a Map App");
        startActivity(OpenBrowser);
    }


    public void openSms() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title dialog
        alertDialogBuilder.setTitle("Chat Owner?");

        // set pesan dari dialog
        alertDialogBuilder
                // .setMessage("Pilih opsi chat!")
                .setCancelable(false)
                .setPositiveButton("SMS", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        checkForSmsPermission();
                    }
                })

                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        alertDialogBuilder.setNeutralButton("WhatsApps", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                OpenBrowser = new Intent(Intent.ACTION_VIEW);
                OpenBrowser.setData(Uri.parse("https://kirimwa.id/" + no_telp));
                UrlBrowser = Intent.createChooser(OpenBrowser, "Choose a Map App");
                startActivity(OpenBrowser);
            }
        });


        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();

    }


    public void openCall() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title dialog
        alertDialogBuilder.setTitle("Call Owner?");

        // set pesan dari dialog
        alertDialogBuilder
                // .setMessage("Pilih opsi chat!")
                .setCancelable(false)
                .setPositiveButton("Telepon", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        checkForPhonePermission();
                    }
                })

                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        alertDialogBuilder.setNeutralButton("WhatsApp", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                OpenBrowser = new Intent(Intent.ACTION_VIEW);
                OpenBrowser.setData(Uri.parse("https://kirimwa.id/" + no_telp));
                UrlBrowser = Intent.createChooser(OpenBrowser, "Choose a Browser App");
                startActivity(OpenBrowser);
            }
        });


        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();

    }

    public void smsSendMessage() {

        Spinner spinner = (Spinner)findViewById(R.id.Spinner);


        String smsNumber = "smsto:" + no_telp;

        String sms = "Permisi, Saya "+ nama.getText().toString() + " Memesan GOR Dengan, Dari Jam "+spinner.getSelectedItem()+", Dan Harga Rp. "+harga_lap ;

        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);

        smsIntent.setData(Uri.parse(smsNumber));

        smsIntent.putExtra("sms_body", sms);

        if (smsIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(smsIntent);
        } else {
            Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show();
        }
    }

    public void callNumber() {

        // Use format with "tel:" and phone number to create phoneNumber.
        String phoneNumber = String.format("tel: %s", no_telp);
        // Create the intent.
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        // Set the data for the intent as the phone number.
        callIntent.setData(Uri.parse(phoneNumber));
        // If package resolves to an app, check for phone permission,
        // and send intent.
        if (callIntent.resolveActivity(getPackageManager()) != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(callIntent);
        }
    }

    private void checkForSmsPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        } else {
            smsSendMessage();
        }
    }

    private void checkForPhonePermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED) {
            // Permission not yet granted. Use requestPermissions().
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            callNumber();
        }
    }
}
