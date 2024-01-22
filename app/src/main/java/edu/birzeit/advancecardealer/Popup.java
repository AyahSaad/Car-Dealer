package edu.birzeit.advancecardealer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class Popup extends AppCompatDialogFragment {
    private TextView infoOrder;
    private ImageView image;
    private Button cancel;
    private Button confirm;
    private int position;
    private List<Car> listImage;
    RequestOptions option;

    CarInfoFragment carInfoFragment = new CarInfoFragment();
    private PopupListener listener;


    public Popup(int position,List<Car> listImage,PopupListener listener) {
        this.position = position;
        this.listImage = listImage;
        this.listener = listener;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }


    public void setListener(PopupListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        DataBaseHelper dataBaseDialog = new DataBaseHelper(getActivity(), "CarsDatabase", null, 1);



        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup,null);
        builder.setView(view);
        infoOrder = view.findViewById(R.id.ReservedCarInfo);
        image = view.findViewById(R.id.carPhoto);
        cancel = view.findViewById(R.id.cancel);
        confirm  = view.findViewById(R.id.confirmBtn);
        Glide.with(view).load(listImage.get(position).getImage()).apply(option).into(image);
        double newPrice = (1 - listImage.get(position).getOffer()) * listImage.get(position).getPrice();
        infoOrder.setText("You want to order "+listImage.get(position).getName()+" With Price "+ newPrice);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();

            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onConfirmation(true);
                }
                dismiss();
            }
        });

        return builder.create();

    }

    public interface PopupListener {
        void onConfirmation(boolean confirmed);
    }
}
