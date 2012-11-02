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
 * @version 1.0.1
 */
public class AMService extends Service {

	/*
	 * Type of AM Service (either "drm" or "amss")
	 */
	private String mType;
	/*
	 * SID value for AM Service
	 */
	private String mSid;

	public AMService(String type, String sid) throws LookupException {
		/*
		 * check for required variables
		 */
		if (type == null || sid == null) {
			throw new LookupException("Required values are missing.");
		}

		/*
		 * Validate type value
		 */
		if (type.equals("drm") || type.equals("amss")) {
			mType = type;
		} else {
			throw new LookupException(
					"Invalid type value. Must be either 'drm' (Digital Radio Mondiale) or 'amss' (AM Signalling System).");
		}

		/*
		 * Validate sid value
		 */
		if (sid.matches("(?i)^[0-9A-F]{6}$")) {
			mSid = sid;
		} else {
			throw new LookupException(
					"Invalid Service Identifier (SId) value. Must be a valid 6-character hexadecimal.");
		}
	}

	public String getType() {
		return mType;
	}
	
	public String getSid() {
		return mSid;
	}
	
	/*
	 * @see org.radiodns.Service#getRadioDNSFqdn()
	 */
	@Override
	public String getRadioDNSFqdn() {
		String fqdn = String.format("%s.%s.am.radiodns.org", mSid, mType);
		fqdn = fqdn.toLowerCase();

		return fqdn;
	}

}