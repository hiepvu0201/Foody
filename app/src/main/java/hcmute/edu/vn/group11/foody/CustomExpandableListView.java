package hcmute.edu.vn.group11.foody;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class CustomExpandableListView extends BaseExpandableListAdapter
{

    Context context;
    List<String> listHeader;  //chưa các phần tử trong group
    HashMap<String,List<String>> listChild;   //phục vụ cho list phần child

    public CustomExpandableListView(Context context, List<String> listHeader, HashMap<String, List<String>> listChild) {
        this.context = context;
        this.listHeader = listHeader;
        this.listChild = listChild;
    }

    @Override
    public int getGroupCount() {    //gét số lượng phần từ ở trong group
        return listHeader.size(); //kích thước của cái mảng
    }

    @Override
    public int getChildrenCount(int groupPosition) {   //lấy ra những cái child trong group
        return listChild.get(listHeader.get(groupPosition)).size(); //lấy ra được các phần tử trong group
     }

    @Override
    public Object getGroup(int groupPosition) { //
        return listHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listChild.get(listHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle= (String) getGroup(groupPosition);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.group_view,null);
        TextView txthearder=convertView.findViewById(R.id.textviewHeader);
        txthearder.setText(headerTitle);
        return convertView;
    }


    //chèn custom cho group và child
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String headeritem= (String) getChild(groupPosition,childPosition);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.child_view,null);
        TextView txtitem=convertView.findViewById(R.id.textviewChild);
        txtitem.setText(headeritem);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
