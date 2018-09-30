package delta.got.Fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import delta.got.Activity.ResultsFromDatabase;
import delta.got.Activity.SearchResult;
import delta.got.Database.Database;
import delta.got.Model.CharacterName;
import delta.got.Model.Cultures;
import delta.got.Model.data;
import delta.got.R;
import delta.got.Rest.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TabSearch.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabSearch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabSearch extends Fragment {
    ArrayList<String> arrayList;
    ListView listView;
    ArrayAdapter<String> adapter;
    EditText et;
    Button btn;
    Database DB;
    Cursor cursor,cur;
    String name;
    ProgressBar progressBar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TabSearch() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabSearch.
     */
    // TODO: Rename and change types and number of parameters
    public static TabSearch newInstance(String param1, String param2) {
        TabSearch fragment = new TabSearch();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflatedView = inflater.inflate(R.layout.fragment_tab_search, container, false);
        et=inflatedView.findViewById(R.id.src);
        DB = new Database(getContext());
        progressBar = inflatedView.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        arrayList = new ArrayList<>();
        listView = inflatedView.findViewById(R.id.list);
        getCultures();


        cursor = DB.getData();
        btn = inflatedView.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                name = et.getText().toString();

                et.setText(" ");
                    cur = DB.getDetails(name);
                    if(cur.getCount()!=0){

                        if(cur.moveToFirst()){
                            Intent intent = new Intent(getContext(), ResultsFromDatabase.class);
                            intent.putExtra("name",cur.getString(1));
                            intent.putExtra("img",cur.getBlob(6));
                            intent.putExtra("house",cur.getString(3));
                            intent.putExtra("culture",cur.getString(2));
                            intent.putExtra("books",cur.getString(4));
                            intent.putExtra("titles",cur.getString(5));
                            startActivity(intent);
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
               else{
                    getCharacters(name);
                }
            }
        });

        return inflatedView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    void getCharacters(String name){

        String url ="https://api.got.show/api/characters/"+name;

        Call<CharacterName> call = ApiService.getInstance().getName(url);

        call.enqueue(new Callback<CharacterName>() {
            @Override
            public void onResponse(Call<CharacterName> call, Response<CharacterName> response) {
                try{
                    CharacterName character = response.body();
                    data data = character.getData();
                    String name = data.getName();
                    String imgurl = data.getImageLink();
                    String house = data.getHouse();
                    String culture = data.getCulture();
                    List<String> books = data.getBooks();
                    List<String> titles = data.getTitles();
                    String tbooks = String.valueOf(books.get(0));
                    String ttitles = String.valueOf(titles.get(0));


                    for (int i=1;i<books.size();i++){
                        tbooks+= ","+String.valueOf(books.get(i));
                    }
                    for (int i=1;i<titles.size();i++){
                        ttitles+= ","+String.valueOf(titles.get(i));
                    }


                    Intent intent = new Intent(getContext(), SearchResult.class);
                    intent.putExtra("name",name);
                    intent.putExtra("imgurl",imgurl);
                    intent.putExtra("house",house);
                    intent.putExtra("culture",culture);
                    intent.putExtra("books",tbooks);
                    intent.putExtra("titles",ttitles);
                    startActivity(intent);


                }catch (Exception ex){
                    Log.d(TAG, "onResponse: "+ex.getMessage());
                    Toast.makeText(getActivity(),"CharacterName doesn't exist",Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<CharacterName> call, Throwable t) {
                Log.d("onFailure:",t.getMessage());
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(),"oops!Something went wrong",Toast.LENGTH_LONG).show();
            }
        });
    }

    void getCultures(){
        Call<List<Cultures>> call = ApiService.getInstance().getAllCultures();

        call.enqueue(new Callback<List<Cultures>>() {
            @Override
            public void onResponse(Call<List<Cultures>> call, Response<List<Cultures>> response) {
                try{
                    List<Cultures> cultures = response.body();
                    for(int i=0;i<cultures.size();i++){
                    String name = cultures.get(i).getName();
                    arrayList.add(name);}
                    adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,arrayList);
                    listView.setAdapter(adapter);

                }catch(Exception ex){
                    Log.d(TAG, "onResponse: "+ex.getMessage());

                }
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<Cultures>> call, Throwable t) {
                Log.d("onFailure:",t.getMessage());
                Toast.makeText(getActivity(),"oops!Something went wrong",Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }


}
