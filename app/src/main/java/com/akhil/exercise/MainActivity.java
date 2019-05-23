package com.akhil.exercise;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.akhil.exercise.Adapter.UserListAdapter;
import com.akhil.exercise.Modal.ResultList;
import com.akhil.exercise.Utilites.Datastore;
import com.akhil.exercise.Utilites.VolleyService;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Datastore mResultCallback = null;
    VolleyService mVolleyService;
    UserListAdapter lsitViewAdapter;
    RecyclerView rvUserList;
    ListView list_view;
    ArrayList<ResultList> resultLists = new ArrayList<ResultList>();
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvUserList = (RecyclerView) findViewById(R.id.rvUserList);

        list_view_api();

        mVolleyService = new VolleyService(mResultCallback, getApplicationContext());
        mVolleyService.getDataVolley("GETCALL", "https://randomuser.me/api/?results=10");
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvUserList.setLayoutManager(manager);


        lsitViewAdapter = new UserListAdapter(resultLists, MainActivity.this);
        lsitViewAdapter.notifyDataSetChanged();


    }


    private void list_view_api() {
        mResultCallback = new Datastore() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        ResultList resultList = new ResultList();
                        String gender = jsonObject.getString("gender");
                        String email = jsonObject.getString("email");
                        String phone = jsonObject.getString("phone");
                        String cell = jsonObject.getString("cell");
                        JSONObject jsonObject1 = jsonObject.getJSONObject("name");
                        String first = jsonObject1.getString("first");
                        String last = jsonObject1.getString("last");
                        String title = jsonObject1.getString("title");
                        JSONObject location = jsonObject.getJSONObject("location");

                        String street = location.getString("street");
                        String city = location.getString("city");
                        String state = location.getString("state");
                        String postcode = location.getString("postcode");
                        JSONObject dob = jsonObject.getJSONObject("dob");
                        String age = dob.getString("age");
                        String date = dob.getString("date");
                        JSONObject picture = jsonObject.getJSONObject("picture");
                        String large = picture.getString("large");
                        String medium = picture.getString("medium");
                        JSONObject timezone = location.getJSONObject("timezone");

                        String offset = timezone.getString("offset");
                        String description = timezone.getString("description");


                        JSONObject registered = jsonObject.getJSONObject("registered");
                        String registered_date = registered.getString("date");
                        String registered_age = registered.getString("age");
                        resultList.setAge(age);
                        resultList.setState(state);
                        resultList.setStreet(street);
                        resultList.setCity(city);
                        resultList.setEmail(email);
                        resultList.setTitle(title);
                        resultList.setDate(date);
                        resultList.setRegistered_age(registered_age);
                        resultList.setRegistered_date(registered_date);
                        resultList.setLarge(large);
                        resultList.setPostcode(postcode);
                        resultList.setGender(gender);
                        resultList.setFirst(first);
                        resultList.setLast(last);
                        resultList.setPhone(phone);
                        resultList.setCell(cell);
                        resultLists.add(resultList);
                        rvUserList.setAdapter(lsitViewAdapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void notifyError(String requestType, VolleyError error) {

            }
        };
    }

}