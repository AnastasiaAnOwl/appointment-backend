package com.kunatava.appointment.propertyeditor;

import java.beans.PropertyEditorSupport;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.util.StringUtils;

import com.kunatava.appointment.model.DateBounds;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DateBoundsPropertyEditor extends PropertyEditorSupport {

	private final SimpleDateFormat dateFormat;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isEmpty(text)) {
			throw new IllegalArgumentException(DateBounds.class.getName() + " is null or empty.");
		}
		String[] dates = text.split("-");
		if (dates.length != 2) {
			throw new IllegalArgumentException(getWrongFormatMessage(text));
		}
		try {
			DateBounds dateBounds = parseDateBounds(dates[0], dates[1]);
			setValue(dateBounds);
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private String getWrongFormatMessage(String text) {
		return MessageFormat.format("Incorrect format of {0}: {1}. Should be: {2}-{2}", DateBounds.class.getName(),
				text, dateFormat.toPattern());
	}

	private DateBounds parseDateBounds(String start, String end) throws ParseException {
		return new DateBounds(dateFormat.parse(start), dateFormat.parse(end));
	}

}
