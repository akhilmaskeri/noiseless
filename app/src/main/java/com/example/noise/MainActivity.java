package com.example.noise;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.net.MulticastSocket;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer players[];
    private Button mediaButtons[];
    private SeekBar seekBar[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        players = new MediaPlayer[16];
        mediaButtons = new Button[16];
        seekBar = new SeekBar[16];

        seekBar[0] = (SeekBar)findViewById(R.id.brownnoise_seekbar);
        seekBar[1] = (SeekBar)findViewById(R.id.coffee_seekbar);
        seekBar[2] = (SeekBar)findViewById(R.id.fan_seekbar);
        seekBar[3] = (SeekBar)findViewById(R.id.fire_seekbar);
        seekBar[4] = (SeekBar)findViewById(R.id.forest_seekbar);
        seekBar[5] = (SeekBar)findViewById(R.id.leaves_seekbar);
        seekBar[6] = (SeekBar)findViewById(R.id.pinknoise_seekbar);
        seekBar[7] = (SeekBar)findViewById(R.id.rain_seekbar);
        seekBar[8] = (SeekBar)findViewById(R.id.seaside_seekbar);
        seekBar[9] = (SeekBar)findViewById(R.id.summernight_seekbar);
        seekBar[10] = (SeekBar)findViewById(R.id.thunderstorm_seekbar);
        seekBar[11] = (SeekBar)findViewById(R.id.train_seekbar);
        seekBar[12] = (SeekBar)findViewById(R.id.water_seekbar);
        seekBar[13] = (SeekBar)findViewById(R.id.waterstream_seekbar);
        seekBar[14] = (SeekBar)findViewById(R.id.whitenoise_seekbar);
        seekBar[15] = (SeekBar)findViewById(R.id.wind_seekbar);


        players[0] = MediaPlayer.create(getApplicationContext(), R.raw.brownnoise);
        players[1] = MediaPlayer.create(getApplicationContext(), R.raw.coffee);
        players[2] = MediaPlayer.create(getApplicationContext(), R.raw.fan);
        players[3] = MediaPlayer.create(getApplicationContext(), R.raw.fire);
        players[4] = MediaPlayer.create(getApplicationContext(), R.raw.forest);
        players[5] = MediaPlayer.create(getApplicationContext(), R.raw.leaves);
        players[6] = MediaPlayer.create(getApplicationContext(), R.raw.pinknoise);
        players[7] = MediaPlayer.create(getApplicationContext(), R.raw.rain);
        players[8] = MediaPlayer.create(getApplicationContext(), R.raw.seaside);
        players[9] = MediaPlayer.create(getApplicationContext(), R.raw.summernight);
        players[10] = MediaPlayer.create(getApplicationContext(), R.raw.thunderstorm);
        players[11] = MediaPlayer.create(getApplicationContext(), R.raw.train);
        players[12] = MediaPlayer.create(getApplicationContext(), R.raw.water);
        players[13] = MediaPlayer.create(getApplicationContext(), R.raw.waterstream);
        players[14] = MediaPlayer.create(getApplicationContext(), R.raw.whitenoise);
        players[15] = MediaPlayer.create(getApplicationContext(), R.raw.wind);

        mediaButtons[0] = (Button)findViewById(R.id.brownnoise);
        mediaButtons[1] = (Button)findViewById(R.id.coffee);
        mediaButtons[2] = (Button)findViewById(R.id.fan);
        mediaButtons[3] = (Button)findViewById(R.id.fire);
        mediaButtons[4] = (Button)findViewById(R.id.forest);
        mediaButtons[5] = (Button)findViewById(R.id.leaves);
        mediaButtons[6] = (Button)findViewById(R.id.pinknnoise);
        mediaButtons[7] = (Button)findViewById(R.id.rain);
        mediaButtons[8] = (Button)findViewById(R.id.seaside);
        mediaButtons[9] = (Button)findViewById(R.id.summernight);
        mediaButtons[10] = (Button)findViewById(R.id.thunderstorm);
        mediaButtons[11] = (Button)findViewById(R.id.train);
        mediaButtons[12] = (Button)findViewById(R.id.water);
        mediaButtons[13] = (Button)findViewById(R.id.waterstream);
        mediaButtons[14] = (Button)findViewById(R.id.whitenoise);
        mediaButtons[15] = (Button)findViewById(R.id.wind);

        for(int i=0;i<16;i++){
            addListener(mediaButtons[i],players[i],seekBar[i]);
            addControl(seekBar[i],players[i]);
            setDefaultVolume(seekBar[i], players[i]);
        }

    }

    public float getVolume(int progress){
        float log1=(float)(Math.log(progress)/Math.log(50));
        return log1;
    }

    public void setDefaultVolume(final SeekBar sb, final MediaPlayer p){
        sb.setProgress(25);
        float volume = getVolume(25);
        p.setVolume(volume, volume);
    }

    public void addListener(final Button b, final MediaPlayer player, final SeekBar seekBar){

        b.setOnClickListener(new View.OnClickListener() {

            Drawable drawable = null;

            @Override
            public void onClick(View v) {

                if(player.isPlaying()){

                    b.setBackgroundColor(Color.WHITE);
                    b.setTextColor(Color.GRAY);
                    player.pause();
                    seekBar.setEnabled(false);
                }
                else{
                    try{

                        b.setBackgroundColor(Color.GRAY);
                        b.setTextColor(Color.WHITE);
                        player.start();
                        player.setLooping(true);
                        seekBar.setEnabled(true);
                    }
                    catch (Exception e){}
                }
            }
        });

    }

    public void addControl(final SeekBar sb, final MediaPlayer player){

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float volume = getVolume(progress);
                player.setVolume(volume, volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

    }

}
