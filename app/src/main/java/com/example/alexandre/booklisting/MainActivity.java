package com.example.alexandre.booklisting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btSearch = findViewById(R.id.bt_search);
        final EditText edtTxt = findViewById(R.id.titulo_text);

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtTxt.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(MainActivity.this, R.string.msgErro, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,10);
                    toast.show();
                } else {
                    Intent bookIntent = new Intent(MainActivity.this, BookActivity.class);
                    bookIntent.putExtra(getString(R.string.parm_titulo), edtTxt.getText().toString());
                    startActivity(bookIntent);
                }
            }
        });

    }
}
