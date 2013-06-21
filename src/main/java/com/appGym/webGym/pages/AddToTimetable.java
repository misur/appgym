package com.appGym.webGym.pages;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.services.PropertyAccess;

import com.appGym.webGym.annotations.AdminAccess;
import com.appGym.webGym.annotations.TrainerAccess;
import com.appGym.webGym.dao.TimetableDAO;
import com.appGym.webGym.dao.TrainingDAO;
import com.appGym.webGym.entities.Day;
import com.appGym.webGym.entities.TimetableE;
import com.appGym.webGym.entities.Training;
import com.appGym.webGym.model.user.GenericSelectModel;

@AdminAccess
@TrainerAccess
public class AddToTimetable {

	@Inject
	private TimetableDAO timetableDAO;

	@Inject
	private TrainingDAO trainingDAO;

	@Inject
	private PropertyAccess access;

	@Property
	private TimetableE timetable;

	@Property
	private Day day;

	private GenericSelectModel<Day> days;

	@Property
	private String time;

	private GenericSelectModel<String> times;

	@Property
	private Training training;

	private GenericSelectModel<Training> trainings;

	public GenericSelectModel<Day> getDays() {
		List<Day> arr = Arrays.asList(Day.MONDAY, Day.TUESDAY, Day.WENESDAY,
				Day.THURSDAY, Day.FRIDAY, Day.SATURDAY, Day.SUNDAY);
		return new GenericSelectModel<Day>(arr, Day.class, null, null, access);
	}

	public GenericSelectModel<Training> getTrainings() {
		List<Training> arr = trainingDAO.findAll();
		return new GenericSelectModel<Training>(arr, Training.class, null,
				null, access);
	}

	public GenericSelectModel<String> getTimes() {
		List<String> arr = Arrays.asList("8:00-9:00", "9:00-10:00",
				"10:00-11:00", "11:00-12:00", "17:00-18:00", "18:00-19:00",
				"19:00-20:00", "20:00-21:00");
		return new GenericSelectModel<String>(arr, String.class, null, null,
				access);
	}

	Object onSubmitFromForm() {
		timetable = new TimetableE();
		timetable.setDay(day);
		timetable.setTime(time);
		timetable.setTraining(training);
		if (timetable.getDay() == null || timetable.getTime() == null
				|| timetable.getTraining() == null) {
			return null;
		} else {
			timetableDAO.save(timetable);
			return Timetable.class;
		}

	}

}
