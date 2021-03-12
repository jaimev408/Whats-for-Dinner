package edu.sjsu.jaime.nutritionapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PortraitFragment extends Fragment {

    ListView mListView;
    Button goBack;
    ArrayList<Integer> position;

    ArrayList<Recipe> work;

    public PortraitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_portrait, container, false);

        if (savedInstanceState != null)
        {
            //work = savedInstanceState.getParcelableArrayList("frag2");
            Toast.makeText(getActivity(), "not empty", Toast.LENGTH_LONG).show();
        }


        work = getActivity().getIntent().getExtras().getParcelableArrayList("recipes_main");

        position = getActivity().getIntent().getIntegerArrayListExtra("position_main");

        goBack = view.findViewById(R.id.button6);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent output = new Intent();
                output.putIntegerArrayListExtra("new_positions", position);
                output.putParcelableArrayListExtra("edit_recipe", work);
                getActivity().setResult(Activity.RESULT_OK, output);
                getActivity().finish();
            }
        });

        mListView = view.findViewById(R.id.listview);
        ArrayList<String> test = new ArrayList<String>();

        if (work.size() > 0) {
            for (Recipe r : work)
            {
                test.add(r.getName());
            }
        }


        ArrayAdapter<String> test2 = new ArrayAdapter<String>(mListView.getContext(), android.R.layout.simple_list_item_1, test);
        mListView.setAdapter(test2);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), Main3Activity.class);
                int pos = i;
                intent.putExtra("sent_work", work);
                intent.putExtra("position", pos);
                startActivityForResult(intent, 3);
                return false;
            }

        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.print(i);
                int pos = i;
                position.add(pos);
                //Toast.makeText(getActivity(), position.toString(), Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();

        ArrayList<String> test = new ArrayList<String>();
        if (work.size() > 0) {
            for (Recipe r : work)
            {
                test.add(r.getName());
            }
        }


        ArrayAdapter<String> test2 = new ArrayAdapter<String>(mListView.getContext(), android.R.layout.simple_list_item_1, test);
        mListView.setAdapter(test2);
    }

    @Override
    public void onSaveInstanceState(Bundle bundy)
    {
        super.onSaveInstanceState(bundy);
        bundy.putParcelableArrayList("frag", work);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 3)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                work = data.getParcelableArrayListExtra("edit_recipes");
            }
        }
    }

}
