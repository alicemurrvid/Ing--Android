package com.example.robotarmh25_remote;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.robotarmh25_remote.ui.connect.ConnectFragment;

import org.junit.Test;

public class AdressMacTest {

    /**
     * Check that the mac address is considered invalid if given an invalid mac address
     */
    @Test
    public void testMacAddressInvalid() {

        ConnectFragment connectFragment = new ConnectFragment();
        assertFalse(connectFragment.macAddressValid("00:11:22:33:44"));
    }


    /**
     * Verifies that the mac address is indeed considered valid if it is given a valid Mac address
     */
    @Test
    public void testMacAddressValid() {

        ConnectFragment connectFragment = new ConnectFragment();
        assertTrue(connectFragment.macAddressValid("00:11:22:33:44:55"));
    }
}
