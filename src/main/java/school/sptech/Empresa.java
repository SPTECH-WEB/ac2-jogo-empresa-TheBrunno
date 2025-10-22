package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.JogoInvalidoException;
import school.sptech.exception.JogoNaoEncontradoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String nome;
    private List<Jogo> jogos;

    public Empresa() {
        jogos = new ArrayList<>();
    }

    public void adicionarJogo(Jogo jogo){
        if(jogo == null ||
                jogo.getCodigo() == null || jogo.getCodigo().isBlank() ||
                jogo.getNome() == null || jogo.getNome().isBlank() ||
                jogo.getGenero() == null || jogo.getGenero().isBlank() ||
                jogo.getPreco() == null || jogo.getPreco() <= 0 ||
                jogo.getAvaliacao() == null || jogo.getAvaliacao() < 0 || jogo.getAvaliacao() > 5 ||
                jogo.getDataLancamento() == null || jogo.getDataLancamento().isAfter(LocalDate.now())
        ){
            throw new JogoInvalidoException();
        }else{
            jogos.add(jogo);
        }
    }

    public Jogo buscarJogoPorCodigo(String codigo){
        if(codigo == null || codigo.isBlank()){
            throw new ArgumentoInvalidoException();
        }

        for(Jogo jogo : jogos){
            if(jogo.getCodigo().equalsIgnoreCase(codigo)){
                return jogo;
            }
        }

        throw new JogoNaoEncontradoException();
    }

    public void removerJogoPorCodigo(String codigo){
        if(codigo == null || codigo.isBlank()){
            throw new ArgumentoInvalidoException();
        }

        for(int i=0; i<jogos.size(); i++){
            if(jogos.get(i).getCodigo().equals(codigo)){
                jogos.remove(i);
                return;
            }
        }

        throw new JogoNaoEncontradoException();
    }

    public Jogo buscarJogoComMelhorAvaliacao(){
        if(jogos.isEmpty()){
            throw new JogoNaoEncontradoException();
        }

        Jogo jogoMaiorAvaliacao = jogos.get(0);

        for(Jogo jogo : jogos){
            if(jogo.getAvaliacao() == jogoMaiorAvaliacao.getAvaliacao()){
                if(jogo.getDataLancamento().isAfter(jogoMaiorAvaliacao.getDataLancamento())){
                    jogoMaiorAvaliacao = jogo;
                }
            }

            if(jogo.getAvaliacao() >= jogoMaiorAvaliacao.getAvaliacao()){
                jogoMaiorAvaliacao = jogo;
            }
        }

        return jogoMaiorAvaliacao;
    }

    public List<Jogo> buscarJogoPorPeriodo(LocalDate dataInicio, LocalDate dataFim){
        if(dataInicio == null || dataFim == null || dataInicio.isAfter(dataFim)){
            throw new ArgumentoInvalidoException();
        }else{
            List<Jogo> jogosPeriodo = new ArrayList<>();

            for(Jogo jogo : jogos){
                if(jogo.getDataLancamento().isAfter(dataInicio) && jogo.getDataLancamento().isBefore(dataFim)){
                    jogosPeriodo.add(jogo);
                }
            }

            return jogosPeriodo;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }
}
