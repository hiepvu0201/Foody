package hcmute.edu.vn.group11.foody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityRestaurant extends AppCompatActivity {

    private TextView tvResName, tvFoodName, tvDesc;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        tvResName = (TextView) findViewById(R.id.txtResName);
        tvFoodName = (TextView) findViewById(R.id.txtFoodName);
        tvDesc = (TextView) findViewById(R.id.txtDecs);
        img = (ImageView) findViewById(R.id.res_thumbnail);

        // receive data
        Intent intent = getIntent();
        String ResName = intent.getExtras().getString("Restaurant Name");
        String Description = intent.getExtras().getString("Description");
        int image = intent.getExtras().getInt("Thumbnail");

        // setting value
        tvResName.setText(ResName);
        tvDesc.setText(Description);
        img.setImageResource(image);
    }
}