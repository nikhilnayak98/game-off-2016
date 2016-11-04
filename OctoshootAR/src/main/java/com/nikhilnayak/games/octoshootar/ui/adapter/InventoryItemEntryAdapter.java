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

import com.nikhilnayak.games.octoshootar.ui.InventoryCraftListener;

import java.util.ArrayList;

import com.nikhilnayak.games.octoshootar.R;
import com.nikhilnayak.games.octoshootar.model.PlayerProfile;
import com.nikhilnayak.games.octoshootar.model.inventory.InventoryItemEntry;
import com.nikhilnayak.games.octoshootar.ui.customviews.InventoryItemEntryView;

public class InventoryItemEntryAdapter extends ArrayAdapter<InventoryItemEntry> {

    private ArrayList<InventoryItemEntry> mInventoryItemyEntries;
    private InventoryItemEntryView.Listener mDetailRequestListener;
    private InventoryCraftListener mCraftRequestListener;
    private PlayerProfile mPlayerProfile;

    public InventoryItemEntryAdapter(Context context, ArrayList<InventoryItemEntry> inventoryItemyEntries,
                                     InventoryItemEntryView.Listener detailListener,
                                     InventoryCraftListener craftListener, PlayerProfile playerProfile) {
        super(context, R.layout.view_inventory_item_entry, inventoryItemyEntries);
        mInventoryItemyEntries = inventoryItemyEntries;
        mDetailRequestListener = detailListener;
        mCraftRequestListener = craftListener;
        mPlayerProfile = playerProfile;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = getContext();
        final InventoryItemEntry currentInventoryItemEntry = mInventoryItemyEntries.get(position);
        InventoryItemEntryView rowView = (InventoryItemEntryView) convertView;

        if (rowView == null) {
            rowView = new InventoryItemEntryView(context);
        }

        rowView.setModel(currentInventoryItemEntry);
        rowView.setDetailRequestListener(mDetailRequestListener);
        rowView.setCraftRequestListener(mCraftRequestListener);
        if (currentInventoryItemEntry.getRecipe().getMissingResources(mPlayerProfile).size() != 0) {
            rowView.setCraftEnable(false);
        } else {
            rowView.setCraftEnable(true);
        }
        return rowView;
    }
}