package fr.kosdev.mareunion.meeting_list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.kosdev.mareunion.DI.DI;
import fr.kosdev.mareunion.R;
import fr.kosdev.mareunion.event.DeleteMeetingEvent;
import fr.kosdev.mareunion.model.Meeting;
import fr.kosdev.mareunion.service.DummyMeetingApiService;
import fr.kosdev.mareunion.service.MeetingApiService;

public class ListMeetingActivity extends AppCompatActivity {

    @BindView(R.id.add_meeting_fab)
    FloatingActionButton addMeeting;
    @BindView(R.id.meeting_list_rcv)
    RecyclerView mRecyclerView;

    private List<Meeting> mMeetings;
    private MeetingApiService mApiService;
    private MeetingRecyclerViewAdapter meetingAdapter;
    DummyMeetingApiService meetingApiService = new DummyMeetingApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting_);
        ButterKnife.bind(this);
        this.configureToolbar();
        this.configureRecyclerView();

        mApiService = DI.getMeetingApiService();

        //addMeeting = (FloatingActionButton) findViewById(R.id.add_meeting_fab) ;
        addMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListMeetingActivity.this, AddMeetingActivity.class);
                startActivity(intent);

            }
        });

    }

    public void configureRecyclerView(){

        mMeetings = new ArrayList<>();
        meetingAdapter = new MeetingRecyclerViewAdapter(mMeetings);
        mRecyclerView.setAdapter(meetingAdapter);
    }

    private void updateMeetingList(){
        meetingAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_list_meeting_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        item.getItemId();
        Toast.makeText(this, "no filter", Toast.LENGTH_LONG).show();
        return true;
    }

    private void configureToolbar(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.meeting_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        updateMeetingList();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe

    public void onEvent(DeleteMeetingEvent event) {
        mApiService.deleteMeeting(event.meeting);
        updateMeetingList();
    }
}