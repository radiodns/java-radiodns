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

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xbill.DNS.CNAMERecord;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.SRVRecord;
import org.xbill.DNS.SimpleResolver;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

/**
 * Represents a Radio Service from which RadioDNS Applications can be resolved
 * 
 * @author Byrion Smith <byrion.smith@thisisglobal.com>
 * @version 1.0
 */
public abstract class Service {

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
	 * @throws LookupException 
	 */
	public String getAuthoritativeFqdn() throws LookupException {
		return resolveAuthoritativeFQDN();
	}

	/**
	 * Get given RadioDNS Application
	 * 
	 * @param applicationId		RadioDNS Application Identifier
	 * @return		RadioDNS Application
	 * @throws LookupException
	 */
	public Application getApplication(String applicationId)
			throws LookupException {
		return resolveApplication(applicationId);
	}

	/**
	 * Get all known RadioDNS Applications
	 * 
	 * @return		Map of RadioDNS Applications
	 * @throws LookupException
	 */
	public Map<String, Application> getApplications() throws LookupException {
		Map<String, Application> applications = new HashMap<String, Application>();
		for (String applicationId : RadioDNS.KNOWN_APPLICATIONS) {
			applications.put(applicationId, resolveApplication(applicationId));
		}
		return applications;
	}
	
	/**
	 * Get RadioVIS Application for the given Application ID
	 * 
	 * @param applicationId			RadioDNS Application Identifier
	 * @return
	 * @throws LookupException
	 */
	private Application resolveApplication(String applicationId)
			throws LookupException {
		return resolveApplication(applicationId, null);
	}
	
	/**
	 * Get RadioVIS Application for the given Application ID and Transport 
	 * Protocol 
	 * 
	 * @param applicationId			RadioDNS Application Identifier
	 * @param transportProtocol		Transport Protocol
	 * @return
	 * @throws LookupException
	 */
	Application resolveApplication(String applicationId,
			String transportProtocol) throws LookupException {
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

		SimpleResolver resolver;
		try {
			resolver = new SimpleResolver();
		} catch (UnknownHostException e) {
			throw new LookupException("Error creating DNS Resolver", e);
		}

		Lookup lookup;
		try {
			lookup = new Lookup(applicationFqdn, Type.SRV);
		} catch (TextParseException e) {
			throw new LookupException("Error parsing DNS response", e);
		}
		lookup.setResolver(resolver);
		org.xbill.DNS.Record[] records = lookup.run();

		if (records != null) {
			List<Record> servers = new ArrayList<Record>();
			for (org.xbill.DNS.Record r : records) {
				if (r.getType() == Type.SRV) {
					servers.add(new Record((SRVRecord) r));
				}
			}

			return new Application(applicationId.toLowerCase(), servers);
		}

		return null;
	}
	
	/**
	 * Resolve Authoritative FQDN for the service
	 * 
	 * @return			Authoritative FQDN String
	 * @throws LookupException
	 */
	String resolveAuthoritativeFQDN() throws LookupException {
		try {
			SimpleResolver resolver = new SimpleResolver();
			Lookup lookup = new Lookup(getRadioDNSFqdn(), Type.CNAME);
			lookup.setResolver(resolver);
			org.xbill.DNS.Record[] records = lookup.run();

			if (records != null) {
				for (org.xbill.DNS.Record record : records) {
					if (record.getType() == Type.CNAME) {
						return ((CNAMERecord) record).getTarget().toString();
					}
				}
			}
		} catch (UnknownHostException e) {
			throw new LookupException("Error creating DNS Resolver", e);
		} catch (TextParseException e) {
			throw new LookupException("Error parsing DNS response", e);
		}

		return null;
	}
}
