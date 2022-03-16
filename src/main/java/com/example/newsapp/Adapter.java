package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Model.Articles;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    List<Articles> articles;

    public Adapter(Context context, List<Articles> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
         return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        final Articles a = articles.get(position);
        String imageUri = a.getUrlToImage();
        String Uri = a.getUrl();
        Picasso.with(context).load(imageUri).into(holder.imageView);
        holder.tvTitle.setText(a.getTitle());
        holder.tvSource.setText(a.getSource().getName());
        holder.tvDate.setText(a.getPublishedAt());
//        holder.tvDate.setText("\u2022"+dateTime(a.getPublishedAt()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Detailed.class);
                intent.putExtra("title",a.getTitle());
                intent.putExtra("source",a.getSource().getName());
                intent.putExtra("time",dateTime(a.getPublishedAt()));
                intent.putExtra("description",a.getDescription());
                intent.putExtra("imageUrl",a.getUrlToImage());
                intent.putExtra("url",a.getUrl());
                context.startActivity(intent);
            }
        });

        String imageUrl = a.getUrlToImage();
        Picasso.with(context).load(imageUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle,tvSource,tvDate;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        tvTitle=itemView.findViewById(R.id.tvTitle);
        tvSource= itemView.findViewById(R.id.tvSource);
        tvDate =itemView.findViewById(R.id.tvDate);
        imageView=itemView.findViewById(R.id.image);
        cardView= itemView.findViewById(R.id.cardView);

        }
    }

    public String dateTime(String s){
        PrettyTime prettyTime = new PrettyTime(new Locale(getCountry()));
        String time = null;
        try {
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD 'T' HH:mm",Locale.ENGLISH);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:",Locale.ENGLISH);
            Date date = simpleDateFormat.parse(s);
            time = prettyTime.format(date);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return time;
    }
    public String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }


}
