package com.example.numbersystemconvertor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    Button go;
    Button go1;
    Button go2;
    Button go3;
    Intent intent;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        go=findViewById(R.id.button);
        go1=findViewById(R.id.button1);
        go2=findViewById(R.id.button2);
        go3=findViewById(R.id.button3);

            Spinner Spinner = (Spinner) findViewById(R.id.spinner);

            Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                public void onClick(View v) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onItemSelected(AdapterView<?> arg0, View view,
                                           int position, long row_id) {

                    switch(position){
                        case 0:
                            intent = new Intent(MainActivity.this, BinaryActivity.class);

                            break;
                        case 1:
                            intent = new Intent(MainActivity.this, DecimalActivity.class);

                            break;
                        case 2:
                            intent = new Intent(MainActivity.this, OctalActivity.class);

                            break;
                        case 3:
                            intent = new Intent(MainActivity.this, HexaDecimalActivity.class);

                            break;

                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    intent = new Intent(MainActivity.this, MainActivity.class);

                }

            });

}
    public void gobtn1(View v)
    {
        startActivity(intent);
        finish();
    }
    public void gobtn2(View v)
    {
        Intent intent=new Intent(MainActivity.this,About.class);
        startActivity(intent);
        finish();
    }
    public void gobtn3(View v)
    {
        Intent intent=new Intent(MainActivity.this,Help.class);
        startActivity(intent);
        finish();
    }

}