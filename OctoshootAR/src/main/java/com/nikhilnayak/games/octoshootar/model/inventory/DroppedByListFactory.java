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

package com.nikhilnayak.games.octoshootar.model.inventory;

import com.nikhilnayak.games.octoshootar.R;

public class DroppedByListFactory {
    public static DroppedByList create(int inventoryItemType) {
        DroppedByList droppedByList = new DroppedByList();
        switch (inventoryItemType) {
            case InventoryItemInformation.TYPE_BABY_DROOL:
                droppedByList.addMonster(R.string.bestiary_baby_ghost_title, DroppedByList.DROP_RATE_BABY_DROOL);
                break;

            case InventoryItemInformation.TYPE_BROKEN_HELMET_HORN:
                droppedByList.addMonster(R.string.bestiary_ghost_with_helmet_title, DroppedByList.DROP_RATE_BROKEN_HELMET_HORN);
                break;

            case InventoryItemInformation.TYPE_COIN:
                droppedByList.addMonster(R.string.bestiary_easy_ghost_title, DroppedByList.DROP_RATE_COIN);
                droppedByList.addMonster(R.string.bestiary_hidden_ghost_title, DroppedByList.DROP_RATE_COIN);
                droppedByList.addMonster(R.string.bestiary_baby_ghost_title, DroppedByList.DROP_RATE_COIN * 2);
                droppedByList.addMonster(R.string.bestiary_ghost_with_helmet_title, DroppedByList.DROP_RATE_COIN * 4);
                droppedByList.addMonster(R.string.bestiary_king_ghost_title, DroppedByList.DROP_RATE_COIN * 10);
                break;

            case InventoryItemInformation.TYPE_KING_CROWN:
                droppedByList.addMonster(R.string.bestiary_king_ghost_title, DroppedByList.DROP_RATE_KING_CROWN);
                break;

            case InventoryItemInformation.TYPE_GHOST_TEAR:
                droppedByList.addMonster(R.string.bestiary_blond_ghost_title, DroppedByList.DROP_RATE_GHOST_TEAR);
                break;
        }
        return droppedByList;
    }
}
