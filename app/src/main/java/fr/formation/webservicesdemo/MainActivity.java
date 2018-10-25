package fr.formation.webservicesdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText etCodePays;
    TextView tvNomPays;
    ListView lvPays;
    HttpClient client;
    String urlCode = "http://demo@services.groupkt.com/country/get/iso2code/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvPays = findViewById(R.id.lvPays);
        tvNomPays = findViewById(R.id.tvNomPays);
        etCodePays = findViewById(R.id.etCodePays);

    }

    public void searchOne(View view) throws JSONException {//method effectuant une recherche en fonction du code entré par l'user
    StringBuilder str = new StringBuilder();


     String codePays = etCodePays.getText().toString();
        String urlCodePays = str.append(urlCode).append(codePays).toString();
    client = new HttpClient();
    client.setAdr(urlCodePays);

    client.start();
        try {

            client.join();

           JSONObject j = new JSONObject( client.getResponse());
           JSONObject j2 = j.getJSONObject("RestResponse");
           JSONObject j3 = j2.getJSONObject("result");
           String nomPays= j3.getString("name");

            tvNomPays.setText( nomPays );

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }





}
