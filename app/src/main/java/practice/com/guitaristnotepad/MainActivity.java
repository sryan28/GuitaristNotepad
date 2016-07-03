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

    private PagerAdapter adapter;
    ImageView recordSymbol;
    ImageView notepadSymbol;

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

//        recordSymbol = (ImageView) findViewById(R.id.home);
//        recordSymbol.setImageResource(R.drawable.ic_action_name11);
//
//        notepadSymbol = (ImageView) findViewById(R.id.notepad);

        initPaging();
    }

    private void initPaging() {
        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, RecordFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, NotepadFragment.class.getName()));

        adapter = new PagerAdapter(this.getSupportFragmentManager(), fragments);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
    }

}