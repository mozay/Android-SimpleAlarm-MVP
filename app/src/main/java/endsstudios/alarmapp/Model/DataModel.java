package endsstudios.alarmapp.Model;

import endsstudios.alarmapp.Presenter.DeleteListener;
import endsstudios.alarmapp.Presenter.ReadListener;
import endsstudios.alarmapp.Presenter.SetListener;
import endsstudios.alarmapp.Presenter.UpdateListener;
import endsstudios.alarmapp.Util.DBHandler;

/**
 * Created by MEHMET on 7.08.2017.
 */

public interface DataModel {
    void getAlarms(DBHandler db,ReadListener listener);
    void setAlarm(DBHandler db,int hour,int minute,SetListener listener);
    void deleteAlarm(DBHandler db,int id,DeleteListener listener);
    void updateAlarm(DBHandler db,int id,int hour,int minute,UpdateListener listener);
}
