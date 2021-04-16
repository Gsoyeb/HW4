package uk.ac.le.co2103.hw4.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Category")
public class ShoppingList {
    @PrimaryKey(autoGenerate = true)
    private int listId;

    private String name;
    private String image;

    public ShoppingList(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public int getListId() {
        return listId;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }
}
