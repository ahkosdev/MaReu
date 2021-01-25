package fr.kosdev.mareunion.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Duration;

import fr.kosdev.mareunion.R;

import static java.time.Duration.ofMinutes;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Meeting {

    String name;
    String date;
    String time;
    String meetingRoom;
    String entrantMail;
    int meetingImage  ;
    long meetingDuration ;



    public Meeting(long meetingDuration,int meetingImage ,String name,String date, String time, String meetingRoom , String entrantMail) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.meetingRoom = meetingRoom;
        this.entrantMail = entrantMail;
        this.meetingImage = meetingImage;
        this.meetingDuration = meetingDuration;
    }

    public int getMeetingImage() {
        return meetingImage;
    }

    public void setMeetingImage(int meetingImage) {
        this.meetingImage = meetingImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(String meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public String getEntrantMail() {
        return entrantMail;
    }

    public void setEntrantMail(String entrantMail) {
        this.entrantMail = entrantMail;
    }

    public long getMeetingDuration() {
        return meetingDuration;
    }

    public void setMeetingDuration(long meetingDuration) {
        this.meetingDuration = meetingDuration;
    }
}
