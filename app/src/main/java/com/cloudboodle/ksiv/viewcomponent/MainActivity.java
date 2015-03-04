package com.cloudboodle.ksiv.viewcomponent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity {
    // global variable to save the last button pressed
    View lastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        lastView = null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Called when the start button is pressed.
     * @param view
     */
    public void btnStartClicked(View view){
        // Create an intent - an obj used to start another activity
        Intent intent = new Intent(this, NextActivity.class);

        // Displays the QuizActivity on the screen.
        startActivity(intent);

        // Disable the dumb default animation
        overridePendingTransition(0, 0);

    }


    /**
     * Called when a button selecting the difficulty is pressed.
     * Changes the icon of a selected image button.
     * @param view
     */
    public void btnLevelClicked(View view){

        // Finds the clicked button and changes its image state to the selected one.
        switch (view.getId()){
            case R.id.btn_lvl_easy:
                ImageButton btnEasy = (ImageButton)view.findViewById(R.id.btn_lvl_easy);
                //btnEasy.setImageResource(R.drawable.btn_lvl_easy_selected);
                if (btnEasy.isSelected()) {
                    btnEasy.setImageResource(R.drawable.btn_lvl_easy);
                }
                btnEasy.setImageResource(R.drawable.btn_lvl_easy_selected);
                displayLevelScore(view);
                break;
            case R.id.btn_lvl_med:
                ImageButton btnMed = (ImageButton)view.findViewById(R.id.btn_lvl_med);
                btnMed.setImageResource(R.drawable.btn_lvl_med_selected);
                displayLevelScore(view);
                break;
            case R.id.btn_lvl_hard:
                ImageButton btnHard = (ImageButton)view.findViewById(R.id.btn_lvl_hard);
                btnHard.setImageResource(R.drawable.btn_lvl_hard_selected);
                displayLevelScore(view);
                break;

        }

        // If a new button is pressed, the image state of the previously pressed button is restored.
        if(lastView != null && lastView.getId() != view.getId()){
            switch (lastView.getId()){
                case R.id.btn_lvl_easy:
                    ImageButton btnEasy = (ImageButton)lastView.findViewById(R.id.btn_lvl_easy);
                    btnEasy.setImageResource(R.drawable.btn_lvl_easy);
                    break;
                case R.id.btn_lvl_med:
                    ImageButton btnMed = (ImageButton)lastView.findViewById(R.id.btn_lvl_med);
                    btnMed.setImageResource(R.drawable.btn_lvl_med);
                    break;
                case R.id.btn_lvl_hard:
                    ImageButton btnHard = (ImageButton)lastView.findViewById(R.id.btn_lvl_hard);
                    btnHard.setImageResource(R.drawable.btn_lvl_hard);
                    break;

            }
        }

        // Save the last view for info about the last selected button
        lastView = view;
    }


    /**
     * Displays the score associated with the selected level.
     * @param view
     */
    public void displayLevelScore(View view){
        // temporary example scores. change later!!
        int easy = 20;
        int medium = 13;
        int hard = 1;

        int score = 0;

        switch (view.getId()){
            case R.id.btn_lvl_easy:
                score = easy;
                break;
            case R.id.btn_lvl_med:
                score = medium;
                break;
            case R.id.btn_lvl_hard:
                score = hard;
                break;
        }

        TextView textView = (TextView)findViewById(R.id.textview_display_score);
        //textView.setTextSize(70); //97pt
        textView.setText(Integer.toString(score));

        TextView pointsText = (TextView)findViewById(R.id.textview_points);
        //pointsText.setTextSize(25);//30
        if(score == 1) {
            pointsText.setText("Point");
        } else {
            pointsText.setText("Points");
        }

    }
}
