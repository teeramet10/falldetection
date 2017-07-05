package app.developteam.detectfall.myapplication;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

/**
 * Created by barbie on 3/2/2017.
 */

public class Alarm {

    public static int siren = R.raw.siren;

    Context context;
    MediaPlayer mediaPlayer;

    public Alarm(Context context) {
        this.context = context;
        mediaPlayer = MediaPlayer.create(context, R.raw.siren);
    }

    public void openAlarm() {
        AudioManager audioManager = (AudioManager) context.getSystemService(context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);

        if(!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            mediaPlayer.isLooping();
        }
    }

    public void closeAlarm(){
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }
}
