package ainaa.acup.execution;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.imageio.ImageIO;

import ainaa.acup.server.ClientThread;

public class ScreenShot implements Runnable{
	
	private File file = null;
	private boolean isForSend;
	private BufferedImage image;
	
	public ScreenShot(boolean isForSend) {
		this.isForSend = isForSend;
	}
	@Override
	public void run() {
		
		file = new File(System.getProperty("user.home")+File.separator+"acup"+File.separator+"screenshot"+File.separator+new File(new Date().toString()).toPath());
		try {
			image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(image, "png", file);
			if(isForSend)
			{
				sendImage();
			}
		} catch (HeadlessException | AWTException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void sendImage() throws IOException
	{
            int bytecount = 2048;
            byte[] buf = new byte[bytecount];

            OutputStream OUT = ClientThread.socket.getOutputStream();
            BufferedOutputStream BuffOUT = new BufferedOutputStream(OUT, bytecount);
            FileInputStream in = new FileInputStream(file);

            int i = 0;
            while ((i = in.read(buf, 0, bytecount)) != -1) {
                BuffOUT.write(buf, 0, i);
                BuffOUT.flush();
            }
        if(null != BuffOUT)
        {
        	BuffOUT.close();
        	OUT.close();
        }
	}
	
}
