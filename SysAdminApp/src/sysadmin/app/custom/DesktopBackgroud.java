package sysadmin.app.custom;

import javax.swing.*;
import java.awt.*;

public class DesktopBackgroud extends JDesktopPane {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image imagen;

    public DesktopBackgroud() {
    }

    public DesktopBackgroud(String nombreImagen) {
        if (nombreImagen != null) {
            imagen = new ImageIcon(getClass().getResource(nombreImagen)).getImage();
        }
    }

    public DesktopBackgroud(Image imagenInicial) {
        if (imagenInicial != null) {
            imagen = imagenInicial;
        }
    }

    public void setImagen(String nombreImagen) {
        if (nombreImagen != null) {
            imagen = new ImageIcon(getClass().getResource(nombreImagen)).getImage();
        } else {
            imagen = null;
        }

        repaint();
    }

    public void setImagen(Image nuevaImagen) {
        imagen = nuevaImagen;

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
        } else {
            setOpaque(true);
        }

        super.paint(g);
    }
}
