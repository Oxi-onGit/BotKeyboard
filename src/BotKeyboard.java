/**
 * @author Oxi-onGit
 */
public class BotKeyboard 
{
    /**
     * verifie les args et lance le thread
     * 
     * @param args 
     */
    
    public static void main(String[] args) 
    {   
        FileManagement file =new FileManagement();
        // test la validité des arguments
        if (args.length != 2 || !file.fileIsExist(args[1])) 
        {
            System.out.println("usage: BotKeyboard [target software (Class Name)] [file link]\n"+
                                "f9  : restore focus to the target software\n"+
                                "f10 : play/pause bot (after the next keyboard entry)\n"+
                                "f11 : stop bot\n\n"+
                                "File syntax:\n" +
                                "{\n" +
                                "     \"key1\" : [\"10\", \"a\"],\n" +
                                "     \"key2\" : [\"10\", \"SHIFT\", \"a\"]\n" +
                                "}\n\n"+
                                "key set\n"+
                                "a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z\n"+
                                "0..9/0..9_NUMPAD\n"+
                                "` - = ! @ # $ ^ & * ( ) _ + [ ] \\ ; : ' \" , . /\n"+
                                "SPACE, TAB, ENTER, ALT, CTRL, SHIFT");
        }else 
        {
           ThreadControle threadControle = new ThreadControle(args[0],args[1]);
           threadControle.start();
        }
    } 
}
