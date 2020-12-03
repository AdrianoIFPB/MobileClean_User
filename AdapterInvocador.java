public class AdapterInvocador {

    private static AdapterInvocador instancia;
    private String matricula = null;
    private String historico = "Sem Registro!";
    private String perfil = null;
    private int SO;
    private SistemaOperacional sistemaOperacional = new SistemaOperacional();

    private ComandoInvocador comandoInvocador;


    private AdapterInvocador(){
        comandoInvocador = new ComandoInvocador( new ComandoImportarHistorico(), new ComandoImportarPerfil(),
                new ComandoImportarSenha(), new ComandoSalvarHistorico(), new ComandoSalvarPerfil());
    };

    public static AdapterInvocador getInstance(){
        if (instancia == null){
            return instancia = new AdapterInvocador();
        }
        else return instancia;
    }

    public boolean importarPerfil(String matric){
        this.matricula = matric;
        return comandoInvocador.importarPerfil(matricula, historico, perfil, SO);
    }

    public void importarHistorico(String matric){
        this.matricula = matric;
        comandoInvocador.importarHistorico(matricula, historico, perfil, SO);
    }

    public boolean importarSenha(String matric){
        this.matricula = matric;
        return comandoInvocador.importarSenha(matricula, historico, perfil, SO);
    }

    public String getSenha(){
        return comandoInvocador.getSenha();
    }

    public void salvarPerfil(String matric, String perf){
        this.matricula = matric;
        this.perfil = perf;
        comandoInvocador.salvarPerfil(matricula, historico, perfil, SO);
    }

    public void salvarHistorico(String matric, String hist){
        this.matricula = matric;
        this.historico = hist;
        comandoInvocador.salvarHistorico(matricula, historico, perfil, SO);
    }

    public void tipoSO(){
        SO = sistemaOperacional.getSistemaOpe();
    }

}