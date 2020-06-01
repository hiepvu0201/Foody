package hcmute.edu.vn.group11.foody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Restaurant> lstRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstRes = new ArrayList<>();
        lstRes.add(new Restaurant("Dì Hai", "Bún Cá Rô", "Description Res", R.drawable.a));
        lstRes.add(new Restaurant("Bà Năm", "Xôi Xoài", "Description Res", R.drawable.b));
        lstRes.add(new Restaurant("Chú Bảy", "Phở Bò", "Description Res", R.drawable.c));
        lstRes.add(new Restaurant("Bà Tư", "Phở Gà", "Description Res", R.drawable.d));
        lstRes.add(new Restaurant("Ông Sáu", "Cơm Gà", "Description Res", R.drawable.e));
        lstRes.add(new Restaurant("Ông Tám", "Kem Dừa", "Description Res", R.drawable.f));
        lstRes.add(new Restaurant("Bà Bé", "Đậu Hũ", "Description Res", R.drawable.a));
        lstRes.add(new Restaurant("Cô Lan", "Bún Đậu Mắm Tôm", "Description Res", R.drawable.e));
        lstRes.add(new Restaurant("Dì Hương", "Xôi Mặn", "Description Res", R.drawable.f));
        lstRes.add(new Restaurant("Sáu Hô", "Bánh Mì", "Description Res", R.drawable.a));
        lstRes.add(new Restaurant("Bảy Móm", "Súp Cua", "Description Res", R.drawable.c));
        lstRes.add(new Restaurant("Hùng Tóc Xoăn", "Me Thái", "Description Res", R.drawable.d));

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, lstRes);
        myrv.setLayoutManager(new GridLayoutManager(this,3));
        myrv.setAdapter(myAdapter);
    }
}