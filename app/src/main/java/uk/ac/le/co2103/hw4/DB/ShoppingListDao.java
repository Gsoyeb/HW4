package uk.ac.le.co2103.hw4.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ShoppingListDao {
    @Insert
    void insert(ShoppingList shoppingList);

    @Update
    void update(ShoppingList shoppingList);

    @Delete
    void delete(ShoppingList shoppingList);

    @Query("delete from Category")
    void deleteAllShoppingLists();

    @Query("select * from Category order by name")
    LiveData<List<ShoppingList>> getAllShoppingLists();

}
