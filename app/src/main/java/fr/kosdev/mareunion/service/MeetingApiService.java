package fr.kosdev.mareunion.service;

import java.util.List;

import fr.kosdev.mareunion.model.Meeting;


public interface MeetingApiService {

    List<Meeting> getMeetings();

    void createMeeting(Meeting meeting);

    void  deleteMeeting(Meeting meeting);

}
