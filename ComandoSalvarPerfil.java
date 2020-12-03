public class ComandoSalvarPerfil implements Comando {
    @Override
    public boolean executar(String matricula, String historico, String perfil, int tipoSO) {
        return ComandoReceptor.getInstancia().salvarPerfil(matricula, perfil, tipoSO);
    }

    @Override
    public String getSenha() {
        return null;
    }
}
