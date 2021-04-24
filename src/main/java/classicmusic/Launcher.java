package classicmusic;

import arc.*;
import arc.func.*;
import mindustry.*;
import mindustry.audio.*;
import mindustry.mod.*;

@SuppressWarnings("unused")
public class Launcher extends Mod {
    private boolean enabled = Core.settings.getBool("classicmusic", true);
    private SoundControl original = new SoundControl(), classic = new ClassicSoundControl();
    private Boolc changed = (e) -> setSoundControl(e ? classic : original);

    public Launcher() {
        changed.get(enabled);
    }

    @Override
    public void init() {
        ClassicSoundtracks.load();
        Vars.ui.settings.sound.checkPref("classicmusic", enabled, changed);
    }

    private void setSoundControl(SoundControl control) {
        Vars.control.sound.stop();
        Vars.control.sound = control;
    }
}
