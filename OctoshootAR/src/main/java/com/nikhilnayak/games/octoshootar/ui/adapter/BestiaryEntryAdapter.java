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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nikhilnayak.games.octoshootar.model.bestiary.BestiaryEntry;
import com.nikhilnayak.games.octoshootar.R;

public class BestiaryEntryAdapter extends ArrayAdapter<BestiaryEntry> {
    private BestiaryEntry[] mBestiaryEntries;

    public BestiaryEntryAdapter(Context context, BestiaryEntry[] bestiaryEntries) {
        super(context, R.layout.row_bestiary_entry, bestiaryEntries);
        mBestiaryEntries = bestiaryEntries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = getContext();
        final BestiaryEntry currentBestiaryEntry = mBestiaryEntries[position];
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = convertView;

        if (rowView == null) {
            rowView = inflater.inflate(R.layout.row_bestiary_entry, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.entryTitle = (TextView) rowView.findViewById(R.id.row_bestiary_entry_title);
            viewHolder.entryImage = (ImageView) rowView.findViewById(R.id.row_bestiary_entry_image);
            viewHolder.health = (TextView) rowView.findViewById(R.id.bestiary_health);
            viewHolder.point = (TextView) rowView.findViewById(R.id.bestiary_point);
            viewHolder.exp = (TextView) rowView.findViewById(R.id.bestiary_exp);
            rowView.setTag(viewHolder);
        }

        final ViewHolder viewHolder = (ViewHolder) rowView.getTag();
        viewHolder.entryTitle.setText(currentBestiaryEntry.getTitleResourceId());
        viewHolder.entryImage.setImageResource(currentBestiaryEntry.getImageResourceId());
        viewHolder.health.setText(currentBestiaryEntry.getHealth());
        viewHolder.point.setText(currentBestiaryEntry.getPointValue());
        viewHolder.exp.setText(currentBestiaryEntry.getExpValue());

        return rowView;
    }

    private final class ViewHolder {
        public TextView entryTitle;
        public ImageView entryImage;
        public TextView health;
        public TextView point;
        public TextView exp;
    }
}
