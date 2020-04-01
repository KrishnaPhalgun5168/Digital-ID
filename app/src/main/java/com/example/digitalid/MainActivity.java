package com.example.digitalid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button generator = findViewById(R.id.Generator);
        generator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText rollnumber = findViewById(R.id.RollNumber);
                String id = rollnumber.getText().toString().toUpperCase();
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitmatrix = multiFormatWriter.encode(id, BarcodeFormat.CODE_128, 600, 200);
                    BarcodeEncoder barcodeencoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeencoder.createBitmap(bitmatrix);
                    ImageView barcode = findViewById(R.id.BarCode);
                    barcode.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
    }
}
