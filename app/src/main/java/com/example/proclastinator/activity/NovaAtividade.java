package com.example.proclastinator.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.proclastinator.R;
import com.example.proclastinator.helpers.AtividadeDao;
import com.example.proclastinator.model.Atividade;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class NovaAtividade extends AppCompatActivity {
    private Button voltar;
    private Button btn;
    private EditText inicio;
    private EditText finalzin;
    private EditText atividade;
    private Atividade recuperarAtividade;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;
    Calendar c;
    DatePickerDialog dpd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_atividade);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        voltar= findViewById(R.id.voltar);
        btn = findViewById(R.id.salvar);
        inicio = findViewById(R.id.inicio);
        finalzin = findViewById(R.id.finalzin);
        atividade = findViewById(R.id.atividade);

        //criando mascara
        SimpleMaskFormatter smf1 = new SimpleMaskFormatter("NN:NN");
        MaskTextWatcher atw1= new MaskTextWatcher(inicio,smf1);
        inicio.addTextChangedListener(atw1);
        SimpleMaskFormatter smf2 = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher atw2 = new MaskTextWatcher(finalzin,smf2);
        finalzin.addTextChangedListener(atw2);
        //fim da mascara


        //dialog picker
            finalzin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    c= Calendar.getInstance();
                    int day = c.get(Calendar.DAY_OF_MONTH);
                    int month = c.get(Calendar.DAY_OF_MONTH);
                    int year = c.get(Calendar.YEAR);

                    dpd = new DatePickerDialog(NovaAtividade.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int myear, int mmonth, int mday) {
                            finalzin.setText(String.format("%02d/%02d/%04d", mday, mmonth,myear));

                        }
                    },day,month,year);
                    dpd.show();



                }
            });


        //fim dialog picker



        //timer picker

            inicio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    calendar = Calendar.getInstance();
                    currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                    currentMinute = calendar.get(Calendar.MINUTE);


                    timePickerDialog = new TimePickerDialog(NovaAtividade.this, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {

                            inicio.setText(String.format("%02d:%02d", hourOfDay, minutes));
                        }
                    }, currentHour, currentMinute, true);

                    timePickerDialog.show();
                }
            });





        //fimtimer picker



        //recuperar tarefa
        recuperarAtividade = (Atividade) getIntent().getSerializableExtra("AtividadeSelecionada");

        //configurar tarefa na caixa de texto

        if(recuperarAtividade !=null){
            atividade.setText(recuperarAtividade.getNome_ativiade());
            inicio.setText(recuperarAtividade.getComeço());
            finalzin.setText(recuperarAtividade.getTermino());
        }



        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
;
                finish();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AtividadeDao atividadeDao = new AtividadeDao(getApplicationContext());

                if(recuperarAtividade!=null){

                    String nome = atividade.getText().toString();
                    String ini = inicio.getText().toString();
                    String fin = finalzin.getText().toString();
                    if (!nome.isEmpty()&& !ini.isEmpty()&& !fin.isEmpty()){

                        Atividade atividades = new Atividade();
                        atividades.setNome_ativiade(nome);
                        atividades.setComeço(ini);
                        atividades.setTermino(fin);
                        atividades.setId(recuperarAtividade.getId());

                        if(atividadeDao.atualizar(atividades)){
                            Intent intent = new Intent(getApplicationContext(), Loadd.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(getApplicationContext(),"Sucesso ao atualizar tarefa",Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(getApplicationContext(),"erro ao atualizar tarefa",Toast.LENGTH_SHORT).show();

                        }




                    }

                }

                else {

                    String nome = atividade.getText().toString();
                    String ini = inicio.getText().toString();
                    String fin = finalzin.getText().toString();

                       if (!nome.isEmpty()&& !ini.isEmpty()&& !fin.isEmpty()) {
                           Atividade atividades = new Atividade();
                           atividades.setNome_ativiade(nome);
                           atividades.setComeço(ini);
                           atividades.setTermino(fin);

                           if( atividadeDao.salvar(atividades)){

                               Intent intent = new Intent(getApplicationContext(), Loadd.class);
                               startActivity(intent);
                               finish();
                               Toast.makeText(getApplicationContext(),"Sucesso ao salvar tarefa",Toast.LENGTH_SHORT).show();
                           }else{
                               Toast.makeText(getApplicationContext(),"erro ao salvar tarefa",Toast.LENGTH_SHORT).show();

                           }



                       }else{
                           Toast.makeText(getApplicationContext(),"Verifique se todos os dados estão preenchidos",Toast.LENGTH_SHORT).show();

                       }

                }


            }
        });










    }
}
