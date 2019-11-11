package tw.org.iii.iiiandroid04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private String answer;
    private int dig = 3;
    private EditText input;
    private TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        log = findViewById(R.id.log);
        answer = createAnswer(3);
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

    }

    public void setting(View view) {
    }

    public void newGame(View view) {
    }

    public void guess(View view) {
        String strInput = input.getText().toString();
        String result = checkAB(strInput);
        log.append(strInput + " => " + result + "\n");
        input.setText("");
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
