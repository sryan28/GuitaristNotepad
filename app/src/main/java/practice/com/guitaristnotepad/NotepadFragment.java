package practice.com.guitaristnotepad;

import android.os.Bundle;

import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NotepadFragment extends ListFragment {

    ArrayList<NotepadEntry> notes;
    NotepadAdapter adapter;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("tag", "here");
//        super.onCreate(savedInstanceState);



//        View root = inflater.inflate(R.layout.notepad_fragment, container, false);
//
//        listView = (ListView) root.findViewById(R.id.notepadList);
//
//        NotepadEntry entry = new NotepadEntry("Nu metal", "Metal", "1/12/47");
//        NotepadEntry entry2 = new NotepadEntry("Nu metal", "Metal", "1/12/47");
//        NotepadEntry entry3 = new NotepadEntry("Nu metal", "Metal", "1/12/47");
//        NotepadEntry entry4 = new NotepadEntry("Nu metal", "Metal", "1/12/47");
//
//        entries.add(entry);
//        entries.add(entry2);
//        entries.add(entry3);
//        entries.add(entry4);
//
//        NotepadAdapter adapter = new NotepadAdapter(getActivity(), entries);
//
//        setListAdapter(adapter);


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.notepad_fragment, container, false);


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        notes = new ArrayList<>();
//
//
        NotepadEntry entry = new NotepadEntry("Nu metal", "Metal", "1/12/47");
        NotepadEntry entry2 = new NotepadEntry("Nu metal2", "Metal", "1/12/47");
        NotepadEntry entry3 = new NotepadEntry("Nu metal3", "Metal", "1/12/47");
        NotepadEntry entry4 = new NotepadEntry("Nu metal4", "Metal", "1/12/47");

        notes.add(entry);
        notes.add(entry2);
        notes.add(entry3);
        notes.add(entry4);
//
        adapter = new NotepadAdapter(getActivity(), notes);
        setListAdapter(adapter);
//
//        listView.setAdapter(adapter);
//        setRetainInstance(true);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(getActivity(), "hello", Toast.LENGTH_LONG).show();
    }

}