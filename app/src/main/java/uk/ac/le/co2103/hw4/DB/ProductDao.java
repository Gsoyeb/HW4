package uk.ac.le.co2103.hw4.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    void insert(Product product);

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);

    @Query("delete from Product")
    void deleteAllProducts();

    @Query("select * from Product order by name")
    LiveData<List<Product>> getAllProducts();
}
