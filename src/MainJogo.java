import javax.swing.JFrame;

public class MainJogo {

    static int width = 800;
    static int height = 600;
    public static void main(String[] args) throws Exception {

        JFrame janela = new JFrame("SpaceInvaders");

        janela.setSize(width, height);
        janela.setLayout(null);
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SpaceInvaders invasaoAlienigena = new SpaceInvaders();
        invasaoAlienigena.setBounds(0, 0, width, height);

        janela.add(invasaoAlienigena);

        janela.addKeyListener(invasaoAlienigena);

        janela.setVisible(true);

    }
    
}
