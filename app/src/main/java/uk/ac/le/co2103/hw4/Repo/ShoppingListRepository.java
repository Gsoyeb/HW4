package uk.ac.le.co2103.hw4.Repo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import uk.ac.le.co2103.hw4.DB.ShoppingDatabase;
import uk.ac.le.co2103.hw4.DB.ShoppingList;
import uk.ac.le.co2103.hw4.DB.ShoppingListDao;

public class ShoppingListRepository {
    private ShoppingListDao shoppingListDao;
    private LiveData<List<ShoppingList>> allShoppingLists;

    public ShoppingListRepository(Application application){
        ShoppingDatabase db = ShoppingDatabase.getInstance(application);
        shoppingListDao = db.shoppingListDao();
        allShoppingLists = shoppingListDao.getAllShoppingLists();
    }

    public LiveData<List<ShoppingList>> getAllShoppingLists() {
        return allShoppingLists;
    }


    public void insert(ShoppingList shoppingList){
        new InsertShoppingListAsyncTask(shoppingListDao).execute(shoppingList);
    }

    public void update(ShoppingList shoppingList){
        new UpdateShoppingListAsyncTask(shoppingListDao).execute(shoppingList);
    }

    public void delete(ShoppingList shoppingList){
        new DeleteShoppingListAsyncTask(shoppingListDao).execute(shoppingList);
    }

    public void deleteAllShoppingLists(){
        new DeleteAllShoppingListsAsyncTask(shoppingListDao).execute();
    }


    //AsyncTask-->Background Thread
    private static class InsertShoppingListAsyncTask extends AsyncTask<ShoppingList, Void, Void>{
        private ShoppingListDao shoppingListDao;

        private InsertShoppingListAsyncTask(ShoppingListDao shoppingListDao) {
            this.shoppingListDao = shoppingListDao;
        }

        @Override
        protected Void doInBackground(ShoppingList... shoppingLists) {
            this.shoppingListDao.insert(shoppingLists[0]);
            return null;
        }
    }

    private static class UpdateShoppingListAsyncTask extends AsyncTask<ShoppingList, Void, Void>{
        private ShoppingListDao shoppingListDao;

        private UpdateShoppingListAsyncTask(ShoppingListDao shoppingListDao) {
            this.shoppingListDao = shoppingListDao;
        }

        @Override
        protected Void doInBackground(ShoppingList... shoppingLists) {
            this.shoppingListDao.update(shoppingLists[0]);
            return null;
        }
    }

    private static class DeleteShoppingListAsyncTask extends AsyncTask<ShoppingList, Void, Void>{
        private ShoppingListDao shoppingListDao;

        private DeleteShoppingListAsyncTask(ShoppingListDao shoppingListDao) {
            this.shoppingListDao = shoppingListDao;
        }

        @Override
        protected Void doInBackground(ShoppingList... shoppingLists) {
            this.shoppingListDao.delete(shoppingLists[0]);
            return null;
        }
    }

    private static class DeleteAllShoppingListsAsyncTask extends AsyncTask<Void, Void, Void>{
        private ShoppingListDao shoppingListDao;

        private DeleteAllShoppingListsAsyncTask(ShoppingListDao shoppingListDao) {
            this.shoppingListDao = shoppingListDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            this.shoppingListDao.deleteAllShoppingLists();
            return null;
        }
    }

}
