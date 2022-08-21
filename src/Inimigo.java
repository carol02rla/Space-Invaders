import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class Inimigo {

    private BufferedImage desenho;
    private int x;
    private int y;
    private int velocidade;
    private int direcao;

    public Inimigo(BufferedImage imagem, int inicioX, int inicioY, int direcao) {
        this.desenho = imagem;
        this.x = inicioX;
        this.y = inicioY;
        this.direcao = direcao;
        this.velocidade = 2;
    }

    public void atualizar() {
        x += velocidade * direcao;
    }

    public void trocaDirecao() {
        direcao = direcao * -1;
        y += 25;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void paint(Graphics2D g) {
        g.drawImage(desenho, x, y, x + 40, y + 40, 0, 0, desenho.getWidth(), desenho.getHeight(), null);
    }

    public int getTam() {
        return 40;
    }
}
