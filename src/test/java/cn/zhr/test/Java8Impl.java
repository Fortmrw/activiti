package cn.zhr.test;

public class Java8Impl implements Java8 {

	private static String str = new Java8Impl().read();
	@Override
	public void sayGuojian() {

	}
	public String read(){
		return str;
	}
	public static void main( String[] args ) {
	    Defaulable defaulable = DefaulableFactory.create( DefaultableImpl::new );
	    System.out.println( defaulable.notRequired() );

	    defaulable = DefaulableFactory.create( OverridableImpl::new );
	    System.out.println( defaulable.notRequired() );
	}

}
