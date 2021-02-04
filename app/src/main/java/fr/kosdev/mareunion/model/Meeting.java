package fr.kosdev.mareunion.model;


import java.util.Calendar;

public class Meeting {

    String name;
    String date;
    String time;
    String meetingRoom;
    String entrantMail;
    int meetingImage  ;
    long meetingDuration ;
    Calendar mCalendar;



    public Meeting(int meetingImage, String name,String date , String time , String meetingRoom , String entrantMail, long meetingDuration, Calendar mCalendar) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.meetingRoom = meetingRoom;
        this.entrantMail = entrantMail;
        this.meetingImage = meetingImage;
        this.meetingDuration = meetingDuration;
        this.mCalendar = mCalendar;
    }

    public Meeting(){

    }

    public Meeting(String name, String meetingRoom) {
        this.name = name;
        this.meetingRoom = meetingRoom;
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

    public Calendar getCalendar() {
        return mCalendar;
    }

    public void setCalendar(Calendar calendar) {
        mCalendar = calendar;
    }
}
