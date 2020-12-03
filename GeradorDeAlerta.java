public abstract class GeradorDeAlerta {

    protected GerenciadorDeDatas gerenciadorDeDatas = GerenciadorDeDatas.getInstancia();

    protected abstract int limpezaPendente();

    protected abstract int valorPerfil();

}
