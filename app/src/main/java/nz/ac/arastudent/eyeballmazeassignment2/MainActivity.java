package nz.ac.arastudent.eyeballmazeassignment2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import nz.ac.arastudent.eyeballmazeassignment2.model.IGame;
import nz.ac.arastudent.eyeballmazeassignment2.model.Model;

public class MainActivity extends AppCompatActivity {

    public Button[][] buttons = new Button[6][4];
    MediaPlayer gameSong;


    public Toolbar myToolbar;

    SharedPreferences sharedPreferences = null;

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
