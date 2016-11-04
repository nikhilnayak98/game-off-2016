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
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nikhilnayak.games.octoshootar.model.PlayerProfile;
import com.nikhilnayak.games.octoshootar.model.mode.GameMode;

import java.util.ArrayList;

import com.nikhilnayak.games.octoshootar.R;


public class GameModeDetailsAdapter extends ArrayAdapter<GameMode> {

    private ArrayList<GameMode> mGameModes;
    private PlayerProfile mPlayerProfile;
    public Listener mListener;

    public GameModeDetailsAdapter(Context context, ArrayList<GameMode> gameModes, Listener l, PlayerProfile p) {
        super(context, R.layout.row_mode_details_entry, gameModes);
        mListener = l;
        mGameModes = gameModes;
        mPlayerProfile = p;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = getContext();
        final Resources res = context.getResources();
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final GameMode currentGameMode = mGameModes.get(position);
        final String[] grades = res.getStringArray(R.array.ranks_array_full);
        final int currentRank = mPlayerProfile.getRankByGameMode(currentGameMode);


        View rowView = convertView;
        if (rowView == null) {
            rowView = inflater.inflate(R.layout.row_mode_details_entry, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.title = (TextView) rowView.findViewById(R.id.row_game_details_title);
            viewHolder.icon = (ImageView) rowView.findViewById(R.id.row_game_details_icon);
            viewHolder.rank = (TextView) rowView.findViewById(R.id.row_game_details_rank);
            rowView.setTag(viewHolder);
        }


        ViewHolder viewHolder = (ViewHolder) rowView.getTag();
        viewHolder.title.setText(currentGameMode.getTitle());
        viewHolder.icon.setBackgroundResource(currentGameMode.getImage());
        viewHolder.rank.setText(grades[currentRank]);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onGameModeSelected(currentGameMode);
            }
        });
        return rowView;
    }

    private final class ViewHolder {
        public TextView title;
        public ImageView icon;
        public TextView rank;
    }

    public interface Listener {
        public void onGameModeSelected(GameMode gameMode);
    }
}
