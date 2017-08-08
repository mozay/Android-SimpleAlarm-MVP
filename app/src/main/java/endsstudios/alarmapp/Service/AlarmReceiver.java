package endsstudios.alarmapp.Service;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import endsstudios.alarmapp.Model.AlarmModel;
import endsstudios.alarmapp.R;
import endsstudios.alarmapp.Util.DBHandler;
import endsstudios.alarmapp.View.MainActivity;
import endsstudios.alarmapp.View.ScreenView;

/**
 * Created by MEHMET on 7.08.2017.
 */

public class AlarmReceiver extends WakefulBroadcastReceiver {

    Context context;
    DBHandler db;
    List<AlarmModel> alarms ;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context=context;
        db = new DBHandler(context);
        startControl();
    }


    private void startControl() {
        alarms = db.getAllAlarm();
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);

        for (int i=0;i<alarms.size();i++)
        {
            if(hour==alarms.get(i).getHour() && minute==alarms.get(i).getMinute())
            {
                alarmCal(hour,minute);
            }
        }

    }

    public void alarmCal(int hour,int minute) {
        Intent i = new Intent(context, ScreenView.class);
        i.putExtra("hoursound",String.valueOf(hour));
        i.putExtra("minutesound",String.valueOf(minute));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED +
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD +
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON +
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        context.startActivity(i);
    }
}