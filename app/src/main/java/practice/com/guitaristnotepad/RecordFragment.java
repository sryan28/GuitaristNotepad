package practice.com.guitaristnotepad;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;


public class RecordFragment extends Fragment {

    private MediaRecorder recorder;
    private MediaPlayer player;
    private String fileName;

    ImageView recordButton;
    ImageView playButton;

    boolean startRecording = true;
    boolean startPlaying = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.record_fragment, container, false);

        recordButton = (ImageView) rootView.findViewById(R.id.record);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(startRecording) {
                    recordButton.setImageResource(R.drawable.ic_action_name9);
                    onRecord(startRecording);
                } else {
                    recordButton.setImageResource(R.drawable.ic_action_name10);
                    stopRecording();
                }
                startRecording = !startRecording;
            }
        });

        playButton = (ImageView) rootView.findViewById(R.id.playstop);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(startPlaying) {
                    playButton.setImageResource(R.drawable.ic_action_name9);
                    startPlaying();
                } else {
                    playButton.setImageResource(R.drawable.ic_action_name7);
                    stopPlaying();
                }

            }
        });

        return rootView;
    }

    private void onRecord(boolean start) {
        if(start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            recorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        recorder.start();
    }

    private void stopRecording() {
        recorder.stop();
        recorder.release();
        recorder = null;
    }

    private void startPlaying() {
        player = new MediaPlayer();
        try {
            player.setDataSource(fileName);
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  void stopPlaying() {
        player.release();
        player = null;
    }

}