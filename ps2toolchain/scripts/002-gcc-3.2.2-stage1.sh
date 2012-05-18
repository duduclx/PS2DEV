#!/bin/sh
# gcc-3.2.2-stage1.sh by Dan Peori (danpeori@oopo.net)

 ## Enter the source directory and patch the source code.
 cd $BUILDDIR/soft/gcc-3.2.2 && cat ../../patches/gcc-3.2.2-PS2.patch | patch -p1 || { exit 1; }

 ## For each target...
 for TARGET in "ee" "iop"; do

  ## Create and enter the build directory.
  mkdir "build-$TARGET-stage1" && cd "build-$TARGET-stage1" || { exit 1; }

  ## Configure the build.
  ../configure --prefix="$PS2DEV/$TARGET" --target="$TARGET" --enable-languages="c" --with-newlib --without-headers || { exit 1; }

  ## Compile and install.
  make clean && make -j 2 && make install && make clean || { exit 1; }

  ## Exit the build directory.
  cd .. || { exit 1; }

 ## End target.
 done
