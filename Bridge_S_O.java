import java.nio.file.Path;
import java.nio.file.Paths;

public class Bridge_S_O {
    private Tipo_S_O linux = new TipoLinux();
    private TipoWindows tipoWindows = new TipoWindows();

    public Path nomePerfil(String matricula, int sisOpe){
        if (sisOpe==1) return Paths.get(tipoWindows.getPathPerfil()+matricula+".txt");
        else return Paths.get(linux.getPathPerfil()+matricula+".txt");
    }

    public Path nomeHistorico(String matricula, int sisOpe){
        if (sisOpe==1) return Paths.get(tipoWindows.getPathHistorico()+matricula+"-historico.txt");
        else return Paths.get(linux.getPathHistorico()+matricula+"-historico.txt");
    }

    public void criaDiretorio(int sisOpe){
        if (sisOpe==1) {
            tipoWindows.criaDiretorioPerfil();
            tipoWindows.criaDiretorioHistorico();
        }
        else {
            linux.criaDiretorioPerfil();
            linux.criaDiretorioHistorico();
        }
    }
}

