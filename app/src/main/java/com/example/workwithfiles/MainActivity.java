package com.example.workwithfiles;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private final static String FILE_NAME = "content.txt";

    // сохранение файла
    public void saveName(View view) {

        FileOutputStream fos = null;
        try {
            EditText textBox = findViewById(R.id.editor);
            String text = textBox.getText().toString();

            // В данном случае если файл "content.txt" уже существует, то он будет перезаписан.
            // Если же нам надо было дописать файл, тогда надо было бы использовать режим MODE_APPEND:
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);

            fos.write(text.getBytes());
            Toast.makeText(this, "Имя сохранено", Toast.LENGTH_SHORT).show();
        } catch (IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            try {
                if (fos != null)
                    fos.close();    // Закрыть поток
            } catch (IOException ex) {

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    // открытие файла
    public void loadName(View view) {

        FileInputStream fis = null;
        TextView textView = findViewById(R.id.text);
        try {
            fis = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            String text = new String(bytes);
            textView.setText(text);
            Toast.makeText(this, "Имя загружено", Toast.LENGTH_SHORT).show();
        } catch (IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {

            try {
                if (fis != null)
                    fis.close();    // Закрыть поток
            } catch (IOException ex) {

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}