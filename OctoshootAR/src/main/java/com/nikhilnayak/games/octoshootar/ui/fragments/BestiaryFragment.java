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

package com.nikhilnayak.games.octoshootar.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.nikhilnayak.games.octoshootar.model.DisplayableItemFactory;
import com.nikhilnayak.games.octoshootar.model.bestiary.BestiaryEntry;
import com.nikhilnayak.games.octoshootar.model.bestiary.BestiaryEntryFactory;
import com.nikhilnayak.games.octoshootar.ui.adapter.BestiaryEntryAdapter;
import com.nikhilnayak.games.octoshootar.R;

public class BestiaryFragment extends Fragment {
    private GridView mBestiaryGridView;


    /**
     * Default Constructor.
     * <p/>
     * lint [ValidFragment]
     * http://developer.android.com/reference/android/app/Fragment.html#Fragment()
     * Every fragment must have an empty constructor, so it can be instantiated when restoring its activity's state.
     */
    public BestiaryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bestiary, container, false);
        mBestiaryGridView = ((GridView) v.findViewById(R.id.bestiary_grid_view));
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBestiaryGridView.setAdapter(new BestiaryEntryAdapter(getActivity(), new BestiaryEntry[]{
                BestiaryEntryFactory.createBestiaryEntry(DisplayableItemFactory.TYPE_EASY_GHOST),
                BestiaryEntryFactory.createBestiaryEntry(DisplayableItemFactory.TYPE_BLOND_GHOST),
                BestiaryEntryFactory.createBestiaryEntry(DisplayableItemFactory.TYPE_BABY_GHOST),
                BestiaryEntryFactory.createBestiaryEntry(DisplayableItemFactory.TYPE_GHOST_WITH_HELMET),
                BestiaryEntryFactory.createBestiaryEntry(DisplayableItemFactory.TYPE_HIDDEN_GHOST),
                BestiaryEntryFactory.createBestiaryEntry(DisplayableItemFactory.TYPE_KING_GHOST)

        }));
    }
}
