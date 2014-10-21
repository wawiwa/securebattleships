/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package web.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.Identity;
import org.picketlink.Identity.AuthenticationResult;
import org.picketlink.annotations.PicketLink;
import org.picketlink.authentication.BaseAuthenticator;

import ejb.domain.Player;
import ejb.service.PlayerServiceLocal;


@RequestScoped
@Named("loginController")
public class LoginController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
    private Identity identity;

    @Inject
    private FacesContext facesContext;
    
    @Inject
	private Logger LOG;
    
    @Inject
    private PlayerServiceLocal psl;
    
    @Inject
    private GameClientController gcc;

    public void login() {
    	LOG.info("login() called");
		AuthenticationResult result = identity.login();
		if (AuthenticationResult.FAILED.equals(result)) {
			facesContext.addMessage(
					null,
					new FacesMessage("Authentication was unsuccessful.  Please check your username and password "
							+ "before trying again."));
		}


		if (AuthenticationResult.SUCCESS.equals(result)) {
			try {
				LOG.info("accountId: "+identity.getAccount().getId());
				Player player = psl.getPdl().findPlayerByUserId(identity.getAccount().getId());
				// pass a player to the gcc
				LOG.info("Player found: "+player);
				gcc.postInit(player);
				//facesContext.getExternalContext().redirect("dashboard.xhtml");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
    
    public String gotoDashboard() {
    	return "dashboard?faces-redirect=true";
    }


}
