#!/bin/sh
# gcc-3.2.2-stage1.sh by Dan Peori (danpeori@oopo.net)

 ## Enter the source directory and patch the source code.
 cd $BUILDDIR/soft/gcc-3.2.2 && cat ../../patches/gcc-3.2.2-PS2.patch | patch -p1 || { exit 1; }

 ## Create and enter the build directory.
 mkdir build-ee-stage2 && cd build-ee-stage2 || { exit 1; }

 ## Configure the build.
 ../configure --prefix="$PS2DEV/ee" --target="ee" --enable-languages="c,c++" --with-newlib --with-headers="$PS2DEV/ee/ee/include" --enable-cxx-flags="-G0" || { exit 1; }

 ## Compile and install.
 make clean && CFLAGS_FOR_TARGET="-G0" make -j 2 && make install && make clean || { exit 1; }
