package hcmute.edu.vn.group11.foody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.group11.foody.databases.Database;
import hcmute.edu.vn.group11.foody.entities.Restaurant;

public class MainActivity extends AppCompatActivity {

    ArrayList<Restaurant> lstRes;
    private Button btnProvince;
    Database database;
    public static String province="TP HCM";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDatabase();
        //insertValues();

        btnProvince = (Button) findViewById(R.id.btn_Province);

        btnProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Province.class);
                startActivity(intent);
            }
        });

        lstRes = new ArrayList<>();
        getRestaurants();
        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, lstRes);
        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myAdapter);

    }

    private void createDatabase(){
        database = new Database(this, "foody.sqlite", null,1);
        database.queryData("CREATE TABLE IF NOT EXISTS Restaurant(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(300), description VARCHAR(300), phone VARCHAR(200), wifi VARCHAR(50), wifipass VARCHAR(50),type VARCHAR(50), maxprice VARCHAR(20), minprice VARCHAR(20), image VARCHAR(500), province VARCHAR(20), address VARCHAR(50));");
        database.queryData("CREATE TABLE IF NOT EXISTS Food (Id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(200),image VARCHAR (500), IdQuan INTEGER REFERENCES Restaurant (Id),gia VARCHAR (100));");
    }

    private void insertValues(){
        database.queryData("INSERT INTO Food VALUES (null,'Bún mắm','https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',1)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún đậu mắm tôm','https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',1)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún bò hế', 'https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',1)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì tương đen','https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',1)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì hải sản','https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',1)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì cay','https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',1)");
        database.queryData("INSERT INTO Food VALUES (null,'Cơm tấm','https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',1)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh canh', 'https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',1)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún riêu','https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',1)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún chả', 'https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',1)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh bột lọc', 'https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',1)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh bèo', 'https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',1)");

        database.queryData("INSERT INTO Restaurant VALUES (null,'Bà năm','Ngon bổ rẻ','0123123123','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2018/07/mon-an-binh-dan.jpg'," +
                "'TP HCM','1 Võ Văn Ngân Thủ Đức TP HCM')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Bà năm','Ngon bổ rẻ','0123123123','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2018/07/mon-an-binh-dan.jpg'," +
                "'TP HCM','1 Võ Văn Ngân Thủ Đức TP HCM')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Bà năm','Ngon bổ rẻ','0123123123','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2018/07/mon-an-binh-dan.jpg'," +
                "'TP HCM','1 Võ Văn Ngân Thủ Đức TP HCM')");
    }

    private void getRestaurants(){
        Cursor dataQuan = database.getData("SELECT * FROM Restaurant Where province ='"+province+"';");
        lstRes.clear();
        while (dataQuan.moveToNext()){
            String address = dataQuan.getString(11);
            String province = dataQuan.getString(10);
            String image = dataQuan.getString(9);
            String minprice = dataQuan.getString(8);
            String maxprice = dataQuan.getString(7);
            String type = dataQuan.getString(6);
            String wifipass = dataQuan.getString(5);
            String wifi = dataQuan.getString(4);
            String phone = dataQuan.getString(3);
            String description = dataQuan.getString(2);
            String name = dataQuan.getString(1);
            int id = dataQuan.getInt(0);
            lstRes.add(new Restaurant(id, name, description,  phone,  wifi,  wifipass,  type,  maxprice,  minprice,  image,  province,  address));
        }
    }
}