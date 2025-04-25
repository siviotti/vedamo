package refactown.vedamo;

import java.util.Objects;

/**
 * Representa um erro durante a validação sobre um valor quando comparado a um metadado.
 *
 * @author Douglas Siviotti
 * @see Resultado
 */
public record Erro(Metadado metadado, String mensagem) {
    public Erro {
        Objects.requireNonNull(metadado, "Metadado não pode ser nulo.");
        Objects.requireNonNull(mensagem, "Mensagem não pode ser nula.");
        if (mensagem.trim().isEmpty()) throw new IllegalArgumentException("Mensagem não pode ser vazia.");
    }
}
