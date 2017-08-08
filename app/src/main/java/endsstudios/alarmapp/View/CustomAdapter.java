package endsstudios.alarmapp.View;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import endsstudios.alarmapp.Model.AlarmModel;
import endsstudios.alarmapp.Model.DataModelImpl;
import endsstudios.alarmapp.Presenter.DataPresenter;
import endsstudios.alarmapp.Presenter.DataPresenterImpl;
import endsstudios.alarmapp.R;
import endsstudios.alarmapp.Util.DBHandler;

/**
 * Created by MEHMET on 7.08.2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    private Context context;
    List<AlarmModel> alarmList;

    private DataPresenter presenterAlarm;
    private DBHandler db;


    public CustomAdapter(Context context, List<AlarmModel> alarmList, DataPresenter presenterAlarm) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.alarmList=alarmList;
        this.presenterAlarm = presenterAlarm;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_item_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final AlarmModel model = alarmList.get(position);
        holder.alarmItemTxt.setText(model.getHour()+" : "+model.getMinute());

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenterAlarm.deleteAlarm(model.getID());
            }
        });

        holder.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog timePicker;

                timePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        presenterAlarm.updateAlarm(model.getID(),selectedHour,selectedMinute);
                    }
                }, hour, minute, true);
                timePicker.setTitle("Update Alarm");
                timePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Set", timePicker);
                timePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel", timePicker);

                timePicker.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }
    public void setItems(List<AlarmModel> alarms) {
        alarmList.clear();
        this.alarmList = alarms;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView alarmItemTxt;
        Button updateBtn,deleteBtn;

        public MyViewHolder(View itemView) {
            super(itemView);
            alarmItemTxt = (TextView)itemView.findViewById(R.id.alarmItem);
            updateBtn = (Button) itemView.findViewById(R.id.updateItem);
            deleteBtn = (Button)itemView.findViewById(R.id.deleteItem);
        }
    }
}