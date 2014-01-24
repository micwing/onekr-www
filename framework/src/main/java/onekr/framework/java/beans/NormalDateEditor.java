/**
 * @Project: main-framework
 * @File: CustomDateEditor.java
 * @package onekr.framework.springmvc.annotation
 * @Description:
 * @author micwing
 * @date 2013-6-7 下午5:38:22
 * @version V1.0
 *
 * Copyright (c) 2013 OneKr Soft Studio. All Rights Reserved.
 *
 * Copying of this document or code and giving it to others and the
 * use or communication of the contents thereof, are forbidden without
 * expressed authority. Offenders are liable to the payment of damages.
 * All rights reserved in the event of the grant of a invention patent or the
 * registration of a utility model, design or code.
 */
package onekr.framework.java.beans;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

/** 
 * @ClassName: CustomDateEditor 
 * @Description: 
 * @author micwing
 * @date 2013-6-7 下午5:38:22 
 */
public class NormalDateEditor extends PropertyEditorSupport{

	private final DateFormat dateFormat;
	private final DateFormat dateFormat2;

	private final boolean allowEmpty;

	private final int exactDateLength;


	public NormalDateEditor() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat2.setLenient(false);
		
		this.dateFormat = dateFormat;
		this.dateFormat2 = dateFormat2;
		this.allowEmpty = true;
		this.exactDateLength = -1;
	}

	/**
	 * Parse the Date from the given text, using the specified DateFormat.
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (this.allowEmpty && !StringUtils.hasText(text)) {
			// Treat empty String as null value.
			setValue(null);
		}
		else if (text != null && this.exactDateLength >= 0 && text.length() != this.exactDateLength) {
			throw new IllegalArgumentException(
					"Could not parse date: it is not exactly" + this.exactDateLength + "characters long");
		}
		else {
			try {
				if (!text.contains(":")) {
					setValue(dateFormat2.parse(text));
				} else {					
					setValue(this.dateFormat.parse(text));
				}
			}
			catch (ParseException ex) {
				throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
			}
		}
	}

	/**
	 * Format the Date as String, using the specified DateFormat.
	 */
	@Override
	public String getAsText() {
		Date value = (Date) getValue();
		return (value != null ? this.dateFormat.format(value) : "");
	}

}
