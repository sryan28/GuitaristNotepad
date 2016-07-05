package practice.com.guitaristnotepad;


import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class SoundManipulationActivity extends FragmentActivity implements PedalListFragment.PedalSelected,
PedalsFragment.SendPedal {

    private ImageView backButton;
    SoundManipulationFragment smf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_manipulation);

        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);

        backButton = (ImageView) mainToolbar.findViewById(R.id.back_image);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        smf = new SoundManipulationFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fram1, smf).commit();
    }

    @Override
    public void sendPedalSelection(String selection) {
        Bundle b = new Bundle();
        b.putString("pedalCategory", selection);
        PedalsFragment pf = new PedalsFragment();
        pf.setArguments(b);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fram1, pf);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void sendPedal(String pedal) {
        //do something with pedal selection here
        getSupportFragmentManager().beginTransaction().replace(R.id.fram1, smf).commit();
    }

}