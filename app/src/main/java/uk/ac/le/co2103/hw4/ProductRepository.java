package uk.ac.le.co2103.hw4;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import uk.ac.le.co2103.hw4.DB.Product;
import uk.ac.le.co2103.hw4.DB.ProductDao;
import uk.ac.le.co2103.hw4.DB.ShoppingDatabase;

public class ProductRepository {
    private ProductDao productDao;
    private LiveData<List<Product>> allProducts;

    public ProductRepository(Application application){
        ShoppingDatabase db = ShoppingDatabase.getInstance(application);
        productDao = db.productDao();
        allProducts = productDao.getAllProducts();
    }

    public LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }

    public void insert(Product product){
        new InsertProductAsyncTask(productDao).execute(product);
    }

    public void update(Product product){
        new UpdateProductAsyncTask(productDao).execute(product);
    }

    public void delete(Product product){
        new DeleteProductAsyncTask(productDao).execute(product);
    }

    public void deleteAllProducts(){
        new DeleteAllProductsAsyncTask(productDao).execute();
    }

    //AsyncTask-->Background Thread
    private static class InsertProductAsyncTask extends AsyncTask<Product, Void, Void>{
        private ProductDao productDao;

        public InsertProductAsyncTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            this.productDao.insert(products[0]);;
            return null;
        }
    }

    private static class UpdateProductAsyncTask extends AsyncTask<Product, Void, Void>{
        private ProductDao productDao;

        public UpdateProductAsyncTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            this.productDao.update(products[0]);;
            return null;
        }
    }

    private static class DeleteProductAsyncTask extends AsyncTask<Product, Void, Void>{
        private ProductDao productDao;

        public DeleteProductAsyncTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            this.productDao.delete(products[0]);;
            return null;
        }
    }

    private static class DeleteAllProductsAsyncTask extends AsyncTask<Void, Void, Void>{
        private ProductDao productDao;

        public DeleteAllProductsAsyncTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            this.productDao.deleteAllProducts();;
            return null;
        }
    }
}
