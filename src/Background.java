import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

public class Background {
    private BufferedImage imagem;
    private int y;

    public Background() {
        try {
            imagem = ImageIO.read(new File("imagens/SpaceImage3.png"));
        } catch (IOException e) {
            System.out.println("Nao foi possivel carregar a imagem do plano de fundo");
            e.printStackTrace();
        }

        y = 0;
    }

    public void paint(Graphics2D g) {
        g.drawImage(imagem, 0, y - MainJogo.height * 2, imagem.getWidth(), imagem.getHeight(), null);
        
        g.drawImage(imagem, 0, y, imagem.getWidth(), -imagem.getHeight(), null);

        g.drawImage(imagem, 0, y, imagem.getWidth(), imagem.getHeight(), null);

        y += 3;

        if(y > MainJogo.height * 2) {
            y = 0;
        }
    }
}
