package hcmute.edu.vn.group11.foody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityRestaurant extends AppCompatActivity {

    private TextView tvResName, tvFoodName, tvDesc, tvPhone, tvType, tvPrice, tvAddress;
    private ImageView img;
    Button btnMenu;


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

        // receive data


        // setting value

    }
}