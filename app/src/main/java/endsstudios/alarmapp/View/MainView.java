package endsstudios.alarmapp.View;

import java.util.List;

import endsstudios.alarmapp.Model.AlarmModel;

/**
 * Created by MEHMET on 7.08.2017.
 */

public interface MainView {

    void getAlarmList();
    void nothingAlarmList();
    void fillAlarmList(List<AlarmModel> alarms);
    void successUpdateAlarm(List<AlarmModel> newlist);
}
