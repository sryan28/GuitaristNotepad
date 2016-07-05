package practice.com.guitaristnotepad;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PedalListFragment extends ListFragment {

    String[] src = {"Distortion", "Delay/Reverb", "Tempo", "Other", "Other2"};

    PedalSelected pedalSelected;

    public interface PedalSelected {
        void sendPedalSelection(String pedal);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pedal_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, src);
        setListAdapter(adapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        pedalSelected.sendPedalSelection(src[position]);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        pedalSelected = (PedalSelected) context;
    }

}