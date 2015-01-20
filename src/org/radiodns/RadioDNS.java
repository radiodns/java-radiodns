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

/**
 * This class is used to return a Service object for a given set of broadcast
 * parameters. The Service can then be used to obtain available RadioDNS
 * applications.
 * 
 * @author Byrion Smith <byrion.smith@thisisglobal.com>
 * @version 1.0.3
 */
public class RadioDNS {

	public static final String RADIOEPG = "radioepg";
	public static final String RADIOTAG = "radiotag";
	public static final String RADIOVIS = "radiovis";
	public static final String RADIOVIS_HTTP = "radiovis-http";

	static final String[] KNOWN_APPLICATIONS = { RADIOEPG, RADIOTAG, RADIOVIS, RADIOVIS_HTTP };

	String mDNSHostname;
	
	public RadioDNS() {
		mDNSHostname = null;
	}
	
	/**
	 * Supply a DNS server hostname to query when performing lookups
	 * 
	 * @param hostname	DNS Server to query
	 */
	public RadioDNS(String hostname) {
		mDNSHostname = hostname;
	}
	
	/**
	 * Lookup a Service based on FM broadcast parameters
	 * 
	 * @param country			Global Country Code (GCC) or ISO 3166-1 alpha-2 country code
	 * @param pi				Programme Identification (PI) value
	 * @param frequency			Frequency value in KHz
	 * @return					Service
	 * @throws LookupException
	 */
	public Service lookupFMService(String country, String pi, int frequency)
			throws LookupException {
		Service s = new FMService(country, pi, frequency);
		if (mDNSHostname != null)
			s.setDNSHostname(mDNSHostname);
		return s;
	}

	/**
	 * Lookup a Service based on DAB broadcast parameters with Packet Address
	 * 
	 * @param gcc		Global Country Code (GCC)
	 * @param eid		Ensemble Identifier (EId)
	 * @param sid		Service Identifer (SId)
	 * @param scids		Service Component Identifer within the Service (SCIdS)
	 * @param pa		Packet Address (PA)
	 * @return			Service
	 * @throws LookupException
	 */
	public Service lookupDABService(String gcc, String eid, String sid,
			String scids, int pa) throws LookupException {
		Service s = new DABService(gcc, eid, sid, scids, pa);
		if (mDNSHostname != null)
			s.setDNSHostname(mDNSHostname);
		return s;
	}

	/**
	 * Lookup a Service based on DAB broadcast parameters with X-Pad
	 * 
	 * @param gcc		Global Country Code (GCC)
	 * @param eid		Ensemble Identifier (EId)
	 * @param sid		Service Identifer (SId)
	 * @param scids		Service Component Identifer within the Service (SCIdS)
	 * @param xpad		X-PAD Applicaton Type (AppTy) and User Applicaton type
	 * @return			Service
	 * @throws LookupException
	 */
	public Service lookupDABService(String gcc, String eid, String sid,
			String scids, String xpad) throws LookupException {
		Service s = new DABService(gcc, eid, sid, scids, xpad);
		if (mDNSHostname != null)
			s.setDNSHostname(mDNSHostname);
		return s;
	}

	/**
	 * Lookup a Service based on DAB broadcast parameters
	 * 
	 * @param gcc		Global Country Code (GCC)
	 * @param eid		Ensemble Identifier (EId)
	 * @param sid		Service Identifer (SId)
	 * @param scids		Service Component Identifer within the Service (SCIdS)
	 * @return			Service
	 * @throws LookupException
	 */
	public Service lookupDABService(String gcc, String eid, String sid,
			String scids) throws LookupException {
		Service s = new DABService(gcc, eid, sid, scids);
		if (mDNSHostname != null)
			s.setDNSHostname(mDNSHostname);
		return s;
	}

	/**
	 * Lookup a Service based on AM broadcast parameters
	 * 
	 * @param type		Type of AM Service (either "drm" or "amss")
	 * @param sid		SID value for AM Service
	 * @return			Service
	 * @throws LookupException
	 */
	public Service lookupAMService(String type, String sid)
			throws LookupException {
		Service s = new AMService(type, sid);
		if (mDNSHostname != null)
			s.setDNSHostname(mDNSHostname);
		return s;
	}

	/**
	 * Lookup a Service based on HD broadcast parameters
	 * 
	 * @param tx		Transmitter identifier
	 * @param cc		Country code
	 * @return			Service
	 * @throws LookupException
	 */
	public Service lookupHDService(String tx, String cc) throws LookupException {
		Service s = new HDService(tx, cc);
		if (mDNSHostname != null)
			s.setDNSHostname(mDNSHostname);
		return s;
	}

	/**
	 * Return a Service object on which to obtain RadioDNS applications using 
	 * the authoritative FQDN parameter
	 * 
	 * @param authoritativeFqdn		Authoritative FQDN String
	 * @return						Service
	 * @throws LookupException
	 */
	public Service lookupIPService(String authoritativeFqdn)
			throws LookupException {
		Service s = new IPService(authoritativeFqdn);
		if (mDNSHostname != null)
			s.setDNSHostname(mDNSHostname);
		return s;
	}
}
