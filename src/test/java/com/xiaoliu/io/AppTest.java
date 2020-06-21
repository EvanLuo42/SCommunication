/*
 * Copyright (C) 2013 Fabien Vauchelles (fabien_AT_vauchelles_DOT_com).
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3, 29 June 2007, of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */

package com.xiaoliu.io;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import static org.testng.AssertJUnit.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 *
 * @author Fabien Vauchelles (fabien_AT_vauchelles_DOT_com)
 */
public final class AppTest
{
    // PUBLIC
    public AppTest()
    {
        // Nothing
    }

    /**
     * Initialize the test.
     *
     * @throws ConfigurationException
     */
    @BeforeClass
    public void setUp()
        throws ConfigurationException
    {
        // My config
        final XMLConfiguration config = new XMLConfiguration( "conf-local/configuration.xml" );
    }

    /**
     * Test always true.
     */
    @Test
    public void testApp()
    {
        assertEquals( "Always true !" ,
                      1 ,
                      1 );
    }
    // PRIVATE
}
