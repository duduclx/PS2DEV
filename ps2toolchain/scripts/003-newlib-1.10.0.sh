#!/bin/sh
# newlib-1.10.0.sh by Dan Peori (danpeori@oopo.net)

 ## Enter the source directory and patch the source code.
 cd $BUILDDIR/soft/newlib-1.10.0 && cat ../../patches/newlib-1.10.0-PS2.patch | patch -p1 || { exit 1; }

 ## Create and enter the build directory.
 mkdir build-ee && cd build-ee || { exit 1; }

 ## Configure the build.
 ../configure --prefix="$PS2DEV/ee" --target="ee" || { exit 1; }

 ## Compile and install.
 make clean && CPPFLAGS="-G0" make -j 2 && make install && make clean || { exit 1; }
