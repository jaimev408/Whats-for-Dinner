package edu.sjsu.jaime.nutritionapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ImageButton mainLogo;
    ArrayList<Recipe> recipes;
    ArrayList<Integer> positions;
    ArrayList<Recipe> recipesChosen;
    Recipe[][] recipesMealPlan;

    HashMap<String, Integer>  ingredients;

    //int[][] tester = new int[3][2];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        tester[0][0] = 1;
        tester[0][1] = 32;
        tester[1][0] = 2;
        tester[2][0] = 3;
        */

        //code to save from landscape
        if (savedInstanceState != null)
        {
            //Toast.makeText(this,"null", Toast.LENGTH_LONG).show();
            recipes = savedInstanceState.getParcelableArrayList("recetas");
        }
        else
        {
            //Toast.makeText(this, "not null", Toast.LENGTH_LONG).show();
            recipes = new ArrayList<>();
            positions = new ArrayList<>();
            recipesChosen = new ArrayList<>();
            recipesMealPlan = new Recipe[3][7];
            ingredients = new HashMap<>();
        }


        mainLogo = findViewById(R.id.imageButton2);

        mainLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent helpIntent = new Intent(MainActivity.this, message.class);
                startActivity(helpIntent);
            }
        });
    }

    public void goToMealPlanner(View view)
    {
        Intent mealIntent = new Intent(MainActivity.this, meals.class);

        mealIntent.putExtra("recipes_chosen", recipesChosen);

        ArrayList<Recipe> breakPlan = new ArrayList<>(Arrays.asList(recipesMealPlan[0]));
        ArrayList<Recipe> lunchPlan = new ArrayList<>(Arrays.asList(recipesMealPlan[1]));
        ArrayList<Recipe> dinnerPlan = new ArrayList<>(Arrays.asList(recipesMealPlan[2]));

        mealIntent.putExtra("recipes_meal_plan_break", breakPlan);
        mealIntent.putExtra("recipes_meal_plan_lunch", lunchPlan);
        mealIntent.putExtra("recipes_meal_plan_dinner", dinnerPlan);

        startActivityForResult(mealIntent,4);
    }

    public void goToIngredients(View view)
    {
        Intent ingIntent = new Intent(MainActivity.this, Main4Activity.class);
        ingIntent.putExtra("map", ingredients);
        startActivityForResult(ingIntent, 6);
    }

    public void goToNewDish(View view)
    {
        Intent dishIntent = new Intent(MainActivity.this, newDishActivity.class);
        dishIntent.putExtra("recipes_main", recipes);
        //dishIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityForResult(dishIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 1)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                 recipes = data.getParcelableArrayListExtra("new_recipes");
            }
        }

        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK)
            {
                recipes = data.getParcelableArrayListExtra("edit_recipe");

                positions = data.getIntegerArrayListExtra("new_positions");

                if (positions.size() > 0) {
                    for (int i : positions) {
                        recipesChosen.add(recipes.get(i));
                    }
                }

                positions.clear();

            }
        }

        if (requestCode == 4)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                ArrayList<Recipe> temp1 = data.getParcelableArrayListExtra("b");
                ArrayList<Recipe> temp2 = data.getParcelableArrayListExtra("l");
                ArrayList<Recipe> temp3 = data.getParcelableArrayListExtra("d");

                for (Recipe r : temp1)
                {
                    if (r != null)
                    {
                        ArrayList<String> in = r.getIngredients();

                        for (String s : in)
                        {
                            if (ingredients.containsKey(s)) {
                                ingredients.put(s, ingredients.get(s) + 1);
                            } else {
                                ingredients.put(s, 1);
                            }
                        }
                    }
                }

                for (Recipe r : temp2)
                {
                    if (r != null)
                    {
                        ArrayList<String> in = r.getIngredients();

                        for (String s : in)
                        {
                            if (ingredients.containsKey(s)) {
                                ingredients.put(s, ingredients.get(s) + 1);
                            } else {
                                ingredients.put(s, 1);
                            }
                        }
                    }
                }

                for (Recipe r : temp3)
                {
                    if (r != null)
                    {
                        ArrayList<String> in = r.getIngredients();

                        for (String s : in)
                        {
                            if (ingredients.containsKey(s)) {
                                ingredients.put(s, ingredients.get(s) + 1);
                            } else {
                                ingredients.put(s, 1);
                            }
                        }
                    }
                }
                temp1.toArray(recipesMealPlan[0]);
                temp2.toArray(recipesMealPlan[1]);
                temp3.toArray(recipesMealPlan[2]);


                recipesChosen = data.getParcelableArrayListExtra("haha");


                /*
                for (Recipe[] r : recipesMealPlan)
                {
                    for (Recipe e : r)
                    {
                        if (e != null) {
                            ArrayList<String> in = e.getIngredients();

                            for (String s : in) {
                                if (ingredients.containsKey(s)) {
                                    ingredients.put(s, ingredients.get(s) + 1);
                                } else {
                                    ingredients.put(s, 1);
                                }
                            }
                        }

                    }
                }
                */
            }
        }
    }



    public void goToRecipes(View view)
    {
        Intent recipesIntent = new Intent(MainActivity.this, Recipes.class);
        recipesIntent.putExtra("recipes_main", recipes);
        recipesIntent.putIntegerArrayListExtra("position_main", positions);
        startActivityForResult(recipesIntent, 2);
    }



}
