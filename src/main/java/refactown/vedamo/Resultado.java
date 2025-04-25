package refactown.vedamo;

import java.util.List;

/**
 * Representa o resultado de uma validação de um conjunto de valores tipo Mapa (chave/valor) a partir de uma estrutura
 * de metadados que define tipos, tamanhos e obrigatoriedade.
 *
 * @author Douglas Siviotti
 * @see Vedamo
 */
public class Resultado {

    private final List<Erro> erros;

    /**
     * Construtor da classe Resultado.
     *
     * @param erros Lista de erros encontrados durante a validação.
     */
    public Resultado(List<Erro> erros) {
        this.erros = List.copyOf(erros);
    }

    /**
     * Verifica se existem erros no resultado da validação.
     *
     * @return true se houver erros, false caso contrário.
     */
    public boolean temErros(){
        return ! erros.isEmpty();
    }

    public List<Erro> getErros() {
        return List.copyOf(erros);
    }
}
