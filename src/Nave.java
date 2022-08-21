import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

public class Nave {

    private BufferedImage desenho;
    private int x;
    private int y;
    private int velocidade;
    private boolean podeAtirar;
    private int recarregando;

    // Construtor
    public Nave() {
        try {
            desenho = ImageIO.read(new File("imagens/nave.png"));
        } catch (IOException e) {
            System.out.println("Nao foi possivel carregar a imagem da nave");
            e.printStackTrace();
        }

        x = (MainJogo.width / 2) - 50;
        y = MainJogo.height - 150;
        velocidade = 3;
        podeAtirar = true;
        recarregando = 0;
    }

    public void paint(Graphics2D g) {
        g.drawImage(desenho, x, y, x + 100, y + 100, 0, 0, desenho.getWidth(), desenho.getHeight(), null);
    }

    // a nave retorna um tiro na posicao em que ela esta
    public Tiro atirar() {
        podeAtirar = false;
        recarregando = 0;

        Tiro novoTiro = new Tiro(x + 48, y);
        return novoTiro;
    }

    public boolean podeAtirar() {
        return podeAtirar;
    }

    public void movimenta(int valor) {
        if(valor == 1) {
            x += velocidade;
        } else if (valor == -1) {
            x -= velocidade;
        }

        if(recarregando >= 10) {
            podeAtirar = true;
            recarregando = 0;
        }
        recarregando++;
    }
}
