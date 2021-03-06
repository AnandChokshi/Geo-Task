package com.mobile.av.geotask.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Anand on 4/8/2015.
 */
public class Task implements Parcelable{

    private int task_id;
    private String title;
    private ArrayList<Item> items;
    private long range;
    private String note;
    private ArrayList<LatLng> location;
    private int status = 0;

    public Task(){
        items = new ArrayList<>();
        location = new ArrayList<>();
    }

    public Task(Parcel in){
        this();
        task_id = in.readInt();
        title = in.readString();
        in.readTypedList(items,Item.CREATOR);
        range = in.readLong();
        note = in.readString();
        in.readTypedList(location,LatLng.CREATOR);
        status = in.readInt();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public long getRange() {
        return range;
    }

    public void setRange(long range) {
        this.range = range;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ArrayList<LatLng> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<LatLng> location) {
        this.location = location;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Title: " + title +
                "\nRange: " + range +
                "\nNote: " + note;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(task_id);
        dest.writeString(title);
        dest.writeTypedList(items);
        dest.writeLong(range);
        dest.writeString(note);
        dest.writeTypedList(location);
        dest.writeInt(status);
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}
