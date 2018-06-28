 
import java.awt.Robot;
import java.awt.event.KeyEvent;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

public class Notepad {

    static int keyInput[] = { KeyEvent.VK_J, KeyEvent.VK_A, KeyEvent.VK_V,
            KeyEvent.VK_A, KeyEvent.VK_SPACE };

    public static void main(String[] args) throws Exception {

        
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
                
                //get keyboard input
                
                for (int i = 0; i < keyInput.length; i++) {
            robot.keyPress(keyInput[i]);
            robot.delay(100);
        }
        
        
    }
}