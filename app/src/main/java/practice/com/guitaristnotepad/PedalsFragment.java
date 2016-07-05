package practice.com.guitaristnotepad;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PedalsFragment extends Fragment {

    private ImageView pedalImage;
    private ImageView prevPedal;
    private ImageView nextPedal;
    private ImageView applyFilter;

    private TextView pedalDescription;
    private TextView currentPedal;

    private ArrayList<Pedal> pedalsList;

    private String pedalCategory;

    SendPedal send;

    private int currentpos = 0;

    public interface SendPedal {
        void sendPedal(String pedal);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pedalCategory = getArguments().getString("pedalCategory");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.pedals_fragment, container, false);

        prevPedal = (ImageView) rootView.findViewById(R.id.prev_button);
        prevPedal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay("prev");
            }
        });
        nextPedal = (ImageView) rootView.findViewById(R.id.next_button);
        nextPedal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay("next");
            }
        });
        applyFilter = (ImageView) rootView.findViewById(R.id.apply_filter);
        applyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send.sendPedal(currentPedal.getText().toString());
                System.out.println(currentPedal.getText().toString());
            }
        });

        pedalDescription = (TextView) rootView.findViewById(R.id.pedal_info);
        pedalImage = (ImageView) rootView.findViewById(R.id.pedal_image);

        currentPedal = (TextView) rootView.findViewById(R.id.pedal_name);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        PedalTask pt = new PedalTask();
        pt.execute(pedalCategory);
    }

    private class PedalTask extends AsyncTask<String, Void, List<Pedal>> {
        @Override
        protected List<Pedal> doInBackground(String... params) {
            pedalsList = new ArrayList<>();
            DBHandler dbHandler = new DBHandler(getContext(), null, null, 1);
//            dbHandler.addPedal(new Pedal("Fuzz", R.drawable.pedal, "A killer sound" +
//                    "with some cool fuzz effects is perfect for that gain sound sound" +
//                    "that all guitarist search for", "Distortion"));
//
//            dbHandler.addPedal(new Pedal("Reverb", R.drawable.d_pedal, "A killer sound" +
//                    "with some cool fuzz effects is perfect for that gain sound sound" +
//                    "that all guitarist search for", "Delay/Reverb"));
//
//            dbHandler.addPedal(new Pedal("Delay", R.drawable.distortion_pedal, "A killer sound" +
//                    "with some cool fuzz effects is perfect for that gain sound sound" +
//                    "that all guitarist search for", "Delay/Reverb"));
//
//            dbHandler.addPedal(new Pedal("Tube Screamer", R.drawable.ic_action_name, "A killer sound" +
//                    "that can only be found on the tube screamer", "Distortion"));
//
//            dbHandler.addPedal(new Pedal("Echo Delay", R.drawable.ic_action_name2, "A killer sound" +
//                    "with some cool fuzz effects is perfect for that gain sound sound" +
//                    "that all guitarist search for", "Delay/Reverb"));
//
//            dbHandler.addPedal(new Pedal("Overdrive", R.drawable.ic_action_name3, "A killer sound" +
//                    "with some cool fuzz effects is perfect for that gain sound sound" +
//                    "that all guitarist search for", "Distortion"));
            pedalsList = dbHandler.getAllPedals(params[0]);

            return pedalsList;
        }

        @Override
        protected void onPostExecute(List<Pedal> pedals) {
            super.onPostExecute(pedals);
            pedalDescription.setText(pedals.get(currentpos).getDescription());

            String pedalName = pedals.get(currentpos).getName();
            currentPedal.setText(pedalName);

            pedalImage.setImageResource(pedals.get(currentpos).getImageId());
        }
    }

    public void updateDisplay(String direction) {
        if(direction.equals("next")) {
            currentpos++;
        } else {
            currentpos--;
        }
        if(currentpos < 0) {
            currentpos = pedalsList.size() - 1;
        } else if(currentpos >= pedalsList.size()) {
            currentpos = 0;
        }

        pedalDescription.setText(pedalsList.get(currentpos).getDescription());
        pedalImage.setImageResource(pedalsList.get(currentpos).getImageId());
        currentPedal.setText(pedalsList.get(currentpos).getName());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        send = (SendPedal) context;
    }

}