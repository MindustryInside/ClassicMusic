package classicmusic;

import arc.*;
import arc.audio.*;
import arc.struct.*;
import mindustry.*;
import mindustry.audio.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;

import static mindustry.gen.Musics.*;
import static classicmusic.ClassicSoundtracks.*;

@SuppressWarnings("unused")
public class Launcher extends Mod {
    private Music lastRandom;
    private Seq<Music> soundtracks;

    public Launcher() {
        Events.on(FileTreeInitEvent.class, e -> ClassicSoundtracks.load());
    }

    @Override
    public void init() {
        soundtracks = Seq.with(asFarAsItGets, fairyDust, hotTub, theShortestPath, weJustStarted, whileTrue);

        menu = randomSoundtrack();
        editor = randomSoundtrack();

        game1 = randomSoundtrack();
        game2 = randomSoundtrack();
        game3 = randomSoundtrack();
        game4 = randomSoundtrack();
        game5 = randomSoundtrack();
        game6 = randomSoundtrack();
        game7 = randomSoundtrack();
        game8 = randomSoundtrack();
        game9 = randomSoundtrack();
        boss1 = randomSoundtrack();
        boss2 = randomSoundtrack();

        // for SoundControl#reload
        Vars.control.sound = new SoundControl();
    }

    private Music randomSoundtrack() {
        return lastRandom = soundtracks.random(lastRandom);
    }
}
