/*
 * Copyright (C) 2011 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration.
 * All Rights Reserved.
 */
package gov.nasa.worldwind.formats.vpf;

import java.util.Collection;

/**
 * @author dcollins
 * @version $Id: VPFSymbolFactory.java 1 2011-07-16 23:22:47Z dcollins $
 */
public interface VPFSymbolFactory
{
    Collection<? extends VPFSymbol> createPointSymbols(VPFFeatureClass featureClass);

    Collection<? extends VPFSymbol> createLineSymbols(VPFFeatureClass featureClass);

    Collection<? extends VPFSymbol> createAreaSymbols(VPFFeatureClass featureClass);

    Collection<? extends VPFSymbol> createTextSymbols(VPFFeatureClass featureClass);

    Collection<? extends VPFSymbol> createComplexSymbols(VPFFeatureClass featureClass);
}
