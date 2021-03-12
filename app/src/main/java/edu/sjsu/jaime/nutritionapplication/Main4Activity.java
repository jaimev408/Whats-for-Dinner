package edu.sjsu.jaime.nutritionapplication;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main4Activity extends AppCompatActivity {

    HashMap<String, Integer> mapa;
    ArrayList<String> a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ListView c = findViewById(R.id.lv);

        mapa =  (HashMap<String, Integer>) getIntent().getSerializableExtra("map");


        //Toast.makeText(this, String.valueOf(mapa.size()), Toast.LENGTH_LONG).show();
        //final int n = mapa.size();
        //float far = 150;
        //final TextView[] myTextViews = new TextView[n];
        a = new ArrayList<>();

        
        int k = 0;
        for (Map.Entry<String, Integer> entry: mapa.entrySet())
        {
            String key = entry.getKey();
            Integer value = entry.getValue();
            String number = String.valueOf(value);
            String weight = String.valueOf(value/2);
            String Text = "Ingredient: " + key + "  Amount: " + number + " " + weight + " grams";
            a.add(Text);
            k++;
        }

        ArrayAdapter<String> test2 = new ArrayAdapter<String>(c.getContext(), android.R.layout.simple_list_item_1, a);
        c.setAdapter(test2);



    }

    public void getOut(View view)
    {
        finish();
    }
}
