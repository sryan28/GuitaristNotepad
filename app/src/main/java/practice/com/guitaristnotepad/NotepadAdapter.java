package practice.com.guitaristnotepad;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 6/28/2016.
 */

public class NotepadAdapter extends ArrayAdapter<NotepadEntry> {

    private Context context;
    ArrayList<NotepadEntry> notepadEntries;

    public NotepadAdapter(Context context, ArrayList<NotepadEntry> notepadEntries) {
        super(context, 0, notepadEntries);
//        this.context = context;
//        this.notepadEntries = notepadEntries;
    }

    public View getView(int pos, View convertView, ViewGroup parent) {
        NotepadEntry entry = getItem(pos);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.notepad_row, parent, false);
        }

        ImageView entryCategory = (ImageView) convertView.findViewById(R.id.entryCategory);
        TextView entryTitle = (TextView) convertView.findViewById(R.id.entryTitle);
        TextView dateRecorded = (TextView) convertView.findViewById(R.id.dateRecorded);

        entryCategory.setImageResource(R.drawable.ic_action_name6);
        entryTitle.setText(entry.getEntryTitle());
        dateRecorded.setText(entry.getDateRecorder());

        return convertView;
    }

}