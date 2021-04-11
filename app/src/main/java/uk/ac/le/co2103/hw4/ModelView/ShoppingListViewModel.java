package uk.ac.le.co2103.hw4.ModelView;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import uk.ac.le.co2103.hw4.DB.ShoppingList;
import uk.ac.le.co2103.hw4.Repo.ShoppingListRepository;

public class ShoppingListViewModel extends AndroidViewModel {
    private ShoppingListRepository repository;
    private LiveData<List<ShoppingList>> allShoppingLists;

    public ShoppingListViewModel(@NonNull Application application){
        super(application);
        repository = new ShoppingListRepository(application);
        allShoppingLists = repository.getAllShoppingLists();
    }

    //return --all methods
    public void insert(ShoppingList shoppingList){repository.insert(shoppingList);}

    public void update(ShoppingList shoppingList){repository.update(shoppingList);}

    public void delete(ShoppingList shoppingList){repository.delete(shoppingList);}

    public void deleteAllShoppingLists(){repository.deleteAllShoppingLists();}

    public LiveData<List<ShoppingList>> getAllShoppingLists() {return allShoppingLists;}
}
