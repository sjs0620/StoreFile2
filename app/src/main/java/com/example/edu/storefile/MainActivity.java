package com.example.edu.storefile;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button buttonIRead, buttonIWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonIRead = findViewById(R.id.buttonIRead);
        buttonIWrite = findViewById(R.id.buttonIWrite);
        buttonIRead.setOnClickListener(this);
        buttonIWrite.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText editTextIn = findViewById(R.id.editTextIContent);
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        String fileName = "storeInner.txt";

        switch (v.getId()) {
            case R.id.buttonIRead:
                try {
                    fileInputStream = openFileInput(fileName);
                    byte[] buffer = new byte[fileInputStream.available()];
                    fileInputStream.read(buffer);
                    editTextIn.setText(new String(buffer));
                    fileInputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.buttonIWrite:
                try {
                    fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                    fileOutputStream.write(editTextIn.getText().toString().getBytes());
                    editTextIn.setText("");
                } catch (FileNotFoundException e) {

                } catch (IOException e) {

                }
                finally {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                break;
        }
    }
}
