public class ComandoInvocador {
    private Comando[] comandos;

    public ComandoInvocador(Comando importarHistoricoComando, Comando importarPerfilComando, Comando importarSenhaComando,
                            Comando salvarHistoricoComando, Comando salvarPerfilComando){
        this.comandos = new Comando[5];
        comandos[0] = importarHistoricoComando;
        comandos[1] = importarPerfilComando;
        comandos[2] = importarSenhaComando;
        comandos[3] = salvarHistoricoComando;
        comandos[4] = salvarPerfilComando;
    }

    public boolean importarHistorico(String matricula, String historico, String perfil, int tipoSO){
        return comandos[0].executar(matricula, historico, perfil, tipoSO);
    }

    public boolean importarPerfil(String matricula, String historico, String perfil, int tipoSO){
        return comandos[1].executar(matricula, historico, perfil, tipoSO);
    }

    public boolean importarSenha(String matricula, String historico, String perfil, int tipoSO){
        return comandos[2].executar(matricula, historico, perfil, tipoSO);
    }

    public String getSenha(){
        return comandos[2].getSenha();
    }

    public boolean salvarHistorico(String matricula, String historico, String perfil, int tipoSO){
        return comandos[3].executar(matricula, historico, perfil, tipoSO);
    }

    public boolean salvarPerfil(String matricula, String historico, String perfil, int tipoSO){
        return comandos[4].executar(matricula, historico, perfil, tipoSO);
    }


}
