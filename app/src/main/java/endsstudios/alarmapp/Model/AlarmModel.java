package endsstudios.alarmapp.Model;

/**
 * Created by MEHMET on 7.08.2017.
 */

public class AlarmModel {

    int _id;
    int hour ;
    int minute;

    // Empty constructor
    public AlarmModel(){

    }

    public AlarmModel(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }

    // constructor
    public AlarmModel(int id, int hour, int minute){
        this._id = id;
        this.hour = hour;
        this.minute = minute;
    }

    public int getID(){
        return this._id;
    }


    public void setID(int id){
        this._id = id;
    }


    public int getHour(){
        return this.hour;
    }


    public void setHour(int hour){
        this.hour = hour;
    }


    public int getMinute(){
        return this.minute;
    }


    public void setMinute(int minute){
        this.minute = minute;
    }
}