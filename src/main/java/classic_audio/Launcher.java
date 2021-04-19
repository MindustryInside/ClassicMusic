package classic_audio;

import mindustry.mod.*;

import static mindustry.Vars.*;

@SuppressWarnings("unused")
public class Launcher extends Mod {
    public Launcher() {
        control.sound = new ClassicSoundControl();
    }

    @Override
    public void init() {
        ClassicSoundtracks.load();
    }
}
