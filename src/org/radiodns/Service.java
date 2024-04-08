/*
 * Copyright (c) 2012 Global Radio UK Limited
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.    
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */

package org.radiodns;

import org.minidns.hla.DnssecResolverApi;
import org.minidns.hla.ResolverResult;
import org.minidns.record.CNAME;
import org.minidns.record.SRV;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents a Radio Service from which RadioDNS Applications can be resolved
 * 
 * @author Byrion Smith <byrion.smith@thisisglobal.com>
 * @version 1.0.3
 */
public abstract class Service {

	String mDNSHostname = null;
	
	/**
	 * Get RadioDNS FQDN
	 * 
	 * @return		RadioDNS FQDN
	 */
	public abstract String getRadioDNSFqdn();

	/**
	 * Get Authoritative FQDN
	 * 
	 * @return		Authoritative FQDN
	 */
	public String getAuthoritativeFqdn() {
		return resolveAuthoritativeFQDN();
	}

	/**
	 * Get given RadioDNS Application
	 * 
	 * @param applicationId		RadioDNS Application Identifier
	 * @return		RadioDNS Application
	 */
	public Application getApplication(String applicationId) {
		return resolveApplication(applicationId);
	}

	/**
	 * Get all known RadioDNS Applications
	 * 
	 * @return		Map of RadioDNS Applications
	 */
	public Map<String, Application> getApplications() throws LookupException {
		Map<String, Application> applications = new HashMap<String, Application>();
		for (String applicationId : RadioDNS.KNOWN_APPLICATIONS) {
			applications.put(applicationId, resolveApplication(applicationId));
		}
		return applications;
	}
	
	/**
	 * Supply a DNS server hostname to query when performing lookups
	 * 
	 * @param hostname	DNS Server to query
	 */
	public void setDNSHostname(String hostname) {
		mDNSHostname = hostname;
	}
	
	/**
	 * Get RadioDNS Application for the given Application ID
	 * 
	 * @param applicationId			RadioDNS Application Identifier
	 * @return
	 */
	Application resolveApplication(String applicationId) {
		return resolveApplication(applicationId, null);
	}
	
	/**
	 * Get RadioDNS Application for the given Application ID and Transport 
	 * Protocol 
	 * 
	 * @param applicationId			RadioDNS Application Identifier
	 * @param transportProtocol		Transport Protocol
	 * @return
	 */
	Application resolveApplication(String applicationId,
			String transportProtocol) {
		String authoritativeFqdn = getAuthoritativeFqdn();
		if (applicationId == null) {
			throw new IllegalArgumentException("Application ID is null");
		}
		if (transportProtocol == null) {
			transportProtocol = "TCP";
		}

		if (authoritativeFqdn == null) {
			return null;
		}

		String applicationFqdn = String.format("_%s._%s.%s",
				applicationId.toLowerCase(), transportProtocol.toLowerCase(),
				authoritativeFqdn);


		try {
			ResolverResult<SRV> result = DnssecResolverApi.INSTANCE.resolve(applicationFqdn, SRV.class);

			if (!result.wasSuccessful()) {
				//error
				return null;
			}

			if (result.isAuthenticData()) {
				return null;
			}

			Set<SRV> srvs = result.getAnswers();
			ArrayList<Record> records = new ArrayList<>();
			for (SRV srv : srvs) {
				records.add(new Record(srv));
			}
			return new Application(applicationId, records);

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Resolve Authoritative FQDN for the service
	 * 
	 * @return			Authoritative FQDN String
	 */
	public String resolveAuthoritativeFQDN() {
		try {
			ResolverResult<CNAME> result = DnssecResolverApi.INSTANCE.resolve(getRadioDNSFqdn(), CNAME.class);

			if (!result.wasSuccessful()) {
				//error
				return null;
			}

			if (result.isAuthenticData()) {
				return null;
			}

			Set<CNAME> cnames = result.getAnswers();
			if (cnames.size() > 0) {
				CNAME cname = cnames.iterator().next();
				return cname.target.toString();
			}

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}


		return null;
	}
}
