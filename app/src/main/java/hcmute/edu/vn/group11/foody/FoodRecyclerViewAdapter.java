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

import hcmute.edu.vn.group11.foody.entities.Food;

public class FoodRecyclerViewAdapter extends RecyclerView.Adapter<FoodRecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Food> mData;

    public FoodRecyclerViewAdapter(Context mContext, List<Food> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public FoodRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item, parent,false);
        return new FoodRecyclerViewAdapter.MyViewHolder(view);
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

                // Passing data to the resactivity
                intent.putExtra("Name", mData.get(position).getName());
                intent.putExtra("image", mData.get(position).getImage());
                intent.putExtra("idQuan", mData.get(position).getIdQuan());
                intent.putExtra("Gia", mData.get(position).getGia());

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
