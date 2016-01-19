package uk.co.jezuk.mango.binarypredicates;

import uk.co.jezuk.mango.BinaryPredicate;

public abstract class And<T1, T2>  implements BinaryPredicate<T1, T2> {
    public And(BinaryPredicate<T1, T2> pred1, BinaryPredicate<T1, T2> pred2) { p1_ = pred1; p2_ = pred2;
    }
    public abstract boolean test(T1 x, T2 y) { return p1_.test(x, y) && p2_.test(x, y); }

    private BinaryPredicate<T1, T2> p1_;
    private BinaryPredicate<T1, T2> p2_;
} // And
