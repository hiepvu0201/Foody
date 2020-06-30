package hcmute.edu.vn.group11.foody.activities;

import android.os.Bundle;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hcmute.edu.vn.group11.foody.CustomExpandableListView;
import hcmute.edu.vn.group11.foody.R;

public class MenuActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    List<String> listdataHeader;
    HashMap<String,List<String>> listdataChild;
    CustomExpandableListView customExpandableListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

          addControl();
        customExpandableListView=new CustomExpandableListView(MenuActivity.this,listdataHeader,listdataChild);
        expandableListView.setAdapter(customExpandableListView);
    }

   private void addControl() {

       expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
       listdataHeader = new ArrayList<>();
       listdataChild = new HashMap<String, List<String>>();

       listdataHeader.add("Bò Mỹ Nhúng Ớt");
       listdataHeader.add("Bún Đậu");
       listdataHeader.add("Món Thêm");

       List<String> BoMyNhungOt = new ArrayList<String>();
       BoMyNhungOt.add("Bò Mỹ nhúng ớt nhở");
       BoMyNhungOt.add("Bò Mỹ nhúng ớt vừa");
       BoMyNhungOt.add("Bò Mỹ nhúng ớt lớn");
       BoMyNhungOt.add("Bê thui mẹt nhỏ");
       BoMyNhungOt.add("Bê thui mẹt vừa");
       BoMyNhungOt.add("Bê thui mẹt lớn");

       List<String> BunDau = new ArrayList<String>();
       List<String> MonThem = new ArrayList<String>();


       listdataChild.put(listdataHeader.get(0), BoMyNhungOt);
       listdataChild.put(listdataHeader.get(1), BunDau);
       listdataChild.put(listdataHeader.get(2), MonThem);

   }
}
