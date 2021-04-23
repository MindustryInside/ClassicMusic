package classicmusic;

import mindustry.*;
import mindustry.audio.*;
import mindustry.mod.*;

@SuppressWarnings("unused")
public class Launcher extends Mod {
    private final SoundControl original = new SoundControl(),
            classic = new ClassicSoundControl();

    public Launcher() {
        setSoundControl(classic);
    }

    @Override
    public void init() {
        ClassicSoundtracks.load();
        Vars.ui.settings.sound.checkPref("classicmusic", true, (b) -> setSoundControl(b ? classic : original));
    }

    private void setSoundControl(SoundControl control) {
        Vars.control.sound.stop();
        Vars.control.sound = control;
    }
}
