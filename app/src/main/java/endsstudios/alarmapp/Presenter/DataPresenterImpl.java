package endsstudios.alarmapp.Presenter;

import java.util.List;

import endsstudios.alarmapp.Model.AlarmModel;
import endsstudios.alarmapp.Model.DataModel;
import endsstudios.alarmapp.Util.DBHandler;
import endsstudios.alarmapp.View.MainView;

/**
 * Created by MEHMET on 7.08.2017.
 */

public class DataPresenterImpl implements DataPresenter {

    private final MainView view;
    private final DataModel model;
    public DBHandler db;


    public DataPresenterImpl(MainView view, DataModel model,DBHandler db) {
        this.view = view;
        this.model = model;
        this.db = db;
    }


    @Override
    public void getAlarmList() {
        model.getAlarms(db, new ReadListener() {

            @Override
            public void onSuccessGetAllAlarms(List<AlarmModel> alarms) {
                view.fillAlarmList(alarms);
            }

            @Override
            public void onFailGetAllAlarms() {
                view.nothingAlarmList();
            }
        });

    }

    @Override
    public void setAlarm(int hour,int minute) {
       model.setAlarm(db, hour, minute, new SetListener() {
           @Override
           public void onSuccessSetAlarm(List<AlarmModel> newList) {
                view.successUpdateAlarm(newList);
           }
       });
    }

    @Override
    public void deleteAlarm(int id) {
        model.deleteAlarm(db, id, new DeleteListener() {
            @Override
            public void onSuccessDelete(List<AlarmModel> newList) {
                view.successUpdateAlarm(newList);
            }
        });
    }

    @Override
    public void updateAlarm(int id, int hour, int minute) {
        model.updateAlarm(db, id, hour, minute, new UpdateListener() {
            @Override
            public void onSuccessUpdate(List<AlarmModel> newList) {
                view.successUpdateAlarm(newList);
            }
        });
    }
}
