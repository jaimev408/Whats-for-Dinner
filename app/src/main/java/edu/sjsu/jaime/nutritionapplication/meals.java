package edu.sjsu.jaime.nutritionapplication;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class meals extends AppCompatActivity {

    ArrayList<Recipe> recipesChosen;
    Recipe[][] recipesMealPlan = new Recipe[3][7];
    private TextView name;
    Spinner spin;
    private RadioGroup days;
    private RadioGroup meals;
    private TextView cal;
    private TextView pro;
    private TextView carb;

    private int calGoals;
    private int proGoals;
    private int carbGials;
    private boolean goalSet;

    private EditText calories_goals;
    private EditText protein_goals;
    private EditText carb_goals;

    private boolean isFirstSelecttion= true;

    private int day;
    private int meal;

    private int protein;
    private int calories;
    private int carbs;

    private RadioGroup.OnCheckedChangeListener daysListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {

            switch (i) {
                case R.id.monday_btn:
                    day = 0;
                    break;
                case R.id.tuesday_btn:
                    day = 1;
                    break;
                case R.id.wed_btn:
                    day = 2;
                    break;
                case R.id.th_btn:
                    day = 3;
                    break;
                case R.id.fri_btn:
                    day = 4;
                    break;
                case R.id.sat_btn:
                    day = 5;
                    break;
                case R.id.sun_btn:
                    day = 6;
            }

            if (days.getCheckedRadioButtonId() != -1 && meals.getCheckedRadioButtonId() != -1)
            {
                if (recipesMealPlan[meal][day] != null)
                {
                    name.setText(recipesMealPlan[meal][day].getName());
                }

                else
                {
                    name.setText("Eating Out");
                }
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener mealsListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i) {
                case R.id.break_btn:
                    meal = 0;
                    break;
                case R.id.lunch_btn:
                    meal = 1;
                    break;
                case R.id.dinner_btn:
                    meal = 2;
                    break;
            }

            if (days.getCheckedRadioButtonId() != -1 && meals.getCheckedRadioButtonId() != -1)
            {
                if (recipesMealPlan[meal][day] != null)
                {
                    name.setText(recipesMealPlan[meal][day].getName());
                }

                else
                {
                    name.setText("Eating Out");
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);


        recipesChosen = getIntent().getExtras().getParcelableArrayList("recipes_chosen");
        pro = findViewById(R.id.pro);
        cal = findViewById(R.id.calo);
        carb = findViewById(R.id.car);

        calories_goals = findViewById(R.id.editText);
        protein_goals = findViewById(R.id.editText2);
        carb_goals = findViewById(R.id.editText3);


        ArrayList<Recipe> temp1 = getIntent().getExtras().getParcelableArrayList("recipes_meal_plan_break");
        ArrayList<Recipe> temp2 = getIntent().getExtras().getParcelableArrayList("recipes_meal_plan_lunch");
        ArrayList<Recipe> temp3 = getIntent().getExtras().getParcelableArrayList("recipes_meal_plan_dinner");

        temp1.toArray(recipesMealPlan[0]);
        temp2.toArray(recipesMealPlan[1]);
        temp3.toArray(recipesMealPlan[2]);



        days = findViewById(R.id.days_group);
        meals = findViewById(R.id.meals_group);
        spin = findViewById(R.id.spinner);
        name = findViewById(R.id.textView17);

        days.setOnCheckedChangeListener(daysListener);
        meals.setOnCheckedChangeListener(mealsListener);


        if (recipesChosen.size() >0)
        {
            ArrayAdapter<Recipe> adapter = new ArrayAdapter<Recipe>(this, android.R.layout.simple_spinner_item, recipesChosen);
            spin.setAdapter(adapter);
        }


        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (days.getCheckedRadioButtonId() != -1 && meals.getCheckedRadioButtonId() != -1)
                {
                    if (recipesMealPlan[meal][day] != null)
                    {
                        protein -= recipesMealPlan[meal][day].getProteins();
                        calories -= recipesMealPlan[meal][day].getCalories();
                        carbs -= recipesMealPlan[meal][day].getCarbs();
                    }
                    recipesMealPlan[meal][day] = (Recipe) adapterView.getItemAtPosition(i);
                    name.setText(recipesMealPlan[meal][day].getName());
                    recipesChosen.remove(i);

                    protein += recipesMealPlan[meal][day].getProteins();
                    calories += recipesMealPlan[meal][day].getCalories();
                    carbs += recipesMealPlan[meal][day].getCarbs();

                    if (goalSet)
                    {
                        if (protein > proGoals && calories > calGoals && carbs > carbGials)
                        {
                            Toast.makeText(meals.this, "GOALS MET", Toast.LENGTH_LONG).show();
                        }
                    }

                    pro.setText(String.valueOf(protein));
                    cal.setText(String.valueOf(calories));
                    carb.setText(String.valueOf(carbs));

                }
                else
                {
                    Toast.makeText(meals.this, "Please select both a day and meal", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void calculate(View view)
    {
        goalSet = true;
        if (calories_goals.getText().toString().length() > 0 )
        {
            calGoals = Integer.valueOf(calories_goals.getText().toString());
        }

        else
        {
            calGoals = 0;
        }
        if (protein_goals.getText().toString().length() > 0 )
        {
            proGoals = Integer.valueOf(protein_goals.getText().toString());
        }

        else
        {
            proGoals = 0;
        }
        if (carb_goals.getText().toString().length() > 0 )
        {
            carbGials = Integer.valueOf(carb_goals.getText().toString());
        }

        else
        {
            carbGials = 0;
        }



    }

    public void addMealPlan(View view)
    {
        ArrayList<Recipe> breakPlan = new ArrayList<>(Arrays.asList(recipesMealPlan[0]));
        ArrayList<Recipe> lunchPlan = new ArrayList<>(Arrays.asList(recipesMealPlan[1]));
        ArrayList<Recipe> dinnerPlan = new ArrayList<>(Arrays.asList(recipesMealPlan[2]));



        Intent output =new Intent();
        output.putExtra("b", breakPlan);
        output.putExtra("l", lunchPlan);
        output.putExtra("d", dinnerPlan);
        output.putExtra("haha", recipesChosen);

        setResult(RESULT_OK, output);
        finish();
    }

    public void goBack(View view)
    {
        finish();
    }
}
