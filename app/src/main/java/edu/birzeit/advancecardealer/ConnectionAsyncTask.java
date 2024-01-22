package edu.birzeit.advancecardealer;

import static edu.birzeit.advancecardealer.CarJsonParser.cars;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class ConnectionAsyncTask extends AsyncTask<String, String, String> {
    private Context context;
    private DataBaseHelper dataBaseHelper;


    Activity activity;
    public ConnectionAsyncTask(Context context,Activity activity,DataBaseHelper dataBaseHelper) {
        this.context = context;
        this.activity = activity;
        this.dataBaseHelper = dataBaseHelper;
    }
    @Override
    protected void onPreExecute() {

    }
    @Override
    protected String doInBackground(String... params) {

        if (isConnected()) {

            return HttpManager.getData(params[0]);
        } else {
            // No Internet Connection, return null or an appropriate error message
            return null;
        }
    }
    @Override
    protected void onPostExecute(String result) {
        if (result != null) {

            List<Car> cars = CarJsonParser.getObjectFromJson(result);
            for(Car car: cars){
                dataBaseHelper.insertCar(car);
            }
            Intent gregSection =  new Intent(activity, RegSection.class);
            activity.startActivity(gregSection);
        } else {
            Toast.makeText(activity, "No Internet Connection or Failed to Fetch Data", Toast.LENGTH_SHORT).show();
        }
    }


    boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // For Android M and newer versions
                NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET));
            } else {
                // For older versions
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected();
            }
        }

        return false;
    }

}