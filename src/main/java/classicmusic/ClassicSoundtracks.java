package classicmusic;

import arc.*;
import arc.audio.*;
import mindustry.*;

// TODO: Auto generation
public class ClassicSoundtracks {
    public static Music asFarAsItGets;
    public static Music fairyDust;
    public static Music hotTub;
    public static Music theShortestPath;
    public static Music weJustStarted;
    public static Music whileTrue;

    public static void load() {
        asFarAsItGets = newMusic("As-far-as-it-Gets");
        fairyDust = newMusic("Fairy-Dust");
        hotTub = newMusic("Hot-Tub");
        theShortestPath = newMusic("The-shortest-Path");
        weJustStarted = newMusic("We-just-Started");
        whileTrue = newMusic("While(true)");
    }

    private static Music newMusic(String name) {
        return Core.audio.newMusic(Vars.tree.get("soundtracks/" + name + ".mp3"));
    }
}
