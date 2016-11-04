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

package com.nikhilnayak.games.octoshootar.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.nikhilnayak.games.octoshootar.model.PlayerProfile;
import com.nikhilnayak.games.octoshootar.model.mode.GameMode;
import com.nikhilnayak.games.octoshootar.ui.customviews.GameModeView;

import java.util.ArrayList;

import com.nikhilnayak.games.octoshootar.R;


public class GameModeViewAdapter extends ArrayAdapter<GameMode> {

    private ArrayList<GameMode> mGameModes;
    private PlayerProfile mPlayerProfile;
    public Listener mListener;


    public GameModeViewAdapter(Context context, ArrayList<GameMode> gameModes, PlayerProfile p, Listener l) {
        super(context, R.layout.view_game_mode, gameModes);
        mGameModes = gameModes;
        mPlayerProfile = p;
        mListener = l;
    }

    public GameModeViewAdapter(Context context, ArrayList<GameMode> gameModes, Listener l) {
        super(context, R.layout.view_game_mode, gameModes);
        mGameModes = gameModes;
        mPlayerProfile = null;
        mListener = l;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final GameMode currentGameMode = mGameModes.get(position);
        GameModeView rowView = (GameModeView) convertView;

        if (rowView == null) {
            rowView = new GameModeView(getContext());
        }

        if (mPlayerProfile == null) {
            rowView.setModelForLeaderboard(currentGameMode);
        } else {
            rowView.setModel(currentGameMode);
            rowView.setGameModeEnabled(currentGameMode.isAvailable(mPlayerProfile));
        }
        rowView.setGameModeSelectedListener(mListener);

        return rowView;

    }

    public interface Listener {
        public void onGameModeSelected(GameModeView view);
    }
}
