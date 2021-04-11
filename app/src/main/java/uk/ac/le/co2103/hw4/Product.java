package uk.ac.le.co2103.hw4;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = ShoppingList.class, parentColumns = "listId", childColumns = "listId", onDelete = ForeignKey.CASCADE))
public class Product {
    @PrimaryKey
    @NonNull
    private String name;

    private int quantity;
    private String unit;

    private int listId;

    public Product(String name, int quantity, String unit, int listId) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public int getListId() {
        return listId;
    }
}
