package com.example.financialapplication.ui.life_timeline;

import android.app.Application;

import com.example.financialapplication.db.repository.LifeEventRepository;
import com.example.financialapplication.db.entity.LifeEventEntity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class LifeTimelineViewModel extends AndroidViewModel {

    private LiveData<List<LifeEventEntity>> events;
    private LifeEventRepository repository;


    public LifeTimelineViewModel(@NonNull Application application) {
        super(application);
        repository = new LifeEventRepository(application);
    }

    public LiveData<List<LifeEventEntity>> getAllLifeEvents() {
        return repository.getAllLifeEvents();
    }

//    public List<LifeEventEntity> getAllLifeEventsStatic() {
//        return repository.getAllLifeEventsStatic();
//    }

    public void insertLifeEvent(LifeEventEntity lifeEventEntity) {
        repository.insert(lifeEventEntity);
    }

    public void deleteLifeEvent(LifeEventEntity lifeEventEntity) {
        repository.delete(lifeEventEntity);
    }

    public void deleteLifeEventById(int id) {
        repository.deleteLifeEventById(id);
    }

}
