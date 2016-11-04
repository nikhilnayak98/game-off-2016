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

package com.nikhilnayak.games.octoshootar.model.bestiary;


import com.nikhilnayak.games.octoshootar.R;
import com.nikhilnayak.games.octoshootar.model.DisplayableItemFactory;

public class BestiaryEntryFactory {

    public static BestiaryEntry createBestiaryEntry(int ghostType) {
        BestiaryEntry bestiaryEntry = new BestiaryEntry();
        switch (ghostType) {
            case DisplayableItemFactory.TYPE_GHOST_WITH_HELMET:
                bestiaryEntry.setTargetableItem(DisplayableItemFactory.createGhostWithHelmet());
                bestiaryEntry.setImageResourceId(R.drawable.ghost_with_helmet_5);
                bestiaryEntry.setTitleResourceId(R.string.bestiary_ghost_with_helmet_title);
                break;
            case DisplayableItemFactory.TYPE_BABY_GHOST:
                bestiaryEntry.setTargetableItem(DisplayableItemFactory.createBabyGhost());
                bestiaryEntry.setImageResourceId(R.drawable.baby_ghost);
                bestiaryEntry.setTitleResourceId(R.string.bestiary_baby_ghost_title);
                break;

            case DisplayableItemFactory.TYPE_HIDDEN_GHOST:
                bestiaryEntry.setTargetableItem(DisplayableItemFactory.createHiddenGhost());
                bestiaryEntry.setImageResourceId(R.drawable.hidden_ghost);
                bestiaryEntry.setTitleResourceId(R.string.bestiary_hidden_ghost_title);
                break;

            case DisplayableItemFactory.TYPE_KING_GHOST:
                bestiaryEntry.setTargetableItem(DisplayableItemFactory.createKingGhost());
                bestiaryEntry.setImageResourceId(R.drawable.king_ghost);
                bestiaryEntry.setTitleResourceId(R.string.bestiary_king_ghost_title);
                break;

            case DisplayableItemFactory.TYPE_BLOND_GHOST:
                bestiaryEntry.setTargetableItem(DisplayableItemFactory.createBlondGhost());
                bestiaryEntry.setImageResourceId(R.drawable.blond_ghost_in_tears);
                bestiaryEntry.setTitleResourceId(R.string.bestiary_blond_ghost_title);
                break;

            default:
                bestiaryEntry.setTargetableItem(DisplayableItemFactory.createEasyGhost());
                bestiaryEntry.setImageResourceId(R.drawable.ghost);
                bestiaryEntry.setTitleResourceId(R.string.bestiary_easy_ghost_title);
                break;
        }
        return bestiaryEntry;
    }
}
