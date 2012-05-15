#!/bin/sh
# newlib-1.10.0.sh by Dan Peori (danpeori@oopo.net)

 ## Download the source code.
 SOURCE=https://github.com/downloads/ps2dev/ps2toolchain/newlib-1.10.0.tar.gz
 wget --continue --no-check-certificate $SOURCE || { exit 1; }

 ## Unpack the source code.
 rm -Rf newlib-1.10.0 && tar xfvz newlib-1.10.0.tar.gz || { exit 1; }

 ## Enter the source directory and patch the source code.
 cd newlib-1.10.0 && cat ../../patches/newlib-1.10.0-PS2.patch | patch -p1 || { exit 1; }

 ## Create and enter the build directory.
 mkdir build-ee && cd build-ee || { exit 1; }

 ## Configure the build.
 ../configure --prefix="$PS2DEV/ee" --target="ee" || { exit 1; }

 ## Compile and install.
 make clean && CPPFLAGS="-G0" make -j 2 && make install && make clean || { exit 1; }
