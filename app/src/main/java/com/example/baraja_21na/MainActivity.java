package com.example.baraja_21na;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    TextView txtSuma, txtNumero;
    Integer Suma = 0, numeros, i = 0;


    Button b1, b2, b3, b4, b5, btnR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtSuma = findViewById(R.id.txtSuma);
        txtNumero = findViewById(R.id.txtNum);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);

        btnR = findViewById(R.id.btnReiniciar);
        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reiniciar(btnR);
            }
        });
    }

    public void clickRecurar(final View v) {

        JSONObject nom = new JSONObject();

        JsonObjectRequest json = new JsonObjectRequest(Request.Method.GET, "http://nuevo.rnrsiilge-org.mx/baraja/numero", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            numeros = response.getInt("numero");
                            i++;
                            switch (numeros)
                            {
                                case 1: v.setBackgroundResource(R.mipmap.uno); break;
                                case 2: v.setBackgroundResource(R.mipmap.dos); break;
                                case 3: v.setBackgroundResource(R.mipmap.tres); break;
                                case 4: v.setBackgroundResource(R.mipmap.cuatro); break;
                                case 5: v.setBackgroundResource(R.mipmap.cinco); break;
                                case 6: v.setBackgroundResource(R.mipmap.seis); break;
                                case 7: v.setBackgroundResource(R.mipmap.siete); break;
                                case 8: v.setBackgroundResource(R.mipmap.ocho); break;
                                case 9: v.setBackgroundResource(R.mipmap.nueve); break;
                                case 10: v.setBackgroundResource(R.mipmap.diez); break;
                                case 11: v.setBackgroundResource(R.mipmap.once); break;
                                case 12: v.setBackgroundResource(R.mipmap.doce); break;
                                case 13: v.setBackgroundResource(R.mipmap.trece); break;
                            }

                            txtNumero.setText(numeros.toString());
                            v.setEnabled(false);
                            Suma = Suma + numeros;
                            txtSuma.setText(Suma.toString());

                            if(Suma > 21)
                            {
                                Toast.makeText(MainActivity.this, "Perdiste", Toast.LENGTH_SHORT).show();
                                deshabilitar(v);
                            }
                            else if(Suma == 21)
                            {
                                Toast.makeText(MainActivity.this, "Ganaste", Toast.LENGTH_SHORT).show();
                                deshabilitar(v);
                            }
                            else if(!statusBotones() && i == 5 && Suma < 21)
                            {
                                Toast.makeText(MainActivity.this, "Ganaste", Toast.LENGTH_SHORT).show();
                                deshabilitar(v);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        VolleyS.getInstance(this).getMyRequestQueue().add(json);
    }


    public void clickEnviar(View v) throws JSONException{

        JSONObject num = new JSONObject();
        num.put("nombre", "Cynthia");
        num.put("numero", Suma);

        JsonObjectRequest json = new JsonObjectRequest(Request.Method.POST, "http://nuevo.rnrsiilge-org.mx/baraja/enviar", num, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Log.d("a", response.toString());
                Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        VolleyS.getInstance(this).getMyRequestQueue().add(json);



    }




    public boolean statusBotones()
    {
       boolean r = false;
        ViewGroup relative = (ViewGroup) findViewById(R.id.contbotones);
        int t = relative.getChildCount();

        for (int i = 0; i < t; i++)
        {
            View view = relative.getChildAt(i);
            if(view instanceof Button)
            {
                if (view.isEnabled())
                {
                    r =  true;
                }
            }
        }
        return  r;
    }

    public  void  deshabilitar(View v)
    {
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
        b5.setEnabled(false);
    }

    public  void Reiniciar(View v)
    {
        b1.setEnabled(true);
        b2.setEnabled(true);
        b3.setEnabled(true);
        b4.setEnabled(true);
        b5.setEnabled(true);
        txtSuma.setText("0");
        txtNumero.setText("0");
        Suma = 0;
        numeros = 0;
        i = 0;
        b1.setBackgroundResource(R.mipmap.p);
        b2.setBackgroundResource(R.mipmap.p);
        b3.setBackgroundResource(R.mipmap.p);
        b4.setBackgroundResource(R.mipmap.p);
        b5.setBackgroundResource(R.mipmap.p);
    }



}
