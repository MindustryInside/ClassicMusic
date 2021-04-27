package classicmusic;

import arc.*;
import mindustry.*;
import mindustry.audio.*;
import mindustry.mod.*;

@SuppressWarnings("unused")
public class Launcher extends Mod {
    private final boolean enabled = Core.settings.getBool("classicmusic", true);

    @Override
    public void init() {
        ClassicSoundtracks.load();
        Vars.ui.settings.sound.checkPref("classicmusic", enabled, this::setSoundControl);

        setSoundControl(enabled);
    }

    private void setSoundControl(boolean classic) {
        Vars.control.sound.stop();
        Vars.control.sound = classic ? new ClassicSoundControl() : new SoundControl();
    }
}
