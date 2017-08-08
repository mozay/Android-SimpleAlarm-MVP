package endsstudios.alarmapp.Presenter;

import java.util.List;

import endsstudios.alarmapp.Model.AlarmModel;

/**
 * Created by MEHMET on 7.08.2017.
 */

public interface UpdateListener {
    void onSuccessUpdate(List<AlarmModel> newList);
}
