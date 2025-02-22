package com.example.myapplication;

import static com.example.myapplication.R.id.button;
import static com.example.myapplication.R.id.status;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainGame extends AppCompatActivity implements View.OnClickListener {

    boolean playerOneActive;
    private TextView Player1score, Player2score, PlayerStatus, PlayerStatus1, pp1input, pp2input;
    private Button[] buttons = new Button[9];
    private ImageButton reset, reset1, playagain, playagain1;

    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    int rounds;

    private int playerOneScoreCount, playerTwoScoreCount;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        Intent j = getIntent();

        String pp1_input = j.getStringExtra("p1Input");
        ((TextView)findViewById(R.id.Player1Name)).setText(pp1_input);

        String pp2_input = j.getStringExtra("p2Input");
        ((TextView)findViewById(R.id.Player2Name)).setText(pp2_input);

        pp1input = findViewById(R.id.Player1Name);
        pp2input = findViewById(R.id.Player2Name);


        Player1score = findViewById(R.id.Player1Score);
        Player2score = findViewById(R.id.Player2Score);
        PlayerStatus = findViewById(R.id.status);
        PlayerStatus1 = findViewById(R.id.status1);

        reset = findViewById(R.id.Reset);
        reset1 = findViewById(R.id.Reset1);
        playagain = findViewById(R.id.PlayAgain);
        playagain1 = findViewById(R.id.PlayAgain1);

        buttons[0] = findViewById(R.id.bttn0);
        buttons[1] = findViewById(R.id.bttn1);
        buttons[2] = findViewById(R.id.bttn2);
        buttons[3] = findViewById(R.id.bttn3);
        buttons[4] = findViewById(R.id.bttn4);
        buttons[5] = findViewById(R.id.bttn5);
        buttons[6] = findViewById(R.id.bttn6);
        buttons[7] = findViewById(R.id.bttn7);
        buttons[8] = findViewById(R.id.bttn8);

        for(int i=0; i<buttons.length; i++){
            buttons[i].setOnClickListener(this);
        }

        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        playerOneActive = true;

        rounds = 0;


        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View v) {

        if (!((Button)v).getText().toString().equals(""))
        {
            return;
        }
        else if(checkWinner())
        {
            return;
        }
        String buttonID = v.getResources().getResourceEntryName(v.getId());
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length()-1,buttonID.length()));

        if (playerOneActive)
        {
            ((Button)v).setText("X");
            ((Button)v).setTextColor(Color.parseColor("#B80000"));
            gameState[gameStatePointer] = 0;
        }
        else
        {
            ((Button)v).setText("O");
            ((Button)v).setTextColor(Color.parseColor("#26FF35"));
            gameState[gameStatePointer] = 1;
        }
        rounds++;

        if(checkWinner())
        {
            if(playerOneActive)
            {
                String p1n;
                p1n = pp1input.getText().toString();
                String p2n;
                p2n = pp2input.getText().toString();
                playerOneScoreCount++;
                updatePlayerScore();
                PlayerStatus.setText(p1n + " " + "has won");
                PlayerStatus1.setText(p1n + " " + "has won");
            }else
            {
                String p1n;
                p1n = pp1input.getText().toString();
                String p2n;
                p2n = pp2input.getText().toString();
                playerOneScoreCount++;
                playerTwoScoreCount++;
                updatePlayerScore();
                PlayerStatus.setText(p2n + " " + "has won");
                PlayerStatus1.setText(p2n + " " + "has won");
            }
        } else if(rounds==9)
        {
            PlayerStatus.setText("Draw");
            PlayerStatus1.setText("Draw");
        }
        else
        {
            playerOneActive = !playerOneActive;
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                updatePlayerScore();
            }
        });

        reset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                updatePlayerScore();
            }
        });

        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
            }
        });

        playagain1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
            }
        });

    }

    private boolean checkWinner() {
        boolean winnerResults = false;
        for (int[] winningPositions: winningPositions)
        {
            if (gameState[winningPositions[0]]==gameState[winningPositions[1]] &&
                    gameState[winningPositions[1]]==gameState[winningPositions[2]] &&
                        gameState[winningPositions[0]]!=2)
            {
                winnerResults = true;
            }
        }

        return winnerResults;
    }

    private void playAgain() {
        rounds = 0;
        playerOneActive = true;
        for (int i=0; i<buttons.length; i++)
        {
            gameState[i] = 2;
            buttons[i].setText("");
        }
        PlayerStatus.setText("Status");
        PlayerStatus.setText("Status");
    }

    private void updatePlayerScore() {
        Player1score.setText(Integer.toString(playerOneScoreCount));
        Player2score.setText(Integer.toString(playerTwoScoreCount));
    }
}