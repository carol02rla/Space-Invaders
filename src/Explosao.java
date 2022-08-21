import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class Explosao {
    private BufferedImage imagem;
    private int x, y;
    private int duracao;
    private int animacaoTotal;
    private int linha;
    private int coluna;

    public Explosao(BufferedImage imagem, int x, int y) {
        this.imagem = imagem;
        this.x = x;
        this.y = y;

        animacaoTotal = 30;
        duracao = 0;
        linha = 0;
        coluna = 0;
    }
    
    public void atualizar() {
        duracao += 2;
        
        linha = duracao / 6;
        coluna = duracao % 5;
    }

    public void paint(Graphics2D g) {
        g.drawImage(imagem, x, y, x + 50, y + 50, 192*coluna, 192*linha, 192*coluna + 192, 192*linha + 192, null);
    }
    
    public boolean acabou() {
        if(duracao >= animacaoTotal) {
            return true;
        }

        return false;
    }
}
