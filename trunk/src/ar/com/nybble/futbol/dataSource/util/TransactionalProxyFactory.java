package ar.com.nybble.futbol.dataSource.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author notarip
 * Solo para utilizar en llamadas a metodos transaccionales
 * ya que su utilidad es el manejo de la integridad mediante
 * el rollback de la operacion en el caso de error
 *
 */
public class TransactionalProxyFactory implements InvocationHandler {
	 private Object target;
	    
	    private TransactionalProxyFactory(Object target) {
	        this.target = target;
	    }
	    
	    public static Object newInstance(Object target) {
	        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new TransactionalProxyFactory(target));
	    }
	    
	    public Object invoke(Object proxy, Method method, Object[] arguments)
	    throws Throwable {
	        Object value;
	        try {
	            HibernateUtil.beginTransaction();
	            value = method.invoke(this.target, arguments);
	            HibernateUtil.commitTransaction();
	        } catch (InvocationTargetException e) {
	            throw e.getTargetException();
	        } catch (Exception e) {
	            HibernateUtil.rollbackTransaction();
	            throw new RuntimeException("Imposible invocar método: "
	                    + e.getMessage());
	        } finally {
	            HibernateUtil.closeSession();
	        }
	        return value;
	    }

}
