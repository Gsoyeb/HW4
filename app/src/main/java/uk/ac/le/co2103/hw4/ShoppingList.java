package uk.ac.le.co2103.hw4;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Category")
public class ShoppingList {
    @PrimaryKey(autoGenerate = true)
    private int listId;

    private String name;
    private int image;

    public ShoppingList(int listId, String name, int image) {
        this.listId = listId;
        this.name = name;
        this.image = image;
    }

    public int getListId() {
        return listId;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
