package com.example.joseph.doctdroid;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Diagnostic extends AppCompatActivity {
     Dialog popAdd ;
    Dialog prescriptio ;
     CardView grid_tension,grid_sucre,grid_poid,grid_temperature,grid_taille,grid_rythme;
     EditText editText;
     Button button,enregistrer;
     String flag;
    DbHelper dbHelper;
    TextView textView;
    String prescription="";
    TextView editText1;
    Button button1;


    Double tension,taille,tempera,poid,sucre,rythme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostic);
        grid_tension=(CardView) findViewById(R.id.grid_tension);
        grid_sucre=(CardView) findViewById(R.id.grid_sucre);
        grid_poid=(CardView) findViewById(R.id.grid_poid);
        grid_temperature=(CardView) findViewById(R.id.grid_temperature);
        grid_taille=(CardView) findViewById(R.id.grid_taille);
        grid_rythme=(CardView) findViewById(R.id.grid_rythme);
        enregistrer=(Button) findViewById(R.id.btn_enregister);


        init1();
        initpop();

        grid_tension.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag="tension";
               popAdd.show();
               editText.setHint("Entrez la valeur de la tension");
            }
        });
        grid_sucre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag="sucre";
                popAdd.show();
                editText.setHint("Entrez le taux de glycemie");
            }
        });
        grid_poid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag="poid";
                popAdd.show();
                editText.setHint("Entrez le poid");
            }
        });
        grid_temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag="temperature";
                popAdd.show();
                editText.setHint("Entrez la temperature");
            }
        });
        grid_taille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag="taille";
                popAdd.show();
                editText.setHint("Entrez la taille");
            }
        });
        grid_rythme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag="rythme";
                popAdd.show();
                editText.setHint("Entrez le nombre de bataments par minutes");
            }
        });

        enregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 dbHelper=new DbHelper(Diagnostic.this,"",null,1);
                 dbHelper.insertInfo(tension,tempera,poid,sucre,taille,rythme,prescription);
                 editText1.setText(prescription);
                 prescriptio.show();

            }
        });
    }

    private void initpop() {
        popAdd=new Dialog(this);
        popAdd.setContentView(R.layout.popup_diagnostique);
        popAdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popAdd.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT,Toolbar.LayoutParams.WRAP_CONTENT);
        popAdd.getWindow().getAttributes().gravity = Gravity.CENTER;

        editText=(EditText)popAdd.findViewById(R.id.value);

        button=(Button) popAdd.findViewById(R.id.regBtnValue);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flag=="temperature"){
                   tempera=Double.parseDouble(editText.getText().toString().trim());
                   if(tempera>36.5 || tempera<36.5){
                       showMessage("Votre Temperature n'est pas normale");
                       if(tempera>36.5){
                           prescription+="Votre Temperature est elevée,ceci pourrait etre le resultat d'une suractivité ou une longue marche sous le soleil " +
                                   ";Reposez-vous à un endroit de température ambiante inférieure à 23 degré celcius,si ceci persiste appuyez une éponge " +
                                   "froide sur votre front en gardant une position couchée";
                       }
                       if(tempera>36){
                           prescription+="Votre température est inferieur à la normale,effectuez un exercice physique";
                       }

                   }
                   editText.setText("");
                   popAdd.dismiss();

                }
                if(flag=="tension"){
                    tension=Double.parseDouble(editText.getText().toString().trim());
                    if(tension>12 || tension<13){
                        showMessage("Votre Tension n'est pas normale");
                        if(tension >12){
                            prescription+="Votre Tension n'est elevée,Evitez de manger des aliments salés ou à forte concenctation d'acide";
                        }
                        if(tension < 13){
                            prescription+="Votre Tension est inférieure,Prenez des aliments suffisament salés en évitant tout exces ou des boisson energissante";
                        }
                    }
                    editText.setText("");
                    popAdd.dismiss();
                }
                if(flag=="poid"){
                    poid=Double.parseDouble(editText.getText().toString().trim());
                    if(poid>74){
                        showMessage("Votre Poid est elevé");
                    }
                    editText.setText("");
                    popAdd.dismiss();
                }
                if(flag=="taille"){
                    taille=Double.parseDouble(editText.getText().toString().trim());
                    editText.setText("");
                    popAdd.dismiss();
                }
                if(flag=="rythme"){
                    rythme=Double.parseDouble(editText.getText().toString().trim());
                    if(rythme<60 || rythme>90){
                        if(rythme<60){
                            prescription+="Votre batement cardiawue est inferieur à 60,Allez voir un medecin rapidement";
                        }
                        if(rythme>90){
                            prescription+="Votre batement cardiawue est superieur à 90,et reposez-vous,si la situation persiste allez voir un medecin";
                        }
                        editText.setText("");
                        popAdd.dismiss();
                    }
                    editText.setText("");
                    popAdd.dismiss();
                }
                if(flag=="sucre"){
                    sucre=Double.parseDouble(editText.getText().toString().trim());
                    if(sucre>70 && sucre<130){
                        showMessage("Votre Taux de Glycemie n'est pas normal");
                    }
                    editText.setText("");
                    popAdd.dismiss();
                }
            }
        });
    }
public void init1(){

    prescriptio=new Dialog(this);
    prescriptio.setContentView(R.layout.prescription);
    prescriptio.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    prescriptio.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT,Toolbar.LayoutParams.WRAP_CONTENT);
    prescriptio.getWindow().getAttributes().gravity = Gravity.CENTER;

    editText1=(TextView)prescriptio.findViewById(R.id.presc);
   // button1=(Button) prescriptio.findViewById(R.id.regokey);


}
    // simple method to show toast message
    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

    }
}
