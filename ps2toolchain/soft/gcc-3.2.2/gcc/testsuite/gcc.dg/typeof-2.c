/* Test typeof with __asm redirection. */
/* { dg-do compile } */
/* { dg-options "-O2" } */

extern int foo1 (int x) __asm ("baz1");
int bar1 (int x) { return x; }
extern __typeof (bar1) foo1 __attribute ((weak, alias ("bar1")));

extern int foo2 (int x) __attribute__ ((const));
extern __typeof (foo2) foo2 __asm ("baz2");
int bar2 (int x)
{
  return foo2 (x) + foo2 (x) + foo2 (x) + foo2 (x) + foo2 (x) + foo2 (x);
}

extern int foo3 (int x);
extern __typeof (foo3) foo3 __asm ("baz3");
int bar3 (int x)
{
  return foo3 (x) + foo3 (x) + foo3 (x) + foo3 (x) + foo3 (x) + foo3 (x);
}

// { dg-final { scan-assembler-not "foo1" } }
// { dg-final { scan-assembler "baz1" } }
// { dg-final { scan-assembler-not "foo2" } }
// { dg-final { scan-assembler "baz2" } }
// { dg-final { scan-assembler-not "baz2.*baz2.*baz2.*baz2.*baz2.*baz2" } }
// { dg-final { scan-assembler-not "foo3" } }
// { dg-final { scan-assembler "baz3.*baz3.*baz3.*baz3.*baz3.*baz3" } }
