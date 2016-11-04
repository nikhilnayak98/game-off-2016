/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016. Nikhil Nayak <nikhilnayak98@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.nikhilnayak.games.octoshootar.model.mode;


import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import com.nikhilnayak.games.octoshootar.mechanics.informations.GameInformationTime;
import com.nikhilnayak.games.octoshootar.R;
import com.nikhilnayak.games.octoshootar.mechanics.informations.GameInformation;
import com.nikhilnayak.games.octoshootar.model.PlayerProfile;

public class GameModeDeathToTheKing extends GameMode {

    private static final int RANK_LIMIT_ADMIRAL = 1500;
    private static final int RANK_LIMIT_SERGEANT = 2000;
    private static final int RANK_LIMIT_CORPORAL = 4000;
    private static final int RANK_LIMIT_SOLDIER = 6000;

    public GameModeDeathToTheKing() {
        super();
    }

    protected GameModeDeathToTheKing(Parcel in) {
        super(in);
    }

    @Override
    public boolean isAvailable(PlayerProfile p) {
        return p.getRankByGameMode(GameModeFactory.createMemorize(0)) >= GameModeFactory.GAME_RANK_CORPORAL;
    }

    @Override
    public int getRank(GameInformation gameInformation) {
        return processRank((GameInformationTime) gameInformation);
    }

    public static final Parcelable.Creator<GameModeDeathToTheKing> CREATOR = new Parcelable.Creator<GameModeDeathToTheKing>() {
        public GameModeDeathToTheKing createFromParcel(Parcel in) {
            return new GameModeDeathToTheKing(in);
        }

        public GameModeDeathToTheKing[] newArray(int size) {
            return new GameModeDeathToTheKing[size];
        }
    };

    protected int processRank(GameInformationTime g) {
        final long score = g.getPlayingTime();
        if (score < RANK_LIMIT_ADMIRAL) {
            return GameModeFactory.GAME_RANK_ADMIRAL;
        } else if (score < RANK_LIMIT_SERGEANT) {
            return GameModeFactory.GAME_RANK_SERGEANT;
        } else if (score < RANK_LIMIT_CORPORAL) {
            return GameModeFactory.GAME_RANK_CORPORAL;
        } else if (score < RANK_LIMIT_SOLDIER) {
            return GameModeFactory.GAME_RANK_SOLDIER;
        } else {
            return GameModeFactory.GAME_RANK_DESERTER;
        }
    }

    @Override
    public String getAdmiralRankRule(Resources res) {
        return String.format(res.getString(R.string.game_mode_rank_rules_death_to_the_king), 1.5);
    }

    @Override
    public String getSergeantRankRule(Resources res) {
        return String.format(res.getString(R.string.game_mode_rank_rules_death_to_the_king), 2.0);
    }

    @Override
    public String getCorporalRankRule(Resources res) {
        return String.format(res.getString(R.string.game_mode_rank_rules_death_to_the_king), 4.0);
    }

    @Override
    public String getSoldierRankRule(Resources res) {
        return String.format(res.getString(R.string.game_mode_rank_rules_death_to_the_king), 6.0);
    }

    @Override
    public String getDeserterRankRule(Resources res) {
        return res.getString(R.string.game_mode_rank_rules_death_to_the_king_deserter);
    }
}

