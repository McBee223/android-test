package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PlayersName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_players_name);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void MainG(View m){
        Intent j = new Intent(this, MainGame.class);

        String p1_input = ((EditText)findViewById(R.id.PLayer1input)).getText().toString();
        j.putExtra("p1Input", p1_input);

        String p2_input = ((EditText)findViewById(R.id.PLayer2input)).getText().toString();
        j.putExtra("p2Input", p2_input);

        startActivity(j);
    }

}