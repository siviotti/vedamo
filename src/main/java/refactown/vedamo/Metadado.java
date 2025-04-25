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
     * @param nome        O nome do metadado no esquema "chave/valor" ou "name/value" que pode ser repetido dentro de uma estrutura.
     * @param tipoValor   O tipo de valor do metadado que definirá regras sobre os valores possíveis.
     * @param tamanho     O tamanho (em quantidade de caracteres ou dígitos) do metadado
     * @param descricao   Texto informativo sobre do que se trata este metadado.
     * @param obrigatorio Indica se o dado é obrigatório ou não dentro de uma estrutura.
     * @param filhos Os metadados "filhos" para quando um Metadado é uma estrutura complexa
     */
    public Metadado(String id, String nome, TipoValor tipoValor, Integer tamanho, String descricao, boolean obrigatorio,
                    List<Metadado> filhos) {
        this.nome = Objects.requireNonNull(nome, "O nome não pode ser nulo");
        this.tipoValor = Objects.requireNonNull(tipoValor, "O tipoValor não pode ser nulo");
        this.descricao = Objects.requireNonNull(descricao, "A descricao não pode ser nula");
        this.tamanho = Objects.requireNonNull(tamanho, "O tamanho não pode ser nulo");
        this.obrigatorio = obrigatorio;
        this.filhos = criaFilhos(filhos, tipoValor);
    }

    List<Metadado> criaFilhos(List<Metadado> filhos, TipoValor tipoValor){
        if (TipoValor.COMPLEXO.equals(tipoValor)) {
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
        return obrigatorio == metadado.obrigatorio && Objects.equals(nome, metadado.nome) &&
                tipoValor == metadado.tipoValor && Objects.equals(filhos, metadado.filhos) &&
                Objects.equals(descricao, metadado.descricao) && Objects.equals(tamanho, metadado.tamanho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, tipoValor, descricao, obrigatorio, tamanho);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("nome='").append(nome).append('\'');
        sb.append(", tipoValor=").append(tipoValor);
        sb.append(", descricao='").append(descricao).append('\'');
        sb.append(", obrigatorio=").append(obrigatorio);
        sb.append(", tamanho=").append(tamanho);
        if (!filhos.isEmpty()){
            sb.append("\n");
            sb.append(", filhos=[").append(filhos).append("]");
        }
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

    public String toJson(int level){
        StringBuilder sb = new StringBuilder();
        String ident = "  ".repeat(level);
        sb.append("{\n");
        sb.append(ident).append("  \"nome\": \"").append(nome).append("\",\n");
        sb.append(ident).append("  \"tipoValor\": \"").append(tipoValor).append("\",\n");
        sb.append(ident).append("  \"descricao\": \"").append(descricao).append("\",\n");
        sb.append(ident).append("  \"obrigatorio\": ").append(obrigatorio).append(",\n");
        sb.append(ident).append("  \"tamanho\": ").append(tamanho);
        if (!filhos.isEmpty()){
            sb.append(",\n").append(ident).append("  \"filhos\": [\n");
            for (int i = 0; i < filhos.size(); i++) {
                sb.append(ident).append("    ").append(filhos.get(i).toJson(level + 2));
                if (i < filhos.size() - 1) {
                    sb.append(",");
                }
                sb.append("\n");
            }
            sb.append(ident).append("  ]");
        }
        sb.append("\n").append(ident).append("}");
        return sb.toString();

    }

}
