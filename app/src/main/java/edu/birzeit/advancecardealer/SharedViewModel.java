package edu.birzeit.advancecardealer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> userEmail = new MutableLiveData<>();
    private MutableLiveData<String> userType = new MutableLiveData<>();

    public void setUserDetails(String email, String type) {
        userEmail.setValue(email);
        userType.setValue(type);
    }

    public LiveData<String> getUserEmail() {
        return userEmail;
    }

    public LiveData<String> getUserType() {
        return userType;
    }
}
