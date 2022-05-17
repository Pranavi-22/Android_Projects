package course.examples.UI.GridLayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;

public class fact_activity extends AppCompatActivity {

    int animalPosition = 0;


    String[] names = {"rabbit" , "fox" , "eagle" ,  "koala" , "fish" , "bear" , "dog" , "cat" , "elephant" , "sheep" , "tiger" , "turtle" };
    String[] weights = {"2.2 lbs" , "15.4 lbs" , "14 lbs" ,  "15 lbs" , "14 lbs" , "300 lbs" , "70 lbs" , "25 lbs" , "6000 lbs" , "120 lbs" , "400 lbs" , "90 lbs" };
    String[] lifeSpans = {"9 years" , "14 years" , "20 years" ,  "10 years" , "4 years" , "13 years" , "10 years" , "18 years" , "80 years" , "12 years" , "10 years" , "80 years" };
    String[] endangereds = {"no" , "no" , "no" ,  "no" , "no" , "no" , "no", "no" , "no", "no" , "yes" , "no" };
    String[] habitats = {"grassland" , "deserts" , "largelakes" ,  "forest" , "water" , "mountain" , "grasslands", "savannas" , "forest", "farmland" , "forests" , "lakes" };
    String[] feedings = {"herbivores" , "carnivores" , "carnivores" ,  "herbivores" , "omnivores" , "omnivores" , "omnivores", "carnivores" , "herbivores", "herbivores" , "carnivores" , "omnivores" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact);

        Intent intent = getIntent();
        String str1 = GridLayoutActivity.EXTRA_RES_ID;
        animalPosition = intent.getIntExtra(str1 , 0) ;

        TextView name = (TextView) findViewById(R.id.animalNameText);
        name.setText(names[animalPosition]);

        TextView weight = (TextView) findViewById(R.id.animalNameText2);
        weight.setText(weights[animalPosition]);


        TextView lifeSpan = (TextView) findViewById(R.id.animalNameText3);
        lifeSpan.setText(lifeSpans[animalPosition]);

        TextView endangered = (TextView) findViewById(R.id.animalNameText5);
        endangered.setText(endangereds[animalPosition]);

        TextView habitat = (TextView) findViewById(R.id.animalNameText6);
        habitat.setText(habitats[animalPosition]);

        TextView feeding = (TextView) findViewById(R.id.animalNameText7);
        feeding.setText(feedings[animalPosition]);

    }



}