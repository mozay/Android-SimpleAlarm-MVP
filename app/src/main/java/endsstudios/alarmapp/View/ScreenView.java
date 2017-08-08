package endsstudios.alarmapp.View;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import endsstudios.alarmapp.R;

public class ScreenView extends Activity {

    @BindView(R.id.alarmCalTxt)
    TextView alarmsetTxt;
    @BindView(R.id.alarmCloseBtn)
    Button closeBtn;

    int hour,minute;
    MediaPlayer mMediaPlayer;
    Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_screen_view);
        ButterKnife.bind(this);

        Bundle b = getIntent().getExtras();
        if(b!=null)
        {
            hour = Integer.valueOf(b.getString("hoursound"));
            minute = Integer.valueOf(b.getString("minutesound"));
            alarmsetTxt.setText(hour+":"+minute);
        }



       try {
            Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(this, alert);
            final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                mMediaPlayer.setLooping(true);
                mMediaPlayer.prepare();
                mMediaPlayer.start();
            }
        } catch(Exception e) {
        }

        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(60000);
    }


    @OnClick(R.id.alarmCloseBtn)
    public void closeAlarm(View view) {
        mMediaPlayer.stop();
        v.cancel();
        finish();
    }
}
