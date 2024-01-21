package edu.birzeit.advancecardealer;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView nameView,ratingView,typeView,priceView;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageTest);
        nameView = itemView.findViewById(R.id.car_name);
        ratingView = itemView.findViewById(R.id.car_rating);
        typeView = itemView.findViewById(R.id.car_type);
        priceView = itemView.findViewById(R.id.car_price);

    }
}
