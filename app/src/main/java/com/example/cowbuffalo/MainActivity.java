package com.example.cowbuffalo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText inputNumber;
    Button submitButton;
    TextView cows_count_tv, bulls_count_tv;
    int bull = 0;
    int cow = 0;
    String inputDigit;

    HashMap<Integer, Integer> randomDigit = new HashMap<>();
    HashMap<Integer, Integer> Input = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNumber = (EditText) findViewById(R.id.input_number);
        submitButton = (Button) findViewById(R.id.button_submit);
        cows_count_tv = (TextView) findViewById(R.id.cows_tv_count);
        bulls_count_tv = (TextView) findViewById(R.id.bulls_tv_count);

        numberFormation();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDigit = inputNumber.getText().toString();
                Toast.makeText(MainActivity.this, inputDigit, Toast.LENGTH_SHORT).show();
                if (inputDigit.length() == 4) {
                    Input.put(Integer.parseInt(String.valueOf(inputDigit.charAt(0))), 1);
                    Input.put(Integer.parseInt(String.valueOf(inputDigit.charAt(1))), 2);
                    Input.put(Integer.parseInt(String.valueOf(inputDigit.charAt(2))), 3);
                    Input.put(Integer.parseInt(String.valueOf(inputDigit.charAt(3))), 4);
                    if (Input.size() == 4) {
                        getAnswer();
                        bulls_count_tv.setText("" + bull);
                        cows_count_tv.setText("" + cow);
                        bull = 0;
                        cow = 0;
                    } else {
                        Toast.makeText(MainActivity.this, "Number should be unique", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Enter 4 digit", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void getAnswer() {
        for (Map.Entry<Integer, Integer> entry : Input.entrySet()) {
            if (randomDigit.containsKey(entry.getKey())) {
                if (randomDigit.get(entry.getKey()).equals(entry.getValue())) {
                    bull++;
                } else {
                    cow++;
                }
            }
        }
    }

    public void numberFormation() {
        int a, b, c, d;
        do {
            a = random();
            b = random();
            c = random();
            d = random();
        } while (a == 0 || b == 0 || c == 0 || d == 0 || a == b || a == c || a == d || b == c || b == d || c == d);
        randomDigit.put(a, 1);
        randomDigit.put(b, 2);
        randomDigit.put(c, 3);
        randomDigit.put(d, 4);
    }

    public int random() {
        double random = Math.random();
        random *= 9;
        random++;
        int dieValue = (int) random;
        return dieValue;
    }
}