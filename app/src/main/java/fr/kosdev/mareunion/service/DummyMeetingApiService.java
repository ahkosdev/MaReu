package fr.kosdev.mareunion.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fr.kosdev.mareunion.model.Meeting;

public class DummyMeetingApiService  implements MeetingApiService {

    List<Meeting> mMeetings = new ArrayList<>();
    List<Meeting> meetingsPerDate = new ArrayList<>();
    List<Meeting> meetingsPerRoom = new ArrayList<>();



    @Override
    public List<Meeting> getMeetings() {
        return mMeetings;
    }

    @Override
    public void createMeeting(Meeting meeting) {
        mMeetings.add(meeting);

    }


    @Override
    public void deleteMeeting(Meeting meeting) {
        mMeetings.remove(meeting);

    }

    @Override
    public List<Meeting> getMeetingsWithDateSelected(Calendar dateSelected) {

        for (int item = 0; item<mMeetings.size();item++){
            if (mMeetings.get(item).getCalendar().getTimeInMillis()== dateSelected.getTimeInMillis()){
             meetingsPerDate.add(mMeetings.get(item));
            }
        }
        return meetingsPerDate;

    }

    @Override
    public List<Meeting> getMeetingsWithRoomSelected() {
        return meetingsPerRoom;
    }
}
