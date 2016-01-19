package uk.co.jezuk.mango.unarypredicates;

import uk.co.jezuk.mango.Predicate;

public abstract class Not<T>  implements Predicate<T> {
    public Not(Predicate<T> p) { p_ = p; }

    public abstract boolean test(T x) { return !p_.test(x); }

    private Predicate<T> p_;
} // Not
