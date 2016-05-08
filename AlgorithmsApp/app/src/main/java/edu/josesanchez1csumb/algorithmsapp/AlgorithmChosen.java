package edu.josesanchez1csumb.algorithmsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.StringTokenizer;

/**
 * Created by jsanchez-garcia on 5/7/16.
 */
public class AlgorithmChosen extends AppCompatActivity{
    SQLiteHelper db = null;
    int row_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.algorithm_chosen);

        Bundle serializedDb = getIntent().getExtras();

        //db = (SQLiteHelper) serializedDb.getSerializable("db_name");
        //getSerializableExtra("db_name");
        row_id = serializedDb.getInt("row_id");

        //TextView row_number = (TextView) findViewById(R.id.row_id);
        //row_number.setText(Integer.toString(row_id));

        SQLiteHelper db = SQLiteHelper.getInstance(this);
        Algorithm results = db.getBasedOnId(row_id);

        if(results != null){
            TextView name = (TextView) findViewById(R.id.algo_name);
            name.setText(results.getName()); //this will display the name of the algorithm

            //now we set the description to the description label
            TextView description = (TextView) findViewById(R.id.algo_description);
            description.setText(results.getDescription());

            //now we would need to parse the information for the runtime into their own labels
            StringTokenizer runtimes = new StringTokenizer(results.getRuntime(), "?");

            LinearLayout ll = (LinearLayout) findViewById(R.id.runtime_layout);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            TextView runtime_text_view = null;
            while(runtimes.hasMoreTokens()){
                runtime_text_view = new TextView(this);
                runtime_text_view.setText(runtimes.nextToken());
                runtime_text_view.setTextSize(20);
                ll.addView(runtime_text_view, lp);
            }
        }



        //db.getBasedOnId(row_id);
        //Algorithm result = db.getBasedOnId(row_id);
        //result.toString();

    }

}
