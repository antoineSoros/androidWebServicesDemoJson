package fr.formation.webservicesdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText etCodePays;
    TextView tvNomPays;
    ListView lvPays;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvPays = findViewById(R.id.lvPays);
        tvNomPays = findViewById(R.id.tvNomPays);
        etCodePays = findViewById(R.id.etCodePays);
    }

    public void searchOne(View view) {//method effectuant une recherche en fonction du code entré par l'user
     String codePays = etCodePays.getText().toString();



    }
}
