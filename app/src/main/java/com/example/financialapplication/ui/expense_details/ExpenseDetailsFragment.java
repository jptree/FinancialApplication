package com.example.financialapplication.ui.expense_details;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

//import com.example.financialapplication.ExpenseDetailsFragmentArgs;
import com.example.financialapplication.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExpenseDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExpenseDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExpenseDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static String expense;
    private ExpenseDetailsViewModel expenseDetailsViewModel;

    TestAdapter testAdapter;
    ViewPager2 viewPager2;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ExpenseDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExpenseDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExpenseDetailsFragment newInstance(String param1, String param2) {
        ExpenseDetailsFragment fragment = new ExpenseDetailsFragment();
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

//        expenseDetailsViewModel =
//                ViewModelProviders.of(this).get(ExpenseDetailsViewModel.class);
//        expenseDetailsViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                getActivity().setTitle(expense + " Details");
//            }
//        });


        return inflater.inflate(R.layout.fragment_expense_details, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        TextView tv = view.findViewById(R.id.text_expense_detail_title);
//        String expenseTitle = ExpenseDetailsFragmentArgs.fromBundle(getArguments()).getExpense();
//        tv.setText(expenseTitle);
        testAdapter = new TestAdapter(this);
        viewPager2 = view.findViewById(R.id.pager);
        viewPager2.setAdapter(testAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        String[] tabTitles = new String[2];
        tabTitles[0] = "Overview";
        tabTitles[1] = "TimeLine";
        new TabLayoutMediator(tabLayout, viewPager2,(tab, position) -> tab.setText(tabTitles[position])
        ).attach();

    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }



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

    public class TestAdapter extends FragmentStateAdapter {
        public TestAdapter(Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Fragment fragment;

            switch (position) {
                case 0:
                    fragment = new TestObjFragment();
                    return fragment;
                case 1:
                    fragment = new TestObjFragmentT();
                    return fragment;
                default:
                    Log.d(TAG, "createFragment: Used default??");
                    fragment = new TestObjFragment();
                    return fragment;
            }

//            Fragment fragment = new TestObjFragment();
//            Bundle args = new Bundle();
//            args.putInt("First", position + 1);
//            fragment.setArguments(args);
//            return fragment;
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }

    public static class TestObjFragment extends Fragment {
        public static final String ARG_OBJECT = "Test";

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_first_page_expense_details, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            Bundle args = getArguments();
//            ViewPager2 viewPager = view.findViewById(R.id.pager);


        }
    }

    public static class TestObjFragmentT extends Fragment {
        public static final String ARG_OBJECT = "Test";
        private ExpenseDrawableView customDrawableView;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {

            View root = inflater.inflate(R.layout.fragment_second_page_expense_details, container, false);
            LinearLayout layout = (LinearLayout) root.findViewById(R.id.linear_layout_expense_drawable);

            customDrawableView = new ExpenseDrawableView(getContext());
            layout.addView(customDrawableView);

            return root;
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            Bundle args = getArguments();
//            ViewPager2 viewPager = view.findViewById(R.id.pager);


        }
    }

}
