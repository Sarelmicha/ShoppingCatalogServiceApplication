package acs.logic.utils;

public enum FilterType {
    NAME("byName"), MIN_PRICE("byMinPrice"), MAX_PRICE("byMaxPrice"), CATEGORY("byCategory");

    private final String filter;

    FilterType(final String filter){
        this.filter=filter;
    }

    @Override
    public String toString() {
        return filter;
    }
}

