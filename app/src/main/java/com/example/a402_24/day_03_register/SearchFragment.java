package com.example.a402_24.day_03_register;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

EditText searchEdit;
    Button search;

    private static final String ip ="http://192.168.10.24:8080";
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Member> memberArrayList;
    RecyclerAdapter_report myAdapter;

    public void setRefs(View v){
        mRecyclerView = v.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(v.getContext().getApplicationContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
        searchEdit = v.findViewById(R.id.editText);
        search = v.findViewById(R.id.button3);
    }

    public void setEvents(View v){
/*
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String member_id = "member_name="+searchEdit.getText().toString();

                            URL url = new URL(ip+"/JS/android/rv_board/searchId");
                            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
                            httpUrlConnection.setRequestMethod("POST");
                            httpUrlConnection.setDoOutput(true);
                            httpUrlConnection.setDoInput(true);
                            httpUrlConnection.getOutputStream().write(member_id.getBytes());

                            if (httpUrlConnection.getResponseCode() == 200) {
                                BufferedReader br = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
                                StringBuffer sb = new StringBuffer();
                                String temp = null;
                                while((temp = br.readLine())!=null){
                                    sb.append(temp);
                                }
                                String JsonMember = sb.toString();
                                JSONArray jsonArray = new JSONArray(JsonMember);
                                final ArrayList<Member> memberList = new ArrayList<>();
                                for( int i = 0 ; i < jsonArray.length() ; i++){
                                    Member member = new Member();
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    member.setMember_id(jsonObject.getString("member_id"));
                                    member.setMember_name(jsonObject.getString("member_name"));
                                    member.setMember_profile_pic(jsonObject.getString("member_profile_pic"));
                                    member.setMember_gender(jsonObject.getString("member_gender"));
                                    member.setMember_Street_name_address(jsonObject.getString("member_Street_name_address"));

                                    memberList.add(member);
                                }


                                memberArrayList = new ArrayList<>();
                                if(memberList != null) {
                                    Log.d("inRunOn:",memberList.get(0).getMember_gender().toString());
                                    for (int i = 0; i < memberList.size(); i++) {

                                        memberArrayList.add(new Member(memberList.get(i).getMember_id(),memberList.get(i).getMember_gender(),memberList.get(i).getMember_name(),memberList.get(i).getMember_profile_pic(),memberList.get(i).getMember_Street_name_address()));
                                        Log.d("inRunOn2:",memberArrayList.get(0).getMember_id());
                                    }

                                    myAdapter = new RecyclerAdapter_report(memberArrayList);

                                    mRecyclerView.setAdapter(myAdapter);

                                }


                            }
                        }catch (Exception e){

                        }
                    }
                });
            }
        });
*/
        // 리사이클뷰의 각 영역 클릭시 클릭이벤트 처리
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){
            //
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                Intent intent = new Intent(getActivity(),Rv_boardSelected.class);
                intent.putExtra("member_id",memberArrayList.get(position).getMember_id());
                intent.putExtra("member_name",memberArrayList.get(position).getMember_name());
                intent.putExtra("member_gender",memberArrayList.get(position).getMember_gender());
                intent.putExtra("member_profile_pic",memberArrayList.get(position).getMember_profile_pic());
                intent.putExtra("member_Street_name_address",memberArrayList.get(position).getMember_Street_name_address());

                startActivity(intent);
            }
        });
    }

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_search, container, false);


        setRefs(v);
        setEvents(v);
        return v;
    }

}
