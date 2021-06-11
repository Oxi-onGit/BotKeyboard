import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Oxi-onGit
 */
public class ThreadKeyboard extends Thread 
{
    
    //init log4j
    private static final Logger logger = LogManager.getLogger(ThreadKeyboard.class);
    private final Map<?,?> mapKey;
    private AtomicBoolean runThread = new AtomicBoolean(false);
    
    /**
     * récupère les données du fichier json en map
     * 
     * @param input_File : fichier Json contenant les Key
     */
    public ThreadKeyboard(String input_File)
    {
        JsonManagement json = new JsonManagement();
        //call de la méthode qui lie le fichier json
        this.mapKey = json.readJson(input_File);
    }
    
    /**
     * initialise les robots et envoie les touches contenues dans le map à L'OS
     */
    @Override
    public void run() 
    {
        try 
        {
            Robot robot = new Robot();
            robot.setAutoWaitForIdle(false);
            while (!Thread.currentThread().isInterrupted())
            {
                //keySet retour la clef de la ligne en cours sur la map
                for (Object keymap : mapKey.keySet())
                {
                    //transforme les données récupère du get en ArrayList<String>
                    List listValue = (List) mapKey.get(keymap);

                    //presse les touches contenues dans l'ArrayList
                    for (int i = 1; i < listValue.size(); i++)
                    {
                        robot.keyPress(keystringToKeycode(listValue.get(i).toString()));
                    }

                    //relâche les touches contenues dans l'ArrayList
                    for (int i = 1; i < listValue.size(); i++)
                    {
                        robot.keyRelease(keystringToKeycode(listValue.get(i).toString()));
                    }
                    
                    //set le temps entre chaque envoie de key ( en ms)
                    robot.setAutoDelay((int)Double.parseDouble(listValue.get(0).toString())); 
                    
                    if (runThread.get()) 
                    {
                        synchronized (runThread) 
                        {
                            runThread.wait();
                        }
                    } 
                }
            }
        } catch (AWTException | InterruptedException ex) 
        {
            logger.debug("boucle thread keyboard "+ ex);
        }
    }
    
        
    /**
     * met en pause le thread_keyboard
     */
    public void goWait()
    {
      runThread.set(true);
    }
    
    /**
     * relance le thread_keyboard
     */
    public  void goNotify()
    { 
        runThread.set(false);
        synchronized (runThread) 
        {
             runThread.notify();
        }
    }
    
    /**
     * return la variable runThread pour savoir si le Thread est wait
     * @return boolean
     */
    public boolean isWait()
    {
        return runThread.get();
    }
    
    /**
     * 
     * return la Virtual Key correspondante au String passé en paramètre le thread
     * 
     * @param keystring : le caractère rechercher
     * @return le int représente la VK correspondante
     */
    private int keystringToKeycode(String keystring)
    {
        switch (keystring) 
        {
            case "a": return KeyEvent.VK_A; 
            case "b": return KeyEvent.VK_B; 
            case "c": return KeyEvent.VK_C; 
            case "d": return KeyEvent.VK_D; 
            case "e": return KeyEvent.VK_E; 
            case "f": return KeyEvent.VK_F; 
            case "g": return KeyEvent.VK_G; 
            case "h": return KeyEvent.VK_H; 
            case "i": return KeyEvent.VK_I; 
            case "j": return KeyEvent.VK_J; 
            case "k": return KeyEvent.VK_K; 
            case "l": return KeyEvent.VK_L; 
            case "m": return KeyEvent.VK_M; 
            case "n": return KeyEvent.VK_N; 
            case "o": return KeyEvent.VK_O; 
            case "p": return KeyEvent.VK_P; 
            case "q": return KeyEvent.VK_Q; 
            case "r": return KeyEvent.VK_R; 
            case "s": return KeyEvent.VK_S; 
            case "t": return KeyEvent.VK_T; 
            case "u": return KeyEvent.VK_U; 
            case "v": return KeyEvent.VK_V; 
            case "w": return KeyEvent.VK_W; 
            case "x": return KeyEvent.VK_X; 
            case "y": return KeyEvent.VK_Y; 
            case "z": return KeyEvent.VK_Z;            
            case "0": return KeyEvent.VK_0; 
            case "1": return KeyEvent.VK_1; 
            case "2": return KeyEvent.VK_2; 
            case "3": return KeyEvent.VK_3; 
            case "4": return KeyEvent.VK_4; 
            case "5": return KeyEvent.VK_5; 
            case "6": return KeyEvent.VK_6; 
            case "7": return KeyEvent.VK_7; 
            case "8": return KeyEvent.VK_8; 
            case "9": return KeyEvent.VK_9; 
            case "0_NUMPAD": return  KeyEvent.VK_NUMPAD0;
            case "1_NUMPAD": return  KeyEvent.VK_NUMPAD1;
            case "2_NUMPAD": return  KeyEvent.VK_NUMPAD2;
            case "3_NUMPAD": return  KeyEvent.VK_NUMPAD3;
            case "4_NUMPAD": return  KeyEvent.VK_NUMPAD4;
            case "5_NUMPAD": return  KeyEvent.VK_NUMPAD5;
            case "6_NUMPAD": return  KeyEvent.VK_NUMPAD6;
            case "7_NUMPAD": return  KeyEvent.VK_NUMPAD7;
            case "8_NUMPAD": return  KeyEvent.VK_NUMPAD8;
            case "9_NUMPAD": return  KeyEvent.VK_NUMPAD9;
            case "`": return KeyEvent.VK_BACK_QUOTE; 
            case "-": return KeyEvent.VK_MINUS; 
            case "=": return KeyEvent.VK_EQUALS; 
            case "!": return KeyEvent.VK_EXCLAMATION_MARK; 
            case "@": return KeyEvent.VK_AT; 
            case "#": return KeyEvent.VK_NUMBER_SIGN; 
            case "$": return KeyEvent.VK_DOLLAR; 
            case "^": return KeyEvent.VK_CIRCUMFLEX; 
            case "&": return KeyEvent.VK_AMPERSAND; 
            case "*": return KeyEvent.VK_ASTERISK; 
            case "(": return KeyEvent.VK_LEFT_PARENTHESIS; 
            case ")": return KeyEvent.VK_RIGHT_PARENTHESIS; 
            case "_": return KeyEvent.VK_UNDERSCORE; 
            case "+": return KeyEvent.VK_PLUS; 
            case "[": return KeyEvent.VK_OPEN_BRACKET; 
            case "]": return KeyEvent.VK_CLOSE_BRACKET; 
            case "\\": return KeyEvent.VK_BACK_SLASH; 
            case ";": return KeyEvent.VK_SEMICOLON; 
            case ":": return KeyEvent.VK_COLON; 
            case "'": return KeyEvent.VK_QUOTE; 
            case "\"": return KeyEvent.VK_QUOTEDBL; 
            case ",": return KeyEvent.VK_COMMA; 
            case ".": return KeyEvent.VK_PERIOD; 
            case "/": return KeyEvent.VK_SLASH; 
            case " ": return KeyEvent.VK_SPACE; 
            case "TAB": return KeyEvent.VK_TAB; 
            case "ENTER": return KeyEvent.VK_ENTER; 
            case "ALT": return KeyEvent.VK_CONTEXT_MENU ; 
            case "CTRL": return KeyEvent.VK_CONTROL; 
            case "SHIFT": return KeyEvent.VK_SHIFT; 
            default:
                return -1;
        }
    }
}