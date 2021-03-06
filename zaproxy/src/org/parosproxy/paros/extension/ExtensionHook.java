/*
 *
 * Paros and its related class files.
 *
 * Paros is an HTTP/HTTPS proxy for assessing web application security.
 * Copyright (C) 2003-2004 Chinotec Technologies Company
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the Clarified Artistic License
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * Clarified Artistic License for more details.
 *
 * You should have received a copy of the Clarified Artistic License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
// ZAP: 2012/12/19 Code Cleanup: Moved array brackets from variable name to type
// ZAP: 2012/12/20 Added listener setter for persistentConnectionListenerList.
// ZAP: 2013/01/16 Issue 453: Dynamic loading and unloading of add-ons
// ZAP: 2013/04/14 Issue 608: Rename the method ExtensionHook.addSiteMapListner to addSiteMapListener
// ZAP: 2013/05/02 Re-arranged all modifiers into Java coding standard order
// ZAP: 2014/03/23 Issue 1022: Proxy - Allow to override a proxied message
// ZAP: 2014/08/17 Issue 1062: Added scannerhook to be added by extensions. 

package org.parosproxy.paros.extension;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.parosproxy.paros.common.AbstractParam;
import org.parosproxy.paros.core.proxy.OverrideMessageProxyListener;
import org.parosproxy.paros.core.proxy.ProxyListener;
import org.parosproxy.paros.core.scanner.ScannerHook;
import org.parosproxy.paros.model.Model;
import org.zaproxy.zap.PersistentConnectionListener;
import org.zaproxy.zap.extension.AddonFilesChangedListener;
import org.zaproxy.zap.view.SiteMapListener;


public class ExtensionHook {

    private ExtensionHookMenu hookMenu = new ExtensionHookMenu();
    private ExtensionHookView hookView = new ExtensionHookView();
    private Model model = null;
    private Vector<OptionsChangedListener> optionsListenerList = new Vector<>();

    private Vector<ProxyListener> proxyListenerList = new Vector<>();
    private List<OverrideMessageProxyListener> overrideMessageProxyListenersList = new ArrayList<>();
    private Vector<SessionChangedListener> sessionListenerList = new Vector<>();
    private Vector<AbstractParam> optionsParamSetList = new Vector<>();
    // ZAP: Added support for site map listeners
    private Vector<SiteMapListener> siteMapListenerList = new Vector<>();
    // ZAP: Added support for Scanner Hooks
    private Vector<ScannerHook> scannerHookList = new Vector<>();
    private Vector<PersistentConnectionListener> persistentConnectionListenerList = new Vector<>();
    private List<AddonFilesChangedListener> addonFilesChangedListenerList = new ArrayList<>(); 
    
    private ViewDelegate view = null;
    private CommandLineArgument[] arg = new CommandLineArgument[0];

    public ExtensionHook(Model model, ViewDelegate view) {
        this.view = view;
        this.model = model;
    }

    public void addOptionsChangedListener(OptionsChangedListener listener) {
        optionsListenerList.add(listener);
    }

    public void addOptionsParamSet(AbstractParam paramSet) {
        optionsParamSetList.add(paramSet);
    }

    public void addProxyListener(ProxyListener listener) {
        proxyListenerList.add(listener);
    }
    
    public void addSessionListener(SessionChangedListener listener) {
        sessionListenerList.add(listener);
    }

    /**
     * @deprecated Replaced by the method {@link #addSiteMapListener(SiteMapListener)}. 
     * It will be removed in a future release.
     */
    @Deprecated
    public void addSiteMapListner(SiteMapListener listener) {
    	siteMapListenerList.add(listener);
    }

    public void addSiteMapListener(SiteMapListener listener) {
        siteMapListenerList.add(listener);
    }
    
    // ZAP: add a scanner hook
    public void addScannerHook(ScannerHook hook)
    {
    	
    	scannerHookList.add(hook);
    }

    public void addPersistentConnectionListener(PersistentConnectionListener listener) {
        persistentConnectionListenerList.add(listener);
    }

    public void addCommandLine(CommandLineArgument[] arg) {
        this.arg = arg;
    }
    
    public void addAddonFilesChangedListener(AddonFilesChangedListener listener) {
    	addonFilesChangedListenerList.add(listener);
    }

    /**
     * @return Returns the hookMenu.
     */
    public ExtensionHookMenu getHookMenu() {
        return hookMenu;
    }
    /**
     * @return Returns the hookView.
     */
    public ExtensionHookView getHookView() {
        return hookView;
    }
    /**
     * @return Returns the model.
     */
    public Model getModel() {
        return model;
    }

    /**
     * @return Returns the optionsListenerList.
     */
    public Vector<OptionsChangedListener> getOptionsChangedListenerList() {
        return optionsListenerList;
    }

    public Vector<AbstractParam> getOptionsParamSetList() {
        return optionsParamSetList;
    }

    /**
     * @return Returns the proxyListenerList.
     */
    public Vector<ProxyListener> getProxyListenerList() {
        return proxyListenerList;
    }

    /**
     * @return Returns the sessionListenerList.
     */
    public Vector<SessionChangedListener> getSessionListenerList() {
        return sessionListenerList;
    }

    public Vector<SiteMapListener> getSiteMapListenerList() {
        return siteMapListenerList;
    }
    
    // ZAP: get all scannerhooks (used by extensionloader and the scanner)
    public Vector<ScannerHook> getScannerHookList() {
    	return scannerHookList;
    }

    public Vector<PersistentConnectionListener> getPersistentConnectionListener() {
        return persistentConnectionListenerList;
    }
    
    /**
     * @return Returns the view.
     */
    public ViewDelegate getView() {
        return view;
    }

    public CommandLineArgument[] getCommandLineArgument() {
        return arg;
    }

	public List<AddonFilesChangedListener> getAddonFilesChangedListener() {
		return addonFilesChangedListenerList;
	}

    public List<OverrideMessageProxyListener> getOverrideMessageProxyListenerList() {
        return overrideMessageProxyListenersList;
    }
}
