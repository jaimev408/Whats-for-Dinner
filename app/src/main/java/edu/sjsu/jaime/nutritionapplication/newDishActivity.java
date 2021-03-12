package edu.sjsu.jaime.nutritionapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class newDishActivity extends AppCompatActivity {

    ArrayList<Recipe> recipes2;
    ArrayList<String> ingredients;
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
        setContentView(R.layout.activity_main2);

        recipes2 = getIntent().getExtras().getParcelableArrayList("recipes_main");
        //AutoCompleteTextView[] eTexts = new AutoCompleteTextView[]{ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6, ingredient7, ingredient8, ingredient9, ingredient10};


        getUIComponents();
        AutoCompleteTextView[] eTexts = new AutoCompleteTextView[]{ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6, ingredient7, ingredient8, ingredient9, ingredient10};


        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //Intent home = new Intent(newDishActivity.this, MainActivity.class);
               // startActivity(home);
            }
        });

        recipeName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b)
                {
                    String test = String.valueOf(recipeName.getText().toString());

                    for (Recipe r : recipes2)
                    {
                        if (test.equals(r.getName()))
                        {
                            Toast.makeText(getApplicationContext(), "Recipe already exists", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

        ArrayList<String> repeated = new ArrayList<>();

        for (Recipe r : recipes2)
        {
            ArrayList<String> temp = r.getIngredients();

            for (String s : temp)
            {
                if (!(repeated.contains(s)))
                {
                    repeated.add(s);
                }
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, repeated);

        for (AutoCompleteTextView a : eTexts)
        {
            a.setAdapter(adapter);
        }

    }


    private void getUIComponents()
    {
        goBack = findViewById(R.id.ingredient_go_back);
        add = findViewById(R.id.ingredient_add_btn);
        recipeName = findViewById(R.id.recipe_name);
        recipeDescription = findViewById(R.id.recipe_description);
        ingredient1 = findViewById(R.id.ingredient1);
        ingredient2 = findViewById(R.id.ingredient2);
        ingredient3 = findViewById(R.id.ingredient3);
        ingredient4 = findViewById(R.id.ingredient4);
        ingredient5 = findViewById(R.id.ingredient5);
        ingredient6 = findViewById(R.id.ingredient6);
        ingredient7 = findViewById(R.id.ingredient7);
        ingredient8 = findViewById(R.id.ingredient8);
        ingredient9 = findViewById(R.id.ingredient9);
        ingredient10 = findViewById(R.id.ingredient10);
        calories = findViewById(R.id.calories_text);
        protein = findViewById(R.id.protein_text);
        carbs = findViewById(R.id.carbs_text);
    }

    public void makeRecipe(View view) {
        ingredients = new ArrayList<>();
        String name = String.valueOf(recipeName.getText().toString());
        AutoCompleteTextView[] eTexts = new AutoCompleteTextView[]{ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6, ingredient7, ingredient8, ingredient9, ingredient10};


        for (Recipe r : recipes2) {
            if (r.getName().equals(name)) {
                found = true;
            }
        }


        if (found) {
            Toast.makeText(this, "Recipe already exists", Toast.LENGTH_LONG).show();
            found = false;
        }
        else if(recipeName.getText().toString().trim().length() == 0)
            {
                Toast.makeText(this, "Name required", Toast.LENGTH_LONG).show();
            }
        else {

            for (AutoCompleteTextView e : eTexts) {
                if (e.getText().toString().trim().length() > 0) {
                    String temp = String.valueOf(e.getText().toString());
                    ingredients.add(temp);

                }
            }

            int cal, pro, carb;

            if (calories.getText().toString().trim().length() > 0) {
                cal = Integer.parseInt(calories.getText().toString());
            } else {
                cal = 0;
            }

            if (protein.getText().toString().trim().length() > 0) {
                pro = Integer.parseInt(protein.getText().toString());
            } else {
                pro = 0;
            }

            if (carbs.getText().toString().trim().length() > 0) {
                carb = Integer.parseInt(carbs.getText().toString());
            } else {
                carb = 0;
            }

            String description = String.valueOf(recipeDescription.getText().toString());


            Recipe rec = new Recipe(name, ingredients, description, cal, carb, pro);
            recipes2.add(rec);


            //Toast.makeText(this, recipes2.get(0).toString(), Toast.LENGTH_LONG).show();
            Intent output =new Intent();
            output.putExtra("new_recipes", recipes2);
            setResult(RESULT_OK, output);
            finish();
        }

    }

}
