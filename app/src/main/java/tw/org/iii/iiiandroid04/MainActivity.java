package tw.org.iii.iiiandroid04;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private String answer;
    private int dig = 3,temp = -1;
    private EditText input;
    private TextView log;
    private int counter;
    private long lastTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        log = findViewById(R.id.log);
        answer = createAnswer(dig);
        Log.v("brad", answer);
    }

    //  Create an answer
    private String createAnswer(int dig){
        LinkedList<Integer> list = new LinkedList<>();
        for (int i=0; i<10; i++) list.add(i);
        Collections.shuffle(list);

        StringBuffer sb = new StringBuffer();
        for (int i=0; i<dig; i++){
            sb.append(list.get(i));
        }

        return sb.toString();
    }

    public void exit(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage("Exit?")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        alertDialog.show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("brad", "onDestroy");
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastTime > 3*1000){
            lastTime = System.currentTimeMillis();
            Toast.makeText(this, "back one more", Toast.LENGTH_SHORT).show();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public void finish() {
        super.finish();
        Log.v("brad", "finish");
    }

    public void setting(View view) {
        String[] items = {"3","4","5","6"};
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Select Game Mode")
                .setSingleChoiceItems(items, dig - 3, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        temp = which;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dig = temp + 3;
                        newGame(null);
                    }
                })
                .create();
        alertDialog.show();
    }

    public void newGame(View view) {
        //Log.v("brad", "new game");
        counter = 0;
        input.setText("");
        log.setText("");
        answer = createAnswer(dig);


    }

    public void guess(View view) {
        counter++;
        String strInput = input.getText().toString();
        if (!isRightNumber(strInput)) {
            return;
        }

        String result = checkAB(strInput);
        log.append(counter + " : " + strInput + " => " + result + "\n");

        if (result.equals(dig + "A0B")){
            // WINNER
            showDialog(true);
        }else if (counter == 10){
            // LOSER
            showDialog(false);
        }

        input.setText("");
    }

    private boolean isRightNumber(String g){
        return g.matches("^[0-9]{" + dig + "}$");
    }

    private void showDialog(boolean isWinner){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(isWinner?"WINNER":"Loser")
                .setMessage(isWinner?"恭喜老爺":"謎底為"+answer)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        newGame(null);
                    }
                })
                .create();

        alertDialog.show();

    }


    private String checkAB(String guess){
        int a, b; a = b = 0;
        for (int i=0; i<guess.length(); i++){
            if (guess.charAt(i) == answer.charAt(i) ){
                a++;
            }else if (answer.indexOf(guess.charAt(i)) >= 0){
                b++;
            }
        }
        return a + "A" + b + "B";
    }
}
