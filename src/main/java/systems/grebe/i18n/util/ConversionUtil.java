package systems.grebe.i18n.util;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

public class ConversionUtil {

	public static final Converter<Double, Long> doubleToLongConverter = new Converter<>() {
		@Override
		public Result<Long> convertToModel(Double aDouble, ValueContext valueContext) {
			if (aDouble != null) {
				return Result.ok(aDouble.longValue());
			} else {
				return Result.ok(null);
			}
		}


		@Override
		public Double convertToPresentation(Long aLong, ValueContext valueContext) {
			if (aLong != null) {
				return aLong.doubleValue();
			} else {
				return null;
			}
		}
	};
}
