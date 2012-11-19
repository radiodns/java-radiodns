package org.radiodns;

/**
 * @author Byrion Smith <byrion.smith@thisisglobal.com>
 * @version 1.0.2
 */
public class FMService extends Service {

	/**
	 * Global Country Code (GCC) value
	 */
	private String mGcc;
	/**
	 * ISO 3166-1 alpha-2 country code value
	 */
	private String mIso3166CountryCode;
	/**
	 * Programme Identification (PI) value
	 */
	private String mPi;
	/**
	 * Frequency value in KHz
	 */
	private int mFrequency;

	/**
	 * Class constructor
	 * 
	 * @param country		Global Country Code (GCC) or ISO 3166-1 alpha-2 country code
	 * @param piCode		Programme Identification (PI)
	 * @param frequency		Frequency value in KHz
	 * @throws LookupException
	 */
	public FMService(String country, String piCode, int frequency)
			throws LookupException {
		/**
		 * check for required variables
		 */
		if (country == null || piCode == null) {
			throw new LookupException("Required values are missing.");
		}

		/**
		 * country value
		 */
		if (country.length() == 2) {
			mGcc = null;
			mIso3166CountryCode = country;
		} else if (country.matches("(?i)^[0-9A-F]{3}$")) {
			mGcc = country;
			mIso3166CountryCode = null;
		} else {
			throw new LookupException(
					"Invalid country value. Must be either a ISO 3166-1 alpha-2 country code or valid hexadecimal value of a RDS Country Code concatanated with a RDS Extended Country Code (ECC).");
		}

		/**
		 * pi value
		 */
		if (piCode.matches("(?i)^[0-9A-F]{4}$")
				&& (mIso3166CountryCode != null || piCode.charAt(0) == mGcc
						.charAt(0))) {
			mPi = piCode;
		} else {
			throw new LookupException(
					"Invalid PI value. Must be a valid hexadecimal RDS Programme Identifier (PI) code and the first character must match the first character of the combined RDS Country Code and RDS Extended Country Code (ECC) value (if supplied).");
		}

		/**
		 * frequency value
		 */
		if (frequency >= 76000 && frequency <= 108000) {
			this.mFrequency = frequency;
		} else {
			throw new LookupException(
					"Invalid frequency value. Must be int between the values 76000 and 108000.");
		}
	}
	
	public String getCountry() {
		return (mGcc != null) ? mGcc : mIso3166CountryCode;
	}
	
	public String getPiCode() {
		return mPi;
	}
	
	public int getFrequency() {
		return mFrequency;
	}
	
	/*
	 * @see org.radiodns.Service#getRadioDNSFqdn()
	 */
	@Override
	public String getRadioDNSFqdn() {
		String country = (mGcc != null) ? mGcc : mIso3166CountryCode;

		String fqdn = String
				.format("%s.%s.%s.fm.radiodns.org",
						String.format("%5s", (int) (mFrequency / 10)).replace(
								" ", "0"), mPi, country);
		fqdn = fqdn.toLowerCase();

		return fqdn;
	}
}
