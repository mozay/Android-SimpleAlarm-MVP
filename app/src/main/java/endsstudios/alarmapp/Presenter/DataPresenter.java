package endsstudios.alarmapp.Presenter;

import endsstudios.alarmapp.Util.DBHandler;

/**
 * Created by MEHMET on 7.08.2017.
 */

public interface DataPresenter {
    void getAlarmList();
    void setAlarm(int hour,int minute);
    void deleteAlarm(int id);
    void updateAlarm(int id,int hour,int minute);
}
