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

package com.nikhilnayak.games.octoshootar.ui.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nikhilnayak.games.octoshootar.ui.InventoryCraftListener;
import com.nikhilnayak.games.octoshootar.R;
import com.nikhilnayak.games.octoshootar.model.inventory.InventoryItemEntry;

public class InventoryItemEntryView extends RelativeLayout {

    private Context mContext;
    private InventoryItemEntry mModel;
    private TextView mTitle;
    private TextView mQuantity;
    private ImageButton mCraftButton;
    private ImageView mItemImage;

    public InventoryItemEntryView(Context context) {
        this(context, null);
    }

    public InventoryItemEntryView(Context context, AttributeSet attr) {
        super(context, attr);
        mContext = context;

        final int halfPadding = context.getResources().getDimensionPixelSize(R.dimen.half_padding);
        setBackgroundResource(R.drawable.card_shadow);
        setPadding(halfPadding, halfPadding, halfPadding, halfPadding);
        setClickable(true);

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_inventory_item_entry, this, true);

        mTitle = (TextView) findViewById(R.id.view_inventory_item_entry_title);
        mQuantity = (TextView) findViewById(R.id.view_inventory_item_entry_quantity);
        mCraftButton = (ImageButton) findViewById(R.id.view_inventory_item_entry_craft_action);
        mItemImage = (ImageView) findViewById(R.id.view_inventory_item_entry_item_image);
    }

    public void setModel(final InventoryItemEntry model) {
        mModel = model;
        final long quantityAvailable = mModel.getQuantityAvailable();
        final int titleResourceId = mModel.getTitleResourceId();
        final int imageResourceId = mModel.getImageResourceId();
        mTitle.setText(mContext.getResources().getQuantityText(titleResourceId, 1));
        mQuantity.setText("x" + String.valueOf(quantityAvailable));
        mItemImage.setImageResource(imageResourceId);
        if (mModel.getRecipe().getIngredientsAndQuantities().size() == 0) {
            mCraftButton.setEnabled(false);
        }
    }

    public void setCraftEnable(boolean isEnable) {
        mCraftButton.setEnabled(isEnable);
    }

    public void setDetailRequestListener(final Listener listener) {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onInventoryItemEntryDetailRequest(mModel);
            }
        });
    }

    public void setCraftRequestListener(final InventoryCraftListener listener) {
        mCraftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCraftRequested(mModel);
            }
        });
    }

    public interface Listener {
        public void onInventoryItemEntryDetailRequest(InventoryItemEntry inventoryItemEntry);
    }
}
