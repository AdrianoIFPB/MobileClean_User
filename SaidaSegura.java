public class SaidaSegura {
    static SaidaSegura instancia;

    private boolean sairMenuPrincipal = false;
    private boolean sairLogin = false;
    private boolean sairMenuEditar = false;
    private boolean sairSO = false;
    private boolean sairPesqHabito = false;
    private boolean fecharPrograma = false;
    private boolean sairSenha = false;
    private boolean sairValidaLogin = false;

    private SaidaSegura(){
    }

    public static SaidaSegura getInstance(){
        if (instancia == null) return instancia = new SaidaSegura();
        else return instancia;
    }

    public void fecharPrograma(){
        sairMenuPrincipal = true;
        sairLogin = true;
        sairMenuEditar = true;
        sairSO = true;
        sairPesqHabito = true;
        sairSenha = true;
        fecharPrograma = true;
        sairValidaLogin = true;
    }

    public boolean getSairMenuPrincipal() {
        return sairMenuPrincipal;
    }

    public void setSairMenuPrincipal(boolean estado) {
        sairMenuPrincipal = estado;
    }

    public boolean getSairLogin() {
        return sairLogin;
    }

    public void setSairLogin(boolean estado) {
        sairLogin = estado;
    }

    public boolean getSairMenuEditar() {
        return sairMenuEditar;
    }

    public void setSairMenuEditar(boolean estado){
        sairMenuEditar = estado;
    }

    public boolean getSairSO() {
        return sairSO;
    }

    public void setSairSO(boolean estado) {
        sairSO=estado;
    }

    public boolean getSairPesqHabito() {
        return sairPesqHabito;
    }

    public void setSairPesqHabito(boolean estado){
        sairPesqHabito = estado;
    }

    public boolean getFecharPrograma(){
        return fecharPrograma;
    }

    public boolean getSairSenha(){ return sairSenha; }

    public void setSairSenha(boolean estado){ sairSenha = estado;}

    public boolean getSairValidaLogin(){ return sairValidaLogin;}

    public void setSairValidaLogin(boolean estado){ sairValidaLogin = estado; }

}
