package refactown.vedamo;

import java.util.Map;

/**
 * Converte um conjunto de valores estruturados no formato "chave/valor" (exemplo JSON, XML etc) para um Mapa organizado com estas chaves e valores.
 *
 * @author Douglas Siviotti
 */
public interface ChaveValorParser {

    Map<String,String> parse(String json);
}
