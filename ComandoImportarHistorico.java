public class ComandoImportarHistorico implements Comando {
    @Override
    public boolean executar(String matricula, String historico, String perfil, int tipoSO) {
        return ComandoReceptor.getInstancia().importarHistorico(matricula, tipoSO);
    }

    @Override
    public String getSenha() {
        return null;
    }
}
