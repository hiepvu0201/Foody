package hcmute.edu.vn.group11.foody;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class ActivityRestaurant extends AppCompatActivity {

    private TextView tvResName, tvFoodName, tvDesc, tvPhone, tvType, tvPrice, tvAddress;
    private ImageView img;
    Button btnMenu;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        btnMenu = (Button) findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityRestaurant.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        tvResName = (TextView) findViewById(R.id.tvResName);
        tvFoodName = (TextView) findViewById(R.id.tvFoodName);
        tvDesc = (TextView) findViewById(R.id.tvDecs);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        tvType  = (TextView) findViewById(R.id.tvType);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        img = (ImageView) findViewById(R.id.res_thumbnail);


        Intent intent = getIntent();
        tvResName.setText(Objects.requireNonNull(intent.getExtras()).getString("RestaurantName"));
        tvPhone.setText(intent.getExtras().getString("phone"));
//        tvWifi.setText(intent.getExtras().getString("wifi"));
//        tvPass.setText(intent.getExtras().getString("wifipass"));
        tvType.setText(intent.getExtras().getString("type"));
        tvPrice.setText((intent.getExtras().getString("minprice"))+" - "+(intent.getExtras().getString("maxprice")));
        tvAddress.setText(intent.getExtras().getString("address"));
        tvDesc.setText(intent.getExtras().getString("description"));
//        tvFoodName.setText(intent.getExtras().getString("address"));



    }

    private void getDataFood(){
        Cursor data = MainActivity.database.getData("");
    }
}