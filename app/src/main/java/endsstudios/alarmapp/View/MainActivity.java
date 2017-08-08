package endsstudios.alarmapp.View;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import endsstudios.alarmapp.Model.AlarmModel;
import endsstudios.alarmapp.Model.DataModelImpl;
import endsstudios.alarmapp.Presenter.DataPresenter;
import endsstudios.alarmapp.Presenter.DataPresenterImpl;
import endsstudios.alarmapp.R;
import endsstudios.alarmapp.Service.AlarmReceiver;
import endsstudios.alarmapp.Util.DBHandler;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.emptyTxt) TextView emptyTxt;
    @BindView(R.id.newAlarmLayout) LinearLayout setAlarmLayout;
    @BindView(R.id.alarmList) RecyclerView alarmList;

    private DataPresenter presenterAlarm;
    private RecyclerView.LayoutManager mLayoutManager;
    DBHandler db;
    CustomAdapter adapter;

    private PendingIntent pendingIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializer();
    }

    private void initializer() {
        db = new DBHandler(this);
        mLayoutManager = new LinearLayoutManager(this);
        alarmList.setLayoutManager(mLayoutManager);
        alarmList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        presenterAlarm = new DataPresenterImpl(this,new DataModelImpl(),db);
        startControlAlarm();
        getAlarmList();
    }

    private void startControlAlarm() {

        Intent dialogIntent = new Intent(getBaseContext(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, dialogIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmMgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60, pendingIntent);
    }

    @OnClick(R.id.newAlarmLayout)
    public void newAlarmClick(View view) {

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog timePicker;

        timePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                presenterAlarm.setAlarm(selectedHour,selectedMinute);
            }
        }, hour, minute, true);
        timePicker.setTitle("Set Alarm");
        timePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Set", timePicker);
        timePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel", timePicker);

        timePicker.show();


    }


    @Override
    public void getAlarmList() {
        presenterAlarm.getAlarmList();
    }

    @Override
    public void nothingAlarmList() {
        emptyTxt.setVisibility(View.VISIBLE);
        alarmList.setVisibility(View.INVISIBLE);
    }



    @Override
    public void fillAlarmList(List<AlarmModel> alarms) {
        emptyTxt.setVisibility(View.INVISIBLE);
        alarmList.setVisibility(View.VISIBLE);
        adapter = new CustomAdapter(this,alarms,presenterAlarm);
        alarmList.setAdapter(adapter);
    }

    @Override
    public void successUpdateAlarm(List<AlarmModel> newList)
    {
        adapter.setItems(newList);
        adapter.notifyDataSetChanged();
    }

}
