package ar.com.nybble.futbol.view.util;
/*
*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/


import java.io.Serializable;



/**
* Es el objeto que contiene cada nodo del arbol, se encarga de guaradar el contenido del
* nodo y de setear las propiedades.
* 
* @author Matej Knopp, notarip
*/

public class ModelBean implements Serializable
{
   private String nombre;
   private Object content = null;
   private boolean linkiable = false;
   

   /**
    * Creates the bean.
    * 
    * @param s, content
    *            
    */
   public ModelBean(String s, Object content)
   {
       this.setNombre(s);
       this.setContent(content);
       if (content instanceof Class)
    	   this.setLinkiable(true);
  }
   
   	/**
    * @param s
    */
public ModelBean(String s)
   {
       this.setNombre(s);
       
   }

   /**
    * Returns the first property.
    * 
    * @return First property
    */
   public String getNombre()
   {
       return nombre;
   }

   /**
    * Sets the value of the name
    * 
    * @param name
    *            
    */
   public void setNombre(String nombre)
   {
       this.nombre = nombre;
   }
   @Override
	public String toString() {
		return this.getNombre();
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
	public Object getContent() {
		return content;
	}
	
	public boolean isLinkiable(){
		return linkiable;
		
	}

	private void setLinkiable(boolean linkiable) {
		this.linkiable = linkiable;
	}

}
