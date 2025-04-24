package refactown.vedamo;

import java.util.List;
import java.util.Objects;

/**
 * Classe que representa um Metadado que descreve caracteristicas de um campo individual (dado) em uma estrutura.
 *
 * @author Douglas Siviotti
 * @see TipoValor
 */
public class Metadado {

    private final String id;
    private final String nome;
    private final TipoValor tipoValor;
    private final String descricao;
    private final boolean obrigatorio;
    private final Integer tamanho;
    private final List<Metadado> filhos;

    public Metadado(String id, String nome, TipoValor tipoValor, Integer tamanho, String descricao) {
        this(id, nome, tipoValor, tamanho, descricao, false, null);
    }

    /**
     * Construtor da classe Metadado.
     *
     * @param id          O identificador único de um metadado dentro de uma estrutura com vários metadados.
     * @param nome        O nome do metadado no esquema "chave/valor" ou "name/value" que pode ser repedito dentro de uma estrutura.
     * @param tipoValor   O tipo de valor do metadado que definirá regras sobre os valores possíveis.
     * @param tamanho     O tamanho (em quantidade de caracteres ou dígitos) do metadado
     * @param descricao   Texto informativo sobre do que se trata este metadado.
     * @param obrigatorio Indica se o dado é obrigatório ou não dentro de uma estrutura.
     * @param filhos Os metadados "filhos" para quando um Metadado é uma estrutura complexa
     */
    public Metadado(String id, String nome, TipoValor tipoValor, Integer tamanho, String descricao, boolean obrigatorio,
                    List<Metadado> filhos) {
        this.id = Objects.requireNonNull(id, "O id não pode ser nulo");
        this.nome = Objects.requireNonNull(nome, "O nome não pode ser nulo");
        this.tipoValor = Objects.requireNonNull(tipoValor, "O tipoValor não pode ser nulo");
        this.descricao = Objects.requireNonNull(descricao, "A descricao não pode ser nula");
        this.tamanho = Objects.requireNonNull(tamanho, "O tamanho não pode ser nulo");
        this.obrigatorio = obrigatorio;
        this.filhos = criaFilhos(filhos, tipoValor);
    }

    List<Metadado> criaFilhos(List<Metadado> filhos, TipoValor tipoValor){
        if (TipoValor.ESTRUTURA.equals(tipoValor)) {
            Objects.requireNonNull(filhos, "Uma estrutura deve ter metadados 'filhos'");
            if (filhos.isEmpty()) {
                return List.copyOf(filhos);
            }
            throw new IllegalArgumentException("Uma estrutura deve ter pelo menos um metadado 'filho'");
        }
        return List.of();
    }

    public Metadado(String id, String nome, TipoValor tipoValor, Integer tamanho, String descricao, boolean obrigatorio) {
        this(id, nome, tipoValor, tamanho, descricao, obrigatorio, null);
    }
    public List<Metadado> getFilhos() {
        return filhos;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public TipoValor getTipoValor() {
        return tipoValor;
    }

    public Integer getTamanho() {
        return tamanho;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isObrigatorio() {
        return obrigatorio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metadado metadado = (Metadado) o;
        return obrigatorio == metadado.obrigatorio && Objects.equals(id, metadado.id) &&
                Objects.equals(nome, metadado.nome) && tipoValor == metadado.tipoValor && Objects.equals(filhos, metadado.filhos) &&
                Objects.equals(descricao, metadado.descricao) && Objects.equals(tamanho, metadado.tamanho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, tipoValor, descricao, obrigatorio, tamanho);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id='").append(id).append('\'');
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", tipoValor=").append(tipoValor);
        sb.append(", descricao='").append(descricao).append('\'');
        sb.append(", obrigatorio=").append(obrigatorio);
        sb.append(", tamanho=").append(tamanho);
        if (!filhos.isEmpty()){
            sb.append(", filhos=").append(filhos);
        }
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Informa se o Metadado é ou não uma "Estrutura", ou seja, um metadado de um tipo complexo formado por outros metadados.
     * @return 'true' se o Metadado é uma estrutura (vários metadados) ou
     * 'false' se é um metadado atômico (simples ou plano).
     */
    public boolean isEstrutura(){
        return ! filhos.isEmpty();
    }

}
