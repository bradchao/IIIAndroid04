package tw.org.iii.iiiandroid04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //  Create an answer
    private String createAnswer(int dig){
        HashSet<Integer> set = new HashSet<>();
        while (set.size() <dig){
            set.add((int)(Math.random()*10));
        }

        StringBuffer sb = new StringBuffer();
        for(Integer i : set){
            sb.append(i);
        }
        Log.v("brad", sb.toString());
        return sb.toString();
    }

    public void exit(View view) {
        createAnswer(4);
    }

    public void setting(View view) {
    }

    public void newGame(View view) {
    }

    public void guess(View view) {
    }
}
