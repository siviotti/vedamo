package refactown.vedamo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MetadadoTest {

    @Test
    public void testCreate(){
        System.out.println("Teste");
        Metadado metadado = new Metadado("nome", "nome", TipoValor.TEXTO, 10, "Nome", true);
        System.out.println(metadado.toJson(0));
        Erro erro = new Erro(metadado, "teste");
        System.out.println(erro);

    }


}