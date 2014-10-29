package app.util;

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

import static org.picketlink.idm.model.basic.BasicModel.addToGroup;
import static org.picketlink.idm.model.basic.BasicModel.grantGroupRole;
import static org.picketlink.idm.model.basic.BasicModel.grantRole;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.Group;
import org.picketlink.idm.model.basic.Role;
import org.picketlink.idm.model.basic.User;

import ejb.domain.GameStat;
import ejb.domain.Player;
import ejb.service.PlayerServiceImpl;
import ejb.service.PlayerServiceLocal;

/**
 * This startup bean creates a number of default users, groups and roles when the application is started.
 * 
 * @author Shane Bryzak
 */
@Singleton
@Startup
@ApplicationScoped
@Named("SecurityInitializer")
public class SecurityInitializer {
	
	@Inject
	private Logger LOG;

    @Inject
    private PartitionManager partitionManager;
    
    @Inject
    private PlayerServiceLocal psl;

    public void initPlayers() {
    	User wade = new User("wade");
    	wade.setEmail("wade@gmu.edu");
        psl.register(wade,new Password("wade")); 
        
    	User jack = new User("jack");
    	jack.setEmail("jack@gmu.edu");
    	psl.register(jack,new Password("jack")); 
        
    	User thierry = new User("thierry");
    	thierry.setEmail("thierry@gmu.edu");
    	psl.register(thierry,new Password("thierry")); 
    }
    
    @PostConstruct
    public void create() {

    	System.out.println("INITIALLINSIJIOJDSKL:DFJKL:JFKL:");
    	LOG.info("initializing security");
    	
    	IdentityManager identityManager = this.partitionManager.createIdentityManager();
  
        
        
        // Create user john
        User john = new User("john");
        john.setEmail("john@acme.com");
        john.setFirstName("John");
        john.setLastName("Smith");

        

        identityManager.add(john);
        identityManager.updateCredential(john, new Password("demo"));

        // Create user mary
        User mary = new User("mary");
        mary.setEmail("mary@acme.com");
        mary.setFirstName("Mary");
        mary.setLastName("Jones");
        identityManager.add(mary);
        identityManager.updateCredential(mary, new Password("demo"));

        // Create user jane
        User jane = new User("jane");
        jane.setEmail("jane@acme.com");
        jane.setFirstName("Jane");
        jane.setLastName("Doe");
        identityManager.add(jane);
        identityManager.updateCredential(jane, new Password("demo"));

        // Create role "manager"
        Role manager = new Role("manager");
        identityManager.add(manager);

        // Create application role "superuser"
        Role superuser = new Role("superuser");
        identityManager.add(superuser);

        // Create group "sales"
        Group sales = new Group("sales");
        identityManager.add(sales);

        RelationshipManager relationshipManager = this.partitionManager.createRelationshipManager();

        // Make john a member of the "sales" group
        addToGroup(relationshipManager, john, sales);

        // Make mary a manager of the "sales" group
        grantGroupRole(relationshipManager, mary, manager, sales);

        // Grant the "superuser" application role to jane
        grantRole(relationshipManager, jane, superuser);
    }

	public PartitionManager getPartitionManager() {
		return partitionManager;
	}

	public void setPartitionManager(PartitionManager partitionManager) {
		this.partitionManager = partitionManager;
	}
    
}