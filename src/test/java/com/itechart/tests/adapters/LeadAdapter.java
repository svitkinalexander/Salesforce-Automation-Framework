package com.itechart.tests.adapters;

import com.itechart.models.Lead;
import com.itechart.models.ResponseStatus;

public class LeadAdapter extends BaseAdapter {

    public ResponseStatus create(Lead lead) {
        String response = post(API_BASE_URL + "/lead",
                jsonReader.toJson(lead), 201);
        return jsonReader.fromJson(response, ResponseStatus.class);
    }

    public Lead getLead(String leadId) {
        String response = get(API_BASE_URL + "/lead/" + leadId, 200);
        return jsonReader.fromJson(response, Lead.class);
    }
}