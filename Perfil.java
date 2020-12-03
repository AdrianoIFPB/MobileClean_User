import javax.swing.*;


class Perfil {
    private boolean transPublico=false;
    private boolean academia=false;//repartições, feiras, bancos
    private boolean banheiro=false;//academias, restaurantes, bares(banheiros), salão
    SaidaSegura saidaSegura = SaidaSegura.getInstance();

    String pesquisaDeHabitos(){

        saidaSegura.setSairPesqHabito(false);
        while (!saidaSegura.getSairPesqHabito()) {

                int trans = JOptionPane.showOptionDialog(null, "Você utiliza transporte público para " +
                        "deslocamento diário?\n", "PESQUISA DE HÁBITOS",0, 3, null, null, null);
                if (trans == 0) {
                    transPublico = true;
                }
                else if (trans == -1) {
                    saidaSegura.fecharPrograma();
                    return null;
                }

                int academ = JOptionPane.showOptionDialog(null, "Você frequenta academia?\n",
                        "PESQUISA DE HÁBITOS",0, 3, null, null, null);
                if (academ == 0) {
                    academia = true;
                }
                else if (academ == -1) {
                    saidaSegura.fecharPrograma();
                    return null;
                }

                int banhe = JOptionPane.showOptionDialog(null, "Você utiliza celular no banheiro?\n"
                        , "PESQUISA DE HÁBITOS",0, 3, null, null, null);
                if (banhe == 0) {
                    banheiro = true;
                }
                else if (banhe == -1) {
                    saidaSegura.fecharPrograma();
                    return null;
                }
                saidaSegura.setSairPesqHabito(true);
        }

        String temp =  perfilLimpeza();
        transPublico=false;
        academia=false;
        banheiro=false;
        return temp;
    }

    private String perfilLimpeza(){
        if (banheiro) return "Limpeza Diária";
        else if (transPublico && academia ) return "Limpeza a cada 3 dias";
        return "Limpeza Semanal";
    }
}
