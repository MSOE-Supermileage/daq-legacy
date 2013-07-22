/* Copyright (C) 2001, 2007 United States Government as represented by
   the Administrator of the National Aeronautics and Space Administration.
   All Rights Reserved.
*/
package gov.nasa.worldwind.servers.wms;

/**
 * @author brownrigg
 * @version $Id: WMSServiceException.java 1 2011-07-16 23:22:47Z dcollins $
 */
public class WMSServiceException extends Exception
{

    public WMSServiceException(String err)
    {
        super(err);
    }

    public WMSServiceException(java.lang.Throwable throwable)
    {
        super(throwable);
    }
}
