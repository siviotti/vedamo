package refactown.vedamo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Enumeração de "Tipos de Valor" possíveis para um metadado.
 *
 * @author Douglas Siviotti
 * @see Metadado
 */
public enum TipoValor {

    TEXTO(  (texto)-> texto),
    INTEIRO(Integer::parseInt),
    DECIMAL(Double::parseDouble),
    DATA(10, (texto) -> LocalDate.parse(texto, DateTimeFormatter.ofPattern("dd/MM/yyyy"))), // No formato dd/MM/yyyy
    HORA(8, (texto) -> LocalTime.parse(texto, DateTimeFormatter.ofPattern("HH:mm:ss"))), // No formato HH:mm:ss
    DATA_HORA(18, (texto) -> LocalDateTime.parse(texto, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))), // no formato dd/mm/yyyy HH:mm:ss
    COMPLEXO((texto) -> texto);

    private final int tamanhoFixo;
    private final TipoValorParser<?> parser;

    TipoValor(TipoValorParser<?> parser) {
        this(0, parser);
    }

    TipoValor(int tamanhoFixo, TipoValorParser<?> parser) {
        this.tamanhoFixo = tamanhoFixo;
        this.parser = parser;
    }

    /**
     * Faz o "parsing" do valor em texto para o valor em tipo específico e compatível com o tipo de valor.
     * @param texto O valor (em texto) a ser parseado para o tipo específico
     * @return O objeto de tipo específico do valor contido no texto
     * @param <T> O tipo de Classe referente ao tipoValor
     */
    @SuppressWarnings("unchecked")
    public <T> T parse(String texto) {
        return (T) parser.parse(texto);
    }

    public int getTamanhoFixo() {
        return tamanhoFixo;
    }
}

interface TipoValorParser<T> {

    T parse(String texto);
}


