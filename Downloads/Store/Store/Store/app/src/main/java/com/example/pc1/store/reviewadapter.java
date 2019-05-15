package com.example.pc1.store;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class reviewadapter extends BaseAdapter {
    private final Context applicationContext;
    private final ArrayList<review> reviewActivity_item;

    public reviewadapter(Context applicationContext, ArrayList<review> reviewActivity_item) {
        this.reviewActivity_item = reviewActivity_item;
        this.applicationContext = applicationContext;
    }

    @Override
    public int getCount() {
        return reviewActivity_item.size();
    }

    @Override
    public Object getItem(int i) {
        return reviewActivity_item.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        if(view ==null) {



            LayoutInflater inflater = (LayoutInflater)applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.reviewlist, null);

            TextView review_realfinish=(TextView)view.findViewById(R.id.review_realfinish);
            TextView review_title = (TextView) view.findViewById(R.id.review_title);
            TextView review_content = (TextView)view. findViewById(R.id.review_content);
            TextView review_date = (TextView) view.findViewById(R.id.review_date);
            ImageView review_image = (ImageView) view.findViewById(R.id.review_img);
            RatingBar review_rating = (RatingBar) view.findViewById(R.id.review_ratingBar);
            ImageView imageView7 =(ImageView)view.findViewById(R.id.imageView7);
            //리뷰 첫번쨰이미지
            ImageView imageView8 =(ImageView)view.findViewById(R.id.imageView8);
            //리뷰 두번쨰이미지
            ImageView imageView9 =(ImageView)view.findViewById(R.id.imageView9);
            //리뷰 세번쨰이미지


            review_realfinish.setText(reviewActivity_item.get(i).getCerti());
            review_title.setText(reviewActivity_item.get(i).getFrom());
            review_content.setText(reviewActivity_item.get(i).getcontent());
            review_date.setText(reviewActivity_item.get(i).getdate());
            //프사이미지
            Glide.with(applicationContext).load(reviewActivity_item.get(i).getimg()).apply(RequestOptions.circleCropTransform()).into(review_image);
            review_rating.setRating(reviewActivity_item.get(i).getrating());
            //리뷰이미지

            Glide.with(applicationContext).load(reviewActivity_item.get(i).getImg11()).into(imageView7);
            Glide.with(applicationContext).load(reviewActivity_item.get(i).getImg12()).into(imageView8);
            Glide.with(applicationContext).load(reviewActivity_item.get(i).getImg13()).into(imageView9);

            imageView7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent gogo = new Intent(applicationContext,image_big_Activity.class);
                    gogo.putExtra("imagepicture",reviewActivity_item.get(i).getImg11());
                    applicationContext.startActivity(gogo.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));



                }
            });
            imageView8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent gogo = new Intent(applicationContext,image_big_Activity.class);
                    gogo.putExtra("imagepicture",reviewActivity_item.get(i).getImg12());
                    applicationContext.startActivity(gogo.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                }
            });

            imageView9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent gogo = new Intent(applicationContext,image_big_Activity.class);
                    gogo.putExtra("imagepicture",reviewActivity_item.get(i).getImg13());
                    applicationContext.startActivity(gogo.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            });

        }

        return view;
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
