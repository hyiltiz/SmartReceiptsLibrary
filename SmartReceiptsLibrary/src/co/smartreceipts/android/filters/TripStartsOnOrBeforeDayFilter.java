package co.smartreceipts.android.filters;

import java.sql.Date;
import java.util.List;
import java.util.TimeZone;

import org.json.JSONException;
import org.json.JSONObject;

import co.smartreceipts.android.R;
import co.smartreceipts.android.model.TripRow;

public class TripStartsOnOrBeforeDayFilter implements Filter<TripRow> {

	private final static String DATE = "date";
	private final static String TIMEZONE = "timezone";

	private final Date mDate;
	private final TimeZone mTimeZone;

	public TripStartsOnOrBeforeDayFilter(Date date, TimeZone timeZone) {
		if (date == null || timeZone == null)
			throw new IllegalArgumentException(
					"ReceiptOnOrAfterDayFilter requires non-null date and timezone");

		mDate = date;
		mTimeZone = timeZone;
	}

	public TripStartsOnOrBeforeDayFilter(JSONObject json) throws JSONException {
		mDate = new Date(json.getLong(DATE));
		mTimeZone = TimeZone.getTimeZone(json.getString(TIMEZONE));
	}

	@Override
	public boolean accept(TripRow t) {
		return FilterUtils.isOnOrBefore(t.getStartDate(), t.getStartTimeZone(), mDate, mTimeZone);
	}

	@Override
	public JSONObject getJsonRepresentation() throws JSONException {
		final JSONObject json = new JSONObject();
		json.put(FilterFactory.CLASS_NAME, this.getClass().getName());
		json.put(DATE, mDate.getTime());
		json.put(TIMEZONE, mTimeZone.getID());
		return json;
	}

	@Override
	public List<Filter<TripRow>> getChildren() {
		return null;
	}

	@Override
	public int getNameResource() {
		return R.string.filter_name_trip_starts_on_or_before;
	}

	@Override
	public FilterType getType() {
		return FilterType.Date;
	}

	@Override
	public int hashCode() {
		final int dtHash = (mDate == null) ? 0 : mDate.hashCode();
		final int tzHash = (mTimeZone == null) ? 0 : mTimeZone.getID().hashCode();
		final int prime = 31;
		int result = 1;
		result = prime * result + dtHash;
		result = prime * result + tzHash;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		TripStartsOnOrBeforeDayFilter other = (TripStartsOnOrBeforeDayFilter) obj;

		if (mDate == null) {
			if (other.mDate != null)
				return false;
		} else if (!mDate.equals(other.mDate))
			return false;

		if (mTimeZone == null) {
			if (other.mTimeZone != null)
				return false;
		} else if (!mTimeZone.equals(other.mTimeZone))
			return false;

		return true;
	}

}
