package hcmute.edu.vn.group11.foody.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import hcmute.edu.vn.group11.foody.R;
import hcmute.edu.vn.group11.foody.adapter.FoodRecyclerViewAdapter;
import hcmute.edu.vn.group11.foody.adapter.RestaurantRecyclerViewAdapter;
import hcmute.edu.vn.group11.foody.databases.Database;
import hcmute.edu.vn.group11.foody.entities.Food;
import hcmute.edu.vn.group11.foody.entities.Restaurant;

public class RestaurantActivity extends AppCompatActivity {

    private TextView tvResName, tvPhone, tvType, tvPrice, tvAddress;
    TextView tvMenu, tvAddWifi;
    ImageButton btnCancel, btnMenu;
    ArrayList<Food> lstFood;
    public static int idQuan;

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
        btnCancel = (ImageButton) findViewById(R.id.imgbtnBack);
        tvMenu = (TextView) findViewById(R.id.tvMenu);
        tvAddWifi = (TextView) findViewById(R.id.tvAddWifi);
        btnMenu = (ImageButton) findViewById(R.id.icMenu);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        tvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantActivity.this, MenuActivity.class);
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

        tvAddWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantActivity.this, WifiActivity.class);
                startActivity(intent);
            }
        });

        tvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dial="tel:"+tvPhone.getText();
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
            }
        });

        tvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantActivity.this, MapsActivity.class);
                startActivity(intent);
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

        idQuan = intent.getIntExtra("id", 0);
        lstFood = new ArrayList<>();
        getFoods();
        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_food_id);
        FoodRecyclerViewAdapter myAdapter = new FoodRecyclerViewAdapter(this, lstFood);
        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myAdapter);

    }

    private void getFoods(){
        Cursor dataFood = MainActivity.database.getData("SELECT * FROM Food Where IdQuan = "+idQuan);
        lstFood.clear();
        while (dataFood.moveToNext()){
            int idQuan = dataFood.getInt(4);
            String gia = dataFood.getString(3);
            String image = dataFood.getString(2);
            String name = dataFood.getString(1);
            int id = dataFood.getInt(0);
            lstFood.add(new Food(id, name, image,  gia, idQuan));
        }
    }
}
