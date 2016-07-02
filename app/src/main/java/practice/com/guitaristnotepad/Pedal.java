package practice.com.guitaristnotepad;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by user on 6/30/2016.
 */

public class Pedal {

    private String name;
    private int imageId;
    private String description;
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String type) {
        this.category = type;
    }

    public Pedal(String name, int imageId, String description, String category) {
        this.name = name;
        this.imageId = imageId;
        this.description = description;
        this.category = category;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
