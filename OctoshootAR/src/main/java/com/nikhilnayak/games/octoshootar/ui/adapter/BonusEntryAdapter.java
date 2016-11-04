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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.nikhilnayak.games.octoshootar.model.bonus.Bonus;
import com.nikhilnayak.games.octoshootar.model.bonus.BonusDamage;
import com.nikhilnayak.games.octoshootar.model.bonus.BonusEntry;
import com.nikhilnayak.games.octoshootar.model.bonus.BonusSpeed;
import com.nikhilnayak.games.octoshootar.R;

public class BonusEntryAdapter extends ArrayAdapter<BonusEntry> {
    private BonusEntry[] mBonusEntries;
    private BonusEntry mEquippedBonus;

    public BonusEntryAdapter(Context context, BonusEntry[] bonusEntries) {
        super(context, R.layout.row_bonus_entry, bonusEntries);
        this.mBonusEntries = bonusEntries;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Context context = getContext();
        final BonusEntry currentBonusEntry = mBonusEntries[position];
        final long quantity = currentBonusEntry.getQuantity();
        final Bonus bonus = currentBonusEntry.getBonus();
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final boolean isEquipped = currentBonusEntry.isEquipped();


        View rowView = convertView;
        if (rowView == null) {
            rowView = inflater.inflate(R.layout.row_bonus_entry, parent, false);
        }
        final CheckBox equippedCheckBox = ((CheckBox) rowView.findViewById(R.id.row_bonus_entry_equipped));
        equippedCheckBox.setChecked(isEquipped);

        //display card
        if (isEquipped) {
            rowView.setBackgroundResource(R.drawable.card_shadow_pressed);
        } else {
            rowView.setBackgroundResource(R.drawable.card_shadow_base);
        }


        if (quantity > 0) {
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final boolean afterClickState = !currentBonusEntry.isEquipped();
                    resetEquippedState();
                    if (afterClickState == true) {
                        mEquippedBonus = currentBonusEntry;
                    } else {
                        mEquippedBonus = null;
                    }
                    currentBonusEntry.setIsEquipped(afterClickState);
                    notifyDataSetChanged();
                }
            });
        } else {
            rowView.setBackgroundResource(R.drawable.card_shadow_disable);
            equippedCheckBox.setEnabled(false);
        }

        ((TextView) rowView.findViewById(R.id.row_bonus_entry_title)).setText(context.getResources().getQuantityString(currentBonusEntry.getTitleResourceId(), 1));
        ((TextView) rowView.findViewById(R.id.row_bonus_entry_quantity)).setText("x" + String.valueOf(currentBonusEntry.getQuantity()));
        ((ImageView) rowView.findViewById(R.id.row_bonus_entry_image)).setImageResource(currentBonusEntry.getImageResourceId());

        if (bonus instanceof BonusDamage) {
            ((TextView) rowView.findViewById(R.id.row_bonus_entry_effect)).setText(String.format(
                    context.getString(currentBonusEntry.getEffectResourceId()),
                    String.valueOf(((BonusDamage) bonus).getBonusDamage())));
        } else if (bonus instanceof BonusSpeed) {
            ((TextView) rowView.findViewById(R.id.row_bonus_entry_effect)).setText(String.format(
                    context.getString(currentBonusEntry.getEffectResourceId()),
                    String.valueOf(((BonusSpeed) bonus).getSpeedReduction())));
        }

        return rowView;
    }

    private void resetEquippedState() {
        for (BonusEntry entry : mBonusEntries) {
            entry.setIsEquipped(false);
        }
    }

    public Bonus getEquippedBonus() {
        return mEquippedBonus == null ? new Bonus.DummyBonus() : mEquippedBonus.getBonus();
    }
}
