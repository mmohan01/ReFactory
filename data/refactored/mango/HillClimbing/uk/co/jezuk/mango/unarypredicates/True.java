package uk.co.jezuk.mango.unarypredicates;

import uk.co.jezuk.mango.Predicate;

public enum True implements Predicate<Object> {
    
     INSTANCE;

    public abstract boolean test(Object x) { return true; }
} // True
