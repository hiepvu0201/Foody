package hcmute.edu.vn.group11.foody;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import hcmute.edu.vn.group11.foody.entities.Food;

public class ActivityRestaurant extends AppCompatActivity {

    private TextView tvResName, tvFoodName, tvDesc, tvPhone, tvType, tvPrice, tvAddress;
    private ImageView img;
    TextView tvMenu;
    ImageButton btnCancel;
    ArrayList<Food> lstFood;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        tvResName = (TextView) findViewById(R.id.tvResName);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        tvType = (TextView) findViewById(R.id.tvType);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        img = (ImageView) findViewById(R.id.res_thumbnail);
        btnCancel = (ImageButton) findViewById(R.id.imgbtnBack);
        tvMenu = (TextView) findViewById(R.id.tvMenu);

        tvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityRestaurant.this, MenuActivity.class);
                startActivity(intent);
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        Intent intent = getIntent();
        tvResName.setText(Objects.requireNonNull(intent.getExtras()).getString("Name"));
        tvPhone.setText(intent.getExtras().getString("phone"));
//        tvWifi.setText(intent.getExtras().getString("wifi"));
//        tvPass.setText(intent.getExtras().getString("wifipass"));
        tvType.setText(intent.getExtras().getString("type"));
        tvPrice.setText((intent.getExtras().getString("minprice")) + " - " + (intent.getExtras().getString("maxprice")));
        tvAddress.setText(intent.getExtras().getString("address"));
//        tvDesc.setText(intent.getExtras().getString("description"));
//        tvFoodName.setText(intent.getExtras().getString("address"));

        int idQuan = intent.getIntExtra("id", 0);

        lstFood = new ArrayList<>();
        getDataFood(idQuan);
        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        FoodRecyclerViewAdapter myAdapter = new FoodRecyclerViewAdapter(this, lstFood);
        myrv.setLayoutManager(new GridLayoutManager(this, 2));
        myrv.setAdapter(myAdapter);


        tvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dial="tel:"+tvPhone.getText();
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
            }
        });
    }

    private void getDataFood(int idQuan){
        Cursor data = MainActivity.database.getData("SELECT * FROM Food Where idQuan ='"+idQuan+"';");
    }
}