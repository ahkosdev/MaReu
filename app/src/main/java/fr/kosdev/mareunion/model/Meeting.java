package fr.kosdev.mareunion.model;

import fr.kosdev.mareunion.R;

public class Meeting {

    String name;
    String date;
    String time;
    String meetingRoom;
    String entrantMail;
    String meetingImage  ;


    public Meeting(String meetingImage ,String name,String date, String time, String meetingRoom , String entrantMail) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.meetingRoom = meetingRoom;
        this.entrantMail = entrantMail;
        this.meetingImage = meetingImage;
    }

    public String getMeetingImage() {
        return meetingImage;
    }

    public void setMeetingImage(String meetingImage) {
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


}
