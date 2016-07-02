package practice.com.guitaristnotepad;


import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class SoundManipulationActivity extends FragmentActivity implements PedalListFragment.PedalSelected,
PedalsFragment.SendPedal {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_manipulation);

        SoundManipulationFragment smf = new SoundManipulationFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fram1, smf).commit();

        PedalListFragment plf = new PedalListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fram2, plf).commit();
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

//        getSupportFragmentManager().beginTransaction().replace(R.id.fram1, );
    }

}