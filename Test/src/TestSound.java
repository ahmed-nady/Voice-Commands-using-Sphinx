 /*
 * Copyright 1999-2004 Carnegie Mellon University.
 * Portions Copyright 2004 Sun Microsystems, Inc.
 * Portions Copyright 2004 Mitsubishi Electric Research Laboratories.
 * All Rights Reserved.  Use is subject to license terms.
 *
 * See the file "license.terms" for information on usage and
 * redistribution of this file, and for a DISCLAIMER OF ALL
 * WARRANTIES.
 *
 */

 

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import java.awt.AWTException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * A simple TestSound demo showing a simple speech application built using Sphinx-4. This application uses the Sphinx-4
 * endpointer, which automatically segments incoming audio into utterances and silences.
 */
public class TestSound {

    public static void main(String[] args) {
        ConfigurationManager cm;

        if (args.length > 0) {
            cm = new ConfigurationManager(args[0]);
        } else {
            cm = new ConfigurationManager(HelloWorld.class.getResource("helloworld.config.xml"));
        }

        Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();

        // start the microphone or exit if the programm if this is not possible
        Microphone microphone = (Microphone) cm.lookup("microphone");
        if (!microphone.startRecording()) {
            System.out.println("Cannot start microphone.");
            recognizer.deallocate();
            System.exit(1);
        }
         Robot robot =null ;
        try {
          robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(HelloWorld.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          HWND hwnd = User32.INSTANCE.FindWindow
		       (null, "ImageJ 3D Viewer"); // window title
		if (hwnd == null) {
			System.out.println("ImageJ 3D Viewer is not running");
		}
		else{
			User32.INSTANCE.ShowWindow(hwnd, 9 );        // SW_RESTORE
			User32.INSTANCE.SetForegroundWindow(hwnd);   // bring to front
		}

        System.out.println("Say: (Zoom In| Zoom Out|Translate Left|Translate Right|Translate Up|Translate Down|Rotate Left|Rotate Right)");

        // loop the recognition until the programm exits.
        while (true) {
            System.out.println("Start speaking. Press Ctrl-C to quit.\n");

            Result result = recognizer.recognize();

            if (result != null) {
                String resultText = result.getBestFinalResultNoFiller();
                System.out.println("You said: " + resultText + '\n');
                
                //handle Scale Case
                if(resultText.equalsIgnoreCase("Zoom In"))
               {
                   /* robot.keyPress(KeyEvent.VK_UP);
                     robot.keyPress(KeyEvent.VK_UP);
                      robot.keyPress(KeyEvent.VK_UP);*/
              //  robot.keyPress(KeyEvent.VK_ALT);
               //     robot.keyPress(KeyEvent.VK_);
               // robot.keyRelease(KeyEvent.VK_NUM_LOCK);
                 robot.keyPress(KeyEvent.VK_ALT);
                  for(int i =0;i<5;i++){
                   robot.keyPress(KeyEvent.VK_UP);
                       }
                       robot.keyRelease(KeyEvent.VK_ALT);
               }
               else if(resultText.equalsIgnoreCase("Zoom Out"))
               {
                  robot.keyPress(KeyEvent.VK_ALT);
                  for(int i =0;i<5;i++){
                   robot.keyPress(KeyEvent.VK_DOWN);
                       }
                       robot.keyRelease(KeyEvent.VK_ALT);
               }
               //handle Translate Case
               else if(resultText.equalsIgnoreCase("Translate"))
               {
               // robot.keyPress(KeyEvent.VK_ALT);
                robot.keyPress(KeyEvent.VK_SHIFT);
                for(int i =0;i<5;i++){
                   robot.keyPress(KeyEvent.VK_LEFT);
                       }
                
                robot.keyRelease(KeyEvent.VK_SHIFT);
               /* robot.keyPress(KeyEvent.VK_NUMPAD4);
                robot.keyPress(KeyEvent.VK_NUMPAD4);
                 robot.keyPress(KeyEvent.VK_NUMPAD4);*/
                   
               }
                 else if(resultText.equalsIgnoreCase("Translate Right"))
               {
                     robot.keyPress(KeyEvent.VK_SHIFT);
                for(int i =0;i<5;i++){
                   robot.keyPress(KeyEvent.VK_RIGHT);
                       }
                 robot.keyRelease(KeyEvent.VK_SHIFT);
              
               }
                 else if(resultText.equalsIgnoreCase("Translate Up"))
               {
                     robot.keyPress(KeyEvent.VK_SHIFT);
                      for(int i =0;i<5;i++){
                   robot.keyPress(KeyEvent.VK_DOWN);
                       }
                robot.keyRelease(KeyEvent.VK_SHIFT);
               }
                 else if(resultText.equalsIgnoreCase("Translate Down"))
               {
                     robot.keyPress(KeyEvent.VK_SHIFT);
                for(int i =0;i<5;i++){
                   robot.keyPress(KeyEvent.VK_UP);
                       }
                 robot.keyRelease(KeyEvent.VK_SHIFT);
                  
               }
                //handle Rotate Case
                 else if(resultText.equalsIgnoreCase("Rotate"))
               {
                 for(int i =0;i<5;i++){
                   robot.keyPress(KeyEvent.VK_LEFT);
                       }
                    
                   
               }
                 else if(resultText.equalsIgnoreCase("Rotate Right"))
               {
                               
              for(int i =0;i<5;i++){
                   robot.keyPress(KeyEvent.VK_RIGHT);
                       }
                   
               }
                  else if(resultText.equalsIgnoreCase("Rotate Up"))
               {
                 for(int i =0;i<5;i++){
                   robot.keyPress(KeyEvent.VK_UP);
                       }
                   
                   
               }
                  else if(resultText.equalsIgnoreCase("Rotate Down"))
               {
                 for(int i =0;i<5;i++){
                   robot.keyPress(KeyEvent.VK_DOWN);
                       }
                   
                   
               }
                
            } else {
                System.out.println("I can't hear what you said.\n");
            }
        }
    }
}
