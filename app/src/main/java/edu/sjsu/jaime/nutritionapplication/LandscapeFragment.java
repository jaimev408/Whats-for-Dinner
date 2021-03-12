package edu.sjsu.jaime.nutritionapplication;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LandscapeFragment extends Fragment {

    ListView mListView;
    Button goBack;

    ArrayList<Recipe> work2;

    TextView ingredientes;
    TextView descripcion;
    TextView calorias;
    TextView proteinas;
    TextView carbos;
    TextView name;

    public LandscapeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_landscape, container, false);

        ingredientes = view.findViewById(R.id.textView12);
        descripcion = view.findViewById(R.id.descriptionSummary);
        calorias = view.findViewById(R.id.show_cal);
        proteinas = view.findViewById(R.id.show_pro);
        carbos = view.findViewById(R.id.show_carbs);
        name = view.findViewById(R.id.nameofrecipe);

        mListView = view.findViewById(R.id.listView2);
        ArrayList<String> test = new ArrayList<String>();

        if (savedInstanceState != null)
        {
            //work2 = savedInstanceState.getParcelableArrayList("frag");
            //Toast.makeText(getActivity(), "Not empty", Toast.LENGTH_LONG).show();

        }
        work2 = getActivity().getIntent().getExtras().getParcelableArrayList("recipes_main");


        if (work2.size() > 0) {
            for (Recipe r : work2)
            {
                test.add(r.getName());
            }
        }


        ArrayAdapter<String> test2 = new ArrayAdapter<String>(mListView.getContext(), android.R.layout.simple_list_item_1, test);
        mListView.setAdapter(test2);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Recipe r = work2.get(i);

                String ing = "";

                for (String s : r.getIngredients())
                {
                    ing += "* " + s + "\n";
                }

                ingredientes.setText(ing);
                name.setText(r.getName());
                descripcion.setText(r.getDirections());
                calorias.setText(String.valueOf(r.getCalories()));
                proteinas.setText(String.valueOf(r.getProteins()));
                carbos.setText(String.valueOf(r.getCarbs()));
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle bundy)
    {
        super.onSaveInstanceState(bundy);
        bundy.putParcelableArrayList("frag2", work2);
    }


}

