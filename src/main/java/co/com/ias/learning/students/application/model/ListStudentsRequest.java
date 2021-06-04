package co.com.ias.learning.students.application.model;

import co.com.ias.learning.students.application.commons.operation.ApplicationRequest;

public class ListStudentsRequest implements ApplicationRequest {
    private final Integer limit;
    private final Integer skip;

    public ListStudentsRequest(Integer limit, Integer skip) {
        this.limit = limit;
        this.skip = skip;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getSkip() {
        return skip;
    }
}
