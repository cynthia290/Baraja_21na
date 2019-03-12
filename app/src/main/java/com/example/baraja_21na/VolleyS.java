package com.example.baraja_21na;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyS {

    private static VolleyS myVolleyS = null;

    private RequestQueue myRequestQueue;

    private  VolleyS(Context context) {myRequestQueue = Volley.newRequestQueue(context);}

    public static VolleyS getInstance(Context context){
        if(myVolleyS == null)
        {
            myVolleyS = new VolleyS(context);
        }
        return myVolleyS;
    }

    public RequestQueue getMyRequestQueue() { return myRequestQueue; }
}
