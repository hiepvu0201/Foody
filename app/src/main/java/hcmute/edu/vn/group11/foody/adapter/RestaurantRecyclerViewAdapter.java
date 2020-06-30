package hcmute.edu.vn.group11.foody.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import hcmute.edu.vn.group11.foody.activities.RestaurantActivity;
import hcmute.edu.vn.group11.foody.R;
import hcmute.edu.vn.group11.foody.entities.Restaurant;

public class RestaurantRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantRecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Restaurant> mData;

    public RestaurantRecyclerViewAdapter(Context mContext, List<Restaurant> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.tv_name.setText(mData.get(position).getName());
        Picasso.with(this.mContext).load(mData.get(position).getImage())
                .into(holder.img_thumbnail,new com.squareup.picasso.Callback(){
                    @Override
                    public void onSuccess() {
                    }
                    @Override
                    public void onError() {
                    }
                });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RestaurantActivity.class);

                // Passing data to the res activity
                intent.putExtra("id", mData.get(position).getId());
                intent.putExtra("Name", mData.get(position).getName());
                intent.putExtra("phone", mData.get(position).getPhone());
                intent.putExtra("wifi", mData.get(position).getWifi());
                intent.putExtra("wifipass", mData.get(position).getWifipass());
                intent.putExtra("type", mData.get(position).getType());
                intent.putExtra("minprice", mData.get(position).getMinprice());
                intent.putExtra("maxprice", mData.get(position).getMaxprice());
                intent.putExtra("description", mData.get(position).getDescription());
                intent.putExtra("province", mData.get(position).getProvince());
                intent.putExtra("address", mData.get(position).getAddress());

                //Start the activity
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        ImageView img_thumbnail;
        CardView cardView;

        public MyViewHolder(View itemView){
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.name_id);
            img_thumbnail = (ImageView) itemView.findViewById(R.id.img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }
}
