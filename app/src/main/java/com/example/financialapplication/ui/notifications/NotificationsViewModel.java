package com.example.financialapplication.ui.notifications;

import android.app.Application;

import com.example.financialapplication.db.entity.ActionEntity;
import com.example.financialapplication.db.entity.UserEntity;
import com.example.financialapplication.db.repository.ActionRepository;
import com.example.financialapplication.db.repository.UserRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    private ActionRepository repository;
    private UserRepository userRepository;

    public NotificationsViewModel(@NonNull Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
        repository = new ActionRepository(application);
        userRepository = new UserRepository(application);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<ActionEntity>> getAllCompletedActions() {
        return repository.getAllCompletedActions();
    }

    public LiveData<List<ActionEntity>> getAllIncompleteActions() {
        return repository.getAllIncompleteActions();
    }

    public void insertAction(ActionEntity actionEntity) {
        repository.insert(actionEntity);
    }

    public void updateUser(UserEntity userEntity) {
        userRepository.update(userEntity);
    }
}