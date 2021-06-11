Bot_keyboard est un robot qui envoie aux claviers les touches qui lui sont passés par un fichier JSON

Fonctionnalité : 
-------------------
Envoie aux claviers la touche ou la combinaison de touches en fonction d'un temps en ms

Plusieurs hotkey :
-	F9 : redonne le focus au logiciel cibler
-	F10 : Play/pause le robot (après la prochaine entrée au clavier)
-	F11 : ferme le bot

La vérification en cours d’utilisation du focus sur le logiciel cible

Comment utiliser Bot_keyboard :
-------------------
Placer la DLL (JIntellitype.dll) et le dossier lib dans le même dossier que le jar du Bot_keyboard

Avec la console système taper la commande suivante

``` java
java -jar Bot_keyboard.jar  [logiciel cible (le nom de la Class)] [le lien du fichier JSON]
```

Syntaxes du fichier json :
-------------------
La première valeur est toujours le temps d’attente après l’exécution de la touche

La seconde valeur est la touche ou le combo de touche
``` Json
{
  "key1" : [10, "a"],
  "key2" : [10, "SHIFT", "a"]
}
```
Les touches configurées : 
-------------------
a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z

0..9/0..9_NUMPAD

` - = ! @ # $ ^ & * ( ) _ + [ ] \ ; : ' " , . /

SPACE, TAB, ENTER, ALT, CTRL, SHIFT


