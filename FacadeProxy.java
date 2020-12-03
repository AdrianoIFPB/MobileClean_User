import javax.swing.*;

public class FacadeProxy extends Facade {

    private int cont = 0;
    private boolean retorno = false;

    private boolean temPermissaoDeAcesso(){
        saidaSegura.setSairLogin(false);
        while (!saidaSegura.getSairLogin()) {
            int novo = JOptionPane.showConfirmDialog(null, "Você é um novo usuário?",
                    "NOVO USUÁRIO?", 0, 3);
            switch (novo) {
                case 0: {
                    String matriculaDigitada;
                    String nome;
                    String cargo;
                    String perfilLimpeza;
                    String senha;
                    adapterInvocador.tipoSO();
                    if (saidaSegura.getFecharPrograma()) return false;
                    perfilLimpeza = perfil.pesquisaDeHabitos();
                    if (saidaSegura.getFecharPrograma()) return false;
                    matriculaDigitada = JOptionPane.showInputDialog(null, "Informe sua matrícula:\n" +
                            "(4 dígitos e somente números)", " --MATRÍCULA--   ", 3);
                    if (gerenciadorLogon.matriculaValida(matriculaDigitada)) {
                        nome = JOptionPane.showInputDialog(null, "Informe seu Nome:",
                                " --NOME DO USUÁRIO--   ", 3);
                        cargo = JOptionPane.showInputDialog(null, "Informe seu cargo:",
                                " --CARGO--   ", 3);
                        senha = gerenciadorLogon.registraSenha();
                        if (senha == null) return false;
                        Usuario.getInstancia(nome, matriculaDigitada, cargo, perfilLimpeza, true, null, senha, "nao");
                        saidaSegura.setSairLogin(true);
                        retorno = true;

                    }
                    break;
                }
                case 1: {
                    adapterInvocador.tipoSO();
                    if (saidaSegura.getFecharPrograma()) return false;
                    String matricula = gerenciadorLogon.loginValido();
                    if (saidaSegura.getSairLogin()) return false;
                    if (matricula != null) {
                        String senhaDigitada = gerenciadorLogon.registraSenha();
                        if (senhaDigitada == null) return false;
                        if (adapterInvocador.importarSenha(matricula)){
                            String senhaSalva = adapterInvocador.getSenha();
                            if (!gerenciadorLogon.confereSenha( senhaSalva, senhaDigitada)){
                                return false;
                            }
                        }
                        else return false;
                        if (adapterInvocador.importarPerfil(matricula)) {
                            adapterInvocador.importarHistorico(matricula);
                            saidaSegura.setSairLogin(true);
                            retorno = true;
                            break;
                        }
                    }
                    break;
                }
                default: {
                    return false;
                }
            }
            if (cont==1 && !retorno) JOptionPane.showMessageDialog(null, "Você tem apenas mais 1(uma) tentativa!",
                    "ATENÇÃO", 2);
            cont++;
            if (cont == 3 && !retorno) return false;
        }
        return retorno;
    }

    @Override
    public void iniciar(){
        if (temPermissaoDeAcesso()){
            super.iniciar();
        }
    }
}
