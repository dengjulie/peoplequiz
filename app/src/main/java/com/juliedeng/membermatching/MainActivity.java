package com.juliedeng.membermatching;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button answer1, answer2, answer3, answer4, endButton;
    TextView score, countdown;
    ImageView member_image;
    Random r;
    List<Member> memberList;
    CountDownTimer cdt;

    int turn;
    int gameScore;

    String[] members = {
            "Aayush Tyagi", "Abhinav Koppu", "Aditya Yadav", "Akkshay Khoslaa", "Amy Shen",
            "Aneesh Jindal", "Angela Dong", "Ayush Kumar", "Candice Ye", "Carol Wang",
            "Cody Hsieh", "Daniel Andrews", "Eric Kong", "Ethan Wong", "Fang Shuo",
            "Japjot Singh", "Jeffrey Zhang", "Joey Hejna", "Julie Deng", "Justin Kim",
            "Kaden Dippe", "Kanyes Thaker", "Krishnan Rajiyah", "Leon Kwak", "Levi Walsh",
            "Louie McConnell", "Max Miranda", "Mohit Katyal", "Mudabbir Khan", "Natasha Wong",
            "Nikhara Rora", "Noah Pepper", "Radhika Dhomse", "Sahil Lamba", "Saman Virai",
            "Sarah Tang", "Sharie Wang", "Shiv Kushwah", "Shreya Reddy", "Shubham Gupta",
            "Srujay Korlakunta", "Stephen Jayakar", "Sumukh Shivakumar", "Suyash Gupta", "Tiger Chen",
            "Victor Sun", "Vidya Ravikumar", "Vineeth Yeevani", "Wilbur Shi", "William Lu",
            "Will Oakley", "Xinyi Chen", "Young Lin", "Zach Govani"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = new Random();
        gameScore = 0;
        turn = 0;

        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);
        endButton = (Button) findViewById(R.id.endButton);
        score = (TextView) findViewById(R.id.score);
        countdown = (TextView) findViewById(R.id.countdown);
        member_image = (ImageView) findViewById(R.id.member_image);

        memberList = new ArrayList<>();

        for (int i = 0; i < members.length; i++) {
            String memberName = members[i].toLowerCase().replace(" ", "");
            int id = getApplicationContext().getResources().getIdentifier("drawable/"+ memberName, null, getApplicationContext().getPackageName());
            memberList.add(new Member(members[i], id));
        }
        Collections.shuffle(memberList);

        cdt = new CountDownTimer(6000, 1000) {

            public void onTick(long millisUntilFinished) {
                countdown.setText("Time remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Toast.makeText(MainActivity.this, "Too Slow!", Toast.LENGTH_SHORT).show();
                gameScore -= 1;
                turn++;
                update(turn);
            }
        };

        update(turn);


        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer1.getText().equals(memberList.get(turn).getName())) {
                    gameScore++;
                    if (turn < memberList.size() - 1) {
                        turn++;
                    } else {
                        turn = 0;
                        Collections.shuffle(memberList);
                    }
                    update(turn);
                } else {
                    Toast.makeText(MainActivity.this, "Wrong Answer :(", Toast.LENGTH_SHORT).show();
                    gameScore -= 1;
                }
            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer2.getText().equals(memberList.get(turn).getName())) {
                    gameScore++;
                    if (turn < memberList.size() - 1) {
                        turn++;
                    } else {
                        turn = 0;
                        Collections.shuffle(memberList);
                    }
                    update(turn);
                } else {
                    Toast.makeText(MainActivity.this, "Wrong Answer :(", Toast.LENGTH_SHORT).show();
                    gameScore -= 1;
                }
            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer3.getText().equals(memberList.get(turn).getName())) {
                    gameScore++;
                    if (turn < memberList.size() - 1) {
                        turn++;
                    } else {
                        turn = 0;
                        Collections.shuffle(memberList);
                    }
                    update(turn);
                } else {
                    Toast.makeText(MainActivity.this, "Wrong Answer :(", Toast.LENGTH_SHORT).show();
                    gameScore -= 1;
                }
            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer4.getText().equals(memberList.get(turn).getName())) {
                    gameScore++;
                    if (turn < memberList.size() - 1) {
                        turn++;
                    } else {
                        turn = 0;
                        Collections.shuffle(memberList);
                    }
                    update(turn);
                } else {
                    Toast.makeText(MainActivity.this, "Wrong Answer :(", Toast.LENGTH_SHORT).show();
                    gameScore -= 1;
                }
            }
        });
        member_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ContactsContract.Intents.Insert.ACTION);
                i.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                i.putExtra(ContactsContract.Intents.Insert.NAME, memberList.get(turn).getName());
                startActivity(i);
            }
        });
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("End Game")
                        .setMessage("Are you sure you want to end the game?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(getApplicationContext(), StartActivity.class);
                                startActivity(i);
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        cdt.cancel();
    }
    @Override
    protected void onStop() {
        super.onStop();
        cdt.cancel();
    }
    @Override
    protected void onResume() {
        super.onResume();
        cdt.start();

    }

    private void update(int turn) {
        member_image.setImageResource(memberList.get(turn).getImage());
        score.setText("Score: " + gameScore);

        int correctButton = r.nextInt(4) + 1;

        //in order of assignment, not display
        int firstButton = turn;
        int secondButton;
        int thirdButton;
        int fourthButton;

        do {
            secondButton = r.nextInt(memberList.size());
        } while (secondButton == firstButton);
        do {
            thirdButton = r.nextInt(memberList.size());
        } while (thirdButton == firstButton || thirdButton == secondButton);
        do {
            fourthButton = r.nextInt(memberList.size());
        } while (fourthButton == firstButton || fourthButton == secondButton || fourthButton == thirdButton);

        switch (correctButton) {
            case 1:
                answer1.setText(memberList.get(firstButton).getName());
                answer2.setText(memberList.get(secondButton).getName());
                answer3.setText(memberList.get(thirdButton).getName());
                answer4.setText(memberList.get(fourthButton).getName());
                break;
            case 2:
                answer2.setText(memberList.get(firstButton).getName());
                answer1.setText(memberList.get(secondButton).getName());
                answer3.setText(memberList.get(thirdButton).getName());
                answer4.setText(memberList.get(fourthButton).getName());
                break;
            case 3:
                answer3.setText(memberList.get(firstButton).getName());
                answer2.setText(memberList.get(secondButton).getName());
                answer1.setText(memberList.get(thirdButton).getName());
                answer4.setText(memberList.get(fourthButton).getName());
                break;
            case 4:
                answer4.setText(memberList.get(firstButton).getName());
                answer2.setText(memberList.get(secondButton).getName());
                answer3.setText(memberList.get(thirdButton).getName());
                answer1.setText(memberList.get(fourthButton).getName());
                break;
        }
        cdt.start();

    }
}

