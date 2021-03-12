package edu.sjsu.jaime.nutritionapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by lalin on 3/17/2018.
 */

public class message extends Activity{

    private String URL;
    private TextView link;

    @Override
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);

        link = findViewById(R.id.textView6);
        URL = getResources().getString(R.string.URL_help);

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                startActivity(intent);
            }
        });

    }


    public void goBack(View view)
    {
        finish();
    }
}
