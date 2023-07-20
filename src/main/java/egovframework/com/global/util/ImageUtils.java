package egovframework.com.global.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageUtils.class);
	
	public interface TextPosition{
		public String getText();
		public float getLeft();
		public float getTop();
	}
	public static void makeImageWithText(File sourceFile, File targetFile, TextPosition position) throws Exception {
		long startTime = System.currentTimeMillis();
		LOGGER.debug("source image : " + sourceFile.getAbsolutePath()+", target image: " + targetFile.getAbsolutePath()+", position text="+position.getText()+",left="+position.getLeft()+",top="+position.getTop());
		String stampdate = position.getText();
//		File loadImage = new File(getClass().getClassLoader().getResource("stamp.png").getFile());
//		File makeImage = new File(loadImage.getParentFile().getAbsolutePath() +File.separatorChar+ "stamp_" + sdf.format(date) + ".png");
		
		String ext = FilenameUtils.getExtension(sourceFile.getName());
		if(ext == null || ext.trim().length() < 1)
			ext = "png";
				
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(sourceFile);
		} catch (IOException e) {
			throw e;
		}
		int imgWidth = bi.getWidth();
		int imgHeight = bi.getHeight();
		LOGGER.debug("image width : " + imgWidth + ", height : " + imgHeight);
		Graphics2D g2 = null;
		g2 = bi.createGraphics();
		Font font = new Font("Default", Font.BOLD, 9);
		FontRenderContext frc = new FontRenderContext(null, true, true);
		Rectangle2D r2 = font.getStringBounds(stampdate, frc);
		int textWidth = (int) r2.getWidth();
		int textHeight = (int) r2.getHeight();
		LOGGER.debug("textWidth=" + textWidth+", textHeight="+textHeight);
		float paddingleft = (imgWidth / 2) - (textWidth / 2);
		float paddingTop = (imgHeight / 2) - (textHeight / 2) + 20;		
		LOGGER.debug("paddingleft=" + paddingleft +", paddingTop="+paddingTop);
		g2.setColor(Color.black);
		g2.setFont(font);
		g2.drawString(stampdate, paddingleft, paddingTop);
		g2.dispose();
		try {
			ImageIO.write(bi, ext, targetFile);
		} catch (IOException e) {
			throw e;
		}
		long endTime = System.currentTimeMillis();
		LOGGER.debug("elapse time=" + ((endTime - startTime) / 1000.0) +"(s)");

	}
	
	public static String makeImgSrc(File imageFile)throws Exception{
		String ext = FilenameUtils.getExtension(imageFile.getName());
		if(ext == null || ext.trim().length() < 1)
			ext = "png";
		byte[] fileContent = FileUtils.readFileToByteArray(imageFile);
		String encodedString = new String(Base64.encodeBase64(fileContent));
		return "data:image/"+ext+";base64,"+encodedString; 
	}
	
	public static TextPosition instanceDefaultTextPosition(File imageFile, final String imageText)throws Exception{
		LOGGER.debug("imageFile : " + imageFile.getAbsolutePath() + ", stampText : " + imageText);
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(imageFile);
		} catch (IOException e) {
			throw e;
		}
		int imgWidth = bi.getWidth();
		int imgHeight = bi.getHeight();
		LOGGER.debug("image width : " + imgWidth + ", height : " + imgHeight);
		Graphics2D g2 = null;
		g2 = bi.createGraphics();
		Font font = new Font("Default", Font.BOLD, 9);
		FontRenderContext frc = new FontRenderContext(null, true, true);
		Rectangle2D r2 = font.getStringBounds(imageText, frc);
		int textWidth = (int) r2.getWidth();
		int textHeight = (int) r2.getHeight();
		LOGGER.debug("textWidth=" + textWidth+", textHeight="+textHeight);
		final float paddingleft = (imgWidth / 2) - (textWidth / 2);
		final float paddingTop = (imgHeight / 2) - (textHeight / 2) + 20;		
		LOGGER.debug("paddingleft=" + paddingleft +", paddingTop="+paddingTop);
		
		return new TextPosition() {
			
			@Override
			public float getTop() {
				return paddingTop;
			}
			
			@Override
			public String getText() {
				return imageText;
			}
			
			@Override
			public float getLeft() {
				return paddingleft;
			}
		};

	}
}
