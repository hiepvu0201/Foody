package hcmute.edu.vn.group11.foody;

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

import hcmute.edu.vn.group11.foody.entities.Restaurant;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Restaurant> mData;

    public RecyclerViewAdapter(Context mContext, List<Restaurant> mData) {
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

        holder.tv_res_name.setText(mData.get(position).getName());
        Picasso.with(this.mContext).load(mData.get(position).getImage())
                .into(holder.img_res_thumbnail,new com.squareup.picasso.Callback(){
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
                Intent intent = new Intent(mContext, ActivityRestaurant.class);

                // Passing data to the book category
                intent.putExtra("Restaurant Name", mData.get(position).getName());
                intent.putExtra("Description", mData.get(position).getDescription());
                intent.putExtra("Thumbnail", mData.get(position).getImage());

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
        TextView tv_res_name;
        ImageView img_res_thumbnail;
        CardView cardView;

        public MyViewHolder(View itemView){
            super(itemView);

            tv_res_name = (TextView) itemView.findViewById(R.id.res_name_id);
            img_res_thumbnail = (ImageView) itemView.findViewById(R.id.res_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }
}
