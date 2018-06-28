/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ahmed nady
 */
 
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

public class ControlImageJWithVoice {
    
    
    
    public static void main(String[] args) throws Exception
    {
        Configuration configuration = new Configuration();
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.dmp");
        
        Robot robot = new Robot();
        
          HWND hwnd = User32.INSTANCE.FindWindow
		       (null, "ImageJ 3D Viewer"); // window title
		if (hwnd == null) {
			System.out.println("ImageJ 3D Viewer is not running");
		}
		else{
			User32.INSTANCE.ShowWindow(hwnd, 9 );        // SW_RESTORE
			User32.INSTANCE.SetForegroundWindow(hwnd);   // bring to front
		}

        LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
        recognizer.startRecognition(true);

        SpeechResult result;
        while ((result = recognizer.getResult()) != null)
        {
             
            System.out.println(result.getHypothesis());
            if(result.getHypothesis().equals("in"))
            {
               robot.keyPress(KeyEvent.VK_ALT);
                robot.keyPress(KeyEvent.VK_NUMPAD9);
               // robot.keyPress(KeyEvent.VK_NUMPAD9);
           // robot.delay(100);
            }
            else if(result.getHypothesis().equals("out"))
            {
                robot.keyPress(KeyEvent.VK_NUMPAD3);
                robot.keyPress(KeyEvent.VK_NUMPAD3);
            }
        }
        recognizer.stopRecognition();
    }
    
}
