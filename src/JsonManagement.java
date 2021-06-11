import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Oxi-onGit
 */
public class JsonManagement 
{
    //init log4j
    private static final Logger logger = LogManager.getLogger(JsonManagement.class);
    
    /**
     * 
     * lie le fichier en json et retourne une map contenant les données
     * 
     * @param input_File
     * @return : la map qui représente les données du fichier en json
     */
    public Map<?,?> readJson(String input_File)
    {
        try 
        {
            Gson gson = new Gson();

            //crée un reader
            Reader reader = Files.newBufferedReader(Paths.get(input_File));

            //convertie les donner du fichier json en map
            Map<?,?> mapKey = gson.fromJson(reader, Map.class);
            
            //ferme le reader
            reader.close();
            
            return mapKey;
        } catch (JsonIOException | JsonSyntaxException | IOException ex) 
        {
            logger.error("erreur Json "+ex );
            return null;
        }
    }
    
}
