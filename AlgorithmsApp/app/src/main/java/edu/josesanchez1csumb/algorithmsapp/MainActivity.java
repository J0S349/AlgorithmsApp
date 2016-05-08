package edu.josesanchez1csumb.algorithmsapp;

import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteHelper db = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = SQLiteHelper.getInstance(this);

        // code for Binary Search Algorithm
        Algorithm BinarySearch = new Algorithm();
        BinarySearch.setID(1);
        BinarySearch.setName("Binary Seach Algorithm");
        BinarySearch.setDescription("The idea behind using a binary search algorithm is that you are only " +
                "working with half the information within each iteration. This results in an efficient runtime" +
                "for searching through an array that is sorted in ascending order, preferably."
                );
        //since mostly not all algorithms have a best case and worst case, especially when it comes to
        // the runtimes for graph based problems, we would just concatinate the strings together
        BinarySearch.setRuntime("Best Case: O(log(n))?Worst Case: O(n)");

        // code for Depth First Search Algorithm
        Algorithm DepthFirstSearch = new Algorithm();
        DepthFirstSearch.setID(2);
        DepthFirstSearch.setName("Depth First Search Algorithm");
        DepthFirstSearch.setDescription("The idea behind BFS is that you want to reduce the number of edges" +
                " between nodes in a graph such that you can only one connection per node. The way in which the " +
                "graph would appear is based on the starting node mainly because it will follow the links made " +
                "between each one until it reaches a dead end. It does this by implementing a stack to keep " +
                "track of the nodes visited.  ");
        DepthFirstSearch.setRuntime("Adjacency Matrix: O(|V|^2) where V is number of vertices?" +
                "Adjacency List: O(|V| + |E|) where E is number of edges");

        // code for Breath First Search
        Algorithm BreathFirstSearch = new Algorithm();
        BreathFirstSearch.setID(3);
        BreathFirstSearch.setName("Breath First Search");
        BreathFirstSearch.setDescription("This algorithm is similar to DFS, except that it uses a Queue instead of " +
                "a stack. On each iteration, all unvisited vertices that are adjacent (connected to current node) are" +
                " marked as visited and added to the queue. Once these new nodes are added, the front vertex is " +
                "removed the vertex.");
        BreathFirstSearch.setRuntime("Adjacency Matrix: O( |V|2) where V is number of vertices?Adjacency List: O( |V| + |E| ) where E is number of edges");

        // code for Merge Sort Algorithm
        Algorithm MergeSort = new Algorithm();
        MergeSort.setID(4);
        MergeSort.setName("Merge Sort Algorithm");
        MergeSort.setDescription("The idea behind Merge Sort is that we want to break down the array of numbers into " +
                "smaller pieces that are easier to manage. We do this recursively until we hit the base case (which is " +
                "an array of one value), after that, we do the recursive call and begin to combine the subarrays and sort " +
                "the values. This happens until the whole array has been fully sorted after the recursive call has finished.");
        MergeSort.setRuntime("Best Case: O(n log(n))?Worst Case: O(n^2)");


        // Add all the Algorithms objects into the Database
        db.addAlgorithm(BinarySearch);
        db.addAlgorithm(DepthFirstSearch);
        db.addAlgorithm(BreathFirstSearch);
        db.addAlgorithm(MergeSort);

        ArrayList<Algorithm> algorithms = db.getAll();
        Button algorithm_button;

        //since we don't want to hard code in all the possible algorithms, we should consider
        // creating them dynamically so it can run for however many algorithms, or datastructures,
        // there could be. We do this by getting the Linear layout object which would
        LinearLayout ll = (LinearLayout) findViewById(R.id.algorithm_button_layout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for(int i = 0; i < algorithms.size(); i++){
            algorithm_button = new Button(this);


            algorithm_button.setText(algorithms.get(i).getName());
            algorithm_button.setId(algorithms.get(i).getID());

            algorithm_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "button clicked: " + v.getId(), Toast.LENGTH_SHORT).show();
                    Intent chosenAlgorithm = new Intent(v.getContext(), AlgorithmChosen.class);
                    Bundle info = new Bundle();

                    //info.putSerializable("db_name", db);
                    info.putInt("row_id", v.getId());
                    chosenAlgorithm.putExtras(info);
                    startActivity(chosenAlgorithm);


                }
            });
            ll.addView(algorithm_button, lp);
        }



    }

}
