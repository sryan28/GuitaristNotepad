package practice.com.guitaristnotepad;

import android.widget.ImageView;

/**
 * Created by user on 6/28/2016.
 */

public class NotepadEntry {

    private String entryTitle;
    private String entryCategory;
    private String dateRecorder;

    public NotepadEntry(String entryTitle, String entryCategory, String dateRecorder) {
        this.entryTitle = entryTitle;
        this.entryCategory = entryCategory;
        this.dateRecorder = dateRecorder;
    }

    public String getEntryTitle() {
        return entryTitle;
    }

    public void setEntryTitle(String entryTitle) {
        this.entryTitle = entryTitle;
    }

    public String getEntryCategory() {
        return entryCategory;
    }

    public void setEntryCategory(String entryCategory) {
        this.entryCategory = entryCategory;
    }

    public String getDateRecorder() {
        return dateRecorder;
    }

    public void setDateRecorder(String dateRecorder) {
        this.dateRecorder = dateRecorder;
    }
}
