import java.awt.Graphics2D;

public class Tiro {

    private int x;
    private int y;
    private int velocidade;
    private int tamX;
    private int tamY;

    // Construtor
    public Tiro(int inicioX, int inicioY) {
        this.x = inicioX;
        this.y = inicioY;
        velocidade = 10;
        tamX = 3;
        tamY = 15;
    }
    
    public void paint(Graphics2D g) {
        g.setColor(java.awt.Color.red);
        g.fillRect(x, y, tamX, tamY);
    }

    public void atualiza() {
        y -= velocidade;
    }

    public boolean destroy() {
        return y < 0; // retorna verdadeiro ou falso
    }

    public boolean colisao(Inimigo inimigo) {
        if(x >= inimigo.getX() && x + tamX <= inimigo.getX() + inimigo.getTam()) {
            if(y <= inimigo.getY() + inimigo.getTam()) {
                return true;
            }
        }

        return false;
    }
}
