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

package com.nikhilnayak.games.octoshootar;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.nikhilnayak.games.octoshootar.ui.InventoryCraftListener;
import com.nikhilnayak.games.octoshootar.ui.dialogfragments.CraftNotEnoughResourcesDialogFragment;
import com.nikhilnayak.games.octoshootar.ui.fragments.InventoryFragment;

import java.util.HashMap;
import java.util.Map;

import com.nikhilnayak.games.octoshootar.model.PlayerProfile;
import com.nikhilnayak.games.octoshootar.model.inventory.InventoryItemEntry;
import com.nikhilnayak.games.octoshootar.model.inventory.InventoryItemInformation;
import com.nikhilnayak.games.octoshootar.model.mode.GameMode;
import com.nikhilnayak.games.octoshootar.ui.dialogfragments.CraftRequestDialogFragment;
import com.nikhilnayak.games.octoshootar.ui.dialogfragments.InventoryItemEntryDetailDialogFragment;
import com.nikhilnayak.games.octoshootar.ui.fragments.BestiaryFragment;
import com.nikhilnayak.games.octoshootar.ui.fragments.GameModeDetailsFragment;
import com.nikhilnayak.games.octoshootar.ui.fragments.GameModeFragment;
import com.nikhilnayak.games.octoshootar.ui.fragments.ProfileFragment;

public class ProfileActivity extends FragmentActivity implements ProfileFragment.Listener, InventoryFragment.Listener,
        CraftRequestDialogFragment.Listener, InventoryCraftListener, GameModeDetailsFragment.Listener {

    private Toast mTextToast;
    private PlayerProfile mPlayerProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mPlayerProfile = new PlayerProfile(getSharedPreferences(PlayerProfile.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE));

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.profile_fragment_container,
                    new ProfileFragment(), null).commit();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        hideToast();
    }

    @Override
    public void onNotAvailableFeatureRequested() {
        makeToast(getResources().getString(R.string.soon_tm));
    }

    @Override
    public void onBestiaryRequested() {
        getSupportFragmentManager().beginTransaction().replace(R.id.profile_fragment_container,
                new BestiaryFragment()).addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
    }

    @Override
    public void onInventoryRequested() {
        getSupportFragmentManager().beginTransaction().replace(R.id.profile_fragment_container,
                new InventoryFragment(), InventoryFragment.TAG).addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
    }

    @Override
    public void onMissionRequested() {
        getSupportFragmentManager().beginTransaction().replace(R.id.profile_fragment_container,
                new GameModeDetailsFragment(), GameModeDetailsFragment.TAG).addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
    }

    /**
     * use to inform user
     *
     * @param message display on screen
     */
    private void makeToast(String message) {
        if (mTextToast != null) {
            mTextToast.cancel();
        }
        mTextToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        mTextToast.show();
    }

    private void hideToast() {
        if (mTextToast != null) {
            mTextToast.cancel();
            mTextToast = null;
        }
    }


    @Override
    public void onCraftRequested(InventoryItemEntry inventoryItemEntry) {
        final HashMap<Integer, Integer> missingResources = inventoryItemEntry.getRecipe().getMissingResources(mPlayerProfile);
        if (missingResources.size() == 0) {
            CraftRequestDialogFragment.newInstance(inventoryItemEntry).show(getSupportFragmentManager(), null);
        } else {
            String missingResourcesString = "";
            for (Map.Entry<Integer, Integer> entry : missingResources.entrySet()) {
                final int quantityMissing = entry.getValue();
                final int nameResourceId = entry.getKey();
                missingResourcesString += String.valueOf(quantityMissing) + "x " + getResources().getQuantityString(nameResourceId, quantityMissing) + ", ";
            }
            missingResourcesString = missingResourcesString.substring(0, missingResourcesString.length() - 2);
            CraftNotEnoughResourcesDialogFragment.newInstance(missingResourcesString).show(getSupportFragmentManager(), null);
        }

    }

    @Override
    public void onInventoryItemEntryDetailRequest(InventoryItemEntry inventoryItemEntry) {
        InventoryItemEntryDetailDialogFragment.newInstance(inventoryItemEntry).show(getSupportFragmentManager(),
                InventoryItemEntryDetailDialogFragment.TAG);
    }

    @Override
    public void onCraftValidated(InventoryItemEntry inventoryItemEntry) {
        for (Map.Entry<InventoryItemInformation, Integer> entry : inventoryItemEntry.getRecipe().getIngredientsAndQuantities().entrySet()) {
            mPlayerProfile.decreaseInventoryItemQuantity(entry.getKey().getType(), entry.getValue());
        }
        long newQuantity = mPlayerProfile.increaseInventoryItemQuantity(inventoryItemEntry.getType());
        boolean areChangesSaved = mPlayerProfile.saveChanges();
        final InventoryFragment inventoryFragment = (InventoryFragment) getSupportFragmentManager().findFragmentByTag(InventoryFragment.TAG);
        final InventoryItemEntryDetailDialogFragment inventoryDialogFragment = (InventoryItemEntryDetailDialogFragment)
                getSupportFragmentManager().findFragmentByTag(InventoryItemEntryDetailDialogFragment.TAG);
        if (areChangesSaved) {
            inventoryItemEntry.setQuantityAvailable(newQuantity);
            if (inventoryFragment != null) inventoryFragment.loadInformation();
            if (inventoryDialogFragment != null)
                inventoryDialogFragment.udpateInventoryItemEntry(inventoryItemEntry);
        }
    }

    @Override
    public void onGameModeDetailsRequest(GameMode gameMode) {
        getSupportFragmentManager().beginTransaction().replace(R.id.profile_fragment_container,
                GameModeFragment.newInstance(gameMode), GameModeFragment.TAG).addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
    }
}
