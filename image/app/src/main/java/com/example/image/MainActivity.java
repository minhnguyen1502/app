package com.example.image;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;


public class MainActivity extends AppCompatActivity {
    ViewFlipper viewFlipper;
    Button next;
    Button previous;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewFlipper = (android.widget.ViewFlipper)findViewById(R.id.viewFlipper);
        next = (Button) findViewById(R.id.next);
        previous = (Button) findViewById(R.id.previous);
//        next.setOnClickListener(this);
//        previous.setOnClickListener(this);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showNext();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showPrevious();
            }
        });
    }



//    @Override
//    public void onClick(android.view.View v) {
//        if (v == next) {
//            viewFlipper.showNext();
//        }
//        else if (v == previous) {
//            viewFlipper.showPrevious();
//        }
//    }
}