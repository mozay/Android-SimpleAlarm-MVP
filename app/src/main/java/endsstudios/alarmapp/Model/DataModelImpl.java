package endsstudios.alarmapp.Model;

import java.util.List;

import endsstudios.alarmapp.Presenter.DeleteListener;
import endsstudios.alarmapp.Presenter.ReadListener;
import endsstudios.alarmapp.Presenter.SetListener;
import endsstudios.alarmapp.Presenter.UpdateListener;
import endsstudios.alarmapp.Util.DBHandler;

/**
 * Created by MEHMET on 7.08.2017.
 */

public class DataModelImpl implements DataModel {


    @Override
    public void getAlarms(DBHandler db,ReadListener listener) {

            List<AlarmModel> alarms = db.getAllAlarm();
            if(alarms.size()!=0)
                listener.onSuccessGetAllAlarms(alarms);
            else
                listener.onFailGetAllAlarms();
    }

    @Override
    public void setAlarm(DBHandler db, int hour,int minute, SetListener listener) {

            db.addAlarm(new AlarmModel(hour,minute));
            listener.onSuccessSetAlarm(db.getAllAlarm());

    }

    @Override
    public void deleteAlarm(DBHandler db, int id, DeleteListener listener) {
            db.deleteContact(id);
            listener.onSuccessDelete(db.getAllAlarm());
    }

    @Override
    public void updateAlarm(DBHandler db, int id, int hour, int minute, UpdateListener listener) {
            db.updateAlarm(id,hour,minute);
            listener.onSuccessUpdate(db.getAllAlarm());
    }
}
