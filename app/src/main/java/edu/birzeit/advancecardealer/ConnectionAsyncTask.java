package edu.birzeit.advancecardealer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import java.util.List;

import edu.birzeit.advancecardealer.admin.AdminMainPage;

public class ConnectionAsyncTask extends AsyncTask<String, String, String> {
    private Context context;


    Activity activity;
    public ConnectionAsyncTask(Context context,Activity activity) {
        this.context = context;
        this.activity = activity;
    }
    @Override
    protected void onPreExecute() {

    }
    @Override
    protected String doInBackground(String... params) {
        System.out.println("-----------------------------------------------");
        System.out.println(isConnected());
        if (isConnected()) {
            // Internet connection is available, proceed with the HTTP request
            return HttpManager.getData(params[0]);
        } else {
            // No Internet Connection, return null or an appropriate error message
            return null;
        }
    }
    @Override
    protected void onPostExecute(String result) {
        if (result != null) {
            // Process the HTTP response here
            List<Car> cars = CarJasonParser.getObjectFromJson(result);
            Intent gregSection =  new Intent(activity, RegSection.class);
            activity.startActivity(gregSection);
        } else {
            // Handle the case where there is no internet connection or the HTTP request failed
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