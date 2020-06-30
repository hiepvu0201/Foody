package hcmute.edu.vn.group11.foody.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ExpandableListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import hcmute.edu.vn.group11.foody.CustomExpandableListView;
import hcmute.edu.vn.group11.foody.R;

public class MenuActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    List<String> listdataHeader;
    HashMap<String,List<String>> listdataChild;
    CustomExpandableListView customExpandableListView;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

          addControl();
        customExpandableListView=new CustomExpandableListView(MenuActivity.this,listdataHeader,listdataChild);
        expandableListView.setAdapter(customExpandableListView);
    }

   @RequiresApi(api = Build.VERSION_CODES.KITKAT)
   private void addControl() {

       expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
       listdataHeader = new ArrayList<>();
       listdataChild = new HashMap<String, List<String>>();



       listdataHeader.add("Đặc sản");
       listdataHeader.add("Món mới");

       List<String> dacSan = new ArrayList<String>();
       Intent intent = getIntent();
       dacSan.add(Objects.requireNonNull(intent.getExtras()).getString("Name")+" - "+intent.getExtras().getString("Gia"));

       listdataChild.put(listdataHeader.get(0), dacSan);

   }
}
