/*
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
package ar.com.nybble.futbol.view;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.LinkLoopException;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.tree.AbstractTree;
import org.apache.wicket.markup.html.tree.BaseTree;
import org.apache.wicket.markup.html.tree.LinkTree;
import org.apache.wicket.markup.html.tree.BaseTree.ILinkCallback;
import org.apache.wicket.model.PropertyModel;

import ar.com.nybble.futbol.CambioDeClub;
import ar.com.nybble.futbol.Jugador;
import ar.com.nybble.futbol.view.util.MenuList;
import ar.com.nybble.futbol.view.util.ModelBean;

/**
 * Base page that serves as a template for pages that inherit from it. Doesn't have to be abstract,
 * but was made abstract here to stress the fact that this page is not meant for direct use.
 * 
 * @author Eelco Hillenius
 */
public abstract class TemplatePage extends WebPage
{
    /** title of the current page. */
    private String pageTitle = "F.U.L.B.O.";
    private BaseTree tree;

    protected AbstractTree getTree()
    {
        return tree;
    }
    
    /**
     * Constructor
     */
    public TemplatePage()
    {
        add(new Label("title", new PropertyModel<String>(this, "pageTitle")));
        tree = new LinkTree("tree", createTreeModel()){
        	@Override
        	protected void onNodeLinkClicked(Object node, BaseTree tree,
        			AjaxRequestTarget target) {
        		super.onNodeLinkClicked(node, tree, target);
        		ModelBean clase = (ModelBean) ((DefaultMutableTreeNode)node).getUserObject();
        		if (clase.isLinkiable())
        			setResponsePage(((Class)clase.getContent()));
        	}
        };
        
        
        
        add(tree);
        tree.getTreeState().collapseAll();
        
               
    	
        add(new AjaxLink("expandAll")
        {
            @Override
            public void onClick(AjaxRequestTarget target)
            {
                getTree().getTreeState().expandAll();
                getTree().updateTree(target);
            }
        });

        add(new AjaxLink("collapseAll")
        {
            @Override
            public void onClick(AjaxRequestTarget target)
            {
                getTree().getTreeState().collapseAll();
                getTree().updateTree(target);
            }
        });
        
    }

    /**
     * Gets the title.
     * 
     * @return title
     */
    public final String getPageTitle()
    {
        return pageTitle;
    }

    /**
     * Sets the title.
     * 
     * @param title
     *            title
     */
    public final void setPageTitle(String title)
    {
        pageTitle = title;
    }
    
    /**
     * Crea el arbol del menu, para agregar una raiz
     * hay que crear un <code>MenuList</code> con el nombre con
     * el que se quiere que apararezca en el arbol
     * Para agregar enlaces hay que utilizar <code>ModelBean</code>
     * con el nombre del enlace y la clase que se quiere enlazar.
     * 
     */
    protected TreeModel createTreeModel()
    {
        List<Object> l1 = new MenuList("Menu");
        l1.add(new ModelBean("Auditoria"));
        l1.add(new ModelBean("Sistemas"));
        
        List<Object> l2 = new MenuList("Consultas");
        l2.add(new ModelBean("Jugadores", ConsultaJugadoresPage.class));
        l2.add(new ModelBean("Clubs", ConsultaJugadoresPage.class));
        
        
        
        l1.add(l2);
        return convertToTreeModel(l1);
    }

    private TreeModel convertToTreeModel(List<Object> list)
    {
        TreeModel model = null;
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(new ModelBean(((MenuList) list).getNombre()));
        add(rootNode, list);
        model = new DefaultTreeModel(rootNode);
        return model;
    }

    private void add(DefaultMutableTreeNode parent, List<Object> sub)
    {
        for (Iterator<Object> i = sub.iterator(); i.hasNext();)
        {
            Object o = i.next();
            if (o instanceof List)
            {
                DefaultMutableTreeNode child = new DefaultMutableTreeNode(new ModelBean(((MenuList)o).getNombre()));
                parent.add(child);
                add(child, (List<Object>)o);
            }
            else
            {
                DefaultMutableTreeNode child = new DefaultMutableTreeNode(o);
                parent.add(child); 
                
            }
        }
    }
    
    
          
}