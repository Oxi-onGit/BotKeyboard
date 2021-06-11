Bot_keyboard is a robot that sends to keyboards the keys that are passed to it by a JSON file

[Version FR du README](https://github.com/Oxi-onGit/BotKeyboard/blob/master/readmeFr.md)

Functionality :
-------------------
Sends the key or key combination to the keyboards according to a time in ms
Several hotkeys :
-	F9 : give the focus back to the target software
-	F10 : Play/pause the bot (after the next keyboard input)
-	F11 : close the bot

In-use verification of the focus on the target software

How to use Bot_keyboard :
-------------------
Place the DLL (JIntellitype.dll) and the lib folder in the same folder as the Bot_keyboard jar

With the system console type the following command 	
``` java
java -jar Bot_keyboard.jar  [target software (Class Name)] [file link]
``` 
Syntaxes of the json file :
-------------------
The first value is always the waiting time after the execution of the

The second value is the key or key combo
``` Json
{
  "key1" : [10, "a"],
  "key2" : [10, "SHIFT", "a"]
}
``` 
The configured keys :
-------------------
a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z

0..9/0..9_NUMPAD

` - = ! @ # $ ^ & * ( ) _ + [ ] \ ; : ' " , . /

SPACE, TAB, ENTER, ALT, CTRL, SHIFT
