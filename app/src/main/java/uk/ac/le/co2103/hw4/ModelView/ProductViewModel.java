package uk.ac.le.co2103.hw4.ModelView;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import uk.ac.le.co2103.hw4.DB.Product;
import uk.ac.le.co2103.hw4.Repo.ProductRepository;

public class ProductViewModel extends AndroidViewModel {
    private ProductRepository repository;
    private LiveData<List<Product>> allProducts;
    private LiveData<List<Product>> allProductsById;


    public ProductViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductRepository(application);
        allProducts = repository.getAllProducts();
    }


    //return --all methods
    public void insert(Product product){repository.insert(product);}

    public void update(Product product){repository.update(product);}

    public void delete(Product product){repository.delete(product);}

    public void deleteAllProducts(){repository.deleteAllProducts();}

    public LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }
}
