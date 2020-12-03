public interface Comando {
    boolean executar(String matricula, String historico, String perfil, int tipoSO);
    String getSenha();

}
