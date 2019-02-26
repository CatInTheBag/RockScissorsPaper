package com.example.cartman.a10035rsp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
   Button startButton;
   ImageView leftImage;
   ImageView rightImage;
   TextView scoreTextView;
   int winsFirstPlayer = 0;
   int winsSecondPlayer = 0;

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      startButton = findViewById(R.id.startButtonId);
      leftImage = findViewById(R.id.leftImageId);
      rightImage = findViewById(R.id.rightImageId);
      scoreTextView = findViewById(R.id.currentScoreId);

      final int[] imagesArray = {
              R.drawable.paper,
              R.drawable.rock,
              R.drawable.scissors
      };

      startButton.setOnClickListener(new View.OnClickListener()
      {
         @Override
         public void onClick(View v)
         {
            Random generator = new Random();
            int number = generator.nextInt(2);
            int number2 = generator.nextInt(2);

            leftImage.setImageResource(imagesArray[number]);
            rightImage.setImageResource(imagesArray[number2]);

            if (((number == 0) && (number2 == 1)) || ((number == 1) && (number2 == 2)) || ((number == 2) && (number2 == 0)))
            {
               winsFirstPlayer++;
               Toast.makeText(getApplicationContext(), R.string.player_one_wins, Toast.LENGTH_SHORT).show();
               scoreTextView.setText("Score " + winsFirstPlayer + "/" + winsSecondPlayer);

               if(winsFirstPlayer == 3)
               {
                  scoreTextView.setText("Player ONE won the game!");
                  winsFirstPlayer = 0;
                  winsSecondPlayer = 0;
               }
            }
            else if (number == number2)
            {
               Toast.makeText(getApplicationContext(), R.string.no_one_win, Toast.LENGTH_SHORT).show();
            }
            else
            {
               winsSecondPlayer++;
               Toast.makeText(getApplicationContext(),R.string.player_two_wins,Toast.LENGTH_SHORT).show();
               scoreTextView.setText("Score " + winsFirstPlayer + "/" + winsSecondPlayer);


               if(winsSecondPlayer == 3)
               {
                  scoreTextView.setText("Player TWO won the game!");
                  winsFirstPlayer = 0;
                  winsSecondPlayer = 0;

                  endGame();
               }
            }
         }
      });
   }

   public void endGame()
   {
      AlertDialog.Builder alert = new AlertDialog.Builder(this);
      alert.setTitle("Hope you enjoyed it");
      alert.setCancelable(false);
      alert.setMessage("Player TWO won the game!");
      alert.setPositiveButton("Bye", new DialogInterface.OnClickListener()
      {
         @Override
         public void onClick(DialogInterface dialog, int which)
         {
            finish();
         }
      });

      alert.show();
   }

   //app restart
   /*if (winsSecondPlayer == 4)
   {
      Intent mStartActivity = new Intent(getApplicationContext(), MainActivity.class);
      int mPendingIntentId = 123456;
      PendingIntent mPendingIntent = PendingIntent.getActivity(getApplicationContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
      AlarmManager mgr = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
      mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
      System.exit(0);
   }*/
}


