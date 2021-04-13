package uk.ac.le.co2103.hw4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import uk.ac.le.co2103.hw4.Adapter.ProductAdapter;
import uk.ac.le.co2103.hw4.DB.Product;
import uk.ac.le.co2103.hw4.ModelView.ProductViewModel;

public class ShoppingListActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "uk.edu.le.co2103.hw4.ID";

    private ProductViewModel productViewModel;

    public static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        Intent intent = getIntent();
        id = intent.getIntExtra(EXTRA_ID,-1);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_product);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShoppingListActivity.this));
        recyclerView.setHasFixedSize(true);

        final ProductAdapter adapter = new ProductAdapter();
        recyclerView.setAdapter(adapter);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getAllProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter.setProducts(products);
            }
        });


    }

//    public void setProducts(){
//        Intent intent = getIntent();
//        int id = intent.getIntExtra(EXTRA_ID,-1);
//        for (Product product : productViewModel.getAllProducts()){} //problem. Need to do it from the DAO
//    }
}