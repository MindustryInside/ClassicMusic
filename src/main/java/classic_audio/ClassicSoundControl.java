package classic_audio;

import arc.*;
import arc.audio.*;
import arc.files.*;
import arc.math.*;
import arc.struct.*;
import mindustry.audio.*;
import mindustry.game.*;

import static mindustry.Vars.*;
import static classic_audio.ClassicSoundtracks.*;

public class ClassicSoundControl extends SoundControl {
    private Seq<Music> classicSoundtracks;

    public ClassicSoundControl() {
        Events.on(EventType.ClientLoadEvent.class, e -> reload());
        setupFilters();
    }

    @Override
    protected void reload() {
        current = null;
        fade = 0f;

        classicSoundtracks = Seq.with(asFarAsItGets, fairyDust, hotTub, theShortestPath, weJustStarted, whileTrue);

        for(var sound : Core.assets.getAll(Sound.class, new Seq<>())){
            var file = Fi.get(Core.assets.getAssetFileName(sound));
            if(file.parent().name().equals("ui")){
                sound.setBus(uiBus);
            }
        }
    }

    @Override
    public void update() {
        boolean paused = state.isGame() && Core.scene.hasDialog();
        boolean playing = state.isGame();

        if (current != null && !current.isPlaying()) {
            current = null;
            fade = 0f;
        }

        if (timer.get(1, 30f)) {
            Core.audio.soundBus.fadeFilterParam(0, Filters.paramWet, paused ? 1f : 0f, 0.4f);
        }

        if (playing != wasPlaying) {
            wasPlaying = playing;

            if (playing) {
                Core.audio.soundBus.play();
                setupFilters();
            } else {
                Core.audio.soundBus.replay();
            }
        }

        if (current != null ){
            current.setVolume(fade * Core.settings.getInt("musicvol") / 100f);
        }

        if (state.isMenu()) {
            silenced = false;
            if (current == null || !current.isPlaying()) {
                playRandom();
            }
        } else {
            silence();

            if (timer.get(musicInterval)) {
                if (Mathf.chance(musicChance)) {
                    playRandom();
                }
            }
        }

        updateLoops();
    }

    @Override
    public void playRandom() {
        playOnce(classicSoundtracks.random());
    }
}
