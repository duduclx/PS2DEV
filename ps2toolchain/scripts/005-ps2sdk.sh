#!/bin/sh

# ps2sdk.sh 

# make sure ps2sdk's makefile does not use it
unset PS2SDKSRC

# Go to Directory
cd $BUILDDIR/ps2sdk

## Build and install.
 make clean && make -j 2 && make release && make clean

## Replace newlib's crt0 with the one in ps2sdk.
 ln -sf "$PS2SDK/ee/startup/crt0.o" "$PS2DEV/ee/lib/gcc-lib/ee/3.2.2/crt0.o"
 ln -sf "$PS2SDK/ee/startup/crt0.o" "$PS2DEV/ee/ee/lib/crt0.o"
