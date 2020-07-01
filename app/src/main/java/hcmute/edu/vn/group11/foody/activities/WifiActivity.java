package hcmute.edu.vn.group11.foody.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hcmute.edu.vn.group11.foody.R;

public class WifiActivity extends AppCompatActivity {

    EditText etWifi, etWifiPass;
    Button btnHuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        etWifi = (EditText) findViewById(R.id.etWifi);
        etWifiPass = (EditText) findViewById(R.id.etWifiPass);
        btnHuy = (Button) findViewById(R.id.btnHuy);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        Intent intent = getIntent();
        etWifi.setText(intent.getExtras().getString("wifi"));
        etWifiPass.setText(intent.getExtras().getString("wifipass"));
    }
}