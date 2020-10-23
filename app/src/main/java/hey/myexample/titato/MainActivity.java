package hey.myexample.titato;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0:cross, 1:circle, 2:empty
    int[][] winningPos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int activePlayer = 0;
    boolean gameActive = true;

    public void put(View view)
    {
        ImageView token = (ImageView) view;
        int pressedTag = Integer.parseInt(token.getTag().toString());
        if (gameState[pressedTag] == 2 && gameActive) {
            gameState[pressedTag] = activePlayer;
            token.setTranslationY(-1500);

            if (activePlayer == 0) {
                token.setImageResource(R.drawable.cr);
                activePlayer = 1;
            } else {
                token.setImageResource(R.drawable.cir);
                activePlayer = 0;
            }
            token.animate().translationYBy(1500).setDuration(300);

            for (int[] winPos : winningPos) {
                if (gameState[winPos[0]] == gameState[winPos[1]] && gameState[winPos[1]] == gameState[winPos[2]] && gameState[winPos[0]] != 2) {
                    if (activePlayer == 1) {
                        Toast.makeText(this, "Cross won", Toast.LENGTH_SHORT).show();
                        gameActive =false;
                    } else {
                        Toast.makeText(this, "Circles won", Toast.LENGTH_SHORT).show();
                        gameActive = false;
                    }
                    Button playAgainButton = (Button)findViewById(R.id.playAgainButton);
                    playAgainButton.setVisibility(View.VISIBLE);
                }
            }

        }
    }
   /* public void isFull(View view)
    {
        GridLayout gridlayout = (GridLayout) findViewById(R.id.grid);
        for (int i=0; i<gridlayout.getChildCount(); i++)
        {
            ImageView tokens = (ImageView) gridlayout.getChildAt(i);
            if(tokens.isShown()&&gameActive)
            {
                Toast.makeText(this, "draw", Toast.LENGTH_SHORT).show();
            }
        }
    }*/
    public void playAgain(View view)
    {
        Button AgainButton = (Button)findViewById(R.id.playAgainButton);
        AgainButton.setVisibility(View.INVISIBLE);
        Toast.makeText(this, "new game started", Toast.LENGTH_SHORT).show();
        GridLayout gridlayout = (GridLayout) findViewById(R.id.grid);

       for (int i=0; i<gridlayout.getChildCount(); i++){
            ImageView tokens = (ImageView)gridlayout.getChildAt(i);
            tokens.setImageDrawable(null);
        }

       for(int j=0; j<9; j++)
        {
            gameState[j] = 2;
        }
        activePlayer = 0;
        gameActive = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}