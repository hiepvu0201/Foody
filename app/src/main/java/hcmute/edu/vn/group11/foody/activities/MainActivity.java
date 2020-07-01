package hcmute.edu.vn.group11.foody.activities;

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

import hcmute.edu.vn.group11.foody.R;
import hcmute.edu.vn.group11.foody.adapter.RestaurantRecyclerViewAdapter;
import hcmute.edu.vn.group11.foody.databases.Database;
import hcmute.edu.vn.group11.foody.entities.Restaurant;

public class MainActivity extends AppCompatActivity {

    ArrayList<Restaurant> lstRes;
    private Button btnProvince;
    public static Database database;
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
                Intent intent = new Intent(MainActivity.this, ProvinceActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        province=intent.getStringExtra("province");

        if(province==null||province==""){
            province="TP HCM";
        }
        btnProvince.setText(province);
        lstRes = new ArrayList<>();
        getRestaurants();
        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_res_id);
        RestaurantRecyclerViewAdapter myAdapter = new RestaurantRecyclerViewAdapter(this, lstRes);
        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myAdapter);

    }

    private void createDatabase(){
        database = new Database(this, "foody.sqlite", null,1);
        database.queryData("CREATE TABLE IF NOT EXISTS Restaurant(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(300), description VARCHAR(300), phone VARCHAR(200), wifi VARCHAR(50), wifipass VARCHAR(50),type VARCHAR(50), maxprice VARCHAR(20), minprice VARCHAR(20), image VARCHAR(500), province VARCHAR(20), address VARCHAR(200));");
        database.queryData("CREATE TABLE IF NOT EXISTS Food (Id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(200),image VARCHAR (500), gia VARCHAR (100), IdQuan INTEGER REFERENCES Restaurant (Id));");
    }

    private void insertValues(){
        database.queryData("INSERT INTO Food VALUES (null,'Bún mắm','https://hocnauan.edu.vn/wp-content/uploads/2018/06/suc-hut-am-thuc-chau-a.jpg','25000vnd',1)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún đậu mắm tôm','https://hocnauan.edu.vn/wp-content/uploads/2019/01/sui-cao.jpg','25000vnd',1)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún bò hế', 'https://hocnauan.edu.vn/wp-content/uploads/2019/01/Tteokguk.jpg','25000vnd',1)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì tương đen','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Yu-Sheng.jpg','25000vnd',1)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì hải sản','https://hocnauan.edu.vn/wp-content/uploads/2019/01/lap.jpg','25000vnd',3)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì cay','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Khao-chae-thai-lan.jpg','25000vnd',3)");
        database.queryData("INSERT INTO Food VALUES (null,'Cơm tấm','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Sushi-Sashimi.jpg','25000vnd',2)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh canh', 'https://hocnauan.edu.vn/wp-content/uploads/2019/01/Bebek-betutu.jpg','25000vnd',2)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún riêu','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Tikoy.jpg','25000vnd',2)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún chả', 'https://hocnauan.edu.vn/wp-content/uploads/2018/08/chao-ech-singapore.jpg','25000vnd',2)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh bột lọc', 'https://hocnauan.edu.vn/wp-content/uploads/2018/08/so-che-thit-ech.jpg','25000vnd',3)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh bèo', 'https://hocnauan.edu.vn/wp-content/uploads/2018/08/thit-ech-xao-keo-lai.jpg','25000vnd',3)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún mắm','https://hocnauan.edu.vn/wp-content/uploads/2016/02/ga-chien-kieu-an-do.jpg','25000vnd',1)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún đậu mắm tôm','https://hocnauan.edu.vn/wp-content/uploads/2018/06/ha-cao-mon-ngon-nguoi-hoa.jpg','25000vnd',1)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún bò hế', 'https://hocnauan.edu.vn/wp-content/uploads/2014/05/ga-dong-tao-xao-sa-ot.jpg','25000vnd',1)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì tương đen','https://hocnauan.edu.vn/wp-content/uploads/2016/06/goi-ngo-sen-tom-thit-1.jpg','25000vnd',1)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì hải sản','https://hocnauan.edu.vn/wp-content/uploads/2019/01/mon-ngon-dai-tiec-tat-nien-cuoi-nam.jpg','25000vnd',3)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì cay','https://hocnauan.edu.vn/wp-content/uploads/2018/09/bo-ap-chao-xot-tieu-xanh.jpg','25000vnd',3)");
        database.queryData("INSERT INTO Food VALUES (null,'Cơm tấm','https://hocnauan.edu.vn/wp-content/uploads/2018/09/tom-hap.jpg','25000vnd',2)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh canh', 'https://hocnauan.edu.vn/wp-content/uploads/2018/09/sup-hai-san.jpg','25000vnd',2)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún riêu','https://hocnauan.edu.vn/wp-content/uploads/2018/08/ga-bo-xoi-mon-ngon-ai-cung-thich.jpg','25000vnd',4)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún chả', 'https://hocnauan.edu.vn/wp-content/uploads/2018/08/mon-ga-hap-mam-nhi.jpg','25000vnd',4)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh bột lọc', 'https://hocnauan.edu.vn/wp-content/uploads/2016/12/cang-cua-bach-hoa.jpg','25000vnd',5)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh bèo', 'https://hocnauan.edu.vn/wp-content/uploads/2016/09/vit-rang-muoi.jpg','25000vnd',6)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún mắm','https://hocnauan.edu.vn/wp-content/uploads/2016/06/hoa-thien-ly-xao-mam.jpg','25000vnd',7)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún đậu mắm tôm','https://hocnauan.edu.vn/wp-content/uploads/2016/06/ga-nuong-chao-do.jpg','25000vnd',8)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún bò hế', 'https://hocnauan.edu.vn/wp-content/uploads/2015/11/ca-chem-chien-xu-sot-kem-bo.jpg','25000vnd',8)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì tương đen','https://hocnauan.edu.vn/wp-content/uploads/2015/05/mon-goi-buoi.jpg','25000vnd',8)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì hải sản','https://hocnauan.edu.vn/wp-content/uploads/2015/04/cach-lam-muc-sot-cay-gion.jpg','25000vnd',9)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì cay','https://hocnauan.edu.vn/wp-content/uploads/2015/04/lau-vit-la-tia-to.jpg','25000vnd',9)");
        database.queryData("INSERT INTO Food VALUES (null,'Cơm tấm','https://hocnauan.edu.vn/wp-content/uploads/2015/01/thit-dong-gio-thu.jpg','25000vnd',9)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh canh', 'https://hocnauan.edu.vn/wp-content/uploads/2014/10/com-ga-hoang-kim.jpg','25000vnd',10)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún riêu','https://hocnauan.edu.vn/wp-content/uploads/2014/06/vit-nuong-chao.jpg','25000vnd',10)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún chả', 'https://hocnauan.edu.vn/wp-content/uploads/2014/04/thit-xien-nuong.jpg','25000vnd',10)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh bột lọc', 'https://hocnauan.edu.vn/wp-content/uploads/2014/04/goi-thit-bo-tron-rau.jpg','25000vnd',11)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh bèo', 'https://hocnauan.edu.vn/wp-content/uploads/2014/04/goi-kho-qua-tom.jpg','25000vnd',12)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún mắm','https://hocnauan.edu.vn/wp-content/uploads/2014/03/muc-ong-hap-gung.jpg','25000vnd',12)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún đậu mắm tôm','https://hocnauan.edu.vn/wp-content/uploads/2014/03/ca-nuc-hap-bo-toi.jpg','25000vnd',12)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún bò hế', 'https://hocnauan.edu.vn/wp-content/uploads/2014/03/com-cuon-xuc-xich-chien.jpg','25000vnd',13)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì tương đen','https://hocnauan.edu.vn/wp-content/uploads/2014/03/com-cuon-xuc-xich-chien.jpg','25000vnd',13)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì hải sản','https://hocnauan.edu.vn/wp-content/uploads/2014/02/gan-bo-xao-ngu-vi.jpg','25000vnd',13)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì cay','https://hocnauan.edu.vn/wp-content/uploads/2013/09/ga-xe-phay-tron-le.jpg','25000vnd',13)");
        database.queryData("INSERT INTO Food VALUES (null,'Cơm tấm','https://hocnauan.edu.vn/wp-content/uploads/2013/09/mon-nhan-boc-tom.jpg','25000vnd',14)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh canh', 'https://hocnauan.edu.vn/wp-content/uploads/2013/09/hao-sua-nuong-toi-1.jpg','25000vnd',14)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún riêu','https://hocnauan.edu.vn/wp-content/uploads/2013/09/tom-tam-dua-nao.jpg','25000vnd',14)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún chả', 'https://hocnauan.edu.vn/wp-content/uploads/2013/09/canh-ga-rim-giam-do-chua.jpg','25000vnd',14)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh bột lọc', 'https://hocnauan.edu.vn/wp-content/uploads/2013/09/nom-bach-tuoc-1.jpg','25000vnd',15)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh bèo', 'https://hocnauan.edu.vn/wp-content/uploads/2013/09/mon-sup-bo-vien.jpg','25000vnd',16)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún mắm','https://hocnauan.edu.vn/wp-content/uploads/2013/09/chem-chep-nuong.jpg','25000vnd',16)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún đậu mắm tôm','https://hocnauan.edu.vn/wp-content/uploads/2013/09/diem-diep-nuong-mo-hanh-1.jpg','25000vnd',16)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún bò hế', 'https://hocnauan.edu.vn/wp-content/uploads/2013/09/nam-bao-ngu-xao-me.jpg','25000vnd',16)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì tương đen','https://hocnauan.edu.vn/wp-content/uploads/2013/09/cai-xanh-cuon-tom-thit.jpg','25000vnd',17)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì hải sản','https://hocnauan.edu.vn/wp-content/uploads/2013/09/bong-bi-nhoi-thit-chien.jpg','25000vnd',17)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì cay','https://hocnauan.edu.vn/wp-content/uploads/2013/09/vit-nau-chao.jpg','25000vnd',17)");
        database.queryData("INSERT INTO Food VALUES (null,'Cơm tấm','https://hocnauan.edu.vn/wp-content/uploads/2013/09/ca-ri-ga.jpg','25000vnd',17)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh canh', 'https://hocnauan.edu.vn/wp-content/uploads/2013/09/chim-cut-uop-chao-chien-vang.jpg','25000vnd',18)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún riêu','https://hocnauan.edu.vn/wp-content/uploads/2013/09/ga-bo-xoi-ngon.jpg','25000vnd',19)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún chả', 'https://hocnauan.edu.vn/wp-content/uploads/2013/09/nem-nuong-an-ngon.jpg','25000vnd',20)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh bột lọc', 'https://hocnauan.edu.vn/wp-content/uploads/2013/09/nam-bao-ngu-xao-me.jpg','25000vnd',20)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh bèo', 'https://hocnauan.edu.vn/wp-content/uploads/2013/09/cai-xanh-cuon-tom-thit.jpg','25000vnd',20)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún mắm','https://hocnauan.edu.vn/wp-content/uploads/2013/09/bong-bi-nhoi-thit-chien.jpg','25000vnd',21)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún đậu mắm tôm','https://hocnauan.edu.vn/wp-content/uploads/2018/08/gio-heo-gia-cay-ngon.jpg','25000vnd',21)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún bò hế', 'https://hocnauan.edu.vn/wp-content/uploads/2018/10/trinh-bay-mon-pho.jpg','25000vnd',21)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì tương đen','https://hocnauan.edu.vn/wp-content/uploads/2018/07/banh-cuon-thanh-tri.jpg','25000vnd',22)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì hải sản','https://hocnauan.edu.vn/wp-content/uploads/2015/12/bun-thang-ha-noi.jpg','25000vnd',23)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì cay','https://hocnauan.edu.vn/wp-content/uploads/2013/09/cach-lam-banh-khuc-ha-noi.jpg','25000vnd',24)");
        database.queryData("INSERT INTO Food VALUES (null,'Cơm tấm','https://hocnauan.edu.vn/wp-content/uploads/2019/01/ban-banh-chung-cung-tet.jpg','25000vnd',25)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh canh', 'https://hocnauan.edu.vn/wp-content/uploads/2019/01/dua-kieu.jpg','25000vnd',25)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún riêu','https://hocnauan.edu.vn/wp-content/uploads/2019/01/mam-co-cung-giao-thua.jpg','25000vnd',25)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún chả', 'https://hocnauan.edu.vn/wp-content/uploads/2019/01/mut-gung.jpg','25000vnd',26)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh bột lọc', 'https://hocnauan.edu.vn/wp-content/uploads/2019/01/mut-mang-cau-xiem.jpg','25000vnd',27)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh bèo', 'https://hocnauan.edu.vn/wp-content/uploads/2019/01/lap-xuong.jpg','25000vnd',27)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún mắm','https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',28)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún đậu mắm tôm','https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',28)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún bò hế', 'https://hocnauan.edu.vn/wp-content/uploads/2019/01/ban-banh-chung-cung-tet.jpg','25000vnd',29)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì tương đen','https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',30)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì hải sản','https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',30)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì cay','https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',31)");
        database.queryData("INSERT INTO Food VALUES (null,'Cơm tấm','https://hocnauan.edu.vn/wp-content/uploads/2019/01/ban-banh-chung-cung-tet.jpg','25000vnd',32)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh canh', 'https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',32)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún riêu','https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',33)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún chả', 'https://hocnauan.edu.vn/wp-content/uploads/2014/02/gan-bo-xao-ngu-vi.jpg','25000vnd',34)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh bột lọc', 'https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',35)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh bèo', 'https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',36)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún mắm','https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',37)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún đậu mắm tôm','https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',37)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún bò hế', 'https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',37)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì tương đen','https://hocnauan.edu.vn/wp-content/uploads/2014/02/gan-bo-xao-ngu-vi.jpg','25000vnd',37)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì hải sản','https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',38)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì cay','https://hocnauan.edu.vn/wp-content/uploads/2013/09/nem-nuong-an-ngon.jpg','25000vnd',38)");
        database.queryData("INSERT INTO Food VALUES (null,'Cơm tấm','https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',38)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh canh', 'https://hocnauan.edu.vn/wp-content/uploads/2013/09/nem-nuong-an-ngon.jpg','25000vnd',38)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún riêu','https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',39)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún chả', 'https://hocnauan.edu.vn/wp-content/uploads/2014/02/gan-bo-xao-ngu-vi.jpg','25000vnd',39)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh bột lọc', 'https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',39)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh bèo', 'https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',39)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún mắm','https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',39)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún đậu mắm tôm','https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',40)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún bò hế', 'https://hocnauan.edu.vn/wp-content/uploads/2014/02/gan-bo-xao-ngu-vi.jpg','25000vnd',40)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì tương đen','https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',40)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì hải sản','https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',40)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì cay','https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',40)");
        database.queryData("INSERT INTO Food VALUES (null,'Cơm tấm','https://hocnauan.edu.vn/wp-content/uploads/2013/09/nem-nuong-an-ngon.jpg','25000vnd',41)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh canh', 'https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',42)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún riêu','https://hocnauan.edu.vn/wp-content/uploads/2014/02/gan-bo-xao-ngu-vi.jpg','25000vnd',42)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún chả', 'https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',42)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh bột lọc', 'https://hocnauan.edu.vn/wp-content/uploads/2015/12/bun-thang-ha-noi.jpg','25000vnd',42)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh bèo', 'https://cdn.tgdd.vn/Files/2018/06/21/1096777/cach-nau-bun-mam-thom-ngon-khong-khac-gi-ngoai-hang.jpg','25000vnd',43)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún mắm','https://hocnauan.edu.vn/wp-content/uploads/2015/12/bun-thang-ha-noi.jpg','25000vnd',44)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún đậu mắm tôm','https://dulichkhampha24.com/wp-content/uploads/2020/04/quan-an-ngon-quan-10-sai-gon-1.jpg','25000vnd',44)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún bò hế', 'https://ngonngon.net/wp-content/uploads/2019/01/20.-B%C3%A1nh-canh-gh%E1%BA%B9-69-%C3%94-Ch%E1%BB%A3-D%E1%BB%ABa-%C4%90%E1%BB%91ng-%C4%90a-30k.jpg','25000vnd',44)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì tương đen','https://mocquan.vn/wp-content/uploads/2018/09/moc-quan-banner-thuc-don-900x595.jpg','25000vnd',44)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì hải sản','https://blog.traveloka.com/source/uploads/sites/9/2017/07/mon-an-ngon-sai-gon-xien-que-nuong-chien.jpg','25000vnd',44)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì cay','https://cdn.eva.vn/upload/2-2020/images/2020-06-23/nhung-mon-thit-vit-nhin-la-muon-an-ngay-chi-em-co-the-lam-cho-tet-doan-ngo-5-1592884775-106-width640height480.jpg','25000vnd',44)");
        database.queryData("INSERT INTO Food VALUES (null,'Cơm tấm','https://cdn.eva.vn/upload/2-2020/images/2020-06-23/nhung-mon-thit-vit-nhin-la-muon-an-ngay-chi-em-co-the-lam-cho-tet-doan-ngo-4-1592884775-819-width874height675.jpg','25000vnd',45)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh canh', 'https://hocnauan.edu.vn/wp-content/uploads/2018/10/Hai-san-tot-cho-mau.jpg','25000vnd',46)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún riêu','https://hocnauan.edu.vn/wp-content/uploads/2018/10/Hai-san-tot-cho-mau.jpg','25000vnd',47)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún chả', 'https://hocnauan.edu.vn/wp-content/uploads/2014/05/ga-dong-tao-xao-sa-ot.jpg','25000vnd',47)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh bột lọc', 'https://hocnauan.edu.vn/wp-content/uploads/2017/05/cac-mon-ca.jpg','25000vnd',47)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh bèo', 'https://hocnauan.edu.vn/wp-content/uploads/2018/05/chao-hat-de.jpg','25000vnd',47)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún mắm','https://hocnauan.edu.vn/wp-content/uploads/2018/05/rau-qua-sach.jpg','25000vnd',47)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún đậu mắm tôm','https://hocnauan.edu.vn/wp-content/uploads/2018/05/ca-hoi.jpg','25000vnd',48)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún bò hế', 'https://hocnauan.edu.vn/wp-content/uploads/2018/05/canh-muop-huong.jpg','25000vnd',48)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì tương đen','https://hocnauan.edu.vn/wp-content/uploads/2018/07/com-gao-luc-mui-me.jpg','25000vnd',48)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì hải sản','c','25000vnd',49)");
        database.queryData("INSERT INTO Food VALUES (null,'Mì cay','https://hocnauan.edu.vn/wp-content/uploads/2018/11/mon-an-ngua-tieu-duong.jpg','25000vnd',49)");
        database.queryData("INSERT INTO Food VALUES (null,'Cơm tấm','https://hocnauan.edu.vn/wp-content/uploads/2019/10/chao-to-yen-hat-sen.jpg','25000vnd',49)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh canh', 'https://hocnauan.edu.vn/wp-content/uploads/2018/08/thuc-pham-thuc-duong-theo-mua.jpg','25000vnd',49)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún riêu','https://hocnauan.edu.vn/wp-content/uploads/2018/08/an-thuc-duoc-tu-gao-kut.jpg','25000vnd',50)");
        database.queryData("INSERT INTO Food VALUES (null,'Bún chả', 'https://hocnauan.edu.vn/wp-content/uploads/2018/08/mon-an-thuc-duong.jpg','25000vnd',50)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh bột lọc', 'https://hocnauan.edu.vn/wp-content/uploads/2018/06/ha-cao-mon-ngon-nguoi-hoa.jpg','25000vnd',50)");
        database.queryData("INSERT INTO Food VALUES (null,'Bánh bèo', 'https://hocnauan.edu.vn/wp-content/uploads/2014/02/gan-bo-xao-ngu-vi.jpg','25000vnd',50)");

        database.queryData("INSERT INTO Restaurant VALUES (null,'Bà năm','Ngon bổ rẻ','0123123123','khongco','khongcho','quán nước','100k','20k','https://travelgear.vn/blog/wp-content/uploads/2019/09/banh-canh-nha-trang.jpg'," +
                "'TP HCM','1 Võ Văn Ngân Thủ Đức TP HCM')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Bà năm','Ngon bổ rẻ','0123123123','khongco','khongcho','quán nước','100k','20k','https://annamtourist.com/media/uf/bai-201/tat-tan-tat-cac-dia-diem-an-uong-ngon-tai-nha-trang-an-nam-tour.jpg'," +
                "'TP HCM','1 Võ Văn Ngân Thủ Đức TP HCM')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Bà năm','Ngon bổ rẻ','0123123123','khongco','khongcho','quán nước','100k','20k','https://daotaobeptruong.vn/wp-content/uploads/2020/04/mon-ngon-moi-ngay.jpg'," +
                "'TP HCM','1 Võ Văn Ngân Thủ Đức TP HCM')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Bà năm','Ngon bổ rẻ','0123123123','khongco','khongcho','quán nước','100k','20k','https://icdn.dantri.com.vn/thumb_w/640/2018/7/16/photo-1-153169977494670128357.jpg'," +
                "'An Giang','1 Võ Văn Ngân Thủ Đức TP HCM')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Bà Sáu Sáu','Ngon bổ rẻ','0892727222','khongco','khongcho','quán nước','100k','20k','https://images.foody.vn/res/g1/4452/prof/s640x400/foody-mobile-h2t-jpg-978-635850964187232369.jpg'," +
                "'An Giang','2 Lê Văn Việt Quận 9 TP HCM')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng MinJun','Đầy đủ dinh dưỡng','0987282722','khongco','khongcho','quán nước','400k','50k','https://images.foody.vn/res/g16/155253/prof/s640x400/foody-upload-api-foody-mobile-foody-mobile-12-jpg--190620141110.jpg'," +
                "'Bà Rịa-Vũng Tàu','135 đường Nam Kỳ Khởi Nghĩa TP HCM')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng SơnPhan','Các món ăn hảo hạng','0987626222','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/sui-cao.jpg'," +
                "'Bà Rịa-Vũng Tàu','Số 1 Công xã Paris, phường Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng HiepVu','Ngon hơn rẻ','0656552272','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/lap.jpg'," +
                "'Bắc Giang','han Bội Châu –Lê Thánh Tôn – Phan Chu Trinh')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng TriDuc','Đảm bảo chất lượng','0252625222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Sushi-Sashimi.jpg'," +
                "'Bắc Giang','Số 2 đường Nguyễn Bỉnh Khiêm, P.Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Vui Ve','Đảm bảo chất lượng','0726262222','khongco','khongcho','quán nước','100k','20k','hhttps://hocnauan.edu.vn/wp-content/uploads/2019/01/Tikoy.jpg'," +
                "'Bắc Kạn','123 nguyễn chí thanh,Phường 9')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Com Cam Nhung','Đảm bảo chất lượng','0782726222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Yu-Sheng.jpg'," +
                "'Bắc Kạn','Số 84A Bà Huyện Thanh Quan – phường 9')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Com Thuan Phat','Đảm bảo chất lượng','0765676727','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/02/cach-lam-cha-ran.jpg'," +
                "'Bạc Liêu','Số 55A – đường 3/2 – phường 119')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Com Tao Thao','Đảm bảo chất lượng','0762726222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2013/09/trung-ran-boc-com-chien-toi.jpg'," +
                "'Bạc Liêu','170 Trần Phú – phường 9')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Com PhatGiao','Đảm bảo chất lượng','0652525262','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2016/03/mon-sup-cua.jpg'," +
                "'Bắc Ninh','159 Khánh Hội – phường 3')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Lẩu','Đảm bảo chất lượng','0986526222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2016/03/lau-mang-chua-ca-lang.jpg'," +
                "'Bắc Ninh','Lầu 4, SC Vivo City – 1058 Nguyễn Văn Linh')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Súp Cuaaa','Đảm bảo chất lượng','0656252522','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2016/03/mon-sup-cua.jpg'," +
                "'Bến Tre','Lầu 3, Pico Plaza – 20 Cộng Hòa')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Sot Me','Đảm bảo chất lượng','0656252522','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2015/12/nau-muc-sot-me-ngon-dai.jpg'," +
                "'Bến Tre','Lầu 3, Pearl Plaza – 561 Điện Biên Phủ, phường 25')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Bo nuong','Đảm bảo chất lượng','0656252522','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2015/11/bo-nuong-mo-chai.jpg'," +
                "'Bình Định','TTTM Gigamall – 242 Phạm Văn Đồng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Lau Cua','Đảm bảo chất lượng','076272622','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2015/11/mon-lau-cua-dong-hai-san-1.jpg'," +
                "'Bình Định','Thảo Điền Pearl – 12 Quốc Hương')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Bun Bo heu','Đảm bảo chất lượng','076272622','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2015/06/bun-bo-hue.jpg'," +
                "'Bình Dương','Số 30 Đuờng Số 1, Khu TTHC')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Thịt Cuộng','Đảm bảo chất lượng','8726272622','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2015/05/mon-thit-heo-cuon-ngong-toi.jpg'," +
                "'Bình Dương','115/1C KP.1A An Phú, Thuận An')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nấm bào ngư','Đảm bảo chất lượng','076262622','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/10/nam-bao-ngu-chien.jpg'," +
                "'Bình Phước','Ngã thư thùng thơ, xã an tây, huyện bến cát')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Bun Moc','Đảm bảo chất lượng','2982726222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/08/cach-lam-bun-moc.jpg'," +
                "'Bình Phước','08 Cách Mạng Tháng 8, Kp Thạnh Lợi')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nuôi Xao','Đảm bảo chất lượng','0872622222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/04/nui-xao-thom-ngon.jpg'," +
                "'TP HCM','Tổ 2, ấp Tân Lợi ,xã Đất Cuốc')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Yến','Đảm bảo chất lượng','0987654445','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/03/che-yen-trai-cay.jpg'," +
                "'Bình Thuận','Số 75 đường NC')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Canh Dinh lang','Đảm bảo chất lượng','0987776662','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2013/09/canh-dinh-lang-nau-suon-non.jpg'," +
                "'Bình Thuận','Khu phố phú nghị phường hòa lợi')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'CUhu','Đảm bảo chất lượng','0766262622','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2015/07/ca-tam-nau-mang-chua.jpg'," +
                "'Cà Mau','10/1 Thủ Khoa Huân')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Huhu','Đảm bảo chất lượng','0666726222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2015/06/bun-bo-hue.jpg'," +
                "'Cà Mau','33/1 kp Đồng An 3, Bình Hòa')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Tom Nuong','Đảm bảo chất lượng','0555655555','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2015/10/tom-nuong-muoi-ot.jpg'," +
                "'Cao Bằng','54 văn công khai')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'HU Goi','Đảm bảo chất lượng','0988882822','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2016/06/goi-ngo-sen-tom-thit-1.jpg'," +
                "'Cao Bằng','119 đường tự do kp2')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Hic Ga','Đảm bảo chất lượng','0787777777','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2018/09/luoc-ga-luoc.jpg'," +
                "'Đắk Lắk','191/2A Ấp Đông An')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Gia Gia','Đảm bảo chất lượng','02746515588','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/mon-ngon-dai-tiec-tat-nien-cuoi-nam.jpg'," +
                "'Đắk Lắk','Kp7, tt. Uyên Hưng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Com an nhanh','Đảm bảo chất lượng','02746273446','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2018/11/lau-kim-chi-hai-san.jpg'," +
                "'Đắk Nông','Lữ đoàn quân đội 550')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Com an nhanh','Đảm bảo chất lượng','02746273446','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2018/09/bo-ap-chao-xot-tieu-xanh.jpg'," +
                "'Đắk Nông','2/1 đường đông nhì, trắng lái thiêu')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Com siu pham','Đảm bảo chất lượng','02746274247','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2018/08/ga-bo-xoi-mon-ngon-ai-cung-thich.jpg'," +
                "'Điện Biên','Đường D1 khu công nghiệp')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Com Nhat Nhoa','Đảm bảo chất lượng','02742246333','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2018/08/mon-ga-hap-mam-nhi.jpg'," +
                "'Điện Biên','163 QL 13, Mỹ Phước')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Com siu cay','Đảm bảo chất lượng','02746554666','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2016/12/cang-cua-bach-hoa.jpg'," +
                "'Đồng Nai','747b long bình- khánh bình')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'An Nhanh','Đảm bảo chất lượng','02746556333','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2016/06/hoa-thien-ly-xao-mam.jpg'," +
                "'Đồng Nai','Đường DT741 số nhà 251 khu phố 5')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Com Tien Dat','Đảm bảo chất lượng','02746552288','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2016/06/ga-nuong-chao-do.jpg'," +
                "'Đồng Tháp','505 Lê Hồng Phong ,phường Phú Hòa')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Com trang','Đảm bảo chất lượng','02746515599','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2015/11/ca-chem-chien-xu-sot-kem-bo.jpg'," +
                "'Đồng Tháp','488 dlbd . tan dinh')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Goi22','Đảm bảo chất lượng','0869578038','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2015/11/ca-chem-chien-xu-sot-kem-bo.jpg'," +
                "'Gia Lai','11 nguyễn trãi kp đông tân')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Goi99','Đảm bảo chất lượng','02746559900','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2015/04/cach-lam-muc-sot-cay-gion.jpg'," +
                "'Gia Lai','số 19 khu phố bình hoà 2')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'HaGiang 29','Đảm bảo chất lượng','0869578039','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2015/04/lau-vit-la-tia-to.jpg'," +
                "'Hà Giang','320A Nguyễn Thị Minh Khai')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'HaGiang 30','Đảm bảo chất lượng','0869579189','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2015/01/thit-dong-gio-thu.jpg'," +
                "'Hà Giang','Số 19, Bình Hòa1')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Hanam 30','Đảm bảo chất lượng','02746506688','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/06/vit-nuong-chao.jpg'," +
                "'Hà Nam','28/35 đại lộ độc lập')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Hanam 30','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/05/nom-su-hao.jpg'," +
                "'Hà Nam','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'HaTinh 30','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/05/nom-su-hao.jpg'," +
                "'Hà Tĩnh','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'hatinh 30','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/05/nom-su-hao.jpg'," +
                "'Hà Tĩnh','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'haiDUong1','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://tamnhinrong.org/163005-banh-xeo.jpg'," +
                "'Hải Dương','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'HaiDUong','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://daotaobeptruong.vn/wp-content/uploads/2020/04/mon-ngon-moi-ngay.jpg'," +
                "'Hải Dương','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'haugaing77','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/05/nom-su-hao.jpg'," +
                "'Hậu Giang','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'HauGiang99','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/05/nom-su-hao.jpg'," +
                "'Hậu Giang','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'HCm992','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/05/nom-su-hao.jpg'," +
                "'TP HCM','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'ComBacHaHCm','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://ngonngon.net/wp-content/uploads/2019/01/20.-B%C3%A1nh-canh-gh%E1%BA%B9-69-%C3%94-Ch%E1%BB%A3-D%E1%BB%ABa-%C4%90%E1%BB%91ng-%C4%90a-30k.jpg'," +
                "'TP HCM','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'ComTaHCM20','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://content.baydep.vn/Upload/Images/Content/Hai-phong/monngon/monngonHP11.png'," +
                "'TP HCM','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'TinhHCM','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://ngonngon.net/wp-content/uploads/2019/01/20.-B%C3%A1nh-canh-gh%E1%BA%B9-69-%C3%94-Ch%E1%BB%A3-D%E1%BB%ABa-%C4%90%E1%BB%91ng-%C4%90a-30k.jpg'," +
                "'TP HCM','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'ComQuanHCM','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://dulichkhampha24.com/wp-content/uploads/2020/04/quan-an-ngon-quan-10-sai-gon-1.jpg'," +
                "'TP HCM','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'HaNoi98','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/05/nom-su-hao.jpg'," +
                "'Hà Nội','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'haNoi99','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/05/nom-su-hao.jpg'," +
                "'Hà Nội','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'haiPhong99','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/05/nom-su-hao.jpg'," +
                "'Hải Phòng','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'haiPhongCom','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/05/nom-su-hao.jpg'," +
                "'Hải Phòng','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Danang 30','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/05/nom-su-hao.jpg'," +
                "'Đà Nẵng','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'DaNang','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/05/nom-su-hao.jpg'," +
                "'Đà Nẵng','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Cần Thơ 30','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/05/nom-su-hao.jpg'," +
                "'Cần Thơ','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'CanTho 30','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/05/nom-su-hao.jpg'," +
                "'Cần Thơ','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Phú Yên30','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/05/nom-su-hao.jpg'," +
                "'Phú Yên','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Phú Yên1','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/05/nom-su-hao.jpg'," +
                "'Phú Yên','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Yên Bái 90','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/05/nom-su-hao.jpg'," +
                "'Yên Bái','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Yên bái 92','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/05/nom-su-hao.jpg'," +
                "'Yên Bái','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'VinhPhuc0','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://images.foody.vn/res/g25/244395/prof/s640x400/foody-mobile-14002583_17477573321-898-636065928349369521.jpg'," +
                "'Vĩnh Phúc','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'VinhPhuc1','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://i.pinimg.com/originals/48/c3/b2/48c3b21a7729c7cdea1011cda75846b1.jpg'," +
                "'Vĩnh Phúc','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'VinhLong2','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2018/10/trinh-bay-mon-pho.jpg'," +
                "'Vĩnh Long','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'VinhLong1','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/02/gan-bo-xao-ngu-vi.jpg'," +
                "'Vĩnh Long','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Tuyên Quang 21','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/03/com-cuon-xuc-xich-chien.jpg'," +
                "'Tuyên Quang','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Tuyên Qang 2','Đảm bảo chất lượng','02746288709','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/03/ca-nuc-hap-bo-toi.jpg'," +
                "'Tuyên Quang','Lô G8. Đường Huỳnh Thúc Kháng')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Tra Vinh 0','Đảm bảo chất lượng','02746554888','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/04/goi-kho-qua-tom.jpg'," +
                "'Trà Vinh','1/18A Kp Hòa Lân 1 Thuận Giao')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Tra Vinh 30','Đảm bảo chất lượng','0869579176','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2014/04/thit-xien-nuong.jpg'," +
                "'Trà Vinh','77 hoàng hoa thám')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Bà năm','Ngon bổ rẻ','0123123123','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2018/07/mon-an-binh-dan.jpg'," +
                "'Tiền Giang','1 Võ Văn Ngân Thủ Đức TP HCM')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Bà năm','Ngon bổ rẻ','0123123123','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2018/07/mon-an-binh-dan.jpg'," +
                "'Tiền Giang','1 Võ Văn Ngân Thủ Đức TP HCM')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Bà năm','Ngon bổ rẻ','0123123123','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2018/07/mon-an-binh-dan.jpg'," +
                "'Thừa Thiên Huế','1 Võ Văn Ngân Thủ Đức TP HCM')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Bà năm','Ngon bổ rẻ','0123123123','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2018/07/mon-an-binh-dan.jpg'," +
                "'Thừa Thiên Huế','1 Võ Văn Ngân Thủ Đức TP HCM')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Bà năm','Ngon bổ rẻ','0123123123','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2018/07/mon-an-binh-dan.jpg'," +
                "'Thanh Hóa','1 Võ Văn Ngân Thủ Đức TP HCM')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Bà năm','Ngon bổ rẻ','0123123123','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2018/07/mon-an-binh-dan.jpg'," +
                "'Thanh Hóa','1 Võ Văn Ngân Thủ Đức TP HCM')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng SơnPhan','Các món ăn hảo hạng','0987626222','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/sui-cao.jpg'," +
                "'Thái Nguyên','Số 1 Công xã Paris, phường Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng SơnPhan','Các món ăn hảo hạng','0987626222','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/sui-cao.jpg'," +
                "'Thái Nguyên','Số 1 Công xã Paris, phường Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng SơnPhan','Các món ăn hảo hạng','0987626222','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/sui-cao.jpg'," +
                "'Thái Bình','Số 1 Công xã Paris, phường Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng SơnPhan','Các món ăn hảo hạng','0987626222','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/sui-cao.jpg'," +
                "'Thái Bình','Số 1 Công xã Paris, phường Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng SơnPhan','Các món ăn hảo hạng','0987626222','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/sui-cao.jpg'," +
                "'Tây Ninh','Số 1 Công xã Paris, phường Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng SơnPhan','Các món ăn hảo hạng','0987626222','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/sui-cao.jpg'," +
                "'Tây Ninh','Số 1 Công xã Paris, phường Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng HiepVu','Ngon hơn rẻ','0656552272','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/lap.jpg'," +
                "'Sơn La','han Bội Châu –Lê Thánh Tôn – Phan Chu Trinh')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng HiepVu','Ngon hơn rẻ','0656552272','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/lap.jpg'," +
                "'Sơn La','han Bội Châu –Lê Thánh Tôn – Phan Chu Trinh')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng HiepVu','Ngon hơn rẻ','0656552272','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/lap.jpg'," +
                "'Sóc Trăng','han Bội Châu –Lê Thánh Tôn – Phan Chu Trinh')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng HiepVu','Ngon hơn rẻ','0656552272','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/lap.jpg'," +
                "'Sóc Trăng','han Bội Châu –Lê Thánh Tôn – Phan Chu Trinh')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng HiepVu','Ngon hơn rẻ','0656552272','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/lap.jpg'," +
                "'Quảng Trị','han Bội Châu –Lê Thánh Tôn – Phan Chu Trinh')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng HiepVu','Ngon hơn rẻ','0656552272','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/lap.jpg'," +
                "'Quảng Trị','han Bội Châu –Lê Thánh Tôn – Phan Chu Trinh')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng HiepVu','Ngon hơn rẻ','0656552272','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/lap.jpg'," +
                "'Quảng Ninh','han Bội Châu –Lê Thánh Tôn – Phan Chu Trinh')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng HiepVu','Ngon hơn rẻ','0656552272','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/lap.jpg'," +
                "'Quảng Ninh','han Bội Châu –Lê Thánh Tôn – Phan Chu Trinh')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng HiepVu','Ngon hơn rẻ','0656552272','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/lap.jpg'," +
                "'Quảng Ngãi','han Bội Châu –Lê Thánh Tôn – Phan Chu Trinh')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng HiepVu','Ngon hơn rẻ','0656552272','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/lap.jpg'," +
                "'Quảng Ngãi','han Bội Châu –Lê Thánh Tôn – Phan Chu Trinh')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng HiepVu','Ngon hơn rẻ','0656552272','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/lap.jpg'," +
                "'Quảng Nam','han Bội Châu –Lê Thánh Tôn – Phan Chu Trinh')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng HiepVu','Ngon hơn rẻ','0656552272','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/lap.jpg'," +
                "'Quảng Nam','han Bội Châu –Lê Thánh Tôn – Phan Chu Trinh')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng HiepVu','Ngon hơn rẻ','0656552272','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/lap.jpg'," +
                "'Quảng Bình','han Bội Châu –Lê Thánh Tôn – Phan Chu Trinh')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng HiepVu','Ngon hơn rẻ','0656552272','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/lap.jpg'," +
                "'Quảng Bình','han Bội Châu –Lê Thánh Tôn – Phan Chu Trinh')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng HiepVu','Ngon hơn rẻ','0656552272','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/lap.jpg'," +
                "'Phú Thọ','han Bội Châu –Lê Thánh Tôn – Phan Chu Trinh')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng HiepVu','Ngon hơn rẻ','0656552272','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/lap.jpg'," +
                "'Phú Thọ','han Bội Châu –Lê Thánh Tôn – Phan Chu Trinh')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng HiepVu','Ngon hơn rẻ','0656552272','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/lap.jpg'," +
                "'Ninh Thuận','han Bội Châu –Lê Thánh Tôn – Phan Chu Trinh')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng HiepVu','Ngon hơn rẻ','0656552272','khongco','khongcho','quán nước','400k','50k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/lap.jpg'," +
                "'Ninh Thuận','han Bội Châu –Lê Thánh Tôn – Phan Chu Trinh')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng TriDuc','Đảm bảo chất lượng','0252625222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Sushi-Sashimi.jpg'," +
                "'Ninh Bình','Số 2 đường Nguyễn Bỉnh Khiêm, P.Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng TriDuc','Đảm bảo chất lượng','0252625222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Sushi-Sashimi.jpg'," +
                "'Ninh Bình','Số 2 đường Nguyễn Bỉnh Khiêm, P.Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng TriDuc','Đảm bảo chất lượng','0252625222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Sushi-Sashimi.jpg'," +
                "'Nghệ An','Số 2 đường Nguyễn Bỉnh Khiêm, P.Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng TriDuc','Đảm bảo chất lượng','0252625222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Sushi-Sashimi.jpg'," +
                "'Nghệ An','Số 2 đường Nguyễn Bỉnh Khiêm, P.Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng TriDuc','Đảm bảo chất lượng','0252625222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Sushi-Sashimi.jpg'," +
                "'Nam Định','Số 2 đường Nguyễn Bỉnh Khiêm, P.Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng TriDuc','Đảm bảo chất lượng','0252625222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Sushi-Sashimi.jpg'," +
                "'Nam Định','Số 2 đường Nguyễn Bỉnh Khiêm, P.Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng TriDuc','Đảm bảo chất lượng','0252625222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Sushi-Sashimi.jpg'," +
                "'Long An','Số 2 đường Nguyễn Bỉnh Khiêm, P.Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng TriDuc','Đảm bảo chất lượng','0252625222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Sushi-Sashimi.jpg'," +
                "'Long An','Số 2 đường Nguyễn Bỉnh Khiêm, P.Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng TriDuc','Đảm bảo chất lượng','0252625222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Sushi-Sashimi.jpg'," +
                "'Lào Cai','Số 2 đường Nguyễn Bỉnh Khiêm, P.Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng TriDuc','Đảm bảo chất lượng','0252625222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Sushi-Sashimi.jpg'," +
                "'Lạng Sơn','Số 2 đường Nguyễn Bỉnh Khiêm, P.Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng TriDuc','Đảm bảo chất lượng','0252625222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Sushi-Sashimi.jpg'," +
                "'Lâm Đồng','Số 2 đường Nguyễn Bỉnh Khiêm, P.Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng TriDuc','Đảm bảo chất lượng','0252625222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Sushi-Sashimi.jpg'," +
                "'Lâm Đồng','Số 2 đường Nguyễn Bỉnh Khiêm, P.Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng TriDuc','Đảm bảo chất lượng','0252625222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Sushi-Sashimi.jpg'," +
                "'Lai Châu','Số 2 đường Nguyễn Bỉnh Khiêm, P.Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng TriDuc','Đảm bảo chất lượng','0252625222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Sushi-Sashimi.jpg'," +
                "'Lai Châu','Số 2 đường Nguyễn Bỉnh Khiêm, P.Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng TriDuc','Đảm bảo chất lượng','0252625222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Sushi-Sashimi.jpg'," +
                "'Kon Tum','Số 2 đường Nguyễn Bỉnh Khiêm, P.Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng TriDuc','Đảm bảo chất lượng','0252625222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Sushi-Sashimi.jpg'," +
                "'Kon Tum','Số 2 đường Nguyễn Bỉnh Khiêm, P.Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng TriDuc','Đảm bảo chất lượng','0252625222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Sushi-Sashimi.jpg'," +
                "'Kiên Giang','Số 2 đường Nguyễn Bỉnh Khiêm, P.Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng TriDuc','Đảm bảo chất lượng','0252625222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Sushi-Sashimi.jpg'," +
                "'Kiên Giang','Số 2 đường Nguyễn Bỉnh Khiêm, P.Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng TriDuc','Đảm bảo chất lượng','0252625222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Sushi-Sashimi.jpg'," +
                "'Khánh Hòa','Số 2 đường Nguyễn Bỉnh Khiêm, P.Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng TriDuc','Đảm bảo chất lượng','0252625222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Sushi-Sashimi.jpg'," +
                "'Khánh Hòa','Số 2 đường Nguyễn Bỉnh Khiêm, P.Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng TriDuc','Đảm bảo chất lượng','0252625222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Sushi-Sashimi.jpg'," +
                "'Hưng Yên','Số 2 đường Nguyễn Bỉnh Khiêm, P.Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng TriDuc','Đảm bảo chất lượng','0252625222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Sushi-Sashimi.jpg'," +
                "'Hưng Yên','Số 2 đường Nguyễn Bỉnh Khiêm, P.Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng TriDuc','Đảm bảo chất lượng','0252625222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2019/01/Sushi-Sashimi.jpg'," +
                "'Hòa Bình','Số 2 đường Nguyễn Bỉnh Khiêm, P.Bến Nghé')");
        database.queryData("INSERT INTO Restaurant VALUES (null,'Nhà Hàng TriDuc','Đảm bảo chất lượng','0252625222','khongco','khongcho','quán nước','100k','20k','https://hocnauan.edu.vn/wp-content/uploads/2018/09/he-thong-quan-an-nha-hang-mon-a.jpg'," +
                "'Hòa Bình','Số 2 đường Nguyễn Bỉnh Khiêm, P.Bến Nghé')");

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