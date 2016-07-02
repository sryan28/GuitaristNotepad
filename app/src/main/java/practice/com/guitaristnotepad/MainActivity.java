package practice.com.guitaristnotepad;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class MainActivity extends FragmentActivity {

    ViewPager pager;
    private PagerAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null) {
            return;
        }

        ImageView explore = (ImageView) findViewById(R.id.explore);
        explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start new activity
                Intent i = new Intent(MainActivity.this, ExploreActivity.class);
                startActivity(i);

            }
        });

        final ImageView manip = (ImageView) findViewById(R.id.manipulate);

        manip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SoundManipulationActivity.class);
                startActivity(i);
            }
        });



        initPaging();
//        Intent i = new Intent(this, SoundManipulationActivity.class);
//        startActivity(i);


//        RecordFragment recordFragment = new RecordFragment();
//        NotepadFragment notepadFragment = new NotepadFragment();
//        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, notepadFragment).commit();
    }

    private void initPaging() {
        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, RecordFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, NotepadFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, SoundManipulationFragment.class.getName()));

        adapter = new PagerAdapter(this.getSupportFragmentManager(), fragments);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
    }

}