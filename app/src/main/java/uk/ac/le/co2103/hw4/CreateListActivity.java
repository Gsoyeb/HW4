package uk.ac.le.co2103.hw4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class CreateListActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY_SHOPPING_IMAGE = "uk.ac.le.co2103.hw4.REPLY.SHOPPING_IMAGE";
    public static final String EXTRA_REPLY_SHOPPING_NAME = "uk.ac.le.co2103.hw4.REPLY.SHOPPING_NAME";
    public static final int PICK_IMAGE = 111;

    private ImageView pictureImage;
    private EditText editShoppingName;
    private Button buttonSave;
    private Button buttonUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);

        pictureImage = findViewById(R.id.myImageView);
        editShoppingName = findViewById(R.id.shoppingList_name);
        buttonSave = findViewById(R.id.btn_create_ShoppingList);
        buttonUpload = findViewById(R.id.btnImageChoose);

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(editShoppingName.getText())){
                    Toast.makeText(CreateListActivity.this, "Please enter at least name",Toast.LENGTH_SHORT).show();
                    setResult(RESULT_CANCELED, replyIntent);
                }else {
                    String name = editShoppingName.getText().toString();
                    int image = pictureImage.getResources().getIdentifier("ic_launcher","drawable", getPackageName());
                    replyIntent.putExtra(EXTRA_REPLY_SHOPPING_IMAGE, image);
                    replyIntent.putExtra(EXTRA_REPLY_SHOPPING_NAME, name);
                    setResult(RESULT_OK, replyIntent);
                    finish();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                        pictureImage.setImageDrawable(drawable);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED)  {
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }
}