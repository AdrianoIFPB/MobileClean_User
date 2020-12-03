import javax.swing.*;

class Usuario extends GeradorDeAlerta{
    static Usuario instancia;
    private InstrucaoLimpeza instrucaoLimpeza;

    private String nome;
    private String matricula;
    private String cargo;
    private String perfilLimpeza;
    private String historicoLimpeza = "";
    private String ultimaLimpeza = "Sem Registro!";
    private String senha;
    private String alarme = "nao";
    private boolean houveAlteracao = false;

    private Usuario(String nome, String matricula, String cargo, String perfil, boolean novoUsuario, String senha, String alarme){
        this.nome = nome.toUpperCase();
        this.matricula = matricula;
        this.cargo = cargo.toUpperCase();
        this.perfilLimpeza = perfil;
        this.senha = senha;
        this.alarme = alarme;
        instrucaoLimpeza = new InstrucaoLimpeza();


        JOptionPane.showMessageDialog(null,"Usuário "+getNome().toUpperCase()+", cadastrado com sucesso!",
                "PHONE CLEAN", 1);
    }

    private Usuario(String nome, String matricula, String cargo, String perfil, String ultimaLimpeza, String senha, String alarme){
        this.nome = nome.toUpperCase();
        this.matricula = matricula;
        this.cargo = cargo.toUpperCase();
        this.perfilLimpeza = perfil;
        instrucaoLimpeza = new InstrucaoLimpeza();
        this.senha = senha;
        this.ultimaLimpeza = ultimaLimpeza;
        this.alarme = alarme;

        JOptionPane.showMessageDialog(null,"Olá "+getNome().toUpperCase()+", seja bem vindo(a)!"+
                        "\nSeu perfil de higienização é de "+this.perfilLimpeza+"\nÚltima "+
                        this.ultimaLimpeza,
                "PHONE CLEAN", 1);

    }

    static Usuario getInstancia(String nome, String matricula, String cargo, String perfil, boolean novoUsuario, String ultimaLimpeza, String senha, String alarme){
        if (instancia == null){
            if (novoUsuario){
                return instancia = new Usuario(nome, matricula, cargo, perfil, true, senha, alarme);
            }
            else {

                return instancia = new Usuario(nome, matricula, cargo, perfil, ultimaLimpeza, senha, alarme);
            }


        }
        else{
            instancia.setUltimaLimpeza(ultimaLimpeza);
            return instancia;
        }
    }

    public void getInstrucoesLimpeza(){
        instrucaoLimpeza.getInstrucoes();
    }

    String getNome() {
        return nome;
    }

    void setNome(String nome) {
        this.nome = nome;
    }

    String getMatricula() {
        return matricula;
    }

    void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    void setCargo(String cargo) {
        this.cargo = cargo;
    }

    void setPerfilLimpeza(String perfilLimpeza) {
        this.perfilLimpeza = perfilLimpeza;
    }

    String getPerfilLimpeza(){
        return perfilLimpeza;
    }

    public void setAlarme(String al){
        alarme=al;
    }

    public String getAlarme(){
        return alarme;
    }

    String getHistoricoLimpeza() {
        return historicoLimpeza;
    }

    void setHistoricoLimpeza(String limpezaConfirmada) {
        if (!ultimaLimpeza.equals(limpezaConfirmada)){
            this.historicoLimpeza += limpezaConfirmada;
            this.ultimaLimpeza = limpezaConfirmada;
        }

    }

    String getUltimaLimpeza(){
        return ultimaLimpeza;
    }

    void setUltimaLimpeza(String ultimaLimpeza){this.ultimaLimpeza = ultimaLimpeza;}

    public void setHouveAlteracao(boolean alteracao){
        houveAlteracao = alteracao;
    }

    public Boolean getHouveAlteracao(){
        return houveAlteracao;
    }

    String salvarPerfil(){
        return nome.toUpperCase()+";"+matricula+";"+cargo+";"+perfilLimpeza+";"+ultimaLimpeza+";"+senha+";"+alarme;
    }

    String salvarHistorico(){
        return getHistoricoLimpeza();
    }

    void importarHistorico(String historico){
        historicoLimpeza = historico;
    }


    @Override
    public String toString() {
        return "Nome: "+nome.toUpperCase()+"\n"+"Matrícula: "+matricula+"\n"+"Cargo: "+cargo+"\n"+
                "Perfil: "+perfilLimpeza+"\n"+"Status Atual: "+ultimaLimpeza;
    }

    @Override
    protected int limpezaPendente() {
        String lastClean = ultimaLimpeza.substring(21, 32);
        int valorPerfil = valorPerfil();
        if (valorPerfil == -1){
            return -1;
        }
        return gerenciadorDeDatas.limpezaPendente(valorPerfil , lastClean);
    }

    @Override
    protected int valorPerfil() {
        if (getPerfilLimpeza().equals("Limpeza Diária")) return (3600000*24);
        if (getPerfilLimpeza().equals("Limpeza a cada 3 dias")) return (3600000*24*3);
        if (getPerfilLimpeza().equals("Limpeza Semanal")) return (3600000*24*7);
        return -1;
    }

    public void alerta(){
        int pendencia = limpezaPendente();
        if (pendencia == 0){
            String estado = "Dispositivo de "+getNome()+" está higienizado!\nPerfil de "+getPerfilLimpeza()+".\n"+
                    getUltimaLimpeza();
            setAlarme("nao");
            JOptionPane.showMessageDialog(null, estado,
                    "SITUAÇÃO DE HIGIENIZAÇÃO", 1);

        } else if (pendencia > 0){
            String estado = "Usuário "+getNome()+" está com a limpeza atrasada em: "+pendencia+" dias!\n"+
                    "Perfil de "+getPerfilLimpeza()+"\n"+getUltimaLimpeza();
            JOptionPane.showMessageDialog(null, estado,
                    "SITUAÇÃO DE HIGIENIZAÇÃO", 2);

        }
    }
}
