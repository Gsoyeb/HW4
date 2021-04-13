package uk.ac.le.co2103.hw4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uk.ac.le.co2103.hw4.Adapter.ProductAdapter;
import uk.ac.le.co2103.hw4.DB.Product;
import uk.ac.le.co2103.hw4.ModelView.ProductViewModel;

public class ShoppingListActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "uk.edu.le.co2103.hw4.ID";

    private ProductViewModel productViewModel;
    private int sizeProducts;
    private int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        Intent intent = getIntent();
        ID = intent.getIntExtra(EXTRA_ID,-1);
        if (ID == -1){
            Toast.makeText(ShoppingListActivity.this, "ID issue",Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(ShoppingListActivity.this, "Id = "+String.valueOf(ID),Toast.LENGTH_SHORT).show();

        RecyclerView recyclerView = findViewById(R.id.recycler_view_product);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShoppingListActivity.this));
        recyclerView.setHasFixedSize(true);

        final ProductAdapter adapter = new ProductAdapter();
        recyclerView.setAdapter(adapter);
        sizeProducts = adapter.getItemCount();

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getAllProductsById(ID).observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter.setProducts(products);
            }
        });

    }
}