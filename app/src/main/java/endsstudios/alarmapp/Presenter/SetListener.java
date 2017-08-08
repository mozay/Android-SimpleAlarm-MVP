package endsstudios.alarmapp.Presenter;

import java.util.List;

import endsstudios.alarmapp.Model.AlarmModel;
import endsstudios.alarmapp.Util.DBHandler;

/**
 * Created by MEHMET on 7.08.2017.
 */

public interface SetListener {
    void onSuccessSetAlarm(List<AlarmModel> newList);
}
