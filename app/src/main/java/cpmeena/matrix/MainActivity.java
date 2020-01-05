package cpmeena.matrix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    String operation;

    Button b2,b0,b8,b9;
    ImageButton b1,b3,b4,b5,b6,b7,b10,b11,b12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        b0 = (Button) findViewById(R.id.button0);
        b1 = (ImageButton) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (ImageButton) findViewById(R.id.button3);
        b4 = (ImageButton) findViewById(R.id.button4);
        b5 = (ImageButton) findViewById(R.id.button5);
        b6 = (ImageButton) findViewById(R.id.button6);
        b7 = (ImageButton) findViewById(R.id.tranpose);
        b8 = (Button) findViewById(R.id.adjoint);
        b9 = (Button) findViewById(R.id.minor);
        b10 = (ImageButton) findViewById(R.id.ainverse_b);
        b11 = (ImageButton) findViewById(R.id.ainverse_binverse);
        b12 = (ImageButton) findViewById(R.id.a_b_inverse);


        clickListener();

    }
    private  void nextActivity(int key){
        Intent intent = new Intent(this, final_result.class);
        intent.putExtra("key1",key);
      //  Toast.makeText((MainActivity.this),"Fill the Matrix",Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    private void clickListener(){

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(0);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(1);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(2);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(3);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(4);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(5);
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(6);
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(7);
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(8);
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(9);
            }
        });

        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(10);
            }
        });

        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(11);
            }
        });

        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(12);
            }
        });
    }

}