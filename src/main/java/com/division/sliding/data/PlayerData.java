package com.division.sliding.data;

public class PlayerData {

    private int taskID;
    private long lastSneakTime;
    private long lastSlideTime;

    public PlayerData() {
        taskID = -1;
        lastSneakTime = -1;
        lastSlideTime = -1;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public long getLastSneakTime() {
        return lastSneakTime;
    }

    public void setLastSneakTime(long lastSneakTime) {
        this.lastSneakTime = lastSneakTime;
    }

    public long getLastSlideTime() {
        return lastSlideTime;
    }

    public void setLastSlideTime(long lastSlideTime) {
        this.lastSlideTime = lastSlideTime;
    }



}
