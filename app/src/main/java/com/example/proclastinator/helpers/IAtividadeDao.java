package com.example.proclastinator.helpers;

import com.example.proclastinator.model.Atividade;

import java.util.List;

public interface IAtividadeDao {
    public boolean salvar(Atividade atividade);
    public boolean atualizar(Atividade atividade);
    public boolean deletar(Atividade atividade);
    public List<Atividade> listar();

}
