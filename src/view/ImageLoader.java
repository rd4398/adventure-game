package view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 * This class acts as image loader and loads all the images that are required for the graphical
 * adventure game.
 */
class ImageLoader {
  HashMap<String, BufferedImage> locations;
  BufferedImage otyugh;
  BufferedImage ruby;
  BufferedImage diamond;
  BufferedImage sapphire;
  BufferedImage stench1;
  BufferedImage stench2;
  BufferedImage player;
  BufferedImage arrow;
  BufferedImage black;

  /**
   * Construct the image loader class and load all the images that are necessary.
   */
  ImageLoader() {
    locations = new HashMap<>();
    try {
      otyugh = ImageIO.read(ClassLoader.getSystemResource("img/otyugh.png"));
      ruby = ImageIO.read(ClassLoader.getSystemResource("img/ruby.png"));
      sapphire = ImageIO.read(ClassLoader.getSystemResource("img/sapphire.png"));
      diamond = ImageIO.read(ClassLoader.getSystemResource("img/diamond.png"));
      stench1 = ImageIO.read(ClassLoader.getSystemResource("img/stench01.png"));
      stench2 = ImageIO.read(ClassLoader.getSystemResource("img/stench02.png"));
      player = ImageIO.read(ClassLoader.getSystemResource("img/player.png"));
      arrow = ImageIO.read(ClassLoader.getSystemResource("img/arrow-white.png"));
      black = ImageIO.read(ClassLoader.getSystemResource("img/black.png"));
      setMap();
    } catch (IOException io) {
      io.printStackTrace();
    }
  }

  public void setMap() {
    try {
      locations.put("E", ImageIO.read(ClassLoader.getSystemResource("img/E.png")));
      locations.put("ES", ImageIO.read(ClassLoader.getSystemResource("img/ES.png")));
      locations.put("EW", ImageIO.read(ClassLoader.getSystemResource("img/EW.png")));
      locations.put("EWS", ImageIO.read(ClassLoader.getSystemResource("img/EWS.png")));
      locations.put("N", ImageIO.read(ClassLoader.getSystemResource("img/N.png")));
      locations.put("NE", ImageIO.read(ClassLoader.getSystemResource("img/NE.png")));
      locations.put("NES", ImageIO.read(ClassLoader.getSystemResource("img/NES.png")));
      locations.put("NEW", ImageIO.read(ClassLoader.getSystemResource("img/NEW.png")));
      locations.put("NEWS", ImageIO.read(ClassLoader.getSystemResource("img/NEWS.png")));
      locations.put("NS", ImageIO.read(ClassLoader.getSystemResource("img/NS.png")));
      locations.put("NW", ImageIO.read(ClassLoader.getSystemResource("img/NW.png")));
      locations.put("NWS", ImageIO.read(ClassLoader.getSystemResource("img/NWS.png")));
      locations.put("S", ImageIO.read(ClassLoader.getSystemResource("img/S.png")));
      locations.put("W", ImageIO.read(ClassLoader.getSystemResource("img/W.png")));
      locations.put("WS", ImageIO.read(ClassLoader.getSystemResource("img/WS.png")));

    } catch (IOException io) {
      io.printStackTrace();
    }
  }
}
