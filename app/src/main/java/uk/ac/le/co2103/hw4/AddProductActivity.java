package uk.ac.le.co2103.hw4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddProductActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String EXTRA_REPLY_PRODUCT_QUANTITY = "uk.ac.le.co2103.hw4.REPLY.PRODUCT_QUANTITY";
    public static final String EXTRA_REPLY_PRODUCT_NAME = "uk.ac.le.co2103.hw4.REPLY.PRODUCT_NAME";
    public static final String EXTRA_REPLY_PRODUCT_UNIT = "uk.ac.le.co2103.hw4.REPLY.PRODUCT_UNIT";

    private EditText nameProduct;
    private EditText quantityProduct;
    private Spinner unitProduct;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        nameProduct = findViewById(R.id.edit_product_name);
        quantityProduct = findViewById(R.id.edit_product_quantity);
        unitProduct = findViewById(R.id.edit_text_unit);
        button = findViewById(R.id.btnAddProduct);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.labels_unit, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitProduct.setAdapter(adapter);
        unitProduct.setOnItemSelectedListener(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(nameProduct.getText()) || TextUtils.isEmpty(quantityProduct.getText())){
                    Toast.makeText(AddProductActivity.this, "Please enter name and quantity",Toast.LENGTH_SHORT).show();
                    setResult(RESULT_CANCELED, replyIntent);
                }else {
                    String name = nameProduct.getText().toString();
                    int quantity = Integer.parseInt(quantityProduct.getText().toString().trim());
                    String unit = String.valueOf(unitProduct.getSelectedItem());

                    replyIntent.putExtra(EXTRA_REPLY_PRODUCT_NAME, name);
                    replyIntent.putExtra(EXTRA_REPLY_PRODUCT_QUANTITY, quantity);
                    replyIntent.putExtra(EXTRA_REPLY_PRODUCT_UNIT, unit);

                    setResult(RESULT_OK, replyIntent);
                    finish();
                }
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}