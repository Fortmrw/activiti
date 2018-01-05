package cn.zhr.test;

import java.util.function.Supplier;

public interface Java8 {
	void sayGuojian();
	default String read(){
		return "郭建";
	}
	static Defaulable create( Supplier< Defaulable > supplier ) {
        return supplier.get();
    }
	
	
}

interface Defaulable {
    // Interfaces now allow default methods, the implementer may or 
    // may not implement (override) them.
    default String notRequired() { 
        return "Default implementation"; 
    }        
}

class DefaultableImpl implements Defaulable {
}

class OverridableImpl implements Defaulable {
    @Override
    public String notRequired() {
        return "Overridden implementation";
    }
}

interface DefaulableFactory {
    // Interfaces now allow static methods
    static Defaulable create( Supplier< Defaulable > supplier ) {
        return supplier.get();
    }
}