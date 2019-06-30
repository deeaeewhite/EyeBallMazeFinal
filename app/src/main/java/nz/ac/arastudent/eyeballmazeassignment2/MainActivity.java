package nz.ac.arastudent.eyeballmazeassignment2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import nz.ac.arastudent.eyeballmazeassignment2.model.IGame;
import nz.ac.arastudent.eyeballmazeassignment2.model.Model;

public class MainActivity extends AppCompatActivity {

    public Button[][] buttons = new Button[6][4];


    public IGame myModel = new Model();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button programmaticLayout = findViewById(R.id.btnProgrammatic);
        programmaticLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent programmaticIntent = new Intent(MainActivity.this, ProgrammaticalActivity.class);
                startActivity(programmaticIntent);
            }
        });

        Button manualLayout = findViewById(R.id.btnManual);
        manualLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent manualIntent = new Intent(MainActivity.this, ManualLayoutActivity.class);
                startActivity(manualIntent);
            }
        });

    }
}
