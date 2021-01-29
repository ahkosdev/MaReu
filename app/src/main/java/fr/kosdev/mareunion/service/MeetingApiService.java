package fr.kosdev.mareunion.service;

import java.util.Calendar;
import java.util.List;

import fr.kosdev.mareunion.model.Meeting;


public interface MeetingApiService {

    List<Meeting> getMeetings();
    List<Meeting> getMeetingsWithDateSelected(Calendar dateSelected);
    List<Meeting> getMeetingsWithRoomSelected();

    void createMeeting(Meeting meeting);

    void  deleteMeeting(Meeting meeting);



}
