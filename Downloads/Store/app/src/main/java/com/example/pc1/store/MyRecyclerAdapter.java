package com.example.pc1.store;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.viewHolder>{


    private final Context context;

    public interface MyRecyclerViewClickListener{
        void onItemClicked(int position);
    }

    private  MyRecyclerViewClickListener mListener;

    public void setOnClickListener(MyRecyclerViewClickListener listener){
        mListener = listener;
    }

    private final ArrayList<Mainpageitem> Mainarraylist;
    
    public MyRecyclerAdapter(Context context, ArrayList<Mainpageitem> Mainarraylist){
        this.Mainarraylist = Mainarraylist;
        this.context = context;
    
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()
        ).inflate(R.layout.mainpage_item_list,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, final int position) {
    Mainpageitem item = Mainarraylist.get(position);

    holder.Fragment_one_itemname.setText(item.getTitle());
    holder.Fragment_one_sold.setText(item.getSold());
    holder.Fragment_one_where.setText(item.getWhere());
    holder.Fragment_one_distance.setText(item.getdistance());
//    holder.Fragment_one_time.setText(item.getTime());
    holder.Fragment_one_check.setChecked(item.getCheck());
//    holder.Fragment_one_img.setImageURI(Uri.parse("http://13.58.3.24/two_1533274590932"));
        Glide.with(context).load(Uri.parse(item.getImg())).into(holder.Fragment_one_img);
//        Picasso.with(context).load(item.getImg()).into(holder.Fragment_one_img);
    if(mListener !=null){
        final int pos = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClicked(pos);
            }
        });
    }


    }

    @Override
    public int getItemCount() {

        return Mainarraylist.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        TextView Fragment_one_sold;
        TextView Fragment_one_itemname;
        TextView Fragment_one_where;
        TextView Fragment_one_distance;
        ImageView Fragment_one_img;
        TextView Fragment_one_time;
        CheckBox Fragment_one_check;

        public viewHolder(View itemView) {
            super(itemView);
            Fragment_one_itemname=itemView.findViewById(R.id.FragmentOne_itemname);
            Fragment_one_where=itemView.findViewById(R.id.FragmentOne_where);
            Fragment_one_sold=itemView.findViewById(R.id.FragmentOne_sold);
            Fragment_one_img=itemView.findViewById(R.id.FragmentOne_imageview);
//            Fragment_one_t=itemView.findViewById(R.id.textView4);
            Fragment_one_check=itemView.findViewById(R.id.checkBox3);
            Fragment_one_distance=itemView.findViewById(R.id.FragmentOne_soldperson);
        }
    }
}
