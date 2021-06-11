import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

/**
 *
 * @author Oxi-onGit
 */
public class JnaManagement 
{
   /**
    * 
    * Vérifier si le logiciel est bien lancé et le met au premier plan et lui donner le focus
    * 
    * pour windows uniquement
    * 
    * @param targetSoftware : nom du logiciel a cibler 
    */
    public void setFocusWindows(String targetSoftware)
    {
        //recherche dans les logiciels en cours d'exécution le logiciel passer en paramètre
        //paramètre de droit = class name et paramètre de gauche tire de la fenêtre
        HWND hwnd = User32.INSTANCE.FindWindow(targetSoftware, null);
        //hwnd est null si le logiciel n'est pas en cours d'exécution
        if (hwnd != null) 
        {
            //met le logiciel Aux premières plantes dans la position d'origine (SW_RESTORE(9))
            User32.INSTANCE.ShowWindow(hwnd, 9 );
            //donne le focus au logiciel
            User32.INSTANCE.SetForegroundWindow(hwnd);
        }
    }
    
    /**
     * 
     * Retourne le nom du logiciel qui a le focus
     */
    public String getFocusWindows()
    {
        char[] buffer = new char[1024 * 2];
        //récupère un pointeur vers la fenêtre qui a le focus
        HWND hwnd = User32.INSTANCE.GetForegroundWindow();
        //récupère le nom de la class de la fenêtre
        User32.INSTANCE.GetClassName(hwnd, buffer, 1024);
        return Native.toString(buffer);
    }
    
}
