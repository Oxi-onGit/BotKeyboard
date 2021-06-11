import com.dstjacques.jhotkeys.JHotKeyListener;
import com.dstjacques.jhotkeys.JHotKeys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Oxi-onGit
 */
public class ThreadControle extends Thread
{
    //init log4j
    private static final Logger logger = LogManager.getLogger(ThreadControle.class);
    private final String targetSoftware;
    private ThreadKeyboard thread_keyboard;
    private final JnaManagement jna;
    
    
    /**
     * le constructeur lance le thread, initialise les hot key(hotKey) et la class jna
     * 
     * @param targetSoftware : logiciel a target
     * @param inputFile : fichier contenant les key
     */
    public ThreadControle(String targetSoftware, String inputFile) 
    {
        jna = new JnaManagement();
        this.targetSoftware = targetSoftware;
        thread_keyboard = new ThreadKeyboard(inputFile);
        hotKey();
        jna.setFocusWindows(targetSoftware);
    }
    
    /**
     *  le thread vérifie que le logiciel cible a bien le focus OS
     */
    @Override
    public void run()
    {   
        thread_keyboard.start();
        while (Thread.currentThread().isAlive()) 
        {  
            try 
            {
                //appel les class jna pour savoir qui a le focus
                if (!jna.getFocusWindows().equals(targetSoftware)) 
                {
                    waitThread_keyboard();
                    System.out.println("press F11 for replay");
                }
                Thread.sleep(100); // ms 
            } catch (InterruptedException ex) 
            {
                logger.debug("boucle thread controle "+ ex);
            }
        }
    }
    
    /**
     *  mettre en pause le Thread
     */
    private void waitThread_keyboard()
    {
        thread_keyboard.goWait();
    }
    
    /**
     * relancer le Thread 
     */
    private void notifyThread_keyboard()
    {
        thread_keyboard.goNotify();
    }
    
    /**
    * return la variable runThread pour savoir si le Thread est wait
    * @return boolean
    */
    private boolean isWait()
    {
        return thread_keyboard.isWait();
    }
    
    /**
    *  met en place deux hotKey F11 et F12 et change un boolean quand la touche est activée
    */
    private void hotKey()
    {
        JHotKeys hotkeys = new JHotKeys(".\\");

        //Global hotkey on pressing 'F9' (Virtual Key Codes)
        hotkeys.registerHotKey(0, 0, 120);
        
        //Global hotkey on pressing 'F10' (Virtual Key Codes)
        hotkeys.registerHotKey(1, 0, 121);

        //Global hotkey on pressing 'F11' (Virtual Key Codes)
        hotkeys.registerHotKey(2, 0, 122);

        //initialise les actions en fonction de l'activation d'une touche initialiser ci-dessus
        JHotKeyListener hotkeyListener = new JHotKeyListener()
        {
          public void onHotKey(int id) 
          {
            switch(id)
            {
              case 0 : 
                //donne le Focus passé en paramètre
                jna.setFocusWindows(targetSoftware);
              break;
              case 1 :
                if (isWait()) 
                {
                    notifyThread_keyboard();
                }else
                {
                    waitThread_keyboard();
                }
              break;
              case 2 : //met en pause le thread / passe la variable a false et kill le bot
                System.exit(0);
              break;
            }
          }
        };
        //ajoute le listener
        hotkeys.addHotKeyListener(hotkeyListener);
    }
}
