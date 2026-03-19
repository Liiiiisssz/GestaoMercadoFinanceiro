package br.com.centroweg.financas.domain.exceptions;

public class RegraDeNegocioException extends RuntimeException{
    public RegraDeNegocioException(String mensagem){
        super(mensagem);
    }
}
