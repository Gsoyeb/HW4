package uk.ac.le.co2103.hw4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateListActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY_IMAGE = "uk.ac.le.co2103.hw4.REPLY.IMAGE";
    public static final String EXTRA_REPLY_SHOPPING_NAME = "uk.ac.le.co2103.hw4.REPLY.SHOPPING_NAME";

    private ImageView pictureImage;
    private EditText editShoppingName;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);

        pictureImage = findViewById(R.id.myImageView);
        editShoppingName = findViewById(R.id.shoppingList_name);
        button = findViewById(R.id.btn_create_ShoppingList);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(editShoppingName.getText())){
                    Toast.makeText(CreateListActivity.this, "Please enter at least name",Toast.LENGTH_SHORT).show();
                    setResult(RESULT_CANCELED, replyIntent);
                }else {
                    String name = editShoppingName.getText().toString();
                    int image = pictureImage.getResources().getIdentifier("ic_launcher","drawable", getPackageName());
                    replyIntent.putExtra(EXTRA_REPLY_IMAGE, image);
                    replyIntent.putExtra(EXTRA_REPLY_SHOPPING_NAME, name);
                    setResult(RESULT_OK, replyIntent);
                    finish();
                }
            }
        });



    }
}