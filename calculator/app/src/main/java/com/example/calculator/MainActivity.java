package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAdd, buttonSub, buttonDivision,
            buttonMul, buttonC, buttonEqual;
    EditText  resultEdt;
    EditText  valueEdt;
    float mValueOne, mValueTwo;
    boolean isAddition, isSubtraction, isMultiplication, isDivision;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        buttonAdd = (Button) findViewById(R.id.buttonadd);
        buttonSub = (Button) findViewById(R.id.buttonsub);
        buttonMul = (Button) findViewById(R.id.buttonmul);
        buttonDivision = (Button) findViewById(R.id.buttondiv);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonEqual = (Button) findViewById(R.id.buttoneql);
        valueEdt = (EditText) findViewById(R.id.edt1);
        resultEdt = (EditText) findViewById(R.id.edt2);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueEdt.setText(valueEdt.getText() + "1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueEdt.setText(valueEdt.getText() + "2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueEdt.setText(valueEdt.getText() + "3");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueEdt.setText(valueEdt.getText() + "4");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueEdt.setText(valueEdt.getText() + "5");
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueEdt.setText(valueEdt.getText() + "6");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueEdt.setText(valueEdt.getText() + "7");
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueEdt.setText(valueEdt.getText() + "8");
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueEdt.setText(valueEdt.getText() + "9");
            }
        });
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueEdt.setText(valueEdt.getText() + "0");
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valueEdt == null) {
                    valueEdt.setText("");
                } else {
                    mValueOne = Float.parseFloat(valueEdt.getText() + "");
                    isAddition = true;
                    valueEdt.setText(null);
                }
            }
        });
        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (valueEdt == null) {
                    valueEdt.setText("");
                } else {
                    mValueOne = Float.parseFloat(valueEdt.getText() + "");
                    isSubtraction = true;
                    valueEdt.setText(null);
                }
            }
        });
        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valueEdt == null) {
                    valueEdt.setText("");
                } else {
                    mValueOne = Float.parseFloat(valueEdt.getText() + "");
                    isMultiplication = true;
                    valueEdt.setText(null);
                }
            }
        });
        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valueEdt == null) {
                    valueEdt.setText("");
                } else {
                    mValueOne = Float.parseFloat(valueEdt.getText() + "");
                    isDivision = true;
                    valueEdt.setText(null);
                }
            }
        });
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueTwo = Float.parseFloat(valueEdt.getText() + "");
                if (isAddition == true) {
                    resultEdt.setText(mValueOne + mValueTwo + "");
                    isAddition = false;
                }
                if (isSubtraction == true) {
                    resultEdt.setText(mValueOne - mValueTwo + "");
                    isSubtraction = false;
                }
                if (isMultiplication == true) {
                    resultEdt.setText(mValueOne * mValueTwo + "");
                    isMultiplication = false;
                }
                if (isDivision == true) {
                    resultEdt.setText(mValueOne / mValueTwo + "");
                    isDivision = false;
                }
            }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueEdt.setText("");
                resultEdt.setText("");
            }
        });
    }
}