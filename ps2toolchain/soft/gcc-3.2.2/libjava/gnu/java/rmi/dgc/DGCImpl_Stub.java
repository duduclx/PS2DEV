// Stub class generated by rmic - DO NOT EDIT!

package gnu.java.rmi.dgc;

public final class DGCImpl_Stub
    extends java.rmi.server.RemoteStub
    implements java.rmi.dgc.DGC
{
    private static final long serialVersionUID = 2L;
    
    private static final long interfaceHash = -669196253586618813L;
    
    private static boolean useNewInvoke;
    
    private static final java.rmi.server.Operation[] operations = {
        new java.rmi.server.Operation("void clean(java.rmi.server.ObjID[], long, java.rmi.dgc.VMID, boolean)"),
        new java.rmi.server.Operation("java.rmi.dgc.Lease dirty(java.rmi.server.ObjID[], long, java.rmi.dgc.Lease)")
    };
    
    private static java.lang.reflect.Method $method_clean_0;
    private static java.lang.reflect.Method $method_dirty_1;
    
    static {
        try {
            java.rmi.server.RemoteRef.class.getMethod("invoke", new java.lang.Class[] { java.rmi.Remote.class, java.lang.reflect.Method.class, java.lang.Object[].class, long.class });
            useNewInvoke = true;
            $method_clean_0 = gnu.java.rmi.dgc.DGCImpl.class.getMethod("clean", new java.lang.Class[] {java.rmi.server.ObjID[].class, long.class, java.rmi.dgc.VMID.class, boolean.class});
            $method_dirty_1 = gnu.java.rmi.dgc.DGCImpl.class.getMethod("dirty", new java.lang.Class[] {java.rmi.server.ObjID[].class, long.class, java.rmi.dgc.Lease.class});
            
        }
        catch (java.lang.NoSuchMethodException e) {
            useNewInvoke = false;
        }
    }
    
    public DGCImpl_Stub() {
        super();
    }
    public DGCImpl_Stub(java.rmi.server.RemoteRef ref) {
        super(ref);
    }
    
    public void clean(java.rmi.server.ObjID[] $param_0, long $param_1, java.rmi.dgc.VMID $param_2, boolean $param_3) throws java.rmi.RemoteException {
        try {
            if (useNewInvoke) {
                ref.invoke(this, $method_clean_0, new java.lang.Object[] {$param_0, new java.lang.Long($param_1), $param_2, new java.lang.Boolean($param_3)}, -5803803475088455571L);
            }
            else {
                java.rmi.server.RemoteCall call = ref.newCall((java.rmi.server.RemoteObject)this, operations, 0, interfaceHash);
                try {
                    java.io.ObjectOutput out = call.getOutputStream();
                    out.writeObject($param_0);
                    out.writeLong($param_1);
                    out.writeObject($param_2);
                    out.writeBoolean($param_3);
                }
                catch (java.io.IOException e) {
                    throw new java.rmi.MarshalException("error marshalling arguments", e);
                }
                ref.invoke(call);
                try {
                    java.io.ObjectInput in = call.getInputStream();
                }
                catch (java.io.IOException e) {
                    throw new java.rmi.UnmarshalException("error unmarshalling return", e);
                }
                finally {
                    ref.done(call);
                }
            }
        }
        catch (java.rmi.RemoteException e) {
            throw e;
        }
        catch (java.lang.Exception e) {
            throw new java.rmi.UnexpectedException("undeclared checked exception", e);
        }
    }
    
    public java.rmi.dgc.Lease dirty(java.rmi.server.ObjID[] $param_0, long $param_1, java.rmi.dgc.Lease $param_2) throws java.rmi.RemoteException {
        try {
            if (useNewInvoke) {
                java.lang.Object $result = ref.invoke(this, $method_dirty_1, new java.lang.Object[] {$param_0, new java.lang.Long($param_1), $param_2}, -8139341527526761862L);
                return ((java.rmi.dgc.Lease)$result);
            }
            else {
                java.rmi.server.RemoteCall call = ref.newCall((java.rmi.server.RemoteObject)this, operations, 1, interfaceHash);
                try {
                    java.io.ObjectOutput out = call.getOutputStream();
                    out.writeObject($param_0);
                    out.writeLong($param_1);
                    out.writeObject($param_2);
                }
                catch (java.io.IOException e) {
                    throw new java.rmi.MarshalException("error marshalling arguments", e);
                }
                ref.invoke(call);
                java.rmi.dgc.Lease $result;
                try {
                    java.io.ObjectInput in = call.getInputStream();
                    $result = (java.rmi.dgc.Lease)in.readObject();
                    return ($result);
                }
                catch (java.io.IOException e) {
                    throw new java.rmi.UnmarshalException("error unmarshalling return", e);
                }
                finally {
                    ref.done(call);
                }
            }
        }
        catch (java.rmi.RemoteException e) {
            throw e;
        }
        catch (java.lang.Exception e) {
            throw new java.rmi.UnexpectedException("undeclared checked exception", e);
        }
    }
    
}
