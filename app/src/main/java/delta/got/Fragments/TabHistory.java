package delta.got.Fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import delta.got.Activity.HistoryDetails;
import delta.got.Activity.SearchResult;
import delta.got.Adapter.DBlistadapter;
import delta.got.Database.Database;
import delta.got.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TabHistory.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabHistory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabHistory extends Fragment {

    ListView history;
    Database DB;
    public static DBlistadapter listAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TabHistory() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabHistory.
     */
    // TODO: Rename and change types and number of parameters
    public static TabHistory newInstance(String param1, String param2) {
        TabHistory fragment = new TabHistory();
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
        // Inflate the layout for this fragment
        View inflatedview= inflater.inflate(R.layout.fragment_tab_history, container, false);

        DB = new Database(getContext());
        history = inflatedview.findViewById(R.id.history);
        final Cursor cur = DB.getData();
        if (cur.getCount() == 0) {
            //Toast.makeText(getContext(), "No data", Toast.LENGTH_LONG).show();
        } else {
            listAdapter = new DBlistadapter(getContext(),cur);
            history.setAdapter(listAdapter);
        }
        history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), HistoryDetails.class);
                intent.putExtra("selecteditem",i);
                startActivity(intent);
            }
        });

        return inflatedview;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void onResume(){

        super.onResume();
        Cursor cursor = DB.getData();
        listAdapter = new DBlistadapter(getContext(),cursor);
        history.setAdapter(listAdapter);
    }
}
