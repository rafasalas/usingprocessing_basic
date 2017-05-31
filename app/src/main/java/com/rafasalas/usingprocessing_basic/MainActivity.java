package com.rafasalas.usingprocessing_basic;
import android.app.Fragment;
import android.app.FragmentManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.audiofx.Visualizer;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private Visualizer mVisualizer;
   public  float flujo=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getFragmentManager();
        android.app.Fragment fragment = new Sketch_1();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
    private void init(){

        mVisualizer = new Visualizer(0);
        mVisualizer.setEnabled(false);
        // mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
        mVisualizer.setCaptureSize(128);

        Visualizer.OnDataCaptureListener captureListener = new Visualizer.OnDataCaptureListener()
        {
            @Override
            public void onWaveFormDataCapture(Visualizer visualizer, byte[] bytes,
                                              int samplingRate)
            { float sum=0;
                //Log.v("wave_form", " " +bytes.length+" "+bytes[0]+" "+bytes[60]+" "+bytes[125]);
                for (int i = 0; i < bytes.length; i++) {
                    sum=sum+(float)bytes[i];
                }
                if (sum<-16383){sum=1;}else{sum=sum/1000;}
                //globals.setFlujo(flujo);
            }

            @Override
            public void onFftDataCapture(Visualizer visualizer, byte[] bytes,
                                         int samplingRate)
            {
                Log.v("fft", " " + bytes.length+bytes[0] + " "+bytes[60] + " " + bytes[125]);
            }
        };

        mVisualizer.setDataCaptureListener(captureListener,
                Visualizer.getMaxCaptureRate() / 2, true, true);

        // Enabled Visualizer and disable when we're done with the stream

        mVisualizer.setEnabled(true);
      /*  player.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer)
            {
                mVisualizer.setEnabled(false);
                mVisualizer.release();
            }
        });*/
    }


    public void release()
    {
        mVisualizer.release();
    }
}
