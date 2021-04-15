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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateProductActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    public static final String EXTRA_REPLY_EDIT_PRODUCT_NAME = "uk.ac.le.co2103.hw4.REPLY.EDIT_PRODUCT_NAME";
    public static final String EXTRA_REPLY_EDIT_PRODUCT_QUANTITY = "uk.ac.le.co2103.hw4.REPLY.EDIT_PRODUCT_QUANTITY";
    public static final String EXTRA_REPLY_EDIT_PRODUCT_UNIT = "uk.ac.le.co2103.hw4.REPLY.EDIT_PRODUCT_UNIT";

    private TextView nameProduct;
    private TextView quantityProduct;
    private Spinner unitProduct;
    private Button button;

    private List<String> units = new ArrayList<>(Arrays.asList(new String[] {"Unit","Kg","Liter"}));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        nameProduct = findViewById(R.id.edit_product_name);
        quantityProduct = findViewById(R.id.edit_product_quantity);
        unitProduct = findViewById(R.id.edit_product_unit);
        button = findViewById(R.id.btnEditProduct);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(UpdateProductActivity.this, R.array.labels_unit, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitProduct.setAdapter(adapter);
        unitProduct.setOnItemSelectedListener(UpdateProductActivity.this);

        Intent intent = getIntent();
        nameProduct.setText(intent.getStringExtra(EXTRA_REPLY_EDIT_PRODUCT_NAME));
        int quantity = intent.getIntExtra(EXTRA_REPLY_EDIT_PRODUCT_QUANTITY, -1);
        quantityProduct.setText(String.valueOf(quantity));

        String unit = intent.getStringExtra(EXTRA_REPLY_EDIT_PRODUCT_UNIT);
        int position = units.indexOf(unit);

        unitProduct.setSelection(position);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();

                String name = nameProduct.getText().toString();
                int quantity = Integer.parseInt(quantityProduct.getText().toString().trim());
                String unit = String.valueOf(unitProduct.getSelectedItem());

                replyIntent.putExtra(EXTRA_REPLY_EDIT_PRODUCT_NAME, name);
                replyIntent.putExtra(EXTRA_REPLY_EDIT_PRODUCT_QUANTITY, quantity);
                replyIntent.putExtra(EXTRA_REPLY_EDIT_PRODUCT_UNIT, unit);

                setResult(RESULT_OK, replyIntent);
                finish();

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


    public void moveUp(View view) {
        int quantity = Integer.parseInt(quantityProduct.getText().toString()) + 1;
        quantityProduct.setText(String.valueOf(quantity));
    }

    public void moveDown(View view) {
        if (Integer.parseInt(quantityProduct.getText().toString()) < 1){return;}
        int quantity = Integer.parseInt(quantityProduct.getText().toString()) - 1;
        quantityProduct.setText(String.valueOf(quantity));
    }

}