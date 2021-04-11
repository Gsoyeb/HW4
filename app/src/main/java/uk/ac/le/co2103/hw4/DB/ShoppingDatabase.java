package uk.ac.le.co2103.hw4.DB;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {ShoppingList.class, Product.class}, version = 1, exportSchema = false)
public abstract class ShoppingDatabase extends RoomDatabase {
    private static ShoppingDatabase INSTANCE;

    public abstract ShoppingListDao shoppingListDao();
    public abstract ProductDao productDao();

    public static synchronized ShoppingDatabase getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ShoppingDatabase.class, "Shopping_database").fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    //AsyncTask--Main thread not available
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ShoppingListDao shoppingListDao;
        private ProductDao productDao;

        private PopulateDbAsyncTask(ShoppingDatabase db){
            this.shoppingListDao = db.shoppingListDao();
            this.productDao = db.productDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //Shopping list
            shoppingListDao.insert(new ShoppingList("Electronics",0));
            shoppingListDao.insert(new ShoppingList("Grocery", 0));
            shoppingListDao.insert(new ShoppingList("Watches", 0));

            //Product
            productDao.insert(new Product("MacBook",1,"Unit",1));
            productDao.insert(new Product("iPad",2,"Unit",1));
            productDao.insert(new Product("Rice",1,"Kilogram",2));
            productDao.insert(new Product("Milk",3,"Liter",2));
            productDao.insert(new Product("Rolex",1,"Unit",3));
            productDao.insert(new Product("Hublot",1,"Unit",3));

            return null;
        }
    }
}
