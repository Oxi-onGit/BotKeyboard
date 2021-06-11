import java.io.File;

/**
 *
 * @author Oxi-onGit
 */
public class FileManagement 
{
     /**
     * return true si les path est un fichier et false si non
     * 
     * @param pathFile path du fichier
     * @return boolean 
     */
    public boolean fileIsExist(String pathFile) 
    {
        return new File(pathFile).exists();
    }
}
