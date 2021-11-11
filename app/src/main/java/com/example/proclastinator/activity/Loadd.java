package com.example.proclastinator.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.proclastinator.R;
import com.example.proclastinator.adapters.RotinaAdapter;
import com.example.proclastinator.helpers.AtividadeDao;
import com.example.proclastinator.model.Atividade;
import com.example.proclastinator.helpers.RecyclerItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Loadd extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RotinaAdapter rotina;
    private List<Atividade> lista = new ArrayList<>();
    private Atividade atividadeSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadd);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

       Toast toast = Toast.makeText(getApplicationContext(),"clique na tarefa para editar, ou pressione para excluir uma tarefa",Toast.LENGTH_LONG);
        toast.show();

        //configurar recycler
        recyclerView = findViewById(R.id.recyclerView);



        //add evento de clicque
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                Atividade atividadeSelecionada = lista.get(position);
                                Intent intent = new Intent(Loadd.this,NovaAtividade.class);
                                intent.putExtra("AtividadeSelecionada",atividadeSelecionada);
                                startActivity(intent);

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                atividadeSelecionada = lista.get(position);
                                AlertDialog.Builder dialog = new AlertDialog.Builder(Loadd.this);
                                dialog.setTitle("confirmar exclusão");
                                dialog.setMessage("Deseja excluir a tarefa: "+atividadeSelecionada.getNome_ativiade()+"?");
                                dialog.setPositiveButton("sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        AtividadeDao atividadeDao = new AtividadeDao(getApplicationContext());
                                        if(atividadeDao.deletar(atividadeSelecionada)){
                                            carregarTarefas();
                                            Toast.makeText(getApplicationContext(),"Sucesso ao excluir tarefa",Toast.LENGTH_SHORT).show();

                                        }else {

                                            Toast.makeText(getApplicationContext(),"erro ao excluir tarefa",Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                });

                                dialog.setNegativeButton("Não",null );

                                dialog.create();
                                dialog.show();


                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NovaAtividade.class);
                startActivity(intent);
                finish();
            }
        });
    }

            public void carregarTarefas(){

                AtividadeDao atividadeDao = new AtividadeDao(getApplicationContext());
                lista = atividadeDao.listar();


                rotina = new RotinaAdapter(lista);

            //listar tarefas
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
                recyclerView.setAdapter(rotina);
            }

    @Override
    protected void onStart() {
        super.onStart();
        carregarTarefas();
    }
}
