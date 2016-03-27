package tech.alvarez.zxingsample;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void write(View view) {
        // CONTACT

        Bundle bundle = new Bundle();
        bundle.putString(ContactsContract.Intents.Insert.NAME, "Daniel Alvarez");
        bundle.putString(ContactsContract.Intents.Insert.PHONE, "77242424");
        bundle.putString(ContactsContract.Intents.Insert.EMAIL, "daniel@alvarez.tech");
//        bundle.putString(ContactsContract.Intents.Insert.POSTAL, "123 Fake St. San Francisco, CA 94102");

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.addExtra("ENCODE_DATA", bundle);
        integrator.shareText(bundle.toString(), "CONTACT_TYPE");

        // LOCATION
/*
        Bundle bundle = new Bundle();
        bundle.putFloat("LAT", 40.829208f);
        bundle.putFloat("LONG", -74.191279f);

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.addExtra("ENCODE_DATA", bundle);
        integrator.shareText(bundle.toString(), "LOCATION_TYPE");
        */

//        IntentIntegrator integrator = new IntentIntegrator(this);
//        integrator.shareText("http://alvarez.tech", "TEXT_TYPE");

        // EMAIL_TYPE, PHONE_TYPE, SMS_TYPE
    }

    public void read(View view) {
        IntentIntegrator integrator = new IntentIntegrator(this);

        integrator.addExtra("SCAN_WIDTH", 800);
        integrator.addExtra("SCAN_HEIGHT", 800);
        integrator.addExtra("PROMPT_MESSAGE", "Busque un código para escanear");

//        integrator.initiateScan(IntentIntegrator.QR_CODE_TYPES);
        integrator.initiateScan();
    }

    public void showDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.scanning_content);
        builder.setMessage(message);
        builder.setPositiveButton(android.R.string.ok, null);
        builder.show();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {

            System.out.println("Información encontrada");
            System.out.println(scanResult.getContents());
            System.out.println(scanResult.getFormatName());

            showDialog(scanResult.getContents());
        }
    }
}
