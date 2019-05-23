package com.akhil.exercise.Utilites;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface Datastore {
    public void notifySuccess(String requestType, JSONObject response);
    public void notifyError(String requestType, VolleyError error);
}
