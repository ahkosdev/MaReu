package fr.kosdev.mareunion.meeting_list;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.kosdev.mareunion.R;
import fr.kosdev.mareunion.event.DeleteMeetingEvent;
import fr.kosdev.mareunion.model.Meeting;

public class MeetingViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.meeting_room_icon_img)
    ImageView mMeetingRoomImage;
    @BindView(R.id.meeting_description_txt)
    TextView mMeetingDescription;
    @BindView(R.id.meeting_delete_button_btn)
    ImageButton mMeetingDeleteButton;

    public MeetingViewHolder( View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void meetingUpDate(Meeting meeting){
        mMeetingDescription.setText(meeting.getName());
        mMeetingDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
            }
        });

    }
}
