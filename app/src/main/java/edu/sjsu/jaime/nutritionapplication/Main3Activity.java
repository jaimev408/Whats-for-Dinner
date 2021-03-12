package edu.sjsu.jaime.nutritionapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    ArrayList<Recipe> recipes3;
    int position;
    //boolean sameName = false;

    ArrayList<String> newIngredients;
    boolean found = false;

    Button goBack;
    Button add;
    EditText recipeName;
    EditText recipeDescription;
    AutoCompleteTextView ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6, ingredient7, ingredient8, ingredient9, ingredient10;
    EditText calories;
    EditText protein;
    EditText carbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        recipes3 = getIntent().getExtras().getParcelableArrayList("sent_work");
        position = getIntent().getExtras().getInt("position");

        String poss = String.valueOf(position);

        getUIComponents();
        AutoCompleteTextView[] eTexts = new AutoCompleteTextView[]{ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6, ingredient7, ingredient8, ingredient9, ingredient10};
        //Toast.makeText(this, poss, Toast.LENGTH_LONG).show();
        readComponents(eTexts);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void getUIComponents()
    {
        goBack = findViewById(R.id.edit_go_back);
        add = findViewById(R.id.ingredient_edit_btn);
        recipeName = findViewById(R.id.edit_recipe_name);
        recipeDescription = findViewById(R.id.edit_recipe_description);
        ingredient1 = findViewById(R.id.edit_ingredient1);
        ingredient2 = findViewById(R.id.edit_ingredient2);
        ingredient3 = findViewById(R.id.edit_ingredient3);
        ingredient4 = findViewById(R.id.edit_ingredient4);
        ingredient5 = findViewById(R.id.edit_ingredient5);
        ingredient6 = findViewById(R.id.edit_ingredient6);
        ingredient7 = findViewById(R.id.edit_ingredient7);
        ingredient8 = findViewById(R.id.edit_ingredient8);
        ingredient9 = findViewById(R.id.edit_ingredient9);
        ingredient10 = findViewById(R.id.edit_ingredient10);
        calories = findViewById(R.id.edit_calories_text);
        protein = findViewById(R.id.edit_protein_text);
        carbs = findViewById(R.id.edit_carbs_text);
    }

    private void readComponents(AutoCompleteTextView[] eTexts)
    {
        recipeName.setText(recipes3.get(position).getName());

        ArrayList<String> ingred = recipes3.get(position).getIngredients();

        for (int i =0; i < ingred.size(); i++)
        {
            String temp = ingred.get(i);
            eTexts[i].setText(temp);
        }

        recipeDescription.setText(recipes3.get(position).getDirections());
        calories.setText(String.valueOf(recipes3.get(position).getCalories()));
        protein.setText(String.valueOf(recipes3.get(position).getProteins()));
        carbs.setText(String.valueOf(recipes3.get(position).getCarbs()));



    }

    public void changeNow(View view)
    {
        newIngredients = new ArrayList<>();
        String newName = String.valueOf(recipeName.getText().toString());
        String newDescription = String.valueOf(recipeDescription.getText().toString());
        int newCal = 0;
        int newPro = 0;
        int newCarb = 0;
        if (calories.getText().toString().trim().length() > 0)
        {
            newCal = Integer.valueOf(calories.getText().toString());
        }
        else
        {
            newCal = 0;
        }
        if (protein.getText().toString().trim().length() > 0)
        {
            newPro = Integer.valueOf(protein.getText().toString());
        }
        else
        {
            newPro = 0;
        }
        if (carbs.getText().toString().trim().length() > 0)
        {
            newCarb = Integer.valueOf(carbs.getText().toString());
        }
        else
        {
            newCarb = 0;
        }

        AutoCompleteTextView[] eTexts = new AutoCompleteTextView[]{ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6, ingredient7, ingredient8, ingredient9, ingredient10};

        //fix for empty
        for (AutoCompleteTextView e : eTexts) {
            if (e.getText().toString().trim().length() > 0) {
                String temp = String.valueOf(e.getText().toString());
                newIngredients.add(temp);
            }
        }



        if (recipeName.getText().toString().trim().length() == 0)
        {
            Toast.makeText(this, "Recipe Name empty", Toast.LENGTH_LONG).show();

        }

        else
        {

            recipes3.get(position).setName(newName);
            recipes3.get(position).setDirections(newDescription);
            recipes3.get(position).setIngredients(newIngredients);
            recipes3.get(position).setCalories(newCal);
            recipes3.get(position).setProteins(newPro);
            recipes3.get(position).setCarbs(newCarb);


            Intent output =new Intent();
            output.putExtra("edit_recipes", recipes3);
            setResult(RESULT_OK, output);
            finish();
        }
    }
}
