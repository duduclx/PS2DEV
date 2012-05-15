#!/bin/sh

# ps2client.sh

# Go to directory.
cd $BUILDDIR/ps2client
 
# Build and install.
make clean && make && make install && make clean
