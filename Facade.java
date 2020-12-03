import javax.swing.*;
import java.util.InputMismatchException;

public class Facade {
    protected GerenciadorDeDatas gerenciadorDeDatas;
    protected GerenciadorLogon gerenciadorLogon;
    protected Perfil perfil;
    protected AdapterInvocador adapterInvocador;
    protected SaidaSegura saidaSegura;

    Facade (){
        gerenciadorDeDatas = GerenciadorDeDatas.getInstancia();
        gerenciadorLogon = new GerenciadorLogon();
        perfil = new Perfil();
        adapterInvocador = AdapterInvocador.getInstance();
        saidaSegura = SaidaSegura.getInstance();
    }

    protected void iniciar() {

        if (Usuario.instancia.getAlarme().equals("sim")){
            Usuario.instancia.alerta();
        }
        saidaSegura.setSairMenuPrincipal(false);
        while (!saidaSegura.getSairMenuPrincipal()) {
            String menuPrincipal = JOptionPane.showInputDialog(null, "\n1- Editar Dados\n" +
                    "2- Visualizar Perfil e Histórico\n" + "3- Registrar Limpeza\n" +
                    "4- Situação Atual de Limpeza\n5- Instruções de Higienização\n6- Atualizar\n7- Sair\n\n" +
                    "DIGITE O Nº DA OPERAÇÃO DESEJADA:", "      MOBILE CLEAN - "+Usuario.instancia.getNome(), -1);

            try {
                switch (Integer.parseInt(menuPrincipal)) {

                    case 1: {//EDIITAR
                        if (Usuario.instancia == null) {
                            JOptionPane.showMessageDialog(null, "Sem Usuário Cadastrado.",
                                    "ATENÇÃO", 2);
                            break;
                        }

                        saidaSegura.setSairMenuEditar(false);
                        while (!saidaSegura.getSairMenuEditar()) {
                            String novaMatricula;
                            String novoNome;
                            String novoCargo;
                            String novoPerfilLimpeza;
                            String menuEditar = JOptionPane.showInputDialog(null, "\n1- Editar Nome\n" +
                                            "2- Editar Cargo\n3- Editar Matrícula\n4- Editar Pesquisa de Hábito\n" +
                                            "5- Voltar ao Menu Anterior\n\nDIGITE O Nº DA OPERAÇÃO DESEJADA:",
                                    "      MOBILE CLEAN - Usuário     ", -1);
                            try {
                                switch (Integer.parseInt(menuEditar)) {
                                    case 1: {//NOME
                                        novoNome = JOptionPane.showInputDialog(null, "Informe seu Nome:",
                                                " --NOME DO USUÁRIO--   ", 3);
                                        if (novoNome!= null){
                                            Usuario.instancia.setNome(novoNome);
                                            Usuario.instancia.setHouveAlteracao(true);
                                        }

                                        break;
                                    }

                                    case 2: {//CARGO
                                        novoCargo = JOptionPane.showInputDialog(null, "Informe seu Cargo:",
                                                " --CARGO--   ", 3);
                                        if (novoCargo!=null){
                                            Usuario.instancia.setCargo(novoCargo);
                                            Usuario.instancia.setHouveAlteracao(true);
                                        }

                                        break;
                                    }

                                    case 3: {//MATRÍCULA
                                        novaMatricula = JOptionPane.showInputDialog(null, "Informe sua matrícula:\n" +
                                                "(4 dígitos e somente números)", " --MATRÍCULA--   ", 3);
                                        if (gerenciadorLogon.matriculaValida(novaMatricula)){
                                            Usuario.instancia.setMatricula(novaMatricula);
                                            Usuario.instancia.setHouveAlteracao(true);
                                        }
                                        break;
                                    }

                                    case 4: {//HÁBITOS
                                        novoPerfilLimpeza = perfil.pesquisaDeHabitos();
                                        if (novoPerfilLimpeza != null) {
                                            Usuario.instancia.setPerfilLimpeza(novoPerfilLimpeza);
                                            Usuario.instancia.setHouveAlteracao(true);
                                        }
                                        break;
                                    }

                                    case 5: {//SAIR
                                        saidaSegura.setSairMenuEditar(true);
                                        adapterInvocador.salvarPerfil(Usuario.instancia.getMatricula(),
                                                Usuario.instancia.salvarPerfil());
                                        break;
                                    }

                                    default: {
                                        JOptionPane.showMessageDialog(null, "Digite um número entre 1 e 5",
                                                "Número inválido!", 0);
                                        break;
                                    }
                                }
                            } catch (InputMismatchException | NumberFormatException | NullPointerException e) {
                                JOptionPane.showMessageDialog(null, "Digite uma entrada válida.",
                                        "Se liga!", 0);
                            }
                        }
                        break;
                    }

                    case 2: {//VISUALIZAR DADOS
                        if (Usuario.instancia == null) {
                            JOptionPane.showMessageDialog(null, "Sem Usuário Cadastrado.",
                                    "ATENÇÃO", 2);
                            break;
                        }
                        JOptionPane.showMessageDialog(null, Usuario.instancia.toString(),
                                "DADOS DO USUÁRIO", 1);
                        JOptionPane.showMessageDialog(null, Usuario.instancia.getHistoricoLimpeza(),
                                "HISTÓRICO DE LIMPEZA", 1);
                        break;
                    }


                    case 3: {//BOTÃO LIMPEZA
                        if (Usuario.instancia == null) {
                            JOptionPane.showMessageDialog(null, "Sem Usuário Cadastrado.",
                                    "ATENÇÃO", 2);
                            break;
                        }
                        int limp = JOptionPane.showConfirmDialog(null, "Registrar Limpeza de Celular?",
                                "REGISTRANDO LIMPEZA!", 0);
                        if (limp == 0) {
                            String temp = "Limpeza efetuada em: " + gerenciadorDeDatas.getData();
                            Usuario.instancia.setHistoricoLimpeza(temp);
                            //Usuario.instancia.setUltimaLimpeza(gerenciadorDeDatas.getData());
                            Usuario.instancia.setAlarme("nao");
                            Usuario.instancia.setHouveAlteracao(true);
                            adapterInvocador.salvarHistorico(Usuario.instancia.getMatricula(),
                                    Usuario.instancia.salvarHistorico());
                            adapterInvocador.salvarPerfil(Usuario.instancia.getMatricula(), Usuario.instancia.salvarPerfil());
                        }
                        break;
                    }

                    case 4: {//Situação Atual de Limpeza
                        if (Usuario.instancia == null) {
                            JOptionPane.showMessageDialog(null, "Sem Usuário Cadastrado.",
                                    "ATENÇÃO", 2);
                            break;
                        }
                        Usuario.instancia.alerta();
                        break;
                    }

                    case 5: {//INSTRUÇÕES
                        Usuario.instancia.getInstrucoesLimpeza();
                        break;
                    }

                    case 6: {//Atualizar
                        String matricula = Usuario.instancia.getMatricula();
                        if (adapterInvocador.importarPerfil(matricula)){
                            adapterInvocador.importarHistorico(matricula);
                            JOptionPane.showMessageDialog(null, "Atualização realizada com sucesso!",
                                    "ATUALIZAÇÃO DE DADOS", 1);
                        }

                        break;
                    }

                    case 7: {// Sair
                        if (Usuario.instancia.getHouveAlteracao()){
                            adapterInvocador.salvarPerfil(Usuario.instancia.getMatricula(),
                                    Usuario.instancia.salvarPerfil());
                            adapterInvocador.salvarHistorico(Usuario.instancia.getMatricula(),
                                    Usuario.instancia.salvarHistorico());
                        }
                        saidaSegura.setSairMenuPrincipal(true);
                        break;
                    }

                    default: {
                        JOptionPane.showMessageDialog(null, "Digite um número entre 1 e 7",
                                "Número inválido!", 0);
                        break;
                    }
                }
            }
            catch (InputMismatchException | NumberFormatException | NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Digite uma entrada válida.",
                        "ATENÇÃO!", 0);
            }
        }

    }
}