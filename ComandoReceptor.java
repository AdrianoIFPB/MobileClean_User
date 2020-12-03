import javax.swing.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ComandoReceptor {

    private static ComandoReceptor instancia;
    private Bridge_S_O bridgeSO = new Bridge_S_O();
    private String senha;

    private ComandoReceptor(){}

    public static ComandoReceptor getInstancia(){
        if (instancia == null){
            return instancia = new ComandoReceptor();
        }
        else return instancia;
    }

    public boolean importarHistorico(String matricula, int sisOpe){
        bridgeSO.criaDiretorio(sisOpe);
        List<String> linhasHistorico;
        String historicoUsuario ="";
        Path caminhoHistorico = bridgeSO.nomeHistorico(matricula, sisOpe);
        try {
            if (Files.exists(caminhoHistorico)){
                linhasHistorico = Files.readAllLines(caminhoHistorico, Charset.defaultCharset());
                for (String linha: linhasHistorico){
                    String linhaHistorico [] = linha.split(";");
                    historicoUsuario += linhaHistorico[0]+"\n";
                }
                Usuario.instancia.importarHistorico(historicoUsuario);
                return true;
            }
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null, "Sem histórico salvo para importar.",
                    "ATENÇÃO!",2);
        }
        return false;
    }

    public boolean importarSenha(String matricula, int sisOpe) {
        bridgeSO.criaDiretorio(sisOpe);
        List<String> linha;
        Path localPerfil = bridgeSO.nomePerfil(matricula, sisOpe);
        try {
            linha = Files.readAllLines(localPerfil, Charset.defaultCharset());
            String palavra []=linha.get(0).split(";");
            senha = palavra[5];
            return true;
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null, "Usuário não encontrado!",
                    "Atenção!",2);

            return false;
        }
    }

    public String getSenha(){
        return senha;
    }

    public boolean importarPerfil(String matricula, int sisOpe) {
        bridgeSO.criaDiretorio(sisOpe);
        List<String> linha;
        Path localPerfil = bridgeSO.nomePerfil(matricula, sisOpe);
        try {
            linha = Files.readAllLines(localPerfil, Charset.defaultCharset());
            String palavra []=linha.get(0).split(";");
            Usuario.getInstancia(palavra[0],palavra[1],palavra[2],palavra[3], false, palavra[4], palavra[5], palavra[6]);
            return true;
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null, "Não foi possível importar o perfil.",
                    "Atenção!",2);
            return false;
        }
    }

    public boolean salvarHistorico(String matricula, String historico, int sisOpe) {
        bridgeSO.criaDiretorio(sisOpe);
        try {
            Path caminhoHistorico = bridgeSO.nomeHistorico(matricula, sisOpe);
            Files.write(caminhoHistorico, historico.getBytes());
            return true;
        } catch (IOException e){
            JOptionPane.showMessageDialog(null,"Não foi possível salvar histórico.",
                    "ATENÇÃO!",0);
            return false;
        }
    }

    public boolean salvarPerfil(String matricula,  String perfil, int sisOpe) {
        bridgeSO.criaDiretorio(sisOpe);
        try {
            Path nomeUsuario = bridgeSO.nomePerfil(matricula, sisOpe);
            Files.write(nomeUsuario, perfil.getBytes());
            return true;
        } catch (IOException e){
            JOptionPane.showMessageDialog(null,"Não foi possível salvar o perfil.",
                    "Ups!",0);
            return false;
        }
    }
}

