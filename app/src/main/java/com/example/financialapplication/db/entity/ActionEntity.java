package com.example.financialapplication.db.entity;

import com.example.financialapplication.R;

import java.util.Calendar;
import java.util.Date;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "actions_table")
public class ActionEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private Calendar doByDate;
    private String doASAP;  // if this is used, set the date to today.
    private String actionTitle;
    private int imageId;
    private String reasoning;
    private String directions;
    private boolean completed;

    // Used to determine data required in the action from the user
    private boolean inputNeeded;
    private String inputDirections; // Specifies what the user needs to do.
    private String inputSaveLocation;
    private String inputDataType;
    private String userInput;

    public ActionEntity(String actionTitle, String reasoning, String directions, boolean inputNeeded) {
        this.actionTitle = actionTitle;
        this.reasoning = reasoning;
        this.directions = directions;
        this.inputNeeded = inputNeeded;
        this.imageId = -700037;
        this.completed = false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Calendar getDoByDate() {
        return doByDate;
    }

    public void setDoByDate(Calendar doByDate) {
        this.doByDate = doByDate;
    }

    public String getDoASAP() {
        return doASAP;
    }

    public void setDoASAP(String doASAP) {
        this.doASAP = doASAP;
    }

    public String getActionTitle() {
        return actionTitle;
    }

    public void setActionTitle(String actionTitle) {
        this.actionTitle = actionTitle;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getReasoning() {
        return reasoning;
    }

    public void setReasoning(String reasoning) {
        this.reasoning = reasoning;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isInputNeeded() {
        return inputNeeded;
    }

    public String getInputDirections() {
        return inputDirections;
    }

    public void setInputDirections(String inputDirections) {
        this.inputDirections = inputDirections;
    }

    public String getInputSaveLocation() {
        return inputSaveLocation;
    }

    public void setInputSaveLocation(String inputSaveLocation) {
        this.inputSaveLocation = inputSaveLocation;
    }

    public String getInputDataType() {
        return inputDataType;
    }

    public void setInputDataType(String inputDataType) {
        this.inputDataType = inputDataType;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
}