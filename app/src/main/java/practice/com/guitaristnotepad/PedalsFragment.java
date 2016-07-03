package practice.com.guitaristnotepad;

import android.content.Context;
import android.media.Image;
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

    ImageView pedalImage;
    ImageView closeImage;
    ImageView prevPedal;
    ImageView nextPedal;
    ImageView applyFilter;

    TextView pedalDescription;
    TextView currentPedal;

    ArrayList<Pedal> pedalsList;

    String pedalCategory;

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
                updateDisplay(currentpos--);
            }
        });
        nextPedal = (ImageView) rootView.findViewById(R.id.next_button);
        nextPedal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay(currentpos++);
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
            DBHandler dbHandler = new DBHandler(getContext(), null, null, 2);
            pedalsList = dbHandler.getAllPedals(params[0]);

            return pedalsList;
        }

        @Override
        protected void onPostExecute(List<Pedal> pedals) {
            super.onPostExecute(pedals);
            pedalDescription.setText(pedals.get(0).getDescription() + pedals.get(0).getDescription()
                    +pedals.get(0).getDescription());

            String pedalName = pedals.get(0).getName();
            currentPedal.setText(pedalName);

            //decode from drawable
            pedalImage.setImageResource(R.drawable.d_pedal);
        }
    }

    public void updateDisplay(int pos) {
        if(pos < 0) {
            currentpos = 0;
            return;
        }  else if(pos > pedalsList.size() - 1) {
            currentpos = pedalsList.size();
            return;
        } else {
            pedalDescription.setText(pedalsList.get(pos).getDescription());
//            pedalImage.setImageResource(pedalsList.get(pos).getImageId());
            currentPedal.setText(pedalsList.get(pos).getName());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        send = (SendPedal) context;
    }

}