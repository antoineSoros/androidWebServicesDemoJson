package fr.formation.webservicesdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etCodePays;
    TextView tvNomPays;
    ListView lvPays;
    HttpClient client;
    String urlCode = "http://demo@services.groupkt.com/country/get/iso2code/";
    String urlAllCountries ="http://demo@services.groupkt.com/country/get/all";



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


  public void seeAllCountry(View view)  {// method permettant d'afficher tous les noms de pays contenu dans le web service

      client = new HttpClient();//on crée un nouvel objet HttpClient
      client.setAdr(urlAllCountries);// on assigne une nouvelle valeur à la variable adr (nouvelle url)
        ArrayList<String>nomPays = new ArrayList<>(); // on crée une arraylist pour recuperer les noms de pays contenu dans les jsons
      client.start();
      try {
          client.join();
          JSONObject j = new JSONObject(client.getResponse());// on crée un nouvel objet Json identique a la reponse du serveur
          JSONObject j2 = j.getJSONObject("RestResponse"); //on descend dans l'arbo' du Json
          JSONArray array = j2.getJSONArray("result");//on recupere le tableau contenu dans l'objet result en créeant un JsonArray
          for(int i=0;i<array.length();i++){

            JSONObject pays = array.getJSONObject(i); // une petite boucle pour inserer tout les noms recup dans le tableaux result dans le tableaux nomPays


           nomPays.add(pays.getString("name"));

          }

          ArrayAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, nomPays);
          lvPays.setAdapter(adapter);

      } catch (JSONException e) {
          e.printStackTrace();
      } catch (InterruptedException e) {
          e.printStackTrace();
      }

  }


   //
}
