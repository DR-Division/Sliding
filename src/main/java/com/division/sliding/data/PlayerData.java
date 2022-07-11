package com.division.sliding.data;

public class PlayerData {

    private int taskID;
    private long lastSneakTime;
    private long lastSlideTime;
    private String direction;

    public PlayerData() {
        taskID = -1;
        lastSneakTime = -1;
        lastSlideTime = -1;
        direction = null;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }


}
