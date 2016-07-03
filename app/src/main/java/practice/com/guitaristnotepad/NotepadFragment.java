package practice.com.guitaristnotepad;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NotepadFragment extends ListFragment {

    ArrayList<NotepadEntry> notes;
    NotepadAdapter adapter;
    ImageView notepadSymbol;
    ImageView recordSymbol;

    boolean isVisible = false;
    boolean isFirstLoad = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        notepadSymbol = (ImageView) getActivity().findViewById(R.id.notepad);
        recordSymbol = (ImageView) getActivity().findViewById(R.id.home);

        if(isVisible) {
            notepadSymbol.setImageResource(R.drawable.ic_action_name12);
            recordSymbol.setImageResource(R.drawable.ic_action_name);
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.notepad_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        notes = new ArrayList<>();

        NotepadEntry entry = new NotepadEntry("Nu metal", "Metal", "1/12/47");
        NotepadEntry entry2 = new NotepadEntry("Nu metal2", "Metal", "1/12/47");
        NotepadEntry entry3 = new NotepadEntry("Nu metal3", "Metal", "1/12/47");
        NotepadEntry entry4 = new NotepadEntry("Nu metal4", "Metal", "1/12/47");

        notes.add(entry);
        notes.add(entry2);
        notes.add(entry3);
        notes.add(entry4);

        adapter = new NotepadAdapter(getActivity(), notes);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent i = new Intent(getActivity(), SoundManipulationActivity.class);
        startActivity(i);

        Toast.makeText(getActivity(), "hello", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser && isFirstLoad) {
            isVisible = true;
            isFirstLoad = !isFirstLoad;
        } else if(isVisibleToUser && !isFirstLoad) {
            setNavigationImages();
        } else {
            isVisible = false;
        }
    }

    private  void setNavigationImages() {
        notepadSymbol.setImageResource(R.drawable.ic_action_name12);
        recordSymbol.setImageResource(R.drawable.ic_action_name);
    }

}