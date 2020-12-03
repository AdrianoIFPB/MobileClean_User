public class ComandoImportarSenha implements Comando{
    @Override
    public boolean executar(String matricula, String historico, String perfil, int tipoSO) {
        return ComandoReceptor.getInstancia().importarSenha(matricula, tipoSO);
    }

    @Override
    public String getSenha() {
        return ComandoReceptor.getInstancia().getSenha();
    }

}
