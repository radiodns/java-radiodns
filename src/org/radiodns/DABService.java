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
 * @author Byrion Smith <byrion.smith@thisisglobal.com>
 * @version 1.0.3
 */
public class DABService extends Service {

	/*
	 * Service Component Identifer within the Service (SCIdS) value
	 */
	private String mScids;
	/*
	 * Service Identifer (SId) value
	 */
	private String mSid;
	/*
	 * Ensemble Identifier (EId) value
	 */
	private String mEid;
	/*
	 * Global Country Code (GCC) value
	 */
	private String mGcc;
	/*
	 * X-PAD Applicaton Type (AppTy) and User Applicaton type value
	 */
	private String mXpad;
	/*
	 * Packet Address (PA) value
	 */
	private Integer mPa;

	public DABService(String gcc, String eid, String sid, String scids, int pa) throws LookupException {
		this(gcc, eid, sid, scids);
		
		if (pa > 0 && pa < 1023) {
			mPa = pa;
		} else {
			throw new LookupException(
					"Invalid data value. Must be a valid Packet Address integer.");
		}
	}
	
	public DABService(String gcc, String eid, String sid, String scids, String xpad) throws LookupException {
		this(gcc, eid, sid, scids);
		
		if (xpad.equals(null)) {
			throw new LookupException("Required values are missing.");
		}
		
		if (xpad.matches("(?i)^[0-9A-F]{2}-[0-9A-F]{3}$")) {
			mXpad = xpad;
		}
	}
	
	public DABService(String gcc, String eid, String sid, String scids) throws LookupException {
		/*
		 * Check for required variables
		 */
		if (gcc == null || eid == null || sid == null || scids == null) {
			throw new LookupException("Required values are missing.");
		}
		
		/*
		 * Validate gcc value
		 */
		if (gcc.matches("(?i)^[0-9A-F]{3}$")) {
			mGcc = gcc;
		} else {
			throw new LookupException(
					"Invalid Global Country Code (GCC) value. Must be a valid 3-character hexadecimal.");
		}
		
		/*
		 * Validate eid value
		 */
		if (eid.matches("(?i)^[0-9A-F]{4}$")) {
			mEid = eid;
		} else {
			throw new LookupException(
					"Invalid Ensemble Identifier (EId) value. Must be a valid 4-character hexadecimal.");
		}
		
		/*
		 * Validate sid value
		 */
		if (sid.matches("(?i)^[0-9A-F]{4}$|^[0-9A-F]{8}$")) {
			mSid = sid;
		} else{
			throw new LookupException(
					"Invalid Service Identifier (SId) value. Must be a valid 4 or 8-character hexadecimal.");
		}
		
		/*
		 * Validate scids value
		 */
		if (scids.matches("(?i)^[0-9A-F]{1}$|^[0-9A-F]{3}$")) {
			mScids = scids;
		} else {
			throw new LookupException(
					"Invalid Service Component Identifier within the Service (SCIdS) value. Must be a valid 1 or 3-character hexadecimal.");
		}
		
		mXpad = null;
		mPa = -1;
	}
	
	public String getScids() {
		return mScids;
	}
	
	public String getSid() {
		return mSid;
	}
	
	public String getEid() {
		return mEid;
	}
	
	public String getGcc() {
		return mGcc;
	}
	
	public String getXpad() {
		return mXpad;
	}
	
	public int getPa() {
		return mPa;
	}

	@Override
	public String getRadioDNSFqdn() {
		String fqdn = String
				.format("%s.%s.%s.%s.dab.radiodns.org", mScids, mSid, mEid, mGcc);
		if (mXpad != null || mPa > -1) {
			fqdn = String.format("%s.%s", (mXpad != null) ? mXpad : mPa, fqdn);
		}
		fqdn = fqdn.toLowerCase();

		return fqdn;
	}
}