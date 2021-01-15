package fr.kosdev.mareunion.meeting_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import fr.kosdev.mareunion.R;
import fr.kosdev.mareunion.event.DeleteMeetingEvent;
import fr.kosdev.mareunion.model.Meeting;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter <MeetingViewHolder> {

    private List<Meeting> mMeetings;

    public MeetingRecyclerViewAdapter(List<Meeting> mMeetings) {
        this.mMeetings = mMeetings;
    }

    @Override
    public MeetingViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_meeting_items, parent, false);
        return new MeetingViewHolder(view);
    }

    @Override
    public void onBindViewHolder( MeetingViewHolder holder, int position) {

        holder.meetingUpDate(mMeetings.get(position));

    }


    @Override
    public int getItemCount() {
        return mMeetings.size();
    }
}
