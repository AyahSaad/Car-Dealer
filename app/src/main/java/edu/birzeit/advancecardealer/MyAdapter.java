package edu.birzeit.advancecardealer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<Car> carsListAdapter;
    RequestOptions option;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Car car);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    public MyAdapter(Context context, List<Car> carsListAdapter) {
        this.context = context;
        this.carsListAdapter = carsListAdapter;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cars_widgets,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nameView.setText(carsListAdapter.get(position).getName());
        holder.typeView.setText(carsListAdapter.get(position).getType());
        double newPrice = (1-carsListAdapter.get(position).getOffer()) * carsListAdapter.get(position).getPrice();
        holder.priceView.setText(String.valueOf(newPrice));
        holder.ratingView.setText(String.valueOf(carsListAdapter.get(position).getRating()));


        Glide.with(context).load(carsListAdapter.get(position).getImage()).apply(option).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    // Get the clicked car item
                    Car clickedCar = carsListAdapter.get(clickedPosition);

                    // Open the CarDetailFragment and pass the car details and position
                    CarInfoFragment carDetailFragment = new CarInfoFragment(clickedPosition,carsListAdapter);
                    carDetailFragment.updateDetails(clickedCar);

                    // Use FragmentManager to replace the current fragment with CarDetailFragment
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_containert, carDetailFragment)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });



    }

    @Override
    public int getItemCount() {

        return carsListAdapter.size();
    }
}
