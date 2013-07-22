/*
Copyright (C) 2001, 2009 United States Government as represented by
the Administrator of the National Aeronautics and Space Administration.
All Rights Reserved.
*/

package gov.nasa.worldwind.servers.wms.security;

import gov.nasa.worldwind.util.Logging;

/**
 * @author Lado Garakanidze
 * @version $Id: AllTrustedSecurityManager.java 1 2011-07-16 23:22:47Z dcollins $
 */

public class AllTrustedSecurityManager implements SecurityManager
{
    @Override
    public boolean allow(Object o) throws SecurityException
    {
        return true;
    }
}
