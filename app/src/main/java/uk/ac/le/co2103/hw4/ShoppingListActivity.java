package uk.ac.le.co2103.hw4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import uk.ac.le.co2103.hw4.Adapter.ProductAdapter;
import uk.ac.le.co2103.hw4.DB.Product;
import uk.ac.le.co2103.hw4.ModelView.ProductViewModel;

public class ShoppingListActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "uk.edu.le.co2103.hw4.ID";
    public static final int ADD_PRODUCT_ACTIVITY_REQUEST_CODE = 11;


    private ProductViewModel productViewModel;
    private int sizeProducts;
    private int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        setTitle("Products");

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

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getAllProductsById(ID).observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter.setProducts(products);
            }
        });


        //Fab --Create product
        FloatingActionButton fab = findViewById(R.id.fabAddProduct);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ShoppingListActivity.this, AddProductActivity.class);
//                intent.putExtra(EXTRA_ID, ID);
                startActivityForResult(intent1, ADD_PRODUCT_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_PRODUCT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            String name = data.getStringExtra(AddProductActivity.EXTRA_REPLY_PRODUCT_NAME);
            int quantity = data.getIntExtra(AddProductActivity.EXTRA_REPLY_PRODUCT_QUANTITY,-1);
            String unit = data.getStringExtra(AddProductActivity.EXTRA_REPLY_PRODUCT_UNIT);

            Product product = new Product(name, quantity, unit, ID);
            productViewModel.insert(product);

            Toast.makeText(this, "Product saved",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Product failed to save",Toast.LENGTH_SHORT).show();
        }
    }
}