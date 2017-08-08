package endsstudios.alarmapp.Presenter;

import java.util.List;

import endsstudios.alarmapp.Model.AlarmModel;

/**
 * Created by MEHMET on 7.08.2017.
 */

public interface ReadListener {
    void onSuccessGetAllAlarms(List<AlarmModel> alarms);
    void onFailGetAllAlarms();
}
