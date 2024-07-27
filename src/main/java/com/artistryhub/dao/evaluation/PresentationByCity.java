package com.artistryhub.dao.evaluation;

import com.artistryhub.model.City;
import com.artistryhub.model.Presentation;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;

public class PresentationByCity implements Evaluation {
	private static final long serialVersionUID = 1L;
	private City city;

	public PresentationByCity(City city) {
		this.city = city;
	}

	@Override
	public void evaluate(Candidate candidate) {
		Presentation presentation = (Presentation) candidate.getObject();
		if (presentation.getCity().equals(city))
			candidate.include(true);
		else
			candidate.include(false);
	}
}