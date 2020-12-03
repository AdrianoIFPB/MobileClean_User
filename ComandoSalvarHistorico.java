public class ComandoSalvarHistorico implements Comando {
    @Override
    public boolean executar(String matricula, String historico, String perfil, int tipoSO) {
        return ComandoReceptor.getInstancia().salvarHistorico(matricula, historico, tipoSO);
    }

    @Override
    public String getSenha() {
        return null;
    }
}
