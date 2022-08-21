import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpaceInvaders extends JPanel implements Runnable, KeyListener {
    
    private Font minhaFonte = new Font("Consolas", Font.BOLD, 20);
    private Nave nave;
    private int direcao = 0;
    private ArrayList<Tiro> tiros;
    private ArrayList<Inimigo> inimigos;
    private ArrayList<Explosao> explosoes;
    private Background background;
    private boolean ganhou;
    private boolean perdeu;
    private BufferedImage imagemExplosao;

    // CONSTRUTOR
    public SpaceInvaders() {
        nave = new Nave();
        tiros = new ArrayList<Tiro>();
        inimigos = new ArrayList<Inimigo>();
        explosoes = new ArrayList<Explosao>();
        background = new Background();
        ganhou = false;
        perdeu = false;

        BufferedImage imagemInimigo = null;
        try {
            imagemInimigo = ImageIO.read(new File("imagens/alien2.png"));
            imagemExplosao = ImageIO.read(new File("imagens/sprite-explosoes.png"));
        } catch (IOException e) {
            System.out.println("Nao foi possivel carregar a imagem do inimigo");
            e.printStackTrace();
        }

        for(int i = 0; i < 50; i++) {
            inimigos.add(new Inimigo(imagemInimigo, 20 + i % 10 * 50, 20 + i / 10 * 50, 1));
        }

        Thread lacoDoJogo = new Thread(this);
        lacoDoJogo.start();
    }

    public void run() {
        while(true) {
            long tempoInicial = System.currentTimeMillis();
            
            update();
            repaint();

            long tempoFinal = System.currentTimeMillis();
            long diferenca = 16 - (tempoFinal - tempoInicial);

            if(diferenca > 0) {
                dorme(diferenca);
            }
        }
    }

    private void update() {
        if(inimigos.size() == 0) {
            ganhou = true;
        }

        nave.movimenta(direcao);

        for(Inimigo inimigo: inimigos) {
            inimigo.atualizar();

            if(inimigo.getY() >= MainJogo.height - 160) {
                perdeu = true;
            }
        }
        
        for(int i = 0; i < tiros.size(); i++) {
            tiros.get(i).atualiza();
            
            if(tiros.get(i).destroy()) {
                tiros.remove(i);
                i--;
            }
            else {
                for(int j = 0; j < inimigos.size(); j++) {
                    if(tiros.get(i).colisao(inimigos.get(j))) {
                        explosoes.add(new Explosao(imagemExplosao, inimigos.get(j).getX(), inimigos.get(j).getY()));
                        inimigos.remove(j);
                        j--;
                        tiros.remove(i);
                        break;
                    }
                }
            }
        }

        for(int i = 0; i < inimigos.size(); i++) {
            if(inimigos.get(i).getX() < 0 || inimigos.get(i).getX() > MainJogo.width - inimigos.get(i).getTam()) {
                for(int j = 0; j < inimigos.size(); j++) {
                    inimigos.get(j).trocaDirecao();
                }
                break;
            }
        }
        
        for(int i = 0; i < explosoes.size(); i++) {
            explosoes.get(i).atualizar();

            if(explosoes.get(i).acabou()) {
                explosoes.remove(i);
                i--;
            }
        }
    }
    
    public void paintComponent(java.awt.Graphics g2) {
        super.paintComponent(g2);  // limpar a tela
        
        Graphics2D g = (Graphics2D) g2.create();
        g.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // desenha o plano de fundo
        background.paint(g);

        // desenha as explosoes
        for(Explosao explosao: explosoes) {
            explosao.paint(g);
        }
                
        // desenha os inimigos
        for(Inimigo inimigo: inimigos) {
            inimigo.paint(g);
        }
        
        // desenha a nave
        nave.paint(g);
        
        // desenha os tiros
        for(Tiro tiroAtual: tiros) {
            tiroAtual.paint(g);
        }

        if(ganhou) {
            g.setColor(java.awt.Color.pink);
            g.setFont(minhaFonte);
            g.drawString("YOU WON", (MainJogo.width/2) - 40, (MainJogo.height/2) - 10);
        }

        if(perdeu) {
            g.setColor(java.awt.Color.pink);
            g.setFont(minhaFonte);
            g.drawString("GAME OVER", (MainJogo.width/2) - 45, (MainJogo.height/2) - 10);
        }
    }
    
    private void dorme(long tempo) {
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {

    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D) {
            direcao = 1;
        }
        
        if(e.getKeyCode() == KeyEvent.VK_A) {
            direcao = -1;
        }
        
        if(e.getKeyCode() == KeyEvent.VK_SPACE && nave.podeAtirar()) {
            tiros.add(nave.atirar());
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D) {
            direcao = 0;
        }
    
        if(e.getKeyCode() == KeyEvent.VK_A) {
            direcao = 0;
        }
    }

}
