package classicmusic;

import arc.*;
import arc.audio.*;
import arc.math.*;
import arc.struct.*;
import mindustry.audio.*;

import static mindustry.Vars.*;
import static classicmusic.ClassicSoundtracks.*;

public class ClassicSoundControl extends SoundControl {
    private final Seq<Music> classicSoundtracks = Seq.with(
        asFarAsItGets, fairyDust, hotTub, theShortestPath, weJustStarted, whileTrue
    );

    public ClassicSoundControl() {
        setupFilters();
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

        if (current != null) {
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
