package edu.sjsu.jaime.nutritionapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Recipe implements Parcelable{

    Recipe[] mObjArray;
    private String name;
    private ArrayList<String> ingredients;
    private String directions;
    private int calories;
    private int carbs;
    private int proteins;

    public Recipe(String name, ArrayList<String> ingredients, String directions, int calories, int carbs, int proteins)
    {
        this.name = name;
        this.ingredients = ingredients;
        this.directions = directions;
        this.calories = calories;
        this.carbs = carbs;
        this.proteins = proteins;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String newName)
    {
        name = newName;
    }

    public ArrayList<String> getIngredients()
    {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> newIngredients)
    {
        ingredients = newIngredients;
    }

    public String getDirections()
    {
        return directions;
    }

    public void setDirections(String newDirections)
    {
        directions = newDirections;
    }

    public int getCalories()
    {
        return calories;
    }

    public void setCalories(int newCalories)
    {
        calories = newCalories;
    }

    public int getCarbs()
    {
        return carbs;
    }

    public void setCarbs(int newCarbs)
    {
        carbs = newCarbs;
    }

    public int getProteins()
    {
        return proteins;
    }

    public void setProteins(int newProteins)
    {
        proteins = newProteins;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeStringList(ingredients);
        parcel.writeString(directions);
        parcel.writeInt(calories);
        parcel.writeInt(carbs);
        parcel.writeInt(proteins);
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>()
    {
        public Recipe createFromParcel(Parcel in)
        {
            return new Recipe(in);
        }

        public Recipe[] newArray(int size)
        {
            return new Recipe[size];
        }
    };

    private Recipe(Parcel parcel)
    {
        name = parcel.readString();
        ingredients = parcel.createStringArrayList();
        directions = parcel.readString();
        calories = parcel.readInt();
        carbs = parcel.readInt();
        proteins = parcel.readInt();
    }


    public String toString()
    {
        return getName();
    }

    //public int hashCode()
    // {

    // }

    public boolean equals(Object x)
    {
        Recipe that = (Recipe) x;
        return this.name.equals(that.name);
    }
}