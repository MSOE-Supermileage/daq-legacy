#ifndef OGL_UTIL_H
#define OGL_UTIL_H

/*
Copyright (C) 2001, 2010 United States Government as represented by
the Administrator of the National Aeronautics and Space Administration.
All Rights Reserved.
*/
#import <Cocoa/Cocoa.h>
#import <OpenGL/gl.h>

/*
    Version $Id: OGLUtil.h 1 2011-07-16 23:22:47Z dcollins $
 */

extern void loadBitmapInGLTexture(GLenum target, NSBitmapImageRep *bitmap);

#endif /* OGL_UTIL_H */