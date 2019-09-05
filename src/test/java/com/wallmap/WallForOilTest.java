package com.wallmap;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WallForOilTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getTotalOil() {
        Integer[] input = new Integer[]{0, 1, 1, 1, 0, 1};
        Integer expected = 1;
        Integer actual = new WallForOil(input).getTotalOil();
        assertSame(expected, actual);

        input = new Integer[]{0,1,0,2,1,0,1,3,2,1,2,1};
        expected = 6;
        actual = new WallForOil(input).getTotalOil();
        assertSame(expected, actual);
    }
}